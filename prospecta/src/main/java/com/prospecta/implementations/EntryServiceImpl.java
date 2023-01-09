package com.prospecta.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prospecta.exceptions.EntryException;
import com.prospecta.models.Entry;
import com.prospecta.repositories.EntryRepo;
import com.prospecta.services.EntryService;

@Service
public class EntryServiceImpl implements EntryService {

	@Autowired
	private EntryRepo entryRepo;

	@Override
	public void saveEntry(Entry entry) throws EntryException {

		Entry existedEntry = entryRepo.findByApi(entry.getApi());
		//check for if same entry is with same api name is exist or not.
		if (existedEntry != null) {
			throw new EntryException("Entry with same Api title already Exists..!");
		} else {
			entryRepo.save(entry);
		}

	}

	@Override
	public List<Entry> getAllEntries() {

		List<Entry> entries = entryRepo.findAll();

		return entries;
	}

}
