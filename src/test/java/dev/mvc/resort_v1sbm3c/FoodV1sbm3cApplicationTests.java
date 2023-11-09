package dev.mvc.resort_v1sbm3c;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import dev.mvc.res.ResDAOInter;
import dev.mvc.res.ResProcInter;
import dev.mvc.res.ResVO;

@SpringBootTest
class FoodV1sbm3cApplicationTests {
  @Autowired
  private ResDAOInter resDAO;
  
  @Autowired  // CateDAOInter interface 객체를 만들어 자동으로 할당해라.
  @Qualifier("dev.mvc.res.ResProc")  // 이 인터페이스에 누가 할당되야 하느냐 Autowired로만 안될 때가 있다.
  private ResProcInter resProc;
  
	@Test
	void contextLoads() {
	}

//	@Test
//	public void testCreate() {
//	  ResVO resVO = new ResVO();
//	  resVO.setResname("맘스터치");
//	  resVO.setResaddress("충남 아산시 신창면 순천향로 14");
//	  resVO.setResphone("041-542-4626");
//	  resVO.setRestime("10:00~23:00");
//	  
////	  System.out.println(resVO.toString());
////	  int cnt = this.resDAO.create(resVO);
////	  System.out.println("->cnt:"+cnt);
//	  
//    int cnt = this.resProc.create(resVO);
//    System.out.println("-> cnt:"+ cnt);
//	}
	
//  @Test
//  public void testList_all() {
//    ArrayList<ResVO> list = this.resProc.list_all();
//    for(ResVO resVO:list) {
//      System.out.println(resVO.toString());
//    }

//    @Test
//    public void testRead() {
//      ResVO resVO = this.resProc.read(2);
//      System.out.println(resVO.toString());
//      
//    }

    @Test
    public void testUpdate() {
     ResVO resVO = new ResVO();
     resVO.setResno(8);
     resVO.setResname("테스트 성공");
     resVO.setResaddress("테스트 성공");
     resVO.setResphone("테스트 성공");
     resVO.setRestime("테스트 성공");
     
     int cnt = this.resProc.update(resVO);
     System.out.println("->"+cnt);
     
    }
}
