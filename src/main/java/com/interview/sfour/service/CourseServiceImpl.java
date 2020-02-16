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
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository repository;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Course> read() {
        return (List<Course>) this.repository.findAll();
    }

    @Override
    public Course create(Course course) {
        course.setCode(null);
        try {
            return this.repository.save(course);
        } catch (DataIntegrityViolationException ex) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Course Already Exist");
        }
    }

    @Override
    public List<Course> readByFields(
            Optional<Integer> code, Optional<String> title, Optional<String> description) {

        return this.repository.findByCodeOrTitleOrDescription(code, title, description);
    }

    @Override
    public Course update(Course newCourse, Integer code) {
        if (!this.repository.findById(code).isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Course Not Found");
        }

        try {
            newCourse.setCode(code);
            return this.repository.save(newCourse);
        } catch (DataIntegrityViolationException ex) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Course Already Exist");
        }
    }

    @Override
    public void delete(Integer code) {
        if (this.repository.findById(code).isPresent()) {
            this.repository.deleteById(code);
        }

        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Course Not Found");
    }

    @Override
    public List<Student> readRelatedStudentsById(Integer code) {
        return new ArrayList<>(this.repository.findById(code).get().getStudents());

    }

    @Override
    public Course readById(Integer code) {
        if (!this.repository.findById(code).isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Course Not Found");
        }
        return this.repository.findById(code).get();
    }
}
