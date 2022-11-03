package services;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.NoResultException;

import actions.views.ItemConverter;
import actions.views.ItemView;
import actions.views.StoreConverter;
import actions.views.StoreView;
import constants.JpaConst;
import models.Item;
import models.validators.ItemValidator;

public class ItemService extends ServiceBase {

    /**
     * 画面から入力された商品の登録内容を元にデータを1件作成し、商品テーブルに登録する
     * @param iv 商品の登録内容
     * @return バリデーションで発生したエラーのリスト
     */
    public List<String> create(ItemView iv) {
        LocalDateTime ldt = LocalDateTime.now();
        iv.setCreatedAt(ldt);
        iv.setUpdatedAt(ldt);

        List<String> errors = ItemValidator.validate(iv, true);
        if (errors.size() == 0) {
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
    /**
     * 検索でエリアコードとJANで絞り込んだ商品データを、指定されたページ数の一覧画面に表示する分取得しItemViewのリストで返却する
     * @param store 店舗
     * @param page ページ数
     * @return 一覧画面に表示するデータのリスト
     */
    public List<ItemView> getSrp(String areaCode, String janCode, int page) {

        List<Item> srpItems = em.createQuery(JpaConst.Q_ITEM_GET_BY_AREACODE_AND_JANCODE_DEF, Item.class)
                .setParameter(JpaConst.JPQL_PARM_JANCODE, janCode)
                .setParameter(JpaConst.JPQL_PARM_AREACODE,areaCode)
                .setFirstResult(JpaConst.ROW_PER_PAGE * (page - 1))
                .setMaxResults(JpaConst.ROW_PER_PAGE)
                .getResultList();
        return ItemConverter.toViewList(srpItems);
    }

    /**
     * エリアコードとJANCODEで絞り込んだ商品データの件数を取得し、返却する
     * @param items
//     * @return 商品データの件数
     */
    public long countSrp(String areaCode, String janCode) {

        long count = (long) em.createQuery(JpaConst.Q_ITEM_COUNT_BY_AREACODE_AND_JANCODE_DEF, Long.class)
                .setParameter(JpaConst.JPQL_PARM_JANCODE, janCode)
                .setParameter(JpaConst.JPQL_PARM_AREACODE, areaCode)
                .getSingleResult();

        return count;
    }




    /**
     * 指定した店舗が作成した商品データを、指定されたページ数の一覧画面に表示する分取得しItemViewのリストで返却する
     * @param store
     * @param page ページ数
     * @return 一覧画面に表示するデータのリスト
     */
    public List<ItemView> getMinePerPage(StoreView store, int page) {

        List<Item> items = em.createNamedQuery(JpaConst.Q_ITEM_GET_ALL_MINE, Item.class)
                .setParameter(JpaConst.JPQL_PARM_STORE, StoreConverter.toModel(store))
                .setFirstResult(JpaConst.ROW_PER_PAGE * (page - 1))
                .setMaxResults(JpaConst.ROW_PER_PAGE)
                .getResultList();
        return ItemConverter.toViewList(items);
    }

    /**
     * 指定した店舗が作成した商品データの件数を取得し、返却する
     * @param store
     * @return 商品データの件数
     */
    public long countAllMine(StoreView store) {

        long count = (long) em.createNamedQuery(JpaConst.Q_ITEM_COUNT_ALL_MINE, Long.class)
                .setParameter(JpaConst.JPQL_PARM_STORE, StoreConverter.toModel(store))
                .getSingleResult();

        return count;
    }

    /**
     * 指定されたページ数の一覧画面に表示する商品データを取得し、ItemViewのリストで返却する
     * @param page ページ数
     * @return 一覧画面に表示するデータのリスト
     */
    public List<ItemView> getAllPerPage(int page) {

        List<Item> items = em.createNamedQuery(JpaConst.Q_ITEM_GET_ALL, Item.class)
                .setFirstResult(JpaConst.ROW_PER_PAGE * (page - 1))
                .setMaxResults(JpaConst.ROW_PER_PAGE)
                .getResultList();
        return ItemConverter.toViewList(items);
    }

    /**
     * 商品テーブルのデータの件数を取得し、返却する
     * @return データの件数
     */
    public long countAll() {
        long items_count = (long) em.createNamedQuery(JpaConst.Q_ITEM_COUNT, Long.class)
                .getSingleResult();
        return items_count;
    }

    /**
     * idを条件に取得したデータをItemViewのインスタンスで返却する
     * @param id
     * @return 取得データのインスタンス
     */
    public ItemView findOne(int id) {
        return ItemConverter.toView(findOneInternal(id));
    }

    /**
     * idを条件にデータを1件取得する
     * @param id
     * @return 取得データのインスタンス
     */
    private Item findOneInternal(int id) {
        return em.find(Item.class, id);

    }

    /**
     * 画面から入力された商品の登録内容を元に、商品データを更新する
     * @param iv 商品の更新内容
     * @return バリデーションで発生したエラーのリスト
     */
    public List<String> update(ItemView iv) {

        //バリデーションを行う
        List<String> errors = ItemValidator.validate(iv, true);

        if (errors.size() == 0) {

            //更新日時を現在時刻に設定
            LocalDateTime ldt = LocalDateTime.now();
            iv.setUpdatedAt(ldt);

            updateInternal(iv);
        }

        //バリデーションで発生したエラーを返却（エラーがなければ0件の空リスト）
        return errors;
    }

    /**
     * 商品データを更新する
     * @param iv 商品データ
     */
    private void updateInternal(ItemView iv) {

        em.getTransaction().begin();
        Item i = findOneInternal(iv.getId());
        ItemConverter.copyViewToModel(i, iv);
        em.getTransaction().commit();

    }

    /**
     * areaCode、janCodeを条件に取得したデータをItemViewのインスタンスで返却する

     * @return 取得データのインスタンス 取得できない場合null
     */
    public Item searchItem(Item areaCode, Item janCode) {
        Item i = null;
        try {

            //followerIDとfolloweeIDを条件に検索し、登録されているItem
            i = em.createNamedQuery(JpaConst.Q_ITEM_GET_BY_AREACODE_AND_JANCODE, Item.class)
                    .setParameter(JpaConst.JPQL_PARM_AREACODE, areaCode)
                    .setParameter(JpaConst.JPQL_PARM_JANCODE, janCode)
                    .getSingleResult();

        } catch (NoResultException ex) {
        }

        return (i);

    }

    /**
     * idを条件に商品データを削除する
     * @param id
     */
    public void destroy(ItemView iv) {

        Item i = findOneInternal(iv.getId());

        //商品データを削除する
        em.getTransaction().begin();
        em.remove(i); // データ削除
        em.getTransaction().commit();

    }

}