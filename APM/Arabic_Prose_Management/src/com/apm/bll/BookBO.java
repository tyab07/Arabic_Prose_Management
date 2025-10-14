package com.apm.bll;

import com.apm.dal.IDataAccessLayerFasade;
import com.apm.dto.BookDTO;

public class BookBO implements IBookBO {
	
	private IDataAccessLayerFasade daf;
	
	
	public BookBO(IDataAccessLayerFasade daf)
	{
		this.daf = daf;
	}

	@Override
	public boolean createBook(String title, int authorId, String era) {
		
		
		 return daf.createBook(title,authorId,era);
	}

	@Override
	public BookDTO retrieveBook(String title) {
		
		return daf.retrieveBook(title);
	}

	@Override
	public boolean updateBook(String title, String era) {
		 return daf.updateBook(title,3,era);//////////////////////////////////////////////////////HardCode??
	}

	@Override
	public boolean deleteBook(String title) {
		return daf.deleteBook(title);
	}

	@Override
	public int searchBook(String title) {
		return daf.searchBook(title);
	}

	
	

}
