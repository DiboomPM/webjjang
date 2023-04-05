package org.zerock.image.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.board.controller.BoardController;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/image")
@Log4j
public class ImageController {

	String modul = "image";

	// 이미지를 선택해서 보내주는 처리
	@GetMapping("/write.do")
	public String writeForm() {
		return modul + "/write";
	}

	// 이미지를 선택해서 보내주는 처리
	@PostMapping("/write.do")
	public String write(String title, MultipartFile[] files) {
		log.info("title:" + title);
		for(MultipartFile mfile : files) {
			if(mfile.getOriginalFilename() != null && !mfile.getOriginalFilename().equals("")) {
			log.info("---------------------------------------------------");
			log.info("name:" + mfile.getOriginalFilename());
			log.info("size:" + mfile.getSize());
			}
		}
		
		return "redirect:list.do";
	}
}
