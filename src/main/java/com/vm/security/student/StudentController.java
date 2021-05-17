package com.vm.security.student;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vm.security.student.model.Student;

@RestController
@RequestMapping("/vm/students")
public class StudentController {

	private static final List<Student> STUDENTS = Arrays.asList(new Student(1, "Jack"), new Student(2, "Rose"),
			new Student(3, "Annie"));

	@GetMapping("/{studentId}")
	public Student getStudent(@PathVariable("studentId") Integer studentId) {
		return STUDENTS.stream().filter(student -> studentId.equals(student.getId())).findFirst().orElseThrow(()->new IllegalStateException("Student :"+studentId+" not found"));
	}

}
