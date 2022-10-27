package actions.views;
import java.util.ArrayList;
import java.util.List;

import constants.AttributeConst;
import constants.JpaConst;
import models.Store;

/**
 * 店舗データのDTOモデル⇔Viewモデルの変換を行うクラス
 *
 */
public class StoreConverter {
    /**
     * ViewモデルのインスタンスからDTOモデルのインスタンスを作成する
     * @param sv StoreViewのインスタンス
     * @return Storeのインスタンス
     */
    public static Store toModel(StoreView sv) {

        return new Store(
                sv.getId(),
                sv.getName(),
                sv.getStoreCode(),
                sv.getAreaCode(),
                sv.getPassword(),
                sv.getCreatedAt(),
                sv.getUpdatedAt(),
                sv.getDeleteFlag() == null
                        ? null
                        : sv.getDeleteFlag() == AttributeConst.DEL_FLAG_TRUE.getIntegerValue()
                                ? JpaConst.STORE_DEL_TRUE
                                : JpaConst.STORE_DEL_FALSE);
    }

    /**
     * DTOモデルのインスタンスからViewモデルのインスタンスを作成する
     * @param s Storeのインスタンス
     * @return StoreViewのインスタンス
     */
    public static StoreView toView(Store s) {

        if(s == null) {
            return null;
        }

        return new StoreView(
                s.getId(),
                s.getName(),
                s.getStoreCode(),
                s.getAreaCode(),
                s.getPassword(),
                s.getCreatedAt(),
                s.getUpdatedAt(),
                s.getDeleteFlag()  == null
                        ? null
                        : s.getDeleteFlag() == JpaConst.STORE_DEL_TRUE
                                ? AttributeConst.DEL_FLAG_TRUE.getIntegerValue()
                                : AttributeConst.DEL_FLAG_FALSE.getIntegerValue());
    }

    /**
     * DTOモデルのリストからViewモデルのリストを作成する
     * @param list DTOモデルのリスト
     * @return Viewモデルのリスト
     */
    public static List<StoreView> toViewList(List<Store> list) {
        List<StoreView> svs = new ArrayList<>();

        for (Store s : list) {
            svs.add(toView(s));
        }

        return svs;
    }

    /**
     * Viewモデルの全フィールドの内容をDTOモデルのフィールドにコピーする
     * @param e DTOモデル(コピー先)
     * @param ev Viewモデル(コピー元)
     */
    public static void copyViewToModel(Store s, StoreView sv) {
        s.setId(sv.getId());
        s.setName(sv.getName());
        s.setStoreCode(sv.getStoreCode());
        s.setAreaCode(sv.getAreaCode());
        s.setPassword(sv.getPassword());
        s.setCreatedAt(sv.getCreatedAt());
        s.setUpdatedAt(sv.getUpdatedAt());
        s.setDeleteFlag(sv.getDeleteFlag());

    }

}
