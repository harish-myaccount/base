package com.squad.cccreview.test;

import ch.qos.logback.classic.Logger;
import com.github.vbauer.herald.annotation.Log;
import com.squad.cccreview.controller.HelloController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@WebMvcTest(HelloController.class)
public class HelloControllerTest {

	@Autowired
    private MockMvc mvc;

	@Log
	private Logger logger;
	
	@Test
	public void testHello() throws Exception {
		logger.debug("Logging should not work here");
		mvc.perform(get("/hello")).andExpect(content().string("hello"));
	}

}