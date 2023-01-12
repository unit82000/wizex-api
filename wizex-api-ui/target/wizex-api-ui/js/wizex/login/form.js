var	form	= {
    /* ================ constants definition ================ */

    /* ================ variables definition ================ */

    /* ================ functions definition ================ */
	login	: function () {
		var agent = window.navigator.userAgent.toLowerCase();

		if(agent.indexOf('trident') > -1){
			dialog.alert('Internet Explorer 브라우저로 접속할 수 없습니다.');
			return;
		}

		if(!$('#userId').val()){
			dialog.alert('아이디를 입력하세요.');
			return;
		}

		if(!$('#userPasswd').val()) {
			dialog.alert('비밀번호를 입력하세요.');
			return;
		}

		$.ajax({
			url			: contextPath + '/login/login.wizex',
			data		: $('#loginForm').serialize(),
			method		: 'post',
			dataType	: 'json',
			success		: function (data) {
				window.location.replace(contextPath + data.message);
			}
		});
	},

	getText	: function () {
		$.ajax({
			url			: contextPath + '/login/getText.wizex',
			method		: 'post',
			dataType	: 'json',
			success		: function(result) {
				$('.loginWrap #subtitle').text(result.subtitle);
				$('.loginWrap #title').text(result.title);
				$('.loginWrap #info1').text(result.info1);
				$('.loginWrap #info2').text(result.info2);
			}
		});
	}
};

function fnEnter() {
	if(util.isEnter(window.event)) form.login();
};

$(function(){
	window.location.hash = '';
	//form.getText();

	$('.loginWrap #subtitle').text("상품정보통합서비스");
	$('.loginWrap #title').text("WIZEX-ProductService");
	$('.loginWrap #info1').text("WIZEX-ProductService는 chrome 브라우저 해상도 1920 * 1080에 최적화 되어 있습니다.");

});