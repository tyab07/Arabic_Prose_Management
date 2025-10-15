package com.apm.bll;

import com.apm.dto.AuthorDTO;

public class AuthorBO implements IAuthorBO{

	@Override
	public boolean createAuthor(int authorId, String name, String biography) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public AuthorDTO retrieveAuthor(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateAuthor(int authorId, String name, String biography) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteAuthor(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean searchAuthor(String name) {
		// TODO Auto-generated method stub
		return false;
	}

}
