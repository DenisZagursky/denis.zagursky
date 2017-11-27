package ServiceTest;

import com.netcracker.zagursky.Application;
import com.netcracker.zagursky.entity.Category;
import com.netcracker.zagursky.service.CategoryService;
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

}
