package models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import constants.JpaConst;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

    @Table(name = JpaConst.TABLE_ITEM)
    @NamedQueries({
        @NamedQuery(//全ての商品データをidの降順に取得する
                name = JpaConst. Q_ITEM_GET_ALL,
                query = JpaConst.Q_ITEM_GET_ALL_DEF),
        @NamedQuery(//全ての商品の件数を取得する
                name = JpaConst.Q_ITEM_COUNT,
                query = JpaConst.Q_ITEM_COUNT_DEF),
        @NamedQuery(
                name = JpaConst.Q_ITEM_GET_ALL_MINE,
                query = JpaConst.Q_ITEM_GET_ALL_MINE_DEF),
        @NamedQuery(
                name = JpaConst.Q_ITEM_COUNT_ALL_MINE,
                query = JpaConst.Q_ITEM_COUNT_ALL_MINE_DEF),

    })


    @Getter //全てのクラスフィールドについてgetterを自動生成する(Lombok)
    @Setter //全てのクラスフィールドについてsetterを自動生成する(Lombok)
    @NoArgsConstructor //引数なしコンストラクタを自動生成する(Lombok)
    @AllArgsConstructor //全てのクラスフィールドを引数にもつ引数ありコンストラクタを自動生成する(Lombok)
    @Entity
    public class Item {

        /**
         * id
         */
        @Id
        @Column(name = JpaConst.ITEM_COL_ID)
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        /**
         * 商品登録した店舗id
         */
        @ManyToOne
        @JoinColumn(name = JpaConst.ITEM_COL_STORE, nullable = false)
        private Store store;

        /**
         * メーカー名
         */
        @Column(name = JpaConst.ITEM_COL_MFR, nullable = false)
        private String manufacturerName;

        /**
         * 品名
         */
        @Column(name = JpaConst.ITEM_COL_NAME, length = 255, nullable = false)
        private String name;

        /**
         * 品番
         */
        @Column(name = JpaConst.ITEM_COL_CODE, nullable = false)
        private String code;

        /**
         * JANコード
         */
        @Column(name = JpaConst.ITEM_COL_JANCODE, length = 13, nullable = false)
        private String janCode;

        /**
         * 登録日時
         */
        @Column(name = JpaConst.ITEM_COL_CREATED_AT, nullable = false)
        private LocalDateTime createdAt;

        /**
         * 更新日時
         */
        @Column(name = JpaConst.ITEM_COL_UPDATED_AT, nullable = false)
        private LocalDateTime updatedAt;

        /**
         * 数量
         */
        @Column(name = JpaConst.ITEM_COL_QTY, nullable = false)
        private String quantity;

    }
