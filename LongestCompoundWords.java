import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LongestCompoundWords {
    public static void main(String[] args) {

        processInputFile("Input_02.txt");
    }

    private static void processInputFile(String fileName) {
        System.out.println("Processing file: " + fileName);
        long startTime = System.currentTimeMillis();
        List<String> words = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                words.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        List<String> compoundedWords = findCompoundedWords(words);

        Collections.sort(compoundedWords, (s1, s2) -> s2.length() - s1.length());

        if (compoundedWords.size() >= 2) {
            System.out.println("Longest compounded word: " + compoundedWords.get(0));
            System.out.println("Second longest compounded word: " + compoundedWords.get(1));
        } else {
            System.out.println("Not enough compounded words found.");
        }

        long endTime = System.currentTimeMillis();
        long processingTime = endTime - startTime;
        System.out.println("Time taken to process the input file: " + processingTime + " milliseconds");
    }

    private static List<String> findCompoundedWords(List<String> words) {
        List<String> compoundedWords = new ArrayList<>();
        for (String word : words) {
            if (isCompoundedWord(word, words)) {
                compoundedWords.add(word);
            }
        }
        return compoundedWords;
    }

    private static boolean isCompoundedWord(String word, List<String> words) {
        if (word.length() == 0) {
            return false;
        }

        for (int i = 1; i <= word.length(); i++) {
            String prefix = word.substring(0, i);
            String suffix = word.substring(i);

            if (words.contains(prefix) && (words.contains(suffix) || isCompoundedWord(suffix, words))) {
                return true;
            }
        }

        return false;
    }
}
