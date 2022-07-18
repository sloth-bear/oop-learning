package spring.core.lifecycle;

public interface NetworkConnectable {
  
  void setUrl(final String url);

  void connect();

  void call(final String message);

  void disconnect();

}
