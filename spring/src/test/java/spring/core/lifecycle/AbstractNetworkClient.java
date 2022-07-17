package spring.core.lifecycle;

public class AbstractNetworkClient implements NetworkConnectable {

  private String url;

  public AbstractNetworkClient() {
    System.out.println("Call constructor, url: " + url);
  }

  @Override
  public void setUrl(final String url) {
    this.url = url;
  }

  @Override
  public void connect() {
    System.out.println("connect: " + url);
  }

  @Override
  public void call(final String message) {
    System.out.println("call: " + url + ", message: " + message);
  }

  @Override
  public void disconnect() {
    System.out.println("disconnect: " + url);
  }

}
