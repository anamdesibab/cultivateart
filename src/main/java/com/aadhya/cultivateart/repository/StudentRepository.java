package com.aadhya.cultivateart.repository;

import com.aadhya.cultivateart.dao.SchoolDO;
import com.aadhya.cultivateart.dao.StudentDO;
import com.aadhya.cultivateart.dao.StudentEventDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<StudentDO, Integer> {

    @Query( value="select * from STUDENT_INFO where SCHOOL_ID = ?1", nativeQuery = true)
    List<StudentDO>  findStudentBySchoolId(int schoolId);
}
