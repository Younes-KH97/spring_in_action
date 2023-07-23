package com.taco_cloud.data;

import com.taco_cloud.domain.Taco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TacoRepository extends JpaRepository<Taco, Long> {
    // There is a method here ... ch9
}
