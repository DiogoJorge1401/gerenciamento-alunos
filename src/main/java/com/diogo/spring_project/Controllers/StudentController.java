package com.diogo.spring_project.Controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.diogo.spring_project.Enums.Status;
import com.diogo.spring_project.Models.Student;
import com.diogo.spring_project.Services.StudentsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StudentController {
  @Autowired
  private StudentsService service;
  private final ModelAndView mv = new ModelAndView();

  @GetMapping("inserirAluno")
  public ModelAndView insertStudentView(HttpSession session) {
    if (session.getAttribute("userLogged") == null) {
      mv.setViewName("redirect:/index");
      return mv;
    }
    mv.setViewName("Student/formStudent");
    mv.addObject("student", new Student());
    return mv;
  }

  @PostMapping("inserirAluno")
  public ModelAndView insertStudent(@Valid Student student, BindingResult result) {
    if (result.hasErrors()) {
      System.out.println(student.getRegistration());
      mv.setViewName("Student/formStudent");
      mv.addObject("student", student);
      return mv;
    }
    mv.setViewName("redirect:/listarAlunos");
    service.save(student);
    return mv;
  }

  @GetMapping("listarAlunos")
  public ModelAndView listStudents(HttpSession session) {
    if (session.getAttribute("userLogged") == null) {
      mv.setViewName("redirect:/index");
      return mv;
    }
    List<Student> students = service.findAll();
    mv.setViewName("Student/listStudents");
    mv.addObject("students", students);
    return mv;
  }

  @GetMapping("editar")
  public ModelAndView editStudentView(@RequestParam long id, HttpSession session) {
    if (session.getAttribute("userLogged") == null) {
      mv.setViewName("redirect:/index");
      return mv;
    }
    Student student = service.findById(id);
    mv.setViewName("Student/formStudent");
    mv.addObject("student", student);
    return mv;
  }

  @GetMapping("remover")
  public ModelAndView deleteStudent(@RequestParam long id, HttpSession session) {
    if (session.getAttribute("userLogged") == null) {
      mv.setViewName("redirect:/index");
      return mv;
    }
    Student student = service.findById(id);
    mv.setViewName("redirect:/listarAlunos");
    service.delete(student);
    return mv;
  }

  @GetMapping("filtroAlunos")
  public ModelAndView filterStudenstsView(HttpSession session) {
    if (session.getAttribute("userLogged") == null) {
      mv.setViewName("redirect:/index");
      return mv;
    }
    ModelAndView mv = new ModelAndView();
    mv.setViewName("Student/filterStudents");
    return mv;
  }

  @GetMapping("listarAlunos/{statusS}")
  public ModelAndView listStudentsByStatus(@PathVariable String statusS, HttpSession session) {
    if (session.getAttribute("userLogged") == null) {
      mv.setViewName("redirect:/index");
      return mv;
    }
    Status status = Status.valueOf(statusS);
    List<Student> students = service.findByStatus(status);
    mv.setViewName("Student/listStudents");
    mv.addObject("students", students);
    return mv;
  }

  @PostMapping("aluno")
  public ModelAndView findByName(String name) {
    List<Student> student = service.findByName(name);
    mv.setViewName("Student/listStudents");
    mv.addObject("students", student);
    return mv;
  }
}
