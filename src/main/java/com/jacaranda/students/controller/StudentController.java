package com.jacaranda.students.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jacaranda.students.model.Student;
import com.jacaranda.students.service.StudentService;


@Controller
public class StudentController {
	
	@Autowired
	StudentService repositorio;
	
	@GetMapping({"/","listStudents"})
	public String listStudents(Model model) {
		model.addAttribute("lista", repositorio.getLista());
		return "listStudents";
	}
	
	@GetMapping("addStudent")
	public String addStudent(Model model) {
		Student student = new Student(); //Como se lo estoy pasando al formulario por primera vez, tengo que instanciar el objeto
		model.addAttribute("student", student);  //El primer "student" es el nombre que tendrá que recoger el formulario.
												//El segundo es el nombre del objeto que yo acabo de crear
		return "addStudent";
	}
	
	@PostMapping("addStudentSubmit")
	public String addStudentSubmit(Model model, @ModelAttribute("student") Student student) {
		repositorio.addStudent(student);
		return "redirect:/listStudents";
	}
	
	@GetMapping("/deleteStudent")
	public String deleteStudent(Model model, 
			@RequestParam(name="name", required=false, defaultValue="") String name,
			@RequestParam(name="surname", required=false, defaultValue="") String surname) {
		Student student = repositorio.getStudent(name, surname);
		model.addAttribute("student", student);
		return "deleteStudent";
	}
	
	@PostMapping("/deleteStudentSubmit")
	public String deleteStudentSubmit(Model model, @ModelAttribute("student") Student student) {
		repositorio.removeStudent(student);
		return "redirect:/listStudents";
	}
	
	@GetMapping("/editStudent")
	public String editStudent(Model model, 
			@RequestParam(name="name", required=false, defaultValue="") String name,
			@RequestParam(name="surname", required=false, defaultValue="") String surname) {
		Student student = repositorio.getStudent(name, surname);
		model.addAttribute("student", student);
		return "editStudent";
	}
	
	@PostMapping("/editStudentSubmit")
	public String editStudentSubmit(Model model, @ModelAttribute("student") Student student) {
		repositorio.updateStudent(student.getName(), student.getSurname(), student.getAge());
		return "redirect:/listStudents";
	}
	
}
