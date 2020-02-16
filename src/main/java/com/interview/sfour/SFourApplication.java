package com.interview.sfour;

import com.interview.sfour.model.Course;
import com.interview.sfour.model.Student;
import com.interview.sfour.repository.CourseRepository;
import com.interview.sfour.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class SFourApplication {

    public static void main(String[] args) {
        SpringApplication.run(SFourApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(StudentRepository repository, CourseRepository courseRepository) {
        return (args) -> {
            // save students
            Student student1 = new Student("John", "Johnson");
            repository.save(new Student("Steve", "Stevens"));
            repository.save(new Student("Mary", "Robinson"));
            repository.save(new Student("Kate", "Keystone"));
            repository.save(new Student("Diana", "Doll"));

            Course course1 = new Course("Programming Java", "");
            Course course2 = new Course("Spring Boot basics", "");
            courseRepository.save(course1);
            courseRepository.save(course2);
            courseRepository.save(new Course("Marketing 1", ""));
            courseRepository.save(new Course("Marketing 2", ""));

            Set<Course> courses = new HashSet<Course>();
            courses.add(course1);
            courses.add(course2);

            student1.setCourses(courses);
            repository.save(student1);
        };
    }

}
