package com.aadhya.cultivateart.repository;

import com.aadhya.cultivateart.dao.SchoolDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<SchoolDO, Integer> {
}
