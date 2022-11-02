package actions;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import actions.views.ItemView;
import actions.views.StoreView;
import constants.AttributeConst;
import constants.ForwardConst;
import constants.JpaConst;
import constants.MessageConst;
import services.ItemService;


public class ItemAction extends ActionBase{


        private ItemService serviceI;

        @Override
        public void process() throws ServletException, IOException {
            serviceI = new ItemService();

            //メソッドを実行
            invoke();

            serviceI.close();
        }

    /**
     * 新規登録画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void entryNew() throws ServletException, IOException {

        putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
        putRequestScope(AttributeConst.ITEM, new ItemView()); //空の商品インスタンス

        //新規登録画面を表示
        forward(ForwardConst.FW_ITEMS_NEW);
    }
    /**
     * 新規登録を行う
     * @throws ServletException
     * @throws IOException
     */
    public void create() throws ServletException, IOException {


        //CSRF対策 tokenのチェック
        if (checkToken()) {

          //セッションからログイン中の店舗情報を取得
            StoreView sv = (StoreView) getSessionScope(AttributeConst.LOGIN_STORE);

            //パラメータの値をもとに商品情報のインスタンスを作成する
            ItemView iv = new ItemView(
                    null,
                    sv, //ログインしている店舗を、商品情報入力者として登録する
                    getRequestParam(AttributeConst.ITEM_MFR),
                    getRequestParam(AttributeConst.ITEM_NAME),
                    getRequestParam(AttributeConst.ITEM_CODE),
                    getRequestParam(AttributeConst.ITEM_JANCODE),
                    null,
                    null,
                    getRequestParam(AttributeConst.ITEM_QTY));



            //商品情報登録
            List<String> errors = serviceI.create(iv);

            if (errors.size() > 0) {
                //登録中にエラーがあった場合

                putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
                putRequestScope(AttributeConst.ITEM, iv);//入力された商品情報
                putRequestScope(AttributeConst.ERR, errors);//エラーのリスト

                //新規登録画面を再表示
                forward(ForwardConst.FW_ITEMS_NEW);

            } else {
                //登録中にエラーがなかった場合

                //セッションに登録完了のフラッシュメッセージを設定
                putSessionScope(AttributeConst.FLUSH, MessageConst.I_REGISTERED.getMessage());

                //一覧画面にリダイレクト
                redirect(ForwardConst.ACT_TOP, ForwardConst.CMD_INDEX);
            }
        }
    }
    /**
     * 一覧画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void index() throws ServletException, IOException {

        //指定されたページ数の一覧画面に表示する商品データを取得
        int page = getPage();
        List<ItemView> items = serviceI.getAllPerPage(page);

        //全商品データの件数を取得
        long itemsCount = serviceI.countAll();

        putRequestScope(AttributeConst.ITEMS, items); //取得した商品データ
        putRequestScope(AttributeConst.ITEMS_COUNT, itemsCount); //全ての商品データの件数
        putRequestScope(AttributeConst.PAGE, page); //ページ数
        putRequestScope(AttributeConst.MAX_ROW, JpaConst.ROW_PER_PAGE); //1ページに表示するレコードの数

        //セッションにフラッシュメッセージが設定されている場合はリクエストスコープに移し替え、セッションからは削除する
        String flush = getSessionScope(AttributeConst.FLUSH);
        if (flush != null) {
            putRequestScope(AttributeConst.FLUSH, flush);
            removeSessionScope(AttributeConst.FLUSH);
        }


        //一覧画面を表示
        forward(ForwardConst.FW_ITEMS_INDEX);
    }
    /**
     * 詳細画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void show() throws ServletException, IOException {

        //idを条件に商品データを取得する
        ItemView iv = serviceI.findOne(toNumber(getRequestParam(AttributeConst.ITEM_ID)));

        if (iv == null) {
            //該当のデータが存在しない場合はエラー画面を表示
            forward(ForwardConst.FW_ERR_UNKNOWN);

        } else {

            putRequestScope(AttributeConst.ITEM, iv); //取得した日報データ

            //詳細画面を表示
            forward(ForwardConst.FW_ITEMS_SHOW);
        }
    }
    /**
     * 検索画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void search() throws ServletException, IOException {

        putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
        putRequestScope(AttributeConst.ITEM, new ItemView()); //空の商品インスタンス

        //検索画面を表示
        forward(ForwardConst.FW_ITEMS_SEARCH);
    }

    /**
     * 編集画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void edit() throws ServletException, IOException {

        //idを条件に商品データを取得する
        ItemView iv = serviceI.findOne(toNumber(getRequestParam(AttributeConst.ITEM_ID)));

        if (iv == null) {
            //該当の商品データが存在しない場合はエラー画面を表示

            forward(ForwardConst.FW_ERR_UNKNOWN);

        } else {

            putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
            putRequestScope(AttributeConst.ITEM, iv); //取得した日報データ

            //編集画面を表示
            forward(ForwardConst.FW_ITEMS_EDIT);
        }

    }
}

