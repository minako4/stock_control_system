package actions.views;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 店舗情報について画面の入力値・出力値を扱うViewモデル
 *
 */
@Getter //全てのクラスフィールドについてgetterを自動生成する(Lombok)
@Setter //全てのクラスフィールドについてsetterを自動生成する(Lombok)
@NoArgsConstructor //引数なしコンストラクタを自動生成する(Lombok)
@AllArgsConstructor //全てのクラスフィールドを引数にもつ引数ありコンストラクタを自動生成する(Lombok)
public class StoreView {

    /**
     * id
     */
    private Integer id;

    /**
     * 店舗名
     */
    private String name;

    /**
     * 店舗コード
     */
    private String storeCode;

    /**
     * エリアコード
     */
    private String areaCode;

    /**
     * パスワード
     */
    private String password;

    /**
     *登録日時
     */
    private LocalDateTime createdAt;

    /**
     * 更新日時
     */
    private LocalDateTime updatedAt;

    /**
     * 削除された店舗かどうか（現役：0、削除済み：1）
     */
    private Integer deleteFlag;
}
