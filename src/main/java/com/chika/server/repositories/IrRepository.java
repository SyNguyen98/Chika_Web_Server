package com.chika.server.repositories;

import com.chika.server.models.house.IR;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IrRepository extends JpaRepository<IR, String> {

    List<IR> findAllByUserId(Long userId);

    void deleteAllByUserId(Long userId);
}
