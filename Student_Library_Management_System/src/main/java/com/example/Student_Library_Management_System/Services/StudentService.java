package com.example.Student_Library_Management_System.Services;

import com.example.Student_Library_Management_System.Enums.CardStatus;
import com.example.Student_Library_Management_System.Models.Card;
import com.example.Student_Library_Management_System.Models.Student;
import com.example.Student_Library_Management_System.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public String createStudent(Student student){

        Card card = new Card();
        card.setCardStatus(CardStatus.ACTIVATED);

        card.setStudent(student);

        student.setCard(card);

        studentRepository.save(student);
        return "Student-info and card created";

    }

    public String findByEmail(String email){
        Student student = studentRepository.findByEmail(email);

        return student.getName();
    }
    public String updateMob(Student newStudent){
        Student originalStudent = studentRepository.findById(newStudent.getId()).get();

        originalStudent.setMobNo(newStudent.getMobNo());

        studentRepository.save(originalStudent);

        return "student updated ";
    }
}
