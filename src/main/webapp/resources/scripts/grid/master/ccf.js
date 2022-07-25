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

    var grid = $("#ccfgrid"), URL = 'rarocConfig/grid/listCCFMaster';

	 var token = $("meta[name='_csrf']").attr("content");
	    var formOptions = {    	
	        headers: {
	            "X-CSRF-TOKEN" : token
	        },
	        complete: function() {
	        	$('#spinnerModal').modal('hide'); 
	        	BootstrapDialog.show({
	                title: 'success',
	                message: 'success'
	            });
	            $('#reportUpload').trigger("reset");
	            $("#ccfgrid").trigger("reloadGrid");           
	        },
	        error: function() {
	            BootstrapDialog.show({
	                title: 'Error',
	                message: 'Unable to upload file'
	            });
	        } 
	    }; 
	   
	
	
	   $("#reportUpload").ajaxForm(formOptions);   
	   $("#reject").ajaxForm(formOptions);   
	   $("#approve").ajaxForm(formOptions);
	   
	    $("#submitButton").click(function(){
	    	$('#spinnerModal').modal({backdrop: 'static', keyboard: false}); 
	    	$('#spinnerModal').modal('show');
	    	$("#loadingIcon").html("<div class='loading'>  <i class='fa fa-spinner fa-spin fa-2x fa-fw'></i> <div>Loading...</div>   </div>");    	
	    });
	    
	   
	
    var viewComment = function (rowid) {

        var ref = grid.getCell(rowid, 'rarocref');
        var path = "raroc/view/comments?ref=" + ref;
        return hs.htmlExpand(this, {
            src: path,
            numberPosition: 'none',
            objectType: 'ajax',
            width: 800,
            height: 400,
            headingText: 'Approver comments for ' + ref
        });
    };


    $("#create").click(function () {
        $.get("raroc/new", function (html) {
            $("#container-fluid").html("<div class='loading'>\n\
                                    <i class='fa fa-refresh fa-spin fa-5x fa-fw'></i>\n\
                                    <div>Loading...</div>\n\
                                </div>");
            $("#container-fluid").html(html);
        });
    });

    var delOptions = {
        onclickSubmit: function (params, postdata) {
            params.url = URL + '/del?recref=' + grid.jqGrid('getCell', postdata, 'rarocref');
        },
        errorTextFormat: function (r) {
            return "Only Incomplete records can be deleted";
        }
    },
    params1 = {
        name: 'id',
        index: 'id',
        hidden: true
    }, params2 = {
        name: 'col1',
        index: 'V_CODE',
        label: 'Code',
        width: '30'
    }, params3 = {
        name: 'col2',
        index: 'V_FACILITY_TYPE',
        label: 'Facility Type',
        width: '30'
    }, params4 = {
        name: 'col3',
        index: 'N_LIMIT_LOWER',
        label: 'Lower Limit',
        width: '85'
    }, params5 = {
        name: 'col4',
        index: 'N_LIMIT_UPPER',
        label: 'Upper Limit',
        width: '45'
    }, params6 = {
        name: 'col5',
        index: 'N_UTILIZATION_LOWER',
        label: 'Lower Utilization',
        width: '35'
    },params7 = {
        name: 'col6',
        index: 'N_UTILIZATION_UPPER',
        label: 'Fixed Cost',
        width: '35'
    },params8 = {
        name: 'col7',
        index: 'N_DRAWN_CCF',
        label: 'Drawn CCF',
        width: '35'
     },params9 = {
        name: 'col8',
        index: 'N_UNDRAWN_CCF',
        label: 'Undrawn CCF',
        width: '35'
    },params10 = {
        name: 'col9',
        index: 'V_STATUS',
        label: 'Status',
        width: '35'
    },options = {
        url: URL,
        colModel: [params1, params2, params3, params4, params5, params6, params7, params8, params9, params10],
        caption: 'CCF Master',
        pager: '#ccfpager',
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
            setGridWidth(grid, "#ccfjqgrid");
        },
        onHeaderClick: function () {
            setGridWidth(grid, "#ccfjqgrid");
        },
        beforeProcessing: function (data, status, xhr) {
            $("#_tk").val(xhr.getResponseHeader("_tk"));
        },
        sortorder: ''
    };

    grid.jqGrid(options).navGrid("#ccfpager", {
        add: false,
        edit: false,
        del: false
    }, {}, {}, {}, {
        sopt: ['eq', 'ne', 'cn', 'nc', 'bw', 'ew', 'nu', 'nn']
    }).navButtonAdd('#ccfpager', {
        caption: "Template",
        buttonicon: "fa fa-file-excel-o",
        onClickButton: function () {
                  window.location.href = "rarocConfig/listCCFMaster/doc";
            
        }
    });
    grid.focus();
    grid.jqGrid("bindKeys", {scrollingRows: true});
    grid.jqGrid(options).setSelection("1");
});