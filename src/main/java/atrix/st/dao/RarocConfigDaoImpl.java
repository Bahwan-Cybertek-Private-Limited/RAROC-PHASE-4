package atrix.st.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.sql.Types;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import atrix.common.model.QueryBuilderModel;
import atrix.common.service.FormatterService;
import atrix.common.service.QueryBuilderService;

import atrix.common.util.GridPage;
import atrix.st.exception.CustomException;
import atrix.st.model.RarocConfigModel;
@Repository("rarocConfigDao")
public class RarocConfigDaoImpl extends JdbcDaoSupport implements RarocConfigDao {

	 @Autowired
	 RarocConfigDaoImpl(DataSource dataSource) {
	        setDataSource(dataSource);
	    }
	@Autowired
	private FormatterService fmt;

	@Autowired
    private QueryBuilderService queryBuilder;

	private String SHEET = "DATA";
	
	@Override
    public GridPage<RarocConfigModel> listRarocConfig(int page, int max, String sidx, String sord, String searchField,
            String searchOper, String searchString, String userid) throws CustomException {
        List<String> columns = Collections.unmodifiableList(Arrays.asList("V_CODE", "D_MIS_DATE",
                "N_CODE", "N_VALUE", "V_STATUS"));

        List<String> orders = Collections.unmodifiableList(Arrays.asList("asc", "desc"));

        if (sidx == null || sidx.isEmpty()) {
            sidx = "V_STATUS";
        }
        if (sord == null || sord.isEmpty()) {
            sord = "desc";
        }

        //check if sidx is in columns 
        if (!columns.contains(sidx)) {
            throw new CustomException();
        }

        if (!orders.contains(sord)) {
            throw new CustomException();
        }

        QueryBuilderModel qObj = queryBuilder.SearchAnd(searchOper, searchField, searchString, columns);
        String query = "SELECT count(*) FROM TEMP_MST_RAROC WHERE v_status is not null " +qObj.getCondition()  ;
        int rowCount = getJdbcTemplate().queryForObject(query, new Object[]{qObj.getRegex()}, Integer.class);
        final int startIdx = ((page - 1) * max) + 1;
        final int endIdx = Math.min(startIdx + max, rowCount);
        query = " SELECT * FROM "
        		+"( SELECT a.*, rownum rnum FROM "
        		+ "(SELECT V_CODE,D_MIS_DATE,N_CODE,N_VALUE,V_STATUS FROM TEMP_MST_RAROC "
                + "WHERE v_status is not null "
                + qObj.getCondition() + " "
                + "ORDER BY " + sidx + " " + sord + ") a"
        + " WHERE rownum <= ?) WHERE rnum >= ?";
        List<RarocConfigModel> lists = new ArrayList<>();
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(query, new Object[]{ qObj.getRegex(),endIdx, startIdx});
        rows.stream().map((row) -> {
        	RarocConfigModel obj = new RarocConfigModel();
            obj.setId(fmt.ToString(row.get("rnum")));
            obj.setCol1(fmt.ToString(row.get("V_CODE")));
            obj.setCol2(fmt.ToString(row.get("D_MIS_DATE")));
            obj.setCol3(fmt.ToString(row.get("N_CODE")));
            obj.setCol4(fmt.ToString(row.get("N_VALUE")));
            obj.setCol5(fmt.ToString(row.get("V_STATUS")));
            return obj;
        }).forEach((obj) -> {
            lists.add(obj);
        });
        return new GridPage<>(lists, page, max, rowCount);
    }

	@Override
    public GridPage<RarocConfigModel> listRarocCNFGMaster(int page, int max, String sidx, String sord, String searchField,
            String searchOper, String searchString, String userid) throws CustomException {
        List<String> columns = Collections.unmodifiableList(Arrays.asList("V_CODE", "D_MIS_DATE",
                "N_CODE", "N_VALUE"));

        List<String> orders = Collections.unmodifiableList(Arrays.asList("asc", "desc"));

        if (sidx == null || sidx.isEmpty()) {
            sidx = "V_CODE";
        }
        if (sord == null || sord.isEmpty()) {
            sord = "desc";
        }

        //check if sidx is in columns 
        if (!columns.contains(sidx)) {
            throw new CustomException();
        }

        if (!orders.contains(sord)) {
            throw new CustomException();
        }

        QueryBuilderModel qObj = queryBuilder.SearchAnd(searchOper, searchField, searchString, columns);
        String query = "SELECT count(*) FROM MST_RAROC WHERE V_CODE is not null " +qObj.getCondition()  ;
        int rowCount = getJdbcTemplate().queryForObject(query, new Object[]{qObj.getRegex()}, Integer.class);
        final int startIdx = ((page - 1) * max) + 1;
        final int endIdx = Math.min(startIdx + max, rowCount);
        query = " SELECT * FROM "
        		+"( SELECT a.*, rownum rnum FROM "
        		+ "(SELECT V_CODE,D_MIS_DATE,N_CODE,N_VALUE FROM MST_RAROC "
                + "WHERE V_CODE is not null "
                + qObj.getCondition() + " "
                + "ORDER BY " + sidx + " " + sord + ") a"
        + " WHERE rownum <= ?) WHERE rnum >= ?";
        List<RarocConfigModel> lists = new ArrayList<>();
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(query, new Object[]{ qObj.getRegex(),endIdx, startIdx});
        rows.stream().map((row) -> {
        	RarocConfigModel obj = new RarocConfigModel();
            obj.setId(fmt.ToString(row.get("rnum")));
            obj.setCol1(fmt.ToString(row.get("V_CODE")));
            obj.setCol2(fmt.ToString(row.get("D_MIS_DATE")));
            obj.setCol3(fmt.ToString(row.get("N_CODE")));
            obj.setCol4(fmt.ToString(row.get("N_VALUE")));
            return obj;
        }).forEach((obj) -> {
            lists.add(obj);
        });
        return new GridPage<>(lists, page, max, rowCount);
    }


	@Override
	public GridPage<RarocConfigModel> listInternalRating(int page, int max, String sidx, String sord,
			String searchField, String searchOper, String searchString, String userid)
			throws CustomException {
		 List<String> columns = Collections.unmodifiableList(Arrays.asList("V_INT_RATING", "V_FORMAT_RATING","V_MAPPED_RATING", "V_GRADE", "V_RATING_TYPE","N_INT_RATING_LIMIT","N_PD","N_LGD","N_PD_NPA","N_LGD_NPA","N_EL","V_MODEL","N_RANK ","V_FORMATED_RATING_LCMC_RD","V_FORMATED_RATING_SME_RD","V_FORMATED_RATING_RD","V_FORMATED_RATING_OSMOS","N_TENURE","F_ELIGIBLE_GUARANTOR","V_GUARANTOR_TYPE","V_MAPPED_EXT_RATING","V_STATUS"));

	        List<String> orders = Collections.unmodifiableList(Arrays.asList("asc", "desc"));

	        if (sidx == null || sidx.isEmpty()) {
	            sidx = "V_STATUS";
	        }
	        if (sord == null || sord.isEmpty()) {
	            sord = "desc";
	        }

	        //check if sidx is in columns 
	        if (!columns.contains(sidx)) {
	            throw new CustomException();
	        }

	        if (!orders.contains(sord)) {
	            throw new CustomException();
	        }

	        QueryBuilderModel qObj = queryBuilder.SearchAnd(searchOper, searchField, searchString, columns);
	        String query = "SELECT count(*) FROM TEMP_mast_internal_rating WHERE v_status is not null " +qObj.getCondition()  ;
	        int rowCount = getJdbcTemplate().queryForObject(query, new Object[]{qObj.getRegex()}, Integer.class);
	        final int startIdx = ((page - 1) * max) + 1;
	        final int endIdx = Math.min(startIdx + max, rowCount);
	        query = " SELECT * FROM "
	        		+"( SELECT a.*, rownum rnum FROM "
	        		+"(SELECT V_INT_RATING, V_FORMAT_RATING,V_MAPPED_RATING, V_GRADE, V_RATING_TYPE,N_INT_RATING_LIMIT,N_PD,N_LGD,N_PD_NPA,N_LGD_NPA,N_EL,V_MODEL,N_RANK ,V_FORMATED_RATING_LCMC_RD,V_FORMATED_RATING_SME_RD,V_FORMATED_RATING_RD,V_FORMATED_RATING_OSMOS,N_TENURE,F_ELIGIBLE_GUARANTOR,V_GUARANTOR_TYPE,V_MAPPED_EXT_RATING,V_STATUS FROM TEMP_mast_internal_rating "
	                + "WHERE v_status is not null "
	                + qObj.getCondition() + " "
	                + "ORDER BY " + sidx + " " + sord + ")a"
	         + " WHERE rownum <= ?) WHERE rnum >= ?";
        List<RarocConfigModel> lists = new ArrayList<>();
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(query, new Object[]{ qObj.getRegex(),endIdx, startIdx});
	        rows.stream().map((row) -> {
	        	RarocConfigModel obj = new RarocConfigModel();
	            obj.setId(fmt.ToString(row.get("rnum")));
	        	obj.setCol1(fmt.ToString(row.get("V_INT_RATING")));
	        	obj.setCol2(fmt.ToString(row.get("V_FORMAT_RATING")));
	        	obj.setCol3(fmt.ToString(row.get("V_MAPPED_RATING")));
	        	obj.setCol4(fmt.ToString(row.get("V_GRADE")));
	        	obj.setCol5(fmt.ToString(row.get("V_RATING_TYPE")));
	        	obj.setCol6(fmt.ToString(row.get("N_INT_RATING_LIMIT")));
	        	obj.setCol7(fmt.ToString(row.get("N_PD")));
	        	obj.setCol8(fmt.ToString(row.get("N_LGD")));
	        	obj.setCol9(fmt.ToString(row.get("N_PD_NPA")));
	        	obj.setCol10(fmt.ToString(row.get("N_LGD_NPA")));
	        	obj.setCol11(fmt.ToString(row.get("N_EL")));
	        	obj.setCol12(fmt.ToString(row.get("V_MODEL")));
	        	obj.setCol13(fmt.ToString(row.get("N_RANK")));
	        	obj.setCol14(fmt.ToString(row.get("V_FORMATED_RATING_LCMC_RD")));
	        	obj.setCol15(fmt.ToString(row.get("V_FORMATED_RATING_SME_RD")));
	        	obj.setCol16(fmt.ToString(row.get("V_FORMATED_RATING_RD")));
	        	obj.setCol17(fmt.ToString(row.get("V_FORMATED_RATING_OSMOS")));
	        	obj.setCol18(fmt.ToString(row.get("N_TENURE")));
	        	obj.setCol19(fmt.ToString(row.get("F_ELIGIBLE_GUARANTOR")));
	        	obj.setCol20(fmt.ToString(row.get("V_GUARANTOR_TYPE")));
	        	obj.setCol21(fmt.ToString(row.get("V_MAPPED_EXT_RATING")));
	        	obj.setCol22(fmt.ToString(row.get("V_STATUS")));

	            return obj;
	        }).forEach((obj) -> {
	            lists.add(obj);
	        });
	        return new GridPage<>(lists, page, max, rowCount);
	}


	@Override
	public GridPage<RarocConfigModel> listExternalRating(int page, int max, String sidx, String sord,
			String searchField, String searchOper, String searchString, String userid)
			throws CustomException {
		 List<String> columns = Collections.unmodifiableList(Arrays.asList("V_RATING_CODE","V_GRADE","V_RANK","F_TERM_FLAG","N_RISK_WEIGHT","N_W","V_GUARANTOR_TYPE","V_STATUS"));

	        List<String> orders = Collections.unmodifiableList(Arrays.asList("asc", "desc"));

	        if (sidx == null || sidx.isEmpty()) {
	            sidx = "V_STATUS";
	        }
	        if (sord == null || sord.isEmpty()) {
	            sord = "desc";
	        }

	        //check if sidx is in columns 
	        if (!columns.contains(sidx)) {
	            throw new CustomException();
	        }

	        if (!orders.contains(sord)) {
	            throw new CustomException();
	        }

	        QueryBuilderModel qObj = queryBuilder.SearchAnd(searchOper, searchField, searchString, columns);
	        String query = "SELECT count(*) FROM TEMP_mast_external_rating WHERE v_status is not null " +qObj.getCondition()  ;
	        int rowCount = getJdbcTemplate().queryForObject(query, new Object[]{qObj.getRegex()}, Integer.class);
	        final int startIdx = ((page - 1) * max) + 1;
	        final int endIdx = Math.min(startIdx + max, rowCount);
	        query = " SELECT * FROM "
	        		+"( SELECT a.*, rownum rnum FROM "
	        		+"(SELECT V_RATING_CODE,V_GRADE,V_RANK,F_TERM_FLAG,N_RISK_WEIGHT,N_W,V_GUARANTOR_TYPE,V_STATUS FROM TEMP_mast_external_rating "
	                + "WHERE v_status is not null "
	                + qObj.getCondition() + " "
	                + "ORDER BY " + sidx + " " + sord + ")a"
	         + " WHERE rownum <= ?) WHERE rnum >= ?";
        List<RarocConfigModel> lists = new ArrayList<>();
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(query, new Object[]{ qObj.getRegex(),endIdx, startIdx});
	        rows.stream().map((row) -> {
	        	RarocConfigModel obj = new RarocConfigModel();
	            obj.setId(fmt.ToString(row.get("rnum")));
	        	obj.setCol1(fmt.ToString(row.get("V_RATING_CODE")));
	        	obj.setCol2(fmt.ToString(row.get("V_GRADE")));
	        	obj.setCol3(fmt.ToString(row.get("V_RANK")));
	        	obj.setCol4(fmt.ToString(row.get("F_TERM_FLAG")));
	        	obj.setCol5(fmt.ToString(row.get("N_RISK_WEIGHT")));
	        	obj.setCol6(fmt.ToString(row.get("N_W")));
	        	obj.setCol7(fmt.ToString(row.get("V_GUARANTOR_TYPE")));
	        	obj.setCol8(fmt.ToString(row.get("V_STATUS")));
	            return obj;
	        }).forEach((obj) -> {
	            lists.add(obj);
	        });
	        return new GridPage<>(lists, page, max, rowCount);
	}

	@Override
	public GridPage<RarocConfigModel> listGuarantorMaster(int page, int max, String sidx, String sord,
			String searchField, String searchOper, String searchString, String userid)
			throws CustomException {
		 List<String> columns = Collections.unmodifiableList(Arrays.asList("V_CODE", "N_VALUE",
	                "V_MAPPING_COL","V_STATUS"));

	        List<String> orders = Collections.unmodifiableList(Arrays.asList("asc", "desc"));

	        if (sidx == null || sidx.isEmpty()) {
	            sidx = "V_STATUS";
	        }
	        if (sord == null || sord.isEmpty()) {
	            sord = "desc";
	        }

	        //check if sidx is in columns 
	        if (!columns.contains(sidx)) {
	            throw new CustomException();
	        }

	        if (!orders.contains(sord)) {
	            throw new CustomException();
	        }

	        QueryBuilderModel qObj = queryBuilder.SearchAnd(searchOper, searchField, searchString, columns);
	        String query = "SELECT count(*) FROM TEMP_MST_GUARANTOR WHERE v_status is not null " +qObj.getCondition()  ;
	        int rowCount = getJdbcTemplate().queryForObject(query, new Object[]{qObj.getRegex()}, Integer.class);
	        final int startIdx = ((page - 1) * max) + 1;
	        final int endIdx = Math.min(startIdx + max, rowCount);
	        query = " SELECT * FROM "
	        		+"( SELECT a.*, rownum rnum FROM "
	        		+"(SELECT V_CODE,N_VALUE,V_MAPPING_COL,V_STATUS FROM TEMP_MST_GUARANTOR "
	                + "WHERE v_status is not null "
	                + qObj.getCondition() + " "
	                + "ORDER BY " + sidx + " " + sord + ")a"
	         + " WHERE rownum <= ?) WHERE rnum >= ?";
        List<RarocConfigModel> lists = new ArrayList<>();
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(query, new Object[]{ qObj.getRegex(),endIdx, startIdx});
	        rows.stream().map((row) -> {
	        	RarocConfigModel obj = new RarocConfigModel();
	            obj.setId(fmt.ToString(row.get("rnum")));
	        	obj.setCol1(fmt.ToString(row.get("V_CODE")));
	        	obj.setCol2(fmt.ToString(row.get("N_VALUE")));
	        	obj.setCol3(fmt.ToString(row.get("V_MAPPING_COL")));
	        	obj.setCol4(fmt.ToString(row.get("V_STATUS")));
	            return obj;
	        }).forEach((obj) -> {
	            lists.add(obj);
	        });
	        return new GridPage<>(lists, page, max, rowCount);
	}


	@Override
	public GridPage<RarocConfigModel> listOthIncomeMaster(int page, int max, String sidx, String sord,
			String searchField, String searchOper, String searchString, String userid)
			throws CustomException {
		 List<String> columns = Collections.unmodifiableList(Arrays.asList("OTH_INCOME_TYPE","CI_RATIO",
	                "TNFR_RATE", "V_STATUS"));

	        List<String> orders = Collections.unmodifiableList(Arrays.asList("asc", "desc"));

	        if (sidx == null || sidx.isEmpty()) {
	            sidx = "V_STATUS";
	        }
	        if (sord == null || sord.isEmpty()) {
	            sord = "desc";
	        }

	        //check if sidx is in columns 
	        if (!columns.contains(sidx)) {
	            throw new CustomException();
	        }

	        if (!orders.contains(sord)) {
	            throw new CustomException();
	        }

	        QueryBuilderModel qObj = queryBuilder.SearchAnd(searchOper, searchField, searchString, columns);
	        String query = "SELECT count(*) FROM TEMP_MST_OTH_INCOME WHERE v_status is not null " +qObj.getCondition()  ;
	        int rowCount = getJdbcTemplate().queryForObject(query, new Object[]{qObj.getRegex()}, Integer.class);
	        final int startIdx = ((page - 1) * max) + 1;
	        final int endIdx = Math.min(startIdx + max, rowCount);
	        query = " SELECT * FROM "
	        		+"( SELECT a.*, rownum rnum FROM "
	        		+"(SELECT OTH_INCOME_TYPE,CI_RATIO,TNFR_RATE,V_STATUS FROM TEMP_MST_OTH_INCOME "
	                + "WHERE v_status is not null "
	                + qObj.getCondition() + " "
	                + "ORDER BY " + sidx + " " + sord + ")a"
	         + " WHERE rownum <= ?) WHERE rnum >= ?";
        List<RarocConfigModel> lists = new ArrayList<>();
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(query, new Object[]{ qObj.getRegex(),endIdx, startIdx});
	        rows.stream().map((row) -> {
	        	RarocConfigModel obj = new RarocConfigModel();
	            obj.setId(fmt.ToString(row.get("rnum")));
	            obj.setCol1(fmt.ToString(row.get("OTH_INCOME_TYPE")));
	        	obj.setCol2(fmt.ToString(row.get("CI_RATIO")));
	        	obj.setCol3(fmt.ToString(row.get("TNFR_RATE")));
	        	obj.setCol4(fmt.ToString(row.get("V_STATUS")));
	            return obj;
	        }).forEach((obj) -> {
	            lists.add(obj);
	        });
	        return new GridPage<>(lists, page, max, rowCount);
	}


	@Override
	public GridPage<RarocConfigModel> listCCFMaster(int page, int max, String sidx, String sord, String searchField,
			String searchOper, String searchString, String userid) throws CustomException {
		 List<String> columns = Collections.unmodifiableList(Arrays.asList("V_CODE","V_FACILITY_TYPE","N_LIMIT_LOWER","N_LIMIT_UPPER","N_UTILIZATION_LOWER","N_UTILIZATION_UPPER","N_DRAWN_CCF","N_UNDRAWN_CCF","V_STATUS"));

	        List<String> orders = Collections.unmodifiableList(Arrays.asList("asc", "desc"));

	        if (sidx == null || sidx.isEmpty()) {
	            sidx = "V_STATUS";
	        }
	        if (sord == null || sord.isEmpty()) {
	            sord = "desc";
	        }

	        //check if sidx is in columns 
	        if (!columns.contains(sidx)) {
	            throw new CustomException();
	        }

	        if (!orders.contains(sord)) {
	            throw new CustomException();
	        }

	        QueryBuilderModel qObj = queryBuilder.SearchAnd(searchOper, searchField, searchString, columns);
	        String query = "SELECT count(*) FROM TEMP_MST_CCF WHERE v_status is not null " +qObj.getCondition()  ;
	        int rowCount = getJdbcTemplate().queryForObject(query, new Object[]{qObj.getRegex()}, Integer.class);
	        final int startIdx = ((page - 1) * max) + 1;
	        final int endIdx = Math.min(startIdx + max, rowCount);
	        query = " SELECT * FROM "
	        		+"( SELECT a.*, rownum rnum FROM "
	           		+"(SELECT V_CODE,V_FACILITY_TYPE,N_LIMIT_LOWER,N_LIMIT_UPPER,N_UTILIZATION_LOWER,N_UTILIZATION_UPPER,N_DRAWN_CCF,N_UNDRAWN_CCF,V_STATUS FROM TEMP_MST_CCF "
	                + "WHERE v_status is not null "
	                + qObj.getCondition() + " "
	                + "ORDER BY " + sidx + " " + sord + ")a"
	         + " WHERE rownum <= ?) WHERE rnum >= ?";
        List<RarocConfigModel> lists = new ArrayList<>();
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(query, new Object[]{ qObj.getRegex(),endIdx, startIdx});
	        rows.stream().map((row) -> {
	        	RarocConfigModel obj = new RarocConfigModel();
	            obj.setId(fmt.ToString(row.get("rnum")));
	        	obj.setCol1(fmt.ToString(row.get("V_CODE")));
	        	obj.setCol2(fmt.ToString(row.get("V_FACILITY_TYPE")));
	        	obj.setCol3(fmt.ToString(row.get("N_LIMIT_LOWER")));
	        	obj.setCol4(fmt.ToString(row.get("N_LIMIT_UPPER")));
	        	obj.setCol5(fmt.ToString(row.get("N_UTILIZATION_LOWER")));
	        	obj.setCol6(fmt.ToString(row.get("N_UTILIZATION_UPPER")));
	        	obj.setCol7(fmt.ToString(row.get("N_DRAWN_CCF")));
	        	obj.setCol8(fmt.ToString(row.get("N_UNDRAWN_CCF")));
	        	obj.setCol9(fmt.ToString(row.get("V_STATUS")));

	            return obj;
	        }).forEach((obj) -> {
	            lists.add(obj);
	        });
	        return new GridPage<>(lists, page, max, rowCount);
	}


