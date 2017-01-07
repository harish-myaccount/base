package com.squad.cccreview.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import ch.qos.logback.classic.Logger;
import com.github.vbauer.herald.annotation.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CloudinaryClientTest {

	@Autowired
	private Cloudinary client;

	@Log
	private Logger logger;

	
	@Test
	public void test() {
		logger.debug("Logging should work here");

		assertNotNull(client);
		try {
			Map response = client.uploader().upload("src/test/resources/test.jpg", ObjectUtils.emptyMap());
			for (Object iterable_element : response.keySet()) {
				System.out.println(iterable_element + " : "+response.get(iterable_element));
			}
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}
	}

}
