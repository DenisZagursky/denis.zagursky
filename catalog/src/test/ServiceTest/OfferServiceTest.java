package ServiceTest;

import com.netcracker.zagursky.Application;
import com.netcracker.zagursky.entity.Offer;
import com.netcracker.zagursky.entity.Tag;
import com.netcracker.zagursky.service.OfferService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
public class OfferServiceTest {

    static Offer offer;
    static Tag tag;
    @Autowired
    private OfferService genericDao;

    @Before
    public void init() throws Exception {
        offer = new Offer("testname", "testdescriptiong", 1.0, "NameCategory");
        tag = new Tag("testtag");
        offer.addTag(tag);

    }

    @After
    public void after() throws Exception {
        offer = null;
        tag = null;
    }

    @Test
    public void getObject() throws Exception {
        Offer on = genericDao.persist(offer);
        assertNotNull(genericDao.findAll());
        assertNotNull(genericDao.findById(offer.getId()));
        genericDao.delete(on.getId());
    }

    @Test
    public void getByName() throws Exception {
        genericDao.persist(offer);
        assertNotNull(genericDao.findAll());
        assertNotNull(genericDao.findByName(offer.getName()));
        genericDao.delete(offer.getId());
    }

    @Test
    public void getTag() throws Exception {
        genericDao.persist(offer);
        assertNotNull(genericDao.findAll());
        assertNotNull(genericDao.findByTag(offer.getTags().get(0).getName()));
        genericDao.delete(offer.getId());
    }

    @Test
    public void getByCategory() throws Exception {
        genericDao.persist(offer);
        assertNotNull(genericDao.findAll());
        assertNotNull(genericDao.findByCategory(offer.getCategory().getName()));
        genericDao.delete(offer.getId());
    }

    @Test
    public void addObject() throws Exception {

        genericDao.persist(offer);
        assertNotNull(genericDao.findById(offer.getId()));
        genericDao.delete(offer.getId());


    }

    @Test
    public void updateObject() throws Exception {
        genericDao.persist(offer);
        Offer updateOffer = new Offer("1", "23", 1.0, "1");
        updateOffer.setId(offer.getId());
        genericDao.update(updateOffer);
        assertSame(Integer.valueOf("23"), Integer.valueOf(genericDao.findById(updateOffer.getId()).getDescription()));
        genericDao.delete(updateOffer.getId());
    }

    @Test
    public void deleteObject() throws Exception {

        genericDao.persist(offer);
        assertNotNull(genericDao.findById(offer.getId()));
        genericDao.delete(offer.getId());
        assertNull(genericDao.findById(offer.getId()));
    }


}
