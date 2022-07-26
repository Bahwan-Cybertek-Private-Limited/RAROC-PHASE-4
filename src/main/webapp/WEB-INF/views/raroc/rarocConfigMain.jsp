
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
                                    <a href="<c:url value='/rarocConfig'/>"><em>Master View </em></a>
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
						         			
            			<div class="spacing" id="uploadFile">
                 
				                
    <form method="post"  action="rarocConfig/jqgrid" id="reportUpload"  enctype="multipart/form-data" htmlEscape="true">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <table class="formTable">
            <tr>
                <td class="forlabel"><input type="file" name="file"/></td>
                <td>
                    <input class="btn btn-default" type="submit" id="submitButton" value="<fmt:message key="label.upload"/>"/>
                </td>                
            </tr>           
        </table>
		</form>
				</div>
				<br/>
            		</div>
					
<br/><br/>
		<div class="col-lg-12">				
						
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
      var visibleId = null;
      $('#masterTables').change(function() {
        	 show($(this).val());
        	 var actionUrl="rarocConfig/"+$(this).val();
                	 $('#reportUpload').attr('action',actionUrl );
        	 
        });
        function show(id) {
           if(visibleId !== id) {
            visibleId = id;
            } 
          hide();
        }
        function hide() {
	          var div, i, id;
	          for(i = 0; i < divs.length; i++) {
		            id = divs[i];
		            div = document.getElementById(id);
		            if(visibleId === id) {
		              div.style.display = "block";
		            } else {
		              div.style.display = "none";
		            }
	          }
	    }  
    	
    	
    	
    	
        
    });
    
   
    </script>
</html>