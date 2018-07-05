package lz.service;

import com.github.pagehelper.PageInfo;
import lz.domain.Order;

import java.util.List;

public interface OrderService {
   public List<Order> findAll();

    PageInfo<Order> findPage(Integer pageNumber, Integer pageSize);
}
