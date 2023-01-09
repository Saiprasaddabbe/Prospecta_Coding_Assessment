package com.prospecta.services;

import java.util.List;

import com.prospecta.exceptions.EntryException;
import com.prospecta.models.Entry;

public interface EntryService {

	
	public void saveEntry(Entry entry)throws EntryException;
	public List<Entry> getAllEntries();
	
	
	
}
