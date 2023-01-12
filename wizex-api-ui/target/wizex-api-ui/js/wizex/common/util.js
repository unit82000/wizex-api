$(document).ready(function () {
});

var	util	= {

	/** enter event 확인 */
	isEnter	: function (event) {
		var keycode = (event.keyCode ? event.keyCode : event.which);
        if(keycode == '13'){
        	event.preventDefault();
        	return true;
        }

        return false;
	},

	/**
	 * left padding.
	 */
	lPad	: function (value, length, padder) {
		if (!value)	value	= '';
		value	= value.toString();
		var	result	= '';
		for (var i = value.length; i < length; i++) {
			result	+= padder;
		}

		return result + value;
	},

	/**
	 * object가 list에 포함되어 있는지 여부 확인.
	 */
	isContain	: function (list, obj, key) {
		var	_obj;
		for (var i = 0; i < list.length; i++) {
			_obj	= list[i];
			if (_obj[key] == obj[key])	return true;
		}

		return false;
	},

	/**
	 * 최대값 조회.
	 */
	getMax		: function (list, key, initVal) {
		var	maxVal	= initVal;
		for (var i = 0; i < list.length; i++) {
			if (list[i][key] > maxVal)	maxVal	= list[i][key];
		}

		return maxVal;
	},

	/**
	 * 고정컬럼명 조회.
	 */
	getEmptyFixedColName	: function (list, key, prefix, initVal, maxVal) {
		var	colName	= prefix + util.lPad(initVal, 3, '0');
		var	exist	= false;
		for (var i = 1; i <= maxVal; i++) {
			colName	= prefix + util.lPad(i, 3, '0');

			for (var j = 0; j < list.length; j++) {
				if (list[j][key] == colName) {
					exist		= true;
					break;
				}
			}

			if (!exist)	return colName;
		}

		return colName;
	},

	/**
	 * CUD data 추출.
	 */
	getCUDData	: function (grid) {
		var	data	= grid.jsGrid('option', 'data');
		var	cudData	= [];
		for (var i = 0; i < data.length; i++) {
			if (data[i].workType == 'C' || data[i].workType == 'U' || data[i].workType == 'D') {
				cudData.push(data[i]);
				data[i].idx	= i;
			}
		}

		return cudData;
	},

	/** 날짜 검증 */
	isValidDate	: function (yyyyMMdd) {
		var	number	= parseInt(yyyyMMdd);
		if (number == NaN || yyyyMMdd.trim().length != 8) return false;

		var	yyyy	= parseInt(yyyyMMdd.substring(0, 4));
		var	mm		= parseInt(yyyyMMdd.substring(4, 6)) - 1;
		var	dd		= parseInt(yyyyMMdd.substring(6, 8));
		var	date	= new Date(yyyy, mm, dd);

		if (yyyyMMdd == util.formatYyyyMMdd(date)) return true;
		return false;
	},

	/**
	 * yyyyMMdd 형식으로 반환.
	 *
	 * @param date
	 * @returns
	 */
	formatYyyyMMdd	: function (date) {
		date	= (date || new Date());

		var	year	= date.getFullYear().toString();
		var	month	= util.lPad((date.getMonth() + 1).toString(), 2, '0');
		var	day		= util.lPad(date.getDate().toString(), 2, '0');

		return (year + month + day);
	},

	/**
	 * yyyyMMddHHmm 형식으로 반환.
	 *
	 * @param date
	 * @return
	 */
	dateFormat	: function(date, format) {
		if(!date) return '';

		if(/^[0-9]{8}$/.test(date) && format == 'yyyy-MM-dd'){
			var y = date.substr(0, 4);
			var m = date.substr(4, 2);
			var d = date.substr(6, 2);

			return y + '-' + m + '-' + d;
		}

		if(typeof date == 'string'){
			date = new Date(date);
		}

		date	= (date || new Date());

		var result = date.toString();

		var	year	= date.getFullYear().toString();
		var	month	= util.lPad((date.getMonth() + 1).toString(), 2, '0');
		var	day		= util.lPad(date.getDate().toString(), 2, '0');
		var hour	= util.lPad(date.getHours().toString(), 2, '0');
		var min		= util.lPad(date.getMinutes().toString(), 2, '0');

		switch(format){
		case 'yyyy-MM-dd HH:mm' :
			result = year + '-' + month + '-' + day + ' ' + hour + ':' + min;
		break;
		case 'yyyy-MM-dd' :
			result = year + '-' + month + '-' + day;
		break;
		case 'yyyyMMdd' :
			result = year + month + day;
		break;
		default :
		break;
		}

		return result;
	},

	/** 배열을 문자열로 */
	arrayToString	: function (list, column, del) {
		var	str	= '';
		for (var i = 0; i < list.length; i++) {
			if (i > 0)	str	+= del;
			str	+= list[i][column];
		}

		return str;
	},

	/**
	 * multi sort.
	 *
	 * @param target(objList)
	 * @param keyList(target key list)
	 * @return target
	 */
	multiSorting	: function(target, keyList){
		target.sort(function(a, b){
			return util.compareTo(a, b, keyList, 0);
		});

		return target;
	},

	/**
	 * multi sort implement.
	 *
	 * @param m(target main)
	 * @param c(target compare)
	 * @param keyList(target key list)
	 * @param *num(init 0)
	 * @return
	 */
	compareTo	: function(m, c, keyList, num){
		if(!num) num = 0;
		if(num >= keyList.length)	return 0;
		else{
			var key = keyList[num];
			var v1 = m[key];
			var v2 = c[key];

			if(!isNaN(v1)) v1 = Number(v1);
			if(!isNaN(v2)) v2 = Number(v2);

			if(v1 > v2)			return 1;
			else if(v1 < v2)	return -1;
			else if(v1 == v2)	return util.compareTo(m, c, keyList, num+1);
		}
	},

	/**
	 * table tab abled.
	 *
	 * @param selector
	 * @return
	 */
	tabableToTextArea	: function(selector) {
		$(selector).find('textarea').unbind('keydown');
		$(selector).find('textarea').keydown(function(e) {
		    if(e.keyCode === 9) { // tab was pressed

			     // get caret position/selection
			     var start = this.selectionStart;
			     var end = this.selectionEnd;

			     var $this = $(this);
			     var value = $this.val();

			     // set textarea value to: text before caret + tab + text after caret
			     $this.val(value.substring(0, start)
			                    + "\t"
			                    + value.substring(end));

			     // put caret at right position again (add one for the tab)
			     this.selectionStart = this.selectionEnd = start + 1;

			     // prevent the focus lose
			     e.preventDefault();
			 }
		});
	},

	/**
	 * (background-color)css class name return. 없으면 생성
	 *
	 * @param selectorText
	 * @return
	 */
	getColorCssClass	: function(colorCd) {
		if(util.isNull(colorCd)) return;

		var colorStr = colorCd.replace('#', '');
		var selectorText = 'wizex-tui-grid-append-custom-color-' + colorStr;
		var ruleList = customCss.rules;
		var ruleLen = ruleList.length;

		for(var i=0; i<ruleLen; i++){
			if(selectorText == ruleList[i].selectorText){
				return selectorText;
			}
		}

		customCss.insertRule('.' + selectorText + ' { background-color:' + colorCd +' !important}');

		return selectorText;
	},

	isNull	: function(str) {
		if(str == null || str == undefined || str == '') return true;
		return false;
	},

	/**
	 * isJSon
	 * json 형식의 문자열인지 체크 후 boolean 반환.
	 */
	isJson	: function(str) {
		try {
			var json = JSON.parse(str);
			return (typeof json === 'object');
		} catch(e){
			return false;
		}
	},

	/**
	 * toJson
	 * string -> json 문자열 변환(tab space 2)
	 * json 형태가 아닐 경우 원본 문자열 반환
	 */
	toJson	: function(str) {
		return util.isJson(str) ? JSON.stringify(JSON.parse(str),null,2) : str;
	}
}