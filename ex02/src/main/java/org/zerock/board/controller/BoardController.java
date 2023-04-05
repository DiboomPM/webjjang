package org.zerock.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.board.service.BoardService;
import org.zerock.board.vo.BoardVO;

import com.webjjang.util.PageObject;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/board")
@Log4j
public class BoardController {
	
	@Autowired
	@Qualifier("boardServiceImpl")
	private BoardService service;
	
	@RequestMapping("/list2.do")
	public String list2() {
		log.info("게시판 리스트2..................................");
		return "board/list2";
	}
	
	@RequestMapping("/list.do")
	public String list(@ModelAttribute("pageObject") PageObject pageObject, Model model) {
		log.info("게시판 리스트..................................");
		model.addAttribute("list", service.list(pageObject));
		return "board/list";
	}
	
	@RequestMapping("/view.do")
	public String view(long no, int inc, Model model) {
		log.info("게시판 글 보기..................................");
		log.info("no="+ no +", inc=" +inc);
		model.addAttribute("vo", service.view(no, inc));
		return "board/view";
	}
	
	@GetMapping("/write.do")
	public String writeForm() {
		log.info("게시판 글쓰기..................................");
		return "board/write";
	}
	
	@PostMapping("/write.do")
	public String write(BoardVO vo) {
		log.info("게시판 글쓰기 처리..................................");
		log.info(vo);
		service.write(vo);
		return "redirect:list.do";
	}
	
	@GetMapping("/update.do")
	public String updateForm(long no, Model model) {
		log.info("게시판 수정 폼..................................");
		model.addAttribute("vo", service.view(no, 0));
		return "board/update";
	}
	
	@PostMapping("/update.do")
	public String update(BoardVO vo) {
		log.info("게시판 글수정 처리..................................");
		service.update(vo);
		return "redirect:view.do?no="+vo.getNo()+"&inc=0";
	}
	
	@PostMapping("/delete.do")
	//@RequestParam[name, defaultValue, required, value] 넘어오는 데이터의 이름이 병수와 다른경우, 값이 넘어오지 않는 경우 기본 값, 필수 항목, 값 세팅
	// 여러개 데이터를 받을 때 리스트로 받으면 클래스를 사용해야 한다.
//	public String delete(@RequestParam("no") ArrayList<Long> no) {
//	public String delete(long[] no) {
	public String delete(BoardVO vo) {
		log.info("게시판 글삭제 처리..................................");
//		log.info(Arrays.toString(no));
		log.info(vo);
		service.delete(vo);
		return "redirect:list.do";
	}
	
}
