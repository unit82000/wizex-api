<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<script type="text/javascript">
var	contextPath	= '${pageContext.request.contextPath }';
</script>

<!-- jquery -->
<script type="text/javascript" src="<c:url value='/js/libs/jquery/jquery-1.11.3.js'/>"></script>

<!-- project common -->
<script type="text/javascript" src="<c:url value='/js/wizex/common/common.js?v=${now}'/>"></script>
<script type="text/javascript" src="<c:url value='/js/wizex/common/util.js?v=${now}'/>"></script>
<script type="text/javascript" src="<c:url value='/js/wizex/common/ajaxCommon.js?v=${now}'/>"></script>

<!-- loadmask -->
<script type="text/javascript" src="<c:url value='/js/libs/jquery/jquery.loadmask.min.js'/>"></script>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/libs/jquery/jquery.loadmask.css'/>"/>

<!-- bootstrap -->
<%-- <link type="text/css" rel="stylesheet" href="<c:url value='/bootstrap-4.4.1-dist/css/bootstrap-cerulean.css'/>"/>
<script type="text/javascript" src="<c:url value='/bootstrap-4.4.1-dist/js/bootstrap.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/bootstrap-4.4.1-dist/js/popper.min.js'/>"></script> --%>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/libs/bootstrap-4.4.1-dist/bootstrap-cerulean.css'/>"/>
<script type="text/javascript" src="<c:url value='/js/libs/bootstrap-4.4.1-dist/bootstrap.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/libs/bootstrap-4.4.1-dist/popper.min.js'/>"></script>

<!-- jquery ui -->
<script type="text/javascript" src="<c:url value='/js/libs/jquery-ui-1.12.1/jquery-ui.js'/>"></script>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/libs/jquery-ui-1.12.1/jquery-ui.min.css'/>"/>

<!-- jstree -->
<script type="text/javascript" src="<c:url value='/js/libs/jstree/jstree.js'/>"></script>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/libs/jstree/themes/default/style.css'/>"/>

<!-- tui-datepicker -->
<script type="text/javascript" src="<c:url value='/js/libs/tui-datepicker/tui-date-picker.min.js'/>"></script>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/libs/tui-datepicker/tui-date-picker.min.css'/>"/>

<!-- tui-grid -->
<script type="text/javascript" src="<c:url value='/js/libs/tui-grid/tui-pagination.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/libs/tui-grid/tui-grid.js?v=${now}'/>"></script>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/libs/tui-grid/tui-pagination.css'/>"/>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/libs/tui-grid/tui-grid.css'/>"/>
<!-- custom grid class -->
<script type="text/javascript" src="<c:url value='/js/wizex/common/customGrid.js?v=${now}'/>"></script>