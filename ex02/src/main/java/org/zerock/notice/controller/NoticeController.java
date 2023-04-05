package org.zerock.notice.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.zerock.board.vo.BoardVO;
import org.zerock.notice.vo.NoticeVO;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/notice")
@Log4j
public class NoticeController {
	
	String modul = "notice";
	
	@RequestMapping("/list.do")
	public String list() {
		log.info("공지사항 리스트..................................");
		return modul +"/list";
	}
	
	@RequestMapping("/view.do")
	public String view(long no) {
		log.info("공지사항 글 보기..................................");
		log.info("no="+ no);
		return modul + "/view";
	}
	
	@GetMapping("/write.do")
	public String writeForm() {
		log.info("공지사항 글쓰기..................................");
		return modul + "/write";
	}
	
	@PostMapping("/write.do")
	public String write(NoticeVO vo) {
		log.info("공지사항 글쓰기 처리..................................");
		log.info(vo);
		return "redirect:list.do";
	}
	
	@GetMapping("/update.do")
	public String updateForm() {
		log.info("공지사항 수정 폼..................................");
		return modul + "/update";
	}
	
	@PostMapping("/update.do")
	public String update() {
		log.info("공지사항 글수정 처리..................................");
		return "redirect:view.do?no=10";
	}
	
	@PostMapping("/delete.do")
	//@RequestParam[name, defaultValue, required, value] 넘어오는 데이터의 이름이 병수와 다른경우, 값이 넘어오지 않는 경우 기본 값, 필수 항목, 값 세팅
	// 여러개 데이터를 받을 때 리스트로 받으면 클래스를 사용해야 한다.
	public String delete(@RequestParam("no") ArrayList<Long> no) {
//		public String delete(long[] no) {
		log.info("공지사항 글삭제 처리..................................");
//		log.info(Arrays.toString(no));
		log.info(no);
		return "redirect:list.do";
	}
	
}
