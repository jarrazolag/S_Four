package com.interview.sfour.controller;

import com.interview.sfour.model.Classroom;
import com.interview.sfour.model.Student;
import com.interview.sfour.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClassController {

    @Autowired
    private ClassService service;

    @GetMapping("/classes")
    public List<Classroom> read() {
            return this.service.read();
    }

    @PostMapping("/classes")
    public Classroom create(@RequestBody Classroom classroom) {
        return this.service.create(classroom);
    }

    @PutMapping("/classes/{id}")
    public Classroom update(@RequestBody Classroom classroom, @PathVariable int id){
        return this.service.update(classroom, id);
    }

    @DeleteMapping("/classes/{id}")
    public void delete(@PathVariable int id) {
        this.service.delete(id);
    }

    @GetMapping("/classes/{id}/students")
    public List<Student> readRelatedStudentsById(@PathVariable int id) {
        return this.service.readRelatedStudentsById(id);
    }
}
