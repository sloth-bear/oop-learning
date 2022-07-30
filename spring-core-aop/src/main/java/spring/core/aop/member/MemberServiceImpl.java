package spring.core.aop.member;

import org.springframework.stereotype.Service;
import spring.core.aop.member.annotation.ClassAop;
import spring.core.aop.member.annotation.MethodAop;

@Service
@ClassAop
public class MemberServiceImpl implements MemberService {

  @Override
  @MethodAop("test value")
  public String externalGreet(final String name) {
    return "Hello! " + name;
  }

  public String internalGreet(final String name) {
    return "Happy";
  }
}
