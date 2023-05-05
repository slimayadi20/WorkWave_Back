package com.example.workwave.controllers;

import com.example.workwave.services.BalanceService;
import com.example.workwave.services.BalanceService.BalanceChange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BalanceController {

    @Autowired
    private BalanceService balanceService;

    @GetMapping("/balance")
    public ResponseEntity<List<BalanceChange>> getBalanceChanges(@RequestParam("account") Long accountId) {
        List<BalanceChange> balanceChanges = balanceService.getBalanceChange(accountId);
        return new ResponseEntity<>(balanceChanges, HttpStatus.OK);
    }
}
