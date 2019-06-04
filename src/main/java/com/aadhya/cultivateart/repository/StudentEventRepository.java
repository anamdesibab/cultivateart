package com.aadhya.cultivateart.repository;

import com.aadhya.cultivateart.dao.StudentEventDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentEventRepository extends JpaRepository<StudentEventDO, Integer> {

    @Query( value="select * from STUDENT_EVENTS where STUDENT_ID in (?1)", nativeQuery = true)
    List<StudentEventDO> findEventsBy(int studentId);

}
