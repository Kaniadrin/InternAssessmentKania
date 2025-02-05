package com.kania.studentdata.models;

import jakarta.validation.constraints.*;

public class StudentDto {
	
	@NotEmpty(message = "The First Name is required")
	private String firstName;
	
	@NotEmpty(message = "The Last Name is required")
	private String lastName;
	
	@NotEmpty(message = "The DoB is required")
	private String DoB;
	
	private int NIM;

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

	public String getDoB() {
		return DoB;
	}

	public void setDoB(String doB) {
		DoB = doB;
	}

	public int getNIM() {
        return NIM;
    }

    public void setNIM(int NIM) {
        this.NIM = NIM;
    }


}
