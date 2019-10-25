package quick.boot.sharding.jdbc.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import quick.boot.sharding.jdbc.entity.Order;
import quick.boot.sharding.jdbc.mapper.OrderMapper;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderMapper orderMapper;

    @GetMapping("/insert/{id}")
    public Object insertOrder(@PathVariable("id") Long id) {
        return orderMapper.insert(new Order(id, 1L));
    }

    @GetMapping("/get/{id}")
    public Object getOrder(@PathVariable("id") Long id) {
        return orderMapper.selectById(id);
    }

    @GetMapping("/list")
    public Object getOrder() {
        return orderMapper.selectList(new QueryWrapper<Order>().lambda().orderByAsc(Order::getOrderId));
    }
}
