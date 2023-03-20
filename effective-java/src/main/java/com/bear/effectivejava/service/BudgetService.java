package com.bear.effectivejava.service;

import com.bear.effectivejava.dto.BudgetInsertRequest;
import com.bear.effectivejava.entity.BudgetEntity;
import com.bear.effectivejava.repository.BudgetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BudgetService {

  private BudgetRepository budgetRepository;

  public BudgetEntity insert(final BudgetInsertRequest request) {
    return budgetRepository.save(BudgetEntity.from(request));
  }
}
