package com.example.StudentCrud.service;

import com.example.StudentCrud.entity.Student;

import java.util.List;

public interface IStudentService {
    //Create
    public Student saveStudent(Student student);
    //Read
    public List<Student> findAll();
    public Student findById(int id);
    //Update
    public void updateStudent(Student student);
    //Delete
    public Student deleteById(int id);



}
