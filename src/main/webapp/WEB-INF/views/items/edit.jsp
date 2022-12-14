<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="constants.ForwardConst" %>

<c:set var="actItem" value="${ForwardConst.ACT_ITEM.getValue()}" />
<c:set var="commUpd" value="${ForwardConst.CMD_UPDATE.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />

<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">

        <h2>日報 編集ページ</h2>
        <form method="POST" action="<c:url value='?action=${actItem}&command=${commUpd}' />">
            <c:import url="_form.jsp" />
        </form>

    </c:param>
</c:import>
