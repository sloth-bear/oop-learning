package com.bear.effectivejava.controller;

import com.bear.effectivejava.controller.builder.BudgetInsertRequestBuilders;
import com.bear.effectivejava.item01.controller.BudgetController;
import com.bear.effectivejava.item01.dto.BudgetInsertRequest;
import com.bear.effectivejava.item01.entity.BudgetEntity;
import com.bear.effectivejava.item01.service.BudgetService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(BudgetController.class)
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
class BudgetControllerTest {

    private static final String PATH = "/v1/api/budgets";

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    BudgetService budgetService;

    @Test
    void givenValidRequest_whenPostingBudget_thenCreatedResponse() throws Exception {
        final BudgetInsertRequest request = BudgetInsertRequestBuilders.newFilledRequest().build();

        final BudgetEntity savedEntity = BudgetEntity.builder().seq(999L).build();
        doReturn(savedEntity).when(budgetService).insert(request);

        mockMvc.perform(
                        post(PATH)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(
                        header().string(HttpHeaders.LOCATION,
                                Matchers.endsWith(PATH + "/" + savedEntity.getSeq())))
                .andDo(print());
    }

    @Test
    void givenInvalidRequest_whenPostingBudget_thenCreatedResponse() throws Exception {
        final BudgetInsertRequest request = BudgetInsertRequestBuilders.newFilledRequest()
                .institution(null)
                .budgetCategorySeq(null)
                .content(null)
                .build();

        mockMvc.perform(
                        post(PATH)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(
                        header().doesNotExist(HttpHeaders.LOCATION))
                .andDo(print());

        verify(budgetService, times(0)).insert(any());
    }
}