package com.interview.sfour.controller;

import com.interview.sfour.model.Course;
import com.interview.sfour.model.Student;
import com.interview.sfour.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.service.ResponseMessage;

import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {

    @Autowired
    private StudentService service;

    @GetMapping("/students")
    public List<Student> read(
            @RequestParam Optional<Integer> id,
            @RequestParam Optional<String> firstName,
            @RequestParam Optional<String> lastName
            ) {
        if(!id.isPresent() && !firstName.isPresent() && !lastName.isPresent()) {
            return this.service.read();
        }
        return this.service.readByFields(id, firstName, lastName);
    }

    @GetMapping("/students/{id}")
    public Student readById(@PathVariable Integer id) {
        return this.service.readById(id);
    }

    @PostMapping("/students")
    public Student create(@RequestBody Student student) {
        return this.service.create(student);
    }

    @PutMapping("/students/{id}")
    public Student update(@RequestBody Student student, @PathVariable int id) {
        return this.service.update(student, id);
    }

    @DeleteMapping("/students/{id}")
    public void delete(@PathVariable int id) {
        this.service.delete(id);
    }

    @GetMapping("/students/{id}/courses")
    public List<Course> readRelatedClassesById(@PathVariable int id) {
        return this.service.readRelatedClassesById(id);
    }

    @PostMapping("/students/{id}/courses/{code}")
    public ResponseMessage enrollStudent(@PathVariable Integer id, @PathVariable Integer code) {
        this.service.enrollStudent(code, id);
        return new ResponseMessage(HttpStatus.CREATED.value(), "Succesfully student enrollment", null, null, null);
    }
}
