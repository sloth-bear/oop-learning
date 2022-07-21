package spring.advanced.trace.log.callback;

public interface TraceCallback<T> {

  T call();
}
