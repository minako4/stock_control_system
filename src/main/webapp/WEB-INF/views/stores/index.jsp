<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="constants.AttributeConst" %>
<%@ page import="constants.ForwardConst" %>

<c:set var="actStore" value="${ForwardConst.ACT_STORE.getValue()}" />
<c:set var="commShow" value="${ForwardConst.CMD_SHOW.getValue()}" />
<c:set var="commNew" value="${ForwardConst.CMD_NEW.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />

<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>店舗リスト</h2>
        <table id="store_list">
            <tbody>
                <tr>
                    <th>店舗名</th>
                    <th>店舗コード</th>
                    <th>エリアコード</th>
                    <th>操作</th>
                </tr>
                <c:forEach var="store" items="${stores}" varStatus="status">
                    <tr class="row${status.count % 2}">

                        <td><c:out value="${store.name}" /></td>
                        <td><c:out value="${store.storeCode}" /></td>
                        <td><c:out value="${store.areaCode}" /></td>

                        <td>
                            <c:choose>
                                <c:when test="${store.deleteFlag == AttributeConst.DEL_FLAG_TRUE.getIntegerValue()}">
                                    （削除済み）
                                </c:when>
                                <c:otherwise>
                                    <a href="<c:url value='?action=${actStore}&command=${commShow}&id=${store.id}' />">詳細を見る</a>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div id="pagination">
            （全 ${stores_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((stores_count - 1) / maxRow) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='?action=${actStore}&command=${commIdx}&page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
        <p><a href="<c:url value='?action=${actStore}&command=${commNew}' />">新規店舗の登録</a></p>

    </c:param>
</c:import>