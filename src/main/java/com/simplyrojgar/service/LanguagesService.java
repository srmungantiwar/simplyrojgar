package com.simplyrojgar.service;

import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Sets;
import com.simplyrojgar.entity.Languages;
import com.simplyrojgar.repository.LanguagesRepository;

@Service
public class LanguagesService {
	
	@Autowired
	private LanguagesRepository languagesRepository;
	
	@Autowired
	private Languages languages;
	
	public Set<Languages> getLanguages() {
		Iterable<Languages> languagesIterable = languagesRepository.findAll();
		return Sets.newLinkedHashSet(languagesIterable);
	}

	public void addLanguage(String language) {
		if(StringUtils.isNotBlank(language)) {
			languages.setLanguage(language);
			languagesRepository.save(languages);
		}
	}
}
