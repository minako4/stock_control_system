package constants;

/**
 * DB関連の項目値を定義するインターフェース
 * ※インターフェイスに定義した変数は public static final 修飾子がついているとみなされる
 */
public interface JpaConst {

    //persistence-unit名
    String PERSISTENCE_UNIT_NAME = "stock_control_system";

    //データ取得件数の最大値
    int ROW_PER_PAGE = 15; //1ページに表示するレコードの数

    //店舗テーブル
    String TABLE_STORE = "stores"; //テーブル名
    //店舗テーブルカラム
    String STORE_COL_ID = "id"; //id
    String STORE_COL_NAME = "name"; //店舗名
    String STORE_COL_STORE_CODE = "store_code"; //店舗コード
    String STORE_COL_AREA_CODE = "area_code"; //エリアコード
    String STORE_COL_PASS = "password"; //パスワード
    String STORE_COL_CREATED_AT = "created_at"; //登録日時
    String STORE_COL_UPDATED_AT = "updated_at"; //更新日時
    String STORE_COL_DELETE_FLAG = "delete_flag"; //削除フラグ


    int STORE_DEL_TRUE = 1; //削除フラグON(削除済み)
    int STORE_DEL_FALSE = 0; //削除フラグOFF(現役)

    //日報テーブル
    String TABLE_REP = "reports"; //テーブル名
    //日報テーブルカラム
    String REP_COL_ID = "id"; //id
    String REP_COL_EMP = "employee_id"; //日報を作成した店舗のid
    String REP_COL_REP_DATE = "report_date"; //いつの日報かを示す日付
    String REP_COL_TITLE = "title"; //日報のタイトル
    String REP_COL_CONTENT = "content"; //日報の内容
    String REP_COL_CREATED_AT = "created_at"; //登録日時
    String REP_COL_UPDATED_AT = "updated_at"; //更新日時

    //Entity名
    String ENTITY_STORE = "store"; //店舗

    //JPQL内パラメータ
    String JPQL_PARM_CODE = "code"; //店舗コード
    String JPQL_PARM_PASSWORD = "password"; //パスワード
    String JPQL_PARM_STORE = "store"; //店舗

    //NamedQueryの nameとquery
    //全ての店舗をidの降順に取得する
    String Q_STORE_GET_ALL = ENTITY_STORE + ".getAll"; //name
    String Q_STORE_GET_ALL_DEF = "SELECT s FROM Store AS s ORDER BY s.id DESC"; //query
    //全ての店舗の件数を取得する
    String Q_STORE_COUNT = ENTITY_STORE + ".count";
    String Q_STORE_COUNT_DEF = "SELECT COUNT(s) FROM Store AS s";
    //店舗コードとハッシュ化済パスワードを条件に未削除の店舗を取得する
    String Q_STORE_GET_BY_CODE_AND_PASS = ENTITY_STORE + ".getByCodeAndPass";
    String Q_STORE_GET_BY_CODE_AND_PASS_DEF = "SELECT s FROM Store AS s WHERE s.deleteFlag = 0 AND s.storeCode = :" + JPQL_PARM_CODE + " AND s.password = :" + JPQL_PARM_PASSWORD;
    //指定した店舗コードを保持する店舗の件数を取得する
    String Q_STORE_COUNT_REGISTERED_BY_STORE_CODE = ENTITY_STORE + ".countRegisteredByCode";
    String Q_STORE_COUNT_REGISTERED_BY_STORE_CODE_DEF = "SELECT COUNT(s) FROM Store AS s WHERE s.storeCode = :" + JPQL_PARM_CODE;
}
