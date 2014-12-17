/*
Brandon Pauly
CSC 321
Programming Assignment

*** TEXT FILES INCLUDED IN DATA ***
*** RUNNABLE AS IS ***
*/


package paulysorting;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Brandon Pauly
 */
public class PaulySorting {
    public static int[] randMilli, reverseMilli, sortedMilli, randHundred, reverseHundred, sortedHundred, randTen, reverseTen, sortedTen;
    private static long comps, ops;
    
    public static void constructArrays() throws FileNotFoundException{
        Scanner scanner1 = new Scanner(new File("data/1000_Random.txt"));
        Scanner scanner2 = new Scanner(new File("data/1000_Reverse.txt"));
        Scanner scanner3 = new Scanner(new File("data/1000_Sorted.txt"));
        randMilli = new int[1000];
        reverseMilli = new int[1000];
        sortedMilli = new int[1000];
        int i = 0;
        while (scanner1.hasNextInt()){
            randMilli[i] = scanner1.nextInt();
            reverseMilli[i] = scanner2.nextInt();
            sortedMilli[i++] = scanner3.nextInt();
        }
        
        Scanner scanner4 = new Scanner(new File("data/100_Random.txt"));
        Scanner scanner5 = new Scanner(new File("data/100_Reverse.txt"));
        Scanner scanner6 = new Scanner(new File("data/100_Sorted.txt"));
        randHundred = new int[100];
        reverseHundred = new int[100];
        sortedHundred = new int[100];
        i = 0;
        while (scanner4.hasNextInt()){
            randHundred[i] = scanner4.nextInt();
            reverseHundred[i] = scanner5.nextInt();
            sortedHundred[i++] = scanner6.nextInt();
        }
        
        Scanner scanner7 = new Scanner(new File("data/10_Random.txt"));
        Scanner scanner8 = new Scanner(new File("data/10_Reverse.txt"));
        Scanner scanner9 = new Scanner(new File("data/10_Sorted.txt"));
        randTen = new int[10];
        reverseTen = new int[10];
        sortedTen = new int[10];
        i = 0;
        while (scanner7.hasNextInt()){
            randTen[i] = scanner7.nextInt();
            reverseTen[i] = scanner8.nextInt();
            sortedTen[i++] = scanner9.nextInt();
        }
    }
    
    public static void insertionSort(int[] A){
        comps = 0;
        ops = 0;
        boolean wasInLoop = false;
        int n = A.length-1; ops++;
        for (int i = 1; i <= n; i++){
            int val = A[i]; ops++;
            int j = i-1; ops++;
            if(!wasInLoop){
                comps++;
            }
            wasInLoop = false;
            while (j >= 0 && val < A[j]){
                ops += 2;
                A[j+1] = A[j]; ops++;
                j--; ops++;
                comps++;
                wasInLoop = true;
            }
            A[j+1] = val; ops++;
        }
        if (wasInLoop){
            comps--;
        }
    }
    
    public static void callMergeSort(int[] A, int i, int j){
        comps = 0;
        ops = 0;
        mergeSort(A, i, j);
    }
    private static void mergeSort(int A[], int i, int j){
        if (i < j){ ops++;
            int m = (i + j)/2; ops++;
            mergeSort(A, i, m);
            mergeSort(A, m+1, j);
            merge(A, i, m, j);
        }
    }
    private static void merge(int A[], int i, int m, int j){
        int p = i; ops++;
        int q = m + 1; ops++;
        int r = i; ops++;
        int C[] = new int[A.length];
        while (p <= m && q <= j){
            ops += 2;
            comps++;
            if (A[p] <= A[q]){
                ops++;
                C[r]= A[p]; ops++;
                p++; ops++;
            }
            else{
                C[r] = A[q]; ops++;
                q++; ops++;
            }
            r++; ops++;
        }
        while (p <= m){
            ops++;
            C[r] = A[p]; ops++;
            p++; ops++;
            r++; ops++;
        }
        while (q <= j){
            ops++;
            C[r] = A[q]; ops++;
            q++; ops++;
            r++; ops++;
        }
        for (r = i; r <= j; r++){
            A[r] = C[r]; ops++;
        }
    }
    
