package com.chika.server.repositories.house;

import com.chika.server.models.house.IrData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IrDataRepository extends JpaRepository<IrData, String> {

}
