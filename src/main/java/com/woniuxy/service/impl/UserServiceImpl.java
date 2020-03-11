package com.woniuxy.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.woniuxy.dao.UserMapper;
import com.woniuxy.pojo.PageBean;
import com.woniuxy.pojo.Permission;
import com.woniuxy.pojo.Users;
import com.woniuxy.service.IUserService;
@Service
@Transactional
public class UserServiceImpl implements IUserService {
	@Autowired
	UserMapper userDao;

	@Override
	public Users findUser(Users users) {
		// TODO Auto-generated method stub
		Users loginUser = userDao.findUser(users);
		return loginUser;
	}

	@Override
	public List<Users> findAll() {
		// TODO Auto-generated method stub
		List<Users> userList = userDao.findAll();
		return userList;
	}

	@Override
	public List<Users> findByPage(PageBean<Users> pageBean) {
		// TODO Auto-generated method stub
		List<Users> userList = userDao.findByPage(pageBean);
		return userList;
	}

	@Override
	public int countAll(PageBean<Users> pageBean) {
		// TODO Auto-generated method stub
		int totalCount = userDao.countAll(pageBean);
		return totalCount;
	}

	@Override
	public void saveUser(Users users) {
		// TODO Auto-generated method stub
		userDao.saveUser(users);
	}

	@Override
	public void update(Integer uid) {
		// TODO Auto-generated method stub
		userDao.updateUser(uid);
	}

	@Override
	public Users findOne(Integer uid) {
		// TODO Auto-generated method stub
		Users users = userDao.findOne(uid);
		return users;
	}

	@Override
	public void updateUser(Users users) {
		// TODO Auto-generated method stub
		userDao.update(users);
	}

	@Override
	public void deleteUser(Integer uid) {
		// TODO Auto-generated method stub
		userDao.delete(uid);
	}

	@Override
	public void delUsers(Integer[] uids) {
		// TODO Auto-generated method stub
		userDao.delUsers(uids);
	}

	@Override
	public void assignRoles(Map<String, Object> map) {
		// TODO Auto-generated method stub
		userDao.assignRoles(map);
	}

	@Override
	public void assignRoleDel(Map<String, Object> map) {
		// TODO Auto-generated method stub
		userDao.assignRoleDel(map);
	}

	@Override
	public List<Permission> selectPermissionByUser(Users loginUser) {
		// TODO Auto-generated method stub
		List<Permission> permissionList = userDao.selectPermissionByUser(loginUser);
		return permissionList;
	}
}
