package atrix.st.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import atrix.common.dao.SecurityDao;
import atrix.common.util.GridPage;
import atrix.st.dao.RarocConfigDao;
import atrix.st.exception.CustomException;
import atrix.st.model.RarocConfigModel;


@Service("rarocConfigService")
public class RarocConfigServiceImpl implements RarocConfigService{

	@Autowired
	private RarocConfigDao rarocConfigDao;
	@Autowired
	private SecurityDao securityDao;
	@Autowired
	private MessageSource msgSrc;

	@Override
	public GridPage<RarocConfigModel> listRarocConfig(int page, int max, String sidx, String sord, String searchField,
			String searchOper, String searchString, HttpServletRequest request) throws CustomException {
		 HttpSession session = request.getSession(false);
		 String userid = (String) session.getAttribute("userid");
	        return rarocConfigDao.listRarocConfig(page, max, sidx, sord, searchField, searchOper, searchString, userid);
	}
	
	@Override
	public GridPage<RarocConfigModel> listRarocCNFGMaster(int page, int max, String sidx, String sord, String searchField,
			String searchOper, String searchString, HttpServletRequest request) throws CustomException {
		 HttpSession session = request.getSession(false);
		 String userid = (String) session.getAttribute("userid");
	        return rarocConfigDao.listRarocCNFGMaster(page, max, sidx, sord, searchField, searchOper, searchString, userid);
	}

	@Override
	public GridPage<RarocConfigModel> listInternalRating(int page, int max, String sidx, String sord,
			String searchField, String searchOper, String searchString, HttpServletRequest request)
			throws CustomException {
		 HttpSession session = request.getSession(false);
		 String userid = (String) session.getAttribute("userid");
	        return rarocConfigDao.listInternalRating(page, max, sidx, sord, searchField, searchOper, searchString, userid);
	}

	@Override
	public GridPage<RarocConfigModel> listExternalRating(int page, int max, String sidx, String sord,
			String searchField, String searchOper, String searchString, HttpServletRequest request)
			throws CustomException {
		 HttpSession session = request.getSession(false);
		 String userid = (String) session.getAttribute("userid");
	        return rarocConfigDao.listExternalRating(page, max, sidx, sord, searchField, searchOper, searchString, userid);
	}

	@Override
	public GridPage<RarocConfigModel> listGuarantorMaster(int page, int max, String sidx, String sord,
			String searchField, String searchOper, String searchString, HttpServletRequest request)
			throws CustomException {
		 HttpSession session = request.getSession(false);
		 String userid = (String) session.getAttribute("userid");
	        return rarocConfigDao.listGuarantorMaster(page, max, sidx, sord, searchField, searchOper, searchString, userid);
	}

	@Override
	public GridPage<RarocConfigModel> listOthIncomeMaster(int page, int max, String sidx, String sord,
			String searchField, String searchOper, String searchString, HttpServletRequest request)
			throws CustomException {
		 HttpSession session = request.getSession(false);
		 String userid = (String) session.getAttribute("userid");
	        return rarocConfigDao.listOthIncomeMaster(page, max, sidx, sord, searchField, searchOper, searchString, userid);
	}

	@Override
	public GridPage<RarocConfigModel> listCCFMaster(int page, int max, String sidx, String sord, String searchField,
			String searchOper, String searchString, HttpServletRequest request) throws CustomException {
		 HttpSession session = request.getSession(false);
		 String userid = (String) session.getAttribute("userid");
	        return rarocConfigDao.listCCFMaster(page, max, sidx, sord, searchField, searchOper, searchString, userid);
	}

	@Override
	public GridPage<RarocConfigModel> listOperatingExpenseMaster(int page, int max, String sidx, String sord,
			String searchField, String searchOper, String searchString, HttpServletRequest request)
			throws CustomException {
		 HttpSession session = request.getSession(false);
		 String userid = (String) session.getAttribute("userid");
	        return rarocConfigDao.listOperatingExpenseMaster(page, max, sidx, sord, searchField, searchOper, searchString, userid);
	}

	@Override
	public GridPage<RarocConfigModel> listRestructuredMaster(int page, int max, String sidx, String sord,
			String searchField, String searchOper, String searchString, HttpServletRequest request)
			throws CustomException {
		 HttpSession session = request.getSession(false);
		 String userid = (String) session.getAttribute("userid");
	        return rarocConfigDao.listRestructuredMaster(page, max, sidx, sord, searchField, searchOper, searchString, userid);
	}

