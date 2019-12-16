package com.chika.server.repositories.house;

import com.chika.server.models.script.ScriptDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScriptDeviceRepository extends JpaRepository<ScriptDevice, Long> {

    List<ScriptDevice> findAllByScriptId(Long scriptId);
}
