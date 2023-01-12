/**
 * 메인
 */

var	main	= {
    /* ================ constants definition ================ */

    /* ================ variables definition ================ */

    /* ================ functions definition ================ */
	addTab	: function (id, name, url) {
		//접속정보 확인. 세션 종료 시 로그인페이지로 이동
		if(!g_userId || !g_userNm || !g_auth){
			dialog.alert('세션 정보가 없습니다. 로그인 페이지로 이동합니다.');
			return window.location.replace(contextPath + '/login/form.wizex');
		}

		if (!url) {
			dialog.alert('URL이 정의되지 않은 메뉴입니다. URL을 먼저 등록한 후 다시 시도해주세요.');
			return;
		}

		$.ajax({
			url			: url,
			dataType	: 'html',
			success		: function (data, textStatus, jqXHR) {
				//ajax 전처리 -> 에러 시 tab 생성 막기 위해 success로 이동
				var	existObj	= $('a[href="#' + id + '"]');
				if (existObj.length > 0) {
					existObj.trigger('click');
					return;
				}

				var	mainTabs	= $('#mainTabs');
				var	tab			= $('<li>').addClass('nav-item');
				var	a	= $('<a>').addClass('nav-link').attr('data-toggle', 'tab').attr('href', '#' + id).html(name).appendTo(tab);
				$('<span>').addClass('badge').addClass('badge-pill').addClass('badge-secondary').html('X').appendTo(a).click(function (e) {
					e.preventDefault();
					var	a	= $(e.target).parent();

					$(a.attr('href')).remove();
					a.parent().remove();

					$('a[href=#welcome]').trigger('click');
				});

				//tab.appendTo(mainTabs);
				// 탭 일괄 종료 추가 후 변경
				$('#mainTabs li.endTab').before(tab);

				//tab 개수의 넓이가 contents넓이 보다클때 탭생성 멈춤
				var mainTabsum=0;
				var breakFlag = false;
				$("#mainTabs li").each(function(){
					var maintabWidth = $(this).width();
					    contentsWidth = $("#mainTabContents").width()-50;
					mainTabsum += Number(maintabWidth);
					if(mainTabsum>contentsWidth){
						dialog.alert('탭 영역이 벗어나 더이상 탭을 생성할 수 없습니다.');
						tab.remove();
						if (!$('.navbar-toggler').is(':hidden')) $('.navbar-toggler').trigger('click');
						breakFlag = true;
						return false;
					}
				});

				if(breakFlag) return;
				//-----------------

				var	mainTabContents	= $('#mainTabContents');
				var	content			= $('<div>').addClass('tab-pane').addClass('fade').addClass('container-fluid').attr('id', id).appendTo(mainTabContents);
				content.html(data);
				a.trigger('click');

				//code.appendSelOptions(mainTabContents);
				util.tabableToTextArea(mainTabContents);
				//customGrid.customEditMod(data);

				//후처리
				if (!$('.navbar-toggler').is(':hidden')) $('.navbar-toggler').trigger('click');
			}
		});
	},

	tabAllClose	: function() {
		var delList = $('#mainTabs li').not('li.default-tab');

		$(delList).each((index, item) => {
			$($(item).find('a.nav-link').attr('href')).remove();
		});

		$(delList).remove();

		$('a[href=#welcome]').trigger('click');
	},

	 menuDesc	: function() {
		var menuId = $('a.nav-link.active').attr('href');
		menuId = menuId.substring(1);

		return $.ajax({
            url			: contextPath + '/admin/menu/getMenuOne.wizex',
            dataType	: 'json',
            method		: 'POST',
            data		: {
            	menuId		: menuId
            },
            success		: function(response){
            	var result = response.result;

            	if(result.length > 0){
                	var rowData = result[0];

                	var title = '화면가이드 - ' + rowData.menuId + '(' + rowData.menuNm + ')';

                	editorPop.fnOpen({
            			title		: title,
            			readData	: rowData.describe
            		});
            	}else{
            		dialog.alert('화면가이드가 제공되지 않는 화면입니다.');
            		return;
            	}
            }
        });
	}
};

$(function() {
	common.initCss();
	util.tabableToTextArea($('body'));

	// 그리드 테마 적용
	tui.Grid.applyTheme('default', {
		selection	: {
			background : '#fff687'
		},
		row	: {
			hover	: {
				background	: '#ffe60073'
			}
		},
		area	: {
			body	: {
				background : '#ffffff'
			}
		},
		cell: {
			normal: {
				//background: '#f5f5f5',
			    background: 'rgba(205, 205, 205, 0.15)',
			    border: '#e2e2e2',
			    text: '#686b68',
			    showVerticalBorder : true
			},
			header: {
				background: '#bcb8aa',
				border: '#a39f93',
				text: '#fff',
			},
			rowHeader	: {
				background	: '#eeecea',
				text	: '#686b68'
			},
			selectedHeader : {
				background : '#837e74'
			},
			selectedRowHeader : {
				background : '#fffde6'
			},
			focused	: {
				background : '#ffec00',
				border	: '#000000'
			},
			focusedInactive	: {
				border	: '#000000'
			},
			editable: {
				background: 'rgba(0, 0, 0, 0)',
				border: '#e2e2e2',
			    text: '#686b68',
			    showVerticalBorder : true
			},
			currentRow : {
				background : '#fff687'
			}
		}
	});

	//그리드 언어 적용
	tui.Grid.setLanguage('ko', {
		filter	: {
			contains: '포함',
			eq: '같음',
			ne: '같지 않음',
			start: '시작 문자',
			end: '끝 문자'
		}
	});
});