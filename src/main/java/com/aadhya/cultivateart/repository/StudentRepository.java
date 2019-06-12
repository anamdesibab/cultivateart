package com.aadhya.cultivateart.repository;

import com.aadhya.cultivateart.dao.SchoolDO;
import com.aadhya.cultivateart.dao.StudentDO;
import com.aadhya.cultivateart.dao.StudentEventDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface StudentRepository extends JpaRepository<StudentDO, Integer> {

    @Query( value="select * from STUDENT_INFO where SCHOOL_ID = ?1", nativeQuery = true)
    List<StudentDO>  findStudentBySchoolId(int schoolId);

    @Query( value="select * from STUDENT_INFO si where si.name like CONCAT('%',:searchString,'%')" +
            "or PARENT_NAME like CONCAT('%',:searchString,'%') or EMAIL_ID like CONCAT('%',:searchString,'%')\n" +
            "or PHONE like CONCAT('%',:searchString,'%') or PHONE2 like CONCAT('%',:searchString,'%')", nativeQuery = true)
    List<StudentDO> findBySearchString(@Param("searchString")String searchString);
}
