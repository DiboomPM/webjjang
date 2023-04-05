package org.zerock.boardreply.service;

import java.util.List;

import org.zerock.boardreply.vo.BoardReplyVO;

import com.webjjang.util.PageObject;

public interface BoardReplyService {
	
	public List<BoardReplyVO> list(PageObject pageObject, long bno);
	
	public int write(BoardReplyVO vo);

	public int update(BoardReplyVO vo);
	
	public Long delete(Long rno);
}
