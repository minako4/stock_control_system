<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="constants.AttributeConst" %>
<%@ page import="constants.ForwardConst" %>

<c:set var="action" value="${ForwardConst.ACT_STORE.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />

<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" /><br />
        </c:forEach>

    </div>
</c:if>

<label for="${AttributeConst.STORE_NAME.getValue()}">店舗名</label><br />
<input type="text" name="${AttributeConst.STORE_NAME.getValue()}" id="${AttributeConst.STORE_NAME.getValue()}" value="${store.name}" />
<br /><br />

<label for="${AttributeConst.STORE_STORE_CODE.getValue()}">店舗コード</label><br />
<input type="text" name="${AttributeConst.STORE_STORE_CODE.getValue()}" id="${AttributeConst.STORE_STORE_CODE.getValue()}" value="${store.storeCode}" />
<br /><br />

<label for="${AttributeConst.STORE_AREA_CODE.getValue()}">エリアコード</label><br />
<input type="text" name="${AttributeConst.STORE_AREA_CODE.getValue()}" id="${AttributeConst.STORE_AREA_CODE.getValue()}" value="${store.areaCode}" />
<br /><br />


<label for="${AttributeConst.STORE_PASS.getValue()}">パスワード</label><br />
<input type="password" name="${AttributeConst.STORE_PASS.getValue()}" id="${AttributeConst.STORE_PASS.getValue()}" />
<br /><br />

<br /><br />
<input type="hidden" name="${AttributeConst.STORE_ID.getValue()}" value="${store.id}" />
<input type="hidden" name="${AttributeConst.TOKEN.getValue()}" value="${_token}" />
<button type="submit">投稿</button>