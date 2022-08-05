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

public class XLSCNFGInternalRating extends AbstractExcelXView {
	
    @Override
    protected void buildExcelDocument(Map<String, Object> model, XSSFWorkbook wb,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        CellStyle bStyle;
        PoiStyles pois = new PoiStyles();
        bStyle = pois.boldStyle(wb);

        response.setHeader("Content-Disposition", "attachment; filename=\"InternalRating.xlsx\"");
        Sheet sheet = wb.createSheet("DATA");
        org.apache.poi.ss.usermodel.Row hrow = sheet.createRow(0);
        org.apache.poi.ss.usermodel.Row data;
        org.apache.poi.ss.usermodel.Cell hcell, dcell;

      
        hcell = hrow.createCell(0);
        hcell.setCellValue("V_INT_RATING");
        hcell.setCellStyle(bStyle);

        hcell = hrow.createCell(1);
        hcell.setCellValue("V_FORMAT_RATING");
        hcell.setCellStyle(bStyle);

        hcell = hrow.createCell(2);
        hcell.setCellValue("V_MAPPED_RATING");
        hcell.setCellStyle(bStyle);

        hcell = hrow.createCell(3);
        hcell.setCellValue("V_GRADE");
        hcell.setCellStyle(bStyle);

        hcell = hrow.createCell(4);
        hcell.setCellValue("V_RATING_TYPE");
        hcell.setCellStyle(bStyle);
        
        hcell = hrow.createCell(5);
        hcell.setCellValue("N_INT_RATING_LIMIT");
        hcell.setCellStyle(bStyle);
        
        hcell = hrow.createCell(6);
        hcell.setCellValue("N_PD");
        hcell.setCellStyle(bStyle);
        
        hcell = hrow.createCell(7);
        hcell.setCellValue("N_LGD");
        hcell.setCellStyle(bStyle);
        
        hcell = hrow.createCell(8);
        hcell.setCellValue("N_PD_NPA");
        hcell.setCellStyle(bStyle);
        
        hcell = hrow.createCell(9);
        hcell.setCellValue("N_LGD_NPA");
        hcell.setCellStyle(bStyle);
        
        hcell = hrow.createCell(10);
        hcell.setCellValue("N_EL");
        hcell.setCellStyle(bStyle);
        
        hcell = hrow.createCell(11);
        hcell.setCellValue("V_MODEL");
        hcell.setCellStyle(bStyle);
        
        hcell = hrow.createCell(12);
        hcell.setCellValue("N_RANK");
        hcell.setCellStyle(bStyle);
        
        hcell = hrow.createCell(13);
        hcell.setCellValue("V_FORMATED_RATING_LCMC_RD");
        hcell.setCellStyle(bStyle);
        
        hcell = hrow.createCell(14);
        hcell.setCellValue("V_FORMATED_RATING_SME_RD");
        hcell.setCellStyle(bStyle);
        
        hcell = hrow.createCell(15);
        hcell.setCellValue("V_FORMATED_RATING_RD");
        hcell.setCellStyle(bStyle);
        
        hcell = hrow.createCell(16);
        hcell.setCellValue("V_FORMATED_RATING_OSMOS");
        hcell.setCellStyle(bStyle);
        
        hcell = hrow.createCell(17);
        hcell.setCellValue("N_TENURE");
        hcell.setCellStyle(bStyle);
        
        hcell = hrow.createCell(18);
        hcell.setCellValue("F_ELIGIBLE_GUARANTOR");
        hcell.setCellStyle(bStyle);
        
        hcell = hrow.createCell(19);
        hcell.setCellValue("V_GUARANTOR_TYPE");
        hcell.setCellStyle(bStyle);
        
        hcell = hrow.createCell(20);
        hcell.setCellValue("V_MAPPED_EXT_RATING");
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
            
            dcell = data.createCell(5);
            dcell.setCellValue(object.getCol6());
            
            dcell = data.createCell(6);
            dcell.setCellValue(object.getCol7());
            
            dcell = data.createCell(7);
            dcell.setCellValue(object.getCol8());
            
            dcell = data.createCell(8);
            dcell.setCellValue(object.getCol9());
            
            dcell = data.createCell(9);
            dcell.setCellValue(object.getCol10());
            
            dcell = data.createCell(10);
            dcell.setCellValue(object.getCol11());
            
            dcell = data.createCell(11);
            dcell.setCellValue(object.getCol12());
            
            dcell = data.createCell(12);
            dcell.setCellValue(object.getCol13());
            
            dcell = data.createCell(13);
            dcell.setCellValue(object.getCol14());
            
            dcell = data.createCell(14);
            dcell.setCellValue(object.getCol15());
            
            dcell = data.createCell(15);
            dcell.setCellValue(object.getCol16());
            
            dcell = data.createCell(16);
            dcell.setCellValue(object.getCol17());
            
            dcell = data.createCell(17);
            dcell.setCellValue(object.getCol18());
            
            dcell = data.createCell(18);
            dcell.setCellValue(object.getCol19());
            
            dcell = data.createCell(19);
            dcell.setCellValue(object.getCol20());
            
            dcell = data.createCell(20);
            dcell.setCellValue(object.getCol21());
          
          
          
            rowno++;
        }


        sheet.autoSizeColumn(1);
        sheet.autoSizeColumn(2);
        sheet.autoSizeColumn(3);
        sheet.autoSizeColumn(4);
        sheet.autoSizeColumn(5);
        
    }
    

}
