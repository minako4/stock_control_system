package services;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.NoResultException;

import actions.views.StoreConverter;
import actions.views.StoreView;
import constants.JpaConst;
import models.Store;
import models.validators.StoreValidator;
import utils.EncryptUtil;

public class StoreService extends ServiceBase {

    /**
     * 指定されたページ数の一覧画面に表示するデータを取得し、StoreViewのリストで返却する
     * @param page ページ数
     * @return 表示するデータのリスト
     */
    public List<StoreView> getPerPage(int page) {
        List<Store> stores = em.createNamedQuery(JpaConst.Q_STORE_GET_ALL, Store.class)
                .setFirstResult(JpaConst.ROW_PER_PAGE * (page - 1))
                .setMaxResults(JpaConst.ROW_PER_PAGE)
                .getResultList();

        return StoreConverter.toViewList(stores);
    }

    /**
     * 店舗テーブルのデータの件数を取得し、返却する
     * @return 店舗テーブルのデータの件数
     */
    public long countAll() {
        long storeCount = (long) em.createNamedQuery(JpaConst.Q_STORE_COUNT, Long.class)
                .getSingleResult();

        return storeCount;
    }

    /**
     * 店舗コード、パスワードを条件に取得したデータをStoreViewのインスタンスで返却する
     * @param storeCode 店舗コード
     * @param plainPass パスワード文字列
     * @param pepper pepper文字列
     * @return 取得データのインスタンス 取得できない場合null
     */
    public StoreView findOne(String storeCode, String plainPass, String pepper) {
        Store s = null;
        try {
            //パスワードのハッシュ化
            String pass = EncryptUtil.getPasswordEncrypt(plainPass, pepper);

            //店舗コードとハッシュ化済パスワードを条件に未削除の店舗を1件取得する
            s = em.createNamedQuery(JpaConst.Q_STORE_GET_BY_CODE_AND_PASS, Store.class)
                    .setParameter(JpaConst.JPQL_PARM_CODE, storeCode)
                    .setParameter(JpaConst.JPQL_PARM_PASSWORD, pass)
                    .getSingleResult();

        } catch (NoResultException ex) {
        }

        return StoreConverter.toView(s);

    }

    /**
     * idを条件に取得したデータをStoreViewのインスタンスで返却する
     * @param id
     * @return 取得データのインスタンス
     */
    public StoreView findOne(int id) {
        Store s = findOneInternal(id);
        return StoreConverter.toView(s);
    }

    /**
     * 店舗コードを条件に該当するデータの件数を取得し、返却する
     * @param storeCode 店舗コード
     * @return 該当するデータの件数
     */
    public long countByCode(String storeCode) {

        //指定した店舗コードを保持する店舗の件数を取得する
        long stores_count = (long) em.createNamedQuery(JpaConst.Q_STORE_COUNT_REGISTERED_BY_STORE_CODE, Long.class)
                .setParameter(JpaConst.JPQL_PARM_CODE, storeCode)
                .getSingleResult();
        return stores_count;
    }

    /**
     * 画面から入力された店舗の登録内容を元にデータを1件作成し、店舗テーブルに登録する
     * @param sv 画面から入力された店舗の登録内容
     * @param pepper pepper文字列
     * @return バリデーションや登録処理中に発生したエラーのリスト
     */
    public List<String> create(StoreView sv, String pepper) {

        //パスワードをハッシュ化して設定
        String pass = EncryptUtil.getPasswordEncrypt(sv.getPassword(), pepper);
        sv.setPassword(pass);

        //登録日時、更新日時は現在時刻を設定する
        LocalDateTime now = LocalDateTime.now();
        sv.setCreatedAt(now);
        sv.setUpdatedAt(now);

        //登録内容のバリデーションを行う
        List<String> errors = StoreValidator.validate(this, sv, true, true);

        //バリデーションエラーがなければデータを登録する
        if (errors.size() == 0) {
            create(sv);
        }

        //エラーを返却（エラーがなければ0件の空リスト）
        return errors;
    }

