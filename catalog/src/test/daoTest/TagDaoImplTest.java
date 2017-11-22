package daoTest;

import com.netcracker.zagursky.Application;
import com.netcracker.zagursky.configuration.RepositoryConfiguration;
import com.netcracker.zagursky.dao.TagDao;
import com.netcracker.zagursky.entity.Tag;
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
public class TagDaoImplTest {
    static Tag tag;
    @Autowired
    private TagDao tagDao;

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
