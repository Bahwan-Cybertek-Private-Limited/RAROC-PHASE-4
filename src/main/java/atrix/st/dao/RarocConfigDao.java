package atrix.st.dao;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import atrix.common.util.GridPage;
import atrix.st.exception.CustomException;
import atrix.st.model.RarocConfigModel;

public interface RarocConfigDao {

	 public GridPage<RarocConfigModel> listRarocConfig(int page, int max, String sidx, String sord, String searchField,
	            String searchOper, String searchString, String userid) throws CustomException;
	
	 public List<RarocConfigModel> listRarocMasterDoc();
	 
	 public List<RarocConfigModel> rarocMasterReadxls(MultipartFile file) throws IOException;
	 
	 public void saveRarocMasterFile(MultipartFile file) throws IOException;
	 
	 public GridPage<RarocConfigModel> listInternalRating(int page, int max, String sidx, String sord,
	            String searchField, String searchOper, String searchString, String userid) throws CustomException;
	 
	 public List<RarocConfigModel> listInternalRatingDoc();
	 
	 
	 public List<RarocConfigModel>  readInternalRatingxls(MultipartFile file) throws IOException;
	 
	 public void saveInternalRatingFile(MultipartFile file) throws IOException;
	 
	 public GridPage<RarocConfigModel> listExternalRating(int page, int max, String sidx, String sord,
	            String searchField, String searchOper, String searchString, String userid) throws CustomException;
	 
	 public List<RarocConfigModel> listExternalRatingDoc();
	 
	 public GridPage<RarocConfigModel> listGuarantorMaster(int page, int max, String sidx, String sord,
	            String searchField, String searchOper, String searchString, String userid) throws CustomException;
	 
	 public List<RarocConfigModel> listGuarantorMasterDoc();
	 
	 public GridPage<RarocConfigModel> listOthIncomeMaster(int page, int max, String sidx, String sord,
	            String searchField, String searchOper, String searchString,String userid) throws CustomException;
	 
	 public List<RarocConfigModel> listOthIncomeMasterDoc();
	 
	 public GridPage<RarocConfigModel> listCCFMaster(int page, int max, String sidx, String sord,
	            String searchField, String searchOper, String searchString,String userid) throws CustomException;
	 
	 public List<RarocConfigModel> listCCFMasterDoc();
	 
	 public GridPage<RarocConfigModel> listOperatingExpenseMaster(int page, int max, String sidx, String sord,
	            String searchField, String searchOper, String searchString,String userid) throws CustomException;
	 
	 public List<RarocConfigModel> listOperatingExpenseMasterDoc();
	 
	 public GridPage<RarocConfigModel> listRestructuredMaster(int page, int max, String sidx, String sord,
	            String searchField, String searchOper, String searchString,String userid) throws CustomException;
	 
	 public List<RarocConfigModel> listRestructuredMasterDoc();
	 
	 public GridPage<RarocConfigModel> listSensitivityIterationMaster(int page, int max, String sidx, String sord,
	            String searchField, String searchOper, String searchString,String userid) throws CustomException;

	 public List<RarocConfigModel> listSensitivityIterationMasterDoc();
	 
	 
	 public GridPage<RarocConfigModel> listAssetType(int page, int max, String sidx, String sord,
	            String searchField, String searchOper, String searchString,String userid) throws CustomException;

	 public List<RarocConfigModel> listAssetTypeDoc();
	 
	 public List<RarocConfigModel>  assetTypeReadxls(MultipartFile file) throws IOException;
	 
	 public void saveAssetTypeFile(MultipartFile file) throws IOException;
	 
	 public GridPage<RarocConfigModel> listBusinessUnit(int page, int max, String sidx, String sord,
	            String searchField, String searchOper, String searchString, String userid) throws CustomException;

	 public List<RarocConfigModel> listBusinessUnitDoc();
	 
	 public List<RarocConfigModel>  businessUnitReadxls(MultipartFile file) throws IOException;
	 
	 public void saveBusinessUnitFile(MultipartFile file) throws IOException;

	 
	 public GridPage<RarocConfigModel> listFinHaircut(int page, int max, String sidx, String sord,
	            String searchField, String searchOper, String searchString,String userid) throws CustomException;

	 public List<RarocConfigModel> listFinHaircutDoc();
	 
	 public List<RarocConfigModel>  finHaircutReadxls(MultipartFile file) throws IOException;
	 
	 public void saveFinHaircutFile(MultipartFile file) throws IOException;
	 
	 public GridPage<RarocConfigModel> listInternalRatingModel(int page, int max, String sidx, String sord,
	            String searchField, String searchOper, String searchString, String userid) throws CustomException;

	 public List<RarocConfigModel> listInternalRatingModelDoc();
	 
	 public List<RarocConfigModel>  internalRatingModelReadxls(MultipartFile file) throws IOException;
	 
	 public void saveInternalRatingModelFile(MultipartFile file) throws IOException;

	 public GridPage<RarocConfigModel> listRatingModelMapping(int page, int max, String sidx, String sord,
	            String searchField, String searchOper, String searchString,String userid) throws CustomException;

	 public List<RarocConfigModel> listRatingModelMappingDoc();
	 
	 public List<RarocConfigModel>  ratingModelMappingReadxls(MultipartFile file) throws IOException;
	 
	 public void saveRatingModelMappingFile(MultipartFile file) throws IOException;
	 
	 public void saveExternalRatingFile(MultipartFile file) throws IOException;
	 
	 public void saveGuarantorMasterFile(MultipartFile file) throws IOException;
	 
	 public void saveOthIncomeMasterFile(MultipartFile file) throws IOException;
	 
	 public void saveCCFMasterFile(MultipartFile file) throws IOException;
	 
	 public void saveOperatingExpenseMasterFile(MultipartFile file) throws IOException;
	 
	 public void saveRestructuredMasterFile(MultipartFile file) throws IOException;
	 
	 public void saveSensitivityIterationFile(MultipartFile file) throws IOException;
	 
	 public List<RarocConfigModel> sensitivityIterationReadxls(MultipartFile file) throws IOException;
	 
	 public List<RarocConfigModel> restructuredMasterReadxls(MultipartFile file) throws IOException;
	 
	 public List<RarocConfigModel> operatingExpenseMasterReadxls(MultipartFile file) throws IOException;
	 
	 public List<RarocConfigModel> externalRatingReadxls(MultipartFile file) throws IOException;
	 
	 public List<RarocConfigModel> guarantorMasterReadxls(MultipartFile file) throws IOException;
	 
	 public List<RarocConfigModel> othIncomeMasterReadxls(MultipartFile file) throws IOException;
	 
	 public List<RarocConfigModel> cCFMasterReadxls(MultipartFile file) throws IOException;

	 public void rejectConfig(String tableName);
	 
	 public void approveConfig();  
	 
	 public void approveInternalRating();
	 
	 public void approveExternalRating();
	 
	 public void approveGuarantor();
	 
	 public void approveIncomeMater();
	 
	 public void approveCCFMaster();
	 
	 public void approveOperatingExpense();
	 
	 public void approveRestructuredMaster();
	 
	 public void approveSensitivityMaster();
	 
	 public void approveAssetType();
	 
	 public void approveFINHaircut();
	 
	 public void approveRatingModelMapping();
	 
	 public void approveInternalRatingMapping();
	 
	 public void approveBusinessUnit();
	 
}
