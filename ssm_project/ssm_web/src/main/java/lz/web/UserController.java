package lz.web;

import lz.domain.Role;
import lz.domain.SysUser;
import lz.service.SysUserService;
import org.apache.shiro.web.session.HttpServletSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("user")

/*访问controller要有的角色*/
//java提供注解
//@RolesAllowed("ROLE_ADMIN")
//security提供注解
@Secured({"ROLE_ADMIN"}) //管理员可见
/*@PreAuthorize("RO_ADMIN")*/
public class UserController {
    @Autowired
    private SysUserService sysUserService;

    @RequestMapping("findAll.do")
    public ModelAndView findAllUser(HttpSession httpSession) {
        //后台获取User对象两种方式
       /*
       //获取security上下文对象，
       SecurityContext context = SecurityContextHolder.getContext();
       //获取权限
        Authentication authentication = context.getAuthentication();
        //获取主要对象
        User principal = (User) authentication.getPrincipal();
        System.out.println(principal.getUsername());
        SecurityContextImpl attribute = (SecurityContextImpl)httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
        Authentication authentication1 = attribute.getAuthentication();
        User principal1 = (User)authentication1.getPrincipal();
        System.out.println(principal1.getUsername());*/
        ModelAndView mv = new ModelAndView();
        List<SysUser> list = sysUserService.findAll();
        mv.addObject("userlist", list);
        mv.setViewName("user-list");
        return mv;
    }

    @RequestMapping("addUser.do")
    public String addUser() {
        //System.out.println("呵呵");
        return "user-add";
    }

    //新增用户
    @RequestMapping("saveUser.do")
    public String saveUser(SysUser sysUser) {
        sysUserService.addUser(sysUser);

        return "redirect:findAll.do";
    }

    //显示详情
    @RequestMapping("showDetail.do")
    public ModelAndView showDetail(String id) {
        ModelAndView mv = new ModelAndView();
        //System.out.println(id);
        SysUser sysUser = sysUserService.findDetailByUid(id);
        //System.out.println(sysUser);
        mv.addObject("user", sysUser);
        mv.setViewName("user-show");
        return mv;
    }

    //用户关系界面  添加角色（a标签，href要携带userid）
    //两大步完成，首先跳转修改页面，回显数据， 然后先改数据后再更新数据库，跳到成功后的一个页面  ，该记录的id 时关键，都得传
    /*
    *查出 当前用户的角色，以及所有角色，把user也返回给前台，
    * modelAndView
    * 转发到user-role-add,页面
    * */
    @RequestMapping("roleAdd.do")
    public ModelAndView roleAdd(String id){
        ModelAndView mv=new ModelAndView();
        SysUser user = sysUserService.findDetailByUid(id);
        //用户的角色集合
        List<Role> roleList = user.getRoleList();
        //所有的角色集合
        List<Role>allRoles= sysUserService.findAllRoles();
       /* System.out.println(roleList);
        System.out.println(allRoles);*/
        mv.addObject("userRoles",roleList);
        mv.addObject("roleList",allRoles);
        mv.addObject("user",user); //要把user传回去，因为下一个页面会用到userID
        mv.setViewName("user-role-add");
        return mv;
    }

    /**
     更新用户角色
     传入参数  userId, 选中的角色  ids[]
     每次修改完用户角色
     先把当前用户的角色都删掉，
     然后把checkBox的id（角色id）添加到当前用户,
     更新完成后跳转userlist页面，不需要携带数据，直接redirect

     */
    @RequestMapping("updateUserRoles.do")
    public String  updateUserRoles(String userId,String[]ids){
        System.out.println(userId+"@@"+ Arrays.toString(ids));
        //删除
        sysUserService.deleteRolesByUid(userId);
        //重新赋值
        if (ids!=null){
            for (String RoleId : ids) {
                sysUserService.addUserRoles(userId,RoleId);
            }
        }
        return "redirect:findAll.do";
    }

}
