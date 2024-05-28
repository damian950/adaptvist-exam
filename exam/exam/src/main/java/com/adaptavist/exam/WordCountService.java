package com.adaptavist.exam;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class WordCountService {
	public Map<String, Integer> countWords(String filePath) throws IOException {
		Map<String, Integer> wordCountMap = new HashMap<>();
		try (BufferedReader br = createBuffferedReader(filePath)) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] words = line.split("\\s+");// Split by whitespace
				for (String word : words) {
					word = 	cleanWord(word);
					if(!word.isBlank()){
						wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);
					}
				}
			}
		}

		return wordCountMap.entrySet().stream()
				.sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
				.collect(Collectors.toMap(
						Map.Entry::getKey,
						Map.Entry::getValue,
						(oldValue, newValue) -> oldValue,
						LinkedHashMap::new // Using LinkedHashMap to preserve insertion order
				));
	}

	public BufferedReader createBuffferedReader(String filePath) throws FileNotFoundException {
		return new BufferedReader(new FileReader(filePath));
	}

	private String cleanWord(String word) {
		return word.replaceAll("[^a-zA-Z0-9]", "").toLowerCase().trim();
	}
}
