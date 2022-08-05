
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>	
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="_csrf" content="${_csrf.token}"/>
        <meta name="_csrf_header" content="${_csrf.headerName}"/>
        <title><fmt:message key="headerTitle"/></title>

        <link rel="icon" type="image/x-icon" href="<c:url value="/resources/css/images/bct.ico"/>">
        <link rel="shortcut icon" type="image/x-icon" href="<c:url value="/resources/css/images/bct.ico"/>">
        <link rel="stylesheet" type="text/css" media="screen" href="./resources/jqGrid/css/trirand/ui.jqgrid-bootstrap.css" />
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/atrix-ui.css"/>">
        <style>

            .ui-jqdialog {

                display: none;

                width: 300px;

                position: absolute;

                padding: .2em;

                font-size: 11px;

                overflow: visible;

                left: 30% !important;

                top: 40% !important;

            }

        </style>
        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
            <script src="<c:url value="/resources/scripts/html5shiv.js"/>"></script>
            <script src="<c:url value="/resources/scripts/respond.min.js"/>"></script>
        <![endif]-->
        <!-- jQuery -->
        <script type="text/javascript" src="<c:url value="/resources/js/jquery.min.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/bootstrap-dialog.min.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/bootstrap-multiselect.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/metisMenu.js"/>"></script>
<!--        <script type="text/javascript" src="<c:url value="/resources/js/jquery.jqGrid.min.js"/>"></script>-->


        <script type="text/ecmascript" src="./resources/jqGrid/js/trirand/i18n/grid.locale-en.js"></script>
        <script type="text/ecmascript" src="./resources/jqGrid/js/trirand/jquery.jqGrid.min.js"></script>
        <script>
            $.jgrid.defaults.styleUI = 'Bootstrap';
        </script>
        <!--        <script type="text/ecmascript" src="./resources/jqGrid/js/ui.multiselect.js"></script>-->
        <script type="text/ecmascript" src="./resources/js/bootstrap-datepicker.js"></script>
        <script type="text/ecmascript" src="./resources/jqGrid/js/bootstrap3-typeahead.js"></script>
        <link rel="stylesheet" type="text/css" media="screen" href="./resources/jqGrid/css/ui.multiselect.css" />
        <link rel="stylesheet" type="text/css" media="screen" href="./resources/css/bootstrap-datepicker.css" />

        <script type="text/javascript" src="<c:url value="/resources/js/highcharts.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/jquery.validationEngine.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/jquery.validationEngine-en.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/moment.min.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/bootstrap-datetimepicker.min.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/jquery.bootstrap-touchspin.min.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/jquery.form.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/highslide-full.min.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/scripts/config/jqgridConfig.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/scripts/config/appSec.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/scripts/template.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/scripts/mconfig/highslideConfig.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/scripts/grid/master/ccf.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/scripts/grid/master/ExternalRating.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/scripts/grid/master/guarantor.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/scripts/grid/master/InternalRating.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/scripts/grid/master/OperatingExpenses.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/scripts/grid/master/othIncome.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/scripts/grid/master/RarocConfig.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/scripts/grid/master/restructured.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/scripts/grid/master/sensitivityIteration.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/scripts/grid/master/AssetType.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/scripts/grid/master/BusinessUnit.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/scripts/grid/master/FinHaircut.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/scripts/grid/master/RatingModelMapping.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/scripts/grid/master/InternalRatingModel.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/scripts/grid/master/listcnfgmaster.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/scripts/grid/master/cnfgccf.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/scripts/grid/master/cnfgExternalRating.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/scripts/grid/master/cnfgguarantor.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/scripts/grid/master/cnfgInternalRating.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/scripts/grid/master/cnfgOperatingExpenses.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/scripts/grid/master/cnfgothIncome.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/scripts/grid/master/cnfgrestructured.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/scripts/grid/master/cnfgsensitivityIteration.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/scripts/grid/master/cnfgAssetType.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/scripts/grid/master/cnfgBusinessUnit.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/scripts/grid/master/cnfgFinHaircut.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/scripts/grid/master/cnfgRatingModelMapping.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/scripts/grid/master/cnfgInternalRatingModel.js"/>"></script>
        
        
        

    </head>
    <body>
        <c:import url="/WEB-INF/views/layout/navbar.jsp" />
        <div id="wrapper">
            <div id="page-wrapper" class="page-wrapper">
                <div class="container-fluid" id="container-fluid">
                    <div class="row">
                        <div class="col-lg-12 marginTop">
                            <div class="panel panel-breadcrumb">
                                <div class="panel-body">
                                    <a href="<c:url value='/rarocMasterApprover'/>"><em>Master View </em></a>
                                </div>                                
                            </div>
                        </div>
                        <div class="col-lg-12">
                         <div class="col-lg-4 col-md-4">
                        <select id="masterTables">
							  <option value="jqgrid">RAROC MASTER</option>
							  <option value="intjqgrid">INTERNAL RATING</option>
							  <option value="extjqgrid">EXTERNAL RATING </option>
							  <option value="guajqgrid">GUARANTOR</option>
							  <option value="imjqgrid">INCOME MASTER</option>
							  <option value="ccfjqgrid">CCF MASTER</option>
							  <option value="oejqgrid">OPERATING EXPENSE</option>
							  <option value="rmjqgrid">RESTRUCTURED MASTER</option>
							  <option value="smjqgrid">SENSITIVITY MASTER</option>
							  <option value="atjqgrid">ASSET TYPE</option>
							  <option value="fhjqgrid">FIN HAIRCUT</option>
							  <option value="rmmjqgrid">RATING MODEL MAPPING</option>
							  <option value="irmjqgrid">INTERNAL RATING MAPPING</option>
							  <option value="bujqgrid">BUSINESS UNIT</option>
						</select>
						</div>
						   
		 <div class="col-lg-4 col-md-4">				   				                
    <form method="post"  action="rarocMasterApprover/jqgrid" id="approve"  htmlEscape="true">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <table class="formTable">
            <tr>
                <td>
                    <input class="btn btn-default" type="submit" id="approveButton" value="Approve"/>
                </td>                
            </tr>           
        </table>
		</form>
		</div>
		 <div class="col-lg-4 col-md-4">				                
    <form method="post"  action="rarocMasterApprover/reject" id="reject" htmlEscape="true">
         <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <table class="formTable">
            <tr>
               <td>
               <input class="btn btn-default" type="hidden" id="tablename" name="tablename" value="TEMP_MST_RAROC"/>
                <input class="btn btn-default" type="submit" id="rejectButton" value="Reject"/>
                </td>                
            </tr>           
        </table>
		</form>      			
         </div>           
				<br/>
				<br/>
            		</div>
					
