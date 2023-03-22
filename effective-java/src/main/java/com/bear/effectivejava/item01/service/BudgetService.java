package com.bear.effectivejava.item01.service;

import com.bear.effectivejava.item01.dto.BudgetInsertRequest;
import com.bear.effectivejava.item01.entity.BudgetEntity;
import com.bear.effectivejava.item01.repository.BudgetRepository;
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
