package com.example.StudentCrud.controller;

import com.example.StudentCrud.entity.Student;
import com.example.StudentCrud.service.StudentServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentController {
    private StudentServiceImp studentServiceImp;
    @Autowired
    public StudentController(StudentServiceImp studentServiceImp){
        this.studentServiceImp = studentServiceImp;
    }

    @GetMapping("/students")
    public List<Student> findAll(){
        return studentServiceImp.findAll();
    }

    @GetMapping("/students/{id}")
    public Student findById(@PathVariable int id){
        return studentServiceImp.findById(id);
    }

    @PutMapping("/students")
    public void updateStudent(@RequestBody Student student){
        studentServiceImp.updateStudent(student);
    }

    @PostMapping("/students")
    public void saveStudent(@RequestBody Student student){
        studentServiceImp.saveStudent(student);
    }
}
