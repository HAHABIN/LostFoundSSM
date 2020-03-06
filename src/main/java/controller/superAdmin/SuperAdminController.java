package controller.superAdmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/superadminPage", method = { RequestMethod.GET, RequestMethod.POST })
public class SuperAdminController {

	@RequestMapping(value = "/areamanage", method = RequestMethod.GET)
	private String areamanagement() {
		// 区域管理页
		return "superadmin/areamanage";
	}

	@RequestMapping(value = "/headlinemanage", method = RequestMethod.GET)
	private String headLinemanagement() {
		// 头条管理页
		return "superadmin/headlinemanage";
	}

	@RequestMapping(value = "/articletypemanage", method = RequestMethod.GET)
	private String articleTypeManage() {
		// 发布类别管理页
		return "superadmin/articletypemanage";
	}

	@RequestMapping(value = "/articleinfomanage", method = RequestMethod.GET)
	private String articleInfoManage() {
		// 发布信息管理页
		return "superadmin/articleinfomanage";
	}

	@RequestMapping(value = "/personinfomanage", method = RequestMethod.GET)
	private String personInfomanage() {
		// 用户信息管理页
		return "superadmin/personinfomanage";
	}

	@RequestMapping(value = "/main", method = RequestMethod.GET)
	private String main() {
		// 超级管理员主页
		return "superadmin/main";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	private String login() {
		// 超级管理员登录页
		return "superadmin/login";
	}
}
