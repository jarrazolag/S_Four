package com.interview.sfour.controller;

import com.interview.sfour.model.Classroom;
import com.interview.sfour.model.Student;
import com.interview.sfour.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentService service;

    @GetMapping("/students")
    public List<Student> read() {
            return this.service.read();
    }

    @PostMapping("/students")
    public Student create(@RequestBody Student student) {
        return this.service.create(student);
    }

    @PutMapping("/students/{id}")
    public Student update(@RequestBody Student student, @PathVariable int id){
        return this.service.update(student, id);
    }

    @DeleteMapping("/students/{id}")
    public void delete(@PathVariable int id) {
        this.service.delete(id);
    }

    @GetMapping("/students/{id}/classes")
    public List<Classroom> readRelatedClassesById(@PathVariable int id) {
        return this.service.readRelatedClassesById(id);
    }
}
