package lz.domain;

import org.springframework.security.core.userdetails.User;

import java.security.Permission;
import java.util.List;

public class Role {
    private String id;
    private String roleName;
    private String roleDesc;
    //权限
    private List<Permission> permissions;
    private List<User> users;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Role{");
        sb.append("id='").append(id).append('\'');
        sb.append(", roleName='").append(roleName).append('\'');
        sb.append(", roleDesc='").append(roleDesc).append('\'');
        sb.append(", permissions=").append(permissions);
        sb.append(", users=").append(users);
        sb.append('}');
        return sb.toString();
    }
}
