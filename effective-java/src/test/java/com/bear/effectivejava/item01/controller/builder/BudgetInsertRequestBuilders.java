package com.bear.effectivejava.controller.builder;

import com.bear.effectivejava.item01.dto.BudgetInsertRequest;

public class BudgetInsertRequestBuilders {

  private BudgetInsertRequestBuilders() {
    throw new UnsupportedOperationException();
  }

  public static BudgetInsertRequest.BudgetInsertRequestBuilder newFilledRequest() {
    return BudgetInsertRequest.builder()
        .budgetCategorySeq(1L)
        .institution("TEST")
        .content("TEST");
  }
}
