package service;

import com.netcracker.zagursky.Application;
import com.netcracker.zagursky.entity.Order;
import com.netcracker.zagursky.service.OrderService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

//import com.netcracker.zagursky.configuration.RepositoryConfiguration;

/**
 * Created by Dzenyaa on 14.11.2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
//@Import(RepositoryConfiguration.class)
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
    public void getOrderItems() throws Exception {
        orderService.persist(order);
        assertNotNull(orderService.getOrderItems(order.getId()));
        orderService.delete(order.getId());
    }

}
