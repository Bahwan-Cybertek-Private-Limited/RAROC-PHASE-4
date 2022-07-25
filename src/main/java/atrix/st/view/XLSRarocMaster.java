package atrix.st.view;

import atrix.common.service.FormatterService;
import atrix.common.util.AbstractExcelXView;
import atrix.common.util.PoiStyles;
import atrix.st.model.RarocConfigModel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;

public class XLSRarocMaster extends AbstractExcelXView {
		
	    @Override
	    protected void buildExcelDocument(Map<String, Object> model, XSSFWorkbook wb,
	            HttpServletRequest request, HttpServletResponse response)
	            throws Exception {

	        CellStyle bStyle;
	        PoiStyles pois = new PoiStyles();
	        bStyle = pois.boldStyle(wb);

	        response.setHeader("Content-Disposition", "attachment; filename=\"RAROCMaster.xlsx\"");
	        Sheet sheet = wb.createSheet("DATA");
	        org.apache.poi.ss.usermodel.Row hrow = sheet.createRow(0);
	        org.apache.poi.ss.usermodel.Row data;
	        org.apache.poi.ss.usermodel.Cell hcell, dcell;

	      
	        hcell = hrow.createCell(0);
	        hcell.setCellValue("V_CODE");
	        hcell.setCellStyle(bStyle);

	        hcell = hrow.createCell(1);
	        hcell.setCellValue("D_MIS_DATE");
	        hcell.setCellStyle(bStyle);

	        hcell = hrow.createCell(2);
	        hcell.setCellValue("N_CODE");
	        hcell.setCellStyle(bStyle);

	        hcell = hrow.createCell(3);
	        hcell.setCellValue("N_VALUE");
	        hcell.setCellStyle(bStyle);

	        hcell = hrow.createCell(4);
	        hcell.setCellValue("V_STATUS");
	        hcell.setCellStyle(bStyle);

	        	        
	        ArrayList list = (ArrayList) model.get("list");
	        Iterator itr1 = list.iterator();
	        Integer rowno = 1;
	        String signal;        
	        while (itr1.hasNext()) {
	        	RarocConfigModel object = (RarocConfigModel) itr1.next();
	            data = sheet.createRow(rowno);

	            dcell = data.createCell(0);
	            dcell.setCellValue(object.getCol1());
	                        
	            
	            dcell = data.createCell(1);
	            dcell.setCellValue((object.getCol2()));

	            dcell = data.createCell(2);
	            dcell.setCellValue((object.getCol3()));

	            dcell = data.createCell(3);
	            dcell.setCellValue((object.getCol4()));

	            dcell = data.createCell(4);
	            dcell.setCellValue(object.getCol5());
	          
	            rowno++;
	        }


	        sheet.autoSizeColumn(1);
	        sheet.autoSizeColumn(2);
	        sheet.autoSizeColumn(3);
	        sheet.autoSizeColumn(4);
	        sheet.autoSizeColumn(5);
	        
	    }
	    
	}