	@Override
	public GridPage<RarocConfigModel> listSensitivityIterationMaster(int page, int max, String sidx, String sord,
			String searchField, String searchOper, String searchString, HttpServletRequest request)
			throws CustomException {
		 HttpSession session = request.getSession(false);
		 String userid = (String) session.getAttribute("userid");
	        return rarocConfigDao.listSensitivityIterationMaster(page, max, sidx, sord, searchField, searchOper, searchString, userid);
	}

	@Override
	public List<RarocConfigModel> listRarocMasterDoc() {
		
		return rarocConfigDao.listRarocMasterDoc();
	}

	@Override
	public List<RarocConfigModel> listInternalRatingDoc() {
		return rarocConfigDao.listInternalRatingDoc();
	}

	@Override
	public List<RarocConfigModel> listExternalRatingDoc() {
		return rarocConfigDao.listExternalRatingDoc();
	}

	@Override
	public List<RarocConfigModel> listGuarantorMasterDoc() {
		return rarocConfigDao.listGuarantorMasterDoc();
	}

	@Override
	public List<RarocConfigModel> listOthIncomeMasterDoc() {
		return rarocConfigDao.listOthIncomeMasterDoc();
	}

	@Override
	public List<RarocConfigModel> listCCFMasterDoc() {
		return rarocConfigDao.listCCFMasterDoc();
	}

	@Override
	public List<RarocConfigModel> listOperatingExpenseMasterDoc() {
		return rarocConfigDao.listOperatingExpenseMasterDoc();
	}

	@Override
	public List<RarocConfigModel> listRestructuredMasterDoc() {
		return rarocConfigDao.listRestructuredMasterDoc();
	}

	@Override
	public List<RarocConfigModel> listSensitivityIterationMasterDoc() {
		return rarocConfigDao.listSensitivityIterationMasterDoc();
	}
	
	
	
	
	
	@Override
	public List<RarocConfigModel> rarocMasterDoc() {
		
		return rarocConfigDao.rarocMasterDoc();
	}

	@Override
	public List<RarocConfigModel> internalRatingDoc() {
		return rarocConfigDao.internalRatingDoc();
	}

	@Override
	public List<RarocConfigModel> externalRatingDoc() {
		return rarocConfigDao.externalRatingDoc();
	}

	@Override
	public List<RarocConfigModel> guarantorMasterDoc() {
		return rarocConfigDao.guarantorMasterDoc();
	}

	@Override
	public List<RarocConfigModel> othIncomeMasterDoc() {
		return rarocConfigDao.othIncomeMasterDoc();
	}

	@Override
	public List<RarocConfigModel> cCFMasterDoc() {
		return rarocConfigDao.cCFMasterDoc();
	}

	@Override
	public List<RarocConfigModel> operatingExpenseMasterDoc() {
		return rarocConfigDao.operatingExpenseMasterDoc();
	}

	@Override
	public List<RarocConfigModel> restructuredMasterDoc() {
		return rarocConfigDao.restructuredMasterDoc();
	}

	@Override
	public List<RarocConfigModel> sensitivityIterationMasterDoc() {
		return rarocConfigDao.sensitivityIterationMasterDoc();
	}

	@Override
	public List<RarocConfigModel> assetTypeDoc() {
		return rarocConfigDao.assetTypeDoc();
	}

	@Override
	public List<RarocConfigModel> businessUnitDoc() {
		return rarocConfigDao.businessUnitDoc();
	}
	
	@Override
	public List<RarocConfigModel> finHaircutDoc() {
		return rarocConfigDao.finHaircutDoc();

	}
	
	
	@Override
	public List<RarocConfigModel> internalRatingModelDoc() {
		return rarocConfigDao.internalRatingModelDoc();

	}
	
	@Override
	public List<RarocConfigModel> ratingModelMappingDoc() {
		return rarocConfigDao.ratingModelMappingDoc();

	}
	
	@Override
	public List<RarocConfigModel>  rarocMasterReadxls(MultipartFile file) throws IOException {
		return rarocConfigDao.rarocMasterReadxls(file);
		
	}

	@Override
	public void saveRarocMasterFile(MultipartFile file) throws IOException {
		rarocConfigDao.saveRarocMasterFile(file);
		
	}

	@Override
	public List<RarocConfigModel> readInternalRatingxls(MultipartFile file) throws IOException {
		return rarocConfigDao.readInternalRatingxls(file);
	}

	@Override
	public void saveInternalRatingFile(MultipartFile file) throws IOException {
		rarocConfigDao.saveInternalRatingFile(file);
		
	}

