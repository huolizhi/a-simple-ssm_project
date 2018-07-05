package lz.dao;

import lz.domain.Permission;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PermissionDao {
    @Select("select * from sys_role_permission srp,sys_permission p where srp.permissionid=p.id and srp.roleid=#{id}")
    public List<Permission>findAllPermissonByid(String id);
}
