<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : error500.jsp
  * @Description : application error 화면
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
<html lang="ko" style="height:100%; overflow:hidden;">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="icon" type="image/png" sizes="16x16" href="<c:url value='/css/wizex/wizex.ico?v=${now}'/>"/>
    <title>접근권한오류</title>
</head>

<body style="width:100%; height:100%; position:relative; margin:0;">
<h5 style="position:absolute; top:50%; left:50%; transform:translate(-50%, -50%); display:inline-block; margin:0; font-size:1rem; color:#666;">이 기능의 접근권한이 없습니다.</h5>
</body>
</html>
