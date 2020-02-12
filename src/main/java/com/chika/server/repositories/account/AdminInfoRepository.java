package com.chika.server.repositories.account;

import com.chika.server.models.user.AdminInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminInfoRepository extends JpaRepository<AdminInfo, Long> {

    Optional<AdminInfo> findByUserId(Long userId);
}
