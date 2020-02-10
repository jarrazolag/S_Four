package com.interview.sfour;

import com.interview.sfour.model.Classroom;
import com.interview.sfour.model.Student;
import com.interview.sfour.repository.ClassRepository;
import com.interview.sfour.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class SfourApplication {

	public static void main(String[] args) {
		SpringApplication.run(SfourApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(StudentRepository repository, ClassRepository classRepository) {
		return (args) -> {
			// save students
			Student student1 = new Student("John", "Johnson");
			repository.save(new Student("Steve", "Stevens"));
			repository.save(new Student("Mary", "Robinson"));
			repository.save(new Student("Kate", "Keystone"));
			repository.save(new Student("Diana", "Doll"));

			Classroom course1 = new Classroom("Programming Java", "");
			Classroom course2 = new Classroom("Spring Boot basics", "");
			classRepository.save(course1);
			classRepository.save(course2);
			classRepository.save(new Classroom("Marketing 1", ""));
			classRepository.save(new Classroom("Marketing 2", ""));

			Set<Classroom> courses = new HashSet<Classroom>();
			courses.add(course1);
			courses.add(course2);

			student1.setClasses(courses);
			repository.save(student1);
		};
	}

}