<br/><br/>
		<div class="col-lg-6">				
						
                            <div id="jqgrid" class="spacing" > 
                                <table id="grid"></table>
                                <div id="pager"></div>                    
                            </div>
                             <div id="intjqgrid" class="spacing" style="display: none;"> 
                                <table id="intgrid"></table>
                                <div id="intpager"></div>                    
                            </div>
                             <div id="extjqgrid" class="spacing" style="display: none;"> 
                                <table id="extgrid"></table>
                                <div id="extpager"></div>                    
                            </div>
                             <div id="guajqgrid" class="spacing" style="display: none;"> 
                                <table id="guagrid"></table>
                                <div id="guapager"></div>                    
                            </div>
                             <div id="imjqgrid" class="spacing" style="display: none;"> 
                                <table id="imgrid"></table>
                                <div id="impager"></div>                    
                            </div>
                             <div id="ccfjqgrid" class="spacing" style="display: none;"> 
                                <table id="ccfgrid"></table>
                                <div id="ccfpager"></div>                    
                            </div>
                             <div id="oejqgrid" class="spacing" style="display: none;"> 
                                <table id="oegrid"></table>
                                <div id="oepager"></div>                    
                            </div>
                             <div id="rmjqgrid" class="spacing" style="display: none;"> 
                                <table id="rmgrid"></table>
                                <div id="rmpager"></div>                    
                            </div>
                             <div id="smjqgrid" class="spacing" style="display: none;"> 
                                <table id="smgrid"></table>
                                <div id="smpager"></div>                    
                            </div>
                             <div id="atjqgrid" class="spacing" style="display: none;"> 
                                <table id="atgrid"></table>
                                <div id="atpager"></div>                    
                            </div>
                             <div id="fhjqgrid" class="spacing" style="display: none;"> 
                                <table id="fhgrid"></table>
                                <div id="fhpager"></div>                    
                            </div>
                             <div id="rmmjqgrid" class="spacing" style="display: none;"> 
                                <table id="rmmgrid"></table>
                                <div id="rmmpager"></div>                    
                            </div>
                             <div id="irmjqgrid" class="spacing" style="display: none;"> 
                                <table id="irmgrid"></table>
                                <div id="irmpager"></div>                    
                            </div>
                             <div id="bujqgrid" class="spacing" style="display: none;"> 
                                <table id="bugrid"></table>
                                <div id="bupager"></div>                    
                            </div>
                            <br />
                        </div>
             
		<div class="col-lg-6">				
						
                            <div id="masterjqgrid" class="spacing" > 
                                <table id="mastergrid"></table>
                                <div id="masterpager"></div>                    
                            </div>
                             <div id="masterintjqgrid" class="spacing" style="display: none;"> 
                                <table id="masterintgrid"></table>
                                <div id="masterintpager"></div>                    
                            </div>
                             <div id="masterextjqgrid" class="spacing" style="display: none;"> 
                                <table id="masterextgrid"></table>
                                <div id="masterextpager"></div>                    
                            </div>
                             <div id="masterguajqgrid" class="spacing" style="display: none;"> 
                                <table id="masterguagrid"></table>
                                <div id="masterguapager"></div>                    
                            </div>
                             <div id="masterimjqgrid" class="spacing" style="display: none;"> 
                                <table id="masterimgrid"></table>
                                <div id="masterimpager"></div>                    
                            </div>
                             <div id="masterccfjqgrid" class="spacing" style="display: none;"> 
                                <table id="masterccfgrid"></table>
                                <div id="masterccfpager"></div>                    
                            </div>
                             <div id="masteroejqgrid" class="spacing" style="display: none;"> 
                                <table id="masteroegrid"></table>
                                <div id="masteroepager"></div>                    
                            </div>
                             <div id="masterrmjqgrid" class="spacing" style="display: none;"> 
                                <table id="masterrmgrid"></table>
                                <div id="masterrmpager"></div>                    
                            </div>
                             <div id="mastersmjqgrid" class="spacing" style="display: none;"> 
                                <table id="mastersmgrid"></table>
                                <div id="mastersmpager"></div>                    
                            </div>
                             <div id="masteratjqgrid" class="spacing" style="display: none;"> 
                                <table id="masteratgrid"></table>
                                <div id="masteratpager"></div>                    
                            </div>
                             <div id="masterfhjqgrid" class="spacing" style="display: none;"> 
                                <table id="masterfhgrid"></table>
                                <div id="masterfhpager"></div>                    
                            </div>
                             <div id="masterrmmjqgrid" class="spacing" style="display: none;"> 
                                <table id="masterrmmgrid"></table>
                                <div id="masterrmmpager"></div>                    
                            </div>
                             <div id="masterirmjqgrid" class="spacing" style="display: none;"> 
                                <table id="masterirmgrid"></table>
                                <div id="masterirmpager"></div>                    
                            </div>
                             <div id="masterbujqgrid" class="spacing" style="display: none;"> 
                                <table id="masterbugrid"></table>
                                <div id="masterbupager"></div>                    
                            </div>
                            <br />
                        </div>
					                        
                    </div>

                </div>
            </div>
            <div class="modal fade" id="displayMsgModal" role="dialog">
	<div class="modal-dialog">
		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-body">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
        		<p></p>
      		</div>
		</div>
	</div>
