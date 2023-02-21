package com.bear.effectivejava.entity;

import com.bear.effectivejava.dto.BudgetInsertRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@Builder
public class BudgetEntity {

  @Id
  private Long seq;

  private Long budgetCategorySeq;

  private String institution;

  private String content;

  private int amount;

  private String etc;

  public static BudgetEntity from(final BudgetInsertRequest request) {
    return BudgetEntity.builder()
        .budgetCategorySeq(request.getBudgetCategorySeq())
        .institution(request.getInstitution())
        .content(request.getContent())
        .amount(request.getAmount())
        .etc(request.getEtc())
        .build();
  }
}
