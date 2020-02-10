package com.interview.sfour.service;

import com.interview.sfour.model.Classroom;
import com.interview.sfour.model.Student;
import com.interview.sfour.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository repository;

    @Override
    public List<Student> read() {
        return (List<Student>) this.repository.findAll();
    }

    @Override
    public Student create(Student student) {
        return this.repository.save(student);
    }

    @Override
    public Student update(Student newStudent, int id) {
        return this.repository.findById(id)
                              .map(student -> {
                                  student.setFirstName(newStudent.getFirstName());
                                  student.setLastName(newStudent.getLastName());
                                  return repository.save(student);
                              })
                              .orElseGet(() -> {
                                  newStudent.setId(id);
                                  return repository.save(newStudent);
                              });
    }

    @Override
    public void delete(int id) {
        this.repository.deleteById(id);
    }

    @Override
    public List<Classroom> readRelatedClassesById(int id) {
        return new ArrayList<>(this.repository.findById(id).get().getClasses());
    }
}
