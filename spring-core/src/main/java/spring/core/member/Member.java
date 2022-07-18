package spring.core.member;

public class Member {

  private Long id;
  private String name;
  private Grade grade;

  public Member(final Long id, final String name, final Grade grade) {
    this.id = id;
    this.name = name;
    this.grade = grade;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Grade getGrade() {
    return grade;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public void setGrade(final Grade grade) {
    this.grade = grade;
  }

  @Override
  public String toString() {
    return "Member{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", grade=" + grade +
        '}';
  }
}
