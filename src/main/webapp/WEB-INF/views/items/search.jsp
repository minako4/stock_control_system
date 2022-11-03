<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="constants.AttributeConst" %>
<%@ page import="constants.ForwardConst" %>

<c:set var="actItem" value="${ForwardConst.ACT_ITEM.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commSrp" value="${ForwardConst.CMD_SRP.getValue()}" />
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" /><br />
        </c:forEach>

    </div>
</c:if>

        <h2>検索</h2>
<form method="POST" action="<c:url value='/?action=${actItem}&command=${commSrp}' />">
<label for="${AttributeConst.STORE_AREA_CODE.getValue()}">エリアコード</label><br />
<input type="text" class="search" name="${AttributeConst.STORE_AREA_CODE.getValue()}" id="${AttributeConst.STORE_AREA_CODE.getValue()}" value="${store.storeAreaCode}" />
<br /><br />



<label for="${AttributeConst.ITEM_JANCODE.getValue()}">JANコード</label><br />
<input type="text" class="search" name="${AttributeConst.ITEM_JANCODE.getValue()}" id="${AttributeConst.ITEM_JANCODE.getValue()}" value="${item.janCode}"/>
<br /><br />


<br /><br />

<button type="submit">検索</button>
</form>
</c:param>
</c:import>