	@Override
	public GridPage<RarocConfigModel> listAssetType(int page, int max, String sidx, String sord, String searchField,
			String searchOper, String searchString, HttpServletRequest request) throws CustomException {
		 HttpSession session = request.getSession(false);
		 String userid = (String) session.getAttribute("userid");
	        return rarocConfigDao.listAssetType(page, max, sidx, sord, searchField, searchOper, searchString, userid);
	}

	@Override
	public List<RarocConfigModel> listAssetTypeDoc() {
		return rarocConfigDao.listAssetTypeDoc();
	}

	@Override
	public List<RarocConfigModel> assetTypeReadxls(MultipartFile file) throws IOException {
		return rarocConfigDao.assetTypeReadxls(file);
	}

	@Override
	public void saveAssetTypeFile(MultipartFile file) throws IOException {
		rarocConfigDao.saveAssetTypeFile(file);
		
	}

	@Override
	public GridPage<RarocConfigModel> listBusinessUnit(int page, int max, String sidx, String sord, String searchField,
			String searchOper, String searchString, HttpServletRequest request) throws CustomException {
		 HttpSession session = request.getSession(false);
		 String userid = (String) session.getAttribute("userid");
	        return rarocConfigDao.listBusinessUnit(page, max, sidx, sord, searchField, searchOper, searchString, userid);
	}

	@Override
	public List<RarocConfigModel> listBusinessUnitDoc() {
		return rarocConfigDao.listBusinessUnitDoc();
	}

	@Override
	public List<RarocConfigModel> businessUnitReadxls(MultipartFile file) throws IOException {
		return rarocConfigDao.businessUnitReadxls(file);
	}

	@Override
	public void saveBusinessUnitFile(MultipartFile file) throws IOException {
		rarocConfigDao.saveBusinessUnitFile(file);
		
	}

	@Override
	public GridPage<RarocConfigModel> listFinHaircut(int page, int max, String sidx, String sord, String searchField,
			String searchOper, String searchString, HttpServletRequest request) throws CustomException {
		 HttpSession session = request.getSession(false);
		 String userid = (String) session.getAttribute("userid");
	        return rarocConfigDao.listFinHaircut(page, max, sidx, sord, searchField, searchOper, searchString, userid);
	}

	@Override
	public List<RarocConfigModel> listFinHaircutDoc() {
		return rarocConfigDao.listFinHaircutDoc();

	}

	@Override
	public List<RarocConfigModel> finHaircutReadxls(MultipartFile file) throws IOException {
		return rarocConfigDao.finHaircutReadxls(file);
	}

	@Override
	public void saveFinHaircutFile(MultipartFile file) throws IOException {
		rarocConfigDao.saveFinHaircutFile(file);
		
	}

	@Override
	public GridPage<RarocConfigModel> listInternalRatingModel(int page, int max, String sidx, String sord,
			String searchField, String searchOper, String searchString, HttpServletRequest request)
			throws CustomException {
		 HttpSession session = request.getSession(false);
		 String userid = (String) session.getAttribute("userid");
	        return rarocConfigDao.listInternalRatingModel(page, max, sidx, sord, searchField, searchOper, searchString, userid);
	}

	@Override
	public List<RarocConfigModel> listInternalRatingModelDoc() {
		return rarocConfigDao.listInternalRatingModelDoc();

	}

	@Override
	public List<RarocConfigModel> internalRatingModelReadxls(MultipartFile file) throws IOException {
		return rarocConfigDao.internalRatingModelReadxls(file);
	}

	@Override
	public void saveInternalRatingModelFile(MultipartFile file) throws IOException {
		rarocConfigDao.saveInternalRatingModelFile(file);
		
	}

	@Override
	public GridPage<RarocConfigModel> listRatingModelMapping(int page, int max, String sidx, String sord,
			String searchField, String searchOper, String searchString, HttpServletRequest request)
			throws CustomException {
		 HttpSession session = request.getSession(false);
		 String userid = (String) session.getAttribute("userid");
	        return rarocConfigDao.listRatingModelMapping(page, max, sidx, sord, searchField, searchOper, searchString, userid);
	}

	@Override
	public List<RarocConfigModel> listRatingModelMappingDoc() {
		return rarocConfigDao.listRatingModelMappingDoc();

	}

	@Override
	public List<RarocConfigModel> ratingModelMappingReadxls(MultipartFile file) throws IOException {
		return rarocConfigDao.ratingModelMappingReadxls(file);
	}

