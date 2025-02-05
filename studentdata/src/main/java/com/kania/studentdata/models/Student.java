package com.kania.studentdata.models;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int NIM;
	
	private String firstName;
	private String lastName;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date DoB;
	
	public int getAge() {
        if (this.DoB == null) {
            return 0;
        }

        LocalDate birthDate = this.DoB.toLocalDate(); 
        LocalDate currentDate = LocalDate.now();

        return Period.between(birthDate, currentDate).getYears(); 
    }

	public int getNIM() {
		return NIM;
	}

	public void setNIM(int nIM) {
		NIM = nIM;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDoB() {
		return DoB;
	}

	public void setDoB(Date doB) {
		DoB = doB;
	}
	

}
