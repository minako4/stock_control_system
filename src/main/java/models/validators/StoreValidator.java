package models.validators;

import java.util.ArrayList;
import java.util.List;

import actions.views.StoreView;
import constants.MessageConst;
import services.StoreService;

public class StoreValidator {
    /**
     * 店舗インスタンスの各項目についてバリデーションを行う
     * @param service 呼び出し元Serviceクラスのインスタンス
     * @param sv StoreViewのインスタンス
     * @param storecodeDuplicateCheckFlag 店舗コードの重複チェックを実施するかどうか(実施する:true 実施しない:false)
     * @param passwordCheckFlag パスワードの入力チェックを実施するかどうか(実施する:true 実施しない:false)
     * @return エラーのリスト
     */
    public static List<String> validate(
            StoreService service, StoreView sv, Boolean storeCodeDuplicateCheckFlag,Boolean isNumeric ,Boolean passwordCheckFlag) {
        List<String> errors = new ArrayList<String>();
      //店舗名のチェック
        String nameError = validateName(sv.getName());
        if (!nameError.equals("")) {
            errors.add(nameError);
        }

        //店舗コードのチェック
        String storeCodeError = validateStoreCode(service, sv.getStoreCode(), storeCodeDuplicateCheckFlag);
        if (!storeCodeError.equals("")) {
            errors.add(storeCodeError);
        }
        //エリアコードのチェック
        String areaCodeError = validateAreaCode(sv.getAreaCode(), isNumeric);
        if (!areaCodeError.equals("")) {
            errors.add(areaCodeError);
        }



        //パスワードのチェック
        String passError = validatePassword(sv.getPassword(), passwordCheckFlag);
        if (!passError.equals("")) {
            errors.add(passError);
        }

        return errors;
    }


    /**
     * 店舗コードの入力チェックを行い、エラーメッセージを返却
     * @param service StoreServiceのインスタンス
     * @param storeCode  店舗コード
     * @param codeDuplicateCheckFlag 店舗コードの重複チェックを実施するかどうか(実施する:true 実施しない:false)
     * @return エラーメッセージ
     */
    private static String validateStoreCode(StoreService service, String storeCode, Boolean codeDuplicateCheckFlag) {

        //入力値がなければエラーメッセージを返却
        if (storeCode == null || storeCode.equals("")) {
            return MessageConst.E_NOSTORE_CODE.getMessage();
        }

        if (codeDuplicateCheckFlag) {
            //店舗コードの重複チェックを実施

            long storesCount = isDuplicateStore(service, storeCode);

            //同一店舗コードが既に登録されている場合はエラーメッセージを返却
            if (storesCount > 0) {
                return MessageConst.E_STORE_CODE_EXIST.getMessage();
            }
        }

        //エラーがない場合は空文字を返却
        return "";
    }
    /**
     * 店舗名に入力値があるかをチェックし、入力値がなければエラーメッセージを返却
     * @param name 氏名
     * @return エラーメッセージ
     */
    private static String validateName(String name) {

        if (name == null || name.equals("")) {
            return MessageConst.E_NOSTORENAME.getMessage();
        }

        //入力値がある場合は空文字を返却
        return "";
    }
    /**
     * @param service StoreServiceのインスタンス
     * @param storeCode 店舗コード
     * @return 店舗テーブルに登録されている同一店舗コードのデータの件数
     */
    private static long isDuplicateStore(StoreService service, String storeCode) {

        long storesCount = service.countByCode(storeCode);
        return storesCount;
    }

    /**
     * エリアコードの入力チェックを行い、入力値がなければエラーメッセージを返却
     * @param areaCode エリアコード
     * @return エラーメッセージ
     */
    private static String validateAreaCode(String areaCode,Boolean isNumeric) {

        if (areaCode == null || areaCode.equals("")) {
            return MessageConst.E_NOAREA_CODE.getMessage();
        }
        boolean isisNumeric = areaCode.matches("[0-9]");
        if (! isisNumeric) {
                // 数値でない場合エラーメッセージを返却
                return MessageConst.E_AREA_CODE_NUM.getMessage();
            }


        //エラーがない場合は空文字を返却
        return "";

    }



    /**
     * パスワードの入力チェックを行い、エラーメッセージを返却
     * @param password パスワード
     * @param passwordCheckFlag パスワードの入力チェックを実施するかどうか(実施する:true 実施しない:false)
     * @return エラーメッセージ
     */
    private static String validatePassword(String password, Boolean passwordCheckFlag) {

        //入力チェックを実施 かつ 入力値がなければエラーメッセージを返却
        if (passwordCheckFlag && (password == null || password.equals(""))) {
            return MessageConst.E_NOPASSWORD.getMessage();
        }

        //エラーがない場合は空文字を返却
        return "";
    }
}
