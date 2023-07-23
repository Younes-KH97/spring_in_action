package com.taco_cloud.data;

import com.taco_cloud.domain.Taco;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TacoRepository extends PagingAndSortingRepository<Taco, Long> {
    // There is a method here ... ch9
}
