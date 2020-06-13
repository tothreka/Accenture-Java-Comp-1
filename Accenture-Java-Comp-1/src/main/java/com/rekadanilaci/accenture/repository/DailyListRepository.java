package com.rekadanilaci.accenture.repository;

import com.rekadanilaci.accenture.domain.DailyList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyListRepository extends JpaRepository<DailyList, Long> {
}
