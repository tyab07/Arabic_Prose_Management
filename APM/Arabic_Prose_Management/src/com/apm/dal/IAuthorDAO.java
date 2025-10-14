package com.apm.dal;

import com.apm.dto.AuthorDTO;

public interface IAuthorDAO {
	
	public boolean createAuthor(int authorId, String name, String biography);
	public AuthorDTO retrieveAuthor(String name);
	public boolean updateAuthor(int authorId, String name, String biography);
	public boolean deleteAuthor(String name);
	
	
	
	public boolean searchAuthor(String name);
	

}
