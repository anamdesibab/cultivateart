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

    @Query(value="select * from STUDENT_INFO \n" +
            "\tleft join STUDENT_EVENTS  ON STUDENT_INFO.ID = STUDENT_EVENTS.STUDENT_ID\n" +
            "\t\tleft join EVENT_IMAGES on EVENT_IMAGES.STUDENT_EVENT_ID = STUDENT_EVENTS.ID\n" +
            "where STUDENT_INFO.id = ?", nativeQuery = true)
    StudentDO findStudentById(int studentId);

    @Query(value="from StudentDO student \n" +
            "\tleft join fetch student.events events\n" +
            "\t\tleft join fetch events.imageSet images\n" +
            "where student.schoolId = ?1")
    List<StudentDO> findStudentsBySchoolId(int schoolId);

    @Query( value="select * from STUDENT_INFO si where si.name like CONCAT('%',:searchString,'%')" +
            "or PARENT_NAME like CONCAT('%',:searchString,'%') or EMAIL_ID like CONCAT('%',:searchString,'%')\n" +
            "or PHONE like CONCAT('%',:searchString,'%') or PHONE2 like CONCAT('%',:searchString,'%')", nativeQuery = true)
    List<StudentDO> findBySearchString(@Param("searchString")String searchString);
}
