package com.dev.libraries.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.libraries.model.LibrariesModel;
import com.dev.libraries.repositories.LibrariesRepository;

@Service
public class LibrarieService {

	@Autowired
	LibrariesRepository librariesRepository;

	public Optional<LibrariesModel> findById(Long id) {
		return librariesRepository.findById(id);
	}

	public Object save(LibrariesModel libraries) {
		return librariesRepository.save(libraries);
	}

	public List<LibrariesModel> findAll(LibrariesModel libraries) {
		return librariesRepository.findAll();
	}

	public void delete(LibrariesModel librariesModel) {
		librariesRepository.delete(librariesModel);

	}

}
