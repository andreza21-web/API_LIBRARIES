package com.dev.libraries.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

	public Page <LibrariesModel> findAll(Pageable pageable) {
		return librariesRepository.findAll(pageable);
	}

	public void delete(LibrariesModel librariesModel) {
		librariesRepository.delete(librariesModel);

	}

}
