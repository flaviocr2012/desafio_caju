package com.desafio.caju.controllers;

import com.desafio.caju.dtos.TransactionDTO;
import com.desafio.caju.services.TransactionService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@WebMvcTest(TransactionController.class)
public class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionService transactionService;

    @Test
    public void testAuthorizeTransaction_ShouldReturnStatusOkAndCode00() throws Exception {
        // Arrange
        Mockito.when(transactionService.authorizeTransaction(any(TransactionDTO.class)))
                .thenReturn("00");

        String transactionJson = "{"
                + "\"accountId\": \"123\","
                + "\"totalAmount\": \"100.00\","
                + "\"mcc\": \"5411\","
                + "\"merchant\": \"PADARIA DO ZE SAO PAULO BR\""
                + "}";

        mockMvc.perform(post("/api/v1/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(transactionJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("00"))
                .andDo(print());
    }

    @Test
    public void testAuthorizeTransaction_ShouldReturnStatusOkAndCode51() throws Exception {

        Mockito.when(transactionService.authorizeTransaction(any(TransactionDTO.class)))
                .thenReturn("51");

        String transactionJson = "{"
                + "\"accountId\": \"123\","
                + "\"totalAmount\": \"500.00\","
                + "\"mcc\": \"5411\","
                + "\"merchant\": \"PADARIA DO ZE               SAO PAULO BR\""
                + "}";

        mockMvc.perform(post("/api/v1/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(transactionJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("51"))
                .andDo(print());
    }

}
