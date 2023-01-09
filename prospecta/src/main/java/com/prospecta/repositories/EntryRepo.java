package com.prospecta.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prospecta.models.Entry;
@Repository
public interface EntryRepo extends JpaRepository<Entry, Integer>{

	public Entry findByApi(String api);
	
}