	@Override
	public GridPage<RarocConfigModel> listOperatingExpenseMaster(int page, int max, String sidx, String sord,
			String searchField, String searchOper, String searchString, String userid)
			throws CustomException {
		 List<String> columns = Collections.unmodifiableList(Arrays.asList("V_FACILITY","V_SEGMENT","N_LOWER_LEVEL","N_UPPER_LEVEL","V_ID","N_FIXED_COST","N_VARIABLE_COST","V_REGION","V_STATUS"));

	        List<String> orders = Collections.unmodifiableList(Arrays.asList("asc", "desc"));

	        if (sidx == null || sidx.isEmpty()) {
	            sidx = "V_STATUS";
	        }
	        if (sord == null || sord.isEmpty()) {
	            sord = "desc";
	        }

	        //check if sidx is in columns 
	        if (!columns.contains(sidx)) {
	            throw new CustomException();
	        }

	        if (!orders.contains(sord)) {
	            throw new CustomException();
	        }

	        QueryBuilderModel qObj = queryBuilder.SearchAnd(searchOper, searchField, searchString, columns);
	        String query = "SELECT count(*) FROM TEMP_MST_OPERATING_EXPENSE WHERE v_status is not null " +qObj.getCondition()  ;
	        int rowCount = getJdbcTemplate().queryForObject(query, new Object[]{qObj.getRegex()}, Integer.class);
	        final int startIdx = ((page - 1) * max) + 1;
	        final int endIdx = Math.min(startIdx + max, rowCount);
	        query = " SELECT * FROM "
	        		+"( SELECT a.*, rownum rnum FROM "
	           		+"(SELECT V_FACILITY,V_SEGMENT,N_LOWER_LEVEL,N_UPPER_LEVEL,V_ID,N_FIXED_COST,N_VARIABLE_COST,V_REGION,V_STATUS FROM TEMP_MST_OPERATING_EXPENSE "
	                + "WHERE v_status is not null "
	                + qObj.getCondition() + " "
	                + "ORDER BY " + sidx + " " + sord + ")a"
	         + " WHERE rownum <= ?) WHERE rnum >= ?";
        List<RarocConfigModel> lists = new ArrayList<>();
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(query, new Object[]{ qObj.getRegex(),endIdx, startIdx});
	        rows.stream().map((row) -> {
	        	RarocConfigModel obj = new RarocConfigModel();
	            obj.setId(fmt.ToString(row.get("rnum")));
	        	obj.setCol1(fmt.ToString(row.get("V_FACILITY")));
	        	obj.setCol2(fmt.ToString(row.get("V_SEGMENT")));
	        	obj.setCol3(fmt.ToString(row.get("N_LOWER_LEVEL")));
	        	obj.setCol4(fmt.ToString(row.get("N_UPPER_LEVEL")));
	        	obj.setCol5(fmt.ToString(row.get("V_ID")));
	        	obj.setCol6(fmt.ToString(row.get("N_FIXED_COST")));
	        	obj.setCol7(fmt.ToString(row.get("N_VARIABLE_COST")));
	        	obj.setCol8(fmt.ToString(row.get("V_REGION")));
	        	obj.setCol9(fmt.ToString(row.get("V_STATUS")));
	            return obj;
	        }).forEach((obj) -> {
	            lists.add(obj);
	        });
	        return new GridPage<>(lists, page, max, rowCount);
	}


	@Override
	public GridPage<RarocConfigModel> listRestructuredMaster(int page, int max, String sidx, String sord,
			String searchField, String searchOper, String searchString, String userid)
			throws CustomException {
		 List<String> columns = Collections.unmodifiableList(Arrays.asList("V_CODE", "N_RW",
	                "N_EL", "V_STATUS"));

	        List<String> orders = Collections.unmodifiableList(Arrays.asList("asc", "desc"));

	        if (sidx == null || sidx.isEmpty()) {
	            sidx = "V_STATUS";
	        }
	        if (sord == null || sord.isEmpty()) {
	            sord = "desc";
	        }

	        //check if sidx is in columns 
	        if (!columns.contains(sidx)) {
	            throw new CustomException();
	        }

	        if (!orders.contains(sord)) {
	            throw new CustomException();
	        }

	        QueryBuilderModel qObj = queryBuilder.SearchAnd(searchOper, searchField, searchString, columns);
	        String query = "SELECT count(*) FROM TEMP_MST_RESTRUCTURED_RW WHERE v_status is not null " +qObj.getCondition()  ;
	        int rowCount = getJdbcTemplate().queryForObject(query, new Object[]{qObj.getRegex()}, Integer.class);
	        final int startIdx = ((page - 1) * max) + 1;
	        final int endIdx = Math.min(startIdx + max, rowCount);
	        query = " SELECT * FROM "
	        		+"( SELECT a.*, rownum rnum FROM "
	             	+"(SELECT V_CODE,N_RW,N_EL,V_STATUS FROM TEMP_MST_RESTRUCTURED_RW "
	                + "WHERE v_status is not null "
	                + qObj.getCondition() + " "
	                + "ORDER BY " + sidx + " " + sord + ")a"
	         + " WHERE rownum <= ?) WHERE rnum >= ?";
        List<RarocConfigModel> lists = new ArrayList<>();
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(query, new Object[]{ qObj.getRegex(),endIdx, startIdx});
	        rows.stream().map((row) -> {
	        	RarocConfigModel obj = new RarocConfigModel();
	            obj.setId(fmt.ToString(row.get("rnum")));
	            obj.setCol1(fmt.ToString(row.get("V_CODE")));
	            obj.setCol2(fmt.ToString(row.get("N_RW")));
	            obj.setCol3(fmt.ToString(row.get("N_EL")));
	            obj.setCol4(fmt.ToString(row.get("V_STATUS")));
	            
	            return obj;
	        }).forEach((obj) -> {
	            lists.add(obj);
	        });
	        return new GridPage<>(lists, page, max, rowCount);
	}


	@Override
	public GridPage<RarocConfigModel> listSensitivityIterationMaster(int page, int max, String sidx, String sord,
			String searchField, String searchOper, String searchString, String userid)
			throws CustomException {
		 List<String> columns = Collections.unmodifiableList(Arrays.asList("N_VALUE", "V_TYPE",
	                "V_STATUS"));

	        List<String> orders = Collections.unmodifiableList(Arrays.asList("asc", "desc"));

	        if (sidx == null || sidx.isEmpty()) {
	            sidx = "V_STATUS";
	        }
	        if (sord == null || sord.isEmpty()) {
	            sord = "desc";
	        }

	        //check if sidx is in columns 
	        if (!columns.contains(sidx)) {
	            throw new CustomException();
	        }

	        if (!orders.contains(sord)) {
	            throw new CustomException();
	        }

	        QueryBuilderModel qObj = queryBuilder.SearchAnd(searchOper, searchField, searchString, columns);
	        String query = "SELECT count(*) FROM TEMP_MST_SENSITIVITY_ITERATIONS WHERE v_status is not null " +qObj.getCondition()  ;
	        int rowCount = getJdbcTemplate().queryForObject(query, new Object[]{qObj.getRegex()}, Integer.class);
	        final int startIdx = ((page - 1) * max) + 1;
	        final int endIdx = Math.min(startIdx + max, rowCount);
	        query = " SELECT * FROM "
	        		+"( SELECT a.*, rownum rnum FROM "
	           		+"(SELECT N_VALUE,V_TYPE,V_STATUS FROM TEMP_MST_SENSITIVITY_ITERATIONS "
	                + "WHERE v_status is not null "
	                + qObj.getCondition() + " "
	                + "ORDER BY " + sidx + " " + sord + ")a"
	         + " WHERE rownum <= ?) WHERE rnum >= ?";
        List<RarocConfigModel> lists = new ArrayList<>();
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(query, new Object[]{ qObj.getRegex(),endIdx, startIdx});
	        rows.stream().map((row) -> {
	        	RarocConfigModel obj = new RarocConfigModel();
	            obj.setId(fmt.ToString(row.get("rnum")));
	            obj.setCol1(fmt.ToString(row.get("N_VALUE")));
	            obj.setCol2(fmt.ToString(row.get("V_TYPE")));
	            obj.setCol3(fmt.ToString(row.get("V_STATUS")));
	            ;
	            return obj;
	        }).forEach((obj) -> {
	            lists.add(obj);
	        });
	        return new GridPage<>(lists, page, max, rowCount);
	}


	@Override
	public List<RarocConfigModel> listRarocMasterDoc() {
		
		List<RarocConfigModel> list = new ArrayList<>();
	
  		String query = "SELECT rownum rnum,V_CODE,D_MIS_DATE,N_CODE,N_VALUE,V_STATUS FROM TEMP_MST_RAROC WHERE v_status is not null ";           
    
  		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(query);
  		rows.stream().map((row) -> {
  			RarocConfigModel obj = new RarocConfigModel();
  			obj.setId(fmt.ToString(row.get("rnum")));
            obj.setCol1(fmt.ToString(row.get("V_CODE")));
            obj.setCol2(fmt.ToString(row.get("D_MIS_DATE")));
            obj.setCol3(fmt.ToString(row.get("N_CODE")));
            obj.setCol4(fmt.ToString(row.get("N_VALUE")));
            obj.setCol5(fmt.ToString(row.get("V_STATUS")));
  			return obj;
  		}).forEach((obj) -> {
  			list.add(obj);
  		});

  		return list;

	}


	@Override
	public List<RarocConfigModel> listInternalRatingDoc() {
		List<RarocConfigModel> list = new ArrayList<>();
		
  		String query ="SELECT rownum rnum, V_INT_RATING, V_FORMAT_RATING,V_MAPPED_RATING, V_GRADE, V_RATING_TYPE,N_INT_RATING_LIMIT,N_PD,N_LGD,N_PD_NPA,N_LGD_NPA,N_EL,V_MODEL,N_RANK ,V_FORMATED_RATING_LCMC_RD,V_FORMATED_RATING_SME_RD,V_FORMATED_RATING_RD,V_FORMATED_RATING_OSMOS,N_TENURE,F_ELIGIBLE_GUARANTOR,V_GUARANTOR_TYPE,V_MAPPED_EXT_RATING,V_STATUS FROM TEMP_mast_internal_rating WHERE v_status is not null";
  		
  		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(query);
  		rows.stream().map((row) -> {
  			RarocConfigModel obj = new RarocConfigModel();
  			obj.setId(fmt.ToString(row.get("rnum")));
        	obj.setCol1(fmt.ToString(row.get("V_INT_RATING")));
        	obj.setCol2(fmt.ToString(row.get("V_FORMAT_RATING")));
        	obj.setCol3(fmt.ToString(row.get("V_MAPPED_RATING")));
        	obj.setCol4(fmt.ToString(row.get("V_GRADE")));
        	obj.setCol5(fmt.ToString(row.get("V_RATING_TYPE")));
        	obj.setCol6(fmt.ToString(row.get("N_INT_RATING_LIMIT")));
        	obj.setCol7(fmt.ToString(row.get("N_PD")));
        	obj.setCol8(fmt.ToString(row.get("N_LGD")));
        	obj.setCol9(fmt.ToString(row.get("N_PD_NPA")));
        	obj.setCol10(fmt.ToString(row.get("N_LGD_NPA")));
        	obj.setCol11(fmt.ToString(row.get("N_EL")));
        	obj.setCol12(fmt.ToString(row.get("V_MODEL")));
        	obj.setCol13(fmt.ToString(row.get("N_RANK")));
        	obj.setCol14(fmt.ToString(row.get("V_FORMATED_RATING_LCMC_RD")));
        	obj.setCol15(fmt.ToString(row.get("V_FORMATED_RATING_SME_RD")));
        	obj.setCol16(fmt.ToString(row.get("V_FORMATED_RATING_RD")));
        	obj.setCol17(fmt.ToString(row.get("V_FORMATED_RATING_OSMOS")));
        	obj.setCol18(fmt.ToString(row.get("N_TENURE")));
        	obj.setCol19(fmt.ToString(row.get("F_ELIGIBLE_GUARANTOR")));
        	obj.setCol20(fmt.ToString(row.get("V_GUARANTOR_TYPE")));
        	obj.setCol21(fmt.ToString(row.get("V_MAPPED_EXT_RATING")));
        	obj.setCol22(fmt.ToString(row.get("V_STATUS")));
  			return obj;
  		}).forEach((obj) -> {
  			list.add(obj);
  		});

  		return list;
	}


	@Override
	public List<RarocConfigModel> listExternalRatingDoc() {
		List<RarocConfigModel> list = new ArrayList<>();
		
  		String query = "SELECT rownum rnum,V_RATING_CODE,V_GRADE,V_RANK,F_TERM_FLAG,N_RISK_WEIGHT,N_W,V_GUARANTOR_TYPE,V_STATUS FROM TEMP_mast_external_rating WHERE v_status is not null"; 
  		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(query);
  		rows.stream().map((row) -> {
  			RarocConfigModel obj = new RarocConfigModel();
  			 obj.setId(fmt.ToString(row.get("rnum")));
	        	obj.setCol1(fmt.ToString(row.get("V_RATING_CODE")));
	        	obj.setCol2(fmt.ToString(row.get("V_GRADE")));
	        	obj.setCol3(fmt.ToString(row.get("V_RANK")));
	        	obj.setCol4(fmt.ToString(row.get("F_TERM_FLAG")));
	        	obj.setCol5(fmt.ToString(row.get("N_RISK_WEIGHT")));
	        	obj.setCol6(fmt.ToString(row.get("N_W")));
	        	obj.setCol7(fmt.ToString(row.get("V_GUARANTOR_TYPE")));
	        	obj.setCol8(fmt.ToString(row.get("V_STATUS")));
  			return obj;
  		}).forEach((obj) -> {
  			list.add(obj);
  		});

  		return list;
	}


	@Override
	public List<RarocConfigModel> listGuarantorMasterDoc() {
		List<RarocConfigModel> list = new ArrayList<>();
		
  		String query = "SELECT rownum rnum,V_CODE,N_VALUE,V_MAPPING_COL,V_STATUS FROM TEMP_MST_GUARANTOR WHERE v_status is not null";
  		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(query);
  		rows.stream().map((row) -> {
  			RarocConfigModel obj = new RarocConfigModel();
  		     obj.setId(fmt.ToString(row.get("rnum")));
  		     obj.setCol1(fmt.ToString(row.get("V_CODE")));
  		     obj.setCol2(fmt.ToString(row.get("N_VALUE")));
  		     obj.setCol3(fmt.ToString(row.get("V_MAPPING_COL")));
  		     obj.setCol4(fmt.ToString(row.get("V_STATUS")));
  			return obj;
  		}).forEach((obj) -> {
  			list.add(obj);
  		});

  		return list;
	}


	@Override
	public List<RarocConfigModel> listOthIncomeMasterDoc() {
		List<RarocConfigModel> list = new ArrayList<>();
		
  		String query = "SELECT rownum rnum,OTH_INCOME_TYPE,CI_RATIO,TNFR_RATE,V_STATUS FROM TEMP_MST_OTH_INCOME WHERE v_status is not null";
  		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(query);
  		rows.stream().map((row) -> {
  			RarocConfigModel obj = new RarocConfigModel();
  			obj.setId(fmt.ToString(row.get("rnum")));
        	obj.setCol1(fmt.ToString(row.get("OTH_INCOME_TYPE")));
        	obj.setCol2(fmt.ToString(row.get("CI_RATIO")));
        	obj.setCol3(fmt.ToString(row.get("TNFR_RATE")));
        	obj.setCol4(fmt.ToString(row.get("V_STATUS")));
  			return obj;
  		}).forEach((obj) -> {
  			list.add(obj);
  		});

  		return list;
	}


	@Override
	public List<RarocConfigModel> listCCFMasterDoc() {
		List<RarocConfigModel> list = new ArrayList<>();
		
  		String query = "SELECT rownum rnum,V_CODE,V_FACILITY_TYPE,N_LIMIT_LOWER,N_LIMIT_UPPER,N_UTILIZATION_LOWER,N_UTILIZATION_UPPER,N_DRAWN_CCF,N_UNDRAWN_CCF,V_STATUS FROM TEMP_MST_CCF WHERE v_status is not null"; 
  		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(query);
  		rows.stream().map((row) -> {
  			RarocConfigModel obj = new RarocConfigModel();
  		   obj.setId(fmt.ToString(row.get("rnum")));
	       	obj.setCol1(fmt.ToString(row.get("V_CODE")));
	       	obj.setCol2(fmt.ToString(row.get("V_FACILITY_TYPE")));
	       	obj.setCol3(fmt.ToString(row.get("N_LIMIT_LOWER")));
	       	obj.setCol4(fmt.ToString(row.get("N_LIMIT_UPPER")));
	       	obj.setCol5(fmt.ToString(row.get("N_UTILIZATION_LOWER")));
	       	obj.setCol6(fmt.ToString(row.get("N_UTILIZATION_UPPER")));
	       	obj.setCol7(fmt.ToString(row.get("N_DRAWN_CCF")));
	       	obj.setCol8(fmt.ToString(row.get("N_UNDRAWN_CCF")));
	       	obj.setCol9(fmt.ToString(row.get("V_STATUS")));
  			return obj;
  		}).forEach((obj) -> {
  			list.add(obj);
  		});

  		return list;
	}


	@Override
	public List<RarocConfigModel> listOperatingExpenseMasterDoc() {
		List<RarocConfigModel> list = new ArrayList<>();
		
  		String query = "SELECT rownum rnum,V_FACILITY,V_SEGMENT,N_LOWER_LEVEL,N_UPPER_LEVEL,V_ID,N_FIXED_COST,N_VARIABLE_COST,V_REGION,V_STATUS FROM TEMP_MST_OPERATING_EXPENSE WHERE v_status is not null";
  		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(query);
  		rows.stream().map((row) -> {
  			RarocConfigModel obj = new RarocConfigModel();
  			 obj.setId(fmt.ToString(row.get("rnum")));
	        	obj.setCol1(fmt.ToString(row.get("V_FACILITY")));
	        	obj.setCol2(fmt.ToString(row.get("V_SEGMENT")));
	        	obj.setCol3(fmt.ToString(row.get("N_LOWER_LEVEL")));
	        	obj.setCol4(fmt.ToString(row.get("N_UPPER_LEVEL")));
	        	obj.setCol5(fmt.ToString(row.get("V_ID")));
	        	obj.setCol6(fmt.ToString(row.get("N_FIXED_COST")));
	        	obj.setCol7(fmt.ToString(row.get("N_VARIABLE_COST")));
	        	obj.setCol8(fmt.ToString(row.get("V_REGION")));
	        	obj.setCol9(fmt.ToString(row.get("V_STATUS")));
  			return obj;
  		}).forEach((obj) -> {
  			list.add(obj);
  		});

  		return list;
	}


	@Override
	public List<RarocConfigModel> listRestructuredMasterDoc() {
		List<RarocConfigModel> list = new ArrayList<>();
		
  		String query = "SELECT rownum rnum,V_CODE,N_RW,N_EL,V_STATUS FROM TEMP_MST_RESTRUCTURED_RW WHERE v_status is not null";            
    
  		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(query);
  		rows.stream().map((row) -> {
  			RarocConfigModel obj = new RarocConfigModel();
  			 obj.setId(fmt.ToString(row.get("rnum")));
	            obj.setCol1(fmt.ToString(row.get("V_CODE")));
	            obj.setCol2(fmt.ToString(row.get("N_RW")));
	            obj.setCol3(fmt.ToString(row.get("N_EL")));
	            obj.setCol4(fmt.ToString(row.get("V_STATUS")));
	            
  			return obj;
  		}).forEach((obj) -> {
  			list.add(obj);
  		});

  		return list;
	}


	@Override
	public List<RarocConfigModel> listSensitivityIterationMasterDoc() {
		List<RarocConfigModel> list = new ArrayList<>();
		
  		String query = "SELECT rownum rnum,N_VALUE,V_TYPE,V_STATUS FROM TEMP_MST_SENSITIVITY_ITERATIONS WHERE v_status is not null";           
    
  		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(query);
  		rows.stream().map((row) -> {
  			RarocConfigModel obj = new RarocConfigModel();
  			 obj.setId(fmt.ToString(row.get("rnum")));
	            obj.setCol1(fmt.ToString(row.get("N_VALUE")));
	            obj.setCol2(fmt.ToString(row.get("V_TYPE")));
	            obj.setCol3(fmt.ToString(row.get("V_STATUS")));
  			return obj;
  		}).forEach((obj) -> {
  			list.add(obj);
  		});

  		return list;
	}


	@Override
	public List<RarocConfigModel>  rarocMasterReadxls(MultipartFile file) throws IOException {
		try {
		      Workbook workbook = new XSSFWorkbook(file.getInputStream());
		      Sheet sheet = workbook.getSheet(SHEET);
		      Iterator<Row> rows = sheet.iterator();
		      List<RarocConfigModel> rarocMasterdata = new ArrayList<RarocConfigModel>();
		      int rowNumber = 0;
		      while (rows.hasNext()) {
		        Row currentRow = rows.next();
		        // skip header
		        if (rowNumber == 0) {
		          rowNumber++;
		          continue;
		        }
		        Iterator<Cell> cellsInRow = currentRow.iterator();
		        RarocConfigModel rarocMaster = new RarocConfigModel();
		        int cellIdx = 0;
		        while (cellsInRow.hasNext()) {
		          Cell currentCell = cellsInRow.next();
		          switch (cellIdx) {
		          case 0:
		        	  rarocMaster.setCol1(currentCell.getStringCellValue());
		            break;
		          case 1:
		        	  rarocMaster.setCol2(currentCell.getStringCellValue());
		            break;
		          case 2:
		        	  rarocMaster.setCol3(currentCell.getStringCellValue());
		            break;
		          case 3:
		        	  rarocMaster.setCol4(currentCell.getStringCellValue());
		            break;
		          
		          default:
		            break;
		          }
		          cellIdx++;
		        }
		        rarocMasterdata.add(rarocMaster);
		      }
		      workbook.close();
		      return rarocMasterdata;
		    } catch (IOException e) {
		      throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
		    }
		  }
		
	


