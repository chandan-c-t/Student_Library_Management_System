package com.example.Student_Library_Management_System.Services;

import com.example.Student_Library_Management_System.DTOs.AuthorEntryDto;
import com.example.Student_Library_Management_System.DTOs.AuthorResponseDto;
import com.example.Student_Library_Management_System.DTOs.BookResponseDto;
import com.example.Student_Library_Management_System.Models.Author;
import com.example.Student_Library_Management_System.Models.Book;
import com.example.Student_Library_Management_System.Repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    public String createAuthor(AuthorEntryDto authorEntryDto){

        Author author = new Author();

        author.setName(authorEntryDto.getName());
        author.setAge(authorEntryDto.getAge());
        authorRepository.save(author);

        return "Author added";
    }

    public AuthorResponseDto getAuthor(int authId){
        Author author =  authorRepository.findById(authId).get();

        AuthorResponseDto authorResponseDto = new AuthorResponseDto();
        List<Book> bookList = author.getBooksWritten();

        List<BookResponseDto> bookResponseDtoList = new ArrayList<>();

        for(Book b : bookList){
            BookResponseDto bookResponseDto = new BookResponseDto();

            bookResponseDto.setGenre(b.getGenre());
            bookResponseDto.setPages(b.getPages());
            bookResponseDto.setName(b.getName());


            bookResponseDtoList.add(bookResponseDto);
        }

        authorResponseDto.setBookWritten(bookResponseDtoList);
        authorResponseDto.setName(author.getName());
        authorResponseDto.setAge(author.getAge());

        return authorResponseDto;
    }
}
