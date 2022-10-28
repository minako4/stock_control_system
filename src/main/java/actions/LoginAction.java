package actions;

import java.io.IOException;

import javax.servlet.ServletException;

import actions.views.StoreView;
import constants.AttributeConst;
import constants.ForwardConst;
import constants.MessageConst;
import constants.PropertyConst;
import services.StoreService;


/**
 * 認証に関する処理を行うActionクラス
 *
 */
public class LoginAction extends ActionBase {

    private StoreService service;

    /**
     * メソッドを実行する
     */
    @Override
    public void process() throws ServletException, IOException {

        service = new StoreService();

        //メソッドを実行
        invoke();

        service.close();
    }

    /**
     * ログイン画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void showLogin() throws ServletException, IOException {

        //CSRF対策用トークンを設定
        putRequestScope(AttributeConst.TOKEN, getTokenId());

        //セッションにフラッシュメッセージが登録されている場合はリクエストスコープに設定する
        String flush = getSessionScope(AttributeConst.FLUSH);
        if (flush != null) {
            putRequestScope(AttributeConst.FLUSH,flush);
            removeSessionScope(AttributeConst.FLUSH);
        }

        //ログイン画面を表示
        forward(ForwardConst.FW_LOGIN);
    }
    /**
     * ログイン処理を行う
     * @throws ServletException
     * @throws IOException
     */
    public void login() throws ServletException, IOException {

        String storeCode = getRequestParam(AttributeConst.STORE_STORE_CODE);
        String plainPass = getRequestParam(AttributeConst.STORE_PASS);
        String pepper = getContextScope(PropertyConst.PEPPER);

        //有効な店舗か認証する
        Boolean isValidStore = service.validateLogin(storeCode, plainPass, pepper);

        if (isValidStore) {
            //認証成功の場合

            //CSRF対策 tokenのチェック
            if (checkToken()) {

                //ログインした店舗のDBデータを取得
                StoreView sv = service.findOne(storeCode, plainPass, pepper);
                //セッションにログインした店舗を設定
                putSessionScope(AttributeConst.LOGIN_STORE, sv);
                //セッションにログイン完了のフラッシュメッセージを設定
                putSessionScope(AttributeConst.FLUSH, MessageConst.I_LOGINED.getMessage());
                //トップページへリダイレクト
                redirect(ForwardConst.ACT_TOP, ForwardConst.CMD_INDEX);
            }
        } else {
            //認証失敗の場合

            //CSRF対策用トークンを設定
            putRequestScope(AttributeConst.TOKEN, getTokenId());
            //認証失敗エラーメッセージ表示フラグをたてる
            putRequestScope(AttributeConst.LOGIN_ERR, true);
            //入力された店舗コードを設定
            putRequestScope(AttributeConst.STORE_STORE_CODE, storeCode);

            //ログイン画面を表示
            forward(ForwardConst.FW_LOGIN);
        }
    }
    /**
     * ログアウト処理を行う
     * @throws ServletException
     * @throws IOException
     */
    public void logout() throws ServletException, IOException {

        //セッションからログイン従業員のパラメータを削除
        removeSessionScope(AttributeConst.LOGIN_STORE);

        //セッションにログアウト時のフラッシュメッセージを追加
        putSessionScope(AttributeConst.FLUSH, MessageConst.I_LOGOUT.getMessage());

        //ログイン画面にリダイレクト
        redirect(ForwardConst.ACT_LOGIN, ForwardConst.CMD_SHOW_LOGIN);

    }

}