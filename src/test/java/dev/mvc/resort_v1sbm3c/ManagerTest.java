package dev.mvc.resort_v1sbm3c;

import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import dev.mvc.manager.ManagerProcInter;
import dev.mvc.manager.ManagerVO;

@SpringBootTest
public class ManagerTest {
  @Autowired
  @Qualifier("dev.mvc.manager.ManagerProc")   // "dev.mvc.manager.managerProc"라고 명명된 클래스 (Qualifier->이름을 찾아와주는 역할)
  private ManagerProcInter managerProc;       // managerProcInter를 구현한 managerProc 클래스의 객체를 자동으로 생성하여 할당.

  @Test
  public void testRead() {
    ManagerVO managerVO = this.managerProc.read(1);
    System.out.println(managerVO.toString());
  }
}
