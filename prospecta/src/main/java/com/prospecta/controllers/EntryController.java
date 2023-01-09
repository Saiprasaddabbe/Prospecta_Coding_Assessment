package com.prospecta.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.prospecta.exceptions.EntryException;
import com.prospecta.models.Data;
import com.prospecta.models.Entry;
import com.prospecta.models.EntryDto;
import com.prospecta.services.EntryService;

@RestController
public class EntryController {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private EntryService entryService;

	@GetMapping("/entries/{category}")
	public ResponseEntity<List<EntryDto>> getEntryHandler(@PathVariable("category") String category) throws EntryException{

		String url = "https://api.publicapis.org/entries";
		Data data = restTemplate.getForObject(url, Data.class);
		
		//getting all categories & filter based on the category passed as an input parameter.
		//this is from given url
		List<EntryDto> webEntries = data.getEntries().stream().filter(entry->entry.getCategory().equals(category))
		                          .map(entry->new EntryDto(entry.getApi(), entry.getDescription()))
		                          .toList();
		
		
		//getting all categories & filter based on the category passed as an input parameter.
	    //this is from local database (Saved Entries)
       List<EntryDto> localEntries = entryService.getAllEntries().stream()
                             .filter(entry->entry.getCategory().equals(category))
                             .map(entry->new EntryDto(entry.getApi(), entry.getDescription()))
                             .toList();
       
       //merging both entries in one list (entries from url & entries from local database)
       ArrayList<EntryDto> allEntries = new ArrayList<>();
       allEntries.addAll(localEntries);
       allEntries.addAll(webEntries);
       
       //Will throw exception if list of entries is empty or category is invalid
       if(allEntries.size()==0) {
    	   throw new EntryException("List is empty..!");
       }
 
		return new ResponseEntity<List<EntryDto>>(allEntries,HttpStatus.OK);
		
	}

	@PostMapping("/entries")
	public ResponseEntity<String> saveEntryHandler(@RequestBody Entry entry) throws EntryException {
		entryService.saveEntry(entry);
		return new ResponseEntity<String>("Entry Saved..!", HttpStatus.CREATED);
	}

}
