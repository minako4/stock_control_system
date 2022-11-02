package actions.views;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter //全てのクラスフィールドについてgetterを自動生成する(Lombok)
@Setter //全てのクラスフィールドについてsetterを自動生成する(Lombok)
@NoArgsConstructor //引数なしコンストラクタを自動生成する(Lombok)
@AllArgsConstructor //全てのクラスフィールドを引数にもつ引数ありコンストラクタを自動生成する(Lombok)
public class ItemView {

    /**
     * id
     */
    private Integer id;

    /**
     *  商品登録した店舗id
     */
    private StoreView store;

    /**
     * メーカー名
     */
    private String manufacturerName;

    /**
     * 品名
     */
    private String name;

    /**
     * 品番
     */
    private String code;

    /**
     * JANコード
     */
    private String janCode;
    /**
     * 登録日時
     */
    private LocalDateTime createdAt;

    /**
     * 更新日時
     */
    private LocalDateTime updatedAt;

    /**
     * 数量
     */
    private String quantity;
}