package com.interview.sfour.service;

import com.interview.sfour.model.Course;
import com.interview.sfour.model.Student;
import com.interview.sfour.repository.CourseRepository;
import com.interview.sfour.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository repository;

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<Student> read() {
        return (List<Student>) this.repository.findAll();
    }

    @Override
    public Student create(Student student) {
        student.setId(null);
        try {
            return this.repository.save(student);
        } catch (DataIntegrityViolationException ex) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Course Already Exist");
        }
    }

    @Override
    public Student update(Student newStudent, Integer id) {
        if (!this.repository.findById(id).isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Student Not Found");
        }

        try {
            newStudent.setId(id);
            return this.repository.save(newStudent);
        } catch (DataIntegrityViolationException ex) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Student Already Exist");
        }
    }

    @Override
    public void delete(Integer id) {
        this.repository.deleteById(id);
    }

    @Override
    public List<Course> readRelatedClassesById(Integer id) {
        return new ArrayList<>(this.repository.findById(id).get().getCourses());
    }

    @Override
    public void enrollStudent(Integer code, Integer id) {
        if (!this.repository.findById(id).isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Student Not Found");
        }

        if (!this.courseRepository.findById(code).isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Course Not Found");
        }

        Student student = this.repository.findById(id).get();
        Course course = this.courseRepository.findById(code).get();
        student.addCourse(course);
        this.repository.save(student);
    }

    @Override
    public Student readById(Integer id) {
        if (!this.repository.findById(id).isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Student Not Found");
        }
        return this.repository.findById(id).get();
    }

    @Override
    public List<Student> readByFields(
            Optional<Integer> id, Optional<String> firstName, Optional<String> lastName) {
        return this.repository.findByIdOrFirstNameOrLastName(id, firstName, lastName);
    }
}
