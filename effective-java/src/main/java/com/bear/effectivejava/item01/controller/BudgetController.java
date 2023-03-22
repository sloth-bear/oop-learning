package com.bear.effectivejava.item01.controller;

import com.bear.effectivejava.item01.dto.BudgetInsertRequest;
import com.bear.effectivejava.item01.dto.ResponseEntities;
import com.bear.effectivejava.item01.entity.BudgetEntity;
import com.bear.effectivejava.item01.service.BudgetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/api/budgets")
@RequiredArgsConstructor
public class BudgetController {

    private final BudgetService budgetService;

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody @Valid final BudgetInsertRequest request) {
        final BudgetEntity inserted = budgetService.insert(request);
        return ResponseEntities.created(inserted.getSeq());
    }

}