	public void saveRarocMasterFile(MultipartFile file) throws IOException {
		if (file != null) {
			
			List<RarocConfigModel> xlsData = rarocMasterReadxls(file);
			
			String query= "DELETE FROM TEMP_MST_RAROC";
			getJdbcTemplate().update(query);
			
			for(RarocConfigModel data:xlsData) {
				query="INSERT INTO TEMP_MST_RAROC (V_CODE, D_MIS_DATE, N_CODE, N_VALUE, V_STATUS) values(?,SYSDATE,?,?,'P')";
				getJdbcTemplate().update(query, new Object[]{data.getCol1(),data.getCol3(),data.getCol4()},new int[] {Types.VARCHAR,Types.NUMERIC,Types.DECIMAL });
				
			}
			
			
			
		}
	}


	@Override
	public List<RarocConfigModel> readInternalRatingxls(MultipartFile file) throws IOException {
		try {
		      Workbook workbook = new XSSFWorkbook(file.getInputStream());
		      Sheet sheet = workbook.getSheet(SHEET);
		      Iterator<Row> rows = sheet.iterator();
		      List<RarocConfigModel> rarocMasterdata = new ArrayList<RarocConfigModel>();
		      int rowNumber = 0;
		      while (rows.hasNext()) {
		        Row currentRow = rows.next();
		        // skip header
		        if (rowNumber == 0) {
		          rowNumber++;
		          continue;
		        }
		        Iterator<Cell> cellsInRow = currentRow.iterator();
		        RarocConfigModel rarocMaster = new RarocConfigModel();
		        int cellIdx = 0;
		        while (cellsInRow.hasNext()) {
		          Cell currentCell = cellsInRow.next();
		          switch (cellIdx) {
		          case 0:
		        	  rarocMaster.setCol1(currentCell.getStringCellValue());
		            break;
		          case 1:
		        	  rarocMaster.setCol2(currentCell.getStringCellValue());
		            break;
		          case 2:
		        	  rarocMaster.setCol3(currentCell.getStringCellValue());
		            break;
		          case 3:
		        	  rarocMaster.setCol4(currentCell.getStringCellValue());
		            break;
		          case 4:
		        	  rarocMaster.setCol5(currentCell.getStringCellValue());
		            break;
		          case 5:
		        	  if (currentCell.getStringCellValue() == null || currentCell.getCellType() == Cell.CELL_TYPE_BLANK||currentCell.getCellType() == Cell.CELL_TYPE_STRING && currentCell.getStringCellValue().trim().isEmpty()) {
	        		  	  rarocMaster.setCol6("0.0");
	        		 }else {
	        			  rarocMaster.setCol6(currentCell.getStringCellValue());	 
	        		}
		            break;
		          case 6:
		        	  if (currentCell.getStringCellValue() == null || currentCell.getCellType() == Cell.CELL_TYPE_BLANK||currentCell.getCellType() == Cell.CELL_TYPE_STRING && currentCell.getStringCellValue().trim().isEmpty()) {
	        		  	  rarocMaster.setCol7("0.0");
	        		 }else {
	        			  rarocMaster.setCol7(currentCell.getStringCellValue());	 
	        		}
		        	  break;
		          case 7:
		        	  if (currentCell.getStringCellValue() == null || currentCell.getCellType() == Cell.CELL_TYPE_BLANK||currentCell.getCellType() == Cell.CELL_TYPE_STRING && currentCell.getStringCellValue().trim().isEmpty()) {
	        		  	  rarocMaster.setCol8("0.0");
	        		 }else {
	        			  rarocMaster.setCol8(currentCell.getStringCellValue());	 
	        		}
		        	  break;
		          case 8:
		        	  if (currentCell.getStringCellValue() == null || currentCell.getCellType() == Cell.CELL_TYPE_BLANK||currentCell.getCellType() == Cell.CELL_TYPE_STRING && currentCell.getStringCellValue().trim().isEmpty()) {
	        		  	  rarocMaster.setCol9("0.0");
	        		 }else {
	        			  rarocMaster.setCol9(currentCell.getStringCellValue());	 
	        		}
		        	  break;
		          case 9:
		        	  if (currentCell.getStringCellValue() == null || currentCell.getCellType() == Cell.CELL_TYPE_BLANK||currentCell.getCellType() == Cell.CELL_TYPE_STRING && currentCell.getStringCellValue().trim().isEmpty()) {
	        		  	  rarocMaster.setCol10("0.0");
	        		 }else {
	        			  rarocMaster.setCol10(currentCell.getStringCellValue());	 
	        		}
		        	  break;
		          case 10:
		        	  if (currentCell.getStringCellValue() == null || currentCell.getCellType() == Cell.CELL_TYPE_BLANK||currentCell.getCellType() == Cell.CELL_TYPE_STRING && currentCell.getStringCellValue().trim().isEmpty()) {
	        		  	  rarocMaster.setCol11("0.0");
	        		 }else {
	        			  rarocMaster.setCol11(currentCell.getStringCellValue());	 
	        		}
		        	  break;
		          case 11:
		        	  rarocMaster.setCol12(currentCell.getStringCellValue());
		            break;
		          case 12:
		        	  if (currentCell.getStringCellValue() == null || currentCell.getCellType() == Cell.CELL_TYPE_BLANK||currentCell.getCellType() == Cell.CELL_TYPE_STRING && currentCell.getStringCellValue().trim().isEmpty()) {
	        		  	  rarocMaster.setCol13("0");
	        		 }else {
	        			  rarocMaster.setCol13(currentCell.getStringCellValue());	 
	        		}		
		        	  break;
		          case 13:
		        	  rarocMaster.setCol14(currentCell.getStringCellValue());
		            break;
		          case 14:
		        	  rarocMaster.setCol15(currentCell.getStringCellValue());
		            break;
		          case 15:
		        	  rarocMaster.setCol6(currentCell.getStringCellValue());
		            break;
		          case 16:
		        	  rarocMaster.setCol17(currentCell.getStringCellValue());
		            break;
		          case 17:
		        	  if (currentCell.getStringCellValue() == null || currentCell.getCellType() == Cell.CELL_TYPE_BLANK||currentCell.getCellType() == Cell.CELL_TYPE_STRING && currentCell.getStringCellValue().trim().isEmpty()) {
	        		  	  rarocMaster.setCol18("0");
	        		 }else {
	        			  rarocMaster.setCol18(currentCell.getStringCellValue());	 
	        		}		
		        	  
		            break;
		          case 18:
		        	  rarocMaster.setCol19(currentCell.getStringCellValue());
		            break;
		          case 19:
		        	  rarocMaster.setCol20(currentCell.getStringCellValue());
		            break;
		          case 20:
		        	  rarocMaster.setCol21(currentCell.getStringCellValue());
		            break;
		          
		          default:
		            break;
		          }
		          cellIdx++;
		        }
		        rarocMasterdata.add(rarocMaster);
		      }
		      workbook.close();
		      return rarocMasterdata;
		    } catch (IOException e) {
		      throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
		    }
	}


	@Override
	public void saveInternalRatingFile(MultipartFile file) throws IOException {
if (file != null) {
			
			List<RarocConfigModel> xlsData = readInternalRatingxls(file);
			
			String query= "DELETE FROM TEMP_mast_internal_rating";
			getJdbcTemplate().update(query);
			
			for(RarocConfigModel data:xlsData) {
				System.out.println(data.getCol1()+","+data.getCol2()+","+data.getCol3()+","+data.getCol4()+","+data.getCol5()+","+data.getCol6()+","+data.getCol7()+","+data.getCol8()+","+data.getCol9()+","+data.getCol10()+","+data.getCol11()+","+data.getCol12()+","+data.getCol13()+","+data.getCol14()+","+data.getCol15()+","+data.getCol16()+","+data.getCol17()+","+data.getCol18()+","+data.getCol19()+","+data.getCol20()+","+data.getCol21());
				query="INSERT INTO TEMP_mast_internal_rating (V_INT_RATING,V_FORMAT_RATING,V_MAPPED_RATING, V_GRADE, V_RATING_TYPE,N_INT_RATING_LIMIT,N_PD,N_LGD,N_PD_NPA,N_LGD_NPA,N_EL,V_MODEL,N_RANK ,V_FORMATED_RATING_LCMC_RD,V_FORMATED_RATING_SME_RD,V_FORMATED_RATING_RD,V_FORMATED_RATING_OSMOS,N_TENURE,F_ELIGIBLE_GUARANTOR,V_GUARANTOR_TYPE,V_MAPPED_EXT_RATING,V_STATUS) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,'P')";
				getJdbcTemplate().update(query, new Object[]{data.getCol1(),data.getCol2(),data.getCol3(),data.getCol4(),data.getCol5(),data.getCol6(),data.getCol7(),data.getCol8(),data.getCol9(),data.getCol10(),data.getCol11(),data.getCol12(),data.getCol13(),data.getCol14(),data.getCol15(),data.getCol16(),data.getCol17(),data.getCol18(),data.getCol19(),data.getCol20(),data.getCol21()},
						new int[] {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.CHAR,Types.VARCHAR ,Types.DECIMAL,Types.DECIMAL,Types.DECIMAL,Types.DECIMAL,Types.DECIMAL,
								   Types.DECIMAL,Types.VARCHAR,Types.NUMERIC,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.DECIMAL,Types.CHAR,Types.VARCHAR,
								   Types.VARCHAR});
				
			}
			
			
			
		}

		
	}


	@Override
	public GridPage<RarocConfigModel> listAssetType(int page, int max, String sidx, String sord, String searchField,
			String searchOper, String searchString, String userid) throws CustomException {
		 List<String> columns = Collections.unmodifiableList(Arrays.asList("V_CODE", "V_DESC",
	                "N_VALUE", "V_GUAR_FLAG", "V_STATUS"));

	        List<String> orders = Collections.unmodifiableList(Arrays.asList("asc", "desc"));

	        if (sidx == null || sidx.isEmpty()) {
	            sidx = "V_STATUS";
	        }
	        if (sord == null || sord.isEmpty()) {
	            sord = "desc";
	        }

	        //check if sidx is in columns 
	        if (!columns.contains(sidx)) {
	            throw new CustomException();
	        }

	        if (!orders.contains(sord)) {
	            throw new CustomException();
	        }

	        QueryBuilderModel qObj = queryBuilder.SearchAnd(searchOper, searchField, searchString, columns);
	        String query = "SELECT count(*) FROM TEMP_MST_ASSET_TYPE WHERE v_status is not null " +qObj.getCondition()  ;
	        int rowCount = getJdbcTemplate().queryForObject(query, new Object[]{qObj.getRegex()}, Integer.class);
	        final int startIdx = ((page - 1) * max) + 1;
	        final int endIdx = Math.min(startIdx + max, rowCount);
	        query = " SELECT * FROM "
	        		+"( SELECT a.*, rownum rnum FROM "
	        		+ "(SELECT V_CODE,V_DESC,N_VALUE,V_GUAR_FLAG,V_STATUS FROM TEMP_MST_ASSET_TYPE "
	                + "WHERE v_status is not null "
	                + qObj.getCondition() + " "
	                + "ORDER BY " + sidx + " " + sord + ") a"
	        + " WHERE rownum <= ?) WHERE rnum >= ?";
	        List<RarocConfigModel> lists = new ArrayList<>();
	        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(query, new Object[]{ qObj.getRegex(),endIdx, startIdx});
	        rows.stream().map((row) -> {
	        	RarocConfigModel obj = new RarocConfigModel();
	            obj.setId(fmt.ToString(row.get("rnum")));
	            obj.setCol1(fmt.ToString(row.get("V_CODE")));
	            obj.setCol2(fmt.ToString(row.get("V_DESC")));
	            obj.setCol3(fmt.ToString(row.get("N_VALUE")));
	            obj.setCol4(fmt.ToString(row.get("V_GUAR_FLAG")));
	            obj.setCol5(fmt.ToString(row.get("V_STATUS")));
	            return obj;
	        }).forEach((obj) -> {
	            lists.add(obj);
	        });
	        return new GridPage<>(lists, page, max, rowCount);
	}


	@Override
	public List<RarocConfigModel> listAssetTypeDoc() {
		List<RarocConfigModel> list = new ArrayList<>();
		
  		String query = "SELECT rownum rnum,V_CODE,V_DESC,N_VALUE,V_GUAR_FLAG,V_STATUS FROM TEMP_MST_ASSET_TYPE WHERE v_status is not null ";           
    
  		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(query);
  		rows.stream().map((row) -> {
  			RarocConfigModel obj = new RarocConfigModel();
  			 obj.setId(fmt.ToString(row.get("rnum")));
	            obj.setCol1(fmt.ToString(row.get("V_CODE")));
	            obj.setCol2(fmt.ToString(row.get("V_DESC")));
	            obj.setCol3(fmt.ToString(row.get("N_VALUE")));
	            obj.setCol4(fmt.ToString(row.get("V_GUAR_FLAG")));
	            obj.setCol5(fmt.ToString(row.get("V_STATUS")));
  			return obj;
  		}).forEach((obj) -> {
  			list.add(obj);
  		});

  		return list;
	}


	@Override
	public List<RarocConfigModel> assetTypeReadxls(MultipartFile file) throws IOException {
		try {
		      Workbook workbook = new XSSFWorkbook(file.getInputStream());
		      Sheet sheet = workbook.getSheet(SHEET);
		      Iterator<Row> rows = sheet.iterator();
		      List<RarocConfigModel> rarocMasterdata = new ArrayList<RarocConfigModel>();
		      int rowNumber = 0;
		      while (rows.hasNext()) {
		        Row currentRow = rows.next();
		        // skip header
		        if (rowNumber == 0) {
		          rowNumber++;
		          continue;
		        }
		        Iterator<Cell> cellsInRow = currentRow.iterator();
		        RarocConfigModel rarocMaster = new RarocConfigModel();
		        int cellIdx = 0;
		        while (cellsInRow.hasNext()) {
		          Cell currentCell = cellsInRow.next();
		          switch (cellIdx) {
		          case 0:
		        	  rarocMaster.setCol1(currentCell.getStringCellValue());
		            break;
		          case 1:
		        	  rarocMaster.setCol2(currentCell.getStringCellValue());
		            break;
		          case 2:
		        	  rarocMaster.setCol3(currentCell.getStringCellValue());
		            break;
		          case 3:
		        	  rarocMaster.setCol4(currentCell.getStringCellValue());
		            break;
		          
		          default:
		            break;
		          }
		          cellIdx++;
		        }
		        rarocMasterdata.add(rarocMaster);
		      }
		      workbook.close();
		      return rarocMasterdata;
		    } catch (IOException e) {
		      throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
		    }
	}


	@Override
	public void saveAssetTypeFile(MultipartFile file) throws IOException {
		if (file != null) {
			
			List<RarocConfigModel> xlsData = assetTypeReadxls(file);
			
			String query= "DELETE FROM TEMP_MST_ASSET_TYPE";
			getJdbcTemplate().update(query);
			
			for(RarocConfigModel data:xlsData) {
				query="INSERT INTO TEMP_MST_ASSET_TYPE (V_CODE,V_DESC,N_VALUE,V_GUAR_FLAG,V_STATUS) values(?,?,?,?,'P')";
				getJdbcTemplate().update(query, new Object[]{data.getCol1(),data.getCol2(),data.getCol3(),data.getCol4()},new int[] {Types.VARCHAR,Types.VARCHAR,Types.DECIMAL,Types.CHAR });
				
			}
			
			
			
		}
		
	}


	@Override
	public GridPage<RarocConfigModel> listBusinessUnit(int page, int max, String sidx, String sord, String searchField,
			String searchOper, String searchString, String userid) throws CustomException {
		 List<String> columns = Collections.unmodifiableList(Arrays.asList("V_CODE", "V_DESC",
	                "F_LATEST_IND","V_STATUS"));

	        List<String> orders = Collections.unmodifiableList(Arrays.asList("asc", "desc"));

	        if (sidx == null || sidx.isEmpty()) {
	            sidx = "V_STATUS";
	        }
	        if (sord == null || sord.isEmpty()) {
	            sord = "desc";
	        }

	        //check if sidx is in columns 
	        if (!columns.contains(sidx)) {
	            throw new CustomException();
	        }

	        if (!orders.contains(sord)) {
	            throw new CustomException();
	        }

	        QueryBuilderModel qObj = queryBuilder.SearchAnd(searchOper, searchField, searchString, columns);
	        String query = "SELECT count(*) FROM TEMP_MST_BUSINESS_UNIT WHERE v_status is not null " +qObj.getCondition()  ;
	        int rowCount = getJdbcTemplate().queryForObject(query, new Object[]{qObj.getRegex()}, Integer.class);
	        final int startIdx = ((page - 1) * max) + 1;
	        final int endIdx = Math.min(startIdx + max, rowCount);
	        query = " SELECT * FROM "
	        		+"( SELECT a.*, rownum rnum FROM "
	        		+ "(SELECT V_CODE,V_DESC,F_LATEST_IND,V_STATUS FROM TEMP_MST_BUSINESS_UNIT "
	                + "WHERE v_status is not null "
	                + qObj.getCondition() + " "
	                + "ORDER BY " + sidx + " " + sord + ") a"
	        + " WHERE rownum <= ?) WHERE rnum >= ?";
	        List<RarocConfigModel> lists = new ArrayList<>();
	        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(query, new Object[]{ qObj.getRegex(),endIdx, startIdx});
	        rows.stream().map((row) -> {
	        	RarocConfigModel obj = new RarocConfigModel();
	            obj.setId(fmt.ToString(row.get("rnum")));
	            obj.setCol1(fmt.ToString(row.get("V_CODE")));
	            obj.setCol2(fmt.ToString(row.get("V_DESC")));
	            obj.setCol3(fmt.ToString(row.get("F_LATEST_IND")));
	            obj.setCol4(fmt.ToString(row.get("V_STATUS")));
	            return obj;
	        }).forEach((obj) -> {
	            lists.add(obj);
	        });
	        return new GridPage<>(lists, page, max, rowCount);
	}


	@Override
	public List<RarocConfigModel> listBusinessUnitDoc() {
		List<RarocConfigModel> list = new ArrayList<>();
		String query = "SELECT rownum rnum,V_CODE,V_DESC,F_LATEST_IND,V_STATUS FROM TEMP_MST_BUSINESS_UNIT WHERE v_status is not null ";           
	    
  		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(query);
  		rows.stream().map((row) -> {
  			RarocConfigModel obj = new RarocConfigModel();
  			 obj.setId(fmt.ToString(row.get("rnum")));
  			obj.setCol1(fmt.ToString(row.get("V_CODE")));
            obj.setCol2(fmt.ToString(row.get("V_DESC")));
            obj.setCol3(fmt.ToString(row.get("F_LATEST_IND")));
            obj.setCol4(fmt.ToString(row.get("V_STATUS")));
  			return obj;
  		}).forEach((obj) -> {
  			list.add(obj);
  		});

  		return list;
	}


	@Override
	public List<RarocConfigModel> businessUnitReadxls(MultipartFile file) throws IOException {
		try {
		      Workbook workbook = new XSSFWorkbook(file.getInputStream());
		      Sheet sheet = workbook.getSheet(SHEET);
		      Iterator<Row> rows = sheet.iterator();
		      List<RarocConfigModel> rarocMasterdata = new ArrayList<RarocConfigModel>();
		      int rowNumber = 0;
		      while (rows.hasNext()) {
		        Row currentRow = rows.next();
		        // skip header
		        if (rowNumber == 0) {
		          rowNumber++;
		          continue;
		        }
		        Iterator<Cell> cellsInRow = currentRow.iterator();
		        RarocConfigModel rarocMaster = new RarocConfigModel();
		        int cellIdx = 0;
		        while (cellsInRow.hasNext()) {
		          Cell currentCell = cellsInRow.next();
		          switch (cellIdx) {
		          case 0:
		        	  rarocMaster.setCol1(currentCell.getStringCellValue());
		            break;
		          case 1:
		        	  rarocMaster.setCol2(currentCell.getStringCellValue());
		            break;
		          case 2:
		        	  rarocMaster.setCol3(currentCell.getStringCellValue());
		            break;
		          
		          default:
		            break;
		          }
		          cellIdx++;
		        }
		        rarocMasterdata.add(rarocMaster);
		      }
		      workbook.close();
		      return rarocMasterdata;
		    } catch (IOException e) {
		      throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
		    }
	}


	@Override
	public void saveBusinessUnitFile(MultipartFile file) throws IOException {
		if (file != null) {
			
			List<RarocConfigModel> xlsData = businessUnitReadxls(file);
			
			String query= "DELETE FROM TEMP_MST_BUSINESS_UNIT";
			getJdbcTemplate().update(query);
			
			for(RarocConfigModel data:xlsData) {
				query="INSERT INTO TEMP_MST_BUSINESS_UNIT (V_CODE,V_DESC,F_LATEST_IND,V_STATUS) values(?,?,?,'P')";
				getJdbcTemplate().update(query, new Object[]{data.getCol1(),data.getCol2(),data.getCol3()},new int[] {Types.VARCHAR,Types.VARCHAR,Types.CHAR });
				
			}
			
			
			
		}
		
	}


	@Override
	public GridPage<RarocConfigModel> listFinHaircut(int page, int max, String sidx, String sord, String searchField,
			String searchOper, String searchString, String userid) throws CustomException {
		 List<String> columns = Collections.unmodifiableList(Arrays.asList("V_FIN_SEC", "N_HAIRCUT",
	                "N_REG_HAIRCUT","V_STATUS"));

	        List<String> orders = Collections.unmodifiableList(Arrays.asList("asc", "desc"));

	        if (sidx == null || sidx.isEmpty()) {
	            sidx = "V_STATUS";
	        }
	        if (sord == null || sord.isEmpty()) {
	            sord = "desc";
	        }

	        //check if sidx is in columns 
	        if (!columns.contains(sidx)) {
	            throw new CustomException();
	        }

	        if (!orders.contains(sord)) {
	            throw new CustomException();
	        }

	        QueryBuilderModel qObj = queryBuilder.SearchAnd(searchOper, searchField, searchString, columns);
	        String query = "SELECT count(*) FROM TEMP_mst_fin_haircut WHERE v_status is not null " +qObj.getCondition()  ;
	        int rowCount = getJdbcTemplate().queryForObject(query, new Object[]{qObj.getRegex()}, Integer.class);
	        final int startIdx = ((page - 1) * max) + 1;
	        final int endIdx = Math.min(startIdx + max, rowCount);
	        query = " SELECT * FROM "
	        		+"( SELECT a.*, rownum rnum FROM "
	        		+ "(SELECT V_FIN_SEC,N_HAIRCUT,N_REG_HAIRCUT,V_STATUS FROM TEMP_mst_fin_haircut "
	                + "WHERE v_status is not null "
	                + qObj.getCondition() + " "
	                + "ORDER BY " + sidx + " " + sord + ") a"
	        + " WHERE rownum <= ?) WHERE rnum >= ?";
	        List<RarocConfigModel> lists = new ArrayList<>();
	        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(query, new Object[]{ qObj.getRegex(),endIdx, startIdx});
	        rows.stream().map((row) -> {
	        	RarocConfigModel obj = new RarocConfigModel();
	            obj.setId(fmt.ToString(row.get("rnum")));
	            obj.setCol1(fmt.ToString(row.get("V_FIN_SEC")));
	            obj.setCol2(fmt.ToString(row.get("N_HAIRCUT")));
	            obj.setCol3(fmt.ToString(row.get("N_REG_HAIRCUT")));
	            obj.setCol4(fmt.ToString(row.get("V_STATUS")));
	            return obj;
	        }).forEach((obj) -> {
	            lists.add(obj);
	        });
	        return new GridPage<>(lists, page, max, rowCount);
	}


