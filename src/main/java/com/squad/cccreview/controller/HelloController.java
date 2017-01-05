package com.squad.cccreview.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.vbauer.herald.annotation.Log;

import ch.qos.logback.classic.Logger;

@Controller
public class HelloController {
	

	@RequestMapping("/hello")
	public String hello() {
		return "hello";
	}
}
