<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="constants.ForwardConst" %>
<%@ page import="constants.AttributeConst" %>

<c:set var="actStore" value="${ForwardConst.ACT_STORE.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commEdit" value="${ForwardConst.CMD_EDIT.getValue()}" />

<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">

        <h2>店舗名 : ${store.name} の登録情報 詳細ページ</h2>

        <table>
            <tbody>
                <tr>
                    <th>店舗名</th>
                    <td><c:out value="${store.name}" /></td>
                </tr>
                <tr>
                    <th>店舗コード</th>
                    <td><c:out value="${store.storeCode}" /></td>
                </tr>
                <tr>
                     <th>エリアコード</th>
                    <td><c:out value="${store.areaCode}" /></td>
                </tr>
                <tr>
                    <th>登録日時</th>
                    <fmt:parseDate value="${store.createdAt}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="createDay" type="date" />
                    <td><fmt:formatDate value="${createDay}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                </tr>
                <tr>
                    <th>更新日時</th>
                    <fmt:parseDate value="${store.updatedAt}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="updateDay" type="date" />
                    <td><fmt:formatDate value="${updateDay}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                </tr>
            </tbody>
        </table>

        <p>
            <a href="<c:url value='?action=${actStore}&command=${commEdit}&id=${employee.id}' />">この店舗の登録情報を編集する</a>
        </p>

        <p>
            <a href="<c:url value='?action=${actStore}&command=${commIdx}' />">一覧に戻る</a>
        </p>
    </c:param>
</c:import>