package com.interview.sfour.repository;

import com.interview.sfour.model.Classroom;
import com.interview.sfour.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRepository extends CrudRepository<Classroom, Integer> {
}
