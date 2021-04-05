# Project 2: NFA (Nondeterministic Finite Automata)

* Author: Aidan Leuck, Zach Sherwood
* Class: CS361 Section 002
* Semester: Spring 2021

## Overview

This program parses input given through a text file and outputs a description to the user of the language(States, Alphabet, Transitions, Start State and Final State) and strings that may or may not be included in the language.

## Compiling and Using

From the directory compile using: $javac fa/nfa/NFADriver.java

From the directory run the following command: $java fa/nfa/NFADriver {testfile}

testfiles included in the project are: p2tc0.txt, p2tc1.txt, p2tc2.txt, p2tc3.txt

The user will be given a text output via the console (terminal, IDE)

## Discussion

My partner and I had little problem setting up the environment and repo for the project (thanks to our previous experience with the first project) and had no issues scaffolding out the project. We began by creating the functions detailed in the interface classes, followed by pair programming out NFAState. Following the logic from the first project this part was relatively straight forward with little debugging involved. The next session together we agreed to pair-program the eClosure method before we attempted the getDFA method like the project specification advised. It was initially difficult to work through how eClosure would work without a way of testing but found that we were both presently surprised on it's successful implementation on the completion of getDfa(). eClosure was purposed as depth first search recursively calling itself to find the full extent a transition could be carried. We decided to abstract some of the logic out into a method (DFS) to help with its application. We found several resources to help us understand how we would implement it a DFS in (https://www.geeksforgeeks.org/depth-first-search-or-dfs-for-a-graph/). 

The most difficult part of this project was the implementation of the getDFA() method. We first researched breadth first searches (https://www.geeksforgeeks.org/breadth-first-search-or-bfs-for-a-graph/) to map out how we wanted to attack the method. Following, we began to pair-program out the logic after individually unsuccessfully making changes to the repo. This proved to be extremely beneficial and solved issues that both of us had been facing. One significant issue was getting the string of states to mirror the output given to us in the project specifications, which was solved by calling the toString() method instead of getName(). We found that different data structures to be paramount in helping grab the states correctly. With the biggest example involving changing our queue data structure to hold a set of NFA states instead of a singular NFA state. After a good amount of debugging and trying to successfully run the debugger on visual studio code, we were able to finish the project relatively quickly. One of the biggest take-aways from this project specifically for both of us was actively finding time to work together rather than separately. 

## Testing

we tested our program by visually verifying that the console output was correct by investigating the testfiles given and also by insuring that our output matched the sample output given in the project description.

