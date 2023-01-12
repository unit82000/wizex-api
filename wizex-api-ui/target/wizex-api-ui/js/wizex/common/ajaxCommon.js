/**
 * ajax의 prefilter를 구현.
 */
$(document).ready(function () {
	// 배열관련 오류처리.
	$.ajaxSetup({
		traditional	: true
	});
});

$.ajaxPrefilter(function (options, originalOptions, jqXHR){
    // error handling
    var errorHanlder    = originalOptions.error;
    if (!errorHanlder) {
        options.error   = function (jqXHR, textStatus, errorThrown) {
        	console.log(jqXHR);
        	if(jqXHR.responseJSON && jqXHR.responseJSON.errorCode) {
        		dialog.alert(jqXHR.responseJSON.errorMessage);
        	} else {
        		alert(textStatus);
        	}
        };
    }
    else {
        options.error   = errorHanlder;
    }

    // before send handling
    var	beforeSend		= originalOptions.beforeSend;
    if (!beforeSend) {
    	options.beforeSend	= function (jqXHR, settings) {
    		// loading start
    		if(options.progress != false){	// 2021.10.21 양준모 추가-- masking true/false
    			$('body').mask('<div class="loading-container"><div class="loading"></div><div id="loading-text"></div></div>');
    		}

    		// 2022.10.26 양준모 추가 -- 요청 url 패턴 분기처리 용 callType 추가. default : 'service', .wizex : 'web'
    		// 해당 분기처리에 따라 exception 발생 시 return 객체 생성 (default : responseVO, .wizex : responseEntity)
    		settings.url.includes('.wizex') ? jqXHR.setRequestHeader("callType", "web") : jqXHR.setRequestHeader("callType", "service");
    	}
    }

    else {
    	options.beforeSend	= beforeSend;
    }

    // complete handling
    var	complete		= originalOptions.complete;
    if (!complete) {
    	options.complete	= function (jqXHR, settings) {
    		// loading end
    		if(options.progress != false){
    			if($('body').find('.loading-container').length > 0){
    				$('body').unmask();
    			}
    		}
    	}
    }else {
		// loading end
    	if(options.progress != false){
			if($('body').find('.loading-container').length > 0){
				$('body').unmask();
			}
		}
    	options.complete	= complete;
    }
});

