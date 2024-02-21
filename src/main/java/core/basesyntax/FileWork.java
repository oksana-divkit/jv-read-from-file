package core.basesyntax;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class FileWork {
    public static final String PREFIX_W = "w";

    public String[] readFromFile(String fileName) {
        String fileContent = readContentFile(fileName);
        String[] words = getAllWords(fileContent);

        return filterWordsStartsWith(PREFIX_W, words);
    }

    private String readContentFile(String fileName) {
        Path path = Paths.get(fileName);
        StringBuilder fileContent = new StringBuilder();

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                fileContent.append(line.toLowerCase()).append(" ");
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }

        return fileContent.toString();
    }

    private String[] getAllWords(String content) {
        return content.split("[^\\w-']");
    }

    private String[] filterWordsStartsWith(String prefix, String[] words) {
        StringBuilder wordsAtWBuilder = new StringBuilder();
        String[] wordsAtW;

        for (String word : words) {
            if (word.startsWith(prefix)) {
                wordsAtWBuilder.append(word).append(" ");
            }
        }

        if (wordsAtWBuilder.isEmpty()) {
            wordsAtW = new String[0];
        } else {
            wordsAtW = wordsAtWBuilder.toString().split(" ");
            Arrays.sort(wordsAtW);
        }

        return wordsAtW;
    }
}
