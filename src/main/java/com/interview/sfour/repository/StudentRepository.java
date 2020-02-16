package com.interview.sfour.repository;

import com.interview.sfour.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends CrudRepository<Student, Integer> {
    List<Student> findByIdOrFirstNameOrLastName(Optional<Integer> id, Optional<String> firstName, Optional<String> lastName);
}

