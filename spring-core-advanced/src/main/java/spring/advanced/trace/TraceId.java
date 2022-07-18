package spring.advanced.trace;

import java.util.UUID;
import lombok.Getter;

/**
 * The transaction id object. This object represents id of transaction for log tracer. The id
 * presents identifier of transaction, and level presents depth of transaction trace.
 */
@Getter
public class TraceId {

  private final String id;
  private final int level;

  public TraceId() {
    this.id = generateId();
    this.level = 0;
  }

  private TraceId(final String id, final int level) {
    this.id = id;
    this.level = level;
  }

  public TraceId createNextLevel() {
    return new TraceId(id, getNextLevel());
  }

  public TraceId createPrevLevel() {
    return new TraceId(id, getPrevLevel());
  }

  public boolean isFirstLevel() {
    return level == 0;
  }

  public int getNextLevel() {
    return level + 1;
  }

  public int getPrevLevel() {
    return level - 1;
  }

  private String generateId() {
    return UUID.randomUUID().toString().substring(0, 8);
  }

}
