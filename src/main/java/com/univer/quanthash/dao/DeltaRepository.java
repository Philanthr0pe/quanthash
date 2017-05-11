package com.univer.quanthash.dao;

import com.univer.quanthash.models.DeltaModel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Vladislav on 14-Apr-17.
 */


public interface DeltaRepository extends JpaRepository<DeltaModel, Long> {
}