	@Override
	public List<RarocConfigModel> listFinHaircutDoc() {
		List<RarocConfigModel> list = new ArrayList<>();
		String query = "SELECT rownum rnum,V_FIN_SEC,N_HAIRCUT,N_REG_HAIRCUT,V_STATUS FROM TEMP_mst_fin_haircut WHERE v_status is not null ";           
	    
  		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(query);
  		rows.stream().map((row) -> {
  			RarocConfigModel obj = new RarocConfigModel();
  			 obj.setId(fmt.ToString(row.get("rnum")));
  			obj.setCol1(fmt.ToString(row.get("V_FIN_SEC")));
            obj.setCol2(fmt.ToString(row.get("N_HAIRCUT")));
            obj.setCol3(fmt.ToString(row.get("N_REG_HAIRCUT")));
            obj.setCol4(fmt.ToString(row.get("V_STATUS")));
  			return obj;
  		}).forEach((obj) -> {
  			list.add(obj);
  		});

  		return list;
	}


	@Override
	public List<RarocConfigModel> finHaircutReadxls(MultipartFile file) throws IOException {
		try {
		      Workbook workbook = new XSSFWorkbook(file.getInputStream());
		      Sheet sheet = workbook.getSheet(SHEET);
		      Iterator<Row> rows = sheet.iterator();
		      List<RarocConfigModel> rarocMasterdata = new ArrayList<RarocConfigModel>();
		      int rowNumber = 0;
		      while (rows.hasNext()) {
		        Row currentRow = rows.next();
		        // skip header
		        if (rowNumber == 0) {
		          rowNumber++;
		          continue;
		        }
		        Iterator<Cell> cellsInRow = currentRow.iterator();
		        RarocConfigModel rarocMaster = new RarocConfigModel();
		        int cellIdx = 0;
		        while (cellsInRow.hasNext()) {
		          Cell currentCell = cellsInRow.next();
		          switch (cellIdx) {
		          case 0:
		        	  rarocMaster.setCol1(currentCell.getStringCellValue());
		            break;
		          case 1:
		        	  rarocMaster.setCol2(currentCell.getStringCellValue());
		            break;
		          case 2:
		        	  rarocMaster.setCol3(currentCell.getStringCellValue());
		            break;
		         
		          
		          default:
		            break;
		          }
		          cellIdx++;
		        }
		        rarocMasterdata.add(rarocMaster);
		      }
		      workbook.close();
		      return rarocMasterdata;
		    } catch (IOException e) {
		      throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
		    }
	}


	@Override
	public void saveFinHaircutFile(MultipartFile file) throws IOException {
		if (file != null) {
			
			List<RarocConfigModel> xlsData = finHaircutReadxls(file);
			
			String query= "DELETE FROM TEMP_mst_fin_haircut";
			getJdbcTemplate().update(query);
			
			for(RarocConfigModel data:xlsData) {
				query="INSERT INTO TEMP_mst_fin_haircut (V_FIN_SEC,N_HAIRCUT,N_REG_HAIRCUT,V_STATUS) values(?,?,?,'P')";
				getJdbcTemplate().update(query, new Object[]{data.getCol1(),data.getCol2(),data.getCol3()},new int[] {Types.VARCHAR,Types.DECIMAL,Types.DECIMAL });
				
			}
			
			
			
		}
		
	}


	@Override
	public GridPage<RarocConfigModel> listInternalRatingModel(int page, int max, String sidx, String sord,
			String searchField, String searchOper, String searchString, String userid) throws CustomException {
		 List<String> columns = Collections.unmodifiableList(Arrays.asList("V_RDM_INT_RAT", "V_INT_RATING","V_STATUS"));

	        List<String> orders = Collections.unmodifiableList(Arrays.asList("asc", "desc"));

	        if (sidx == null || sidx.isEmpty()) {
	            sidx = "V_STATUS";
	        }
	        if (sord == null || sord.isEmpty()) {
	            sord = "desc";
	        }

	        //check if sidx is in columns 
	        if (!columns.contains(sidx)) {
	            throw new CustomException();
	        }

	        if (!orders.contains(sord)) {
	            throw new CustomException();
	        }

	        QueryBuilderModel qObj = queryBuilder.SearchAnd(searchOper, searchField, searchString, columns);
	        String query = "SELECT count(*) FROM TEMP_cnfg_int_rat_mapping WHERE v_status is not null " +qObj.getCondition()  ;
	        int rowCount = getJdbcTemplate().queryForObject(query, new Object[]{qObj.getRegex()}, Integer.class);
	        final int startIdx = ((page - 1) * max) + 1;
	        final int endIdx = Math.min(startIdx + max, rowCount);
	        query = " SELECT * FROM "
	        		+"( SELECT a.*, rownum rnum FROM "
	        		+ "(SELECT V_RDM_INT_RAT,V_INT_RATING,V_STATUS FROM TEMP_cnfg_int_rat_mapping "
	                + "WHERE v_status is not null "
	                + qObj.getCondition() + " "
	                + "ORDER BY " + sidx + " " + sord + ") a"
	        + " WHERE rownum <= ?) WHERE rnum >= ?";
	        List<RarocConfigModel> lists = new ArrayList<>();
	        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(query, new Object[]{ qObj.getRegex(),endIdx, startIdx});
	        rows.stream().map((row) -> {
	        	RarocConfigModel obj = new RarocConfigModel();
	            obj.setId(fmt.ToString(row.get("rnum")));
	            obj.setCol1(fmt.ToString(row.get("V_RDM_INT_RAT")));
	            obj.setCol2(fmt.ToString(row.get("V_INT_RATING")));
	            obj.setCol3(fmt.ToString(row.get("V_STATUS")));
	            return obj;
	        }).forEach((obj) -> {
	            lists.add(obj);
	        });
	        return new GridPage<>(lists, page, max, rowCount);
	}


	@Override
	public List<RarocConfigModel> listInternalRatingModelDoc() {
		List<RarocConfigModel> list = new ArrayList<>();
		String query = "SELECT rownum rnum,V_RDM_INT_RAT,V_INT_RATING,V_STATUS FROM TEMP_cnfg_int_rat_mapping WHERE v_status is not null ";           
	    
  		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(query);
  		rows.stream().map((row) -> {
  			RarocConfigModel obj = new RarocConfigModel();
  			 obj.setId(fmt.ToString(row.get("rnum")));
	            obj.setCol1(fmt.ToString(row.get("V_RDM_INT_RAT")));
	            obj.setCol2(fmt.ToString(row.get("V_INT_RATING")));
	            obj.setCol3(fmt.ToString(row.get("V_STATUS")));
  			return obj;
  		}).forEach((obj) -> {
  			list.add(obj);
  		});

  		return list;
	}


	@Override
	public List<RarocConfigModel> internalRatingModelReadxls(MultipartFile file) throws IOException {
		try {
		      Workbook workbook = new XSSFWorkbook(file.getInputStream());
		      Sheet sheet = workbook.getSheet(SHEET);
		      Iterator<Row> rows = sheet.iterator();
		      List<RarocConfigModel> rarocMasterdata = new ArrayList<RarocConfigModel>();
		      int rowNumber = 0;
		      while (rows.hasNext()) {
		        Row currentRow = rows.next();
		        // skip header
		        if (rowNumber == 0) {
		          rowNumber++;
		          continue;
		        }
		        Iterator<Cell> cellsInRow = currentRow.iterator();
		        RarocConfigModel rarocMaster = new RarocConfigModel();
		        int cellIdx = 0;
		        while (cellsInRow.hasNext()) {
		          Cell currentCell = cellsInRow.next();
		          switch (cellIdx) {
		          case 0:
		        	  rarocMaster.setCol1(currentCell.getStringCellValue());
		            break;
		          case 1:
		        	  rarocMaster.setCol2(currentCell.getStringCellValue());
		            break;
		          
		          
		          default:
		            break;
		          }
		          cellIdx++;
		        }
		        rarocMasterdata.add(rarocMaster);
		      }
		      workbook.close();
		      return rarocMasterdata;
		    } catch (IOException e) {
		      throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
		    }
	}


	@Override
	public void saveInternalRatingModelFile(MultipartFile file) throws IOException {
		if (file != null) {
			
			List<RarocConfigModel> xlsData = internalRatingModelReadxls(file);
			
			String query= "DELETE FROM TEMP_cnfg_int_rat_mapping";
			getJdbcTemplate().update(query);
			
			for(RarocConfigModel data:xlsData) {
				query="INSERT INTO TEMP_cnfg_int_rat_mapping (V_RDM_INT_RAT,V_INT_RATING,V_STATUS) values(?,?,'P')";
				getJdbcTemplate().update(query, new Object[]{data.getCol1(),data.getCol2()},new int[] {Types.VARCHAR,Types.VARCHAR });
				
			}
			
			
			
		}
		
	}


	@Override
	public GridPage<RarocConfigModel> listRatingModelMapping(int page, int max, String sidx, String sord,
			String searchField, String searchOper, String searchString, String userid) throws CustomException {
		 List<String> columns = Collections.unmodifiableList(Arrays.asList("V_SEGMENT", "M_RDM_MODEL","V_STATUS"));

	        List<String> orders = Collections.unmodifiableList(Arrays.asList("asc", "desc"));

	        if (sidx == null || sidx.isEmpty()) {
	            sidx = "V_STATUS";
	        }
	        if (sord == null || sord.isEmpty()) {
	            sord = "desc";
	        }

	        //check if sidx is in columns 
	        if (!columns.contains(sidx)) {
	            throw new CustomException();
	        }

	        if (!orders.contains(sord)) {
	            throw new CustomException();
	        }

	        QueryBuilderModel qObj = queryBuilder.SearchAnd(searchOper, searchField, searchString, columns);
	        String query = "SELECT count(*) FROM TEMP_cnfg_rating_model_map WHERE v_status is not null " +qObj.getCondition()  ;
	        int rowCount = getJdbcTemplate().queryForObject(query, new Object[]{qObj.getRegex()}, Integer.class);
	        final int startIdx = ((page - 1) * max) + 1;
	        final int endIdx = Math.min(startIdx + max, rowCount);
	        query = " SELECT * FROM "
	        		+"( SELECT a.*, rownum rnum FROM "
	        		+ "(SELECT V_SEGMENT,M_RDM_MODEL,V_STATUS FROM TEMP_cnfg_rating_model_map "
	                + "WHERE v_status is not null "
	                + qObj.getCondition() + " "
	                + "ORDER BY " + sidx + " " + sord + ") a"
	        + " WHERE rownum <= ?) WHERE rnum >= ?";
	        List<RarocConfigModel> lists = new ArrayList<>();
	        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(query, new Object[]{ qObj.getRegex(),endIdx, startIdx});
	        rows.stream().map((row) -> {
	        	RarocConfigModel obj = new RarocConfigModel();
	            obj.setId(fmt.ToString(row.get("rnum")));
	            obj.setCol1(fmt.ToString(row.get("V_SEGMENT")));
	            obj.setCol2(fmt.ToString(row.get("M_RDM_MODEL")));
	            obj.setCol3(fmt.ToString(row.get("V_STATUS")));
	            return obj;
	        }).forEach((obj) -> {
	            lists.add(obj);
	        });
	        return new GridPage<>(lists, page, max, rowCount);
	}


	@Override
	public List<RarocConfigModel> listRatingModelMappingDoc() {
		List<RarocConfigModel> list = new ArrayList<>();
		String query = "SELECT rownum rnum, V_SEGMENT,M_RDM_MODEL,V_STATUS FROM TEMP_cnfg_rating_model_map WHERE v_status is not null ";           
	    
  		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(query);
  		rows.stream().map((row) -> {
  			RarocConfigModel obj = new RarocConfigModel();
  			obj.setId(fmt.ToString(row.get("rnum")));
            obj.setCol1(fmt.ToString(row.get("V_SEGMENT")));
            obj.setCol2(fmt.ToString(row.get("M_RDM_MODEL")));
            obj.setCol3(fmt.ToString(row.get("V_STATUS")));
  			return obj;
  		}).forEach((obj) -> {
  			list.add(obj);
  		});

  		return list;
	}


	@Override
	public List<RarocConfigModel> ratingModelMappingReadxls(MultipartFile file) throws IOException {
		try {
		      Workbook workbook = new XSSFWorkbook(file.getInputStream());
		      Sheet sheet = workbook.getSheet(SHEET);
		      Iterator<Row> rows = sheet.iterator();
		      List<RarocConfigModel> rarocMasterdata = new ArrayList<RarocConfigModel>();
		      int rowNumber = 0;
		      while (rows.hasNext()) {
		        Row currentRow = rows.next();
		        // skip header
		        if (rowNumber == 0) {
		          rowNumber++;
		          continue;
		        }
		        Iterator<Cell> cellsInRow = currentRow.iterator();
		        RarocConfigModel rarocMaster = new RarocConfigModel();
		        int cellIdx = 0;
		        while (cellsInRow.hasNext()) {
		          Cell currentCell = cellsInRow.next();
		          switch (cellIdx) {
		          case 0:
		        	  rarocMaster.setCol1(currentCell.getStringCellValue());
		            break;
		          case 1:
		        	  rarocMaster.setCol2(currentCell.getStringCellValue());
		            break;
		         		          
		          default:
		            break;
		          }
		          cellIdx++;
		        }
		        rarocMasterdata.add(rarocMaster);
		      }
		      workbook.close();
		      return rarocMasterdata;
		    } catch (IOException e) {
		      throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
		    }
	}


	@Override
	public void saveRatingModelMappingFile(MultipartFile file) throws IOException {
if (file != null) {
			
			List<RarocConfigModel> xlsData = ratingModelMappingReadxls(file);
			
			String query= "DELETE FROM TEMP_cnfg_rating_model_map";
			getJdbcTemplate().update(query);
			
			for(RarocConfigModel data:xlsData) {
				query="INSERT INTO TEMP_cnfg_rating_model_map ( V_SEGMENT,M_RDM_MODEL,V_STATUS) values(?,?,'P')";
				getJdbcTemplate().update(query, new Object[]{data.getCol1(),data.getCol2()},new int[] {Types.VARCHAR,Types.VARCHAR });
				
			}
			
			
			
		}
		
	}


	@Override
	public void saveExternalRatingFile(MultipartFile file) throws IOException {
		if (file != null) {
			
			List<RarocConfigModel> xlsData = externalRatingReadxls(file);
			
			String query= "DELETE FROM TEMP_mast_external_rating";
			getJdbcTemplate().update(query);
			
			for(RarocConfigModel data:xlsData) {
				
				System.out.println(data.getCol1()+","+data.getCol2()+","+data.getCol3()+","+data.getCol4()+","+data.getCol5()+","+data.getCol6()+","+data.getCol7());
				query="INSERT INTO TEMP_mast_external_rating (V_RATING_CODE,V_GRADE,V_RANK,F_TERM_FLAG,N_RISK_WEIGHT,N_W,V_GUARANTOR_TYPE,V_STATUS) values(?,?,?,?,?,?,?,'P')";
				getJdbcTemplate().update(query, new Object[]{data.getCol1(),data.getCol2(),data.getCol3(),data.getCol4(),data.getCol5(),data.getCol6(),data.getCol7()},new int[] {Types.VARCHAR,Types.CHAR,Types.NUMERIC,Types.CHAR,Types.DECIMAL,Types.DECIMAL,Types.VARCHAR });
				
			}
			
			
			
		}
		
	}

	@Override
	public List<RarocConfigModel> externalRatingReadxls(MultipartFile file) throws IOException {
		try {
		      Workbook workbook = new XSSFWorkbook(file.getInputStream());
		      Sheet sheet = workbook.getSheet(SHEET);
		      Iterator<Row> rows = sheet.iterator();
		      List<RarocConfigModel> rarocMasterdata = new ArrayList<RarocConfigModel>();
		      int rowNumber = 0;
		      while (rows.hasNext()) {
		        Row currentRow = rows.next();
		        // skip header
		        if (rowNumber == 0) {
		          rowNumber++;
		          continue;
		        }
		        Iterator<Cell> cellsInRow = currentRow.iterator();
		        RarocConfigModel rarocMaster = new RarocConfigModel();
		        int cellIdx = 0;
		        while (cellsInRow.hasNext()) {
		          Cell currentCell = cellsInRow.next();
		          switch (cellIdx) {
		          case 0:
		        	  rarocMaster.setCol1(currentCell.getStringCellValue());
		            break;
		          case 1:
		        	  rarocMaster.setCol2(currentCell.getStringCellValue());
		            break;
		          case 2:
		        	  if (currentCell.getStringCellValue() == null || currentCell.getCellType() == Cell.CELL_TYPE_BLANK||currentCell.getCellType() == Cell.CELL_TYPE_STRING && currentCell.getStringCellValue().trim().isEmpty()) {
	        		  	  rarocMaster.setCol3("0");
	        		 }else {
	        			  rarocMaster.setCol3(currentCell.getStringCellValue());	 
	        		 }
		            break;
		          case 3:
		        	  rarocMaster.setCol4(currentCell.getStringCellValue());
		            break;
		          case 4:
		        	  if (currentCell.getStringCellValue() == null || currentCell.getCellType() == Cell.CELL_TYPE_BLANK||currentCell.getCellType() == Cell.CELL_TYPE_STRING && currentCell.getStringCellValue().trim().isEmpty()) {
	        		  	  rarocMaster.setCol5("0.0");
	        		 }else {
	        			  rarocMaster.setCol5(currentCell.getStringCellValue());	 
	        		 }
		            break;
		          case 5:
		        	  if (currentCell.getStringCellValue() == null || currentCell.getCellType() == Cell.CELL_TYPE_BLANK||currentCell.getCellType() == Cell.CELL_TYPE_STRING && currentCell.getStringCellValue().trim().isEmpty()) {
		        		  	  rarocMaster.setCol6("0.0");
		        		 }else {
		        			  rarocMaster.setCol6(currentCell.getStringCellValue());	 
		        		}
		        	
		            break;
		          case 6:
		        	  rarocMaster.setCol7(currentCell.getStringCellValue());
		            break;
		          
		          default:
		            break;
		          }
		          cellIdx++;
		        }
		        rarocMasterdata.add(rarocMaster);
		      }
		      workbook.close();
		      return rarocMasterdata;
		    } catch (IOException e) {
		      throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
		    }
	}



	@Override
	public void saveGuarantorMasterFile(MultipartFile file) throws IOException {
		if (file != null) {
			
			List<RarocConfigModel> xlsData = guarantorMasterReadxls(file);
			
			String query= "DELETE FROM TEMP_MST_GUARANTOR";
			getJdbcTemplate().update(query);
			
			for(RarocConfigModel data:xlsData) {
				query="INSERT INTO TEMP_MST_GUARANTOR (V_CODE,N_VALUE,V_MAPPING_COL,V_STATUS) values(?,?,?,'P')";
				getJdbcTemplate().update(query, new Object[]{data.getCol1(),data.getCol2(),data.getCol3()},new int[] {Types.VARCHAR,Types.DECIMAL,Types.VARCHAR });
				
			}
			
			
			
		}
		
	}

	@Override
	public List<RarocConfigModel> guarantorMasterReadxls(MultipartFile file) throws IOException {
		try {
		      Workbook workbook = new XSSFWorkbook(file.getInputStream());
		      Sheet sheet = workbook.getSheet(SHEET);
		      Iterator<Row> rows = sheet.iterator();
		      List<RarocConfigModel> rarocMasterdata = new ArrayList<RarocConfigModel>();
		      int rowNumber = 0;
		      while (rows.hasNext()) {
		        Row currentRow = rows.next();
		        // skip header
		        if (rowNumber == 0) {
		          rowNumber++;
		          continue;
		        }
		        Iterator<Cell> cellsInRow = currentRow.iterator();
		        RarocConfigModel rarocMaster = new RarocConfigModel();
		        int cellIdx = 0;
		        while (cellsInRow.hasNext()) {
		          Cell currentCell = cellsInRow.next();
		          switch (cellIdx) {
		          case 0:
		        	  rarocMaster.setCol1(currentCell.getStringCellValue());
		            break;
		          case 1:
		        	  rarocMaster.setCol2(currentCell.getStringCellValue());
		            break;
		          case 2:
		        	  rarocMaster.setCol3(currentCell.getStringCellValue());
		            break;
		         
		          
		          default:
		            break;
		          }
		          cellIdx++;
		        }
		        rarocMasterdata.add(rarocMaster);
		      }
		      workbook.close();
		      return rarocMasterdata;
		    } catch (IOException e) {
		      throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
		    }
	}



	@Override
	public void saveOthIncomeMasterFile(MultipartFile file) throws IOException {
		if (file != null) {
			
			List<RarocConfigModel> xlsData = othIncomeMasterReadxls(file);
			
			String query= "DELETE FROM TEMP_MST_OTH_INCOME";
			getJdbcTemplate().update(query);
			
			for(RarocConfigModel data:xlsData) {
				query="INSERT INTO TEMP_MST_OTH_INCOME (OTH_INCOME_TYPE,CI_RATIO,TNFR_RATE,V_STATUS) values(?,?,?,'P')";
				getJdbcTemplate().update(query, new Object[]{data.getCol1(),data.getCol2(),data.getCol3()},new int[] {Types.VARCHAR,Types.DECIMAL,Types.DECIMAL });
				
			}
			
			
			
		}
		
	}

