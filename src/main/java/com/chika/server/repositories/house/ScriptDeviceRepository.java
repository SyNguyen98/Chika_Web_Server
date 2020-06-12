package com.chika.server.repositories.house;

import com.chika.server.models.house.ScriptDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScriptDeviceRepository extends JpaRepository<ScriptDevice, String> {

    List<ScriptDevice> findAllByScriptId(String scriptId);

    void deleteAllByScriptId(String scriptId);
}
