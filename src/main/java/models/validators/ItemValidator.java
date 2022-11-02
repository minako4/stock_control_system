package models.validators;

import java.util.ArrayList;
import java.util.List;

import actions.views.ItemView;
import constants.MessageConst;


public class ItemValidator {

    /**
     * 商品インスタンスの各項目についてバリデーションを行う
     * @param iv 商品インスタンス
     * @return エラーのリスト
     */
    public static List<String> validate(ItemView iv, Boolean isNumeric) {
        List<String> errors = new ArrayList<String>();

        //メーカーManufacturerNameのチェック
        String manufacturerNameError = validateManufacturerName(iv.getManufacturerName());
        if (!manufacturerNameError.equals("")) {
            errors.add(manufacturerNameError);
        }

        //品名Nameのチェック
        String nameError = validateName(iv.getName());
        if (!nameError.equals("")) {
            errors.add(nameError);
        }
        //品番Codeのチェック
        String codeError = validateCode(iv.getCode());
        if (!codeError.equals("")) {
            errors.add(codeError);
        }

        //janCodeのチェック
        String janCodeError = validateJanCode(iv.getJanCode(),isNumeric);
        if (!janCodeError.equals("")) {
            errors.add(janCodeError);
        }
        //数量Quantityのチェック
        String quantityError = validateQuantity(iv.getQuantity());
        if (!quantityError.equals("")) {
            errors.add(quantityError);
        }
        return errors;
    }

    /**
     * メーカー名に入力値があるかをチェックし、入力値がなければエラーメッセージを返却
     * @param  manufacturerName メーカー名
     * @return エラーメッセージ
     */
    private static String validateManufacturerName(String manufacturerName) {
        if (manufacturerName == null || manufacturerName.equals("")) {
            return MessageConst.E_NOMFR.getMessage();
        }
        //入力値がある場合は空文字を返却
        return "";
    }

    /**
     * 商品名に入力値があるかをチェックし、入力値がなければエラーメッセージを返却
     * @param  name 商品名
     * @return エラーメッセージ
     */
    private static String validateName(String name) {
        if (name == null || name.equals("")) {
            return MessageConst.E_NONAME.getMessage();
        }

        return "";
    }

    /**
     * 品番に入力値があるかをチェックし、入力値がなければエラーメッセージを返却
     * @param  code 品番
     * @return エラーメッセージ
     */
    private static String validateCode(String code) {
        if (code == null || code.equals("")) {
            return MessageConst.E_NOCODE.getMessage();
        }
        return "";
    }

    /**
     * JANコードに入力値があるかをチェックし、入力値がなければエラーメッセージを返却
     * @param jancodeCheckFlag
     * @param  jancode 品番
     * @return エラーメッセージ
     */
    private static String validateJanCode(String janCode, Boolean isNumeric) {
        if (janCode == null || janCode.equals("")) {
            return MessageConst.E_NOJANCODE.getMessage();
        }
        boolean isisNumeric = janCode.matches("[0-9]{13}");
        if (! isisNumeric) {
                // 数値でない場合、13桁でない場合エラーメッセージを返却
                return MessageConst.E_JANCODE_13.getMessage();
            }


        //エラーがない場合は空文字を返却
        return "";

    }


    /**
     * 数量に入力値があるかをチェックし、入力値がなければエラーメッセージを返却
     * @param  quantity 数量
     * @return エラーメッセージ
     */
    private static String validateQuantity(String quantity) {
        if (quantity == null || quantity.equals("")) {
            return MessageConst.E_NOQTY.getMessage();
        }
        return "";
    }

}
