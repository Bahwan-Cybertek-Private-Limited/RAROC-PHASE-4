<%-- 
    Document   : rarocAdminMain
    Created on : Aug 21, 2014, 12:04:42 PM
    Author     : vinoy
--%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
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
        <script type="text/javascript" src="<c:url value="/resources/scripts/config/highslideConfig.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/scripts/grid/raroc/rarocAuth.js"/>"></script>
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
                                    <a href="<c:url value='/raroc'/>"><em>RAROC</em></a>
                                </div>                                
                            </div>
                        </div>
                        <div class="col-lg-12">
                            <div id="jqgrid" class="spacing"> 
                                <table id="grid"></table>
                                <div id="pager"></div>                    
                            </div>
                        </div>
                    </div>

                </div>
            </div>
           
        </div>
                    <c:import url="/WEB-INF/views/layout/footer.jsp" />
        
    </body>
</html>
