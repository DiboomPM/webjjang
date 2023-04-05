package org.zerock.boardreply.mapper;

import java.util.List;
import java.util.Map;

import org.zerock.boardreply.vo.BoardReplyVO;

public interface BoardReplyMapper {
	
	public List<BoardReplyVO> list(Map<String, Object> map);
	
	public Long getTotalRow(Map<String, Object> map);
	
	public int write(BoardReplyVO vo);

	public int update(BoardReplyVO vo);
	
	public Long delete(Long rno);
	
}
