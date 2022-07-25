package atrix.st.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import atrix.common.service.CSRFTokenService;
import atrix.st.service.RarocConfigService;

@Controller
@RequestMapping(value = "/rarocMasterApprover")
public class RarocMasterApproverController {

    @Autowired
    private RarocConfigService rarocConfigService;
    @Autowired
    private CSRFTokenService csrfTokenService;

    @RequestMapping(method = RequestMethod.GET)
    public String approverPage(Map<String, Object> model) {
        return "master/rarocMasterApprover";
    }

    @RequestMapping(value = "/jqgrid", method = RequestMethod.POST)
    public String approveConfig() {
        rarocConfigService.approveConfig();
        return "master/rarocMasterApprover";
    }

    @RequestMapping(value = "/intjqgrid", method = RequestMethod.POST)
    public String approveInternalRating() {
        rarocConfigService.approveInternalRating();
        return "master/rarocMasterApprover";
    }

    @RequestMapping(value = "/extjqgrid", method = RequestMethod.POST)
    public String approveExternalRating() {
        rarocConfigService.approveExternalRating();
        return "master/rarocMasterApprover";
    }

    @RequestMapping(value = "/guajqgrid", method = RequestMethod.POST)
    public String approveGuarantor() {
        rarocConfigService.approveGuarantor();
        return "master/rarocMasterApprover";
    }

    @RequestMapping(value = "/imjqgrid", method = RequestMethod.POST)
    public String approveIncomeMater() {
        rarocConfigService.approveIncomeMater();
        return "master/rarocMasterApprover";
    }

    @RequestMapping(value = "/ccfjqgrid", method = RequestMethod.POST)
    public String approveCCFMaster() {
        rarocConfigService.approveCCFMaster();
        return "master/rarocMasterApprover";
    }

    @RequestMapping(value = "/oejqgrid", method = RequestMethod.POST)
    public String approveOperatingExpense() {
        rarocConfigService.approveOperatingExpense();
        return "master/rarocMasterApprover";
    }

    @RequestMapping(value = "/rmjqgrid", method = RequestMethod.POST)
    public String approveRestructuredMaster() {
        rarocConfigService.approveRestructuredMaster();
        return "master/rarocMasterApprover";
    }

    @RequestMapping(value = "/smjqgrid", method = RequestMethod.POST)
    public String approveSensitivityMaster() {
        rarocConfigService.approveSensitivityMaster();
        return "master/rarocMasterApprover";
    }

    @RequestMapping(value = "/atjqgrid", method = RequestMethod.POST)
    public String approveAssetType() {
        rarocConfigService.approveAssetType();
        return "master/rarocMasterApprover";
    }

    @RequestMapping(value = "/fhjqgrid", method = RequestMethod.POST)
    public String approveFINHaircut() {
        rarocConfigService.approveFINHaircut();
        return "master/rarocMasterApprover";
    }

    @RequestMapping(value = "/rmmjqgrid", method = RequestMethod.POST)
    public String approveRatingModelMapping() {
        rarocConfigService.approveRatingModelMapping();
        return "master/rarocMasterApprover";
    }

    @RequestMapping(value = "/irmjqgrid", method = RequestMethod.POST)
    public String approveInternalRatingMapping() {
        rarocConfigService.approveInternalRatingMapping();
        return "master/rarocMasterApprover";
    }

    @RequestMapping(value = "/bujqgrid", method = RequestMethod.POST)
    public String approveBusinessUnit() {
        rarocConfigService.approveBusinessUnit();
        return "master/rarocMasterApprover";
    }

    @RequestMapping(value = "/reject", method = RequestMethod.POST)
    public String rejectConfig(@RequestParam(value = "tablename") String tableName) {
        rarocConfigService.rejectConfig(tableName);
        return "master/rarocMasterApprover";
    }

}
