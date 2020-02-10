package com.interview.sfour.service;

import com.interview.sfour.model.Classroom;
import com.interview.sfour.model.Student;

import java.util.List;

public interface ClassService {

    List<Classroom> read();

    Classroom create(Classroom classObj);

    Classroom update(Classroom classObj, int id);

    void delete(int id);

    List<Student> readRelatedStudentsById(int id);
}
