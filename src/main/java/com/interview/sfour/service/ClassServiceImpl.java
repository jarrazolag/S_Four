package com.interview.sfour.service;

import com.interview.sfour.model.Classroom;
import com.interview.sfour.model.Student;
import com.interview.sfour.repository.ClassRepository;
import com.interview.sfour.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ClassServiceImpl implements ClassService {

    @Autowired
    private ClassRepository repository;

    @Override
    public List<Classroom> read() {
        return (List<Classroom>) this.repository.findAll();
    }

    @Override
    public Classroom create(Classroom classObj) {
        return this.repository.save(classObj);
    }

    @Override
    public Classroom update(Classroom newClass, int id) {
        return this.repository.findById(id)
                              .map(student -> {
                                  student.setTitle(newClass.getTitle());
                                  student.setDescription(newClass.getDescription());
                                  return repository.save(student);
                              })
                              .orElseGet(() -> {
                                  newClass.setCode(id);
                                  return repository.save(newClass);
                              });
    }

    @Override
    public void delete(int id) {
        this.repository.deleteById(id);
    }

    @Override
    public List<Student> readRelatedStudentsById(int id) {
        return new ArrayList<>(this.repository.findById(id).get().getStudents());

    }
}
