package com.simplyrojgar.service;

import java.util.Optional;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Sets;
import com.simplyrojgar.entity.Language;
import com.simplyrojgar.repository.LanguagesRepository;

@Service
public class LanguagesService {
	
	@Autowired
	private LanguagesRepository languagesRepository;
	
	@Autowired
	private Language languages;
	
	public Set<Language> getLanguages() {
		Iterable<Language> languagesIterable = languagesRepository.findAll();
		return Sets.newLinkedHashSet(languagesIterable);
	}

	public void addLanguage(String language) {
		if(StringUtils.isNotBlank(language)) {
			languages.setLanguage(language);
			languagesRepository.save(languages);
		}
	}

	public Language getLanguageById(Long id) {
		Optional<Language> languageOpt = languagesRepository.findById(id);
		return languageOpt.get();
	}

	public void removeLanguageById(Long id) {
		languagesRepository.deleteById(id);
		
	}
}
