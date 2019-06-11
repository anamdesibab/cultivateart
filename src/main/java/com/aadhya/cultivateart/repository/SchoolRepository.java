package com.aadhya.cultivateart.repository;

import com.aadhya.cultivateart.dao.SchoolDO;
import com.aadhya.cultivateart.dao.StudentEventDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SchoolRepository extends JpaRepository<SchoolDO, Integer> {

    @Query( value="select * from SCHOOL_INFO si where si.name like CONCAT('%',:searchString,'%')" +
            "or PROFILE like CONCAT('%',:searchString,'%') or ADDRESS like CONCAT('%',:searchString,'%')\n" +
            "or WEBSITE like CONCAT('%',:searchString,'%') or PHONE1 like CONCAT('%',:searchString,'%')", nativeQuery = true)
    List<SchoolDO> findBySearchString(@Param("searchString")String searchString);
}
