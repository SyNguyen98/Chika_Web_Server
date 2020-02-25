package com.chika.server.repositories.product;

import com.chika.server.models.product.ButtonWifi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Sy Nguyen
 * @version 1.0
 * @since 25-02-2020
 */
@Repository
public interface ButtonWifiRepository extends JpaRepository<ButtonWifi, String> {

    List<ButtonWifi> findAllByRoomId(String roomId);

    List<ButtonWifi> findAllBySwitchWifiId(String switchId);
}
