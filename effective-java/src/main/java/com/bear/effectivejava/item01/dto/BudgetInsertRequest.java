package com.bear.effectivejava.item01.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@EqualsAndHashCode
public class BudgetInsertRequest {

  @Min(1)
  @NotNull
  private Long budgetCategorySeq;

  @NotNull
  @Size(min = 1, max = 20)
  private String institution;

  @NotNull
  @Size(min = 1, max = 100)
  private String content;

  @Max(Integer.MAX_VALUE)
  private int amount;

  private String etc;
}
