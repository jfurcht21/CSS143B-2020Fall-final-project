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
            for(int j = 0; j < words.length; j++){
                if(indexes.containsKey(words[j])){
                    indexes.get(words[j]).add();
                }
                else{
                    List<List<Integer>> newList = new ArrayList<>();
                    newList.add(i);
                    indexes.put(words[j], j);
                }
            }
        }
        return indexes;
    }
}