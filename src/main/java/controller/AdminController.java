package controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import model.Admin;
import service.IAdminService;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

	@Autowired
	private IAdminService adminService;
	
	/**
	 * 检查登陆状态并读取相关cookie
	 * 
	 * @param req request对象
	 * 
	 * @return 若cookie验证有效，自动登陆，否则跳转到adminLogin界面
	 */

	@RequestMapping
	public String checkLkogin(HttpServletRequest req) {
		Cookie []cookies = req.getCookies();
		if (cookies!=null) {
			String email = null;
			String pass =null;
			for (Cookie cookie : cookies) {
				if ("email".equals(cookie.getName())) {
					email = cookie.getValue();
				}else if ("pass".equals(cookie.getName())) {
					pass = cookie.getValue();
				}
			}
			Admin admin = new Admin();
			admin.setEmail(email);
			admin.setPass(pass);
			Admin loginAdmin = adminService.login(admin);
			if (loginAdmin!=null) {
				req.getSession().setAttribute("admin", loginAdmin);
				return "admin/adminInfo";
			}
		}
		return "admin/adminLogin";
	}
	

	/**
	 * 管理员登陆，并可以添加保存登陆信息的cookie
	 * 
	 * @param admin 实体类对象，存储页面表单传递的参数
	 * @param remember 是否添加cookie的标志符
	 * @param req request对象
	 * @param resp response对象
	 * 
	 * @return 登陆成功-adminInfo.jsp 登陆失败-adminLogin.jsp
	 */

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

		req.setAttribute("msg", "用户名密码错误");

		return "admin/adminLogin";
	}

	/**
	 * 
	 * 注销登陆，删除存储的登陆信息
	 * 
	 * @param req request对象
	 * @param resp response对象
	 * @return 跳转到登陆界面
	 */
	
	@RequestMapping(value ="/logout")
	public String adminLogOut(HttpServletRequest req,HttpServletResponse resp){
//		如果想要清除全部数据
//		req.getSession().invalidate();
		//清除部分数据
		req.getSession().removeAttribute("admin");
		Cookie []cookies = req.getCookies();
		for (Cookie cookie : cookies) {
			if ("email".equals(cookie.getName())||"pass".equals(cookie.getName())) {
				cookie.setMaxAge(0);
				resp.addCookie(cookie);
			}
		}
		return "admin/adminLogin";
	}

	/**
	 * 
	 * @param admin 映射类对象，用于存储表单数据
	 * @param file 多媒体文件对象，用户存储上传文件数据
	 * @param req	request对象
	 * @return 重定向页面
	 */
	@RequestMapping(value="/updateAdmin", method = RequestMethod.POST)
	public String updateAdmin(
			Admin admin,
			@RequestParam(value="imgFile",required= false)
	        MultipartFile file,
	        HttpServletRequest req){
		System.out.println(admin);
		if (!file.isEmpty()) {
			String savePath = req.getServletContext().getRealPath("upload");
			System.out.println("savepath"+savePath);
			adminService.uploadImg(savePath,file);
			admin.setImg("/upload/" + file.getOriginalFilename());
		}
		adminService.updateAdmin(admin);
		req.setAttribute("msg", "修改成功");
				return "redirect:/admin";
		
	}
}