	@Override
	public void saveRatingModelMappingFile(MultipartFile file) throws IOException {
		rarocConfigDao.saveRatingModelMappingFile(file);
		
	}

	@Override
	public void saveExternalRatingFile(MultipartFile file) throws IOException {
		rarocConfigDao.saveExternalRatingFile(file);
		
	}

	@Override
	public void saveGuarantorMasterFile(MultipartFile file) throws IOException {
		rarocConfigDao.saveGuarantorMasterFile(file);
		
	}

	@Override
	public void saveOthIncomeMasterFile(MultipartFile file) throws IOException {
		rarocConfigDao.saveOthIncomeMasterFile(file);
		
	}

	@Override
	public void saveCCFMasterFile(MultipartFile file) throws IOException {
		rarocConfigDao.saveCCFMasterFile(file);
		
	}

	@Override
	public void saveOperatingExpenseMasterFile(MultipartFile file) throws IOException {
		rarocConfigDao.saveOperatingExpenseMasterFile(file);
		
	}

	@Override
	public void saveRestructuredMasterFile(MultipartFile file) throws IOException {
		rarocConfigDao.saveRestructuredMasterFile(file);
		
	}

	@Override
	public void saveSensitivityIterationFile(MultipartFile file) throws IOException {
		rarocConfigDao.saveSensitivityIterationFile(file);
		
	}

	@Override
	public void rejectConfig(String tableName) {
		 rarocConfigDao.rejectConfig(tableName);
	}

	@Override
	public void approveConfig() {
		 rarocConfigDao.approveConfig();
	}

	@Override
	public void approveInternalRating() {
		rarocConfigDao.approveInternalRating();
		
	}

	@Override
	public void approveExternalRating() {
		rarocConfigDao.approveExternalRating();
		
	}

	@Override
	public void approveGuarantor() {
		rarocConfigDao.approveGuarantor();
		
	}

	@Override
	public void approveIncomeMater() {
		rarocConfigDao.approveIncomeMater();
		
	}

	@Override
	public void approveCCFMaster() {
		rarocConfigDao.approveCCFMaster();
		
	}

	@Override
	public void approveOperatingExpense() {
		rarocConfigDao.approveOperatingExpense();
		
	}

	@Override
	public void approveRestructuredMaster() {
		rarocConfigDao.approveRestructuredMaster();
		
	}

	@Override
	public void approveSensitivityMaster() {
		rarocConfigDao.approveSensitivityMaster();
		
	}

	@Override
	public void approveAssetType() {
		rarocConfigDao.approveAssetType();
		
	}

	@Override
	public void approveFINHaircut() {
		rarocConfigDao.approveFINHaircut();
		
	}

	@Override
	public void approveRatingModelMapping() {
		rarocConfigDao.approveRatingModelMapping();
		
	}

	@Override
	public void approveInternalRatingMapping() {
		rarocConfigDao.approveInternalRatingMapping();
		
	}

	@Override
	public void approveBusinessUnit() {
		rarocConfigDao.approveBusinessUnit();
		
	}

	@Override
	public GridPage<RarocConfigModel> listCNFGInternalRating(int page, int max, String sidx, String sord,
			String searchField, String searchOper, String searchString, HttpServletRequest request)
			throws CustomException {
		HttpSession session = request.getSession(false);
		 String userid = (String) session.getAttribute("userid");
	        return rarocConfigDao.listCNFGInternalRating(page, max, sidx, sord, searchField, searchOper, searchString, userid);
	}

	@Override
	public GridPage<RarocConfigModel> listCNFGExternalRating(int page, int max, String sidx, String sord,
			String searchField, String searchOper, String searchString, HttpServletRequest request)
			throws CustomException {
		HttpSession session = request.getSession(false);
		 String userid = (String) session.getAttribute("userid");
	        return rarocConfigDao.listCNFGExternalRating(page, max, sidx, sord, searchField, searchOper, searchString, userid);
	}

	@Override
	public GridPage<RarocConfigModel> listCNFGGuarantorMaster(int page, int max, String sidx, String sord,
			String searchField, String searchOper, String searchString, HttpServletRequest request)
			throws CustomException {
		HttpSession session = request.getSession(false);
		 String userid = (String) session.getAttribute("userid");
	        return rarocConfigDao.listCNFGGuarantorMaster(page, max, sidx, sord, searchField, searchOper, searchString, userid);
	}

