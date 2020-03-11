package com.woniuxy.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.sql.visitor.functions.Now;
import com.woniuxy.pojo.Message;
import com.woniuxy.pojo.PageBean;
import com.woniuxy.pojo.Role;
import com.woniuxy.pojo.Users;
import com.woniuxy.service.IRoleService;
import com.woniuxy.service.IUserService;

@Controller
@RequestMapping("user")
public class UserController {
	@Autowired
	IUserService us;
	@Autowired
	IRoleService rs;
//	@RequestMapping("index")
//	public String index(Model model,PageBean<Users> pageBean) {
//		//第一次进来时，没有任何数据提交个pageBean对象，这时pagebean为null
//		//设置当前页
//		if (pageBean.getCurrentPage()==null) {
//			pageBean.setCurrentPage(1);
//		}
//		//设置每页显示的条目数
//		pageBean.setPageSize(5);
//		//获得数据表中的所有用户信息
//		List<Users> userList = us.findByPage(pageBean);
//		//设置每页显示的数据
//		pageBean.setList(userList);
//		//设置总行数
//		int totalCount = us.countAll();
//		pageBean.setTotalCount(totalCount);
//		//设置总页数
//		int pages = pageBean.getTotalCount()%pageBean.getPageSize()==0?pageBean.getTotalCount()/pageBean.getPageSize():pageBean.getTotalCount()/pageBean.getPageSize()+1;
//		pageBean.setPages(pages);
//		model.addAttribute("pageBean", pageBean);
//		return "user/index";
//	}
	
	
	@RequestMapping("index")
	public String index() {
		return "user/index";
	}
	@RequestMapping("save")
	public String save() {
		return "user/save";
	}
	
	@RequestMapping("assignRole")
	public String assignRole(Integer uid,Model model) {
		
//		//查找角色表中所有的角色
//		List<Role> roleList = roleService.findAll();
//		//查找该用户已有的角色
//		List<Role> assignRoleList = roleService.selectRolesByUid(uid);
//		//查找该用户未分配的角色
//		List<Role> unAssignRoleList = new ArrayList<Role>();
//		//循环所有的已分配角色
//		for (Role role : assignRoleList) {
//			//所有角色是否包含已分配角色
//			if(roleList.contains(role)) {
//				System.out.println("UserController.assignRole()"+role);
//				//从所有角色中移除已分配角色
//				roleList.remove(role);
//				
//			}
//		}
		
		List<Role> roleList = rs.selectAll(uid);
		List<Role> roleAllocation = rs.findAllocation(uid);
		model.addAttribute("uid", uid);
		model.addAttribute("roleList", roleList);
		model.addAttribute("roleAllocation", roleAllocation);
		return "user/assignRole";
	}
	
	@RequestMapping("assignRoleDo")
	@ResponseBody
	public Object assignRoleDo(Integer uid,Integer rid) {
		Message message = new Message();
		try {
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("uid", uid);
			map.put("rid", rid);
			us.assignRoles(map);
			message.setFlag(true);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			message.setFlag(false);
		}
		return message;
	}
	
	@RequestMapping("assignRoleDel")
	@ResponseBody
	public Object assignRoleDel(Integer rid,Integer uid) {
		Message message = new Message();
		try {
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("uid", uid);
			map.put("rid", rid);
			us.assignRoleDel(map);
			message.setFlag(true);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			message.setFlag(false);
		}
		return message;
	}
	@RequestMapping("update")
	public String update(Integer uid,Model model) {
		Message message = new Message();
		try {
			Users users = us.findOne(uid);
			message.setFlag(true);
			model.addAttribute("users", users);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			message.setFlag(false);
		}
		return "user/update";
	}
	
	//ajax操作
	@RequestMapping("indexajax")
	@ResponseBody
	public Object indexajax(PageBean<Users> pageBean) {
		Message message = new Message();
		try {
			//第一次进来时，没有任何数据提交个pageBean对象，这时pagebean为null
			//设置当前页
			if (pageBean.getCurrentPage()==null) {
				pageBean.setCurrentPage(1);
			}
			//设置每页显示的条目数
			pageBean.setPageSize(5);
			//获得数据表中的所有用户信息
			List<Users> userList = us.findByPage(pageBean);
			//设置每页显示的数据
			pageBean.setList(userList);
			//设置总行数
			int totalCount = us.countAll(pageBean);
			pageBean.setTotalCount(totalCount);
			//设置总页数
			int pages = pageBean.getTotalCount()%pageBean.getPageSize()==0?pageBean.getTotalCount()/pageBean.getPageSize():pageBean.getTotalCount()/pageBean.getPageSize()+1;
			pageBean.setPages(pages);
			message.setFlag(true);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			message.setFlag(false);	
		}
		message.setObj(pageBean);
		return message;
	}
	@RequestMapping("saveUser")
	@ResponseBody
	public Object saveUser(Users users) {
		Message message = new Message();
		try {
			users.setUpwd("123456");
			users.setUdate(new Date());
			us.saveUser(users);
			message.setFlag(true);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			message.setFlag(false);
		}
		return message;
	}
	@RequestMapping("updateUser")
	@ResponseBody
	public Object updateUser(Users users) {
		Message message = new Message();
		try {
			us.updateUser(users);
			message.setFlag(true);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			message.setFlag(false);
		}
		return message;
	}
	@RequestMapping("deleteUser")
	@ResponseBody
	public Object deleteUser(Integer uid) {
		Message message = new Message();
		try {
			us.deleteUser(uid);
			message.setFlag(true);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			message.setFlag(false);
		}
		return message;
	}
	@RequestMapping("delUsers")
	@ResponseBody
	public Object delUsers(Integer[] uids) {
		Message message = new Message();
		for (Integer integer : uids) {
			System.out.println(integer);
		}
		try {
			us.delUsers(uids);
			message.setFlag(true);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			message.setFlag(false);
		}
		return message;
	}
}
