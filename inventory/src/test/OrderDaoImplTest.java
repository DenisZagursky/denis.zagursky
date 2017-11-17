import com.netcracker.zagursky.dao.impl.OrderDaoImpl;
import com.netcracker.zagursky.entity.Order;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Dzenyaa on 14.11.2017.
 */
public class OrderDaoImplTest {
    static Order order;
    static OrderDaoImpl categoryDao = new OrderDaoImpl();

    @Before
    public void init() throws Exception {
        order = new Order("1", 1, "1");
    }

    @After
    public void after() throws Exception {
        order = null;
    }


    @Test
    public void getOrderItems() throws Exception {
        categoryDao.persist(order);
        assertNotNull(categoryDao.getOrderItems(order.getId()));
        categoryDao.delete(order);
    }

}
