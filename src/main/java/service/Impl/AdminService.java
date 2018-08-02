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
		//savePathΪ�ļ��洢·��  /upload
		//filePathΪ�ļ�·��  /upload+�ļ���
		String filePath = savePath + File.separator + file.getOriginalFilename();
		
		File saveFile  = new File(filePath);
		//�ж��ļ��ĸ���·���Ƿ���� �������� ����
		if (!saveFile.getParentFile().exists()) {
			saveFile.getParentFile().mkdirs();
		}
		try {
			//д���ļ�  �൱���������write����
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
