package com.simplyrojgar.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.simplyrojgar.entity.Languages;

@Repository
public interface LanguagesRepository extends CrudRepository<Languages,Long>{

}
