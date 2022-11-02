<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="constants.ForwardConst" %>

<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commEdt" value="${ForwardConst.CMD_EDIT.getValue()}" />
<c:set var="actItem" value="${ForwardConst.ACT_ITEM.getValue()}" />
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">

        <h2>商品 詳細ページ</h2>

        <table>
            <tbody>
                <tr>
                    <th>店舗名</th>
                    <td><c:out value="${item.store.name}" /></td>
                </tr>
                <tr>
                    <th>メーカー名</th>
                    <td><c:out value="${item.manufacturerName}" /></td>
                </tr>
                <tr>
                    <th>品名</th>
                    <td><c:out value="${item.name}" /></td>
                </tr>
                <tr>
                    <th>品番</th>
                    <td><c:out value="${item.code}" /></td>
                </tr>
                <tr>
                    <th>JANコード</th>
                    <td><c:out value="${item.janCode}" /></td>
                </tr>
                <tr>
                    <th>在庫数</th>
                    <td><c:out value="${item.quantity}" /></td>
                </tr>
                <tr>
                    <th>登録日時</th>
                    <fmt:parseDate value="${item.createdAt}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="createDay" type="date" />
                    <td><fmt:formatDate value="${createDay}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                </tr>
                <tr>
                    <th>更新日時</th>
                    <fmt:parseDate value="${item.updatedAt}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="updateDay" type="date" />
                    <td><fmt:formatDate value="${updateDay}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                </tr>
            </tbody>
        </table>


            <p>
                <a href="<c:url value='?action=${actItem}&command=${commEdt}&id=${item.id}' />">この商品情報を編集する</a>
            </p>


        <p>
            <a href="<c:url value='?action=${actItem}&command=${commIdx}' />">一覧に戻る</a>
        </p>
    </c:param>
</c:import>