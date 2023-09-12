package com.example.StudentCrud.entity;

import jakarta.persistence.*;

@Entity
@Table(name="student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    int id;
    @Column(name="course")
    String course;
    @Column(name="fee")
    Double fee;
    @Column(name="studentname")
    String studentName;

    public Student() {
    }

    public Student(String course, Double fee, String studentName) {
        this.course = course;
        this.fee = fee;
        this.studentName = studentName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", course='" + course + '\'' +
                ", fee=" + fee +
                ", studentName='" + studentName + '\'' +
                '}';
    }
}
