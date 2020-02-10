package com.interview.sfour.service;

import com.interview.sfour.model.Classroom;
import com.interview.sfour.model.Student;

import java.util.List;

public interface StudentService {

    List<Student> read();

    Student create(Student student);

    Student update(Student student, int id);

    void delete(int id);

    List<Classroom> readRelatedClassesById(int id);
}
