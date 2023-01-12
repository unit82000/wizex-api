$(document).ready(function () {
	// 다이알로그(alert, info, confirm) 초기화
	dialog.initDialog();
});

var	dialog	= {
	ids			: {
		alert		: '_alert_',
		confirm		: '_confirm_'
	},

	/** 다이알로그(alert, info, confirm) 초기화 */
	initDialog	: function () {
		console.log('initDialog...');

		// alert
		$('#' + dialog.ids['alert']).dialog({
			autoOpen: false,
			resizable	: false,
			modal: true,
			width: 500,
			buttons: [
				{
					text	: '확인',
					click	: function () {
						$(this).dialog('close');

						// callback이 있으면 수행하고 제거.
						var	callback	= $(this).dialog('option', '_customOkCallback');
						if (callback) {
							callback();
							$(this).dialog('option', '_customOkCallback', undefined);
						}
					}
				}
			]
		});

		// confirm
		$('#' + dialog.ids['confirm']).dialog({
			autoOpen	: false,
			resizable	: false,
			modal		: true,
			width		: 500,
			buttons: [
				{
					text	: '확인',
					click	: function () {
						$(this).dialog('close');

						// callback이 있으면 수행하고 제거.
						var	callback	= $(this).dialog('option', '_customOkCallback');
						if (callback) {
							callback();
							$(this).dialog('option', '_customOkCallback', undefined);
						}
					}
				},
				{
					text	: '취소',
					click	: function () {
						$(this).dialog('close');

						// callback이 있으면 수행하고 제거.
						var	callback	= $(this).dialog('option', '_customCancelCallback');
						if (callback) {
							callback();
							$(this).dialog('option', '_customCancelCallback', undefined);
						}
					}
				}
			]
		});

		console.log('done.');
	},

	/**
	 * alert
	 *
	 * @param	message		보여줄 메시지(html)
	 * @param	callback	[확인] click시 호출.
	 */
	alert	: function (message, okCallback) {
		var	alertObj	= $('#' + dialog.ids['alert']);
		alertObj.dialog('open').find('div').html(message);

		// callback이 있으면 등록.
		if (okCallback) alertObj.dialog('option', '_customOkCallback', okCallback);

		// 서버 재기동 시 session 없으면 로그인페이지로
		if(sessionErrorFlag) alertObj.dialog('option', '_customOkCallback', function(){
			sessionErrorFlag = false;
			return window.location.replace(contextPath + '/login/form.wizex');
		});
	},

	/**
	 * alert
	 *
	 * @param	message		보여줄 메시지(html)
	 * @param	callback	[확인] click시 호출.
	 */
	confirm	: function (message, okCallback, cancelCallback) {
		var	alertObj	= $('#' + dialog.ids['confirm']);
		alertObj.dialog('open').find('div').html(message);

		// callback이 있으면 등록.
		if (okCallback) 	alertObj.dialog('option', '_customOkCallback', okCallback);
		if (cancelCallback) alertObj.dialog('option', '_customCancelCallback', cancelCallback);
	}
};

