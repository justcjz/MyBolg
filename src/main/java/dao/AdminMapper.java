package dao;

import model.Admin;

public interface AdminMapper {
    int deleteByPrimaryKey(Integer amId);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(Integer amId);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);
    
    Admin login(Admin admin);
}