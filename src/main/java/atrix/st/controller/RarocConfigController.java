package atrix.st.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import atrix.common.service.CSRFTokenService;
import atrix.common.util.GridPage;
import atrix.st.exception.CustomException;
import atrix.st.model.RarocConfigModel;
import atrix.st.service.RarocConfigService;

@Controller
@RequestMapping(value = "/rarocConfig")
public class RarocConfigController {


    @Autowired
    private RarocConfigService rarocConfigService;
    @Autowired
    private CSRFTokenService csrfTokenService;
    @Autowired
    private MessageSource msgSrc;
    private static final Logger logger = Logger.getLogger(RarocConfigController.class);
	
	  @RequestMapping(method = RequestMethod.GET)
	    public String createPage(Map<String, Object> model) {
	        return "raroc/rarocConfigMain";
	    }
	  
	 
	  
	//RAROC grid for users
	    @RequestMapping(value = "/grid/listRarocMaster", method = RequestMethod.GET)
	    public @ResponseBody
	    GridPage<RarocConfigModel> listRarocMaster(
	            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
	            @RequestParam(value = "max", required = false, defaultValue = "20") int max,
	            @RequestParam(value = "sidx", required = false) String sidx,
	            @RequestParam(value = "sord", required = false) String sord,
	            @RequestParam(value = "searchField", required = false) String searchField,
	            @RequestParam(value = "searchOper", required = false) String searchOper,
	            @RequestParam(value = "searchString", required = false) String searchString,
	            HttpServletRequest request, HttpServletResponse response) throws CustomException {
	        csrfTokenService.removeTokenFromSession(request);
	        response.setHeader("_tk", csrfTokenService.getTokenFromSession(request));
	        GridPage<RarocConfigModel> gridList = rarocConfigService.listRarocConfig(page, max, sidx, sord, searchField, searchOper, searchString, request);
	        return gridList;
	    }
	    
	    @RequestMapping(value = "/listRarocMaster/doc", method = RequestMethod.GET)
	    public String listRarocMasterDoc(Map<String, Object> map, HttpServletRequest request) {
	        HttpSession session = request.getSession(false);
	        map.put("list",rarocConfigService.listRarocMasterDoc());
	        return "xlsRarocMaster";
	    		
	    }
	    
	    @RequestMapping( value = "/jqgrid",method = RequestMethod.POST)
		public String uploadFile(@RequestParam("file") MultipartFile file) {
	    	try {
				if (null == file) {
					throw new IOException("Invalid Input");
				}
				rarocConfigService.saveRarocMasterFile(file);
			} catch (IOException e) {
				return e.getMessage();
			}
			 return "raroc/rarocConfigMain";
		}
	    
	    @RequestMapping(value = "/grid/listInternalRating", method = RequestMethod.GET)
	    public @ResponseBody
	    GridPage<RarocConfigModel> listInternalRating(
	            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
	            @RequestParam(value = "max", required = false, defaultValue = "20") int max,
	            @RequestParam(value = "sidx", required = false) String sidx,
	            @RequestParam(value = "sord", required = false) String sord,
	            @RequestParam(value = "searchField", required = false) String searchField,
	            @RequestParam(value = "searchOper", required = false) String searchOper,
	            @RequestParam(value = "searchString", required = false) String searchString,
	            HttpServletRequest request, HttpServletResponse response) throws CustomException {
	        csrfTokenService.removeTokenFromSession(request);
	        response.setHeader("_tk", csrfTokenService.getTokenFromSession(request));
	        GridPage<RarocConfigModel> gridList = rarocConfigService.listInternalRating(page, max, sidx, sord, searchField, searchOper, searchString, request);
	        return gridList;
	    }
	    
	    @RequestMapping(value = "/listInternalRating/doc", method = RequestMethod.GET)
	    public String listInternalRatingDoc(Map<String, Object> map, HttpServletRequest request) {
	        HttpSession session = request.getSession(false);
	        map.put("list",rarocConfigService.listInternalRatingDoc());
	        return "xlsInternalRating";
	    		
	    }
	    
	    @RequestMapping( value = "/intjqgrid",method = RequestMethod.POST)
		public String uploadInternalRatingFile(@RequestParam("file") MultipartFile file) {
	    	try {
				if (null == file) {
					throw new IOException("Invalid Input");
				}
				rarocConfigService.saveInternalRatingFile(file);
			} catch (IOException e) {
				return e.getMessage();
			}
			 return "raroc/rarocConfigMain";
		}
	    