	@Override
	public List<RarocConfigModel> othIncomeMasterReadxls(MultipartFile file) throws IOException {
		try {
		      Workbook workbook = new XSSFWorkbook(file.getInputStream());
		      Sheet sheet = workbook.getSheet(SHEET);
		      Iterator<Row> rows = sheet.iterator();
		      List<RarocConfigModel> rarocMasterdata = new ArrayList<RarocConfigModel>();
		      int rowNumber = 0;
		      while (rows.hasNext()) {
		        Row currentRow = rows.next();
		        // skip header
		        if (rowNumber == 0) {
		          rowNumber++;
		          continue;
		        }
		        Iterator<Cell> cellsInRow = currentRow.iterator();
		        RarocConfigModel rarocMaster = new RarocConfigModel();
		        int cellIdx = 0;
		        while (cellsInRow.hasNext()) {
		          Cell currentCell = cellsInRow.next();
		          switch (cellIdx) {
		          case 0:
		        	  rarocMaster.setCol1(currentCell.getStringCellValue());
		            break;
		          case 1:
		        	  rarocMaster.setCol2(currentCell.getStringCellValue());
		            break;
		          case 2:
		        	  rarocMaster.setCol3(currentCell.getStringCellValue());
		            break;
		          
		          default:
		            break;
		          }
		          cellIdx++;
		        }
		        rarocMasterdata.add(rarocMaster);
		      }
		      workbook.close();
		      return rarocMasterdata;
		    } catch (IOException e) {
		      throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
		    }
	}


	@Override
	public void saveCCFMasterFile(MultipartFile file) throws IOException {
		if (file != null) {
			
			List<RarocConfigModel> xlsData = cCFMasterReadxls(file);
			
			String query= "DELETE FROM TEMP_MST_CCF";
			getJdbcTemplate().update(query);
			
			for(RarocConfigModel data:xlsData) {
				query="INSERT INTO TEMP_MST_CCF (V_CODE,V_FACILITY_TYPE,N_LIMIT_LOWER,N_LIMIT_UPPER,N_UTILIZATION_LOWER,N_UTILIZATION_UPPER,N_DRAWN_CCF,N_UNDRAWN_CCF,V_STATUS) values(?,?,?,?,?,?,?,?,'P')";
				getJdbcTemplate().update(query, new Object[]{data.getCol1(),data.getCol2(),data.getCol3(),data.getCol4(),data.getCol5(),data.getCol6(),data.getCol7(),data.getCol8()},new int[] {Types.VARCHAR,Types.VARCHAR,Types.NUMERIC,Types.NUMERIC,Types.NUMERIC,Types.NUMERIC,Types.DECIMAL,Types.DECIMAL });
				
			}
			
			
			
		}		
	}

	@Override
	public List<RarocConfigModel> cCFMasterReadxls(MultipartFile file) throws IOException {
		try {
		      Workbook workbook = new XSSFWorkbook(file.getInputStream());
		      Sheet sheet = workbook.getSheet(SHEET);
		      Iterator<Row> rows = sheet.iterator();
		      List<RarocConfigModel> rarocMasterdata = new ArrayList<RarocConfigModel>();
		      int rowNumber = 0;
		      while (rows.hasNext()) {
		        Row currentRow = rows.next();
		        // skip header
		        if (rowNumber == 0) {
		          rowNumber++;
		          continue;
		        }
		        Iterator<Cell> cellsInRow = currentRow.iterator();
		        RarocConfigModel rarocMaster = new RarocConfigModel();
		        int cellIdx = 0;
		        while (cellsInRow.hasNext()) {
		          Cell currentCell = cellsInRow.next();
		          switch (cellIdx) {
		          case 0:
		        	  rarocMaster.setCol1(currentCell.getStringCellValue());
		            break;
		          case 1:
		        	  rarocMaster.setCol2(currentCell.getStringCellValue());
		            break;
		          case 2:
		        	  rarocMaster.setCol3(currentCell.getStringCellValue());
		            break;
		          case 3:
		        	  rarocMaster.setCol4(currentCell.getStringCellValue());
		            break;
		          case 4:
		        	  rarocMaster.setCol5(currentCell.getStringCellValue());
		            break;
		          case 5:
		        	  rarocMaster.setCol6(currentCell.getStringCellValue());
		            break;
		          case 6:
		        	  rarocMaster.setCol7(currentCell.getStringCellValue());
		            break;
		          case 7:
		        	  rarocMaster.setCol8(currentCell.getStringCellValue());
		            break;
		          
		          default:
		            break;
		          }
		          cellIdx++;
		        }
		        rarocMasterdata.add(rarocMaster);
		      }
		      workbook.close();
		      return rarocMasterdata;
		    } catch (IOException e) {
		      throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
		    }
	}


	@Override
	public void saveOperatingExpenseMasterFile(MultipartFile file) throws IOException {
		if (file != null) {
			
			List<RarocConfigModel> xlsData = operatingExpenseMasterReadxls(file);
			
			String query= "DELETE FROM TEMP_MST_OPERATING_EXPENSE";
			getJdbcTemplate().update(query);
			
			for(RarocConfigModel data:xlsData) {
				query="INSERT INTO TEMP_MST_OPERATING_EXPENSE (V_FACILITY,V_SEGMENT,N_LOWER_LEVEL,N_UPPER_LEVEL,V_ID,N_FIXED_COST,N_VARIABLE_COST,V_REGION,V_STATUS) values(?,?,?,?,?,?,?,?,'P')";
				getJdbcTemplate().update(query, new Object[]{data.getCol1(),data.getCol2(),data.getCol3(),data.getCol4(),data.getCol5(),data.getCol6(),data.getCol7(),data.getCol8()},new int[] {Types.VARCHAR,Types.VARCHAR,Types.NUMERIC,Types.NUMERIC,Types.VARCHAR,Types.DECIMAL,Types.DECIMAL,Types.VARCHAR });
				
			}
			
			
			
		}
		
	}

	@Override
	public List<RarocConfigModel> operatingExpenseMasterReadxls(MultipartFile file) throws IOException {
		try {
		      Workbook workbook = new XSSFWorkbook(file.getInputStream());
		      Sheet sheet = workbook.getSheet(SHEET);
		      Iterator<Row> rows = sheet.iterator();
		      List<RarocConfigModel> rarocMasterdata = new ArrayList<RarocConfigModel>();
		      int rowNumber = 0;
		      while (rows.hasNext()) {
		        Row currentRow = rows.next();
		        // skip header
		        if (rowNumber == 0) {
		          rowNumber++;
		          continue;
		        }
		        Iterator<Cell> cellsInRow = currentRow.iterator();
		        RarocConfigModel rarocMaster = new RarocConfigModel();
		        int cellIdx = 0;
		        while (cellsInRow.hasNext()) {
		          Cell currentCell = cellsInRow.next();
		          switch (cellIdx) {
		          case 0:
		        	  rarocMaster.setCol1(currentCell.getStringCellValue());
		            break;
		          case 1:
		        	  rarocMaster.setCol2(currentCell.getStringCellValue());
		            break;
		          case 2:
		        	  rarocMaster.setCol3(currentCell.getStringCellValue());
		            break;
		          case 3:
		        	  rarocMaster.setCol4(currentCell.getStringCellValue());
		            break;
		          case 4:
		        	  rarocMaster.setCol5(currentCell.getStringCellValue());
		            break;
		          case 5:
		        	  rarocMaster.setCol6(currentCell.getStringCellValue());
		            break;
		          case 6:
		        	  rarocMaster.setCol7(currentCell.getStringCellValue());
		            break;
		          case 7:
		        	  rarocMaster.setCol8(currentCell.getStringCellValue());
		            break;
		         
		          default:
		            break;
		          }
		          cellIdx++;
		        }
		        rarocMasterdata.add(rarocMaster);
		      }
		      workbook.close();
		      return rarocMasterdata;
		    } catch (IOException e) {
		      throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
		    }
	}


	@Override
	public void saveRestructuredMasterFile(MultipartFile file) throws IOException {
		if (file != null) {
			
			List<RarocConfigModel> xlsData = restructuredMasterReadxls(file);
			
			String query= "DELETE FROM TEMP_MST_RESTRUCTURED_RW";
			getJdbcTemplate().update(query);
			
			for(RarocConfigModel data:xlsData) {
				query="INSERT INTO TEMP_MST_RESTRUCTURED_RW (V_CODE,N_RW,N_EL,V_STATUS) values(?,?,?,'P')";
				getJdbcTemplate().update(query, new Object[]{data.getCol1(),data.getCol2(),data.getCol3()},new int[] {Types.VARCHAR,Types.DECIMAL,Types.NUMERIC });
				
			}
			
			
			
		}
		
	}

	@Override
	public List<RarocConfigModel> restructuredMasterReadxls(MultipartFile file) throws IOException {
		try {
		      Workbook workbook = new XSSFWorkbook(file.getInputStream());
		      Sheet sheet = workbook.getSheet(SHEET);
		      Iterator<Row> rows = sheet.iterator();
		      List<RarocConfigModel> rarocMasterdata = new ArrayList<RarocConfigModel>();
		      int rowNumber = 0;
		      while (rows.hasNext()) {
		        Row currentRow = rows.next();
		        // skip header
		        if (rowNumber == 0) {
		          rowNumber++;
		          continue;
		        }
		        Iterator<Cell> cellsInRow = currentRow.iterator();
		        RarocConfigModel rarocMaster = new RarocConfigModel();
		        int cellIdx = 0;
		        while (cellsInRow.hasNext()) {
		          Cell currentCell = cellsInRow.next();
		          switch (cellIdx) {
		          case 0:
		        	  rarocMaster.setCol1(currentCell.getStringCellValue());
		            break;
		          case 1:
		        	  rarocMaster.setCol2(currentCell.getStringCellValue());
		            break;
		          case 2:
		        	  rarocMaster.setCol3(currentCell.getStringCellValue());
		            break;
		          		          
		          default:
		            break;
		          }
		          cellIdx++;
		        }
		        rarocMasterdata.add(rarocMaster);
		      }
		      workbook.close();
		      return rarocMasterdata;
		    } catch (IOException e) {
		      throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
		    }
	}


	@Override
	public void saveSensitivityIterationFile(MultipartFile file) throws IOException {
		if (file != null) {
			
			List<RarocConfigModel> xlsData = sensitivityIterationReadxls(file);
			
			String query= "DELETE FROM TEMP_MST_SENSITIVITY_ITERATIONS";
			getJdbcTemplate().update(query);
			
			for(RarocConfigModel data:xlsData) {
				query="INSERT INTO TEMP_MST_SENSITIVITY_ITERATIONS (N_VALUE,V_TYPE,V_STATUS) values(?,?,'P')";
				getJdbcTemplate().update(query, new Object[]{data.getCol1(),data.getCol2()},new int[] {Types.DECIMAL,Types.VARCHAR });
				
			}
			
			
			
		}
	}


	@Override
	public List<RarocConfigModel> sensitivityIterationReadxls(MultipartFile file) throws IOException {
		try {
		      Workbook workbook = new XSSFWorkbook(file.getInputStream());
		      Sheet sheet = workbook.getSheet(SHEET);
		      Iterator<Row> rows = sheet.iterator();
		      List<RarocConfigModel> rarocMasterdata = new ArrayList<RarocConfigModel>();
		      int rowNumber = 0;
		      while (rows.hasNext()) {
		        Row currentRow = rows.next();
		        // skip header
		        if (rowNumber == 0) {
		          rowNumber++;
		          continue;
		        }
		        Iterator<Cell> cellsInRow = currentRow.iterator();
		        RarocConfigModel rarocMaster = new RarocConfigModel();
		        int cellIdx = 0;
		        while (cellsInRow.hasNext()) {
		          Cell currentCell = cellsInRow.next();
		          switch (cellIdx) {
		          case 0:
		        	  rarocMaster.setCol1(currentCell.getStringCellValue());
		            break;
		          case 1:
		        	  rarocMaster.setCol2(currentCell.getStringCellValue());
		            break;
		         
		          
		          default:
		            break;
		          }
		          cellIdx++;
		        }
		        rarocMasterdata.add(rarocMaster);
		      }
		      workbook.close();
		      return rarocMasterdata;
		    } catch (IOException e) {
		      throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
		    }
	}


	@Override
	public void rejectConfig(String tableName) {
		String	query="update "+tableName+" set V_STATUS ='R'";
		getJdbcTemplate().update(query);
	}


	@Override
	public void approveConfig() {
		List<Map<String, Object>> rows=null;
		
		
		String query= "DELETE FROM MST_RAROC";
		getJdbcTemplate().update(query);
		
		query= "update temp_MST_RAROC set v_status='A'";
		getJdbcTemplate().update(query);
		
		query="SELECT V_CODE,N_CODE,N_VALUE FROM TEMP_MST_RAROC WHERE v_status is not null and v_status='P' ";
    	rows = getJdbcTemplate().queryForList(query);
	        try {
	        	 rows.stream().forEach((row) -> {
	        		 String V_CODE=fmt.ToString(row.get("V_CODE"));
	        		 String N_CODE=fmt.ToString(row.get("N_CODE"));
	            	 String N_VALUE=fmt.ToString(row.get("N_VALUE"));
		             
	            	String qury="INSERT INTO MST_RAROC (V_CODE, D_MIS_DATE, N_CODE, N_VALUE) values(?,SYSDATE,?,?)";
	     			getJdbcTemplate().update(qury, new Object[]{V_CODE,N_CODE,N_VALUE},new int[] {Types.VARCHAR,Types.NUMERIC,Types.DECIMAL });

		            });
	           
	            
	        } catch (Exception e) {
	            logger.error(e);
	        }
	}
	
	

	@Override
	public void approveInternalRating() {
		List<Map<String, Object>> rows=null;
		
		
		String query= "DELETE FROM mast_internal_rating";
		getJdbcTemplate().update(query);
		
		query= "update temp_mast_internal_rating set v_status='A'";
		getJdbcTemplate().update(query);
		
		query="SELECT V_INT_RATING, V_FORMAT_RATING,V_MAPPED_RATING, V_GRADE, V_RATING_TYPE,N_INT_RATING_LIMIT,N_PD,N_LGD,N_PD_NPA,N_LGD_NPA,N_EL,V_MODEL,N_RANK ,V_FORMATED_RATING_LCMC_RD,V_FORMATED_RATING_SME_RD,V_FORMATED_RATING_RD,V_FORMATED_RATING_OSMOS,N_TENURE,F_ELIGIBLE_GUARANTOR,V_GUARANTOR_TYPE,V_MAPPED_EXT_RATING FROM TEMP_mast_internal_rating  WHERE v_status is not null and v_status='P' ";
    	rows = getJdbcTemplate().queryForList(query);
	        try {
	        	 rows.stream().forEach((row) -> {
	        	 String V_INT_RATING = fmt.ToString(row.get("V_INT_RATING"));
	        	 String V_FORMAT_RATING = fmt.ToString(row.get("V_FORMAT_RATING"));
	        	 String V_MAPPED_RATING = fmt.ToString(row.get("V_MAPPED_RATING"));
	        	 String V_GRADE = fmt.ToString(row.get("V_GRADE"));
	        	 String V_RATING_TYPE = fmt.ToString(row.get("V_RATING_TYPE"));
	        	 String N_INT_RATING_LIMIT = fmt.ToString(row.get("N_INT_RATING_LIMIT"));
	        	 String N_PD = fmt.ToString(row.get("N_PD"));
	        	 String N_LGD = fmt.ToString(row.get("N_LGD"));
	        	 String N_PD_NPA =fmt.ToString(row.get("N_PD_NPA"));
	        	 String N_LGD_NPA=fmt.ToString(row.get("N_LGD_NPA"));
	        	 String N_EL =fmt.ToString(row.get("N_EL"));
	        	 String V_MODEL=fmt.ToString(row.get("V_MODEL"));
	        	 String N_RANK=fmt.ToString(row.get("N_RANK"));
	        	 String V_FORMATED_RATING_LCMC_RD=fmt.ToString(row.get("V_FORMATED_RATING_LCMC_RD"));
	        	 String V_FORMATED_RATING_SME_RD=fmt.ToString(row.get("V_FORMATED_RATING_SME_RD"));
	        	 String V_FORMATED_RATING_RD=fmt.ToString(row.get("V_FORMATED_RATING_RD"));
	        	 String V_FORMATED_RATING_OSMOS=fmt.ToString(row.get("V_FORMATED_RATING_OSMOS"));
	        	 String N_TENURE=fmt.ToString(row.get("N_TENURE"));
	        	 String F_ELIGIBLE_GUARANTOR=fmt.ToString(row.get("F_ELIGIBLE_GUARANTOR"));
	        	 String V_GUARANTOR_TYPE=fmt.ToString(row.get("V_GUARANTOR_TYPE"));
	        	 String V_MAPPED_EXT_RATING=fmt.ToString(row.get("V_MAPPED_EXT_RATING"));

		             
	            	String qury="INSERT INTO mast_internal_rating (V_INT_RATING, V_FORMAT_RATING,V_MAPPED_RATING, V_GRADE, V_RATING_TYPE,"
							+ "N_INT_RATING_LIMIT,N_PD,N_LGD,N_PD_NPA,N_LGD_NPA,N_EL,V_MODEL,N_RANK ,V_FORMATED_RATING_LCMC_RD,V_FORMATED_RATING_SME_RD,V_FORMATED_RATING_RD,V_FORMATED_RATING_OSMOS,N_TENURE,F_ELIGIBLE_GUARANTOR,V_GUARANTOR_TYPE,V_MAPPED_EXT_RATING) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
					getJdbcTemplate().update(qury, new Object[]{V_INT_RATING, V_FORMAT_RATING,V_MAPPED_RATING, V_GRADE, V_RATING_TYPE,N_INT_RATING_LIMIT,N_PD,N_LGD,N_PD_NPA,N_LGD_NPA,N_EL,V_MODEL,N_RANK ,V_FORMATED_RATING_LCMC_RD,V_FORMATED_RATING_SME_RD,V_FORMATED_RATING_RD,V_FORMATED_RATING_OSMOS,N_TENURE,F_ELIGIBLE_GUARANTOR,V_GUARANTOR_TYPE,V_MAPPED_EXT_RATING},
							new int[] {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.CHAR,Types.VARCHAR ,Types.DECIMAL,Types.DECIMAL,Types.DECIMAL,Types.DECIMAL,Types.DECIMAL,
									   Types.DECIMAL,Types.VARCHAR,Types.NUMERIC,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.DECIMAL,Types.CHAR,Types.VARCHAR,
									   Types.VARCHAR});
		            });
	           
	            
	        } catch (Exception e) {
	            logger.error(e);
	        }
	}


	@Override
	public void approveExternalRating() {
		List<Map<String, Object>> rows=null;
		
		
		String query= "DELETE FROM mast_external_rating";
		getJdbcTemplate().update(query);
		
		query= "update temp_mast_external_rating set v_status='A'";
		getJdbcTemplate().update(query);
		
		query="SELECT V_RATING_CODE,V_GRADE,V_RANK,F_TERM_FLAG,N_RISK_WEIGHT,N_W,V_GUARANTOR_TYPE FROM TEMP_mast_external_rating WHERE v_status is not null and v_status='P' ";
    	rows = getJdbcTemplate().queryForList(query);
	        try {
	        	 rows.stream().forEach((row) -> {
	        		 
	        		 String V_RATING_CODE = fmt.ToString(row.get("V_RATING_CODE"));
	        		 String V_GRADE =fmt.ToString(row.get("V_GRADE"));
		        	 String V_RANK = fmt.ToString(row.get("V_RANK"));
		        	 String F_TERM_FLAG = fmt.ToString(row.get("F_TERM_FLAG"));
		        	 String N_RISK_WEIGHT = fmt.ToString(row.get("N_RISK_WEIGHT"));
		        	 String N_W = fmt.ToString(row.get("N_W"));
		        	 String V_GUARANTOR_TYPE = fmt.ToString(row.get("V_GUARANTOR_TYPE"));
		        	
		             
	            	String  qury="INSERT INTO mast_external_rating (V_RATING_CODE,V_GRADE,V_RANK,F_TERM_FLAG,N_RISK_WEIGHT,N_W,V_GUARANTOR_TYPE) values (?,?,?,?,?,?,?)";
	 				 getJdbcTemplate().update(qury, new Object[]{V_RATING_CODE,V_GRADE,V_RANK,F_TERM_FLAG,N_RISK_WEIGHT,N_W,V_GUARANTOR_TYPE},new int[] {Types.VARCHAR,Types.CHAR,Types.NUMERIC,Types.CHAR,Types.DECIMAL,Types.DECIMAL,Types.VARCHAR });
	 				

		            });
	           
	            
	        } catch (Exception e) {
	            logger.error(e);
	        }
		
	}


	@Override
	public void approveGuarantor() {
		List<Map<String, Object>> rows=null;
		
		
		String query= "DELETE FROM MST_GUARANTOR";
		getJdbcTemplate().update(query);
		
		query= "update temp_MST_GUARANTOR set v_status='A'";
		getJdbcTemplate().update(query);
		
		query="SELECT V_CODE,N_VALUE,V_MAPPING_COL FROM TEMP_MST_GUARANTOR WHERE v_status is not null and v_status='P' ";
    	rows = getJdbcTemplate().queryForList(query);
	        try {
	        	 rows.stream().forEach((row) -> {
	        		
	        		String V_CODE = fmt.ToString(row.get("V_CODE"));
	 	        	String N_VALUE = fmt.ToString(row.get("N_VALUE"));
	 	        	String V_MAPPING_COL = fmt.ToString(row.get("V_MAPPING_COL"));
	 	        	
	 	        String 	qury="INSERT INTO MST_GUARANTOR (V_CODE,N_VALUE,V_MAPPING_COL) values(?,?,?)";
					getJdbcTemplate().update(qury, new Object[]{V_CODE,N_VALUE,V_MAPPING_COL},new int[] {Types.VARCHAR,Types.DECIMAL,Types.VARCHAR });
					
		            });
	           
	            
	        } catch (Exception e) {
	            logger.error(e);
	        }
		
	}

