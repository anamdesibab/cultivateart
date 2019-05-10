package com.aadhya.cultivateart.repository;

import com.aadhya.cultivateart.dao.EventDO;
import com.aadhya.cultivateart.dao.SchoolDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<EventDO, Integer> {
}