	    @RequestMapping(value = "/grid/listExternalRating", method = RequestMethod.GET)
	    public @ResponseBody
	    GridPage<RarocConfigModel> listExternalRating(
	            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
	            @RequestParam(value = "max", required = false, defaultValue = "20") int max,
	            @RequestParam(value = "sidx", required = false) String sidx,
	            @RequestParam(value = "sord", required = false) String sord,
	            @RequestParam(value = "searchField", required = false) String searchField,
	            @RequestParam(value = "searchOper", required = false) String searchOper,
	            @RequestParam(value = "searchString", required = false) String searchString,
	            HttpServletRequest request, HttpServletResponse response) throws CustomException {
	        csrfTokenService.removeTokenFromSession(request);
	        response.setHeader("_tk", csrfTokenService.getTokenFromSession(request));
	        GridPage<RarocConfigModel> gridList = rarocConfigService.listExternalRating(page, max, sidx, sord, searchField, searchOper, searchString, request);
	        return gridList;
	    }
	    
	    @RequestMapping(value = "/listExternalRating/doc", method = RequestMethod.GET)
	    public String listExternalRatingDoc(Map<String, Object> map, HttpServletRequest request) {
	        HttpSession session = request.getSession(false);
	        map.put("list",rarocConfigService.listExternalRatingDoc());
	        return "xlsExternalRating";
	    		
	    }
	    
	    @RequestMapping( value = "/extjqgrid",method = RequestMethod.POST)
		public String uploadExternalRatingFile(@RequestParam("file") MultipartFile file) {
	    	try {
				if (null == file) {
					throw new IOException("Invalid Input");
				}
				rarocConfigService.saveExternalRatingFile(file);
			} catch (IOException e) {
				return e.getMessage();
			}
			 return "raroc/rarocConfigMain";
		}
	    @RequestMapping(value = "/grid/listGuarantorMaster", method = RequestMethod.GET)
	    public @ResponseBody
	    GridPage<RarocConfigModel> listGuarantorMaster(
	            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
	            @RequestParam(value = "max", required = false, defaultValue = "20") int max,
	            @RequestParam(value = "sidx", required = false) String sidx,
	            @RequestParam(value = "sord", required = false) String sord,
	            @RequestParam(value = "searchField", required = false) String searchField,
	            @RequestParam(value = "searchOper", required = false) String searchOper,
	            @RequestParam(value = "searchString", required = false) String searchString,
	            HttpServletRequest request, HttpServletResponse response) throws CustomException {
	        csrfTokenService.removeTokenFromSession(request);
	        response.setHeader("_tk", csrfTokenService.getTokenFromSession(request));
	        GridPage<RarocConfigModel> gridList = rarocConfigService.listGuarantorMaster(page, max, sidx, sord, searchField, searchOper, searchString, request);
	        return gridList;
	    }
	    
	    @RequestMapping(value = "/listGuarantorMaster/doc", method = RequestMethod.GET)
	    public String listGuarantorMasterDoc(Map<String, Object> map, HttpServletRequest request) {
	        HttpSession session = request.getSession(false);
	        map.put("list",rarocConfigService.listGuarantorMasterDoc());
	        return "xlsGuarantor";
	    		
	    }
	    @RequestMapping( value = "/guajqgrid",method = RequestMethod.POST)
		public String uploadGuarantorMasterFile(@RequestParam("file") MultipartFile file) {
	    	try {
				if (null == file) {
					throw new IOException("Invalid Input");
				}
				rarocConfigService.saveGuarantorMasterFile(file);
			} catch (IOException e) {
				return e.getMessage();
			}
			 return "raroc/rarocConfigMain";
		}
	    @RequestMapping(value = "/grid/listOthIncomeMaster", method = RequestMethod.GET)
	    public @ResponseBody
	    GridPage<RarocConfigModel> listOthIncomeMaster(
	            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
	            @RequestParam(value = "max", required = false, defaultValue = "20") int max,
	            @RequestParam(value = "sidx", required = false) String sidx,
	            @RequestParam(value = "sord", required = false) String sord,
	            @RequestParam(value = "searchField", required = false) String searchField,
	            @RequestParam(value = "searchOper", required = false) String searchOper,
	            @RequestParam(value = "searchString", required = false) String searchString,
	            HttpServletRequest request, HttpServletResponse response) throws CustomException {
	        csrfTokenService.removeTokenFromSession(request);
	        response.setHeader("_tk", csrfTokenService.getTokenFromSession(request));
	        GridPage<RarocConfigModel> gridList = rarocConfigService.listOthIncomeMaster(page, max, sidx, sord, searchField, searchOper, searchString, request);
	        return gridList;
	    }
	    
