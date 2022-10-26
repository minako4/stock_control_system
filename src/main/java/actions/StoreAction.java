package actions;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import actions.views.StoreView;
import constants.AttributeConst;
import constants.ForwardConst;
import constants.JpaConst;
import constants.MessageConst;
import constants.PropertyConst;
import services.StoreService;

public class StoreAction extends ActionBase {
    private StoreService service;

    @Override
    public void process() throws ServletException, IOException {
        service = new StoreService();

        //メソッドを実行
        invoke();

        service.close();
    }

    /**
     * 一覧画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void index() throws ServletException, IOException {

        //指定されたページ数の一覧画面に表示するデータを取得
        int page = getPage();
        List<StoreView> stores = service.getPerPage(page);

        //全ての店舗データの件数を取得
        long storeCount = service.countAll();

        putRequestScope(AttributeConst.STORES, stores); //取得した店舗データ
        putRequestScope(AttributeConst.STORE_COUNT, storeCount); //全ての店舗データの件数
        putRequestScope(AttributeConst.PAGE, page); //ページ数
        putRequestScope(AttributeConst.MAX_ROW, JpaConst.ROW_PER_PAGE); //1ページに表示するレコードの数

        //セッションにフラッシュメッセージが設定されている場合はリクエストスコープに移し替え、セッションからは削除する
        String flush = getSessionScope(AttributeConst.FLUSH);
        if (flush != null) {
            putRequestScope(AttributeConst.FLUSH, flush);
            removeSessionScope(AttributeConst.FLUSH);
        }

        //一覧画面を表示
        forward(ForwardConst.FW_STORES_INDEX);

    }

    /**
     * 新規登録画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void entryNew() throws ServletException, IOException {

        putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
        putRequestScope(AttributeConst.STORE, new StoreView()); //空のストアインスタンス

        //新規登録画面を表示
        forward(ForwardConst.FW_STORES_NEW);
    }

    /**
     * 新規登録を行う
     * @throws ServletException
     * @throws IOException
     */
    public void create() throws ServletException, IOException {

        //CSRF対策 tokenのチェック
        if (checkToken()) {

            //パラメータの値を元に従業員情報のインスタンスを作成する
            StoreView sv = new StoreView(
                    null,
                    getRequestParam(AttributeConst.STORE_NAME),
                    getRequestParam(AttributeConst.STORE_STORE_CODE),
                    getRequestParam(AttributeConst.STORE_AREA_CODE),
                    getRequestParam(AttributeConst.STORE_PASS),
                    null,
                    null,

                    AttributeConst.DEL_FLAG_FALSE.getIntegerValue());

            //アプリケーションスコープからpepper文字列を取得
            String pepper = getContextScope(PropertyConst.PEPPER);

            //情報登録
            List<String> errors = service.create(sv, pepper);

            if (errors.size() > 0) {
                //登録中にエラーがあった場合

                putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
                putRequestScope(AttributeConst.STORE, sv); //入力された店舗情報
                putRequestScope(AttributeConst.ERR, errors); //エラーのリスト

                //新規登録画面を再表示
                forward(ForwardConst.FW_STORES_NEW);

            } else {
                //登録中にエラーがなかった場合

                //セッションに登録完了のフラッシュメッセージを設定
                putSessionScope(AttributeConst.FLUSH, MessageConst.I_REGISTERED.getMessage());

                //一覧画面にリダイレクト
                redirect(ForwardConst.ACT_STORE, ForwardConst.CMD_INDEX);
            }

        }
    }
    /**
     * 詳細画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void show() throws ServletException, IOException {

        //idを条件に店舗データを取得する
        StoreView sv = service.findOne(toNumber(getRequestParam(AttributeConst.STORE_ID)));

        if (sv == null || sv.getDeleteFlag() == AttributeConst.DEL_FLAG_TRUE.getIntegerValue()) {

            //データが取得できなかった、または論理削除されている場合はエラー画面を表示
            forward(ForwardConst.FW_ERR_UNKNOWN);
            return;
        }

        putRequestScope(AttributeConst.STORE, sv); //取得した店舗情報

        //詳細画面を表示
        forward(ForwardConst.FW_STORES_SHOW);
    }
    /**
     * 編集画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void edit() throws ServletException, IOException {

        //idを条件に店舗データを取得する
        StoreView sv = service.findOne(toNumber(getRequestParam(AttributeConst.STORE_ID)));

        if (sv == null || sv.getDeleteFlag() == AttributeConst.DEL_FLAG_TRUE.getIntegerValue()) {

            //データが取得できなかった、または論理削除されている場合はエラー画面を表示
            forward(ForwardConst.FW_ERR_UNKNOWN);
            return;
        }

        putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
        putRequestScope(AttributeConst.STORE, sv); //取得した店舗情報

        //編集画面を表示する
        forward(ForwardConst.FW_STORES_EDIT);

    }
}
