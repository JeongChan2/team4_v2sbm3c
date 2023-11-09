package dev.mvc.resort_v1sbm3c;

import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import dev.mvc.manager.ManagerProcInter;
import dev.mvc.manager.ManagerVO;
import dev.mvc.res.ResProcInter;
import dev.mvc.rescontents.RescontentsProcInter;

@SpringBootTest
public class RescontentsTest {
  @Autowired
  @Qualifier("dev.mvc.manager.ManagerProc")   // "dev.mvc.manager.managerProc"라고 명명된 클래스 (Qualifier->이름을 찾아와주는 역할)
  private ManagerProcInter managerProc;       // managerProcInter를 구현한 managerProc 클래스의 객체를 자동으로 생성하여 할당.

  @Autowired
  @Qualifier("dev.mvc.res.ResProc")  // @Component("dev.mvc.cate.CateProc")
  private ResProcInter resProc;
  
  @Autowired
  @Qualifier("dev.mvc.rescontents.RescontentsProc") // @Component("dev.mvc.contents.ContentsProc")
  private RescontentsProcInter rescontentsProc;
  
//  @Test
//  public void testRead() {
//    HashMap<String, Object> hashMap = new HashMap<String, Object>();
//    hashMap.put("rescontentsno", 15);
//    hashMap.put("passwd", "1234");
//    
//    System.out.println("-> cnt: " + this.rescontentsProc.password_check(hashMap));
//  }
  
  @Test
  public void testCount_by_resno() {
    System.out.println("-> cnt: " + this.rescontentsProc.count_by_resno(1));          
  }
  
}
