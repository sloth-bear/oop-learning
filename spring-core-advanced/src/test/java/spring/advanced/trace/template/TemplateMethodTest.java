package spring.advanced.trace.template;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import spring.advanced.trace.template.code.AbstractTemplate;
import spring.advanced.trace.template.code.Logic1Template;
import spring.advanced.trace.template.code.Logic2Template;

@Slf4j
public class TemplateMethodTest {

  @Test
  void templateMethodV0() {
    logic1();
    logic2();
  }

  @Test
  void templateMethodV1() {
    final AbstractTemplate logic1 = new Logic1Template();
    logic1.execute();

    final AbstractTemplate logic2 = new Logic2Template();
    logic2.execute();
  }

  @Test
  void templateMethodV2() {
    final AbstractTemplate logic1 = new AbstractTemplate() {
      @Override
      protected void call() {
        log.info("This is the business logic1");
      }
    };

    log.info(logic1.getClass().toString());
    logic1.execute();

    final AbstractTemplate logic2 = new AbstractTemplate() {
      @Override
      protected void call() {
        log.info("This is the business logic2 ");
      }
    };

    log.info(logic2.getClass().toString());
    logic2.execute();
  }

  private void logic1() {
    final var startTime = System.currentTimeMillis();

    log.info("This is the business logic1");

    final var endTime = System.currentTimeMillis();
    final var result = endTime - startTime;
    log.info(String.format("result: {}%d", result));
  }

  private void logic2() {
    final var startTime = System.currentTimeMillis();

    log.info("This is the business logic2");

    final var endTime = System.currentTimeMillis();
    final var result = endTime - startTime;
    log.info(String.format("result: {}%d", result));
  }

}
