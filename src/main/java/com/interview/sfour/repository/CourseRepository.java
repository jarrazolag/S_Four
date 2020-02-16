package com.interview.sfour.repository;

import com.interview.sfour.model.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends CrudRepository<Course, Integer> {

    List<Course> findByCodeOrTitleOrDescription(
            Optional<Integer> code,
            Optional<String> title,
            Optional<String> description);
}
