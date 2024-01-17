package com.kgisl.CourseManagement.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kgisl.CourseManagement.entity.Student;
@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

}
