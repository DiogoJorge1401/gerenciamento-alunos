package com.diogo.spring_project.Repositorys;

import com.diogo.spring_project.Models.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Usuario, Long> {

  @Query("select j from Usuario j where j.user = :user and j.password = :password")
  public Usuario searchLogin(String user, String password);
}
