package com.woniuxy.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import com.woniuxy.pojo.PageBean;
import com.woniuxy.pojo.Permission;
import com.woniuxy.pojo.Users;

public interface UserMapper {
	//@Select("select * from users where uname=#{uname} and upwd=#{upwd}")
	Users findUser(Users users);

	List<Users> findAll();

	List<Users> findByPage(PageBean<Users> pageBean);

	int countAll(PageBean<Users> pageBean);

	void saveUser(Users users);

	void updateUser(Integer uid);

	Users findOne(Integer uid);

	void update(Users users);

	void delete(Integer uid);

	void delUsers(Integer[] uids);

	void assignRoles(Map<String, Object> map);

	void assignRoleDel(Map<String, Object> map);

	List<Permission> selectPermissionByUser(Users loginUser);

}
