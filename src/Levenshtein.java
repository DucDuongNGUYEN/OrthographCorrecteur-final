public class Levenshtein {
    //This method calculates the Levenshtein distance between two strings.
    public static int distanceLevenshtein(String word1, String word2){
        // Initialize a 2D array to store the distances between characters in word1 and word2.
        int[][] distance = new int [word1.length() + 1][word2.length() + 1];

        // Initialize the first column of the 2D array with the number of characters in word1.
        for(int i = 0; i <= word1.length(); i++) distance[i][0] = i;

        // Initialize the first row of the 2D array with the number of characters in word2.
        for(int j = 0; j <= word2.length(); j++) distance [0][j] = j;

        // Iterate through the 2D array and fill in the values of each cell with the minimum distance between the two strings.
        for(int i = 1; i <= word1.length(); i++){
            for(int j = 1; j <= word2.length(); j++){
                // If the characters in word1 and word2 match at the current index, the distance is equal to the distance in the previous cell.
                if(word1.charAt(i - 1) == (word2.charAt(j - 1))){ distance[i][j] = distance[i - 1][j - 1]; }
                /* If the characters in word1 and word2 do not match, the distance is the minimum of the three possible distances
                 (delete a character, insert a character, or substitute a character)*/
                else{distance[i][j] = Math.min(distance[i - 1][j - 1] + 1, Math.min(distance[i - 1][j] + 1, distance[i][j - 1] + 1));}
            }
        }

        // Return the distance between the two strings, which is stored in the last cell of the 2D array.
        return distance[word1.length()][word2.length()];
    }
}

