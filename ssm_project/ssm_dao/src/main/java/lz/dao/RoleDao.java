package lz.dao;

import lz.domain.Role;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RoleDao {
    @Select("select * from sys_user_role sur, sys_role r  where sur.roleid=r.id and sur.userid=#{id}")
    @Results({
            @Result(column ="id",property = "id",id = true),
            @Result(property = "permissions", column = "id", many = @Many(select = "lz.dao.PermissionDao.findAllPermissonByid"))
    })
    public List<Role> finRolesByRid(String id);
}
