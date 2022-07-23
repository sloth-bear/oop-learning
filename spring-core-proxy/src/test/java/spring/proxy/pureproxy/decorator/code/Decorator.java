package spring.proxy.pureproxy.decorator.code;

public abstract class Decorator implements Component {

  protected final Component component;

  protected Decorator(final Component component) {
    this.component = component;
  }

}
