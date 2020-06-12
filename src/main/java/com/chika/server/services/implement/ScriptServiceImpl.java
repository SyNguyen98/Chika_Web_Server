package com.chika.server.services.implement;

import com.chika.server.models.house.Script;
import com.chika.server.models.house.ScriptDevice;
import com.chika.server.repositories.house.ScriptDeviceRepository;
import com.chika.server.repositories.house.ScriptRepository;
import com.chika.server.services.ScriptService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScriptServiceImpl implements ScriptService {

    private final ScriptRepository scriptRepository;

    private final ScriptDeviceRepository scriptDeviceRepository;

    public ScriptServiceImpl(ScriptRepository scriptRepository, ScriptDeviceRepository scriptDeviceRepository) {
        this.scriptRepository = scriptRepository;
        this.scriptDeviceRepository = scriptDeviceRepository;
    }

    @Override
    public List<Script> getAllByUserId(Long userId) {
        return scriptRepository.findAllByUserId(userId);
    }

    @Override
    @Transactional
    public Script save(Script script) {
        String scriptId = scriptRepository.save(script).getId();
        script.setId(scriptId);
        script.setDevices(script.getDevices().stream()
                .peek(scriptDevice -> {
                    scriptDevice.setScriptId(scriptId);
                    scriptDeviceRepository.save(scriptDevice);
                })
                .collect(Collectors.toList())
        );
        return script;
    }

    @Override
    public void deleteById(String id) {
        scriptDeviceRepository.deleteAllByScriptId(id);
        scriptRepository.deleteById(id);
    }
}
