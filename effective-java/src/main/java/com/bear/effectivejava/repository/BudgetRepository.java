package com.bear.effectivejava.repository;

import com.bear.effectivejava.entity.BudgetEntity;
import org.springframework.data.repository.CrudRepository;

public interface BudgetRepository extends CrudRepository<BudgetEntity, Long> {

}
