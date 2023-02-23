package com.example.Student_Library_Management_System.Controllers;

import com.example.Student_Library_Management_System.DTOs.IssueBookDto;
import com.example.Student_Library_Management_System.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("/issueBook")
    public String issueBook( @RequestBody IssueBookDto issueBookDto){
        try {
            return transactionService.issueBook(issueBookDto);
        }
        catch(Exception e){
            return e.getMessage();
        }
    }

    @GetMapping("/getTxnInfo")
    public String getTransaction(@RequestParam("bookId") int bookId, @RequestParam("cardId") int cardId){
        return transactionService.getTransactions(bookId, cardId);
    }
}
