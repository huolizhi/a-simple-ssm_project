package lz.service;

import lz.dao.SysUserDao;
import lz.domain.Role;
import lz.domain.SysUser;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/*
* 给框架提供数据，必须继承UserDetailsService接口，提供了方法
* */
public interface SysUserService extends UserDetailsService {


    List<SysUser> findAll();

    void addUser(SysUser sysUser);

    SysUser findDetailByUid(String id);

    List<Role> findAllRoles();

    void deleteRolesByUid(String userId);

    void addUserRoles(String userId, String  roleId);
}
