package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.log4j.Log4j;

// mapping은 클래스 위에, 매서드 위에 -> 클래스 매핑 + 메서드 매핑 --> 전체 uri에 해당한다.
@Controller
@RequestMapping("/sample/*")
@Log4j
public class SampleController {
	
	// String - redirect: 에 안붙으면 jsp로 forword, redirect:이 붙어 있으면 페이지 이동
	// void면 uri가 jsp의 정보가 된다. 예) /sample/test -> /sample/test.jsp로 자동으로 찾음
	@RequestMapping("/basic") // uri = /sample/basic/...
	public void basic() {
		log.info("basic()....................");
	}
	
	@RequestMapping(value="/basicGet", method = RequestMethod.GET)
	public void basicGet() {
		log.info("basicGet()....................");
	}

	@GetMapping("/basicGet2")
	public void basicGet2() {
		log.info("basicGet2()....................");
	}
}
