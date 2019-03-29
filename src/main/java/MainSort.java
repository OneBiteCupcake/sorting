import com.google.common.base.Stopwatch;
import com.sun.xml.internal.bind.v2.model.annotation.Quick;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

public class MainSort {

    public static void main(String[] args) {
        /*generateRandomNumbers(100, "smallArray.txt");
        generateRandomNumbers(1000000, "mediumArray.txt");
        generateRandomNumbers(1000000000, "largeArray.txt");*/

        int[] smallArrayToSort = readFile("smallArray.txt");
        sort(smallArrayToSort);

        //int[] mediumArrayToSort = readFile("mediumArray.txt");
        //sort(mediumArrayToSort);

        //int[] largeArrayToSort = readFile("largeArray.txt");
        //sort(largeArrayToSort);
    }

    public static void sort(int[] arrayToSort) {
        final int[] copyOfOriginal = arrayToSort.clone();
        System.out.println("Array length: " + arrayToSort.length);
        //printArray(arrayToSort);

        InsertionSort is = new InsertionSort();
        Stopwatch timer = Stopwatch.createStarted();
        is.sort(arrayToSort);
        System.out.println("InsertionSort took: " + timer.stop());
        arrayToSort = copyOfOriginal;

        MergeSort ms = new MergeSort();
        timer = Stopwatch.createStarted();
        ms.sort(arrayToSort, 0, arrayToSort.length - 1);
        System.out.println("MergeSort took: " + timer.stop());
        arrayToSort = copyOfOriginal;

        QuickSort qs = new QuickSort();
        timer = Stopwatch.createStarted();
        qs.sort(arrayToSort, 0, arrayToSort.length - 1);
        System.out.println("QuickSort took: " + timer.stop());
        arrayToSort = copyOfOriginal;

        SelectionSort ss = new SelectionSort();
        timer = Stopwatch.createStarted();
        ss.sort(arrayToSort);
        System.out.println("SelectionSort took: " + timer.stop());

        timer = Stopwatch.createStarted();
        Arrays.sort(arrayToSort);
        System.out.println("Java default sort took: " + timer.stop());
    }

    public static void generateRandomNumbers(int size, String fileName) {
        try {
            Random rand = new Random(new Date().getTime());
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));

            for (int i = 0; i < size; i++) {
                writer.append(Integer.toString(rand.nextInt(Integer.MAX_VALUE)));

                if (i != size - 1) {
                    writer.append(',');
                }
            }

            writer.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static int[] readFile(String fileName) {
        String data = "";

        try {
            data = new String(Files.readAllBytes(Paths.get(fileName)));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return convertStringArrayToIntArray(data.split(","));
    }

    public static int[] convertStringArrayToIntArray(String[] stringArray) {
        int[] intArray = new int[stringArray.length];

        for (int i = 0; i < stringArray.length; i++) {
            intArray[i] = Integer.parseInt(stringArray[i]);
        }

        return intArray;
    }

    /* A utility function to print array of size n*/
    public static void printArray(int arr[]) {
        int n = arr.length;
        for (int i = 0; i < n; ++i)
            System.out.print(arr[i] + " ");

        System.out.println();
    }

}
