package constants;

/**
 * リクエストパラメーターの変数名、変数値、jspファイルの名前等画面遷移に関わる値を定義するEnumクラス
 *
 */
public enum ForwardConst {

    //action
    ACT("action"),
    ACT_TOP("Top"),
    ACT_STORE("Store"),
    ACT_LOGIN("Login"),
    ACT_ITEM("Item"),
    //command
    CMD("command"),
    CMD_NONE(""),
    CMD_INDEX("index"),
    CMD_SHOW("show"),
    CMD_SHOW_LOGIN("showLogin"),
    CMD_LOGIN("login"),
    CMD_LOGOUT("logout"),
    CMD_NEW("entryNew"),
    CMD_CREATE("create"),
    CMD_EDIT("edit"),
    CMD_UPDATE("update"),
    CMD_DESTROY("destroy"),
    CMD_SEARCH("search"),

    //jsp
    FW_ERR_UNKNOWN("error/unknown"),
    FW_TOP_INDEX("topPage/index"),
    FW_LOGIN("login/login"),
    FW_STORES_INDEX("stores/index"),
    FW_STORES_SHOW("stores/show"),
    FW_STORES_NEW("stores/new"),
    FW_STORES_EDIT("stores/edit"),
    FW_ITEMS_INDEX("items/index"),
    FW_ITEMS_SHOW("items/show"),
    FW_ITEMS_NEW("items/new"),
    FW_ITEMS_SRP("items/searchResults"),
    FW_ITEMS_EDIT("items/edit"),
    FW_ITEMS_SEARCH("items/search");
    /**
     * 文字列
     */
    private final String text;

    /**
     * コンストラクタ
     */
    private ForwardConst(final String text) {
        this.text = text;
    }

    /**
     * 値(文字列)取得
     */
    public String getValue() {
        return this.text;
    }

}