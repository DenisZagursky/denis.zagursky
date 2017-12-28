package service;

import com.netcracker.zagursky.Application;
import com.netcracker.zagursky.entity.Order;
import com.netcracker.zagursky.entity.OrderItem;
import com.netcracker.zagursky.service.OrderService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * Created by Dzenyaa on 14.11.2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
public class OrderServiceTest {
    Order order;
    @Autowired
    private OrderService orderService;

    @Before
    public void init() throws Exception {
        order = new Order("1", "1", "1");
    }

    @After
    public void after() throws Exception {
        order = null;
    }


    @Test
    public void addOrderItem() throws Exception {
        orderService.persist(order);
        OrderItem orderItem = new OrderItem("1", "1", 1, "1");
        orderService.addOrderItem(order.getId(), orderItem);
        orderService.delete(order.getId());
    }

    @Test
    public void removeOrderItem() throws Exception {
        orderService.persist(order);
        OrderItem orderItem = new OrderItem("1", "1", 1, "1");
        order = orderService.addOrderItem(order.getId(), orderItem);
        orderItem.setId(2);
        orderService.removeOrderItem(order.getId(), orderItem);
        orderService.delete(order.getId());
    }
}
