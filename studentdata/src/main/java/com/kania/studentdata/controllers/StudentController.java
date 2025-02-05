package com.kania.studentdata.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.kania.studentdata.models.Student;
import com.kania.studentdata.models.StudentDto;
import com.kania.studentdata.repository.StudentRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/students")
public class StudentController {

	@Autowired
	private StudentRepository studentRepo;
	
	@GetMapping({"","/"})
	public String getStudents(Model model) {
		var students = studentRepo.findAll(Sort.by(Sort.Direction.DESC, "NIM"));
		model.addAttribute("students", students);
		
		return "students/index";
	}
	
	@GetMapping("/create")
	public String createStudent(Model model) {
		StudentDto studentDto = new StudentDto();
		model.addAttribute("studentDto", studentDto);
		
		return "students/create";
	}
	
	@PostMapping("/create")
	public String createStudent(
			@Valid @ModelAttribute StudentDto studentDto,
			BindingResult result
			) {
		
		if (result.hasErrors()) {
			return "students/create";
		}
		
		Student student = new Student();
		student.setFirstName(studentDto.getFirstName());
		student.setLastName(studentDto.getLastName());
	    student.setDoB(java.sql.Date.valueOf(studentDto.getDoB()));
		
		studentRepo.save(student);
		
		return "redirect:/students";
	}
	
	@GetMapping("/edit")
	public String editStudent(Model model, @RequestParam int id) {
	    Student student = studentRepo.findById(id);
	    if (student == null) {
	        return "redirect:/students";
	    }
	    
	    StudentDto studentDto = new StudentDto();
	    studentDto.setFirstName(student.getFirstName());
	    studentDto.setLastName(student.getLastName());
	    
	    if (student.getDoB() != null) {
	        studentDto.setDoB(student.getDoB().toString());  
	    }

	    model.addAttribute("student", student);
	    model.addAttribute("studentDto", studentDto);

	    return "students/edit";
	}
	
	@PostMapping("/edit")
	public String editStudent(
	        Model model,
	        @RequestParam int id,
	        @Valid @ModelAttribute StudentDto studentDto,
	        BindingResult result
	        ) {
	    
	    Student student = studentRepo.findById(id);
	    if(student == null) {
	        return "redirect:/students";
	    }
	    
	    model.addAttribute("student", student);
	    
	    if (result.hasErrors()) {
	        return "students/edit";
	    }
	    
	    student.setFirstName(studentDto.getFirstName());
	    student.setLastName(studentDto.getLastName());
	    student.setDoB(java.sql.Date.valueOf(studentDto.getDoB()));
	    
	    studentRepo.save(student);
	    
	    return "redirect:/students";
	}
	
	@GetMapping("/delete")
	public String deleteStudent(@RequestParam int id) {
		Student student = studentRepo.findById(id);
		
		if (student != null) {
			studentRepo.delete(student);
		}
		
		return "redirect:/students";
	}

}
