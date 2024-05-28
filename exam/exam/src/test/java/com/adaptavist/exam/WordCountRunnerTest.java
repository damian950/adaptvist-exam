package com.adaptavist.exam;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

class WordCountRunnerTest {
	WordCountRunner wordCountRunner;

	@Mock
	WordCountService wordCountService;
	@Mock
	Logger logger;

	final String filePath = "test.txt";
	@BeforeEach
	void init() {
		MockitoAnnotations.openMocks(this);
		wordCountRunner = spy(new WordCountRunner(wordCountService));
	}

	@Test
	void thatGetsWordCount() throws Exception {
		wordCountRunner.run(filePath);
		verify(wordCountService).countWords(filePath);
	}
}
