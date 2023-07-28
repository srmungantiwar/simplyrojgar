package com.simplyrojgar.controller;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simplyrojgar.entity.Language;
import com.simplyrojgar.service.LanguagesService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/languages")
public class LanguageController {
	
	@Autowired
	private LanguagesService languagesService;

	@GetMapping("/all")
	public Set<Language> getLanguages() {
		log.info("Started getting langauages");
		Set<Language> allLanguages = new LinkedHashSet<>();
		allLanguages = languagesService.getLanguages();
		log.info("Retrieved langauages Successfully!");
		return allLanguages;
	}
	
	@GetMapping("/id/{id}")
	public Language getLanguageById(@PathVariable Long id) {
		log.info("Started getting langauage for Id :{}",id);
		Language language =  languagesService.getLanguageById(id);
		return language;
	}
	
	@PostMapping("/{language}")
	public void addLanguage(@PathVariable String language) {
		log.info("Started adding language :{}", language);
		languagesService.addLanguage(language);
		log.info("Completed adding language");
	}
	
	@PutMapping("/{language}/id/{id}")
	public void updateLanguage(@PathVariable String language) {
		log.info("Started updating language :{}", language);
		
	}
	
	@DeleteMapping("/id/{id}")
	public void removeLanguage(@PathVariable Long id) {
		languagesService.removeLanguageById(id);
	}
}