	@Override
	public void approveIncomeMater() {
		List<Map<String, Object>> rows=null;
		
		
		String query= "DELETE FROM MST_OTH_INCOME";
		getJdbcTemplate().update(query);
		
		query= "update temp_MST_OTH_INCOME set v_status='A'";
		getJdbcTemplate().update(query);
		
		query="SELECT OTH_INCOME_TYPE,CI_RATIO,TNFR_RATE FROM TEMP_MST_OTH_INCOME WHERE v_status is not null and v_status='P' ";
    	rows = getJdbcTemplate().queryForList(query);
	        try {
	        	 rows.stream().forEach((row) -> {
	        		 String OTH_INCOME_TYPE = fmt.ToString(row.get("OTH_INCOME_TYPE"));
	 	        	String CI_RATIO = fmt.ToString(row.get("CI_RATIO"));
	 	        	String TNFR_RATE = fmt.ToString(row.get("TNFR_RATE"));
	 	        	
		             
	            	String qury="INSERT INTO MST_OTH_INCOME (OTH_INCOME_TYPE,CI_RATIO,TNFR_RATE) values(?,?,?)" ; 
	            	getJdbcTemplate().update(qury, new Object[]{OTH_INCOME_TYPE,CI_RATIO,TNFR_RATE},new int[] {Types.VARCHAR,Types.DECIMAL,Types.DECIMAL }); 
	            							
		            });
	           
	            
	        } catch (Exception e) {
	            logger.error(e);
	        }
		
	}


	@Override
	public void approveCCFMaster() {
		List<Map<String, Object>> rows=null;
		
		
		String query= "DELETE FROM MST_CCF";
		getJdbcTemplate().update(query);
		
		query= "update temp_MST_CCF set v_status='A'";
		getJdbcTemplate().update(query);
		
		query="SELECT V_CODE,V_FACILITY_TYPE,N_LIMIT_LOWER,N_LIMIT_UPPER,N_UTILIZATION_LOWER,N_UTILIZATION_UPPER,N_DRAWN_CCF,N_UNDRAWN_CCF FROM TEMP_MST_CCF  WHERE v_status is not null and v_status='P' ";
    	rows = getJdbcTemplate().queryForList(query);
	        try {
	        	 rows.stream().forEach((row) -> {
	        		 String V_CODE = fmt.ToString(row.get("V_CODE"));
	        	 String V_FACILITY_TYPE = fmt.ToString(row.get("V_FACILITY_TYPE"));
	        	 String N_LIMIT_LOWER = fmt.ToString(row.get("N_LIMIT_LOWER"));
	        	 String N_LIMIT_UPPER = fmt.ToString(row.get("N_LIMIT_UPPER"));
	        	 String N_UTILIZATION_LOWER = fmt.ToString(row.get("N_UTILIZATION_LOWER"));
	        	 String N_UTILIZATION_UPPER = fmt.ToString(row.get("N_UTILIZATION_UPPER"));
	        	 String N_DRAWN_CCF = fmt.ToString(row.get("N_DRAWN_CCF"));
	        	 String N_UNDRAWN_CCF = fmt.ToString(row.get("N_UNDRAWN_CCF"));
		             
	            	String qury="INSERT INTO MST_CCF (V_CODE,V_FACILITY_TYPE,N_LIMIT_LOWER,N_LIMIT_UPPER,N_UTILIZATION_LOWER,N_UTILIZATION_UPPER,N_DRAWN_CCF,N_UNDRAWN_CCF) values(?,?,?,?,?,?,?,?)";
					getJdbcTemplate().update(qury, new Object[]{V_CODE,V_FACILITY_TYPE,N_LIMIT_LOWER,N_LIMIT_UPPER,N_UTILIZATION_LOWER,N_UTILIZATION_UPPER,N_DRAWN_CCF,N_UNDRAWN_CCF},new int[] {Types.VARCHAR,Types.VARCHAR,Types.NUMERIC,Types.NUMERIC,Types.NUMERIC,Types.NUMERIC,Types.DECIMAL,Types.DECIMAL });
					
		            });
	           
	            
	        } catch (Exception e) {
	            logger.error(e);
	        }
		
	}


	@Override
	public void approveOperatingExpense() {
		List<Map<String, Object>> rows=null;
		
		
		String query= "DELETE FROM MST_OPERATING_EXPENSE";
		getJdbcTemplate().update(query);
		
		query= "update temp_MST_OPERATING_EXPENSE set v_status='A'";
		getJdbcTemplate().update(query);
		
		query="SELECT V_FACILITY,V_SEGMENT,N_LOWER_LEVEL,N_UPPER_LEVEL,V_ID,N_FIXED_COST,N_VARIABLE_COST,V_REGION FROM TEMP_MST_OPERATING_EXPENSE WHERE v_status is not null and v_status='P'";
    	rows = getJdbcTemplate().queryForList(query);
	        try {
	        	 rows.stream().forEach((row) -> {
	        	 String V_FACILITY = fmt.ToString(row.get("V_FACILITY"));
	        	 String V_SEGMENT =fmt.ToString(row.get("V_SEGMENT"));
	        	 String N_LOWER_LEVEL=fmt.ToString(row.get("N_LOWER_LEVEL"));
	        	 String N_UPPER_LEVEL=fmt.ToString(row.get("N_UPPER_LEVEL"));
	        	 String V_ID=fmt.ToString(row.get("V_ID"));
	        	 String N_FIXED_COST=fmt.ToString(row.get("N_FIXED_COST"));
	        	 String N_VARIABLE_COST=fmt.ToString(row.get("N_VARIABLE_COST"));
	        	 String V_REGION=fmt.ToString(row.get("V_REGION"));
		             
	            	String  qury="INSERT INTO MST_OPERATING_EXPENSE (V_FACILITY,V_SEGMENT,N_LOWER_LEVEL,N_UPPER_LEVEL,V_ID,N_FIXED_COST,N_VARIABLE_COST,V_REGION) values(?,?,?,?,?,?,?,?)";
	 				getJdbcTemplate().update(qury, new Object[]{V_FACILITY,V_SEGMENT,N_LOWER_LEVEL,N_UPPER_LEVEL,V_ID,N_FIXED_COST,N_VARIABLE_COST,V_REGION},new int[] {Types.VARCHAR,Types.VARCHAR,Types.NUMERIC,Types.NUMERIC,Types.VARCHAR,Types.DECIMAL,Types.DECIMAL,Types.VARCHAR });
		            });
	           
	            
	        } catch (Exception e) {
	            logger.error(e);
	        }
		
	}


	@Override
	public void approveRestructuredMaster() {
		List<Map<String, Object>> rows=null;
		
		
		String query= "DELETE FROM MST_RESTRUCTURED_RW";
		getJdbcTemplate().update(query);
		
		query= "update temp_MST_RESTRUCTURED_RW set v_status='A'";
		getJdbcTemplate().update(query);
		
		query="SELECT V_CODE,N_RW,N_EL FROM TEMP_MST_RESTRUCTURED_RW WHERE v_status is not null and v_status='P' ";
    	rows = getJdbcTemplate().queryForList(query);
	        try {
	        	 rows.stream().forEach((row) -> {
	        		 String V_CODE=fmt.ToString(row.get("V_CODE"));
	        		 String N_RW=fmt.ToString(row.get("N_RW"));
	            	 String N_EL=fmt.ToString(row.get("N_EL"));
		             
	            	String qury="INSERT INTO MST_RESTRUCTURED_RW (V_CODE,N_RW,N_EL) values(?,?,?)";
					getJdbcTemplate().update(qury, new Object[]{V_CODE,N_RW,N_EL},new int[] {Types.VARCHAR,Types.DECIMAL,Types.NUMERIC });

		            });
	           
	            
	        } catch (Exception e) {
	            logger.error(e);
	        }
		
	}


	@Override
	public void approveSensitivityMaster() {
		List<Map<String, Object>> rows=null;
		
		
		String query= "DELETE FROM MST_SENSITIVITY_ITERATIONS";
		getJdbcTemplate().update(query);
		
		query= "update temp_MST_SENSITIVITY_ITERATIONS set v_status='A'";
		getJdbcTemplate().update(query);
		
		query="SELECT N_VALUE,V_TYPE FROM TEMP_MST_SENSITIVITY_ITERATIONS WHERE v_status is not null and v_status='P'";
    	rows = getJdbcTemplate().queryForList(query);
	        try {
	        	 rows.stream().forEach((row) -> {
	        		 String N_VALUE=fmt.ToString(row.get("N_VALUE"));
	        		 String V_TYPE=fmt.ToString(row.get("V_TYPE"));
		             
	            	String qury="INSERT INTO MST_SENSITIVITY_ITERATIONS (N_VALUE,V_TYPE) values(?,?)";
	     			getJdbcTemplate().update(qury, new Object[]{N_VALUE,V_TYPE},new int[] {Types.DECIMAL,Types.VARCHAR });

		            });
	           
	            
	        } catch (Exception e) {
	            logger.error(e);
	        }
		
	}


	@Override
	public void approveAssetType() {
		List<Map<String, Object>> rows=null;
		
		
		String query= "DELETE FROM MST_ASSET_TYPE";
		getJdbcTemplate().update(query);
		
		query= "update temp_MST_ASSET_TYPE set v_status='A'";
		getJdbcTemplate().update(query);
		
		query="SELECT V_CODE,V_DESC,N_VALUE,V_GUAR_FLAG FROM TEMP_MST_ASSET_TYPE WHERE v_status is not null and v_status='P' ";
    	rows = getJdbcTemplate().queryForList(query);
	        try {
	        	 rows.stream().forEach((row) -> {
	        		 String V_CODE=fmt.ToString(row.get("V_CODE"));
	        		 String V_DESC=fmt.ToString(row.get("V_DESC"));
	            	 String N_VALUE=fmt.ToString(row.get("N_VALUE"));
	            	 String V_GUAR_FLAG=fmt.ToString(row.get("V_GUAR_FLAG"));
		             
	            	String qury="INSERT INTO MST_ASSET_TYPE (V_CODE,V_DESC,N_VALUE,V_GUAR_FLAG) values(?,?,?,?)";
	     			getJdbcTemplate().update(qury, new Object[]{V_CODE,V_DESC,N_VALUE,V_GUAR_FLAG},new int[] {Types.VARCHAR,Types.VARCHAR,Types.DECIMAL,Types.CHAR });

		            });
	           
	            
	        } catch (Exception e) {
	            logger.error(e);
	        }
		
	}


	@Override
	public void approveFINHaircut() {
		List<Map<String, Object>> rows=null;
		
		
		String query= "DELETE FROM mst_fin_haircut";
		getJdbcTemplate().update(query);
		
		query= "update temp_mst_fin_haircut set v_status='A'";
		getJdbcTemplate().update(query);
		
		query="SELECT V_FIN_SEC,N_HAIRCUT,N_REG_HAIRCUT FROM TEMP_mst_fin_haircut WHERE v_status is not null and v_status='P'";
    	rows = getJdbcTemplate().queryForList(query);
	        try {
	        	 rows.stream().forEach((row) -> {
	        		 String V_FIN_SEC=fmt.ToString(row.get("V_FIN_SEC"));
	        		 String N_HAIRCUT=fmt.ToString(row.get("N_HAIRCUT"));
	            	 String N_REG_HAIRCUT=fmt.ToString(row.get("N_REG_HAIRCUT"));
		             
	            	String qury="INSERT INTO mst_fin_haircut (V_FIN_SEC,N_HAIRCUT,N_REG_HAIRCUT) values(?,?,?)";
					getJdbcTemplate().update(qury, new Object[]{V_FIN_SEC,N_HAIRCUT,N_REG_HAIRCUT},new int[] {Types.VARCHAR,Types.DECIMAL,Types.DECIMAL });
					
		            });
	           
	            
	        } catch (Exception e) {
	            logger.error(e);
	        }
		
	}


	@Override
	public void approveRatingModelMapping() {
		List<Map<String, Object>> rows=null;
		
		
		String query= "DELETE FROM cnfg_rating_model_map";
		getJdbcTemplate().update(query);
		
		query= "update temp_cnfg_rating_model_map set v_status='A'";
		getJdbcTemplate().update(query);
		 
		query="SELECT V_SEGMENT,M_RDM_MODEL FROM TEMP_cnfg_rating_model_map WHERE v_status is not null and v_status='P'";
    	rows = getJdbcTemplate().queryForList(query);
	        try {
	        	 rows.stream().forEach((row) -> {
	        		 String V_SEGMENT=fmt.ToString(row.get("V_SEGMENT"));
	        		 String M_RDM_MODEL=fmt.ToString(row.get("M_RDM_MODEL"));
		             
	        		String  qury="INSERT INTO cnfg_rating_model_map (V_SEGMENT,M_RDM_MODEL) values(?,?)";
	 				getJdbcTemplate().update(qury, new Object[]{ V_SEGMENT,M_RDM_MODEL},new int[] {Types.VARCHAR,Types.VARCHAR });
		            });
	           
	            
	        } catch (Exception e) {
	            logger.error(e);
	        }
		
	}


	@Override
	public void approveInternalRatingMapping() {
		List<Map<String, Object>> rows=null;
		
		
		String query= "DELETE FROM cnfg_int_rat_mapping";
		getJdbcTemplate().update(query);
		
		query= "update TEMP_cnfg_int_rat_mapping set v_status='A'";
		 getJdbcTemplate().update(query);
		
		query="SELECT V_RDM_INT_RAT,V_INT_RATING FROM TEMP_cnfg_int_rat_mapping WHERE v_status is not null and v_status='P'";
    	rows = getJdbcTemplate().queryForList(query);
	        try {
	        	 rows.stream().forEach((row) -> {
	        		 String V_RDM_INT_RAT=fmt.ToString(row.get("V_RDM_INT_RAT"));
	        		 String V_INT_RATING=fmt.ToString(row.get("V_INT_RATING"));
		             
	        		 String qury="INSERT INTO cnfg_int_rat_mapping (V_RDM_INT_RAT,V_INT_RATING) values(?,?)";
	 				getJdbcTemplate().update(qury, new Object[]{V_RDM_INT_RAT,V_INT_RATING},new int[] {Types.VARCHAR,Types.VARCHAR });
	 				
		            });
	           
	            
	        } catch (Exception e) {
	            logger.error(e);
	        }
		
	}


	@Override
	public void approveBusinessUnit() {
		List<Map<String, Object>> rows=null;
		
		
		String query= "DELETE FROM MST_BUSINESS_UNIT";
		getJdbcTemplate().update(query);
		
		 query= "update TEMP_MST_BUSINESS_UNIT set v_status='A'";
		 getJdbcTemplate().update(query);
			
		query="SELECT V_CODE,V_DESC,F_LATEST_IND FROM TEMP_MST_BUSINESS_UNIT WHERE v_status is not null and v_status='P'";
    	rows = getJdbcTemplate().queryForList(query);
	        try {
	        	 rows.stream().forEach((row) -> {
	        		 String V_CODE=fmt.ToString(row.get("V_CODE"));
	        		 String V_DESC=fmt.ToString(row.get("V_DESC"));
	            	 String F_LATEST_IND=fmt.ToString(row.get("F_LATEST_IND"));
		             
	            		String qury="INSERT INTO MST_BUSINESS_UNIT (V_CODE,V_DESC,F_LATEST_IND) values (?,?,?)";
	    				getJdbcTemplate().update(qury, new Object[]{V_CODE,V_DESC,F_LATEST_IND},new int[] {Types.VARCHAR,Types.VARCHAR,Types.CHAR });
	    				
		            });
	           
	            
	        } catch (Exception e) {
	            logger.error(e);
	        }
		
	}

	@Override
	public List<RarocConfigModel> rarocMasterDoc() {
		List<RarocConfigModel> list = new ArrayList<>();
		
  		String query = "SELECT rownum rnum,V_CODE,D_MIS_DATE,N_CODE,N_VALUE FROM MST_RAROC WHERE V_CODE is not null ";           
    
  		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(query);
  		rows.stream().map((row) -> {
  			RarocConfigModel obj = new RarocConfigModel();
  			obj.setId(fmt.ToString(row.get("rnum")));
            obj.setCol1(fmt.ToString(row.get("V_CODE")));
            obj.setCol2(fmt.ToString(row.get("D_MIS_DATE")));
            obj.setCol3(fmt.ToString(row.get("N_CODE")));
            obj.setCol4(fmt.ToString(row.get("N_VALUE")));
          return obj;
  		}).forEach((obj) -> {
  			list.add(obj);
  		});

  		return list;
	}

	@Override
	public List<RarocConfigModel> internalRatingDoc() {
	List<RarocConfigModel> list = new ArrayList<>();
		
  		String query ="SELECT rownum rnum, V_INT_RATING, V_FORMAT_RATING,V_MAPPED_RATING, V_GRADE, V_RATING_TYPE,N_INT_RATING_LIMIT,N_PD,N_LGD,N_PD_NPA,N_LGD_NPA,N_EL,V_MODEL,N_RANK ,V_FORMATED_RATING_LCMC_RD,V_FORMATED_RATING_SME_RD,V_FORMATED_RATING_RD,V_FORMATED_RATING_OSMOS,N_TENURE,F_ELIGIBLE_GUARANTOR,V_GUARANTOR_TYPE,V_MAPPED_EXT_RATING FROM mast_internal_rating WHERE V_INT_RATING is not null";
  		
  		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(query);
  		rows.stream().map((row) -> {
  			RarocConfigModel obj = new RarocConfigModel();
  			obj.setId(fmt.ToString(row.get("rnum")));
        	obj.setCol1(fmt.ToString(row.get("V_INT_RATING")));
        	obj.setCol2(fmt.ToString(row.get("V_FORMAT_RATING")));
        	obj.setCol3(fmt.ToString(row.get("V_MAPPED_RATING")));
        	obj.setCol4(fmt.ToString(row.get("V_GRADE")));
        	obj.setCol5(fmt.ToString(row.get("V_RATING_TYPE")));
        	obj.setCol6(fmt.ToString(row.get("N_INT_RATING_LIMIT")));
        	obj.setCol7(fmt.ToString(row.get("N_PD")));
        	obj.setCol8(fmt.ToString(row.get("N_LGD")));
        	obj.setCol9(fmt.ToString(row.get("N_PD_NPA")));
        	obj.setCol10(fmt.ToString(row.get("N_LGD_NPA")));
        	obj.setCol11(fmt.ToString(row.get("N_EL")));
        	obj.setCol12(fmt.ToString(row.get("V_MODEL")));
        	obj.setCol13(fmt.ToString(row.get("N_RANK")));
        	obj.setCol14(fmt.ToString(row.get("V_FORMATED_RATING_LCMC_RD")));
        	obj.setCol15(fmt.ToString(row.get("V_FORMATED_RATING_SME_RD")));
        	obj.setCol16(fmt.ToString(row.get("V_FORMATED_RATING_RD")));
        	obj.setCol17(fmt.ToString(row.get("V_FORMATED_RATING_OSMOS")));
        	obj.setCol18(fmt.ToString(row.get("N_TENURE")));
        	obj.setCol19(fmt.ToString(row.get("F_ELIGIBLE_GUARANTOR")));
        	obj.setCol20(fmt.ToString(row.get("V_GUARANTOR_TYPE")));
        	obj.setCol21(fmt.ToString(row.get("V_MAPPED_EXT_RATING")));
  			return obj;
  		}).forEach((obj) -> {
  			list.add(obj);
  		});

  		return list;
	}

	@Override
	public List<RarocConfigModel> externalRatingDoc() {
List<RarocConfigModel> list = new ArrayList<>();
		
  		String query = "SELECT rownum rnum,V_RATING_CODE,V_GRADE,V_RANK,F_TERM_FLAG,N_RISK_WEIGHT,N_W,V_GUARANTOR_TYPE FROM mast_external_rating WHERE V_RATING_CODE is not null"; 
  		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(query);
  		rows.stream().map((row) -> {
  			RarocConfigModel obj = new RarocConfigModel();
  			 obj.setId(fmt.ToString(row.get("rnum")));
	        	obj.setCol1(fmt.ToString(row.get("V_RATING_CODE")));
	        	obj.setCol2(fmt.ToString(row.get("V_GRADE")));
	        	obj.setCol3(fmt.ToString(row.get("V_RANK")));
	        	obj.setCol4(fmt.ToString(row.get("F_TERM_FLAG")));
	        	obj.setCol5(fmt.ToString(row.get("N_RISK_WEIGHT")));
	        	obj.setCol6(fmt.ToString(row.get("N_W")));
	        	obj.setCol7(fmt.ToString(row.get("V_GUARANTOR_TYPE")));
	        	
  			return obj;
  		}).forEach((obj) -> {
  			list.add(obj);
  		});

  		return list;
	}
	

	@Override
	public List<RarocConfigModel> guarantorMasterDoc() {
List<RarocConfigModel> list = new ArrayList<>();
		
  		String query = "SELECT rownum rnum,V_CODE,N_VALUE,V_MAPPING_COL FROM MST_GUARANTOR WHERE V_CODE is not null";
  		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(query);
  		rows.stream().map((row) -> {
  			RarocConfigModel obj = new RarocConfigModel();
  		     obj.setId(fmt.ToString(row.get("rnum")));
  		     obj.setCol1(fmt.ToString(row.get("V_CODE")));
  		     obj.setCol2(fmt.ToString(row.get("N_VALUE")));
  		     obj.setCol3(fmt.ToString(row.get("V_MAPPING_COL")));
  			return obj;
  		}).forEach((obj) -> {
  			list.add(obj);
  		});

  		return list;
	}

	@Override
	public List<RarocConfigModel> othIncomeMasterDoc() {
List<RarocConfigModel> list = new ArrayList<>();
		
  		String query = "SELECT rownum rnum,OTH_INCOME_TYPE,CI_RATIO,TNFR_RATE FROM MST_OTH_INCOME WHERE OTH_INCOME_TYPE is not null";
  		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(query);
  		rows.stream().map((row) -> {
  			RarocConfigModel obj = new RarocConfigModel();
  			obj.setId(fmt.ToString(row.get("rnum")));
        	obj.setCol1(fmt.ToString(row.get("OTH_INCOME_TYPE")));
        	obj.setCol2(fmt.ToString(row.get("CI_RATIO")));
        	obj.setCol3(fmt.ToString(row.get("TNFR_RATE")));
  			return obj;
  		}).forEach((obj) -> {
  			list.add(obj);
  		});

  		return list;	}

