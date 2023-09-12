package com.example.StudentCrud.service;


import com.example.StudentCrud.entity.Student;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImp implements IStudentService{

    //CRUD raw sql
    private DataSource dataSource;
    public StudentServiceImp(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public Student saveStudent(Student student) {
        String query = "INSERT INTO student(course,fee,studentname) values(?,?,?)";
        Student tempStudent = new Student();
        try{
            Connection conn = dataSource.getConnection();

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1,student.getCourse());
            statement.setDouble(2,student.getFee());
            statement.setString(3,student.getStudentName());

            int response = statement.executeUpdate();

            if(response > 0){
                System.out.println("Alumno registrado correctamente");
                tempStudent = student;
            }else{
                System.out.println("Error");
            }
        }catch(Exception e){
            e.printStackTrace();

        }
        return tempStudent;
    }

    @Override
    public List<Student> findAll() {
        String query = "SELECT * FROM student";
        List<Student> studentList = new ArrayList<>();

        try{
            Connection conn = dataSource.getConnection();

            PreparedStatement preparedStatement = conn.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                Student tempStudent = new Student();
                tempStudent.setId(resultSet.getInt("id"));
                tempStudent.setFee(resultSet.getDouble("fee"));
                tempStudent.setCourse(resultSet.getString("course"));
                tempStudent.setStudentName(resultSet.getString("studentname"));
                studentList.add(tempStudent);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return studentList;
    }

    @Override
    public Student findById(int id) {
        String query = "SELECT * FROM student WHERE id=?";
        Student tempStudent = new Student();
        try{
            Connection conn = dataSource.getConnection();
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1,id);

            ResultSet response = statement.executeQuery();
            if(response.next()){
                tempStudent.setId(response.getInt("id"));
                tempStudent.setCourse(response.getString("course"));
                tempStudent.setFee(response.getDouble("fee"));
                tempStudent.setStudentName(response.getString("studentname"));
                System.out.println(tempStudent);
            }else{
                System.out.println("Id not found");
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return tempStudent;
    }

    @Override
    public void updateStudent(Student student) {
        String query = "UPDATE student SET course=?, fee=?, studentname=? WHERE id=?";
        try{
            Connection conn = dataSource.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1,student.getCourse());
            preparedStatement.setDouble(2,student.getFee());
            preparedStatement.setString(3,student.getStudentName());
            preparedStatement.setInt(4,student.getId());
            preparedStatement.executeUpdate();
            System.out.println("Student updated");
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteById(int id) {
        String deleteQuery = "DELETE FROM student where id=?";
        try{
            Connection conn = dataSource.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(deleteQuery);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
            System.out.println("Student deleted");
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
