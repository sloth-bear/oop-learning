package spring.advanced.trace.strategy;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import spring.advanced.trace.strategy.code.callback.Callback;
import spring.advanced.trace.strategy.code.callback.TimeLogTemplate;

@Slf4j
public class TemplateCallbackTest {

  @Test
  @SuppressWarnings("Convert2Lambda")
  void anonymous() {
    final TimeLogTemplate template = new TimeLogTemplate();
    template.execute(new Callback() {
      @Override
      public void call() {
        log.info("logic1");
      }
    });

    template.execute(new Callback() {
      @Override
      public void call() {
        log.info("logic2");
      }
    });
  }

  @Test
  void lambda() {
    final TimeLogTemplate template = new TimeLogTemplate();
    template.execute(() -> log.info("logic1"));
    template.execute(() -> log.info("logic2"));
  }
}
