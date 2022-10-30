package actions.views;

import java.util.ArrayList;
import java.util.List;

import models.Item;

public class ItemConverter {

    /**
     * ViewモデルのインスタンスからDTOモデルのインスタンスを作成する
     * @param iv ItemViewのインスタンス
     * @return Itemのインスタンス
     */
    public static Item toModel(ItemView iv) {
        return new Item(
                iv.getId(),
                StoreConverter.toModel(iv.getStore()),
                iv.getManufacturerName(),
                iv.getName(),
                iv.getCode(),
                iv.getJANCode(),
                iv.getCreatedAt(),
                iv.getUpdatedAt(),
                iv.getQuantity()) ;
    }

    /**
     * DTOモデルのインスタンスからViewモデルのインスタンスを作成する
     * @param r Reportのインスタンス
     * @return ReportViewのインスタンス
     */
    public static ItemView toView(Item i) {

        if (i == null) {
            return null;
        }

        return new ItemView(
                i.getId(),
                StoreConverter.toView(i.getStore()),
                i.getManufacturerName(),
                i.getName(),
                i.getCode(),
                i.getJANCode(),
                i.getCreatedAt(),
                i.getUpdatedAt(),
                i.getQuantity()) ;
    }

    /**
     * DTOモデルのリストからViewモデルのリストを作成する
     * @param list DTOモデルのリスト
     * @return Viewモデルのリスト
     */
    public static List<ItemView> toViewList(List<Item> list) {
        List<ItemView> ivs = new ArrayList<>();

        for (Item i : list) {
            ivs.add(toView(i));
        }

        return ivs;
    }

    /**
     * Viewモデルの全フィールドの内容をDTOモデルのフィールドにコピーする
     * @param r DTOモデル(コピー先)
     * @param rv Viewモデル(コピー元)
     */
    public static void copyViewToModel(Item i, ItemView iv) {
        i.setId(iv.getId());
        i.setStore(StoreConverter.toModel(iv.getStore()));
        i.setManufacturerName(iv.getManufacturerName());
        i.setName(iv.getName());
        i.setCode(iv.getCode());
        i.setJANCode(iv.getJANCode());
        i.setCreatedAt(iv.getCreatedAt());
        i.setUpdatedAt(iv.getUpdatedAt());
        i.setQuantity(iv.getQuantity()) ;

    }

}