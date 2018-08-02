package service;

import org.springframework.web.multipart.MultipartFile;

import model.Admin;

public interface IAdminService {

	/**
	 * ��֤��½
	 * @param admin admin�������ڴ洢����
	 * @return admin�������ڷ��ز�ѯ��Ϣ
	 */
	Admin login(Admin admin);
	
	/**
	 * ��֤��½
	 * @param email ������Ϣ
	 * @param pass ������Ϣ
	 * @return admin�������ڷ��ز�ѯ��Ϣ
	 */
	Admin login(String email, String pass);
	
	/**
	 * �ϴ������ļ���������
	 * @param savePath �ļ��Ĵ洢·��
	 * @param file �ļ�����
	 */
	void uploadImg(String savePath, MultipartFile file);
	
	/**
	 * �޸�admin��Ϣ
	 * @param admin ӳ����������ڴ洢Ҫ�޸ĵ���Ϣ
	 */
	void updateAdmin(Admin admin);
}
