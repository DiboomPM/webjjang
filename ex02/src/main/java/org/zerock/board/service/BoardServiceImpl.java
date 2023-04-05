package org.zerock.board.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.zerock.board.mapper.BoardMapper;
import org.zerock.board.vo.BoardVO;

import com.webjjang.util.PageObject;

@Service
@Qualifier("boardServiceImpl")
public class BoardServiceImpl implements BoardService{

	@Inject
	private BoardMapper mapper;
	
	@Override
	public List<BoardVO> list(PageObject pageObject){
		pageObject.setTotalRow(mapper.getTotalRow(pageObject));
		return mapper.list(pageObject);
	}
	
	@Override
	public BoardVO view(long no, int inc){
		if(inc == 1)
			mapper.increase(no);
		return mapper.view(no);
	}
	
	@Override
	public int write(BoardVO vo){
		return mapper.write(vo);
	}
	
	@Override
	public int update(BoardVO vo){
		return mapper.update(vo);
	}
	
	@Override
	public int delete(BoardVO vo){
		return mapper.delete(vo);
	}
	
}
