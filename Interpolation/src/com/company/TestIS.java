package com.company;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;

// Name: JaDante Hendrick
// Class: CS 4306/4
// Term: Fall 2022
// Instructor: Dr. Haddad
// Assignment: 2

// ALGORITHM DESIGN BLOCK
/* Problem Statement:
    Make an array of 1024 numbers from 1 to 9999 and implement the interpolation search algorithm to search the array
    for randomly generated values. Return the values being searched for, whether they were found, the index they
    were found in and how many divisions it took to find them.
*/
/* Algorithm Logical Steps:

    Given a sorted array of 1024 integers and a key to search for:
        1: Set the left variable to 0 and the right variable to 1023
        2: Use the formula "left + ((key - values[left]) * (right - left)) / (values[right] - values[left])"
           to calculate the array index that needs to be checked for the key
            2.1: If that array value is the key the search is finished
            2.2: If it is greater than the key, the right variable is set to one less than the current index being
                 checked. This divides the array so the part before the current index is being searched
            2.3: If it is less than the key, the left variable is set to one more the current index being checked.
                 This divides the array so the part after the current index is being searched
        3. Step 2 repeats until...
            3.1: The key is found
            3.2: The key is less than the array value of left. Since the list is ordered this means the remaining values
                 being searched are greater than the key
            3.3: The key is greater than the array value of right. Since the list is ordered this means the remaining
                 values being searched are less than the key
        4. Repeat 2 and 3 for each key being searched for
        5. Print the results to the screen
*/
/* Algorithm Pseudocode Syntax:
    InterpolationSearch takes in int[] values and int key
    {
        boolean found <- false
        int index <- -1
        int divisions <- 0
        int left <- 0
        int right <- values.length - 1
        int pos

        while key is greater than values[left] and key less than values[right])
        {
            pos <- left + ((key - values[left]) * (right - left)) / (values[right] - values[left])

            if key equals values[pos]
            {
                found <- true
                index <- pos
                break loop
            }
            else if key is less than values[pos]
            {
                right <- pos - 1
            }
            else if key is greater than values[pos]
            {
                left <- pos + 1
            }

            divisions++
        }
    }
*/
// Implementation Section
public class TestIS {
    public static void main(String[] args) {
        int option = 0;
        int[] values = new int[1024];
        int tableSize = 0;
        Scanner scan = new Scanner(System.in);

        // Controls the main menu
        while(option != 4) {
            System.out.println("Main Menu");
            System.out.println("1. Create and display array values[]");
            System.out.println("2. Read output table size");
            System.out.println("3. Run algorithm and display outputs");
            System.out.println("4. Exit program");
            System.out.println("\nEnter option number:");

            option = scan.nextInt();

            if (option == 1) {
                values = RandomDistinct();
            } else if (option == 2) {
                System.out.println("Enter table size: ");
                tableSize = scan.nextInt();
                System.out.println("Table size set to " + tableSize);
            } else if (option == 3) {
                RunIS(values, tableSize);
            }

            System.out.println("\n");
        }
    }
    public static int[] RandomDistinct()
    {
        int[] values = new int[1024]; // array will contain random distinct numbers between 1 and 9999
        Random rnd = new Random();

        // Generates random, distinct ints in the array
        for(int i = 0; i < values.length; i++)
        {
            int val;
            do
            {
                val = rnd.nextInt(10000-1) + 1; // generate random number between 1 (inclusive) and 10000 (exclusive)
            }
            while(Arrays.asList(values).contains(val));

            values[i] = val;
        }

        // Sorts the array from lowest to highest
        Arrays.sort(values);

        int newLine = 0;

        // Displays the array of ints, 30 values per line
        for(int i : values)
        {
            System.out.print(i + "  ");
            newLine++;

            if(newLine == 30)
            {
                System.out.println();
                newLine = 0;
            }
        }

        return values;
    }
    public static void RunIS(int[] values, int tableSize)
    {
        float average = 0;
        float keyAmount = tableSize;

        // Creates table header
        String output = String.format("%-4s %-5s %-5s %-9s", "Key", "Found", "Index", "Divisions");
        System.out.println(output);
        System.out.println("-------------------------------------");

        // Generates random keys between 1-9999 to search for in the array
        // After the search is complete data is printed into the table
        // tableSize determines how many times this loop runs
        while(tableSize != 0)
        {
            Random rnd = new Random();
            int key = rnd.nextInt(10000-1) + 1; // generate random number between 1 (inclusive) and 10000 (exclusive)
            InterpolationSearch search = new InterpolationSearch(values, key);

            // formats the output
            String keyOutput = String.format("%-4d", key);
            String foundOutput = String.format("%-5b", search.found);
            String indexOutput = String.format("%-5d", search.index);
            String divisionsOutput = String.format("%-9d", search.divisions);
            System.out.println(keyOutput + "\t" + foundOutput + "\t" + indexOutput + "\t" + divisionsOutput);

            // Adds the amount of divisions every search does to average
            average += search.divisions;
            tableSize--;
        }

        // Calculate divisions average
        average = average / keyAmount;

        // lg(lg 1024) - average
        float difference = 3.322f - average;

        System.out.println("Divisions Average: " + average);
        System.out.println("Difference: " + difference);
    }
}
