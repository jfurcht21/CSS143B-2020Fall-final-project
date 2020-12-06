package edu.uwb.css143b2020fall.service;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class IndexerImpl implements Indexer {
    public Map<String, List<List<Integer>>> index(List<String> docs) {
        Map<String, List<List<Integer>>> indexes = new HashMap<>();

        //Loop through each document
        for(int i = 0; i < docs.size(); i++){
            String doc = docs.get(i);
            List<List<Integer>> listCurrentDoc = new ArrayList<>();

            //Split document 1 into array of individual words
            String[] words = doc.split(" ");

            //Loop through array of words and insert each word at proper index
            for(int j = 0; j < words.length; j++){

                //Removes whitespace or empty words
                if(words[j] == " " || words[j] == ""){
                    continue;
                }

                // What to do if word has already been found in document
                // Add the word in an existing list and add the words location in the array (j)
                if(indexes.containsKey(words[j])){
                    indexes.get(words[j]).add(Arrays.asList(i));
                }

                // If first time word is popping up in the document, create a new entry in the Map
                else{
                    List<Integer> newWord = new ArrayList<>();
                    listCurrentDoc.add(newWord);
                    listCurrentDoc.set(0, Arrays.asList(j));
                    indexes.put(words[j], listCurrentDoc);
                }

            }


        }
        return indexes;
    }
}