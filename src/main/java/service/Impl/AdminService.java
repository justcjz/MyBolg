package service.Impl;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import dao.AdminMapper;

import model.Admin;
import service.IAdminService;
@Service
public class AdminService implements IAdminService {
	@Autowired
	private AdminMapper adminMapper;

	@Override
	public Admin login(Admin admin) {
		return adminMapper.login(admin);
	}

	@Override
	public Admin login(String email, String pass) {
		Admin admin = new Admin();
		admin.setEmail(email);
		admin.setPass(pass);
		return this.login(admin);
	}

	@Override
	public void uploadImg(String savePath, MultipartFile file) {
		//savePath为文件存储路径  /upload
		//filePath为文件路径  /upload+文件名
		String filePath = savePath + File.separator + file.getOriginalFilename();
		
		File saveFile  = new File(filePath);
		//判断文件的父级路径是否存在 若不存在 创建
		if (!saveFile.getParentFile().exists()) {
			saveFile.getParentFile().mkdirs();
		}
		try {
			//写入文件  相当于输出流的write方法
			file.transferTo(saveFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateAdmin(Admin admin) {
		adminMapper.updateByPrimaryKey(admin);
		
	}

}
