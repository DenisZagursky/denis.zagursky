package daoTest;

import com.netcracker.zagursky.dao.impl.PriceDaoImpl;
import com.netcracker.zagursky.entity.Price;
import org.junit.*;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Dzenyaa on 15.11.2017.
 */
public class PriceDaoImplTest {
    static Price price;
    static PriceDaoImpl priceDao=new PriceDaoImpl();

    @Before
    public void init() throws Exception {
        price = new Price(1.0);

    }

    @Test
    public void findByName() throws Exception {
        priceDao.persist(price);
        assertNotNull(priceDao.getByValue(1.0));
        priceDao.delete(price);
    }
    @After
    public  void after() throws Exception {
        price = null;

    }
}
