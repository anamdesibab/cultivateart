package com.aadhya.cultivateart.repository;

import com.aadhya.cultivateart.dao.ImageSetDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageSetRepository extends JpaRepository<ImageSetDO, Integer> {

    @Query( value="select * from EVENT_IMAGES where STUDENT_EVENT_ID in (?1)", nativeQuery = true)
    List<ImageSetDO> findImageSetBy(int studentEventId);
}