	    @RequestMapping(value = "/listOthIncomeMaster/doc", method = RequestMethod.GET)
	    public String listOthIncomeMasterDoc(Map<String, Object> map, HttpServletRequest request) {
	        HttpSession session = request.getSession(false);
	        map.put("list",rarocConfigService.listOthIncomeMasterDoc());
	        return "xlsOthIncome";
	    		
	    }
	    @RequestMapping( value = "/imjqgrid",method = RequestMethod.POST)
		public String uploadOthIncomeMasterFile(@RequestParam("file") MultipartFile file) {
	    	try {
				if (null == file) {
					throw new IOException("Invalid Input");
				}
				rarocConfigService.saveOthIncomeMasterFile(file);
			} catch (IOException e) {
				return e.getMessage();
			}
			 return "raroc/rarocConfigMain";
		}
	    
	    @RequestMapping(value = "/grid/listCCFMaster", method = RequestMethod.GET)
	    public @ResponseBody
	    GridPage<RarocConfigModel> listCCFMaster(
	            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
	            @RequestParam(value = "max", required = false, defaultValue = "20") int max,
	            @RequestParam(value = "sidx", required = false) String sidx,
	            @RequestParam(value = "sord", required = false) String sord,
	            @RequestParam(value = "searchField", required = false) String searchField,
	            @RequestParam(value = "searchOper", required = false) String searchOper,
	            @RequestParam(value = "searchString", required = false) String searchString,
	            HttpServletRequest request, HttpServletResponse response) throws CustomException {
	        csrfTokenService.removeTokenFromSession(request);
	        response.setHeader("_tk", csrfTokenService.getTokenFromSession(request));
	        GridPage<RarocConfigModel> gridList = rarocConfigService.listCCFMaster(page, max, sidx, sord, searchField, searchOper, searchString, request);
	        return gridList;
	    }
	    
	    @RequestMapping(value = "/listCCFMaster/doc", method = RequestMethod.GET)
	    public String listCCFMasterDoc(Map<String, Object> map, HttpServletRequest request) {
	        HttpSession session = request.getSession(false);
	        map.put("list",rarocConfigService.listCCFMasterDoc());
	        return "xlsCCFMaster";
	    		
	    }
	    @RequestMapping( value = "/ccfjqgrid",method = RequestMethod.POST)
		public String uploadCCFMasterFile(@RequestParam("file") MultipartFile file) {
	    	try {
				if (null == file) {
					throw new IOException("Invalid Input");
				}
				rarocConfigService.saveCCFMasterFile(file);
			} catch (IOException e) {
				return e.getMessage();
			}
			 return "raroc/rarocConfigMain";
		}
	    
	    @RequestMapping(value = "/grid/listOperatingExpenseMaster", method = RequestMethod.GET)
	    public @ResponseBody
	    GridPage<RarocConfigModel> listOperatingExpenseMaster(
	            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
	            @RequestParam(value = "max", required = false, defaultValue = "20") int max,
	            @RequestParam(value = "sidx", required = false) String sidx,
	            @RequestParam(value = "sord", required = false) String sord,
	            @RequestParam(value = "searchField", required = false) String searchField,
	            @RequestParam(value = "searchOper", required = false) String searchOper,
	            @RequestParam(value = "searchString", required = false) String searchString,
	            HttpServletRequest request, HttpServletResponse response) throws CustomException {
	        csrfTokenService.removeTokenFromSession(request);
	        response.setHeader("_tk", csrfTokenService.getTokenFromSession(request));
	        GridPage<RarocConfigModel> gridList = rarocConfigService.listOperatingExpenseMaster(page, max, sidx, sord, searchField, searchOper, searchString, request);
	        return gridList;
	    }
	    
	    @RequestMapping(value = "/listOperatingExpenseMaster/doc", method = RequestMethod.GET)
	    public String listOperatingExpenseMasterDoc(Map<String, Object> map, HttpServletRequest request) {
	        HttpSession session = request.getSession(false);
	        map.put("list",rarocConfigService.listOperatingExpenseMasterDoc());
	        return "xlsOperatingExpenseMaster";
	    		
	    }
	    
