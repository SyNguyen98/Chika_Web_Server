package com.chika.server.repositories.house;

import com.chika.server.models.house.IrValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IrValueRepository extends JpaRepository<IrValue, String> {

    IrValue findByDeviceAndProtocol(String device, String protocol);
}
