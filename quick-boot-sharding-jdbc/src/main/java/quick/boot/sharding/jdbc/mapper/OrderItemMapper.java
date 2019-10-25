package quick.boot.sharding.jdbc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import quick.boot.sharding.jdbc.entity.OrderItem;

@Repository
public interface OrderItemMapper extends BaseMapper<OrderItem> {

}
