package t9_phone;

import java.util.ArrayList;

public class Node {

    private final Node[] next;
    private boolean valid;

    public Node() {
        next = new Node[27];
        valid = false;
    }

    public void add(String word, int index) {
        if (index == word.length()) {
            valid = true;
            return;
        }

        char c = word.charAt(index);
        int branch = code(c);

        if (next[branch] == null) {
            next[branch] = new Node();
        }

        next[branch].add(word, ++index);
    }

    public ArrayList<String> collect(String encoding) {
        ArrayList<String> words = new ArrayList<>();

        collect(encoding, "", words);

        return words;
    }

    public void collect(String encoding, String path, ArrayList<String> words) {
        // The length of the current path will be the index of the next key in the encoding
        int index = path.length();

        // If we have reached the end of the encoding we're done
        if (index == encoding.length()) {
            // Add path to word list if it is valid
            if (valid) {
                words.add(path);
            }
            return;
        }

        // Get the next key in the sequence
        char key = encoding.charAt(index);
        int keyIndex = index(key);

        int branch = keyIndex * 3;
        if (next[branch] != null) {
            next[branch].collect(encoding, path + character(branch), words);
        }

        branch++;
        if (next[branch] != null) {
            next[branch].collect(encoding, path + character(branch), words);
        }

        branch++;
        if (next[branch] != null) {
            next[branch].collect(encoding, path + character(branch), words);
        }
    }

    private static int code(char c) {
        int code;

        code = switch(c) {
            case 'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p' -> c - 97;
            case 'r','s','t','u','v' -> c - 98;
            case 'x','y','z' -> c - 99;
            case 'å' -> 24;
            case 'ä' -> 25;
            case 'ö' -> 26;
            default -> throw new IllegalArgumentException("The character " + c + " is not supported!");
        };

        return code;
    }

    private static char character(int code) {
        int c;

        c = switch (code) {
            case 0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15 -> code + 97;
            case 16,17,18,19,20 -> code + 98;
            case 21,22,23 -> code + 99;
            case 24 -> 229;
            case 25 -> 228;
            case 26 -> 246;
            default -> throw new IllegalArgumentException(
                    "Code " + code + " not supported. Character of code is: " + (char) code
            );
        };

        return (char) c;
    }

    private static int index(char key) {
        int index = key - 49;

        if (index < 0 || 8 < index) {
            throw new IllegalArgumentException("The given key is not valid. Key: " + key + 49);
        }

        return index;
    }
}
