package com.chika.server.repositories.house;

import com.chika.server.models.house.IrValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IrValueRepository extends JpaRepository<IrValue, String> {

    List<IrValue> findAllByRemoteIrId(String remoteIrId);

    void deleteAllByRemoteIrId(String remoteIrId);
}