	    @RequestMapping( value = "/oejqgrid",method = RequestMethod.POST)
	  		public String uploadOperatingExpenseMasterFile(@RequestParam("file") MultipartFile file) {
	  	    	try {
	  				if (null == file) {
	  					throw new IOException("Invalid Input");
	  				}
	  				rarocConfigService.saveOperatingExpenseMasterFile(file);
	  			} catch (IOException e) {
	  				return e.getMessage();
	  			}
	  			 return "raroc/rarocConfigMain";
	  		}
	    
	    @RequestMapping(value = "/grid/listRestructuredMaster", method = RequestMethod.GET)
	    public @ResponseBody
	    GridPage<RarocConfigModel> listRestructuredMaster(
	            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
	            @RequestParam(value = "max", required = false, defaultValue = "20") int max,
	            @RequestParam(value = "sidx", required = false) String sidx,
	            @RequestParam(value = "sord", required = false) String sord,
	            @RequestParam(value = "searchField", required = false) String searchField,
	            @RequestParam(value = "searchOper", required = false) String searchOper,
	            @RequestParam(value = "searchString", required = false) String searchString,
	            HttpServletRequest request, HttpServletResponse response) throws CustomException {
	        csrfTokenService.removeTokenFromSession(request);
	        response.setHeader("_tk", csrfTokenService.getTokenFromSession(request));
	        GridPage<RarocConfigModel> gridList = rarocConfigService.listRestructuredMaster(page, max, sidx, sord, searchField, searchOper, searchString, request);
	        return gridList;
	    }
	    
	    @RequestMapping(value = "/listRestructuredMaster/doc", method = RequestMethod.GET)
	    public String listRestructuredMasterDoc(Map<String, Object> map, HttpServletRequest request) {
	        HttpSession session = request.getSession(false);
	        map.put("list",rarocConfigService.listRestructuredMasterDoc());
	        return "xlsRestructuredMaster";
	    		
	    }
	    
	    @RequestMapping( value = "/rmjqgrid",method = RequestMethod.POST)
  		public String uploadRestructuredMasterFile(@RequestParam("file") MultipartFile file) {
  	    	try {
  				if (null == file) {
  					throw new IOException("Invalid Input");
  				}
  				rarocConfigService.saveRestructuredMasterFile(file);
  			} catch (IOException e) {
  				return e.getMessage();
  			}
  			 return "raroc/rarocConfigMain";
  		}
	    
	    @RequestMapping(value = "/grid/listSensitivityIterationMaster", method = RequestMethod.GET)
	    public @ResponseBody
	    GridPage<RarocConfigModel> listSensitivityIterationMaster(
	            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
	            @RequestParam(value = "max", required = false, defaultValue = "20") int max,
	            @RequestParam(value = "sidx", required = false) String sidx,
	            @RequestParam(value = "sord", required = false) String sord,
	            @RequestParam(value = "searchField", required = false) String searchField,
	            @RequestParam(value = "searchOper", required = false) String searchOper,
	            @RequestParam(value = "searchString", required = false) String searchString,
	            HttpServletRequest request, HttpServletResponse response) throws CustomException {
	        csrfTokenService.removeTokenFromSession(request);
	        response.setHeader("_tk", csrfTokenService.getTokenFromSession(request));
	        GridPage<RarocConfigModel> gridList = rarocConfigService.listSensitivityIterationMaster(page, max, sidx, sord, searchField, searchOper, searchString, request);
	        return gridList;
	    }
	    
	    @RequestMapping(value = "/listSensitivityIterationMaster/doc", method = RequestMethod.GET)
	    public String listSensitivityIterationDoc(Map<String, Object> map, HttpServletRequest request) {
	        HttpSession session = request.getSession(false);
	        map.put("list",rarocConfigService.listSensitivityIterationMasterDoc());
	        return "xlsSensitivityIteration";
	    		
	    }
	    
	    @RequestMapping( value = "/smjqgrid",method = RequestMethod.POST)
  		public String uploadSensitivityIterationFile(@RequestParam("file") MultipartFile file) {
  	    	try {
  				if (null == file) {
  					throw new IOException("Invalid Input");
  				}
  				rarocConfigService.saveSensitivityIterationFile(file);
  			} catch (IOException e) {
  				return e.getMessage();
  			}
  			 return "raroc/rarocConfigMain";
  		}
	    
	    //Asset Type
	    
