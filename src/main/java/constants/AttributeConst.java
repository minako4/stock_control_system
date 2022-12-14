package constants;

/**
 * 画面の項目値等を定義するEnumクラス
 *
 */
public enum AttributeConst {

    //フラッシュメッセージ
    FLUSH("flush"),

    //一覧画面共通
    MAX_ROW("maxRow"),
    PAGE("page"),

    //入力フォーム共通
    TOKEN("_token"),
    ERR("errors"),

    //ログイン中の店
    LOGIN_STORE("login_store"),

    //ログイン画面
    LOGIN_ERR("loginError"),

    //店舗管理
    STORE("store"),
    STORES("stores"),
    STORE_COUNT("stores_count"),
    STORE_ID("id"),
    STORE_NAME("name"),
    STORE_AREA_CODE("area_code"),
    STORE_STORE_CODE("store_code"),
    STORE_PASS("password"),

    //削除フラグ
    DEL_FLAG_TRUE(1),
    DEL_FLAG_FALSE(0),

    //商品管理
    ITEM("item"),
    ITEMS("items"),
    ITEMS_COUNT("items_count"),
    ITEM_ID("id"),
    ITEM_STORE("store_id"),
    ITEM_MFR("manufacturer_name"),
    ITEM_NAME("name"),
    ITEM_CODE("code"),
    ITEM_JANCODE("jan_code"),
    ITEM_QTY("quantity");

    private final String text;
    private final Integer i;

    private AttributeConst(final String text) {
        this.text = text;
        this.i = null;
    }

    private AttributeConst(final Integer i) {
        this.text = null;
        this.i = i;
    }

    public String getValue() {
        return this.text;
    }

    public Integer getIntegerValue() {
        return this.i;
    }

}