package edu.uwb.css143b2020fall.service;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class IndexerImpl implements Indexer {
    public Map<String, List<List<Integer>>> index(List<String> docs) {
        Map<String, List<List<Integer>>> indexes = new HashMap<>();
        // add your code
        for(int i = 0; i < docs.size(); i++){
            String doc = docs.get(i);
            String[] words = doc.split(" ");
            for(int j = 0; j < words.length; j++){
                if(indexes.containsKey(words[j])){
                    indexes.get(words[j]).add(Arrays.asList(i));
                }
                else{
                    List<List<Integer>> newList = new ArrayList<>();
                    List<Integer> newWord = new ArrayList<>();
                    newList.add(newWord);
                    //Fails here on i = 1 during indexer test. Also check to ensure document number is
                    //Separate from list of positions in the document
                    newList.set(i, Arrays.asList(j));
                    indexes.put(words[j], newList);
                }
            }
        }
        return indexes;
    }
}