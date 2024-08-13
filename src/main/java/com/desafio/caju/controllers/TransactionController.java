package com.desafio.caju.controllers;

import com.desafio.caju.dtos.TransactionDTO;
import com.desafio.caju.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Map<String, String>> authorizeTransaction(@RequestBody TransactionDTO transactionDTO) {
            String code = transactionService.authorizeTransaction(transactionDTO);
            return ResponseEntity.ok(Collections.singletonMap("code", code));

        }
    }


