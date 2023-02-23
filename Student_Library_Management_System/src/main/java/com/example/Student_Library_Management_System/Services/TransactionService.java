package com.example.Student_Library_Management_System.Services;

import com.example.Student_Library_Management_System.DTOs.IssueBookDto;
import com.example.Student_Library_Management_System.Enums.CardStatus;
import com.example.Student_Library_Management_System.Enums.TransactionStatus;
import com.example.Student_Library_Management_System.Models.Book;
import com.example.Student_Library_Management_System.Models.Card;
import com.example.Student_Library_Management_System.Models.Transaction;
import com.example.Student_Library_Management_System.Repositories.BookRepository;
import com.example.Student_Library_Management_System.Repositories.CardRepository;
import com.example.Student_Library_Management_System.Repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    BookRepository bookRepository;
    @Autowired
    CardRepository cardRepository;

    public String issueBook(IssueBookDto issueBookDto) throws  Exception{

        int bookId = issueBookDto.getBookId();
        int cardId = issueBookDto.getCardId();

        Book book = bookRepository.findById(bookId).get();
        Card card = cardRepository.findById(cardId).get();

        Transaction transaction = new Transaction();

        transaction.setBook(book);
        transaction.setCard(card);
        transaction.setTransactionId(UUID.randomUUID().toString());
        transaction.setIssueOperation(true);
        transaction.setTransactionStatus(TransactionStatus.PENDING);

        if(book==null || book.isIssued()==true){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new Exception("Book is not available");
        }

        if(card==null || !card.getCardStatus().equals(CardStatus.ACTIVATED)){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new Exception("card is not valid");
        }

        transaction.setTransactionStatus(TransactionStatus.SUCCESS);
       // transactionRepository.save(transaction);

        book.setIssued(true);

        List<Transaction> listOfTransactionsForBook = book.getTransactionList();
        listOfTransactionsForBook.add(transaction);
        book.setTransactionList(listOfTransactionsForBook);

        List<Book> issuedBooksForCard = card.getBooksIssued();
        issuedBooksForCard.add(book);
        card.setBooksIssued(issuedBooksForCard);

        List<Transaction> transactionsForCard = card.getTransactions();
        transactionsForCard.add(transaction);
        card.setTransactions(transactionsForCard);

        cardRepository.save(card);

        return "Book issued successfully";
    }

    public String getTransactions(int bookId, int cardId){
        List<Transaction> transactionList = transactionRepository.getTransactionsForBookAndCard(bookId,cardId);

        String transactionId = transactionList.get(0).getTransactionId();
        return transactionId;
    }
}
