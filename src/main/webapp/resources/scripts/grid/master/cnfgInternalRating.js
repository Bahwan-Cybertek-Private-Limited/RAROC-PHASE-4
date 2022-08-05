/* 
 * © 2013 Asymmetrix Solutions Private Limited. All rights reserved.
 * This work is part of the Risk Solutions and is copyrighted by Asymmetrix Solutions Private Limited.
 * All rights reserved.  No part of this work may be reproduced, stored in a retrieval system, adopted or 
 * transmitted in any form or by any means, electronic, mechanical, photographic, graphic, optic recording or
 * otherwise translated in any language or computer language, without the prior written permission of 
 * Asymmetrix Solutions Private Limited.
 * 
 * Asymmetrix Solutions Private Limited
 * 115, Bldg 2, Sector 3, Millennium Business Park,
 * Navi Mumbai, India, 410701
 */
$(function () {

	
	
    var grid = $("#masterintgrid"), URL = 'rarocConfig/grid/listCNFGInternalRating';
	 
	var
    params1 = {
        name: 'id',
        index: 'id',
        hidden: true
    }, params2 = {
        name: 'col1',
        index: 'V_INT_RATING',
        label: 'INT Rating',
        width: '30'
    }, params3 = {
        name: 'col2',
        index: 'V_FORMAT_RATING',
        label: 'Format Rating',
        width: '30'
    }, params4 = {
        name: 'col3',
        index: 'V_MAPPED_RATING',
        label: 'Mapped Rating',
        width: '85'
    }, params5 = {
        name: 'col4',
        index: 'V_GRADE',
        label: 'Grade',
        width: '45'
    }, params6 = {
        name: 'col5',
        index: 'V_RATING_TYPE',
        label: 'Rating Type',
        width: '35'
    },params7 = {
        name: 'col6',
        index: 'N_INT_RATING_LIMIT',
        label: 'INT Rating Limit',
        width: '35'
    },params8 = {
        name: 'col7',
        index: 'N_PD',
        label: 'PD',
        width: '35'
    },params9 = {
        name: 'col8',
        index: 'N_LGD',
        label: 'LGD',
        width: '35'
    },params10 = {
        name: 'col9',
        index: 'N_PD_NPA',
        label: 'PD NPA',
        width: '35'
    },params11 = {
		name: 'col10',
		index: 'N_LGD_NPA',
		label: 'LGD NPA',
		width: '35'
    },params12 = {
        name: 'col11',
        index: 'N_EL',
        label: 'EL',
        width: '35'
    },params13 = {
        name: 'col12',
        index: 'V_MODEL',
        label: 'Model',
        width: '35'
    },params14 = {
        name: 'col13',
        index: 'N_RANK',
        label: 'Rank',
        width: '35'
    },params15 = {
        name: 'col14',
        index: 'V_FORMATED_RATING_LCMC_RD',
        label: 'Rating LCMC RD',
        width: '35'
    },params16 = {
    	name: 'col15',
        index: 'V_FORMATED_RATING_SME_RD',
        label: 'Rating SME RD',
        width: '35'
    },params17 = {
        name: 'col16',
	    index: 'V_FORMATED_RATING_RD',
	    label: 'Rating RD',
	    width: '35'
	},params18 = {
        name: 'col17',
        index: 'V_FORMATED_RATING_OSMOS',
        label: 'Rating OSMOS',
        width: '35'
    },params19 = {
        name: 'col18',
        index: 'N_TENURE',
        label: 'Tenure',
        width: '35'
    },params20 = {
        name: 'col19',
        index: 'F_ELIGIBLE_GUARANTOR',
        label: 'Eligible Guarantor',
        width: '35'
    },params21 = {
        name: 'col20',
        index: 'V_GUARANTOR_TYPE',
        label: 'Guarantor Type',
        width: '35'
    },params22 = {
        name: 'col21',
        index: 'V_MAPPED_EXT_RATING',
        label: 'External Rating',
        width: '35'
    },options = {
        url: URL,
        colModel: [params1, params2, params3, params4, params5, params6, params7, params8, params9, params10, params11, params12, params13, params14, params15, params16, params17, params18, params19, params20, params21, params22],
        caption: 'Internal Rating',
        pager: '#masterintpager',
        rowNum: 13,
        rowList: [13, 30, 45],
        datatype: 'json',
        emptyrecords: 'Nothing to view',
        loadComplete: function () {
            if (grid.jqGrid("getGridParam", 'reccount') > 15) {
                grid.jqGrid("setGridHeight", 350);
            } else {
                grid.jqGrid("setGridHeight", 'auto');
            }
            setGridWidth(grid, "#masterintjqgrid");
        },
        onHeaderClick: function () {
            setGridWidth(grid, "#masterintjqgrid");
        },
        beforeProcessing: function (data, status, xhr) {
            $("#_tk").val(xhr.getResponseHeader("_tk"));
        },
        sortorder: ''
    };

    grid.jqGrid(options).navGrid("#masterintpager", {
        add: false,
        edit: false,
        del: false
    }, {}, {}, {}, {
        sopt: ['eq', 'ne', 'cn', 'nc', 'bw', 'ew', 'nu', 'nn']
    }).navButtonAdd('#masterintpager', {
        caption: "Template",
        buttonicon: "fa fa-file-excel-o",
        onClickButton: function () {
                  window.location.href = "rarocConfig/listCNFGInternalRating/doc";
            
        }
    });
    grid.focus();
    grid.jqGrid("bindKeys", {scrollingRows: true});
    grid.jqGrid(options).setSelection("1");
});