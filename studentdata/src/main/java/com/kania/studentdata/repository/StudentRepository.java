package com.kania.studentdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kania.studentdata.models.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
	
	public Student findById(int NIM);

}