    /**
     * 画面から入力された店舗の更新内容を元にデータを1件作成し、店舗テーブルを更新する
     * @param sv 画面から入力された店舗の登録内容
     * @param pepper pepper文字列
     * @return バリデーションや更新処理中に発生したエラーのリスト
     */
    public List<String> update(StoreView sv, String pepper) {

        //idを条件に登録済みの店舗情報を取得する
        StoreView savedStore = findOne(sv.getId());

        boolean validateStoreCode = false;
        if (!savedStore.getStoreCode().equals(sv.getStoreCode())) {
            //店舗コードを更新する場合

            //店舗コードについてのバリデーションを行う
            validateStoreCode = true;
            //変更後の店舗コードを設定する
            savedStore.setStoreCode(sv.getStoreCode());
        }

        boolean validatePass = false;
        if (sv.getPassword() != null && !sv.getPassword().equals("")) {
            //パスワードに入力がある場合

            //パスワードについてのバリデーションを行う
            validatePass = true;

            //変更後のパスワードをハッシュ化し設定する
            savedStore.setPassword(
                    EncryptUtil.getPasswordEncrypt(sv.getPassword(), pepper));
        }

        savedStore.setName(sv.getName()); //変更後の氏名を設定する

        //更新日時に現在時刻を設定する
        LocalDateTime today = LocalDateTime.now();
        savedStore.setUpdatedAt(today);

        //更新内容についてバリデーションを行う
        List<String> errors = StoreValidator.validate(this, savedStore, validateStoreCode, validatePass);

        //バリデーションエラーがなければデータを更新する
        if (errors.size() == 0) {
            update(savedStore);
        }

        //エラーを返却（エラーがなければ0件の空リスト）
        return errors;
    }

    /**
     * idを条件に店舗データを論理削除する
     * @param id
     */
    public void destroy(Integer id) {

        //idを条件に登録済みの従業員情報を取得する
        StoreView savedStore = findOne(id);

        //更新日時に現在時刻を設定する
        LocalDateTime today = LocalDateTime.now();
        savedStore.setUpdatedAt(today);

        //論理削除フラグをたてる
        savedStore.setDeleteFlag(JpaConst.STORE_DEL_TRUE);

        //更新処理を行う
        update(savedStore);

    }

    /**
     * 店舗コードとパスワードを条件に検索し、データが取得できるかどうかで認証結果を返却する
     * @param storeCode 店舗コード
     * @param plainPass パスワード
     * @param pepper pepper文字列
     * @return 認証結果を返却す(成功:true 失敗:false)
     */
    public Boolean validateLogin(String storeCode, String plainPass, String pepper) {

        boolean isValidStore = false;
        if (storeCode != null && !storeCode.equals("") && plainPass != null && !plainPass.equals("")) {
            StoreView sv = findOne(storeCode, plainPass, pepper);

            if (sv != null && sv.getId() != null) {

                //データが取得できた場合、認証成功
                isValidStore = true;
            }
        }

        //認証結果を返却する
        return isValidStore;
    }

    /**
     * idを条件にデータを1件取得し、Storeのインスタンスで返却する
     * @param id
     * @return 取得データのインスタンス
     */
    private Store findOneInternal(int id) {
        Store s = em.find(Store.class, id);

        return s;
    }

    /**
     * 店舗データを1件登録する
     * @param sv 店舗データ
     * @return 登録結果(成功:true 失敗:false)
     */
    private void create(StoreView sv) {

        em.getTransaction().begin();
        em.persist(StoreConverter.toModel(sv));
        em.getTransaction().commit();

    }

    /**
     * 店舗データを更新する
     * @param sv 画面から入力された店舗の登録内容
     */
    private void update(StoreView sv) {

        em.getTransaction().begin();
        Store s = findOneInternal(sv.getId());
        StoreConverter.copyViewToModel(s, sv);
        em.getTransaction().commit();

    }


}
