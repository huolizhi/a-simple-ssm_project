package lz.dao;

import lz.domain.Role;
import lz.domain.SysUser;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface SysUserDao {
    @Select("select * from SYS_USER where username=#{username}")
    @Results({
            @Result(column = "id" ,property = "id",id = true),
            @Result(property = "roleList", column = "id", many = @Many(select = "lz.dao.RoleDao.finRolesByRid"))
    })
    SysUser findUserByUsername(String username);

    @Select("select * from SYS_USER")
    List<SysUser> findAll();

    @Insert("insert into SYS_USER(username,email,password,phoneNum,status) values(#{username},#{email},#{password},#{phoneNum},#{status}) ")
    void addUser(SysUser sysUser);

    @Select("select * from SYS_USER where id=#{id}")
    @Results({
            @Result(column = "id", property = "id", id = true),
            @Result(column = "id", property = "roleList", many = @Many(select = "lz.dao.RoleDao.finRolesByRid"))
    })
    SysUser findDetailByUid(String id);


    /*   @Select("select r.id,r.rolename" +
               "  from SyS_User            u,\n" +
               "       sys_user_role       ur,\n" +
               "       SyS_role            r,\n" +
               "       sys_role_permission rp,\n" +
               "       sys_permission      p\n" +
               " where u.id = ur.userid\n" +
               "   and ur.roleid = r.id\n" +
               "   and rp.roleid = ur.roleid\n" +
               "   and rp.permissionid = p.id\n" +
               "   and u.id =#{id}")
       @Results({
               @Result(property = "id" ,column ="r.id",id = true),
               @Result(property = "roleList" ,column ="r.rolename",many = @Many),

       })
       SysUser findDetailByUid(String id);*/
    @Select("select *from sys_role")
    List<Role> findAllRoles();

    @Delete("delete from sys_user_role where userid=#{userId}")
    void deleteRolesByUid(String userId);


    @Insert("insert into sys_user_role values(#{userid},#{roleid})")
    void addUserRoles(@Param("userid") String userId, @Param("roleid") String roleId);
}
