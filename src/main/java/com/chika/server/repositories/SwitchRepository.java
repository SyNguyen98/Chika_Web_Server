package com.chika.server.repositories;

import com.chika.server.models.house.Switch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Sy Nguyen
 * @version 1.0
 * @since 27-09-2019
 */
@Repository
public interface SwitchRepository extends JpaRepository<Switch, String> {

    List<Switch> findAllByUserId(Long userId);
}
