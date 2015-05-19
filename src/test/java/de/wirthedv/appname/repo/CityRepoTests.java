package de.wirthedv.appname.repo;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.wirthedv.appname.AppProfiles;
import de.wirthedv.appname.SpringBootFacesApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringBootFacesApplication.class)
@IntegrationTest
@DirtiesContext
@ActiveProfiles(AppProfiles.TEST)
public class CityRepoTests {
    
    @Autowired
    private CityRepo cityRepo;

    @Test
    public void testFindAll() {
        assertThat(cityRepo.findAll(), not(empty()));
    }
}
