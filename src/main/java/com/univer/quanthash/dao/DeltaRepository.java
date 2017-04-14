package com.univer.quanthash.dao;

import com.univer.quanthash.models.DeltaModel;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

/**
 * Created by Vladislav on 14-Apr-17.
 */

public interface DeltaRepository extends CrudRepository<DeltaModel, Long> {
}