	    @RequestMapping(value = "/grid/listAssetType", method = RequestMethod.GET)
	    public @ResponseBody
	    GridPage<RarocConfigModel> listAssetType(
	            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
	            @RequestParam(value = "max", required = false, defaultValue = "20") int max,
	            @RequestParam(value = "sidx", required = false) String sidx,
	            @RequestParam(value = "sord", required = false) String sord,
	            @RequestParam(value = "searchField", required = false) String searchField,
	            @RequestParam(value = "searchOper", required = false) String searchOper,
	            @RequestParam(value = "searchString", required = false) String searchString,
	            HttpServletRequest request, HttpServletResponse response) throws CustomException {
	        csrfTokenService.removeTokenFromSession(request);
	        response.setHeader("_tk", csrfTokenService.getTokenFromSession(request));
	        GridPage<RarocConfigModel> gridList = rarocConfigService.listAssetType(page, max, sidx, sord, searchField, searchOper, searchString, request);
	        return gridList;
	    }
	    
	    @RequestMapping(value = "/listAssetType/doc", method = RequestMethod.GET)
	    public String listAssetTypeDoc(Map<String, Object> map, HttpServletRequest request) {
	        HttpSession session = request.getSession(false);
	        map.put("list",rarocConfigService.listAssetTypeDoc());
	        return "xlsAssetType";
	    		
	    }
	    
	    @RequestMapping( value = "/atjqgrid",method = RequestMethod.POST)
		public String uploadAssetTypeFile(@RequestParam("file") MultipartFile file) {
	    	try {
				if (null == file) {
					throw new IOException("Invalid Input");
				}
				rarocConfigService.saveAssetTypeFile(file);
			} catch (IOException e) {
				return e.getMessage();
			}
			 return "raroc/rarocConfigMain";
		}
	    
	    //Business Unit
	    
	    @RequestMapping(value = "/grid/listBusinessUnit", method = RequestMethod.GET)
	    public @ResponseBody
	    GridPage<RarocConfigModel> listBusinessUnit(
	            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
	            @RequestParam(value = "max", required = false, defaultValue = "20") int max,
	            @RequestParam(value = "sidx", required = false) String sidx,
	            @RequestParam(value = "sord", required = false) String sord,
	            @RequestParam(value = "searchField", required = false) String searchField,
	            @RequestParam(value = "searchOper", required = false) String searchOper,
	            @RequestParam(value = "searchString", required = false) String searchString,
	            HttpServletRequest request, HttpServletResponse response) throws CustomException {
	        csrfTokenService.removeTokenFromSession(request);
	        response.setHeader("_tk", csrfTokenService.getTokenFromSession(request));
	        GridPage<RarocConfigModel> gridList = rarocConfigService.listBusinessUnit(page, max, sidx, sord, searchField, searchOper, searchString, request);
	        return gridList;
	    }
	    
	    @RequestMapping(value = "/listBusinessUnit/doc", method = RequestMethod.GET)
	    public String listBusinessUnitDoc(Map<String, Object> map, HttpServletRequest request) {
	        HttpSession session = request.getSession(false);
	        map.put("list",rarocConfigService.listBusinessUnitDoc());
	        return "xlsBusinessUnit";
	    		
	    }
	    
	    @RequestMapping( value = "/bujqgrid",method = RequestMethod.POST)
		public String uploadBusinessUnitFile(@RequestParam("file") MultipartFile file) {
	    	try {
				if (null == file) {
					throw new IOException("Invalid Input");
				}
				rarocConfigService.saveBusinessUnitFile(file);
			} catch (IOException e) {
				return e.getMessage();
			}
			 return "raroc/rarocConfigMain";
		}
	    
	    
	    //FIN Haircut
	    
	    
	    @RequestMapping(value = "/grid/listFinHaircut", method = RequestMethod.GET)
	    public @ResponseBody
	    GridPage<RarocConfigModel> listFinHaircut(
	            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
	            @RequestParam(value = "max", required = false, defaultValue = "20") int max,
	            @RequestParam(value = "sidx", required = false) String sidx,
	            @RequestParam(value = "sord", required = false) String sord,
	            @RequestParam(value = "searchField", required = false) String searchField,
	            @RequestParam(value = "searchOper", required = false) String searchOper,
	            @RequestParam(value = "searchString", required = false) String searchString,
	            HttpServletRequest request, HttpServletResponse response) throws CustomException {
	        csrfTokenService.removeTokenFromSession(request);
	        response.setHeader("_tk", csrfTokenService.getTokenFromSession(request));
	        GridPage<RarocConfigModel> gridList = rarocConfigService.listFinHaircut(page, max, sidx, sord, searchField, searchOper, searchString, request);
	        return gridList;
	    }
	    
