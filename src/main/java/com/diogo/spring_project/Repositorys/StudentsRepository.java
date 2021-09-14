package com.diogo.spring_project.Repositorys;

import java.util.List;

import com.diogo.spring_project.Enums.Status;
import com.diogo.spring_project.Models.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentsRepository extends JpaRepository<Student,Long> {
  
  List<Student> findByStatus(Status status);

  List<Student> findByName(String name);
}
