package com.squad.cccreview.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.*;

import com.squad.cccreview.jpa.ReviewRepository;
import com.squad.cccreview.model.Review;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RepositoryTest {

	@Autowired
    private ReviewRepository repo;

    @Test
    public void exampleTest() {
    	assertThat(repo.count()).isEqualTo(0);
    }
    
    @Test
    public void addAndDeleteTest() {
    	Review testreview = new Review("this is test review","test company",3.5);
    	repo.insert(testreview);
    	assertThat(repo.count()).isEqualTo(1);
    	repo.delete(testreview);
    	assertThat(repo.count()).isEqualTo(0);
    }

}
