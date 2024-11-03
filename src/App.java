import java.util.Arrays;
import java.util.Comparator;

public class App {

    // Bubble Sort
    public static <T> void bubbleSort(T[] array, Comparator<? super T> comparator) {
        int n = array.length;
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false; 
            for (int j = 0; j < n - 1 - i; j++) {
                if (comparator.compare(array[j], array[j + 1]) > 0) {
                    T temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true; 
                }
            }
            if (!swapped) {
                break; 
            }
        }
    }

    // Merge Sort
    public static <T> void mergeSort(T[] array, Comparator<? super T> comparator) {
        if (array.length < 2) {
            return;
        }

        int mid = array.length / 2;
        T[] left = Arrays.copyOfRange(array, 0, mid);
        T[] right = Arrays.copyOfRange(array, mid, array.length);

        mergeSort(left, comparator);
        mergeSort(right, comparator);
        merge(array, left, right, comparator);
    }
    private static <T> void merge(T[] array, T[] left, T[] right, Comparator<? super T> comparator) {
        int i = 0, j = 0, k = 0;

        // Merging left and right arrays
        while (i < left.length && j < right.length) {
            if (comparator.compare(left[i], right[j]) <= 0) {
                array[k++] = left[i++];
            } else {
                array[k++] = right[j++];
            }
        }
        while (i < left.length) {
            array[k++] = left[i++];
        }

        while (j < right.length) {
            array[k++] = right[j++];
        }
    }
    public static void main(String[] args) {
        Integer[] numbersBubble = {5, 3, 8, 4, 2};
        Integer[] numbersMerge = {5, 3, 8, 4, 2};

        // Bubble Sort
        System.out.println("Before Bubble Sort: " + Arrays.toString(numbersBubble));
        long startBubble = System.nanoTime();
        bubbleSort(numbersBubble, Comparator.naturalOrder());
        long endBubble = System.nanoTime();
        System.out.println("After Bubble Sort: " + Arrays.toString(numbersBubble));
        long durationBubble = endBubble - startBubble;
        System.out.println("Bubble Sort took: " + durationBubble + " nanoseconds");

        // Merge Sort
        System.out.println("\nBefore Merge Sort: " + Arrays.toString(numbersMerge));
        long startMerge = System.nanoTime();
        mergeSort(numbersMerge, Comparator.naturalOrder());
        long endMerge = System.nanoTime();
        System.out.println("After Merge Sort: " + Arrays.toString(numbersMerge));
        long durationMerge = endMerge - startMerge;
        System.out.println("Merge Sort took: " + durationMerge + " nanoseconds");
    }
}
