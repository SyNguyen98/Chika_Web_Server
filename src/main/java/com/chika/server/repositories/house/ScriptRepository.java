package com.chika.server.repositories.house;

import com.chika.server.models.house.Script;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScriptRepository extends JpaRepository<Script, Long> {

    Optional<Script> findByName(String name);
}
