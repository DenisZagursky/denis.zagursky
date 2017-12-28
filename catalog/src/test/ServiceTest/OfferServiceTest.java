package ServiceTest;

import com.netcracker.zagursky.Application;
import com.netcracker.zagursky.entity.Offer;
import com.netcracker.zagursky.entity.Price;
import com.netcracker.zagursky.entity.Tag;
import com.netcracker.zagursky.service.OfferService;
import com.netcracker.zagursky.service.TagService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
public class OfferServiceTest {

    static Offer offer;
    static Tag tag;
    @Autowired
    private OfferService genericDao;
    @Autowired
    private TagService tagService;

    @Before
    public void init() throws Exception {
        offer = new Offer("testname", "testdescriptiong");
        tag = new Tag("testtag");
        //offer.addTag(tag);

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
        Offer updateOffer = new Offer("1", "23");
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

    @Test
    public void gerByPrice() throws Exception {

        genericDao.persist(offer);
        offer.setPrice(new Price(2));
        genericDao.update(offer);
        System.out.println(genericDao.findByPrice(1, 3));
        assertNotNull(genericDao.findById(offer.getId()));
        genericDao.delete(offer.getId());
        assertNull(genericDao.findById(offer.getId()));
    }

    @Test
    public void gerByTags() throws Exception {

        genericDao.persist(offer);
        tagService.persist(tag);
        offer.addTag(tag);
        genericDao.update(offer);
        List<String> tags = new ArrayList<>();
        tags.add(tag.getName());
        System.out.println(genericDao.findByTags(tags));
        assertNotNull(genericDao.findById(offer.getId()));
        genericDao.delete(offer.getId());
        assertNull(genericDao.findById(offer.getId()));
    }
}
