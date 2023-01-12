/* ================ create instance ================ */
var wizexFile     = new WizexFile();

/* ================ object definitions ================ */
function WizexFile() {
    var vm  = this;

    /* ================ constant definitions ================ */
    var IMAGE_PATTERN   = /\.(jpg|png|gif|jpeg)$/i;

    var BBS_PATTERN     = /\.(pdf|xls|xlsx|doc|docx|ppt|pptx|jpg|png|gif|jpeg)$/i;

    var XLS_PATTERN     = /\.(xls|xlsx)$/i;

    /* ================ variable definitions ================ */
    vm.IMAGE_PATTERN    = IMAGE_PATTERN;
    vm.BBS_PATTERN      = BBS_PATTERN;
    vm.XLS_PATTERN      = XLS_PATTERN;

    /* ================ assign functions ================ */
    vm.upload			= upload;
    vm.initUpload       = initUpload;
    vm.confirm	        = confirm;
    vm.remove			= remove;
    vm.download			= download;

    /* ================ function definitions ================ */
    /**
     * 업로드
     *
     * @param arg{ file : file object, attachFileCategory : attachFileCategory, callback : callback function }
     */
    function upload(arg) {
    	var file = arg.file;
    	var attachFileCategory = arg.attachFileCategory;
    	var callback = arg.callback;

    	var formData = new FormData();
    	formData.encoding = 'multipart/form-data';
    	formData.append('file', file);
    	formData.append('attachFileCategory', attachFileCategory);

    	return $.ajax({
    		url			: contextPath + '/file/upload.wizex',
    		enctype		: 'multipart/form-data',
    		method		: 'POST',
    		data		: formData,
    		processData	: false,
    		contentType	: false,
    		async		: false,
    		success		: function(response){
    			if(callback) callback(response);
    		}
    	});
    }

    /**
     * 삭제
     *
     * @param arg{ attachFileId : attachFileId, callback : callback function }
     */
    function remove(arg) {
    	var callback = arg.callback;
    	return $.ajax({
    		url			: contextPath + '/file/remove.wizex',
    		dataType	: 'json',
            method		: 'POST',
            async		: false,
            data		: {
            	attachFileId	: arg.attachFileId
            },
           success		: function(response){
        	   if(callback) callback(response);
           }
    	});
    }

    function initUpload(file, pattern, limitSize, callback, attachFileCategory) {

        $(file).fileupload({
            url         : contextPath + '/file/upload.wizex',
            formData    : {
            	attachFileCategory        : attachFileCategory
            },
            add : function (e, data) {
                if (!pattern.test(data.originalFiles[0].name.toLowerCase())) {
                    dialog.alert('지원하지 않는 파일형식 입니다.');
                    return false;
                }

                if (data.originalFiles[0].size > limitSize) {
                	dialog.alert(limitSize + 'bytes 이하의 파일만 지원합니다.');
                    return false;
                }

                data.submit();
            },
            progressall : function (e, data) {
                var progress = parseInt(data.loaded / data.total * 100, 10);
                console.log('progress=' + progress);
            },
            done        : function (e, data) {
                callback(data);
            }
        });
    }

    /**
     * 업로드 후 확인.
     *
     * @param attachFileId
     * @returns
     */
    function confirm(attachFileId) {
    	if (!attachFileId) {
    		dialog.alert('첨부파일 아이디(attachFileId)가 없습니다.');
    		return;
    	}

    	$.ajax({
    		url			: contextPath + '/file/confirm.wizex?attachFileId=' + attachFileId,
    		dataType	: 'json',
    		success		: function (data, textStatus, jqXHR) {
    			dialog.alert('파일확인이 완료되었습니다.');
    		}
    	});
    }

    /**
     * 파일 다운로드.
     *
     * @param attachFileId
     * @returns
     */
    function download(attachFileId, attachFileNm) {
    	var retName = '첨부파일';
    	if(attachFileNm){
    		retName = attachFileNm;
    	}

    	var url = contextPath + '/file/download.wizex?attachFileId=' + attachFileId  + '&delYn=N';
    	var ret = window.open(url, retName);
    	//window.location = contextPath + '/file/download.wizex?attachFileId=' + attachFileId  + '&delYn=N';
    }
}

