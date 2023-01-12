<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" 	   uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : header.jsp
  * @Description : tiles header 화면
  * @Modification Information
  *
  *	      수정일			   수정자		수정내용
  *	---------------------------------------------------
  * 2022.10.26 		 유지우	       최초 생성
  *
  * author	유지우
  * since	2022.10.26
  */
%>

<script type="text/javascript">
var	contextPath	= '${pageContext.request.contextPath }';
</script>

<div class="navbar navbar-expand-lg fixed-top navbar-dark bg-kb-yellow">

	<div onClick="window.location.reload()" class="navbar-brand"></div>
	<!-- <h1><a href="#" onClick="window.location.reload()" class="navbar-brand">WIZEX-API</a></h1> -->
	<button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive">
	  <span class="navbar-toggler-icon"></span>
	</button>
	<div class="collapse navbar-collapse" id="navbarResponsive" style="height:100%;">
		<ul class="navbar-nav mr-auto">
			<c:forEach var="menu" items="${_menus_key_}" >
				<li class="nav-item dropdown">
					<c:choose>
						<c:when test="${menu.menuUrl == null || menu.menuUrl eq '' }">
							<a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#">【 ${menu.menuNm } 】</a>
						</c:when>
						<c:otherwise>
							<a class="nav-link" href="#"
								onclick="javascript:main.addTab('${menu.menuId }', '${menu.menuNm }', '<c:url value='${menu.menuUrl }'/>');">${menu.menuNm }</a>
						</c:otherwise>
					</c:choose>

					<%-- 2단계 메뉴 --%>
					<c:if test="${menu.children != null && fn:length(menu.children) > 0 }">
						<div class="dropdown-menu" aria-labelledby="download">
							<c:forEach var="menu2" items="${menu.children }">
								<a class="dropdown-item"  href="#"
								onclick="javascript:main.addTab('${menu2.menuId }', '${menu2.menuNm }', '<c:url value='${menu2.menuUrl }'/>');">${menu2.menuNm }</a>
							</c:forEach>
						</div>
					</c:if>
				</li>
			</c:forEach>
		</ul>

		<div style="height:100%; background:#8d744a; padding: 5px 10px 5px 10px;">
			<span class="customertitle"></span>
			<c:choose>
				<c:when test="${_user_key_ == null }">
					<a class="btn btn-secondary btn-sm" href="<c:url value="/login/form.wizex"/>">LOGIN</a>
				</c:when>
				<c:otherwise>
					<span class="adminName">${_user_key_.userNm } / ${_user_key_.userId }</span>
					<a style="padding:0.1rem 0.5rem; font-size:0.8rem;" class="btn btn-secondary btn-sm" href="<c:url value="/login/logout.wizex"/>">LOGOUT</a>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</div>
