package ServiceTest;

import com.netcracker.zagursky.Application;
import com.netcracker.zagursky.entity.Price;
import com.netcracker.zagursky.service.PriceService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Dzenyaa on 15.11.2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
public class PriceServiceTest {
    static Price price;
    @Autowired
    private PriceService priceService;

    @Before
    public void init() throws Exception {
        price = new Price(1.0);

    }

    @Test
    public void findByName() throws Exception {
        priceService.persist(price);
        assertNotNull(priceService.getByValue(1.0));
        priceService.delete(price.getId());
    }

    @After
    public void after() throws Exception {
        price = null;

    }
}
