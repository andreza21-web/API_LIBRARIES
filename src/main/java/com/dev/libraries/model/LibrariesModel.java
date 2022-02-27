package com.dev.libraries.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.hateoas.RepresentationModel;

@Entity
@Table(name = "Libraries")
public class LibrariesModel extends RepresentationModel<LibrariesModel> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false, length = 130)
	private String title;

	@Column(nullable = false, length = 130)
	private String author;

	@Column(nullable = false, length = 130)
	private String isbn;

	@Column(nullable = false, length = 130)
	private String genero;

	@Column(nullable = false, length = 130)
	private LocalDateTime registrationDate;

	public LibrariesModel() {
	}

	public LibrariesModel(Long id, String title, String author, String isbn, String genero,
			LocalDateTime registrationDate) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.isbn = isbn;
		this.genero = genero;
		this.registrationDate = registrationDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public LocalDateTime getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(LocalDateTime registrationDate) {
		this.registrationDate = registrationDate;
	}

}
