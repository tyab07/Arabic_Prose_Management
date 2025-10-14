package com.apm.bll;

import com.apm.dto.AuthorDTO;
import com.apm.dto.BookDTO;
import com.apm.dto.SentenceDTO;

public class BussinessLayerFasade implements IBussinessLayerFasade{
	
	
	
	private IBookBO book;
	private IAuthorBO author;
	private ISentenceBO sentence;
	
	
	
	public BussinessLayerFasade(IBookBO book,IAuthorBO author,ISentenceBO sentence)
	{
		this.book = book;
		this.author = author;
		this.sentence = sentence;
		
	}

	@Override
	public boolean createBook(String title, int authorId, String era) {
		return book.createBook(title, authorId, era);
	}

	@Override
	public BookDTO retrieveBook(String title) {
		return book.retrieveBook(title);
	}

	@Override
	public boolean updateBook(String title, String era) {
		return book.updateBook(title, era);
	}

	@Override
	public boolean deleteBook(String title) {
		return book.deleteBook(title);
	}
	public int searchBook(String title) {
		return book.searchBook(title);
	}

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

	@Override
	public boolean createSentence(int sentenceId, int bookId, int sentenceNumber, String text, String textDiacritized,
			String translation, String notes) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public SentenceDTO retrieveSentence(int bookId, int sentenceNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateSenetence(int sentenceId, int bookId, int sentenceNumber, String text, String textDiacritized,
			String translation, String notes) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteSentence(int bookId, int sentenceNumber) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean searchBook(int bookId, int sentenceNumber) {
		// TODO Auto-generated method stub
		return false;
	}

}
