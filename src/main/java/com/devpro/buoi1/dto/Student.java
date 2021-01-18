package com.devpro.buoi1.dto;

public class Student {
	private String name;
	private int age;
	private String address;
	private float grade;

	public Student(String name, int age, String address, float grade) {
		super();
		this.name = name;
		this.age = age;
		this.address = address;
		this.grade = grade;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public float getGrade() {
		return grade;
	}

	public void setGrade(float grade) {
		this.grade = grade;
	}

}
