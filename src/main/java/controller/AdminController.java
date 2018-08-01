package controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import model.Admin;
import service.IAdminService;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

	@Autowired
	private IAdminService adminService;

	@RequestMapping
	public String checkLkogin() {
		return "admin/adminLogin";

	}

	@RequestMapping(value = "/login")
	public String adminLogin(Admin admin, String remember,
			HttpServletRequest req, HttpServletResponse resp) {
		Admin loginAdmin = adminService.login(admin);
		if (loginAdmin != null) {

			if (remember != null) {

				Cookie cookie_email = new Cookie("email", admin.getEmail());
				Cookie cookie_pass = new Cookie("pass", admin.getPass());

				cookie_email.setMaxAge(20);
				cookie_pass.setMaxAge(20);

				resp.addCookie(cookie_pass);
				resp.addCookie(cookie_email);
			}
			req.getSession().setAttribute("admin", loginAdmin);

			return "admin/adminInfo";
		}

		req.setAttribute("msg", "”√ªß√˚√‹¬Î¥ÌŒÛ");

		return "admin/adminLogin";
	}

}
