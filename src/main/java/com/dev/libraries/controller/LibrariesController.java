package com.dev.libraries.controller;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.libraries.dto.LibrarieDto;
import com.dev.libraries.model.LibrariesModel;
import com.dev.libraries.service.LibrarieService;

@RestController
@RequestMapping("/libraries")
public class LibrariesController {

	@Autowired
	LibrarieService service;

	@GetMapping("/{id}")
	public ResponseEntity<Object> getOneLibraries(@PathVariable(value = "id") Long id) {
		Optional<LibrariesModel> librariesModel = service.findById(id);
		if (!librariesModel.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("libraries not found.");
		}
		librariesModel.get()
				.add(linkTo(methodOn(LibrariesController.class).getAllLibraries()).withRel("book list"));
		return ResponseEntity.status(HttpStatus.OK).body(librariesModel.get());
	}

	@GetMapping
	public ResponseEntity<List<LibrariesModel>> getAllLibraries() {
		List<LibrariesModel> librariesList = service.findAll();
		if (librariesList.isEmpty()) {
			return new ResponseEntity<List<LibrariesModel>>(HttpStatus.NOT_FOUND);
		} else {
			for (LibrariesModel libraries : librariesList) {
				long id = libraries.getId();
				libraries.add(linkTo(methodOn(LibrariesController.class).getOneLibraries(id)).withSelfRel());

			}
		}
		return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
	}

	@PostMapping
	public ResponseEntity<Object> saveLibraries(@Valid @RequestBody LibrariesModel libraries) {
		return ResponseEntity.status(HttpStatus.OK).body(service.save(libraries));

	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> updateLibrarie(@PathVariable(value = "id") Long id,
			@RequestBody @Valid LibrarieDto librariesDto) {
		Optional<LibrariesModel> librariesModel = service.findById(id);
		if (!librariesModel.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Libraries not found.");
		}
		LibrariesModel librarieModel = new LibrariesModel();
		BeanUtils.copyProperties(librariesDto, librarieModel);
		librarieModel.setId(librariesModel.get().getId());
		return ResponseEntity.status(HttpStatus.OK).body(service.save(librarieModel));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteParkingSpot(@PathVariable(value = "id") Long id) {
		Optional<LibrariesModel> librariesModel = service.findById(id);
		if (!librariesModel.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Libraries not found.");
		}
		service.delete(librariesModel.get());
		return ResponseEntity.status(HttpStatus.OK).body("Libraries deleted successfully.");
	}
}
