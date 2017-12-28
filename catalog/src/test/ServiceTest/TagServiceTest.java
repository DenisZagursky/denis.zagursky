package ServiceTest;

import com.netcracker.zagursky.Application;
import com.netcracker.zagursky.entity.Offer;
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

import static org.junit.Assert.assertNotNull;

/**
 * Created by Dzenyaa on 15.11.2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
public class TagServiceTest {
    static Tag tag;
    @Autowired
    private TagService tagService;
    @Autowired
    private OfferService offerService;

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
        tagService.persist(tag);
        assertNotNull(tagService.findByName("name"));
        tagService.delete(tag.getId());
    }

    @Test
    public void getOffers() throws Exception {
        tagService.persist(tag);
        Offer offer = new Offer("1", "1");

        offerService.persist(offer);
        offerService.addTag(offer.getId(), tag);
        assertNotNull(tagService.getOffers(tag.getId()));
        tagService.delete(tag.getId());
        offerService.delete(offer.getId());
    }

}