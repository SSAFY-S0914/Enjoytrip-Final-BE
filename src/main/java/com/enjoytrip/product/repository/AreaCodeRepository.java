package com.enjoytrip.product.repository;

import com.enjoytrip.product.entity.AreaCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaCodeRepository extends JpaRepository<AreaCode, Long> {
}
