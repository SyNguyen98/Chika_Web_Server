package com.chika.server.repositories.product;

import com.chika.server.models.product.ButtonRf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Sy Nguyen
 * @version 1.0
 * @since 25-02-2020
 */
@Repository
public interface ButtonRfRepository extends JpaRepository<ButtonRf, String> {

    List<ButtonRf> findAllByRoomId(String roomId);

    List<ButtonRf> findAllBySwitchRfId(String switchId);
}
