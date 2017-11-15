package daoTest;

import com.netcracker.zagursky.dao.impl.CategoryDaoImpl;
import com.netcracker.zagursky.entity.Category;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Dzenyaa on 14.11.2017.
 */
public class CategoryDaoImplTest {
    static Category category;
    static CategoryDaoImpl categoryDao= new CategoryDaoImpl();

    @Before
    public  void init() throws Exception {
        category = new Category("name");
    }
    @After
    public  void after() throws Exception {
        category = null;
    }


    @Test
    public void findByName() throws Exception {
        categoryDao.persist(category);
        assertNotNull(categoryDao.findByName("name"));
        categoryDao.delete(category);
    }

}
