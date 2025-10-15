package com.apm.dal;

import com.apm.dto.SentenceDTO;

public class SentenceDAO implements ISentenceDAO {

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
