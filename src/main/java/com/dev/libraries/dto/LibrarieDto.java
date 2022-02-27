package com.dev.libraries.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
@Getter
@Setter
public class LibrarieDto {

	@NotBlank
	private String title;

	@NotBlank
	private String author;

	@NotBlank
	private String isbn;

	@NotBlank
	private String genero;

	public LibrarieDto() {
	}

	public LibrarieDto(String title, String author, String isbn, String genero) {
		this.title = title;
		this.author = author;
		this.isbn = isbn;
		this.genero = genero;
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

}
