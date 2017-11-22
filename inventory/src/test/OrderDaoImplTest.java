import com.netcracker.zagursky.Application;
import com.netcracker.zagursky.configuration.RepositoryConfiguration;
import com.netcracker.zagursky.dao.OrderDao;
import com.netcracker.zagursky.entity.Order;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Dzenyaa on 14.11.2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@Import(RepositoryConfiguration.class)
public class OrderDaoImplTest {
    static Order order;
    @Autowired
    private OrderDao categoryDao;

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
