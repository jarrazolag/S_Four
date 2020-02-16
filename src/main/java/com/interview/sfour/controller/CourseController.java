package com.interview.sfour.controller;

import com.interview.sfour.model.Course;
import com.interview.sfour.model.Student;
import com.interview.sfour.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class CourseController {

    @Autowired
    private CourseService service;

    @GetMapping("/courses")
    public List<Course> read(
            @RequestParam Optional<Integer> code,
            @RequestParam Optional<String> title,
            @RequestParam Optional<String> description) {
        if (!code.isPresent() && !title.isPresent() && !description.isPresent()) {
            return this.service.read();
        }
        return this.service.readByFields(code, title, description);
    }

    @GetMapping("/courses/{code}")
    public Course readById(@PathVariable Integer code) {
        return this.service.readById(code);
    }

    @PostMapping("/courses")
    public Course create(@RequestBody Course course) {
        return this.service.create(course);
    }

    @PutMapping("/courses/{code}")
    public Course update(@RequestBody Course course, @PathVariable Integer code) {
        return this.service.update(course, code);
    }

    @DeleteMapping("/courses/{code}")
    public void delete(@PathVariable Integer code) {
        this.service.delete(code);
    }

    @GetMapping("/courses/{code}/students")
    public List<Student> readRelatedStudentsById(@PathVariable Integer code) {
        return this.service.readRelatedStudentsById(code);
    }
}