	@Override
	public GridPage<RarocConfigModel> listCNFGOthIncomeMaster(int page, int max, String sidx, String sord,
			String searchField, String searchOper, String searchString, HttpServletRequest request)
			throws CustomException {
		HttpSession session = request.getSession(false);
		 String userid = (String) session.getAttribute("userid");
	        return rarocConfigDao.listCNFGOthIncomeMaster(page, max, sidx, sord, searchField, searchOper, searchString, userid);
	}

	@Override
	public GridPage<RarocConfigModel> listCNFGCCFMaster(int page, int max, String sidx, String sord, String searchField,
			String searchOper, String searchString, HttpServletRequest request) throws CustomException {
		HttpSession session = request.getSession(false);
		 String userid = (String) session.getAttribute("userid");
	        return rarocConfigDao.listCNFGCCFMaster(page, max, sidx, sord, searchField, searchOper, searchString, userid);
	}

	@Override
	public GridPage<RarocConfigModel> listCNFGOperatingExpenseMaster(int page, int max, String sidx, String sord,
			String searchField, String searchOper, String searchString, HttpServletRequest request)
			throws CustomException {
		HttpSession session = request.getSession(false);
		 String userid = (String) session.getAttribute("userid");
	        return rarocConfigDao.listCNFGOperatingExpenseMaster(page, max, sidx, sord, searchField, searchOper, searchString, userid);
	}

	@Override
	public GridPage<RarocConfigModel> listCNFGRestructuredMaster(int page, int max, String sidx, String sord,
			String searchField, String searchOper, String searchString, HttpServletRequest request)
			throws CustomException {
		HttpSession session = request.getSession(false);
		 String userid = (String) session.getAttribute("userid");
	        return rarocConfigDao.listCNFGRestructuredMaster(page, max, sidx, sord, searchField, searchOper, searchString, userid);
	}

	@Override
	public GridPage<RarocConfigModel> listCNFGSensitivityIterationMaster(int page, int max, String sidx, String sord,
			String searchField, String searchOper, String searchString, HttpServletRequest request)
			throws CustomException {
		HttpSession session = request.getSession(false);
		 String userid = (String) session.getAttribute("userid");
	        return rarocConfigDao.listCNFGSensitivityIterationMaster(page, max, sidx, sord, searchField, searchOper, searchString, userid);
	}

	@Override
	public GridPage<RarocConfigModel> listCNFGAssetType(int page, int max, String sidx, String sord, String searchField,
			String searchOper, String searchString, HttpServletRequest request) throws CustomException {
		HttpSession session = request.getSession(false);
		 String userid = (String) session.getAttribute("userid");
	        return rarocConfigDao.listCNFGAssetType(page, max, sidx, sord, searchField, searchOper, searchString, userid);
	}

	@Override
	public GridPage<RarocConfigModel> listCNFGBusinessUnit(int page, int max, String sidx, String sord,
			String searchField, String searchOper, String searchString, HttpServletRequest request)
			throws CustomException {
		HttpSession session = request.getSession(false);
		 String userid = (String) session.getAttribute("userid");
	        return rarocConfigDao.listCNFGBusinessUnit(page, max, sidx, sord, searchField, searchOper, searchString, userid);
	}

	@Override
	public GridPage<RarocConfigModel> listCNFGFinHaircut(int page, int max, String sidx, String sord,
			String searchField, String searchOper, String searchString, HttpServletRequest request)
			throws CustomException {
		HttpSession session = request.getSession(false);
		 String userid = (String) session.getAttribute("userid");
	        return rarocConfigDao.listCNFGFinHaircut(page, max, sidx, sord, searchField, searchOper, searchString, userid);
	}

	@Override
	public GridPage<RarocConfigModel> listCNFGInternalRatingModel(int page, int max, String sidx, String sord,
			String searchField, String searchOper, String searchString, HttpServletRequest request)
			throws CustomException {
		HttpSession session = request.getSession(false);
		 String userid = (String) session.getAttribute("userid");
	        return rarocConfigDao.listCNFGInternalRatingModel(page, max, sidx, sord, searchField, searchOper, searchString, userid);
	}

	@Override
	public GridPage<RarocConfigModel> listCNFGRatingModelMapping(int page, int max, String sidx, String sord,
			String searchField, String searchOper, String searchString, HttpServletRequest request)
			throws CustomException {
		HttpSession session = request.getSession(false);
		 String userid = (String) session.getAttribute("userid");
	        return rarocConfigDao.listCNFGRatingModelMapping(page, max, sidx, sord, searchField, searchOper, searchString, userid);
	}

	
	
}
