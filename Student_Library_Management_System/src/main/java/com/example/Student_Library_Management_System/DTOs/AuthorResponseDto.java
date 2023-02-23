package com.example.Student_Library_Management_System.DTOs;

import java.util.List;

public class AuthorResponseDto {
   private String name;
    private int age;

    private List<BookResponseDto> bookWritten;

    public AuthorResponseDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<BookResponseDto> getBookWritten() {
        return bookWritten;
    }

    public void setBookWritten(List<BookResponseDto> bookWritten) {
        this.bookWritten = bookWritten;
    }
}
