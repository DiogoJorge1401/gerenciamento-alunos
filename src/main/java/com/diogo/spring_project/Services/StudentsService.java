package com.diogo.spring_project.Services;

import java.util.List;

import com.diogo.spring_project.Enums.Status;
import com.diogo.spring_project.Models.Student;
import com.diogo.spring_project.Repositorys.StudentsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentsService {
  @Autowired
  private StudentsRepository repository;

  public void save(Student student){
    repository.save(student);
  }
  public List<Student> findAll(){
    return repository.findAll();
  }
  public Student findById(long id){
    return repository.getById(id);
  }
  public void delete(Student student) {
    repository.delete(student);
  }
  public List<Student> findByStatus(Status status){
    return repository.findByStatus(status);
  }
  public List<Student> findByName(String name) {
    return repository.findByName(name);
  }
}
