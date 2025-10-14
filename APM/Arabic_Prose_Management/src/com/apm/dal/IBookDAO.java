package com.apm.dal;

import java.sql.SQLException;

import com.apm.dto.BookDTO;

public interface IBookDAO {

	public boolean createBook(String title,int authorId,String era) ;
	public BookDTO retrieveBook(String title);
	boolean updateBook(String title,int authorId,String era);
	public boolean deleteBook(String title);
	
	
	
	public int searchBook(String title);

	
}
