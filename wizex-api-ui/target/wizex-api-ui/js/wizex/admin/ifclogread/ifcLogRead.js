/**
 *	인터페이스로그(view)
 */
var ifcLogRead = {
		/* ================ constants definition ================ */
		//인터페이스로그 grid
		grid01Options	: {
			el	: document.querySelector('.ifcLogRead #grid01'),
			bodyHeight	: 'fitToParent',
			rowHeight : 28,
			minRowHeight : 28,
			rowHeaders	: ['rowNum'],
			columns	: [
				{
					header	: '트랜잭션ID',
					name	: 'txId',
					width	: 300,
					align	: 'center',
				},
				{
					header	: '인터페이스ID',
					name	: 'ifcId',
					width	: 200,
					align	: 'center',
				},
				{
					header	: '요청일시',
					name	: 'reqDt',
					width	: 200,
					align	: 'center',
					renderer	: {
						 type	: CustomRenderer_date
					}
				},
				{
					header	: '응답일시',
					name	: 'resDt',
					width	: 200,
					align	: 'center',
					renderer	: {
						 type	: CustomRenderer_date
					}
				},
				{
					header	: '요청IP',
					name	: 'reqIp',
					width	: 200,
					align	: 'center',
				},
				{
					header	: '처리시간(ms)',
					name	: 'prcTm',
					width	: 150,
					align	: 'center',
				}
			],
			columnOptions	: {
				resizable	: true
			}
		},

	    /* ================ variables definition ================ */

		grid01	: undefined,

		selected : undefined,

		/* ================ functions definition ================ */
		grid01Read	: function(){
			$('.ifcLogRead #grid01FileCnt').text('');
			ifcLogRead.selected = undefined;
			return $.ajax({
	            url			: contextPath + '/admin/ifcLogRead/getIfcLogList.wizex',
	            dataType	: 'json',
	            method		: 'POST',
	            data		: {
	            	ifcId		: $('.ifcLogRead input[name=ifcId]').val(),
	            	reqIp		: $('.ifcLogRead input[name=reqIp]').val(),
	            	reqStDt		: $('.ifcLogRead input[name=reqStDt]').val(),
	            	reqEdDt		: $('.ifcLogRead input[name=reqEdDt]').val(),
	            },
	            success		: function(response){
	            	ifcLogRead.grid01.resetData(response.result);
	            	ifcLogRead.grid01.refreshLayout();
	            	$('.ifcLogRead #grid01Cnt').text(response.result.length + '건');
	            }
	        });
		},

		fnEnter	: function(){
			if(util.isEnter(window.event))	ifcLogRead.grid01Read();
		},
};

$(function(){
	ifcLogRead.grid01	= new tui.Grid(ifcLogRead.grid01Options);
	ifcLogRead.grid01.refreshLayout();

	ifcLogRead.grid01.on('focusChange', function(e) {
		ifcLogRead.cellSelected = e;

		var val = ifcLogRead.grid01.getRow(e.rowKey);
		var reqMsg = util.toJson(val.reqMsg);
		var resMsg = util.toJson(val.resMsg);

		$('.ifcLogRead #text01').val(reqMsg);
		$('.ifcLogRead #text01').attr('readonly', true);
		$('.ifcLogRead #text02').val(resMsg);
		$('.ifcLogRead #text02').attr('readonly', true);
	});
});