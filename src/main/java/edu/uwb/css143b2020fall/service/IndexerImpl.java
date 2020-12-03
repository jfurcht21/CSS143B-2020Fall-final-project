package edu.uwb.css143b2020fall.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IndexerImpl implements Indexer {
    public Map<String, List<List<Integer>>> index(List<String> docs) {
        Map<String, List<List<Integer>>> indexes = new HashMap<>();
        // add your code
        for(int i = 0; i < docs.size(); i++){
            String doc = docs.get(i);
            String[] words = doc.split(" ");
            for(String word : words){
                if(indexes.containsKey(word)){
                    indexes.put();
                }
            }
        }
        return indexes;
    }
}