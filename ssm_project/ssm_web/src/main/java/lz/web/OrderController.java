package lz.web;

import com.github.pagehelper.PageInfo;
import lz.domain.Order;
import lz.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/order")
@Secured({"ROLE_ADMIN","ROLE_USER"}) //管理员，用户都可见
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping("/finAll.do")
    public ModelAndView finAll(){
        List<Order> oList = orderService.findAll();
        ModelAndView mv=new ModelAndView();
        mv.addObject("list",oList);
        mv.setViewName("order-list");
        return mv;
    }

    @RequestMapping("/findPage.do")
    /*
    * 分页，默认传第一页，每页显示5条
    * */
    public ModelAndView finPage(@RequestParam(defaultValue = "1") Integer pageNumber,@RequestParam(defaultValue = "5") Integer pageSize){
        System.out.println(pageNumber+"##"+pageSize);
        PageInfo<Order> pageInfo= orderService.findPage(pageNumber,pageSize);
        ModelAndView mv=new ModelAndView();
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("order-list");
        return mv;
    }

}
