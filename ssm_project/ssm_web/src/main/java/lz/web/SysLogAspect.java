package lz.web;

/*
* 切面类
* */


import lz.domain.SysLog;
import lz.service.SysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component //组建
@Aspect    //切面类
public class SysLogAspect {
    @Autowired
    private SysLogService sysLogService;

    @Autowired
    private HttpServletRequest request;
    private  String methodName;

    //前置
    @Before("execution(* lz.web.*Controller.*(..))")
    public void before(JoinPoint jp){
        System.out.println("前置");
        //获取到目标对象，Controller对象
        Class clazz = jp.getTarget().getClass();
        //获取类名
        String simpleName = clazz.getSimpleName();
        //方法名
        String name = jp.getSignature().getName();
        //拼接类名+方法名
        methodName=simpleName+"."+name;


    }

    //后置
    @After("execution(* lz.web.*Controller.*(..))")
    public void after( ){
        System.out.println("后置");
            //获取username
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        User principal = (User) authentication.getPrincipal();
            //获取ip，通过request,需要配置request监听器，注入request,
            //不是controller,无法直接获取
        String remoteAddr = request.getRemoteAddr();
        //封装Syslog类
        SysLog sysLog=new SysLog();
        sysLog.setVisitTime(new Date());
        sysLog.setIp(remoteAddr);
        sysLog.setMethod(methodName);
        sysLog.setUsername( principal.getUsername());

        sysLogService.addLog(sysLog);

    }
}
