package com.dev.libraries.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
				.add(linkTo(methodOn(LibrariesController.class).getAllLibraries(null)).withRel("book list"));
		return ResponseEntity.status(HttpStatus.OK).body(librariesModel.get());
	}

	@GetMapping
	public ResponseEntity<Page<LibrariesModel>> getAllLibraries(
			@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
		Page<LibrariesModel> librariesList = service.findAll(pageable);
		if (librariesList.isEmpty()) {
			return new ResponseEntity<Page<LibrariesModel>>(HttpStatus.NOT_FOUND);
		} else {
			for (LibrariesModel libraries : librariesList) {
				long id = libraries.getId();
				libraries.add(linkTo(methodOn(LibrariesController.class).getOneLibraries(id)).withSelfRel());

			}
		}
		return ResponseEntity.status(HttpStatus.OK).body(service.findAll(pageable));
	}

	@PostMapping
	public ResponseEntity<Object> saveLibraries(@Valid @RequestBody LibrarieDto librariesDto) {
		LibrariesModel librarieModel = new LibrariesModel();
		BeanUtils.copyProperties(librariesDto, librarieModel);
		librarieModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
		return ResponseEntity.status(HttpStatus.OK).body(service.save(librarieModel));

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
		librarieModel.setRegistrationDate(librariesModel.get().getRegistrationDate());
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
