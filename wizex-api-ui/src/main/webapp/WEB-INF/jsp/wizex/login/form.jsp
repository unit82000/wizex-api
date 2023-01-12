<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%-- <c:set var="date" value="<%=new java.util.Date()%>" />
<c:set var="now" scope="application"><fmt:formatDate value="${date}" pattern="yyyyMMddhhmmss" /></c:set> --%>
<c:set var="now" scope="application" value="39" />
<%
  /**
  * @Class Name : template.jsp
  * @Description : tiles template 화면
  * @Modification Information
  *
  *	      수정일			   수정자		수정내용
  *	---------------------------------------------------
  * 2019.12.10 		   김경석	       최초 생성
  *
  * author	김경석
  * since	2019.12.10
  */
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>WIZEX-API</title>

    <!-- 공통 script -->
    <c:import url="/WEB-INF/jsp/layouts/admin/scripts.jsp?v=${now}" />

	<link rel="icon" type="image/png" sizes="16x16" href="<c:url value='/css/wizex/wizex.ico?v=${now}'/>"/>
    <link type="text/css" rel="stylesheet" href="<c:url value='/css/wizex/wizex.css?v=${now}'/>"/>

    <script type="text/javascript" src="<c:url value='/js/wizex/login/form.js?v=${now}'/>"></script>
</head>

<body class="h-100 pt-0">
	<div class="logoWrap">
		<h5 align="center"><img src="<c:url value='/images/logo_main_login.jpg' />" style=width:100%;></h5>
	</div>
	<div class="jumbotron loginWrap">
		<form id="loginForm" action="" method="post">

			<fieldset>
				<h5 id="subtitle" align="center" style="font-weight:bold; color:#58646a !important;"></h5>
				<h2 id="title" align="center" style="font-weight:bold;"></h2>
				<%-- <h2 align="center"><img src="<c:url value='/images/WIZEX_AXManager.jpg' />"  border="0"></h2> --%>
				<div class="form-group row">
					<label for="userId" class="col-sm-2 col-form-label">아이디</label>
					<div class="col-sm-7">
						<input type="text" class="form-control" id="userId" name="userId" value="" onkeydown="javascript:fnEnter();"/>
					</div>
				</div>
				<div class="form-group row mb-0">
					<label for="userPasswd" class="col-sm-2 col-form-label">비밀번호</label>
					<div class="col-sm-7">
						<input type="password" class="form-control" id="userPasswd" name="pwd" value="" onkeydown="javascript:fnEnter();"/>
					</div>
				</div>
			</fieldset>
			<a href="#" class="btn btn-kb-yellow col-sm-3" onclick="javascript:form.login(); return false;">LOGIN</a>
		</form>
		<div class="mainTT" style="padding-top:50px;">
			<p id="info1" class="notice" align="center"></p>
			<p id="info2" class="notice" align="center"></p>
		</div>
	</div>


	<%-- 공통 레이어 팝업(alert, info, confirm) --%>
	<c:import url="/WEB-INF/jsp/layouts/admin/dialog.jsp?v=${now}" />
</body>
</html>
