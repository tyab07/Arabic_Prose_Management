package com.apm.bll;

import com.apm.dto.SentenceDTO;

public interface ISentenceBO {
	public boolean createSentence(int sentenceId, int bookId, int sentenceNumber,String text, String textDiacritized, String translation, String notes);
	public SentenceDTO retrieveSentence(int bookId,int sentenceNumber);
	public boolean updateSenetence(int sentenceId, int bookId, int sentenceNumber,String text, String textDiacritized, String translation, String notes);
	public boolean deleteSentence(int bookId,int sentenceNumber);
	
	
	
	public boolean searchBook(int bookId,int sentenceNumber);

}