	@Override
	public List<RarocConfigModel> cCFMasterDoc() {
		List<RarocConfigModel> list = new ArrayList<>();
		
  		String query = "SELECT rownum rnum,V_CODE,V_FACILITY_TYPE,N_LIMIT_LOWER,N_LIMIT_UPPER,N_UTILIZATION_LOWER,N_UTILIZATION_UPPER,N_DRAWN_CCF,N_UNDRAWN_CCF FROM MST_CCF WHERE V_CODE is not null"; 
  		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(query);
  		rows.stream().map((row) -> {
  			RarocConfigModel obj = new RarocConfigModel();
  		   obj.setId(fmt.ToString(row.get("rnum")));
	       	obj.setCol1(fmt.ToString(row.get("V_CODE")));
	       	obj.setCol2(fmt.ToString(row.get("V_FACILITY_TYPE")));
	       	obj.setCol3(fmt.ToString(row.get("N_LIMIT_LOWER")));
	       	obj.setCol4(fmt.ToString(row.get("N_LIMIT_UPPER")));
	       	obj.setCol5(fmt.ToString(row.get("N_UTILIZATION_LOWER")));
	       	obj.setCol6(fmt.ToString(row.get("N_UTILIZATION_UPPER")));
	       	obj.setCol7(fmt.ToString(row.get("N_DRAWN_CCF")));
	       	obj.setCol8(fmt.ToString(row.get("N_UNDRAWN_CCF")));
  			return obj;
  		}).forEach((obj) -> {
  			list.add(obj);
  		});

  		return list;
	}

	@Override
	public List<RarocConfigModel> operatingExpenseMasterDoc() {
		List<RarocConfigModel> list = new ArrayList<>();
		
  		String query = "SELECT rownum rnum,V_FACILITY,V_SEGMENT,N_LOWER_LEVEL,N_UPPER_LEVEL,V_ID,N_FIXED_COST,N_VARIABLE_COST,V_REGION FROM MST_OPERATING_EXPENSE WHERE V_FACILITY is not null";
  		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(query);
  		rows.stream().map((row) -> {
  			RarocConfigModel obj = new RarocConfigModel();
  			 obj.setId(fmt.ToString(row.get("rnum")));
	        	obj.setCol1(fmt.ToString(row.get("V_FACILITY")));
	        	obj.setCol2(fmt.ToString(row.get("V_SEGMENT")));
	        	obj.setCol3(fmt.ToString(row.get("N_LOWER_LEVEL")));
	        	obj.setCol4(fmt.ToString(row.get("N_UPPER_LEVEL")));
	        	obj.setCol5(fmt.ToString(row.get("V_ID")));
	        	obj.setCol6(fmt.ToString(row.get("N_FIXED_COST")));
	        	obj.setCol7(fmt.ToString(row.get("N_VARIABLE_COST")));
	        	obj.setCol8(fmt.ToString(row.get("V_REGION")));
  			return obj;
  		}).forEach((obj) -> {
  			list.add(obj);
  		});

  		return list;
	}

	@Override
	public List<RarocConfigModel> restructuredMasterDoc() {
		List<RarocConfigModel> list = new ArrayList<>();
		
  		String query = "SELECT rownum rnum,V_CODE,N_RW,N_EL FROM MST_RESTRUCTURED_RW WHERE V_CODE is not null";            
    
  		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(query);
  		rows.stream().map((row) -> {
  			RarocConfigModel obj = new RarocConfigModel();
  			 obj.setId(fmt.ToString(row.get("rnum")));
	            obj.setCol1(fmt.ToString(row.get("V_CODE")));
	            obj.setCol2(fmt.ToString(row.get("N_RW")));
	            obj.setCol3(fmt.ToString(row.get("N_EL")));
	            
  			return obj;
  		}).forEach((obj) -> {
  			list.add(obj);
  		});

  		return list;
	}

	@Override
	public List<RarocConfigModel> sensitivityIterationMasterDoc() {
		List<RarocConfigModel> list = new ArrayList<>();
		
  		String query = "SELECT rownum rnum,N_VALUE,V_TYPE FROM MST_SENSITIVITY_ITERATIONS WHERE V_TYPE is not null";           
    
  		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(query);
  		rows.stream().map((row) -> {
  			RarocConfigModel obj = new RarocConfigModel();
  			 obj.setId(fmt.ToString(row.get("rnum")));
	            obj.setCol1(fmt.ToString(row.get("N_VALUE")));
	            obj.setCol2(fmt.ToString(row.get("V_TYPE")));
  			return obj;
  		}).forEach((obj) -> {
  			list.add(obj);
  		});

  		return list;
	}

	@Override
	public List<RarocConfigModel> assetTypeDoc() {
		List<RarocConfigModel> list = new ArrayList<>();
		
  		String query = "SELECT rownum rnum,V_CODE,V_DESC,N_VALUE,V_GUAR_FLAG FROM MST_ASSET_TYPE WHERE V_CODE is not null ";           
    
  		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(query);
  		rows.stream().map((row) -> {
  			RarocConfigModel obj = new RarocConfigModel();
  			 obj.setId(fmt.ToString(row.get("rnum")));
	            obj.setCol1(fmt.ToString(row.get("V_CODE")));
	            obj.setCol2(fmt.ToString(row.get("V_DESC")));
	            obj.setCol3(fmt.ToString(row.get("N_VALUE")));
	            obj.setCol4(fmt.ToString(row.get("V_GUAR_FLAG")));
  			return obj;
  		}).forEach((obj) -> {
  			list.add(obj);
  		});

  		return list;
	}

	@Override
	public List<RarocConfigModel> businessUnitDoc() {
		List<RarocConfigModel> list = new ArrayList<>();
		String query = "SELECT rownum rnum,V_CODE,V_DESC,F_LATEST_IND FROM MST_BUSINESS_UNIT WHERE V_CODE is not null ";           
	    
  		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(query);
  		rows.stream().map((row) -> {
  			RarocConfigModel obj = new RarocConfigModel();
  			 obj.setId(fmt.ToString(row.get("rnum")));
  			obj.setCol1(fmt.ToString(row.get("V_CODE")));
            obj.setCol2(fmt.ToString(row.get("V_DESC")));
            obj.setCol3(fmt.ToString(row.get("F_LATEST_IND")));
  			return obj;
  		}).forEach((obj) -> {
  			list.add(obj);
  		});

  		return list;
	}

	@Override
	public List<RarocConfigModel> finHaircutDoc() {
		List<RarocConfigModel> list = new ArrayList<>();
		String query = "SELECT rownum rnum,V_FIN_SEC,N_HAIRCUT,N_REG_HAIRCUT FROM mst_fin_haircut WHERE V_FIN_SEC is not null ";           
	    
  		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(query);
  		rows.stream().map((row) -> {
  			RarocConfigModel obj = new RarocConfigModel();
  			 obj.setId(fmt.ToString(row.get("rnum")));
  			obj.setCol1(fmt.ToString(row.get("V_FIN_SEC")));
            obj.setCol2(fmt.ToString(row.get("N_HAIRCUT")));
            obj.setCol3(fmt.ToString(row.get("N_REG_HAIRCUT")));
  			return obj;
  		}).forEach((obj) -> {
  			list.add(obj);
  		});

  		return list;
	}

	@Override
	public List<RarocConfigModel> internalRatingModelDoc() {
		List<RarocConfigModel> list = new ArrayList<>();
		String query = "SELECT rownum rnum,V_RDM_INT_RAT,V_INT_RATING FROM cnfg_int_rat_mapping WHERE V_RDM_INT_RAT is not null ";           
	    
  		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(query);
  		rows.stream().map((row) -> {
  			RarocConfigModel obj = new RarocConfigModel();
  			 obj.setId(fmt.ToString(row.get("rnum")));
	            obj.setCol1(fmt.ToString(row.get("V_RDM_INT_RAT")));
	            obj.setCol2(fmt.ToString(row.get("V_INT_RATING")));
  			return obj;
  		}).forEach((obj) -> {
  			list.add(obj);
  		});

  		return list;
	}

	@Override
	public List<RarocConfigModel> ratingModelMappingDoc() {
		List<RarocConfigModel> list = new ArrayList<>();
		String query = "SELECT rownum rnum, V_SEGMENT,M_RDM_MODEL FROM cnfg_rating_model_map WHERE V_SEGMENT is not null ";           
	    
  		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(query);
  		rows.stream().map((row) -> {
  			RarocConfigModel obj = new RarocConfigModel();
  			obj.setId(fmt.ToString(row.get("rnum")));
            obj.setCol1(fmt.ToString(row.get("V_SEGMENT")));
            obj.setCol2(fmt.ToString(row.get("M_RDM_MODEL")));
  			return obj;
  		}).forEach((obj) -> {
  			list.add(obj);
  		});

  		return list;
	}

	@Override
	public GridPage<RarocConfigModel> listCNFGInternalRating(int page, int max, String sidx, String sord,
			String searchField, String searchOper, String searchString, String userid)
			throws CustomException {
		 List<String> columns = Collections.unmodifiableList(Arrays.asList("V_INT_RATING", "V_FORMAT_RATING","V_MAPPED_RATING", "V_GRADE", "V_RATING_TYPE","N_INT_RATING_LIMIT","N_PD","N_LGD","N_PD_NPA","N_LGD_NPA","N_EL","V_MODEL","N_RANK ","V_FORMATED_RATING_LCMC_RD","V_FORMATED_RATING_SME_RD","V_FORMATED_RATING_RD","V_FORMATED_RATING_OSMOS","N_TENURE","F_ELIGIBLE_GUARANTOR","V_GUARANTOR_TYPE","V_MAPPED_EXT_RATING"));

	        List<String> orders = Collections.unmodifiableList(Arrays.asList("asc", "desc"));

	        if (sidx == null || sidx.isEmpty()) {
	            sidx = "V_INT_RATING";
	        }
	        if (sord == null || sord.isEmpty()) {
	            sord = "desc";
	        }

	        //check if sidx is in columns 
	        if (!columns.contains(sidx)) {
	            throw new CustomException();
	        }

	        if (!orders.contains(sord)) {
	            throw new CustomException();
	        }

	        QueryBuilderModel qObj = queryBuilder.SearchAnd(searchOper, searchField, searchString, columns);
	        String query = "SELECT count(*) FROM mast_internal_rating WHERE V_INT_RATING is not null " +qObj.getCondition()  ;
	        int rowCount = getJdbcTemplate().queryForObject(query, new Object[]{qObj.getRegex()}, Integer.class);
	        final int startIdx = ((page - 1) * max) + 1;
	        final int endIdx = Math.min(startIdx + max, rowCount);
	        query = " SELECT * FROM "
	        		+"( SELECT a.*, rownum rnum FROM "
	        		+"(SELECT V_INT_RATING, V_FORMAT_RATING,V_MAPPED_RATING, V_GRADE, V_RATING_TYPE,N_INT_RATING_LIMIT,N_PD,N_LGD,N_PD_NPA,N_LGD_NPA,N_EL,V_MODEL,N_RANK ,V_FORMATED_RATING_LCMC_RD,V_FORMATED_RATING_SME_RD,V_FORMATED_RATING_RD,V_FORMATED_RATING_OSMOS,N_TENURE,F_ELIGIBLE_GUARANTOR,V_GUARANTOR_TYPE,V_MAPPED_EXT_RATING FROM mast_internal_rating "
	                + "WHERE V_INT_RATING is not null "
	                + qObj.getCondition() + " "
	                + "ORDER BY " + sidx + " " + sord + ")a"
	         + " WHERE rownum <= ?) WHERE rnum >= ?";
     List<RarocConfigModel> lists = new ArrayList<>();
     List<Map<String, Object>> rows = getJdbcTemplate().queryForList(query, new Object[]{ qObj.getRegex(),endIdx, startIdx});
	        rows.stream().map((row) -> {
	        	RarocConfigModel obj = new RarocConfigModel();
	            obj.setId(fmt.ToString(row.get("rnum")));
	        	obj.setCol1(fmt.ToString(row.get("V_INT_RATING")));
	        	obj.setCol2(fmt.ToString(row.get("V_FORMAT_RATING")));
	        	obj.setCol3(fmt.ToString(row.get("V_MAPPED_RATING")));
	        	obj.setCol4(fmt.ToString(row.get("V_GRADE")));
	        	obj.setCol5(fmt.ToString(row.get("V_RATING_TYPE")));
	        	obj.setCol6(fmt.ToString(row.get("N_INT_RATING_LIMIT")));
	        	obj.setCol7(fmt.ToString(row.get("N_PD")));
	        	obj.setCol8(fmt.ToString(row.get("N_LGD")));
	        	obj.setCol9(fmt.ToString(row.get("N_PD_NPA")));
	        	obj.setCol10(fmt.ToString(row.get("N_LGD_NPA")));
	        	obj.setCol11(fmt.ToString(row.get("N_EL")));
	        	obj.setCol12(fmt.ToString(row.get("V_MODEL")));
	        	obj.setCol13(fmt.ToString(row.get("N_RANK")));
	        	obj.setCol14(fmt.ToString(row.get("V_FORMATED_RATING_LCMC_RD")));
	        	obj.setCol15(fmt.ToString(row.get("V_FORMATED_RATING_SME_RD")));
	        	obj.setCol16(fmt.ToString(row.get("V_FORMATED_RATING_RD")));
	        	obj.setCol17(fmt.ToString(row.get("V_FORMATED_RATING_OSMOS")));
	        	obj.setCol18(fmt.ToString(row.get("N_TENURE")));
	        	obj.setCol19(fmt.ToString(row.get("F_ELIGIBLE_GUARANTOR")));
	        	obj.setCol20(fmt.ToString(row.get("V_GUARANTOR_TYPE")));
	        	obj.setCol21(fmt.ToString(row.get("V_MAPPED_EXT_RATING")));

	            return obj;
	        }).forEach((obj) -> {
	            lists.add(obj);
	        });
	        return new GridPage<>(lists, page, max, rowCount);

	}

	@Override
	public GridPage<RarocConfigModel> listCNFGExternalRating(int page, int max, String sidx, String sord,
			String searchField, String searchOper, String searchString, String userid)
			throws CustomException {
		 List<String> columns = Collections.unmodifiableList(Arrays.asList("V_RATING_CODE","V_GRADE","V_RANK","F_TERM_FLAG","N_RISK_WEIGHT","N_W","V_GUARANTOR_TYPE"));

	        List<String> orders = Collections.unmodifiableList(Arrays.asList("asc", "desc"));

	        if (sidx == null || sidx.isEmpty()) {
	            sidx = "V_RATING_CODE";
	        }
	        if (sord == null || sord.isEmpty()) {
	            sord = "desc";
	        }

	        //check if sidx is in columns 
	        if (!columns.contains(sidx)) {
	            throw new CustomException();
	        }

	        if (!orders.contains(sord)) {
	            throw new CustomException();
	        }

	        QueryBuilderModel qObj = queryBuilder.SearchAnd(searchOper, searchField, searchString, columns);
	        String query = "SELECT count(*) FROM mast_external_rating WHERE V_RATING_CODE is not null " +qObj.getCondition()  ;
	        int rowCount = getJdbcTemplate().queryForObject(query, new Object[]{qObj.getRegex()}, Integer.class);
	        final int startIdx = ((page - 1) * max) + 1;
	        final int endIdx = Math.min(startIdx + max, rowCount);
	        query = " SELECT * FROM "
	        		+"( SELECT a.*, rownum rnum FROM "
	        		+"(SELECT V_RATING_CODE,V_GRADE,V_RANK,F_TERM_FLAG,N_RISK_WEIGHT,N_W,V_GUARANTOR_TYPE FROM mast_external_rating "
	                + "WHERE V_RATING_CODE is not null "
	                + qObj.getCondition() + " "
	                + "ORDER BY " + sidx + " " + sord + ")a"
	         + " WHERE rownum <= ?) WHERE rnum >= ?";
     List<RarocConfigModel> lists = new ArrayList<>();
     List<Map<String, Object>> rows = getJdbcTemplate().queryForList(query, new Object[]{ qObj.getRegex(),endIdx, startIdx});
	        rows.stream().map((row) -> {
	        	RarocConfigModel obj = new RarocConfigModel();
	            obj.setId(fmt.ToString(row.get("rnum")));
	        	obj.setCol1(fmt.ToString(row.get("V_RATING_CODE")));
	        	obj.setCol2(fmt.ToString(row.get("V_GRADE")));
	        	obj.setCol3(fmt.ToString(row.get("V_RANK")));
	        	obj.setCol4(fmt.ToString(row.get("F_TERM_FLAG")));
	        	obj.setCol5(fmt.ToString(row.get("N_RISK_WEIGHT")));
	        	obj.setCol6(fmt.ToString(row.get("N_W")));
	        	obj.setCol7(fmt.ToString(row.get("V_GUARANTOR_TYPE")));
	            return obj;
	        }).forEach((obj) -> {
	            lists.add(obj);
	        });
	        return new GridPage<>(lists, page, max, rowCount);

	}

	@Override
	public GridPage<RarocConfigModel> listCNFGGuarantorMaster(int page, int max, String sidx, String sord,
			String searchField, String searchOper, String searchString, String userid)
			throws CustomException {
		List<String> columns = Collections.unmodifiableList(Arrays.asList("V_CODE", "N_VALUE",
                "V_MAPPING_COL"));

        List<String> orders = Collections.unmodifiableList(Arrays.asList("asc", "desc"));

        if (sidx == null || sidx.isEmpty()) {
            sidx = "V_CODE";
        }
        if (sord == null || sord.isEmpty()) {
            sord = "desc";
        }

        //check if sidx is in columns 
        if (!columns.contains(sidx)) {
            throw new CustomException();
        }

        if (!orders.contains(sord)) {
            throw new CustomException();
        }

        QueryBuilderModel qObj = queryBuilder.SearchAnd(searchOper, searchField, searchString, columns);
        String query = "SELECT count(*) FROM MST_GUARANTOR WHERE V_CODE is not null " +qObj.getCondition()  ;
        int rowCount = getJdbcTemplate().queryForObject(query, new Object[]{qObj.getRegex()}, Integer.class);
        final int startIdx = ((page - 1) * max) + 1;
        final int endIdx = Math.min(startIdx + max, rowCount);
        query = " SELECT * FROM "
        		+"( SELECT a.*, rownum rnum FROM "
        		+"(SELECT V_CODE,N_VALUE,V_MAPPING_COL FROM MST_GUARANTOR "
                + "WHERE V_CODE is not null "
                + qObj.getCondition() + " "
                + "ORDER BY " + sidx + " " + sord + ")a"
         + " WHERE rownum <= ?) WHERE rnum >= ?";
    List<RarocConfigModel> lists = new ArrayList<>();
    List<Map<String, Object>> rows = getJdbcTemplate().queryForList(query, new Object[]{ qObj.getRegex(),endIdx, startIdx});
        rows.stream().map((row) -> {
        	RarocConfigModel obj = new RarocConfigModel();
            obj.setId(fmt.ToString(row.get("rnum")));
        	obj.setCol1(fmt.ToString(row.get("V_CODE")));
        	obj.setCol2(fmt.ToString(row.get("N_VALUE")));
        	obj.setCol3(fmt.ToString(row.get("V_MAPPING_COL")));
            return obj;
        }).forEach((obj) -> {
            lists.add(obj);
        });
        return new GridPage<>(lists, page, max, rowCount);

	}

	@Override
	public GridPage<RarocConfigModel> listCNFGOthIncomeMaster(int page, int max, String sidx, String sord,
			String searchField, String searchOper, String searchString, String userid)
			throws CustomException {
		 List<String> columns = Collections.unmodifiableList(Arrays.asList("OTH_INCOME_TYPE","CI_RATIO",
	                "TNFR_RATE"));

	        List<String> orders = Collections.unmodifiableList(Arrays.asList("asc", "desc"));

	        if (sidx == null || sidx.isEmpty()) {
	            sidx = "OTH_INCOME_TYPE";
	        }
	        if (sord == null || sord.isEmpty()) {
	            sord = "desc";
	        }

	        //check if sidx is in columns 
	        if (!columns.contains(sidx)) {
	            throw new CustomException();
	        }

	        if (!orders.contains(sord)) {
	            throw new CustomException();
	        }

	        QueryBuilderModel qObj = queryBuilder.SearchAnd(searchOper, searchField, searchString, columns);
	        String query = "SELECT count(*) FROM MST_OTH_INCOME WHERE OTH_INCOME_TYPE is not null " +qObj.getCondition()  ;
	        int rowCount = getJdbcTemplate().queryForObject(query, new Object[]{qObj.getRegex()}, Integer.class);
	        final int startIdx = ((page - 1) * max) + 1;
	        final int endIdx = Math.min(startIdx + max, rowCount);
	        query = " SELECT * FROM "
	        		+"( SELECT a.*, rownum rnum FROM "
	        		+"(SELECT OTH_INCOME_TYPE,CI_RATIO,TNFR_RATE FROM MST_OTH_INCOME "
	                + "WHERE OTH_INCOME_TYPE is not null "
	                + qObj.getCondition() + " "
	                + "ORDER BY " + sidx + " " + sord + ")a"
	         + " WHERE rownum <= ?) WHERE rnum >= ?";
     List<RarocConfigModel> lists = new ArrayList<>();
     List<Map<String, Object>> rows = getJdbcTemplate().queryForList(query, new Object[]{ qObj.getRegex(),endIdx, startIdx});
	        rows.stream().map((row) -> {
	        	RarocConfigModel obj = new RarocConfigModel();
	            obj.setId(fmt.ToString(row.get("rnum")));
	            obj.setCol1(fmt.ToString(row.get("OTH_INCOME_TYPE")));
	        	obj.setCol2(fmt.ToString(row.get("CI_RATIO")));
	        	obj.setCol3(fmt.ToString(row.get("TNFR_RATE")));
	            return obj;
	        }).forEach((obj) -> {
	            lists.add(obj);
	        });
	        return new GridPage<>(lists, page, max, rowCount);

	}

