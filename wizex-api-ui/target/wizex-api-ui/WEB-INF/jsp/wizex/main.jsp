<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%
  /**
  * @Class Name : main.jsp
  * @Description : PF Main 화면
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
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <title>WIZEX-API</title>

	<!-- 공통 script -->
    <c:import url="/WEB-INF/jsp/layouts/admin/scripts.jsp?v=${now}" />

	<script type="text/javascript" src="<c:url value='/js/wizex/main.js?v=${now}'/>"></script>

	<link rel="icon" type="image/png" sizes="16x16" href="<c:url value='/css/wizex/wizex.ico?v=${now}'/>"/>
    <link type="text/css" rel="stylesheet" href="<c:url value='/css/wizex/wizex.css?v=${now}'/>"/>
    <link type="text/css" rel="stylesheet" href="<c:url value='/css/wizex/wizex_custom.css?v=${now}'/>"/>

<script type="text/javascript">
$(document).ready(function(){

	/* 사용자 정보 매핑*/
	g_userId = '<c:out value="${_user_key_.userId}"/>';
	g_userNm = '<c:out value="${_user_key_.userNm}"/>';
	g_auth = '<c:out value="${_user_key_.auth}"/>';

	// 로그인 정보 없을 시 login page로 이동
	if(!g_userId){
		window.location.replace(contextPath + '/login/form.wizex');
	}

	common.initCss(g_auth);
});

function f_requestAPI() {
	var	apiId			= $('select[name=apiId]').val();
	var	parameters		= $('textarea[name=parameters]').val();
	var	responseType	= $('select[name=responseType]').val();

	if (!apiId) {
		alert('API ID를 입력하세요.');
		return false;
	}

	var	url	= contextPath + '/api/' + apiId + '.' + responseType + '?v=1.0';
	$.ajax({
        url			: url,
        data		: JSON.parse(parameters),
        dataType	: responseType == 'jsonapi' ? 'json' : 'xml',
        method		: 'POST'
    }).done(function(response) {
    	console.log(response);
    	if (responseType == 'jsonapi')
    		$('textarea[name=response]').val(JSON.stringify(response));
    	else
    		$('textarea[name=response]').val((new XMLSerializer()).serializeToString(response));
    });
};

function f_changeApiId() {
	var	params	= '';
	var	apiId			= $('select[name=apiId]').val();
	console.log(apiId);
	if (apiId == 'S00002') {
		params	= '{ "pdCd" : "KP0024960", "stDt" : "20220918" }';

	} else if (apiId == 'CND0000001') {
		params	= '{ "stDt" : "20220918", "saleChannelCode" : "GA", "payRuleCode" : "01" }';

	} else {
		params	= '{}';
	}

	$('textarea[name=parameters]').val(params);
};

function f_clearCache() {
	var	url	= contextPath + '/admin/cache/clearCache.do';
	$.ajax({
        url			: url,
        data		: {},
        dataType	: 'json',
        method		: 'POST'
    }).done(function(response) {
    	console.log(response);
    });
}

function f_clearCndRule() {
	var	url	= contextPath + '/admin/cndrule/clearCndRule.do';
	$.ajax({
        url			: url,
        data		: {},
        dataType	: 'json',
        method		: 'POST'
    }).done(function(response) {
    	console.log(response);
    });
}
</script>
</head>
<body class="h-100">
	<c:import url="/WEB-INF/jsp/layouts/admin/header.jsp" />

	<div class="align-self-start wizex-content">
		<ul id="mainTabs" class="nav nav-tabs">
			<li class="nav-item default-tab">
				<a class="nav-link active" data-toggle="tab" href="#welcome">Main</a>
			</li>
			<li class="nav-item dafault-tab endTab ml-auto">
				<!-- <a href="#" id="menuDesc" onclick="javascript:main.menuDesc(); return false;" class="btn btn-secondary btn-sm">화면가이드</a> -->
				<a href="#" id="tabAllClose" onclick="javascript:main.tabAllClose(); return false;" class="btn btn-secondary btn-sm">전체닫기</a>
			</li>
		</ul>
		<div id="mainTabContents" class="tab-content">
			<div class="tab-pane fade container-fluid show active" id="welcome">
				<div class="wizexBox mainArea">
					<div class="wizexCol" style="width:65%;">
						<div class="wizexRow" style="height:100%">
							<div class="wizex-content-title">
								<h2>API 테스트</h2>
							</div>
							<div class="gridSrch binbox">
								<div class="row">
									<label for="" class="col-form-label">API ID</label>
									<select name="apiId" class="form-control col-sm-2" onchange="javascript:f_changeApiId();">
										<option value="">선택</option>
										<option value="S00002">상품_기본속성정보조회(S00002)</option>
										<option value="CND0000001">인수규칙1(CND0000001)</option>
										<option value="users">users(EXAM)</option>
										<option value="products">products(EXAM)</option>
									</select>
									<select name="responseType" class="form-control col-sm-2">
										<option value="jsonapi">JSON</option>
										<option value="xmlapi">XML</option>
									</select>
									<a href="#" class="btn btn-kb-gray btn-sm ml-auto searchBtn" onclick="javascript:f_requestAPI();">조회</a>
									<a href="#" class="btn btn-kb-gray btn-sm searchBtn" onclick="javascript:f_clearCache();">캐시초기화</a>
									<a href="#" class="btn btn-kb-gray btn-sm searchBtn" onclick="javascript:f_clearCndRule();">조건규칙초기화</a>
								</div>
							</div>
							<div class="resizeHeight" style="height: 134px;">
								<textarea name="parameters" class="col-sm-12" style="height: 100%;" placeholder="parameters..."></textarea>
							</div>
							<div class="resizeHeight" style="height: 200px; margin-top: 5px;">
								<textarea name="response" class="col-sm-12" style="height: 100%;"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<c:import url="/WEB-INF/jsp/layouts/admin/footer.jsp?v=${now}" />

</body>
</html>