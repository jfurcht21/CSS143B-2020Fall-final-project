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
        for(int i = 0; i < docs.size(); i++){
            String doc = docs.get(i);
            List<List<Integer>> listCurrentDoc = new ArrayList<>();

            //Split document 1 into array of individual words
            String[] words = doc.split(" ");

            //Loop through array of words and insert each word into combined HashMap
            for(int j = 0; j < words.length; j++){

                //Removes whitespace or empty words
                if(words[j] == " " || words[j] == ""){
                    continue;
                }

                else{
                    wordsInDoc.put(new int[]{i,j}, words[j]);
                }
            }

            for(Map.Entry<int[], String> entry : wordsInDoc.entrySet()){
                int documentNumber = entry.getKey()[0];
                int wordNumber = entry.getKey()[1];
                String word = entry.getValue();

                //Checks to see if word is already in indexes
                if(indexes.containsKey(word)){
                    if(indexes.get(word).get(documentNumber).size() == 0){
                        indexes.get(word).set(documentNumber, Arrays.asList((wordNumber)));
                    }
                    else{
                        List<Integer> tmp = indexes.get(word).get(documentNumber);
                        ArrayList<Integer> wordsInDocument = new ArrayList<>(tmp.size());
                        for(int l = 0; l < tmp.size(); l++){
                         wordsInDocument.add((tmp.get(l)));
                        }
                        for(int l = 0; l < wordsInDocument.size(); l++){
                            if(wordsInDocument.get(l) > wordNumber){
                                wordsInDocument.add(l, wordNumber);
                                break;
                            }
                            if(l == wordsInDocument.size() - 1){
                                wordsInDocument.add(wordNumber);
                                break;
                            }
                        }
                        indexes.get(word).set(documentNumber, wordsInDocument);
                    }
                }
                else{
                    List<List<Integer>> newWord = new ArrayList<>(docs.size());
                    for(int k = 0; k < docs.size(); k++){
                        newWord.add(new ArrayList<>());
                    }
                    newWord.set(documentNumber, Arrays.asList(wordNumber));
                    indexes.put(word, newWord);
                }
            }
        }
        return indexes;
    }
}