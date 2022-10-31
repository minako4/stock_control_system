package actions;

import java.io.IOException;

import javax.servlet.ServletException;

import actions.views.ItemView;
import constants.AttributeConst;
import constants.ForwardConst;
import services.ItemService;


public class ItemAction extends ActionBase{


        private ItemService service;

        @Override
        public void process() throws ServletException, IOException {
            service = new ItemService();

            //メソッドを実行
            invoke();

            service.close();
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
}
