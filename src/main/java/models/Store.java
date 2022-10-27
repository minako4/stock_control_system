package models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import constants.JpaConst;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = JpaConst.TABLE_STORE)
@NamedQueries({
    @NamedQuery(
            name = JpaConst.Q_STORE_GET_ALL,
            query = JpaConst.Q_STORE_GET_ALL_DEF),
    @NamedQuery(
            name = JpaConst.Q_STORE_COUNT,
            query = JpaConst.Q_STORE_COUNT_DEF),
    @NamedQuery(
            name = JpaConst.Q_STORE_COUNT_REGISTERED_BY_STORE_CODE,
            query = JpaConst.Q_STORE_COUNT_REGISTERED_BY_STORE_CODE_DEF),
    @NamedQuery(
            name = JpaConst.Q_STORE_GET_BY_CODE_AND_PASS,
            query = JpaConst.Q_STORE_GET_BY_CODE_AND_PASS_DEF)
})


@Getter //全てのクラスフィールドについてgetterを自動生成する(Lombok)
@Setter //全てのクラスフィールドについてsetterを自動生成する(Lombok)
@NoArgsConstructor //引数なしコンストラクタを自動生成する(Lombok)
@AllArgsConstructor //全てのクラスフィールドを引数にもつ引数ありコンストラクタを自動生成する(Lombok)
@Entity
public class Store {

    /**
     * id
     */
    @Id
    @Column(name = JpaConst.STORE_COL_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 店舗名
     */
    @Column(name = JpaConst.STORE_COL_NAME, nullable = false)
    private String name;

    /**
     * 店舗コード
     */
    @Column(name = JpaConst.STORE_COL_STORE_CODE, nullable = false, unique = true)
    private String storeCode;

    /**
     * エリアコード
     */
    @Column(name = JpaConst.STORE_COL_AREA_CODE, nullable = false)
    private String areaCode;

    /**
     * パスワード
     */
    @Column(name = JpaConst.STORE_COL_PASS, length = 64, nullable = false)
    private String password;

    /**
     *登録日時
     */
    @Column(name = JpaConst.STORE_COL_CREATED_AT, nullable = false)
    private LocalDateTime createdAt;

    /**
     * 更新日時
     */
    @Column(name = JpaConst.STORE_COL_UPDATED_AT, nullable = false)
    private LocalDateTime updatedAt;

    /**
     * 削除された店舗かどうか（現役：0、削除済み：1）
     */
    @Column(name = JpaConst.STORE_COL_DELETE_FLAG, nullable = false)
    private Integer deleteFlag;

}

