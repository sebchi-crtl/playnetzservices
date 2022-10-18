package com.playnetz.purchase.repository;

import com.playnetz.purchase.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Long> {
}
