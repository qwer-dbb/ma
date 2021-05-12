package cn.smbms.controller;

import cn.smbms.pojo.Role;
import cn.smbms.service.role.RoleService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/Role")
public class RoleController{
    @Autowired
    RoleService roleSerivce;

    @RequestMapping("/getlist")
    @ResponseBody
    public  String getlist() throws Exception {

        List<Role> list = roleSerivce.getRoleList();
        return JSON.toJSONString(list);
    }

}