</div>
            <!-- /#page-wrapper -->
            <c:import url="/WEB-INF/views/layout/footer.jsp" />
        </div>
    </body>
    <script type="text/javascript">
    $(document).ready(function(){
      var divs = ["jqgrid", "intjqgrid", "extjqgrid", "guajqgrid","imjqgrid","ccfjqgrid","oejqgrid","rmjqgrid","smjqgrid","atjqgrid","fhjqgrid","rmmjqgrid","irmjqgrid","bujqgrid"];
      var masterDivs=["masterjqgrid", "masterintjqgrid", "masterextjqgrid", "masterguajqgrid","masterimjqgrid","masterccfjqgrid","masteroejqgrid","masterrmjqgrid","mastersmjqgrid","masteratjqgrid","masterfhjqgrid","masterrmmjqgrid","masterirmjqgrid","masterbujqgrid"];
      var tableNames=["TEMP_MST_RAROC","TEMP_mast_internal_rating","TEMP_mast_external_rating","TEMP_MST_GUARANTOR","TEMP_MST_OTH_INCOME","TEMP_MST_CCF","TEMP_MST_OPERATING_EXPENSE","TEMP_MST_RESTRUCTURED_RW","TEMP_MST_SENSITIVITY_ITERATIONS","TEMP_MST_ASSET_TYPE","TEMP_mst_fin_haircut","TEMP_cnfg_rating_model_map","TEMP_cnfg_int_rat_mapping","TEMP_MST_BUSINESS_UNIT"];
      var visibleId = null;
      var mastervisibleId=null;
      $('#masterTables').change(function() {
        	 show($(this).val());
        	
        	 
        	 var pos = divs.indexOf($(this).val() );
        	 var tableName= tableNames[pos]; 
        	 $('#tablename').val(tableName);
        	 var actionUrl="rarocMasterApprover/reject/";
        	 var approveUrl="rarocMasterApprover/"+$(this).val()
        	   	 $('#approve').attr('action',approveUrl );
              
        });
        function show(id) {
           if(visibleId !== id) {
            visibleId = id;
            mastervisibleId='master'+visibleId
            
            } 
          hide();
        }
        function hide() {
	          var div, i, id,masterid;
	          for(i = 0; i < divs.length; i++) {
		            id = divs[i];
		            div = document.getElementById(id);
		            
		            if(visibleId === id) {
		              div.style.display = "block";
		            
		            } else {
		              div.style.display = "none";
		              
		            }
		            
		            
	          }
	          
	          for(j = 0; j < masterDivs.length; j++) {
		           masterid = masterDivs[j];
		          
		            div = document.getElementById(masterid);
		            
		            if('master'+visibleId === masterid) {
		              div.style.display = "block";
		            
		            } else {
		              div.style.display = "none";
		              
		            }
		            
		            
	          }
	          
	       }  
    	
     	
        
    });
    
   
    </script>
</html>