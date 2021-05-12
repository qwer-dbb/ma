package cn.smbms.service.user;

import java.util.List;

import cn.smbms.dao.user.UserDao;
import cn.smbms.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDao userDao;


	/**
	 * 增加用户信息
	 * @param user
	 * @return
	 */
	public boolean add(User user) throws Exception {
		int a=userDao.add(user);
		if (a>0){
			return true;
		}else {
			return false;
		}

	}

	/**
	 * 用户登录
	 * @param userCode
	 * @param userPassword
	 * @return
	 */
	public User login(String userCode, String userPassword) {
		return userDao.login(userCode,userPassword);
	}

	/**
	 * 根据条件查询用户列表
	 * @param queryUserName
	 * @param queryUserRole
	 * @return
	 */
	public List<User> getUserList(String queryUserName, Integer queryUserRole, Integer currentPageNo, Integer pageSize) throws Exception {
		return userDao.getUserList(queryUserName,queryUserRole,currentPageNo,pageSize);
	}

	/**
	 * 根据条件查询用户表记录数
	 * @param queryUserName
	 * @param queryUserRole
	 * @return
	 */
	public int getUserCount(String queryUserName, Integer queryUserRole) throws Exception {
		return userDao.getUserCount(queryUserName,queryUserRole);
	}

	/**
	 * 根据userCode查询出User
	 * @param userCode
	 * @return
	 */
	public User selectUserCodeExist(String userCode) throws Exception {
		return userDao.getLoginUser(userCode);
	}

	/**
	 * 根据ID删除user
	 * @param delId
	 * @return
	 */
	public boolean deleteUserById(Integer delId) throws Exception {
		int byId = userDao.deleteUserById(delId);
		if (byId>0){
			return true;
		}else {
			return false;
		}
	}

	/**
	 * 根据ID查找user
	 * @param id
	 * @return
	 */
	public User getUserById(Integer id) throws Exception {
		return userDao.getUserById(id);
	}

	@Override
	public boolean modify(User user) throws Exception {
		int modify = userDao.modify(user);
		if (modify>0){
			return true;
		}else {
			return false;
		}
	}

	/**
	 * 根据userId修改密码
	 * @param id
	 * @param pwd
	 * @return
	 */
	public boolean updatePwd(int id, String pwd) throws Exception {
		int a=userDao.updatePwd(id,pwd);
		if (a>0){
			return true;
		}
		return false;
	}
}
