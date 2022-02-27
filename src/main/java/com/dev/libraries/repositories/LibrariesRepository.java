package com.dev.libraries.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.libraries.model.LibrariesModel;

public interface LibrariesRepository extends JpaRepository<LibrariesModel, Long> {

}
