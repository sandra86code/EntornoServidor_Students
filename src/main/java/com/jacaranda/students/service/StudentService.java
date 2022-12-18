package com.jacaranda.students.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jacaranda.students.model.Student;


@Service
public class StudentService {
	
	private List<Student> lista;

	public StudentService() {
		lista = new ArrayList<>();
		lista.add(new Student ("Inma", "Olias", 25));
		lista.add(new Student ("Sandra", "Ruiz", 35));
		lista.add(new Student ("Pepe", "Perez", 75));
		lista.add(new Student ("Maria", "Fernandez", 51));
	}
	
	
	public List<Student> getLista() {
		return lista;
	}



	public boolean addStudent(Student e) {
		return lista.add(e);
	}

	public boolean removeStudent(Student o) {
		return lista.remove(o);
	}

	public Student updateStudent(String name, String surname, int age) {
		boolean encontrado = false;
		Student resultado = null;
		Iterator<Student> iterator = this.lista.iterator();
		while(iterator.hasNext() && !encontrado) {
			resultado = iterator.next();
			if(resultado.getName().equals(name) && resultado.getSurname().equals(surname)) {
				resultado.setAge(age);
				encontrado = true;
			}
		}
		return resultado;
	}

	public Student getStudent(String name, String surname) {
		boolean encontrado = false;
		Student resultado = null;
		Iterator<Student> iterator = this.lista.iterator();
		while(iterator.hasNext() && !encontrado) {
			resultado = iterator.next();
			if(resultado.getName().equals(name) && resultado.getSurname().equals(surname)) {
				encontrado = true;
			}
		}
		return resultado;
	}
	
	
	
}
