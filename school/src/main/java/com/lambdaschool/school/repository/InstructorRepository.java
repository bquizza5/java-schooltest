package com.lambdaschool.school.repository;

import com.lambdaschool.school.model.Course;
import com.lambdaschool.school.model.Instructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.lang.reflect.Array;
import java.util.ArrayList;

public interface InstructorRepository extends CrudRepository<Instructor, Long>
{
    ArrayList<Instructor> findInstructorsByInstructnameEquals (String name);

    @Query(value = "SELECT * FROM instructor WHERE instructid = :instructid", nativeQuery = true)
    Instructor findInstructorById(long instructid);
}
