package io.github.ttallaemideul.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import io.github.ttallaemideul.base.BaseController;
import io.github.ttallaemideul.base.ResultData;
import io.github.ttallaemideul.config.SysProperty;
import io.github.ttallaemideul.service.admin.AdminService;
import io.github.ttallaemideul.utils.UtilString;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/admin")
@Slf4j
public class AdminController extends BaseController {

	@Autowired
	private AdminService adminService;
	
	@GetMapping
	public String main(Model model, @RequestParam(required = false, defaultValue = "1") String page //
			, @RequestParam(required = false, defaultValue = "10") String rows) {
		Map<String, Object> param = new HashMap<>();
		model.addAllAttributes(adminService.getUserList(UtilString.parseInt(page,1), UtilString.parseInt(rows,10), param));
		return "web/admin/admin_main";
	}
	
	@GetMapping("/dummyInsert")
	@ResponseBody
	public ResultData dummyInsert() {
		ResultData resultData = getResultData();
		try {
			int cnt = adminService.insertDummyUser();
			resultData.getData().put("cnt", cnt);
		} catch(Exception e) {
			log.error(e.getMessage(), e);
			resultData.setStatus(SysProperty.RESULT_ERROR);
			if(log.isDebugEnabled()) {
				resultData.setMessage(e.getMessage());
			}
		}
		return resultData;
	}
}
