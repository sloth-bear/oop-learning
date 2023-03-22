package com.bear.effectivejava.item01.repository;

import com.bear.effectivejava.item01.entity.BudgetEntity;
import org.springframework.data.repository.CrudRepository;

public interface BudgetRepository extends CrudRepository<BudgetEntity, Long> {

}
