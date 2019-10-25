package quick.boot.sharding.jdbc.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import quick.boot.sharding.jdbc.entity.Order;
import quick.boot.sharding.jdbc.entity.User;
import quick.boot.sharding.jdbc.mapper.OrderMapper;
import quick.boot.sharding.jdbc.mapper.UserMapper;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/insert/{id}")
    public Object insertOrder(@PathVariable("id") Long id) {
        return userMapper.insert(new User(id));
    }

    @GetMapping("/get/{id}")
    public Object getOrder(@PathVariable("id") Long id) {
        return userMapper.selectById(id);
    }

    @GetMapping("/list")
    public Object getOrder() {
        return userMapper.selectList(new QueryWrapper<User>().lambda().orderByAsc(User::getUserId));
    }
}
