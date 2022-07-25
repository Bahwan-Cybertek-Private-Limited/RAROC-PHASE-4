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

	
	
}
