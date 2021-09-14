package com.diogo.spring_project.Controllers;

import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.diogo.spring_project.Exceptions.EmailAlredyExistsException;
import com.diogo.spring_project.Exceptions.EncryptExcecption;
import com.diogo.spring_project.Models.Usuario;
import com.diogo.spring_project.Services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
  @Autowired
  private UserService service;
  private final ModelAndView mv = new ModelAndView();

  @GetMapping("index")
  public ModelAndView index(HttpSession session) {
    if (session.getAttribute("userLogged") == null) {
      mv.setViewName("redirect:/");
      mv.addObject("msg", "Você precisa estar logado!");
      return mv;
    }
    mv.setViewName("home/index");
    Object attribute = session.getAttribute("bemVindo");
    bemVindoMessage(session, attribute);
    return mv;
  }

  private void bemVindoMessage(HttpSession session, Object attribute) {
    if (attribute != null) {
      mv.addObject("ok", true);
      session.removeAttribute("bemVindo");
    } else {
      mv.addObject("ok", null);
    }
  }

  @GetMapping
  public ModelAndView login() {
    mv.setViewName("Login/login");
    mv.addObject("usuario", new Usuario());
    return mv;
  }

  @GetMapping("cadastro")
  public ModelAndView register() {
    mv.setViewName("Login/register");
    mv.addObject("usuario", new Usuario());
    return mv;
  }

  @PostMapping("salvarUser")
  public ModelAndView save(@Valid Usuario usuario, HttpSession session)
      throws EncryptExcecption, EmailAlredyExistsException {
    mv.setViewName("redirect:/index");
    Usuario user = service.save(usuario);
    session.setAttribute("userLogged", user);
    session.setAttribute("bemVindo", true);
    return mv;
  }

  @PostMapping("login")
  public ModelAndView login(Usuario usuario, BindingResult result, HttpSession session)
      throws NoSuchAlgorithmException {
    mv.setViewName("redirect:/index");
    Usuario user = service.loginUser(usuario);
    mv.addObject("msg", null);
    if (user == null) {
      mv.setViewName("redirect:/");
      mv.addObject("msg", "Usuário não encontrado!");
      return mv;
    }
    session.setAttribute("userLogged", user);
    session.setAttribute("bemVindo", true);
    return mv;
  }

  @PostMapping("logout")
  public ModelAndView logout(HttpSession session) {
    session.invalidate();
    mv.setViewName("redirect:/");
    return mv;
  }
}
