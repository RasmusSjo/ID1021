package quick_sort;

import utils.ArrayGenerator;

import java.util.Arrays;

public class LinkedSort {

    public static void quickSort(LinkedList list) {
        if (list.length() <= 1) return;

        // Create two auxiliary lists, one for the smaller-
        // and one for the larger nodes
        LinkedList smaller = new LinkedList();
        LinkedList larger = new LinkedList();
        LinkedList.Node pivot = partition(list, smaller, larger);

        quickSort(smaller);
        quickSort(larger);

        smaller.add(pivot);
        smaller.append(larger);

        list.first = smaller.first;
        list.last = smaller.last;
    }

    private static LinkedList.Node partition(LinkedList list, LinkedList smaller, LinkedList larger) {
        LinkedList.Node pivot = list.first;

        LinkedList.Node current = pivot.next;
        while (current != null) {
            LinkedList.Node node = current;
            current = current.next;
            if (node.value <= pivot.value) {
                smaller.add(node);
            }
            else {
                larger.add(node);
            }
        }

        return pivot;
    }

    public static LinkedList.Node sort(LinkedList.Node list) {
        if (list == null || list.next == null) return list;

        LinkedList.Node head;
        LinkedList.Node smallPart = null;
        LinkedList.Node smallTemp = null;
        LinkedList.Node largePart = null;
        LinkedList.Node largeTemp = null;

        LinkedList.Node current = list.next;
        while (current != null) {
            // Check if the current element should be in the large or small part
            if (current.value <= list.value) {
                if (smallPart == null) {
                    smallPart = current;
                    smallTemp = current;
                }
                else {
                    smallTemp.next = current;
                    smallTemp = smallTemp.next;
                }
            }
            else {
                if (largePart == null) {
                    largePart = current;
                    largeTemp = current;
                }
                else {
                    largeTemp.next = current;
                    largeTemp = largeTemp.next;
                }
            }
            current = current.next;
        }
        // The following part can be greatly improved by keeping the pivot in the smaller list
        if (smallTemp != null) smallTemp.next = null;
        if (largeTemp != null) largeTemp.next = null;

        smallPart = sort(smallPart);
        largePart = sort(largePart);

        if (smallPart != null) {
            head = smallPart;
            current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = list;
        }
        else head = list;

        list.next = largePart;

        return head;
    }
}


