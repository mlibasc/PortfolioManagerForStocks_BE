package com.api.stocks.repository;

import com.api.stocks.entity.FXSpot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FXSpotRepository extends JpaRepository<FXSpot, Long> {

}
