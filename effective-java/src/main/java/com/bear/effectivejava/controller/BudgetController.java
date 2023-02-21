package com.bear.effectivejava.controller;

import com.bear.effectivejava.dto.BudgetInsertRequest;
import com.bear.effectivejava.dto.ResponseEntities;
import com.bear.effectivejava.service.BudgetService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/budgets")
@RequiredArgsConstructor
public class BudgetController {

  private final BudgetService budgetService;

  @PostMapping
  public ResponseEntity<Void> insert(@RequestBody @Valid final BudgetInsertRequest request) {
    final var inserted = budgetService.insert(request);
    return ResponseEntities.created(inserted.getSeq());
  }

}
