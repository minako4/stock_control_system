package actions;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import actions.views.StoreView;
import constants.AttributeConst;
import constants.ForwardConst;
import constants.JpaConst;
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
}
