package com.apm.dal;

import com.apm.dto.SentenceDTO;

public interface ISentenceDAO {
	public boolean createSentence(int sentenceId, int bookId, int sentenceNumber,String text, String textDiacritized, String translation, String notes);
	public SentenceDTO retrieveSentence(int bookId,int sentenceNumber);
	public boolean updateSenetence(int sentenceId, int bookId, int sentenceNumber,String text, String textDiacritized, String translation, String notes);
	public boolean deleteSentence(int bookId,int sentenceNumber);
	
	
	
	public boolean searchBook(int bookId,int sentenceNumber);

}
