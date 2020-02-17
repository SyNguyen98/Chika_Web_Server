package com.chika.server.repositories.device;

import com.chika.server.models.product.Switch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Sy Nguyen
 * @version 1.0
 * @since 23-11-2019
 */
@Repository
public interface SwitchRepository extends JpaRepository<Switch, String> {

}
