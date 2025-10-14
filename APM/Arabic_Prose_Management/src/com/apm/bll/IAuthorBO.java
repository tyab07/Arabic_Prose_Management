package com.apm.bll;

import com.apm.dto.AuthorDTO;

public interface IAuthorBO {
	public boolean createAuthor(int authorId, String name, String biography);
	public AuthorDTO retrieveAuthor(String name);
	public boolean updateAuthor(int authorId, String name, String biography);
	public boolean deleteAuthor(String name);
	
	
	
	public boolean searchAuthor(String name);

}
