package lz.dao;

import com.github.pagehelper.PageInfo;
import lz.domain.Order;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface OrderDao {
    @Select("select * from orders")
    @Results(value = {
            @Result(column = "productId",property = "product",one = @One(select = "lz.dao.ProductDao.findProductByid"))
    })
    public List<Order> findAll();



}
