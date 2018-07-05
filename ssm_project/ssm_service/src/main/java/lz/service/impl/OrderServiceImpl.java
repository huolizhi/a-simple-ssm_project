package lz.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lz.dao.OrderDao;
import lz.domain.Order;
import lz.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public List<Order> findAll() {
        return orderDao.findAll();

    }

    @Override
    public PageInfo<Order> findPage(Integer pageNumber, Integer pageSize) {
        PageHelper.startPage(pageNumber,pageSize);
        List<Order> list = orderDao.findAll();
        PageInfo<Order> pageInfo=new PageInfo<>(list,7);
        return pageInfo;
    }
}
