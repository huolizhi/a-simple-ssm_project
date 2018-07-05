package lz.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lz.domain.Product;
import lz.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@org.springframework.stereotype.Controller
@RequestMapping("/product")
@Secured({"ROLE_ADMIN","ROLE_USER"}) //管理员，用户都可见
//@PreAuthorize("hasAuthority('ROLE_ADMIN')")

public class Controller {
    @Autowired
    private ProductService productService;

    /*
    查询所有，返回mv,
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAll() {
        ModelAndView mv = new ModelAndView();
        List<Product> pList = productService.findAll();
        //System.out.println(pList);
        mv.addObject("pList", pList);
        mv.setViewName("product-list");
        return mv;
    }

    /*
    增加时要通过后端，可以校验是否登陆，跳转到新增界面
     */
    @RequestMapping("/init.do")
    public String init() {
       // return "forward: /pages/product-add.jsp";
        return "product-add";

    }

    /*
    保存到数据库中，完成后跳转列表界面，（用重定向 调用所有findAll）
     */

    @RequestMapping("/save.do")
    public String save(Product product) {
       productService.save(product);
       return "redirect:/product/findAll.do";

    }

    /*
    更新 用id查数据库，返回mv,包含product,
     */
    @RequestMapping("/initUpdate.do")
    public ModelAndView initUpdate(String id){
        Product product = productService.findProductByid(id);
        ModelAndView mv = new ModelAndView();
        mv.addObject("product",product);
        mv.setViewName("product-update");
        return mv;
    }

    @RequestMapping("/update.do")
    public String update(Product product) {
        productService.update(product);
        return "redirect:/product/findAll.do";

    }
    @RequestMapping("/delete.do")
    public String delete(String id) {
        //System.out.println(id);
        productService.delete(id);
        return "redirect:/product/findAll.do";

    }

}
