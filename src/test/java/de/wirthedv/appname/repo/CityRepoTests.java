package de.wirthedv.appname.repo;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.emptyIterable;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.wirthedv.appname.AppProfiles;
import de.wirthedv.appname.SpringBootFacesApplication;
import de.wirthedv.appname.domain.QCity;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringBootFacesApplication.class)
// TODO: This test should not start embedded Tomcat
@IntegrationTest
// TODO: with @DirtiesContext it fails as tries to execute schema.sql for every method
//@DirtiesContext
@ActiveProfiles(AppProfiles.TEST)
public class CityRepoTests {
    
    @Autowired
    private CityRepo cityRepo;

    @Test
    public void testFindAll() {
        assertThat(cityRepo.findAll(), not(empty()));
    }

    @Test
    public void testFindSomeWithQueryDsl() {
        assertThat(cityRepo.findAll(QCity.city.country.eq("Australia")), not(emptyIterable()));
    }
}
