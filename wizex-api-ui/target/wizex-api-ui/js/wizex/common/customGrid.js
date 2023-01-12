class CustomRenderer_date {
	constructor(props) {
    	var el = document.createElement('div');
    	el.classList.add('tui-grid-cell-content');

    	this.el = el;
    	this.render(props);
    }

    getElement() {
    	return this.el;
    }

    render(props) {
    	var value = props.value;

    	if(props.columnInfo.editor && ('C' == props.grid.getValue(props.rowKey, 'rowStat')) && !value) {
			switch(props.columnInfo.name) {
			case 'aplyStDt' :
			case 'reqStDt'  :
				props.grid.setValue(props.rowKey, props.columnInfo.name, g_appInfo['BASE_ST_DT']);
				break;
			case 'aplyEdDt' :
			case 'reqEdDt'  :
				props.grid.setValue(props.rowKey, props.columnInfo.name, g_appInfo['BASE_ED_DT']);
				break;
			}
		}

    	$(this.el).text(util.dateFormat(value, 'yyyy-MM-dd HH:mm'));
    }
}
