import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.HashMap;

public class MyTrieSet implements TrieSet61B {
    private int size;

    private class Node {
        private boolean isKey;
        private Character letter;
        private HashMap<Character, Node> next;

        public Node(Character c, boolean b) {
            isKey = b;
            letter = c;
            next = new HashMap<>();
        }
    }

    private Node root; // root.next is the first value

    public MyTrieSet() {
        root = new Node(null, false);
    }

    public int HashCodeCalc(char ch) {
        Character c = Character.valueOf(ch);
        return c.hashCode();
    }

    @Override
    public void clear() {
        root.next = null;
    }

    @Override
    public boolean contains(String key) {
        char[] charArray = key.toCharArray();
        HashMap<Character, Node> curr = root.next;
        for (char c : charArray) {
            if (curr == null) {
                return false;
            } else if (curr.containsKey(c)) {
                curr = curr.get(c).next;
            } else {
                return false;
            }
        }
        return true;
    }

    @Override
    public void add(String key) {
        char[] charArray = key.toCharArray();
        HashMap<Character, Node> curr = root.next;
        for (int x = 0; x < charArray.length; x++) {
            if (!curr.containsKey(charArray[x])) {
                curr.put(charArray[x], new Node(charArray[x], true));
            }
            curr = curr.get(charArray[x]).next;
        }
        size++;
    }

    @Override
    public List<String> keysWithPrefix(String prefix) {
        List<String> StringList = new LinkedList<>();
        char[] prefixArray = prefix.toCharArray();
        HashMap<Character, Node> curr = root.next;

        for (int x = 0; x < prefixArray.length; x++) {
            curr = curr.get(prefixArray[x]).next;
        }
        String repr = prefix;


        for (char c : curr.keySet()) {
            repr = repr + c;
            if (curr.get(c).isKey) {
                StringList.add(repr);
                for (char c2 : curr.get(c).next.keySet()) {
                    repr = repr + c2;
                    if (curr.get(c2) == null || curr.get(c2).isKey) {
                        StringList.add(repr);
                        repr = prefix;
                    }
                }

                repr = prefix;
            }

            System.out.println(StringList);
        }

        return StringList;
    }


    @Override
    public String longestPrefixOf(String key) {
        throw new UnsupportedOperationException();
    }

}
