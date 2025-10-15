package com.apm.bll;

import com.apm.dto.BookDTO;

public interface IBookBO {
	
	public boolean createBook(String title,int authorId,String era);
	public BookDTO retrieveBook(String title);
	boolean updateBook(String title, String era);
	public boolean deleteBook(String title);
	
	
	
	public int searchBook(String title);

}
