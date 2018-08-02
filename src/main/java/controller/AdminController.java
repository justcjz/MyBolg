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
	 * ����½״̬����ȡ���cookie
	 * 
	 * @param req request����
	 * 
	 * @return ��cookie��֤��Ч���Զ���½��������ת��adminLogin����
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
	 * ����Ա��½����������ӱ����½��Ϣ��cookie
	 * 
	 * @param admin ʵ������󣬴洢ҳ������ݵĲ���
	 * @param remember �Ƿ����cookie�ı�־��
	 * @param req request����
	 * @param resp response����
	 * 
	 * @return ��½�ɹ�-adminInfo.jsp ��½ʧ��-adminLogin.jsp
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

		req.setAttribute("msg", "�û����������");

		return "admin/adminLogin";
	}

	/**
	 * 
	 * ע����½��ɾ���洢�ĵ�½��Ϣ
	 * 
	 * @param req request����
	 * @param resp response����
	 * @return ��ת����½����
	 */
	
	@RequestMapping(value ="/logout")
	public String adminLogOut(HttpServletRequest req,HttpServletResponse resp){
//		�����Ҫ���ȫ������
//		req.getSession().invalidate();
		//�����������
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
	 * @param admin ӳ����������ڴ洢������
	 * @param file ��ý���ļ������û��洢�ϴ��ļ�����
	 * @param req	request����
	 * @return �ض���ҳ��
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
		req.setAttribute("msg", "�޸ĳɹ�");
				return "redirect:/admin";
		
	}
}
