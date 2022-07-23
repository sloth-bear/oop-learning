package spring.proxy.pureproxy.concreteproxy.code;

public class ConcreteClient {

  private final Concrete concrete;

  public ConcreteClient(final Concrete concrete) {
    this.concrete = concrete;
  }

  public void execute() {
    concrete.operate();
  }
}