    public static void callQuickSort(int[] A, int i, int j){
        ops = 0;
        comps = 0;
        quickSort(A, i, j);
    }
    private static void quickSort(int[] A, int i, int j){
        if (i < j){
            ops++;
            int p = partition(A, i, j); ops++;
            quickSort(A, i, p-1);
            quickSort(A, p+1, j);
        }
    }
    private static int partition(int[] A, int i, int j){
        int val = A[i]; ops++;
        int h = i; ops++;
        for (int k = i+1; k <= j; k++){
            comps++;
            if (A[k] < val){ ops++;
                h++; ops++;
                swap(A, h, k);
            }
        }
        swap(A, i, h);
        return h;
    }
    private static void swap(int A[], int a, int b) {
        int temp = A[a]; ops++;
        A[a] = A[b]; ops++;
        A[b] = temp; ops++;
    }

    public static void main(String[] args) throws FileNotFoundException {
        
        constructArrays();
        System.out.println("Insertion sort running on 10 randomly arranged integers...");
        insertionSort(randTen);
        System.out.println("Insertion sort on 10 randomly arranged integers:\n" + Arrays.toString(randTen));
        System.out.printf("Insertion sort on 10 randomly arranged integers did %d operations and %d comparisons.\n\n", ops, comps);
        
        constructArrays();
        System.out.println("Merge sort running on 10 randomly arranged integers...");
        callMergeSort(randTen, 0, 9);
        System.out.println("Merge sort on 10 randomly arranged integers:\n" + Arrays.toString(randTen));
        System.out.printf("Merge sort on 10 randomly arranged integers did %d operations and %d comparisons.\n\n", ops, comps);
        
        constructArrays();
        System.out.println("Quick sort running on 10 randomly arranged integers...");
        callQuickSort(randTen, 0, 9);
        System.out.println("Quick sort on 10 randomly arranged integers:\n" + Arrays.toString(randTen));
        System.out.printf("Quick sort on 10 randomly arranged integers did %d operations and %d comparisons.\n\n", ops, comps);
        
        constructArrays();
        System.out.println("Insertion sort running on 10 integers arranged in decreasing order...");
        insertionSort(reverseTen);
        System.out.println("Insertion sort on 10 integers arranged in reverse order:\n" + Arrays.toString(reverseTen));
        System.out.printf("Insertion sort on 10 integers arranged in reverse order did %d operations and %d comparisons.\n\n", ops, comps);
        
        constructArrays();
        System.out.println("Merge sort running on 10 integers arranged in decreasing order...");
        callMergeSort(reverseTen, 0, 9);
        System.out.println("Merge sort on 10 integers arranged in reverse order:\n" + Arrays.toString(reverseTen));
        System.out.printf("Merge sort on 10 integers arranged in reverse order did %d operations and %d comparisons.\n\n", ops, comps);
        
        constructArrays();
        System.out.println("Quick sort running on 10 integers arranged in decreasing order...");
        callQuickSort(reverseTen, 0, 9);
        System.out.println("Quick sort on 10 integers arranged in reverse order:\n" + Arrays.toString(reverseTen));
        System.out.printf("Quick sort on 10 integers arranged in reverse order did %d operations and %d comparisons.\n\n", ops, comps);
        
        constructArrays();
        System.out.println("Insertion sort running on 10 integers arranged in sorted order...");
        insertionSort(sortedTen);
        System.out.println("Insertion sort on 10 integers arranged in sorted order:\n" + Arrays.toString(sortedTen));
        System.out.printf("Insertion sort on 10 integers arranged in sorted order did %d operations and %d comparisons.\n\n", ops, comps);
        
        constructArrays();
        System.out.println("Merge sort running on 10 integers in sorted order...");
        callMergeSort(sortedTen, 0, 9);
        System.out.println("Merge sort on 10 integers arranged in sorted order:\n" + Arrays.toString(sortedTen));
        System.out.printf("Merge sort on 10 integers arranged in sorted order did %d operations and %d comparisons.\n\n", ops, comps);
        
        constructArrays();
        System.out.println("Quick sort running on 10 integers arranged in sorted order...");
        callQuickSort(sortedTen, 0, 9);
        System.out.println("Quick sort on 10 integers arranged in sorted order:\n" + Arrays.toString(sortedTen));
        System.out.printf("Quick sort on 10 integers arranged in sorted order did %d operations and %d comparisons.\n\n", ops, comps);
        
        constructArrays();
        System.out.println("Insertion sort running on 100 randomly arranged integers...");
        insertionSort(randHundred);
        System.out.println("Insertion sort on 100 randomly arranged integers:\n" + Arrays.toString(randHundred));
        System.out.printf("Insertion sort on 100 randomly arranged integers did %d operations and %d comparisons.\n\n", ops, comps);
        
        constructArrays();
        System.out.println("Merge sort running on 100 randomly arranged integers...");
        callMergeSort(randHundred, 0, 99);
        System.out.println("Merge sort on 100 randomly arranged integers:\n" + Arrays.toString(randHundred));
        System.out.printf("Merge sort on 100 randomly arranged integers did %d operations and %d comparisons.\n\n", ops, comps);
        
        constructArrays();
        System.out.println("Quick sort running on 100 randomly arranged integers...");
        callQuickSort(randHundred, 0, 99);
        System.out.println("Quick sort on 100 randomly arranged integers:\n" + Arrays.toString(randHundred));
        System.out.printf("Quick sort on 100 randomly arranged integers did %d operations and %d comparisons.\n\n", ops, comps);
        
        constructArrays();
        System.out.println("Insertion sort running on 100 integers arranged in decreasing order...");
        insertionSort(reverseHundred);
        System.out.println("Insertion sort on 100 integers arranged in reverse order:\n" + Arrays.toString(reverseHundred));
        System.out.printf("Insertion sort on 100 integers arranged in reverse order did %d operations and %d comparisons.\n\n", ops, comps);
        
        constructArrays();
        System.out.println("Merge sort running on 100 integers arranged in decreasing order...");
        callMergeSort(reverseHundred, 0, 99);
        System.out.println("Merge sort on 100 integers arranged in reverse order:\n" + Arrays.toString(reverseHundred));
        System.out.printf("Merge sort on 100 integers arranged in reverse order did %d operations and %d comparisons.\n\n", ops, comps);

        constructArrays();
        System.out.println("Quick sort running on 100 integers arranged in decreasing order...");
        callQuickSort(reverseHundred, 0, 99);
        System.out.println("Quick sort on 100 integers arranged in reverse order:\n" + Arrays.toString(reverseHundred));
        System.out.printf("Quick sort on 100 integers arranged in reverse order did %d operations and %d comparisons.\n\n", ops, comps);
        
        constructArrays();
        System.out.println("Insertion sort running on 100 integers arranged in sorted order...");
        insertionSort(sortedHundred);
        System.out.println("Insertion sort on 100 integers arranged in sorted order:\n" + Arrays.toString(sortedHundred));
        System.out.printf("Insertion sort on 100 integers arranged in sorted order did %d operations and %d comparisons.\n\n", ops, comps);
        
        constructArrays();
        System.out.println("Merge sort running on 100 integers in sorted order...");
        callMergeSort(sortedHundred, 0, 99);
        System.out.println("Merge sort on 100 integers arranged in sorted order:\n" + Arrays.toString(sortedHundred));
        System.out.printf("Merge sort on 100 integers arranged in sorted order did %d operations and %d comparisons.\n\n", ops, comps);
        
        constructArrays();
        System.out.println("Quick sort running on 100 integers in sorted order...");
        callQuickSort(sortedHundred, 0, 99);
        System.out.println("Quick sort on 100 integers arranged in sorted order:\n" + Arrays.toString(sortedHundred));
        System.out.printf("Quick sort on 100 integers arranged in sorted order did %d operations and %d comparisons.\n\n", ops, comps);
        
        constructArrays();
        System.out.println("Insertion sort running on 1000 randomly arranged integers...");
        insertionSort(randMilli);
        System.out.println("Insertion sort on 1000 randomly arranged integers:\n" + Arrays.toString(randMilli));
        System.out.printf("Insertion sort on 1000 randomly arranged integers did %d operations and %d comparisons.\n\n", ops, comps);
        
        constructArrays();
        System.out.println("Merge sort running on 1000 randomly arranged integers...");
        callMergeSort(randMilli, 0, 999);
        System.out.println("Merge sort on 1000 randomly arranged integers:\n" + Arrays.toString(randMilli));
        System.out.printf("Merge sort on 1000 randomly arranged integers did %d operations and %d comparisons.\n\n", ops, comps);
        
        constructArrays();
        System.out.println("Quick sort running on 1000 randomly arranged integers...");
        callQuickSort(randMilli, 0, 999);
        System.out.println("Quick sort on 1000 randomly arranged integers:\n" + Arrays.toString(randMilli));
        System.out.printf("Quick sort on 1000 randomly arranged integers did %d operations and %d comparisons.\n\n", ops, comps);
        
        constructArrays();
        System.out.println("Insertion sort running on 1000 integers arranged in decreasing order...");
        insertionSort(reverseMilli);
        System.out.println("Insertion sort on 1000 integers arranged in reverse order:\n" + Arrays.toString(reverseMilli));
        System.out.printf("Insertion sort on 1000 integers arranged in reverse order did %d operations and %d comparisons.\n\n", ops, comps);
        
        constructArrays();
        System.out.println("Merge sort running on 1000 integers arranged in decreasing order...");
        callMergeSort(reverseMilli, 0, 999);
        System.out.println("Merge sort on 1000 integers arranged in reverse order:\n" + Arrays.toString(reverseMilli));
        System.out.printf("Merge sort on 1000 integers arranged in reverse order did %d operations and %d comparisons.\n\n", ops, comps);
        
        constructArrays();
        System.out.println("Quick sort running on 1000 integers arranged in decreasing order...");
        callQuickSort(reverseMilli, 0, 999);
        System.out.println("Quick sort on 1000 integers arranged in reverse order:\n" + Arrays.toString(reverseMilli));
        System.out.printf("Quick sort on 1000 integers arranged in reverse order did %d operations and %d comparisons.\n\n", ops, comps);
        
        constructArrays();
        System.out.println("Insertion sort running on 1000 integers arranged in sorted order...");
        insertionSort(sortedMilli);
        System.out.println("Insertion sort on 1000 integers arranged in sorted order:\n" + Arrays.toString(sortedMilli));
        System.out.printf("Insertion sort on 1000 integers arranged in sorted order did %d operations and %d comparisons.\n\n", ops, comps);
        
        constructArrays();
        System.out.println("Merge sort running on 1000 integers in sorted order...");
        callMergeSort(sortedMilli, 0, 999);
        System.out.println("Merge sort on 1000 integers arranged in sorted order:\n" + Arrays.toString(sortedMilli));
        System.out.printf("Merge sort on 1000 integers arranged in sorted order did %d operations and %d comparisons.\n\n", ops, comps);
        
        constructArrays();
        System.out.println("Quick sort running on 1000 integers in sorted order...");
        callQuickSort(sortedMilli, 0, 999);
        System.out.println("Quick sort on 1000 integers arranged in sorted order:\n" + Arrays.toString(sortedMilli));
        System.out.printf("Quick sort on 1000 integers arranged in sorted order did %d operations and %d comparisons.\n\n", ops, comps);
    }
    
}
