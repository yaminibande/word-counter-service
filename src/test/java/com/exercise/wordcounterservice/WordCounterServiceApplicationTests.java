package com.exercise.wordcounterservice;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.clearAllCaches;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.exercise.wordcounterservice.service.WordCounterServiceImpl;

@SpringBootTest
class WordCounterServiceApplicationTests {
	
	@Autowired
	WordCounterServiceImpl wCounter;
	
	
	@AfterEach
	void TestLevelTearDown() {
		wCounter.clearWordCounter();
		clearAllCaches();
	}

	@Test
	void testThatUserIsAbleToAddOneWord() {
		List<String> word = new ArrayList<>();
		word.add("Word");
		assertEquals(wCounter.addWord(word), "Word/Words Added successfully!", "Success message should be displayed");
	    assertTrue(wCounter.getWordCounterSize() == 1);
	}
	
	@Test
	void testThatUserIsAbleToAddMultipleWords() {
		List<String> word = new ArrayList<>();
		word.add("Word");
		word.add("Counter");
		word.add("Service");
		word.add("Multiple");
		assertEquals(wCounter.addWord(word), "Word/Words Added successfully!", "Success message should be displayed");
	    assertTrue(wCounter.getWordCounterSize() == 4);
	}
	
	@Test
	void testThatUserIsUnableToAddAlphaNumbericWords() {
		try {
			List<String> word = new ArrayList<>();
			word.add("Word123");
			wCounter.addWord(word);
		}
		catch (Exception e) {
			assertThatIllegalArgumentException();
		}
	}
	

	@Test
	void testThatUserIsUnableToAddNull() {
		try {
			List<String> word = new ArrayList<>();
			word.add(null);
			wCounter.addWord(word);
		}
		catch (Exception e) {
			assertThatIllegalArgumentException();
		}
	}


	@Test
	void testThatUserIsUnableToAddEmptyString() {
		try {
			List<String> word = new ArrayList<>();
			word.add("");
			wCounter.addWord(word);
		}
		catch (Exception e) {
			assertThatIllegalArgumentException();
		}
	}
	

	@Test
	void testThatUserIsAbleToGetCountSingleInstance() {
		List<String> word = new ArrayList<>();
		word.add("word");
		word.add("counter");
		word.add("service");
		assertEquals(wCounter.addWord(word), "Word/Words Added successfully!", "Success message should be displayed");
		assertTrue(wCounter.getWordCount("word") == 1, "The count should be 1");
	}

	@Test
	void testThatUserIsAbleToGetCountMultipleInstance() {
		List<String> word = new ArrayList<>();
		word.add("word");
		word.add("word");
		word.add("word");
		word.add("counter");
		word.add("counter");
		word.add("counter");
		word.add("counter");
		word.add("service");
		assertEquals(wCounter.addWord(word), "Word/Words Added successfully!", "Success message should be displayed");
		assertTrue(wCounter.getWordCount("word") == 3, "The count for word 'word' should be 3");
		assertTrue(wCounter.getWordCount("counter") == 4, "The count for word 'counter' should be 4");
		assertTrue(wCounter.getWordCount("service") == 1, "The count for word 'service' should be 1");
	}

}
