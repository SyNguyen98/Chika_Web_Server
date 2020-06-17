package com.chika.server.services.implement;

import com.chika.server.exception.ResourceNotFoundException;
import com.chika.server.models.house.Script;
import com.chika.server.models.house.ScriptDevice;
import com.chika.server.repositories.house.ScriptDeviceRepository;
import com.chika.server.repositories.house.ScriptRepository;
import com.chika.server.services.ScheduleService;
import com.chika.server.services.ScriptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScriptServiceImpl implements ScriptService {

    private final ScriptRepository scriptRepository;

    private final ScriptDeviceRepository scriptDeviceRepository;

    private final ScheduleService scheduleService;

    public ScriptServiceImpl(ScriptRepository scriptRepository, ScriptDeviceRepository scriptDeviceRepository, ScheduleService scheduleService) {
        this.scriptRepository = scriptRepository;
        this.scriptDeviceRepository = scriptDeviceRepository;
        this.scheduleService = scheduleService;
    }

    @Override
    public List<Script> getAllByUserId(Long userId) {
        return scriptRepository.findAllByUserId(userId);
    }

    @Override
    public Script getById(String id) {
        return scriptRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Script", "id", id));
    }

    @Override
    @Transactional
    public Script save(Script script) {
        scheduleService.initialize(script);

        Script newScript = scriptRepository.save(new Script(script.getLogo(), script.getName(),
                script.getTime(), script.getDays(), script.getUserId()));
        String scriptId = newScript.getId();
        script.getDevices().forEach(device -> {
            ScriptDevice scriptDevice = new ScriptDevice();
            scriptDevice.setDeviceId(device.getId());
            scriptDevice.setName(device.getName());
            scriptDevice.setType(device.getType());
            scriptDevice.setTopic(device.getTopic());
            scriptDevice.setState(device.getState());
            scriptDevice.setSwitchButton(device.getSwitchButton());
            scriptDevice.setScriptId(scriptId);
            scriptDeviceRepository.save(scriptDevice);
        });
        return getById(scriptId);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        scheduleService.cancel(id);
        scriptDeviceRepository.deleteAllByScriptId(id);
        scriptRepository.deleteById(id);
    }
}
