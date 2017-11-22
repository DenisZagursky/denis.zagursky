package daoTest;

import com.netcracker.zagursky.Application;
import com.netcracker.zagursky.configuration.RepositoryConfiguration;
import com.netcracker.zagursky.dao.PriceDao;
import com.netcracker.zagursky.entity.Price;
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
 * Created by Dzenyaa on 15.11.2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@Import(RepositoryConfiguration.class)
public class PriceDaoImplTest {
    static Price price;
    @Autowired
    private PriceDao priceDao;

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
    public void after() throws Exception {
        price = null;

    }
}
