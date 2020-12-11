package edu.uwb.css143b2020fall.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SearcherImpl implements Searcher {
    public List<Integer> search(String keyPhrase, Map<String, List<List<Integer>>> index) {
        List<Integer> result = new ArrayList<>();

        //Separates search input into individual words
        String[] searchWords = keyPhrase.trim().split(" ");

        //If the first word isn't found then the phrase wont be found or if length == 0
        if(!index.containsKey(searchWords[0]) || keyPhrase.length() == 0){
            return result;
        }

        // If only one word
        if(searchWords.length == 1){
            List<List<Integer>> searchTerm = index.get(keyPhrase);
            for(int i = 0; i < searchTerm.size(); i++){
                if(searchTerm.get(i).size() > 0){
                    result.add(i);
                }
            }
        }

        // If more than one word
        if(searchWords.length > 1){
            for(int i = 0; i < index.get(searchWords[0]).size(); i++){
                List<List<Integer>> position = new ArrayList<>();
                for(String word : searchWords){
                    position.add(index.get(word).get(i));
                }
                if(inOrder(position)){
                    result.add(i);
                }
            }
        }
        return result;
    }

    //Functions to check order of words
    private boolean inOrder(List<List<Integer>> position) {
        for (int i = 0; i < position.get(0).size(); i++) {
            return inOrderHelper(position, 1, position.get(0).get(i));
        }
        return false;
    }

    private boolean inOrderHelper(List<List<Integer>> position, int wordIndex, int previous) {

        if (position.size() <= wordIndex) {
            return true;
        }

        else {
            for (Integer place : position.get(wordIndex)) {
                if (place != previous + 1) {
                    continue;
                }
                return inOrderHelper(position, wordIndex + 1, previous + 1);
            }
        }
        return false;
    }
}