	@Override
	public GridPage<RarocConfigModel> listCNFGCCFMaster(int page, int max, String sidx, String sord, String searchField,
			String searchOper, String searchString, String userid) throws CustomException {
		 List<String> columns = Collections.unmodifiableList(Arrays.asList("V_CODE","V_FACILITY_TYPE","N_LIMIT_LOWER","N_LIMIT_UPPER","N_UTILIZATION_LOWER","N_UTILIZATION_UPPER","N_DRAWN_CCF","N_UNDRAWN_CCF"));

	        List<String> orders = Collections.unmodifiableList(Arrays.asList("asc", "desc"));

	        if (sidx == null || sidx.isEmpty()) {
	            sidx = "V_CODE";
	        }
	        if (sord == null || sord.isEmpty()) {
	            sord = "desc";
	        }

	        //check if sidx is in columns 
	        if (!columns.contains(sidx)) {
	            throw new CustomException();
	        }

	        if (!orders.contains(sord)) {
	            throw new CustomException();
	        }

	        QueryBuilderModel qObj = queryBuilder.SearchAnd(searchOper, searchField, searchString, columns);
	        String query = "SELECT count(*) FROM MST_CCF WHERE V_CODE is not null " +qObj.getCondition()  ;
	        int rowCount = getJdbcTemplate().queryForObject(query, new Object[]{qObj.getRegex()}, Integer.class);
	        final int startIdx = ((page - 1) * max) + 1;
	        final int endIdx = Math.min(startIdx + max, rowCount);
	        query = " SELECT * FROM "
	        		+"( SELECT a.*, rownum rnum FROM "
	           		+"(SELECT V_CODE,V_FACILITY_TYPE,N_LIMIT_LOWER,N_LIMIT_UPPER,N_UTILIZATION_LOWER,N_UTILIZATION_UPPER,N_DRAWN_CCF,N_UNDRAWN_CCF FROM MST_CCF "
	                + "WHERE V_CODE is not null "
	                + qObj.getCondition() + " "
	                + "ORDER BY " + sidx + " " + sord + ")a"
	         + " WHERE rownum <= ?) WHERE rnum >= ?";
     List<RarocConfigModel> lists = new ArrayList<>();
     List<Map<String, Object>> rows = getJdbcTemplate().queryForList(query, new Object[]{ qObj.getRegex(),endIdx, startIdx});
	        rows.stream().map((row) -> {
	        	RarocConfigModel obj = new RarocConfigModel();
	            obj.setId(fmt.ToString(row.get("rnum")));
	        	obj.setCol1(fmt.ToString(row.get("V_CODE")));
	        	obj.setCol2(fmt.ToString(row.get("V_FACILITY_TYPE")));
	        	obj.setCol3(fmt.ToString(row.get("N_LIMIT_LOWER")));
	        	obj.setCol4(fmt.ToString(row.get("N_LIMIT_UPPER")));
	        	obj.setCol5(fmt.ToString(row.get("N_UTILIZATION_LOWER")));
	        	obj.setCol6(fmt.ToString(row.get("N_UTILIZATION_UPPER")));
	        	obj.setCol7(fmt.ToString(row.get("N_DRAWN_CCF")));
	        	obj.setCol8(fmt.ToString(row.get("N_UNDRAWN_CCF")));

	            return obj;
	        }).forEach((obj) -> {
	            lists.add(obj);
	        });
	        return new GridPage<>(lists, page, max, rowCount);

	}

	@Override
	public GridPage<RarocConfigModel> listCNFGOperatingExpenseMaster(int page, int max, String sidx, String sord,
			String searchField, String searchOper, String searchString, String userid)
			throws CustomException {
		 List<String> columns = Collections.unmodifiableList(Arrays.asList("V_FACILITY","V_SEGMENT","N_LOWER_LEVEL","N_UPPER_LEVEL","V_ID","N_FIXED_COST","N_VARIABLE_COST","V_REGION"));

	        List<String> orders = Collections.unmodifiableList(Arrays.asList("asc", "desc"));

	        if (sidx == null || sidx.isEmpty()) {
	            sidx = "V_FACILITY";
	        }
	        if (sord == null || sord.isEmpty()) {
	            sord = "desc";
	        }

	        //check if sidx is in columns 
	        if (!columns.contains(sidx)) {
	            throw new CustomException();
	        }

	        if (!orders.contains(sord)) {
	            throw new CustomException();
	        }

	        QueryBuilderModel qObj = queryBuilder.SearchAnd(searchOper, searchField, searchString, columns);
	        String query = "SELECT count(*) FROM MST_OPERATING_EXPENSE WHERE V_FACILITY is not null " +qObj.getCondition()  ;
	        int rowCount = getJdbcTemplate().queryForObject(query, new Object[]{qObj.getRegex()}, Integer.class);
	        final int startIdx = ((page - 1) * max) + 1;
	        final int endIdx = Math.min(startIdx + max, rowCount);
	        query = " SELECT * FROM "
	        		+"( SELECT a.*, rownum rnum FROM "
	           		+"(SELECT V_FACILITY,V_SEGMENT,N_LOWER_LEVEL,N_UPPER_LEVEL,V_ID,N_FIXED_COST,N_VARIABLE_COST,V_REGION FROM MST_OPERATING_EXPENSE "
	                + "WHERE V_FACILITY is not null "
	                + qObj.getCondition() + " "
	                + "ORDER BY " + sidx + " " + sord + ")a"
	         + " WHERE rownum <= ?) WHERE rnum >= ?";
     List<RarocConfigModel> lists = new ArrayList<>();
     List<Map<String, Object>> rows = getJdbcTemplate().queryForList(query, new Object[]{ qObj.getRegex(),endIdx, startIdx});
	        rows.stream().map((row) -> {
	        	RarocConfigModel obj = new RarocConfigModel();
	            obj.setId(fmt.ToString(row.get("rnum")));
	        	obj.setCol1(fmt.ToString(row.get("V_FACILITY")));
	        	obj.setCol2(fmt.ToString(row.get("V_SEGMENT")));
	        	obj.setCol3(fmt.ToString(row.get("N_LOWER_LEVEL")));
	        	obj.setCol4(fmt.ToString(row.get("N_UPPER_LEVEL")));
	        	obj.setCol5(fmt.ToString(row.get("V_ID")));
	        	obj.setCol6(fmt.ToString(row.get("N_FIXED_COST")));
	        	obj.setCol7(fmt.ToString(row.get("N_VARIABLE_COST")));
	        	obj.setCol8(fmt.ToString(row.get("V_REGION")));
	            return obj;
	        }).forEach((obj) -> {
	            lists.add(obj);
	        });
	        return new GridPage<>(lists, page, max, rowCount);

	}

	@Override
	public GridPage<RarocConfigModel> listCNFGRestructuredMaster(int page, int max, String sidx, String sord,
			String searchField, String searchOper, String searchString, String userid)
			throws CustomException {
		 List<String> columns = Collections.unmodifiableList(Arrays.asList("V_CODE", "N_RW",
	                "N_EL"));

	        List<String> orders = Collections.unmodifiableList(Arrays.asList("asc", "desc"));

	        if (sidx == null || sidx.isEmpty()) {
	            sidx = "V_CODE";
	        }
	        if (sord == null || sord.isEmpty()) {
	            sord = "desc";
	        }

	        //check if sidx is in columns 
	        if (!columns.contains(sidx)) {
	            throw new CustomException();
	        }

	        if (!orders.contains(sord)) {
	            throw new CustomException();
	        }

	        QueryBuilderModel qObj = queryBuilder.SearchAnd(searchOper, searchField, searchString, columns);
	        String query = "SELECT count(*) FROM MST_RESTRUCTURED_RW WHERE V_CODE is not null " +qObj.getCondition()  ;
	        int rowCount = getJdbcTemplate().queryForObject(query, new Object[]{qObj.getRegex()}, Integer.class);
	        final int startIdx = ((page - 1) * max) + 1;
	        final int endIdx = Math.min(startIdx + max, rowCount);
	        query = " SELECT * FROM "
	        		+"( SELECT a.*, rownum rnum FROM "
	             	+"(SELECT V_CODE,N_RW,N_EL FROM MST_RESTRUCTURED_RW "
	                + "WHERE V_CODE is not null "
	                + qObj.getCondition() + " "
	                + "ORDER BY " + sidx + " " + sord + ")a"
	         + " WHERE rownum <= ?) WHERE rnum >= ?";
     List<RarocConfigModel> lists = new ArrayList<>();
     List<Map<String, Object>> rows = getJdbcTemplate().queryForList(query, new Object[]{ qObj.getRegex(),endIdx, startIdx});
	        rows.stream().map((row) -> {
	        	RarocConfigModel obj = new RarocConfigModel();
	            obj.setId(fmt.ToString(row.get("rnum")));
	            obj.setCol1(fmt.ToString(row.get("V_CODE")));
	            obj.setCol2(fmt.ToString(row.get("N_RW")));
	            obj.setCol3(fmt.ToString(row.get("N_EL")));
	            
	            return obj;
	        }).forEach((obj) -> {
	            lists.add(obj);
	        });
	        return new GridPage<>(lists, page, max, rowCount);

	}

	@Override
	public GridPage<RarocConfigModel> listCNFGSensitivityIterationMaster(int page, int max, String sidx, String sord,
			String searchField, String searchOper, String searchString, String userid)
			throws CustomException {
		 List<String> columns = Collections.unmodifiableList(Arrays.asList("N_VALUE", "V_TYPE"));

	        List<String> orders = Collections.unmodifiableList(Arrays.asList("asc", "desc"));

	        if (sidx == null || sidx.isEmpty()) {
	            sidx = "N_VALUE";
	        }
	        if (sord == null || sord.isEmpty()) {
	            sord = "desc";
	        }

	        //check if sidx is in columns 
	        if (!columns.contains(sidx)) {
	            throw new CustomException();
	        }

	        if (!orders.contains(sord)) {
	            throw new CustomException();
	        }

	        QueryBuilderModel qObj = queryBuilder.SearchAnd(searchOper, searchField, searchString, columns);
	        String query = "SELECT count(*) FROM MST_SENSITIVITY_ITERATIONS WHERE N_VALUE is not null " +qObj.getCondition()  ;
	        int rowCount = getJdbcTemplate().queryForObject(query, new Object[]{qObj.getRegex()}, Integer.class);
	        final int startIdx = ((page - 1) * max) + 1;
	        final int endIdx = Math.min(startIdx + max, rowCount);
	        query = " SELECT * FROM "
	        		+"( SELECT a.*, rownum rnum FROM "
	           		+"(SELECT N_VALUE,V_TYPE FROM MST_SENSITIVITY_ITERATIONS "
	                + "WHERE N_VALUE is not null "
	                + qObj.getCondition() + " "
	                + "ORDER BY " + sidx + " " + sord + ")a"
	         + " WHERE rownum <= ?) WHERE rnum >= ?";
     List<RarocConfigModel> lists = new ArrayList<>();
     List<Map<String, Object>> rows = getJdbcTemplate().queryForList(query, new Object[]{ qObj.getRegex(),endIdx, startIdx});
	        rows.stream().map((row) -> {
	        	RarocConfigModel obj = new RarocConfigModel();
	            obj.setId(fmt.ToString(row.get("rnum")));
	            obj.setCol1(fmt.ToString(row.get("N_VALUE")));
	            obj.setCol2(fmt.ToString(row.get("V_TYPE")));
	            ;
	            return obj;
	        }).forEach((obj) -> {
	            lists.add(obj);
	        });
	        return new GridPage<>(lists, page, max, rowCount);

	}

	@Override
	public GridPage<RarocConfigModel> listCNFGAssetType(int page, int max, String sidx, String sord, String searchField,
			String searchOper, String searchString, String userid) throws CustomException {
		List<String> columns = Collections.unmodifiableList(Arrays.asList("V_CODE", "V_DESC",
                "N_VALUE", "V_GUAR_FLAG"));

        List<String> orders = Collections.unmodifiableList(Arrays.asList("asc", "desc"));

        if (sidx == null || sidx.isEmpty()) {
            sidx = "V_CODE";
        }
        if (sord == null || sord.isEmpty()) {
            sord = "desc";
        }

        //check if sidx is in columns 
        if (!columns.contains(sidx)) {
            throw new CustomException();
        }

        if (!orders.contains(sord)) {
            throw new CustomException();
        }

        QueryBuilderModel qObj = queryBuilder.SearchAnd(searchOper, searchField, searchString, columns);
        String query = "SELECT count(*) FROM MST_ASSET_TYPE WHERE V_CODE is not null " +qObj.getCondition()  ;
        int rowCount = getJdbcTemplate().queryForObject(query, new Object[]{qObj.getRegex()}, Integer.class);
        final int startIdx = ((page - 1) * max) + 1;
        final int endIdx = Math.min(startIdx + max, rowCount);
        query = " SELECT * FROM "
        		+"( SELECT a.*, rownum rnum FROM "
        		+ "(SELECT V_CODE,V_DESC,N_VALUE,V_GUAR_FLAG FROM MST_ASSET_TYPE "
                + "WHERE V_CODE is not null "
                + qObj.getCondition() + " "
                + "ORDER BY " + sidx + " " + sord + ") a"
        + " WHERE rownum <= ?) WHERE rnum >= ?";
        List<RarocConfigModel> lists = new ArrayList<>();
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(query, new Object[]{ qObj.getRegex(),endIdx, startIdx});
        rows.stream().map((row) -> {
        	RarocConfigModel obj = new RarocConfigModel();
            obj.setId(fmt.ToString(row.get("rnum")));
            obj.setCol1(fmt.ToString(row.get("V_CODE")));
            obj.setCol2(fmt.ToString(row.get("V_DESC")));
            obj.setCol3(fmt.ToString(row.get("N_VALUE")));
            obj.setCol4(fmt.ToString(row.get("V_GUAR_FLAG")));
            return obj;
        }).forEach((obj) -> {
            lists.add(obj);
        });
        return new GridPage<>(lists, page, max, rowCount);

	}

	@Override
	public GridPage<RarocConfigModel> listCNFGBusinessUnit(int page, int max, String sidx, String sord,
			String searchField, String searchOper, String searchString, String userid)
			throws CustomException {
		 List<String> columns = Collections.unmodifiableList(Arrays.asList("V_CODE", "V_DESC",
	                "F_LATEST_IND"));

	        List<String> orders = Collections.unmodifiableList(Arrays.asList("asc", "desc"));

	        if (sidx == null || sidx.isEmpty()) {
	            sidx = "V_CODE";
	        }
	        if (sord == null || sord.isEmpty()) {
	            sord = "desc";
	        }

	        //check if sidx is in columns 
	        if (!columns.contains(sidx)) {
	            throw new CustomException();
	        }

	        if (!orders.contains(sord)) {
	            throw new CustomException();
	        }

	        QueryBuilderModel qObj = queryBuilder.SearchAnd(searchOper, searchField, searchString, columns);
	        String query = "SELECT count(*) FROM MST_BUSINESS_UNIT WHERE V_CODE is not null " +qObj.getCondition()  ;
	        int rowCount = getJdbcTemplate().queryForObject(query, new Object[]{qObj.getRegex()}, Integer.class);
	        final int startIdx = ((page - 1) * max) + 1;
	        final int endIdx = Math.min(startIdx + max, rowCount);
	        query = " SELECT * FROM "
	        		+"( SELECT a.*, rownum rnum FROM "
	        		+ "(SELECT V_CODE,V_DESC,F_LATEST_IND FROM MST_BUSINESS_UNIT "
	                + "WHERE V_CODE is not null "
	                + qObj.getCondition() + " "
	                + "ORDER BY " + sidx + " " + sord + ") a"
	        + " WHERE rownum <= ?) WHERE rnum >= ?";
	        List<RarocConfigModel> lists = new ArrayList<>();
	        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(query, new Object[]{ qObj.getRegex(),endIdx, startIdx});
	        rows.stream().map((row) -> {
	        	RarocConfigModel obj = new RarocConfigModel();
	            obj.setId(fmt.ToString(row.get("rnum")));
	            obj.setCol1(fmt.ToString(row.get("V_CODE")));
	            obj.setCol2(fmt.ToString(row.get("V_DESC")));
	            obj.setCol3(fmt.ToString(row.get("F_LATEST_IND")));
	            return obj;
	        }).forEach((obj) -> {
	            lists.add(obj);
	        });
	        return new GridPage<>(lists, page, max, rowCount);

	}

	@Override
	public GridPage<RarocConfigModel> listCNFGFinHaircut(int page, int max, String sidx, String sord,
			String searchField, String searchOper, String searchString, String userid)
			throws CustomException {
		 List<String> columns = Collections.unmodifiableList(Arrays.asList("V_FIN_SEC", "N_HAIRCUT",
	                "N_REG_HAIRCUT"));

	        List<String> orders = Collections.unmodifiableList(Arrays.asList("asc", "desc"));

	        if (sidx == null || sidx.isEmpty()) {
	            sidx = "V_FIN_SEC";
	        }
	        if (sord == null || sord.isEmpty()) {
	            sord = "desc";
	        }

	        //check if sidx is in columns 
	        if (!columns.contains(sidx)) {
	            throw new CustomException();
	        }

	        if (!orders.contains(sord)) {
	            throw new CustomException();
	        }

	        QueryBuilderModel qObj = queryBuilder.SearchAnd(searchOper, searchField, searchString, columns);
	        String query = "SELECT count(*) FROM mst_fin_haircut WHERE V_FIN_SEC is not null " +qObj.getCondition()  ;
	        int rowCount = getJdbcTemplate().queryForObject(query, new Object[]{qObj.getRegex()}, Integer.class);
	        final int startIdx = ((page - 1) * max) + 1;
	        final int endIdx = Math.min(startIdx + max, rowCount);
	        query = " SELECT * FROM "
	        		+"( SELECT a.*, rownum rnum FROM "
	        		+ "(SELECT V_FIN_SEC,N_HAIRCUT,N_REG_HAIRCUT FROM mst_fin_haircut "
	                + "WHERE V_FIN_SEC is not null "
	                + qObj.getCondition() + " "
	                + "ORDER BY " + sidx + " " + sord + ") a"
	        + " WHERE rownum <= ?) WHERE rnum >= ?";
	        List<RarocConfigModel> lists = new ArrayList<>();
	        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(query, new Object[]{ qObj.getRegex(),endIdx, startIdx});
	        rows.stream().map((row) -> {
	        	RarocConfigModel obj = new RarocConfigModel();
	            obj.setId(fmt.ToString(row.get("rnum")));
	            obj.setCol1(fmt.ToString(row.get("V_FIN_SEC")));
	            obj.setCol2(fmt.ToString(row.get("N_HAIRCUT")));
	            obj.setCol3(fmt.ToString(row.get("N_REG_HAIRCUT")));
	            return obj;
	        }).forEach((obj) -> {
	            lists.add(obj);
	        });
	        return new GridPage<>(lists, page, max, rowCount);

	}

	@Override
	public GridPage<RarocConfigModel> listCNFGInternalRatingModel(int page, int max, String sidx, String sord,
			String searchField, String searchOper, String searchString, String userid)
			throws CustomException {
		 List<String> columns = Collections.unmodifiableList(Arrays.asList("V_RDM_INT_RAT", "V_INT_RATING"));

	        List<String> orders = Collections.unmodifiableList(Arrays.asList("asc", "desc"));

	        if (sidx == null || sidx.isEmpty()) {
	            sidx = "V_RDM_INT_RAT";
	        }
	        if (sord == null || sord.isEmpty()) {
	            sord = "desc";
	        }

	        //check if sidx is in columns 
	        if (!columns.contains(sidx)) {
	            throw new CustomException();
	        }

	        if (!orders.contains(sord)) {
	            throw new CustomException();
	        }

	        QueryBuilderModel qObj = queryBuilder.SearchAnd(searchOper, searchField, searchString, columns);
	        String query = "SELECT count(*) FROM cnfg_int_rat_mapping WHERE V_RDM_INT_RAT is not null " +qObj.getCondition()  ;
	        int rowCount = getJdbcTemplate().queryForObject(query, new Object[]{qObj.getRegex()}, Integer.class);
	        final int startIdx = ((page - 1) * max) + 1;
	        final int endIdx = Math.min(startIdx + max, rowCount);
	        query = " SELECT * FROM "
	        		+"( SELECT a.*, rownum rnum FROM "
	        		+ "(SELECT V_RDM_INT_RAT,V_INT_RATING FROM cnfg_int_rat_mapping "
	                + "WHERE V_RDM_INT_RAT is not null "
	                + qObj.getCondition() + " "
	                + "ORDER BY " + sidx + " " + sord + ") a"
	        + " WHERE rownum <= ?) WHERE rnum >= ?";
	        List<RarocConfigModel> lists = new ArrayList<>();
	        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(query, new Object[]{ qObj.getRegex(),endIdx, startIdx});
	        rows.stream().map((row) -> {
	        	RarocConfigModel obj = new RarocConfigModel();
	            obj.setId(fmt.ToString(row.get("rnum")));
	            obj.setCol1(fmt.ToString(row.get("V_RDM_INT_RAT")));
	            obj.setCol2(fmt.ToString(row.get("V_INT_RATING")));
	            return obj;
	        }).forEach((obj) -> {
	            lists.add(obj);
	        });
	        return new GridPage<>(lists, page, max, rowCount);

	}

	@Override
	public GridPage<RarocConfigModel> listCNFGRatingModelMapping(int page, int max, String sidx, String sord,
			String searchField, String searchOper, String searchString, String userid)
			throws CustomException {
		 List<String> columns = Collections.unmodifiableList(Arrays.asList("V_SEGMENT", "M_RDM_MODEL"));

	        List<String> orders = Collections.unmodifiableList(Arrays.asList("asc", "desc"));

	        if (sidx == null || sidx.isEmpty()) {
	            sidx = "V_SEGMENT";
	        }
	        if (sord == null || sord.isEmpty()) {
	            sord = "desc";
	        }

	        //check if sidx is in columns 
	        if (!columns.contains(sidx)) {
	            throw new CustomException();
	        }

	        if (!orders.contains(sord)) {
	            throw new CustomException();
	        }

	        QueryBuilderModel qObj = queryBuilder.SearchAnd(searchOper, searchField, searchString, columns);
	        String query = "SELECT count(*) FROM cnfg_rating_model_map WHERE V_SEGMENT is not null " +qObj.getCondition()  ;
	        int rowCount = getJdbcTemplate().queryForObject(query, new Object[]{qObj.getRegex()}, Integer.class);
	        final int startIdx = ((page - 1) * max) + 1;
	        final int endIdx = Math.min(startIdx + max, rowCount);
	        query = " SELECT * FROM "
	        		+"( SELECT a.*, rownum rnum FROM "
	        		+ "(SELECT V_SEGMENT,M_RDM_MODEL FROM cnfg_rating_model_map "
	                + "WHERE V_SEGMENT is not null "
	                + qObj.getCondition() + " "
	                + "ORDER BY " + sidx + " " + sord + ") a"
	        + " WHERE rownum <= ?) WHERE rnum >= ?";
	        List<RarocConfigModel> lists = new ArrayList<>();
	        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(query, new Object[]{ qObj.getRegex(),endIdx, startIdx});
	        rows.stream().map((row) -> {
	        	RarocConfigModel obj = new RarocConfigModel();
	            obj.setId(fmt.ToString(row.get("rnum")));
	            obj.setCol1(fmt.ToString(row.get("V_SEGMENT")));
	            obj.setCol2(fmt.ToString(row.get("M_RDM_MODEL")));
	            return obj;
	        }).forEach((obj) -> {
	            lists.add(obj);
	        });
	        return new GridPage<>(lists, page, max, rowCount);

	}

	
}
