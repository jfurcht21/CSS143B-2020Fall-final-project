package edu.uwb.css143b2020fall.service;

import org.hibernate.cache.spi.StandardCacheTransactionSynchronization;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class IndexerImpl implements Indexer {
    public Map<String, List<List<Integer>>> index(List<String> docs) {
        Map<String, List<List<Integer>>> indexes = new HashMap<>();
        Map<int[], String> wordsInDoc = new HashMap<>();

        //Loop through each document
        for(int i = 0; i < docs.size(); i++)
        {
            String doc = docs.get(i);
            //Split document 1 into array of individual words
            String[] words = doc.split(" ");

            // Finds additional whitespace or null words
            int whiteSpace = 0;
            for(String word : words) {
                if (word.equals("") || word.equals(" ")) {
                    whiteSpace++;
                }
            }

            //If whitespace or null words are found they will be removed
            if(whiteSpace > 0)
            {
                String[] wordsNoWhiteSpace = new String[words.length-whiteSpace];
                int wordIndex = 0;
                for(int index = 0; index < words.length; index++)
                {
                    if(words[index].equals(""))
                        continue;
                    wordsNoWhiteSpace[wordIndex++] = words[index];
                }
                words = wordsNoWhiteSpace;
            }

            //Add words with document number and index in document to a map
            for(int j = 0; j < words.length; j++)
                wordsInDoc.put(new int[]{i, j}, words[j]);
        }

        // https://www.baeldung.com/java-iterate-map
        // Iterates through the map above to add words to indexes
        for (Map.Entry<int[], String> entry : wordsInDoc.entrySet()) {
            int documentNumber = entry.getKey()[0];
            int wordNumber = entry.getKey()[1];
            String word = entry.getValue();

            //Checks to see if word is already in indexes
            //If it is, logic is different than from a new word
            if (indexes.containsKey(word)) {
                if (indexes.get(word).get(documentNumber).size() == 0) {
                    indexes.get(word).set(documentNumber, Arrays.asList((wordNumber)));
                }
                else {
                    List<Integer> tmp = indexes.get(word).get(documentNumber);
                    ArrayList<Integer> wordsInDocument = new ArrayList<>(tmp.size());
                    for (int l = 0; l < tmp.size(); l++) {
                        wordsInDocument.add((tmp.get(l)));
                    }
                    for (int l = 0; l < wordsInDocument.size(); l++) {
                        if (wordsInDocument.get(l) > wordNumber) {
                            wordsInDocument.add(l, wordNumber);
                            break;
                        }
                        if (l == wordsInDocument.size() - 1) {
                            wordsInDocument.add(wordNumber);
                            break;
                        }
                    }
                    indexes.get(word).set(documentNumber, wordsInDocument);
                }
            }
            else {
                List<List<Integer>> newWord = new ArrayList<>(docs.size());
                for (int k = 0; k < docs.size(); k++) {
                    newWord.add(new ArrayList<>());
                }
                newWord.set(documentNumber, Arrays.asList(wordNumber));
                indexes.put(word, newWord);
            }
        }
        return indexes;
    }
}