package com.example.Student_Library_Management_System.DTOs;

import com.example.Student_Library_Management_System.Enums.Genre;

public class BookResponseDto {
    private String name;
    private int pages;
    private int authId;
    private Genre genre;

    public BookResponseDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getAuthId() {
        return authId;
    }

    public void setAuthId(int authId) {
        this.authId = authId;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
