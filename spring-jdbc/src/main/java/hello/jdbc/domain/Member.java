package hello.jdbc.domain;

import lombok.Data;

@Data
public class Member {

  private String id;
  private int money;

  public Member() {
  }

  public Member(final String id, final int money) {
    this.id = id;
    this.money = money;
  }

}
