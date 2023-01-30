import java.util.ArrayList;
import java.util.HashMap;

public class Trigram {
    // HashMap to store the trigrams as keys and the words that contain the trigram as values in an ArrayList
    private HashMap<String, ArrayList<String>> trigrams;

    // Constructor to initialize the trigrams hashmap
    public Trigram(HashMap<String, ArrayList<String>> trigrams) {
        this.trigrams = trigrams;
    }

    // Override the toString method to return the string representation of the trigrams hashmap
    public String toString() {
        StringBuilder st = new StringBuilder();
        // Iterate over the key set of the hashmap and append each key-value pair to the string builder
        for (String key : trigrams.keySet()) {
            st.append(key).append(" = ").append(trigrams.get(key)).append(",");
        }
        return st.toString();
    }

    // Method to find all the trigrams of a given word
    public ArrayList<String> trigramsOfWord(String word) {
        ArrayList<String> trigramsOfWord = new ArrayList<>();
        // Adding "<" and ">" to the word before finding its trigrams
        word = "<" + word + ">";
        for (int i = 0; i < word.length() - 2; i++)
            trigramsOfWord.add(word.substring(i, i + 3));
        return trigramsOfWord;
    }

    // Method to find the most common words in the hashmap that match the trigrams of the given word
    public ArrayList<String> mostCommons(String word) {
        HashMap<String, Integer> count = new HashMap<>();
        // Get the trigrams of the word
        for (String trigram : trigramsOfWord(word)) {
            try {
                // Try to get the words in the hashmap that match the current trigram
                for (String w : trigrams.get(trigram)) {
                    // Increase the count of the word if it already exists in the count hashmap, otherwise add the word to the hashmap with a count of 1
                    count.put(w, count.containsKey(w) ? count.get(w) + 1 : 1);
                }
            } catch (NullPointerException ignored) {
            }
        }

        ArrayList<String> mostCommons = new ArrayList<>();
        int cmp = 0;
        // Continue looping until either 100 words have been added to the mostCommons list or all the words in the count hashmap have been added
        while (cmp <= 100 && cmp < count.size()) {
            // Get the word in the count hashmap with the highest count
            String mostOccurring = count.entrySet().stream().max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1).get().getKey();
            mostCommons.add(mostOccurring);
            count.remove(mostOccurring);
            cmp++;
        }
        return mostCommons;
    }

    // Method to correct the given word by finding the word in the mostCommons list that has the smallest Levenshtein distance from the word
    public String correct(String word, ArrayList<String> mostCommons) {
        String corrected = mostCommons.get(0);
        for (int i = 1; i < mostCommons.size(); i++) {
            if (Levenshtein.distanceLevenshtein(word, mostCommons.get(i)) < Levenshtein.distanceLevenshtein(word, corrected)) {
                corrected = mostCommons.get(i);
            }
        }
        System.out.print(mostCommons.subList(0, 5) + " -> ");
        return corrected;
    }
}
