package t9_phone;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

public class Trie {

    Node root;

    public Trie() {
        root = new Node();
    }

    public void add(String word) {
        root.add(word, 0);
    }

    public ArrayList<String> decode(String encoding) {
        return root.collect(encoding);
    }

    public static char key(char c) {
        char key;

        key = switch (c) {
            case 'a','b','c' -> '1';
            case 'd','e','f' -> '2';
            case 'g','h','i' -> '3';
            case 'j','k','l' -> '4';
            case 'm','n','o' -> '5';
            case 'p','r','s' -> '6';
            case 't','u','v' -> '7';
            case 'x','y','z' -> '8';
            case 'å','ä','ö' -> '9';
            default -> throw new IllegalArgumentException("No key for the given character: " + c);
        };

        return key;
    }

    private static String encode(String word) {
        StringBuilder keys = new StringBuilder();

        for (int i = 0; i < word.length(); i++) {
            char key = key(word.charAt(i));
            keys.append(key);
        }

        return keys.toString();
    }

    public ArrayList<String> populate() {
        String fileName = "src/t9_phone/kelly.txt";

        ArrayList<String> words = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String word;
            while ((word = br.readLine()) != null) {
                add(word);
                words.add(word);
            }
        } catch (Exception e) {
            System.out.println(" file " + fileName + " not found");
        }

        return words;
    }

    public static void main(String[] args) {
        Trie t9 = new Trie();

        ArrayList<String> words = t9.populate();

        for (int i = 0; i < 100; i++) {
            Random random = new Random();
            String word = words.get(random.nextInt(words.size()));

            String encoding = encode(word);

            System.out.println("The word " + word + " is encoded as " + encoding);
            System.out.println("The result of decoding it is:");
            ArrayList<String> result = t9.decode(encoding);
            for (String decoding : result) {
                System.out.println(decoding);
            }
            System.out.println();
        }
    }
}
