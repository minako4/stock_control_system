package constants;

/**
 * 各出力メッセージを定義するEnumクラス
 *
 */
public enum MessageConst {

    //認証
    I_LOGINED("ログインしました"),
    E_LOGINED("ログインに失敗しました。"),
    I_LOGOUT("ログアウトしました。"),

    //DB更新
    I_REGISTERED("登録が完了しました。"),
    I_UPDATED("更新が完了しました。"),
    I_DELETED("削除が完了しました。"),

    //バリデーション
    E_NOSTORENAME("店舗名を入力してください。"),
    E_NOPASSWORD("パスワードを入力してください。"),
    E_NOSTORE_CODE("店舗コードを入力してください。"),
    E_NOAREA_CODE("エリアコードを入力してください。"),
    E_STORE_CODE_EXIST("入力された店舗の情報は既に存在しています。"),
    E_NOMFR("メーカー名を入力してください。"),
    E_NONAME("商品名を入力してください。"),
    E_NOCODE("品番を入力してください。"),
    E_NOJANCODE("JANコードを入力してください。"),
    E_JANCODE_13("JANコードは13桁で入力してください。"),
    E_NOQTY("数量を入力してください。");


    /**
     * 文字列
     */
    private final String text;

    /**
     * コンストラクタ
     */
    private MessageConst(final String text) {
        this.text = text;
    }

    /**
     * 値(文字列)取得
     */
    public String getMessage() {
        return this.text;
    }
}