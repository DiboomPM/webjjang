package org.zerock.boardreply.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.boardreply.service.BoardReplyService;
import org.zerock.boardreply.vo.BoardReplyVO;

import com.webjjang.util.PageObject;

import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/boardreply")
@Log4j
public class BoardReplyController {
	
	@Autowired
	@Qualifier("boardReplyServiceImpl")
	private BoardReplyService service;
	
	@GetMapping(value = "/list.do", produces= {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Map<String, Object>> list(PageObject pageObject, long bno){
		log.info("댓글 리스트");
		log.info("pageObject = " + pageObject + ", bno = " + bno);
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("list",	service.list(pageObject, bno));
		map.put("pageObject", pageObject);
		
		log.info("return map = " + map);
	
		
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@PostMapping(value = "/write.do", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> write(@RequestBody BoardReplyVO vo, HttpSession session) {
		// session에서 id를 꺼내야한다.
		String id = "test";
		vo.setReplyer(id);
		
		log.info(vo);
		
		
		try {
			service.write(vo);
			return new ResponseEntity<String>(URLEncoder.encode("댓글이 정상적으로 등록되었습니다.","UTF-8"), HttpStatus.OK);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@PostMapping(value = "/update.do", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> update(@RequestBody BoardReplyVO vo) {

		
		log.info(MediaType.TEXT_PLAIN_VALUE);
		log.info(vo);
		
		
		try {
			int result = service.update(vo);
			if(result == 1)
				return new ResponseEntity<String>(URLEncoder.encode("댓글이 정상적으로 수정되었습니다.","UTF-8"), HttpStatus.OK);
			else
				return new ResponseEntity<String>(URLEncoder.encode("댓글의 정보를 확인해주세요. 새로고침 후 진행해주세요","UTF-8"), HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping(value = "/delete.do", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> delete(long rno) {
		
		log.info(rno);
		
		try {
			long result = service.delete(rno);
			log.info(rno);
			if(result == 1)
				return new ResponseEntity<String>(URLEncoder.encode("댓글이 정상적으로 삭제가 되었습니다.","UTF-8"), HttpStatus.OK);
			else
				return new ResponseEntity<String>(URLEncoder.encode("댓글의 정보를 확인해주세요. 새로고침 후 진행해주세요","UTF-8"), HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
