package org.zerock.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardServiceTest {
	
//	@Setter(onMethod_ = {@Autowired})
//	private BoardServiceimpl  service;
	
	@Test
	public void testExist() {
		log.info("\n서비스 테스트 -------------------------------------------------");
//		log.info(service);
//		log.info(service.getDao());
	}
}

