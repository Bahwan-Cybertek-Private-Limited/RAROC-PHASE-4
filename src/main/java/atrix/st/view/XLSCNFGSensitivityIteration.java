package atrix.st.view;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import atrix.common.util.AbstractExcelXView;
import atrix.common.util.PoiStyles;
import atrix.st.model.RarocConfigModel;

public class XLSCNFGSensitivityIteration extends AbstractExcelXView {
	
    @Override
    protected void buildExcelDocument(Map<String, Object> model, XSSFWorkbook wb,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        CellStyle bStyle;
        PoiStyles pois = new PoiStyles();
        bStyle = pois.boldStyle(wb);

        response.setHeader("Content-Disposition", "attachment; filename=\"SensitivityIteration.xlsx\"");
        Sheet sheet = wb.createSheet("DATA");
        org.apache.poi.ss.usermodel.Row hrow = sheet.createRow(0);
        org.apache.poi.ss.usermodel.Row data;
        org.apache.poi.ss.usermodel.Cell hcell, dcell;

      
        hcell = hrow.createCell(0);
        hcell.setCellValue("N_VALUE");
        hcell.setCellStyle(bStyle);

        hcell = hrow.createCell(1);
        hcell.setCellValue("V_TYPE");
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

           
          
            rowno++;
        }


        sheet.autoSizeColumn(1);
        sheet.autoSizeColumn(2);
        sheet.autoSizeColumn(3);
        sheet.autoSizeColumn(4);
        sheet.autoSizeColumn(5);
        
    }
    
}