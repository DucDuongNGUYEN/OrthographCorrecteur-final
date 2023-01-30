import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;

// Class to implement a spelling correction program
public class CorrecteurOrthograph {
    // HashSet to store a dictionary of correct words
    private HashSet<String> dictionary = new HashSet<>();
    // HashMap to store trigrams of words
    private static HashMap<String, ArrayList<String>> trigrams = new HashMap<>();

    // Constructor to read words from a file and create a dictionary
    public CorrecteurOrthograph(String filename) throws IOException {
        // Create a File object from the file name
        File file = new File(filename);
        // Create a BufferedReader to read the file
        BufferedReader br = new BufferedReader(new FileReader(file));
        // Read each line of the file
        String st;
        while ((st = br.readLine()) != null) {
            // Add the lowercase version of the word to the dictionary
            dictionary.add(st.toLowerCase(Locale.ROOT));
            // Create trigrams for the word
            st = "<" + st + ">";
            for (int i = 0; i < st.length() - 2; i++) {
                // Check if the trigram already exists in the HashMap
                if (!trigrams.containsKey(st.substring(i, i + 3))) {
                    // If not, create a new ArrayList and add the word to it
                    ArrayList<String> wordsWithTrigram = new ArrayList<>();
                    wordsWithTrigram.add(st);
                    trigrams.put(st.substring(i, i + 3), wordsWithTrigram);
                } else {
                    // If yes, add the word to the existing ArrayList
                    trigrams.get(st.substring(i, i + 3)).add(st);
                }
            }
        }
        // Close the BufferedReader
        br.close();
    }

    // Method to correct the spelling of a word
    public String correct(String word) {
        // Check if the word is already in the dictionary
        if (dictionary.contains(word)) return word;
        // If not, create a Trigram object and use it to correct the word
        Trigram trigram = new Trigram(trigrams);
        return trigram.correct(word, trigram.mostCommons(word));
    }

    // Method to correct the spelling of all words in a text file
    public void correctAll(String text) throws IOException {
        // Create a File object from the file name
        File file = new File(text);
        // Create a BufferedReader to read the file
        BufferedReader br = new BufferedReader(new FileReader(file));
        // Read each line of the file
        String st;
        while ((st = br.readLine()) != null) {
            // Correct the spelling of the line and print it to the console
            System.out.println(st + " = " + correct(st));
        }
        // Close the BufferedReader
        br.close();
    }
}
