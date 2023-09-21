package single_linked_list;

import java.util.Random;

public class ArrayList {

    int[] list;

    public ArrayList(int length) {
        Random random = new Random();
        list = new int[length];
        for (int i = 0; i < length; i++) {
            list[i] = random.nextInt(length);
        }
    }

    public int length() {
        return list.length;
    }

    public int get(int index) throws IndexOutOfBoundsException {
        return list[index];
    }

    public void append(ArrayList list2) {
        int newLength = list.length + list2.length();
        int[] newList = new int[newLength];

        System.arraycopy(list, 0, newList, 0, list.length);
        for (int i = 0; i < list2.length(); i++) {
            newList[list.length + i] = list2.get(i);
        }

        list = newList;
    }
}
