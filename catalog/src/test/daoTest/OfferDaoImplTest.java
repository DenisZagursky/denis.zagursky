package daoTest;

import com.netcracker.zagursky.dao.impl.OfferDaoImpl;
import com.netcracker.zagursky.entity.Offer;
import com.netcracker.zagursky.entity.Tag;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class OfferDaoImplTest {
    static Offer offer;
    static Tag tag;
    static OfferDaoImpl genericDao = new OfferDaoImpl();
    ;

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
        genericDao.persist(offer);
        assertNotNull(genericDao.findAll());
        assertNotNull(genericDao.findById(offer.getId()));
        genericDao.delete(offer);
    }

    @Test
    public void getByName() throws Exception {
        genericDao.persist(offer);
        assertNotNull(genericDao.findAll());
        assertNotNull(genericDao.findByName(offer.getName()));
        genericDao.delete(offer);
    }

    @Test
    public void getTag() throws Exception {
        genericDao.persist(offer);
        assertNotNull(genericDao.findAll());
        assertNotNull(genericDao.findByTag(offer.getTags().get(0).getName()));
        genericDao.delete(offer);
    }

    @Test
    public void getByCategory() throws Exception {
        genericDao.persist(offer);
        assertNotNull(genericDao.findAll());
        assertNotNull(genericDao.findByCategory(offer.getCategory().getName()));
        genericDao.delete(offer);
    }

    @Test
    public void addObject() throws Exception {

        genericDao.persist(offer);
        assertNotNull(genericDao.findById(offer.getId()));
        genericDao.delete(offer);


    }

    @Test
    public void updateObject() throws Exception {
        genericDao.persist(offer);
        Offer updateOffer = new Offer("1", "23", 1.0, "1");
        updateOffer.setId(offer.getId());
        genericDao.update(updateOffer);
        assertSame("23", genericDao.findById(updateOffer.getId()).getDescription());
        genericDao.deleteById(updateOffer.getId());
    }

    @Test
    public void deleteObject() throws Exception {

        genericDao.persist(offer);
        assertNotNull(genericDao.findById(offer.getId()));
        genericDao.delete(offer);
        assertNull(genericDao.findById(offer.getId()));
    }


}
