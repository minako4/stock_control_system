<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="constants.AttributeConst" %>
<%@ page import="constants.ForwardConst" %>

<c:set var="actItem" value="${ForwardConst.ACT_ITEM.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commShow" value="${ForwardConst.CMD_SHOW.getValue()}" />
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

        <h2>検索結果一覧</h2>
        <table id="item_list">
            <tbody>
                <tr>
                    <th class="store_name">店舗名</th>
                    <th class="item_name">品名</th>
                    <th class="jan_code">JANコード</th>
                    <th class="quantity">数量</th>
                    <th class="item_action">操作</th>
                </tr>
                <c:forEach var="item" items="${items}" varStatus="status">

                    <tr class="row${status.count % 2}">
                        <td class="store_name"><c:out value="${item.store.name}" /></td>
                        <td class="item_name"><c:out value="${item.name}" /></td>
                        <td class="jan_code"><c:out value="${item.janCode}" /></td>
                        <td class="quantity"><c:out value="${item.quantity}" /></td>
                        <td class="item_action"><a href="<c:url value='?action=${actItem}&command=${commShow}&id=${item.id}' />">詳細を見る</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <div id="pagination">
            （全 ${items_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((items_count - 1) / maxRow) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='?action=${actItem}&command=${commIdx}&page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>


</c:param>
</c:import>