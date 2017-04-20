<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/version/taglibs.jsp"%>
<%@ include file="/version/meta.jsp"%>
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="box_fanye">
	<tr>
		<td bgcolor="#FFFFFF" style="width:100%" align="right">
			共<c:out value="${pgBean.recordCount}" />条,共<c:out value="${pgBean.pageCount}" />页
			&nbsp;&nbsp;
			<c:if test="${pgBean.currentPage != 1}">
			<a href="javascript:toNextPage('<c:out value="${url}"/>','<c:out value="${flag}"/>','<c:out value="${pgBean.prePage}"/>','<c:out value="${param1}"/>','<c:out value="${param2}"/>')">上一页</a>
			</c:if>
			&nbsp;&nbsp;
			<c:choose>
				<c:when test="${pgBean.pageCount <= 10}">
					<c:set var="begin" value="${pgBean.firstPage}"/>
					<c:set var="end" value="${pgBean.pageCount}"/>
				</c:when>
				<c:otherwise>
					<c:choose>
						<c:when test="${pgBean.currentPage <= 3}">
							<c:set var="begin" value="${pgBean.firstPage}"/>
							<c:set var="end" value="${begin + 9}"/>
						</c:when>
					<c:otherwise>
					<c:choose>
						<c:when test="${(pgBean.currentPage - 2 + 9 )< pgBean.pageCount}">
							<c:set var="begin" value="${pgBean.currentPage - 2 }"/>
							<c:set var="end" value="${begin + 9}"/>
						</c:when>
						<c:otherwise>
							<c:set var="begin" value="${pgBean.pageCount - 9}"/>
							<c:set var="end" value="${pgBean.pageCount}"/>
						</c:otherwise>
					</c:choose>
					</c:otherwise>
					</c:choose>
				</c:otherwise>
			</c:choose>
			<c:forEach begin="${begin}" step="1" end="${end}" var="idx">
				<c:choose>
				<c:when test="${pgBean.currentPage == idx}">
				<a href="javascript:toNextPage('<c:out value="${url}"/>','<c:out value="${flag}"/>','<c:out value="${idx}"/>','<c:out value="${param1}"/>','<c:out value="${param2}"/>')"">[${idx}]</a>
				</c:when>
				<c:otherwise>
				<a href="javascript:toNextPage('<c:out value="${url}"/>','<c:out value="${flag}"/>','<c:out value="${idx}"/>','<c:out value="${param1}"/>','<c:out value="${param2}"/>')"">${idx}</a>
				</c:otherwise>
				</c:choose>
			</c:forEach>
			&nbsp;&nbsp;
			<c:if test="${pgBean.currentPage != pgBean.pageCount}">
			<a href="javascript:toNextPage('<c:out value="${url}"/>','<c:out value="${flag}"/>','<c:out value="${pgBean.nextPage}"/>','<c:out value="${param1}"/>','<c:out value="${param2}"/>')">下一页</a>
			</c:if>
		</td>
	</tr>
</table>
</td>
