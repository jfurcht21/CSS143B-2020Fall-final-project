package edu.uwb.css143b2020fall.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SearcherImpl implements Searcher {
    public List<Integer> search(String keyPhrase, Map<String, List<List<Integer>>> index) {
        List<Integer> result = new ArrayList<>();

        // For null or empty search terms
        if(keyPhrase.length() == 0 || keyPhrase == null){
            System.out.println("Please enter a valid search term");
            return result;
        }
        //Separates search input into individual words
        String[] searchWords = keyPhrase.split(" ");

        // If only one word
        if(searchWords.length == 1){
            if(!index.containsKey((searchWords[0]))){
                return result;
            }
            
        }



        return result;
    }
}