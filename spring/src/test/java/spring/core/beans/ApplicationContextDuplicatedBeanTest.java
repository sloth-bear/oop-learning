package spring.core.beans;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.core.member.MemberRepository;
import spring.core.member.MemoryMemberRepository;

public class ApplicationContextDuplicatedBeanTest {

  AnnotationConfigApplicationContext dbcContext = new AnnotationConfigApplicationContext(
      DuplicatedBeanConfig.class);

  @Test
  @DisplayName("타입 조회 시 같은 타입이 둘 이상 존재하면 중복 에러가 발생한다.")
  void byDuplicatedType() {
    assertThrows(NoUniqueBeanDefinitionException.class,
        () -> dbcContext.getBean(MemberRepository.class));
  }

  @Test
  @DisplayName("같은 타입이 둘 이상 존재하는 빈은 타입으로 조회 시 빈 이름을 지정하면 정상적으로 조회된다.")
  void byDuplicatedTypeWithName() {
    final var memberRepository = dbcContext.getBean("memberRepository1", MemberRepository.class);
    assertThat(memberRepository).isInstanceOf(MemberRepository.class);
  }

  @Test
  @DisplayName("특정 타입을 모두 조회한다.")
  void findAllTypes() {
    final var beansOfType = dbcContext.getBeansOfType(MemberRepository.class);
    assertThat(beansOfType.size()).isEqualTo(2);
  }

  @Configuration
  static class DuplicatedBeanConfig {

    @Bean
    public MemberRepository memberRepository1() {
      return new MemoryMemberRepository();
    }

    @Bean
    public MemberRepository memberRepository2() {
      return new MemoryMemberRepository();
    }
  }

}
