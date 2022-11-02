<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="constants.ForwardConst" %>
<%@ page import="constants.AttributeConst" %>

<c:set var="actTop" value="${ForwardConst.ACT_TOP.getValue()}" />
<c:set var="actStore" value="${ForwardConst.ACT_STORE.getValue()}" />
<c:set var="actItem" value="${ForwardConst.ACT_ITEM.getValue()}" />
<c:set var="actLogin" value="${ForwardConst.ACT_LOGIN.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commNew" value="${ForwardConst.CMD_NEW.getValue()}" />
<c:set var="commOut" value="${ForwardConst.CMD_LOGOUT.getValue()}" />
<c:set var="commSearch" value="${ForwardConst.CMD_SEARCH.getValue()}" />


<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
    <title><c:out value="在庫管理システム" /></title>
    <link rel="stylesheet" href="<c:url value='/css/reset.css' />">
    <link rel="stylesheet" href="<c:url value='/css/style.css' />">
</head>
<body>
    <div id="wrapper">
        <div id="header">
            <div id="header_menu">
                <h1><a href="<c:url value='/?action=${actTop}&command=${commIdx}' />">在庫管理システム</a></h1>&nbsp;&nbsp;&nbsp;
                <c:if test="${sessionScope.login_store != null}">
                    <a href="<c:url value='?action=${actStore}&command=${commIdx}' />">店舗一覧</a>&nbsp;
                    <a href="<c:url value='?action=${actStore}&command=${commNew}' />">店舗登録</a>&nbsp;
                    <a href="<c:url value='?action=${actItem}&command=${commNew}' />">商品登録</a>&nbsp;
                    <a href="<c:url value='?action=${actItem}&command=${commSearch}' />">在庫検索</a>&nbsp;
                    <a href="<c:url value='?action=${actItem}&command=${commIdx}' />">商品一覧
                    </a>&nbsp;
                </c:if>
            </div>
            <c:if test="${sessionScope.login_store != null}">
                <div id="store_name">
                    <c:out value="${sessionScope.login_store.name}" />
                    &nbsp;店&nbsp;&nbsp;&nbsp;
                    <a href="<c:url value='?action=${actLogin}&command=${commOut}' />">ログアウト</a>
                </div>
            </c:if>
        </div>
        <div id="content">${param.content}</div>
        <div id="footer">by M.Suzuki</div>
    </div>
</body>
</html>