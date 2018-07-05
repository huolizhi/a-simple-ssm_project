package lz.service.impl;

import lz.dao.SysUserDao;
import lz.domain.Role;
import lz.domain.SysUser;
import lz.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserDao sysUserDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /*security框架的用户登陆，需要继承UserDetailservice接口
     * 给框架提供数据，
     * 框架会自动调用Load'UserByUsrename，获取数据
     * username用户页面传来的
     * UserDetails接口，用他的实现类User接收返回的数据
     *user三个参数，权限列表
     * */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //System.out.println("userDetailService方法执行了");
        SysUser sysUser = new SysUser();
        //根据用户名查询出密码，及权限
        sysUser = sysUserDao.findUserByUsername(username);
        List<GrantedAuthority> list = new ArrayList<>();
        List<Role>rList=sysUser.getRoleList();
        //赋值的权限集合有要求，需要时GrantedAuthority类型
        if (rList != null&&rList.size()>0) {
            for (Role role : rList) {
                list.add(new SimpleGrantedAuthority(role.getRoleName()));
            }
        }

        //System.out.println(sysUser);

       /* list.add(new SimpleGrantedAuthority("ROLE_USER"));
        list.add(new SimpleGrantedAuthority("ROLE_ADMIN"));*/
       //用户名，加密后的密码，权限列表复制给User
        //框架判断输入的密码是否和数据库中的一致，该用户是否有权限登陆
        User user = new User(sysUser.getUsername(),sysUser.getPassword(), list);
        return user;
    }


    /*查询所有用户*/
    @Override
    public List<SysUser> findAll() {
        return sysUserDao.findAll();
    }

    //注册用户，将密码加密后再存数据库
    //需要用框架BCrpassEncoder的encode方法
    @Override
    public void addUser(SysUser sysUser) {
        String encode = bCryptPasswordEncoder.encode(sysUser.getPassword());
        sysUser.setPassword(encode);
        sysUserDao.addUser(sysUser);

    }

    @Override
    public SysUser findDetailByUid(String id) {
        return sysUserDao.findDetailByUid(id);
    }

    @Override
    public List<Role> findAllRoles() {
        return sysUserDao.findAllRoles();
    }

    @Override
    public void deleteRolesByUid(String userId) {
        sysUserDao.deleteRolesByUid(userId);
    }

    @Override
    public void addUserRoles(String userId, String roleId) {
        sysUserDao.addUserRoles(userId,  roleId);
    }
}