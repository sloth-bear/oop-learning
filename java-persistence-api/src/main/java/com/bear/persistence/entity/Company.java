package com.bear.persistence.entity;

import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Company {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "company", orphanRemoval = true)
  private List<Car> cars;

  public void addCar(final Car car) {
    this.cars.add(car);
    car.setCompany(this);
  }

  public void removeCar(final Car car) {
    car.setCompany(null);
    this.cars.remove(car);
  }

  public void removeCars() {
    final var iterator = this.cars.iterator();
    while (iterator.hasNext()) {
      final var car = iterator.next();
      car.setCompany(null);
      iterator.remove();
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Company company = (Company) o;
    return Objects.equals(id, company.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return "Company{" +
        "id=" + id +
        ", name='" + name + '\'' +
        '}';
  }
}
