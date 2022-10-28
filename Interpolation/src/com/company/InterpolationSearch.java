package com.company;

// Name: JaDante Hendrick
// Class: CS 4306/4
// Term: Fall 2022
// Instructor: Dr. Haddad
// Assignment: 2

// ALGORITHM DESIGN BLOCK

/* Problem Statement:
    Make an array of 1024 numbers from 1 to 9999 and implement the interpolation search algorithm to search the array
    for randomly generated values. Return the values being searched for, whether or not they were found, the index they
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
            2.3: If it is less than the key, the left variable is set to one more the the current index being checked.
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
public class InterpolationSearch {
    public boolean found; // indicates if key value is found or not
    public int index; // index of target value in list. value isn't in list -1 returned
    public int divisions; // number of divisions performed to find the value

    public InterpolationSearch(int[] values, int key)
    {
        found = false;
        index = -1;
        divisions = 0;
        int left = 0;
        int right = values.length - 1;
        int pos;

        // The formula for estimating the position of the key using interpolation search is:
        // left + ((key - values[left]) * (right - left)) / (values[right] - values[left])
        // if the element at the position resulting from that formula is equal to the key the search stops
        // if it is greater than the key the search continues with the first part
        // if it is less than the key the search continues with the second part
        while (key >= values[left] && key <= values[right])
        {
            pos = left + ((key - values[left]) * (right - left)) / (values[right] - values[left]);

            if(key == values[pos])
            {
                found = true;
                index = pos;
                break;
            }
            else if(key < values[pos])
            {
                right = pos - 1;
            }
            else if(key > values[pos])
            {
                left = pos + 1;
            }

            divisions++;
        }
    }
}

