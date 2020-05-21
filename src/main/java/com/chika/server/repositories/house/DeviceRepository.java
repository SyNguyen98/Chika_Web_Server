package com.chika.server.repositories.house;

import com.chika.server.models.house.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Sy Nguyen
 * @version 1.0
 * @since 14-05-2020
 */
@Repository
public interface DeviceRepository extends JpaRepository<Device, String> {

    List<Device> findAllByRoomIdOrderByCreatedAt(String roomId);

    List<Device> findAllByTopicContains(String topic);

    @Query(value = "select device.topic from Device device")
    List<Object[]> getAllTopic();
}
