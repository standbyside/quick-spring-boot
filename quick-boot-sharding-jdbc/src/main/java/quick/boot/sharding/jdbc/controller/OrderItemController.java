package quick.boot.sharding.jdbc.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import quick.boot.sharding.jdbc.entity.OrderItem;
import quick.boot.sharding.jdbc.mapper.OrderItemMapper;

@RestController
@RequestMapping("/item")
public class OrderItemController {

    @Autowired
    private OrderItemMapper itemMapper;

    @GetMapping("/insert/{id}")
    public Object insertOrder(@PathVariable("id") Long id) {
        return itemMapper.insert(new OrderItem(id, 1L));
    }

    @GetMapping("/get/{id}")
    public Object getOrder(@PathVariable("id") Long id) {
        return itemMapper.selectById(id);
    }

    @GetMapping("/list")
    public Object getOrder() {
        return itemMapper.selectList(new QueryWrapper<OrderItem>().lambda().orderByAsc(OrderItem::getOrderId));
    }
}
