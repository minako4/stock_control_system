<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="constants.AttributeConst" %>
<%@ page import="constants.ForwardConst" %>

<c:set var="action" value="${ForwardConst.ACT_ITEM.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />

<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" /><br />
        </c:forEach>

    </div>
</c:if>

<label for="${AttributeConst.ITEM_STORE.getValue()}">商品登録する店舗名</label><br />
<c:out value="${sessionScope.login_store.name}" />
<br /><br />

<label for="${AttributeConst.ITEM_MFR.getValue()}">メーカー名</label><br />
<input type="text" name="${AttributeConst.ITEM_MFR.getValue()}" id="${AttributeConst.ITEM_MFR.getValue()}" value="${item.manufacturerName}" />
<br /><br />

<label for="${AttributeConst.ITEM_NAME.getValue()}">品名</label><br />
<input type="text" name="${AttributeConst.ITEM_NAME.getValue()}" id="${AttributeConst.ITEM_NAME.getValue()}" value="${item.name}" />
<br /><br />


<label for="${AttributeConst.ITEM_CODE.getValue()}">品番</label><br />
<input type="text" name="${AttributeConst.ITEM_CODE.getValue()}" id="${AttributeConst.ITEM_CODE.getValue()}" value="${item.code}"/>
<br /><br />

<label for="${AttributeConst.ITEM_JANCODE.getValue()}">JANコード</label><br />
<input type="text" name="${AttributeConst.ITEM_JANCODE.getValue()}" id="${AttributeConst.ITEM_JANCODE.getValue()}" value="${item.janCode}"/>
<br /><br />

<label for="${AttributeConst.ITEM_QTY.getValue()}">数量</label><br />
<input type="text" name="${AttributeConst.ITEM_QTY.getValue()}" id="${AttributeConst.ITEM_QTY.getValue()}" value="${item.quantity}"/>
<br /><br />

<br /><br />
<input type="hidden" name="${AttributeConst.ITEM_ID.getValue()}" value="${item.id}" />
<input type="hidden" name="${AttributeConst.TOKEN.getValue()}" value="${_token}" />
<button type="submit">登録</button>