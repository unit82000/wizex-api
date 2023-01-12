/* 사용자 정보 */
var g_uid;
var g_unm;
var g_auth;
var sessionErrorFlag = false;

var customCss;

var common = {
		initCss : function(auth){
			var styleSheetList = document.styleSheets;
			var styleSheet = undefined;

			for(var i=0; i<styleSheetList.length; i++){
				if(styleSheetList[i].href && styleSheetList[i].href.includes("/css/wizex/wizex.css")){
					styleSheet = styleSheetList[i];
				}

				if(styleSheetList[i].href && styleSheetList[i].href.includes("/css/wizex/wizex_custom.css")){
					customCss = styleSheetList[i];
				}
			}

			/*.auth-l*/
			//styleSheet.cssRules[429].style.display="none";

			/*.auth-m*/
			//styleSheet.cssRules[430].style.display="none";

			/*.auth-s*/
			//styleSheet.cssRules[431].style.display="none";

			/** -- 권한별 버튼 제어 일시 막음
			if(auth == 0 || auth == 1) return;

			styleSheet.cssRules[430].style.display="none";

			if(auth == 2) return;

			styleSheet.cssRules[431].style.display="none";
			*/
		},

		initResizable	: function(selector){
//			if(selector){
//				console.log(selector);
//				console.log($(selector).find('.wizexBox'));
//			}
//			$('.wizexCol').resizable({
//				ghost : true,
//				handles	: 'e',
//				stop	: function(event, ui){
//					var data = objSrch.grid01.getData();
//					objSrch.grid01.destroy();
//					objSrch.grid01	= new tui.Grid(objSrch.grid01Options);
//					objSrch.grid01.resetData(data);
//					objSrch.grid01.refreshLayout();
//				}
//			});
//
//			$('.wizexRow').resizable({
//				ghost : true,
//				handles	: 's',
//				stop	: function(event, ui){
//					var data = objSrch.grid01.getData();
//					objSrch.grid01.destroy();
//					objSrch.grid01	= new tui.Grid(objSrch.grid01Options);
//					objSrch.grid01.resetData(data);
//					objSrch.grid01.refreshLayout();
//				}
//			});
		}
};

$(function() {
	console.log('WIZEX-POM init!');
	//최초 접속 시 자동 새로고침
	if(!window.location.hash){
		window.location = window.location + '#loaded';
		window.location.reload(true);
	}
});