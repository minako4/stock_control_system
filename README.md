# Stock_Control_System

* 複数の店舗でエリア別に一つの商品についての在庫数を確認できるシステムです

# DEMO
[![Image from Gyazo](https://i.gyazo.com/119b035afcc2cbe4cac22f959f6031a2.gif)](https://gyazo.com/119b035afcc2cbe4cac22f959f6031a2)


# Features

* 店舗管理機能<br>
店舗情報の登録・更新・表示・論理削除を行います。<br>
同一の店舗番号を複数登録することはできません。<br>
 JANコードは必ず13桁で入力できます。(13桁以外で登録しようとするとエラーを返却します。)<br>
* 商品情報管理機能<br>
商品情報の登録・更新・表示・削除ができます。<br>
* ログイン機能<br>
登録した店舗の「店舗番号」と「パスワード」を使ってログインを行います。<br>
* 検索機能<br>
同一エリアの店舗で扱っている同じ商品の在庫数を検索でき、一目で確認できます。


# Requirement

* javax.servlet.jsp.jstl-api 1.2.1
* taglibs-standard-impl 1.2.5
* hibernate-core 5.4.28.Final
* mysql-connector-java 8.0.23
* Java
* Tomcat9.0
* MySQL8.0

# Author

* 作成者
鈴木海南子
* E-mail
sminako32@gmail.com

# License

This software is released under the MIT License, see LICENSE.
(https://en.wikipedia.org/wiki/MIT_License).
