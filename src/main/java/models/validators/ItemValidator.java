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
    public static List<String> validate(ItemView iv) {
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
        //JANコードJANCodeのチェック
        String JANCodeError = validateJANCode(iv.getJANCode());
        if (!JANCodeError.equals(null)) {
            errors.add(JANCodeError);
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
     * JANコードに入力値があるかをチェックし、入力値がなければエラーメッセージを返却、
     * 13桁でなければエラーメッセージを返却
     * @param  janCode
     * @param isNumeric(JANコードの数値かどうかのチェックを実施)
     * @param codeDigitCheckFlag  JANコードの桁数チェックを実施(13桁:true それ以外:false)
     * @return エラーメッセージ
     */
    private static String validateJANCode(String janCode) {
      //入力値がなければエラーメッセージを返却
        if (janCode == null || janCode.equals("")) {
            return MessageConst.E_NOJANCODE.getMessage();
        }
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
