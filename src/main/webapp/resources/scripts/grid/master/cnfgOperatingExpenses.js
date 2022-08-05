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

    var grid = $("#masteroegrid"), URL = 'rarocConfig/grid/listCNFGOperatingExpenseMaster';


    var 
    params1 = {
        name: 'id',
        index: 'id',
        hidden: true
    }, params2 = {
        name: 'col1',
        index: 'V_FACILITY',
        label: 'Facility',
        width: '30'
    }, params3 = {
        name: 'col2',
        index: 'V_SEGMENT',
        label: 'Segment',
        width: '30'
    }, params4 = {
        name: 'col3',
        index: 'N_LOWER_LEVEL',
        label: 'Lower Level',
        width: '85'
    }, params5 = {
        name: 'col4',
        index: 'N_UPPER_LEVEL',
        label: 'Upper Level',
        width: '45'
    }, params6 = {
        name: 'col5',
        index: 'V_ID',
        label: 'Id',
        width: '35'
    },params7 = {
        name: 'col6',
        index: 'N_FIXED_COST',
        label: 'Fixed Cost',
        width: '35'
    },params8 = {
        name: 'col7',
        index: 'N_VARIABLE_COST',
        label: 'Variable Cost',
        width: '35'
     },params9 = {
        name: 'col8',
        index: 'V_REGION',
        label: 'Region',
        width: '35'
    },options = {
        url: URL,
        colModel: [params1, params2, params3, params4, params5, params6, params7, params8, params9],
        caption: 'Operating Expenses',
        pager: '#masteroepager',
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
            setGridWidth(grid, "#masteroejqgrid");
        },
        onHeaderClick: function () {
            setGridWidth(grid, "#masteroejqgrid");
        },
        beforeProcessing: function (data, status, xhr) {
            $("#_tk").val(xhr.getResponseHeader("_tk"));
        },
        sortorder: ''
    };

    grid.jqGrid(options).navGrid("#masteroepager", {
        add: false,
        edit: false,
        del: false
    }, {}, {}, {}, {
        sopt: ['eq', 'ne', 'cn', 'nc', 'bw', 'ew', 'nu', 'nn']
    }).navButtonAdd('#masteroepager', {
        caption: "Template",
        buttonicon: "fa fa-file-excel-o",
        onClickButton: function () {
                  window.location.href = "rarocConfig/listCNFGOperatingExpenseMaster/doc";
            
        }
    });
    grid.focus();
    grid.jqGrid("bindKeys", {scrollingRows: true});
    grid.jqGrid(options).setSelection("1");
});