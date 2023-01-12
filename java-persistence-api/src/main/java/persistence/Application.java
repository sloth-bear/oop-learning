package persistence;

import javax.persistence.Persistence;
import persistence.entity.Customer;

public class Application {

  public static void main(String[] args) {
    final var emf = Persistence.createEntityManagerFactory("jpabook");
    final var em = emf.createEntityManager();
    final var tx = em.getTransaction();

    try {
      tx.begin();
      final var customer = Customer.of(3L, "Tester", 20);
      em.persist(customer);

      customer.setAge(30);

      final var foundEntity = em.find(Customer.class, 3L);
      em.remove(foundEntity);

      final var query = em.createQuery("SELECT c from Customer c", Customer.class);
      final var customers = query.getResultList();
      System.out.println(customers);
      tx.commit();
    } catch (Exception e) {
      System.out.println(e.getMessage());
      tx.rollback();
    } finally {
      em.close();
      emf.close();
    }
  }
}
