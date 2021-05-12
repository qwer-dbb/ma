package cn.smbms.service.role;

import java.util.List;

import cn.smbms.dao.role.RoleDao;
import cn.smbms.pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleDao roleDao;

	@Override
	public List<Role> getRoleList() throws Exception {
		return roleDao.getRoleList();
	}
}
