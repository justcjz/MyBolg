package service;

import org.springframework.web.multipart.MultipartFile;

import model.Admin;

public interface IAdminService {

	/**
	 * 验证登陆
	 * @param admin admin对象，用于存储数据
	 * @return admin对象，用于返回查询信息
	 */
	Admin login(Admin admin);
	
	/**
	 * 验证登陆
	 * @param email 邮箱信息
	 * @param pass 密码信息
	 * @return admin对象，用于返回查询信息
	 */
	Admin login(String email, String pass);
	
	/**
	 * 上传单个文件到服务器
	 * @param savePath 文件的存储路径
	 * @param file 文件对象
	 */
	void uploadImg(String savePath, MultipartFile file);
	
	/**
	 * 修改admin信息
	 * @param admin 映射类对象，用于存储要修改的信息
	 */
	void updateAdmin(Admin admin);
}
