package daoTest;

import com.netcracker.zagursky.Application;
import com.netcracker.zagursky.configuration.RepositoryConfiguration;
import com.netcracker.zagursky.dao.CategoryDao;
import com.netcracker.zagursky.entity.Category;
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
public class CategoryDaoImplTest {
    static Category category;
    @Autowired
    private CategoryDao categoryDao;

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
        categoryDao.persist(category);
        assertNotNull(categoryDao.findByName("name"));
        categoryDao.delete(category);
    }

}
