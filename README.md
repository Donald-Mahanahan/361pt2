# Project 1: DFA (Deterministic Finite Automata)

* Author: Aidan Leuck, Zach Sherwood
* Class: CS361 Section 002
* Semester: Spring 2021

## Overview

This program parses input given through a text file and outputs a description to the user of the langauge(States, Alphabet, Transitions, Start State and Final State) and strings that may or may not be included in the lanugage.

## Compiling and Using

From the directory compile using: $javac fa/dfa/DFADriver.java

From the directory run the following command: $java fa/dfa/DFADriver {testfile}

testfiles included in the project are: p1tc1.txt, p1tc2.txt, p1tc3.txt

The user will be given a text output via the console (terminal, IDE)

## Discussion

Initally, my partner and I had relatively little problems setting up the project or environment. We created a repo on Github and used Visual Studio Code for development and began researching different data structures (sets, maps and lists) before deciding on implementing an assortment of HashMaps and HashSets for storing and retrieving information. In the beginning stages of development, we pair programmed and discussed how we would like to format our project logically before determining that one member would focus on implementing the logic portion of the pass/fail (yes/no) test strings located in the text file and the other would work on giving a correct output to the user via the toString() method. In as far as issues encountered in giving a correct ouput, we ran into issues in replicating the sample output given in the project description. The ouput was correct but in incorrect ordering. After some debugging we were able to successfully determine that a linked hashset would be a more appropriate data structure when it appeared to give the same output as shown in the project description.

The most challenging portion of the project was trying to create a data structure in order to hold the transitions. It took a bit of thought and trial and error, but we finally decided that we would use a hashmap nested inside a hashmap. This would allow us to achieve something of the form state a -> 1 -> state b. Using this structure we were able to store and retrieve transitions based off the current state and given alphabet symbol. Another minor issue that we ran into was that when storing a hashmap with key DFAState, it would create a new entry into the first hashmap even if they had the same name. For example when adding a transition for state A we would have two entries, one for alphabet symbol one and alphabet symbol 2. This was not a good way to store the data as we needed one entry for state A to contain both transitions. Initially, I was confused on how to overcome this problem, but then I realized that the hashmap would be using the equals method in order to determine whether to create a new entry or add to an existing entry. By default, the equals method checks for object equality (are both instances of the class referencing the same object in memory). Because of this I decided to override the equals method for the DFAState class. I changed both the equals method and the hashcode of the class to be derived from the equality of the name member variable. Once the equals method was overriden everything started working as expected.

## Testing

we tested our program by visually verifying that the console output was correct by investigating the testfiles given and also by insuring that our output matched the sample output given in the project description.


