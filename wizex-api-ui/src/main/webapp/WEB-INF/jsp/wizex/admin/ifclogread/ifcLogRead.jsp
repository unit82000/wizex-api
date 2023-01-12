<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" 	uri="http://www.springframework.org/tags/form" %>

<%@ taglib prefix="wizex" uri="/WEB-INF/tlds/wizex-tags.tld"%>
<%
  /**
  * @Class Name : ifcLogRead.jsp
  * @Description : 인터페이스 로그
  * @Modification Information
  *
  *	      수정일			   수정자		수정내용
  *	---------------------------------------------------
  * 2022.10.27 		  유지우	       최초 생성
  *
  * author	유지우
  * since	2022.10.27
  */
%>

<script type="text/javascript"
	src="<c:url value='/js/wizex/admin/ifclogread/ifcLogRead.js?v=${now}'/>"></script>
<div class="wizexBox ifcLogRead">
	<div class="wizexCol" style="width: 70%;">
		<div class="wizexRow" style="height: 100%;">
			<div class="wizex-content-title">
				<h2>인터페이스 호출로그</h2>
			</div>
			<div class="wizex-content-top">
				<label class="col-form-label col-sm-1" style="text-align: center;">인터페이스ID</label>
				<input type="text" class="form-control col-sm-2" name="ifcId" onkeydown="javascript:ifcLogRead.fnEnter();" />
				<label class="col-form-label col-sm-1" style="text-align: center;">요청IP</label>
				<input type="text" class="form-control col-sm-2" name="reqIp" onkeydown="javascript:ifcLogRead.fnEnter();" />
				<label class="col-form-label col-sm-1" style="text-align:center;">요청일시</label>
				<input type="datetime-local" class="form-control col-sm-2" name="reqStDt"/>  ~
				<input type="datetime-local" class="form-control col-sm-2" name="reqEdDt" />
				<a href="#" onclick="javascript:ifcLogRead.grid01Read(); return false;" class="btn btn-kb-gray btn-sm ml-auto searchBtn">조회</a>
			</div>
			<div class="wizex-content-main">
				<div class="grid-container">
					<div id="grid01" style="width: 100%;"></div>
				</div>
			</div>
			<div class="wizex-content-bottom">
				<span id="grid01Cnt"></span>
			</div>
		</div>
	</div>
	<div class="wizexCol" style="width: calc(30% -25px);">
		<div class="wizexRow" style="height: 100%">
			<div class="wizex-content-title">
				<h2>요청 메시지</h2>
			</div>
			<div class="wizex-content-main">
				<div class="wizexCol" style="width: 100%;">
					<div class="wizexRow" style="height: 50%;">
						<div class="wizex-content-main">
							<div class="text-container">
								<textarea id="text01"></textarea>
							</div>
						</div>
					</div>
					<div class="wizexRow" style="height: 50%;">
						<div class="wizex-content-title">
							<h2>응답 메시지</h2>
						</div>
						<div class="wizex-content-main">
							<div class="text-container">
								<textarea id="text02"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>