package daoTest;

import com.netcracker.zagursky.dao.impl.TagDaoImpl;
import com.netcracker.zagursky.entity.Tag;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Dzenyaa on 15.11.2017.
 */
public class TagDaoImplTest {
    static Tag tag;
    static TagDaoImpl tagDao= new TagDaoImpl();

    @Before
    public void init() throws Exception {
        tag = new Tag("name");
    }
    @After
    public void after() throws Exception {
        tag = null;
    }

    @Test
    public void findByName() throws Exception {
        tagDao.persist(tag);
        assertNotNull(tagDao.findByName("name"));
        tagDao.delete(tag);
    }

}
