package service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
