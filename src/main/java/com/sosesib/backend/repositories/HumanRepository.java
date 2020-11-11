package com.sosesib.backend.repositories;

import com.sosesib.backend.models.Human;
import org.springframework.data.repository.CrudRepository;

public interface HumanRepository  extends CrudRepository<Human,String> {

}
