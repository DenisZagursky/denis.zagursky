package ServiceTest;

import com.netcracker.zagursky.Application;
import com.netcracker.zagursky.entity.Category;
import com.netcracker.zagursky.entity.Offer;
import com.netcracker.zagursky.service.CategoryService;
import com.netcracker.zagursky.service.OfferService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Dzenyaa on 14.11.2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
public class CategoryServiceTest {
    static Category category;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private OfferService genericDao;

    @Before
    public void init() throws Exception {
        category = new Category("name");
    }

    @After
    public void after() throws Exception {
        category = null;
    }


    @Test
    public void findByName() throws Exception {
        categoryService.persist(category);
        assertNotNull(categoryService.findByName("name"));
        categoryService.delete(category.getId());
    }

    @Test
    public void getOffers() throws Exception {
        categoryService.persist(category);
        Offer offer = new Offer();
        offer.setCategory(category);
        genericDao.persist(offer);
        System.out.println(categoryService.getOffers(category.getId()));
    }

}
