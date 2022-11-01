package services;

import java.time.LocalDateTime;
import java.util.List;

import actions.views.ItemConverter;
import actions.views.ItemView;
import models.validators.ItemValidator;

public class ItemService extends ServiceBase{

    /**
     * 画面から入力された商品の登録内容を元にデータを1件作成し、商品テーブルに登録する
     * @param iv 商品の登録内容
     * @return バリデーションで発生したエラーのリスト
     */
    public List<String> create(ItemView iv) {
        List<String> errors = ItemValidator.validate(iv,true);
        if (errors.size() == 0) {
            LocalDateTime ldt = LocalDateTime.now();
            iv.setCreatedAt(ldt);
            iv.setUpdatedAt(ldt);

            createInternal(iv);
            }

            //エラーを返却（エラーがなければ0件の空リスト）
            return errors;
        }
    /**
     * 商品データを1件登録する
     * @param iv 商品データ
     */
    private void createInternal(ItemView iv) {

        em.getTransaction().begin();
        em.persist(ItemConverter.toModel(iv));
        em.getTransaction().commit();

    }
}