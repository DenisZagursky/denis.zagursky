import com.netcracker.zagursky.Application;
import com.netcracker.zagursky.client.InventoryClient;
import com.netcracker.zagursky.entity.inventory.Order;
import com.netcracker.zagursky.entity.inventory.OrderItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by Dzenyaa on 08.12.2017.
 */
@SpringBootTest(classes = {Application.class})
@RunWith(SpringRunner.class)
public class OrderClientTest {
    @Autowired
    private InventoryClient inventoryClient;


    @Test
    public void getOrder() throws Exception {
        System.out.println(inventoryClient.getOrderById(2));
    }

    @Test
    public void postOrder() throws Exception {
        System.out.println(inventoryClient.postOrder(new Order("1", "1", "1")));
    }

    @Test
    public void addOrderItem() throws Exception {
        System.out.println(inventoryClient.addOrderItem(1, new OrderItem("1", "1", 1.0, "1")));
    }

    @Test
    public void getCustumerOrder() throws Exception {
        List<Order> orders = inventoryClient.getCustumerOrder("1");
        System.out.println(orders);
    }

    @Test
    public void updateOrder() throws Exception {
        Order order = inventoryClient.getOrderById(2);
        order.setSignOfThePayment(true);
        System.out.println(inventoryClient.updateOrder(order));
    }

    @Test
    public void deleteOrder() throws Exception {
        Order order = inventoryClient.getOrderById(1);
        inventoryClient.deleteOrder(3);
    }

    @Test
    public void getOrders() throws Exception {
        System.out.println(inventoryClient.getOrders());
    }
}
