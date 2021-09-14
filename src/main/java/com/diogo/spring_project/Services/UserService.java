package com.diogo.spring_project.Services;

import java.security.NoSuchAlgorithmException;

import com.diogo.spring_project.Exceptions.EmailAlredyExistsException;
import com.diogo.spring_project.Exceptions.EncryptExcecption;
import com.diogo.spring_project.Models.Usuario;
import com.diogo.spring_project.Repositorys.UserRepository;
import com.diogo.spring_project.Utils.Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  @Autowired
  private UserRepository repository;

  public Usuario save(Usuario user) throws EncryptExcecption, EmailAlredyExistsException {
    try {
      if (findUser(user.getEmail())) {
        throw new EmailAlredyExistsException("Esse email já está cadastrado!");
      }
      user.setPassword(Util.md5(user.getPassword()));
    } catch (NoSuchAlgorithmException e) {
      throw new EncryptExcecption("Erro na criptografia da senha!");
    }
    return repository.save(user);
  }

  public Usuario loginUser(Usuario usuario) throws NoSuchAlgorithmException {
    Usuario userLogin = repository.searchLogin(usuario.getUser(), Util.md5(usuario.getPassword()));
    return userLogin;
  }

  private boolean findUser(String email) {
    for (Usuario u : repository.findAll()) {
      if (u.getEmail().equals(email)) {
        return true;
      }
    }
    return false;
  }
}
