package com.wolfmatemark.wmmuonlite;

import java.io.IOException;
import java.util.*;

public class NameReader {
    private List<String> listOfFirstNames;
    private List<String> listOfSecondNames;

    public NameReader() {
        listOfFirstNames = new ArrayList<>();
        listOfSecondNames = new ArrayList<>();
    }

    public void readNames(String fileName) throws IOException {
        listOfFirstNames = Arrays.asList("Big", "Old", "Small", "Great", "Shiny", "Used", "Worn", "Ancient", "Enormous", "Tiny");
        listOfSecondNames = Arrays.asList("Handy", "Elven", "Dark", "Magical", "Crappy", "Weak", "Strong", "Epic", "Dwarven", "Junk");
    }

    public List<String> getListOfFirstNames() {
        return listOfFirstNames;
    }

    public List<String> getListOfSecondNames() {
        return listOfSecondNames;
    }
}
