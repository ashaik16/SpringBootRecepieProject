package com.recepies.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.recepies.demo.model.Recepie;

@Repository
public interface RecepieRepository extends CrudRepository<Recepie, Long> {
	Recepie findByName(String name);
}
