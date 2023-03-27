package com.assessment.stockapi.repositories;

import com.assessment.stockapi.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByUserIdAndStockId(long userId, long stockId);
}