	    @RequestMapping(value = "/listFinHaircut/doc", method = RequestMethod.GET)
	    public String listFinHaircutDoc(Map<String, Object> map, HttpServletRequest request) {
	        HttpSession session = request.getSession(false);
	        map.put("list",rarocConfigService.listFinHaircutDoc());
	        return "xlsFinHaircut";
	    		
	    }
	    
	    @RequestMapping( value = "/fhjqgrid",method = RequestMethod.POST)
		public String uploadFinHaircutFile(@RequestParam("file") MultipartFile file) {
	    	try {
				if (null == file) {
					throw new IOException("Invalid Input");
				}
				rarocConfigService.saveFinHaircutFile(file);
			} catch (IOException e) {
				return e.getMessage();
			}
			 return "raroc/rarocConfigMain";
		}
	    
//Internal Rating Model
	    
	    @RequestMapping(value = "/grid/listInternalRatingModel", method = RequestMethod.GET)
	    public @ResponseBody
	    GridPage<RarocConfigModel> listInternalRatingModel(
	            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
	            @RequestParam(value = "max", required = false, defaultValue = "20") int max,
	            @RequestParam(value = "sidx", required = false) String sidx,
	            @RequestParam(value = "sord", required = false) String sord,
	            @RequestParam(value = "searchField", required = false) String searchField,
	            @RequestParam(value = "searchOper", required = false) String searchOper,
	            @RequestParam(value = "searchString", required = false) String searchString,
	            HttpServletRequest request, HttpServletResponse response) throws CustomException {
	        csrfTokenService.removeTokenFromSession(request);
	        response.setHeader("_tk", csrfTokenService.getTokenFromSession(request));
	        GridPage<RarocConfigModel> gridList = rarocConfigService.listInternalRatingModel(page, max, sidx, sord, searchField, searchOper, searchString, request);
	        return gridList;
	    }
	    
	    @RequestMapping(value = "/listInternalRatingModel/doc", method = RequestMethod.GET)
	    public String listInternalRatingModelDoc(Map<String, Object> map, HttpServletRequest request) {
	        HttpSession session = request.getSession(false);
	        map.put("list",rarocConfigService.listInternalRatingModelDoc());
	        return "xlsInternalRatingModel";
	    		
	    }
	    
	    @RequestMapping( value = "/irmjqgrid",method = RequestMethod.POST)
		public String uploadInternalRatingModelFile(@RequestParam("file") MultipartFile file) {
	    	try {
				if (null == file) {
					throw new IOException("Invalid Input");
				}
				rarocConfigService.saveInternalRatingModelFile(file);
			} catch (IOException e) {
				return e.getMessage();
			}
			 return "raroc/rarocConfigMain";
		}
	    
	    
//Internal Rating Model
	    
	    @RequestMapping(value = "/grid/listRatingModelMapping", method = RequestMethod.GET)
	    public @ResponseBody
	    GridPage<RarocConfigModel> listRatingModelMapping(
	            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
	            @RequestParam(value = "max", required = false, defaultValue = "20") int max,
	            @RequestParam(value = "sidx", required = false) String sidx,
	            @RequestParam(value = "sord", required = false) String sord,
	            @RequestParam(value = "searchField", required = false) String searchField,
	            @RequestParam(value = "searchOper", required = false) String searchOper,
	            @RequestParam(value = "searchString", required = false) String searchString,
	            HttpServletRequest request, HttpServletResponse response) throws CustomException {
	        csrfTokenService.removeTokenFromSession(request);
	        response.setHeader("_tk", csrfTokenService.getTokenFromSession(request));
	        GridPage<RarocConfigModel> gridList = rarocConfigService.listRatingModelMapping(page, max, sidx, sord, searchField, searchOper, searchString, request);
	        return gridList;
	    }
	    
	    @RequestMapping(value = "/listRatingModelMapping/doc", method = RequestMethod.GET)
	    public String listRatingModelMappingDoc(Map<String, Object> map, HttpServletRequest request) {
	        HttpSession session = request.getSession(false);
	        map.put("list",rarocConfigService.listRatingModelMappingDoc());
	        return "xlsRatingModelMapping";
	    		
	    }
	    
	    @RequestMapping( value = "/rmmjqgrid",method = RequestMethod.POST)
		public String uploadRatingModelMappingFile(@RequestParam("file") MultipartFile file) {
	    	try {
				if (null == file) {
					throw new IOException("Invalid Input");
				}
				rarocConfigService.saveRatingModelMappingFile(file);
			} catch (IOException e) {
				return e.getMessage();
			}
			 return "raroc/rarocConfigMain";
		}
	    
	    
}
