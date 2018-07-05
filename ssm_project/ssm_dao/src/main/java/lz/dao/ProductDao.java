package lz.dao;

import com.github.pagehelper.PageHelper;
import lz.domain.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ProductDao {
    @Select("select * from product")
    public List<Product> findAll();

    @Insert("insert into product(productNum,productName,cityName,DepartureTime,productPrice,productDesc,productStatus) values(#{productNum},#{productName},#{cityName},#{DepartureTime},#{productPrice},#{productDesc},#{productStatus})")
    public void save(Product product);

    @Select("select * from product where id=#{id}")
    Product findProductByid(String id);
    @Update("update product set productNum=#{productNum},productName=#{productName},cityName=#{cityName},DepartureTime=#{DepartureTime},productPrice=#{productPrice},productDesc=#{productDesc},productStatus=#{productStatus}where id=#{id}")
    void update(Product product);
    @Delete("delete from product where id=#{id}")
    void delete(String id);
}
