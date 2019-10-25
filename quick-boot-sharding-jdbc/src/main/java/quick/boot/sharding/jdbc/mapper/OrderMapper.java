package quick.boot.sharding.jdbc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import quick.boot.sharding.jdbc.entity.Order;

@Repository
public interface OrderMapper extends BaseMapper<Order> {

}
