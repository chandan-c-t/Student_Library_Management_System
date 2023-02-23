package com.example.Student_Library_Management_System.Services;

import com.example.Student_Library_Management_System.DTOs.BookReqDto;
import com.example.Student_Library_Management_System.Models.Author;
import com.example.Student_Library_Management_System.Models.Book;
import com.example.Student_Library_Management_System.Repositories.AuthorRepository;
import com.example.Student_Library_Management_System.Repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    AuthorRepository authorRepository;



    public String addBook(BookReqDto bookReqDto){



        int authorId = bookReqDto.getAuthorId();

        Author author = authorRepository.findById(authorId).get();

       Book book = new Book();

       book.setName(bookReqDto.getName());

       book.setGenre(bookReqDto.getGenre());

       book.setPages(bookReqDto.getPages());

       book.setAuthor(author);


        List<Book> currentBooksWritten = author.getBooksWritten();
        currentBooksWritten.add(book);

        author.setBooksWritten(currentBooksWritten);

        authorRepository.save(author);

//        bookRepository.save(book);
        return "book added";
    }
}
