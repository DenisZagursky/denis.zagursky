/**
 * Created by Dzenyaa on 27.12.2017.
 */

import com.netcracker.zagursky.Application;
import com.netcracker.zagursky.client.CatalogClient;
import com.netcracker.zagursky.entity.catalog.Offer;
import com.netcracker.zagursky.entity.catalog.OffersFilter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest(classes = {Application.class})
@RunWith(SpringRunner.class)
public class CatalogClientTest {

    @Autowired
    private CatalogClient catalogClient;

    @Test
    public void getOffersByFilter() throws Exception {
        OffersFilter offersFilter=new OffersFilter();
        offersFilter.setCategoryName("new offers");
    List<Offer> offers=catalogClient.getOffersByFilter(offersFilter);
        System.out.println(offers);
    }
}
