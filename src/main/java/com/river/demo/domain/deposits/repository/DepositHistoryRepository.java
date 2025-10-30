package com.river.demo.domain.deposits.repository;

import com.river.demo.domain.deposits.model.entity.DepositHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepositHistoryRepository extends JpaRepository<DepositHistory, Long> {



}
