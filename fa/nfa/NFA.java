package fa.nfa;

import fa.State;
import fa.dfa.DFA;
import fa.dfa.DFAState;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.Deque;

/**
 * Implementation of NFA class
 * 
 * @author Aidan Leuck, Zachary Sherwood
 *
 */

public class NFA implements NFAInterface {
	// store start
	private NFAState startState;
	// store alpha
	private LinkedHashSet<Character> alphabet;
	// store states
	private LinkedHashSet<NFAState> states;

	public NFA() {
		// Instanitate all private variables at runtime

		alphabet = new LinkedHashSet<Character>();
		states = new LinkedHashSet<NFAState>();

	}

	@Override
	public void addStartState(String name) {
		startState = new NFAState(name);

		if (startState != null) {

			addState(startState);
		}

	}

	/**
	 * Checks if a state exists in the NFA
	 * 
	 * @param name - name of state to check
	 */
	private NFAState stateExist(String name) {
		for (NFAState s : states) {
			if (name.equals(s.getName())) {
				return s;

			}

		}
		return null;

	}

	@Override
	public void addState(String name) {
		if (name != null) {
			NFAState newState = new NFAState(name);
			addState(newState);
		}
	}

	/**
	 * Adds a state to the NFA by reference
	 * 
	 * @param name - NFA state to add
	 */
	public void addState(NFAState name) {
		states.add(name);
	}

	@Override
	public void addFinalState(String name) {
		// Check if already exists, if does throw error and exit
		if (stateExist(name) != null) {
			System.err.println("A state already exists for " + name);
			System.exit(2);
		}
		NFAState finalState = new NFAState(name);
		finalState.setFinal();
		states.add(finalState);

	}

	@Override
	public void addTransition(String fromState, char onSymb, String toState) {
		NFAState from = stateExist(fromState);
		NFAState to = stateExist(toState);
		if (from == null) {
			System.err.println("No state exists for " + fromState);
			System.exit(2);
		} else if (to == null) {
			System.err.println("No state exists for " + toState);
			System.exit(2);
		}
		if (!(onSymb == 'e')) {
			alphabet.add(onSymb);

		}

		from.addTransition(onSymb, to);

	}

	@Override
	public Set<? extends State> getStates() {
		return states;
	}

	@Override
	public Set<? extends State> getFinalStates() {
		Set<NFAState> finalStates = new LinkedHashSet<NFAState>();
		for (NFAState state : states) {
			if (state.isFinal()) {
				finalStates.add(state);
			}
		}
		return finalStates;

	}

	@Override
	public State getStartState() {
		return (State) startState;
	}

	@Override
	public Set<Character> getABC() {
		return alphabet;
	}

	@Override
	public DFA getDFA() {
		DFA dfa = new DFA();

		// instantiate queue for bfs
		Queue<Set<NFAState>> queue = new LinkedList<Set<NFAState>>();
		// Keep track of states that have already been visited
		HashSet<NFAState> visited = new HashSet<NFAState>();
		// Add the eClosure of the start state as the starting point for the BFS
		queue.add(eClosure(startState));

		// iterate through queue
		while (!queue.isEmpty()) {

			// first item
			Set<NFAState> current = queue.poll();
			// If we don't have a start state add the first item that was in the queue as
			// the start state
			if (dfa.getStartState() == null) {
				dfa.addStartState(current.toString());
			}
			// Iterate through every item in the alphabet
			for (Character a : this.alphabet) {
				// Keep track of transitions
				Set<NFAState> transitionSet = new HashSet<NFAState>();

				for (NFAState transition : current) {
					// Get the set of transitions that can be reached on a alphabet symbol in the
					// language
					Set<NFAState> dfaTransitions = transition.getTo(a);
					// If there is a path between the current state and alphabet symbol
					if (dfaTransitions != null) {
						for (NFAState dfaTransition : dfaTransitions) {
							// Make sure we haven't already visited the state
							if (!visited.contains(dfaTransition)) {
								transitionSet.addAll(eClosure(dfaTransition));
							}
						}
					}
				}
				// Keep track of certain parameters
				boolean hasState = false;
				boolean finalState = false;
				// Check if the state is already in the dfa
				for (DFAState dfaState : dfa.getStates()) {
					if (transitionSet.toString().equals(dfaState.getName())) {
						hasState = true;
					}
				}
				// If the state is not in the dfa we need to add it
				if (!hasState) {
					for (NFAState nfaState : transitionSet) {
						// Check if any of the states are final
						if (nfaState.isFinal()) {
							finalState = true;
						}
					}
					// If final add as a finalState
					if (finalState) {
						queue.add(transitionSet);
						dfa.addFinalState(transitionSet.toString());
						// If no states are final then just add as normal state
					} else {
						queue.add(transitionSet);
						dfa.addState(transitionSet.toString());
					}
				}
				// Add the transition from the current state to the state in the transitionSet
				dfa.addTransition(current.toString(), a, transitionSet.toString());
			}
		}
		return dfa;
	}

	@Override
	public Set<NFAState> getToState(NFAState from, char onSymb) {
		NFAState start = new NFAState(null);

		for (NFAState l : states) {
			if (from.getName().equals(l.getName())) {
				start = l;
				break;
			}
		}
		return start.getTo(onSymb);
	}

	@Override
	public Set<NFAState> eClosure(NFAState s) {
		Set<NFAState> visited = new LinkedHashSet<NFAState>();
		return DFS(s, visited);

	}

	/**
	 * Performs a depth first search on a specified NFAState
	 * 
	 * @param s             - state to start at
	 * @param visitedStates - Keeps track of what states have been already visited
	 * @return the set of NFAStates that are reachable by a DFS
	 */
	public Set<NFAState> DFS(NFAState s, Set<NFAState> visitedStates) {
		Set<NFAState> transitions = new LinkedHashSet<NFAState>();
		transitions.add(s);
		// If there is an empty transition and not visited
		if (s.getTo('e') != null && !visitedStates.contains(s)) {
			Set<NFAState> temp = new LinkedHashSet<NFAState>();
			temp.addAll(s.getTo('e'));
			visitedStates.add(s);
			// Go through each state that was reachable by e and recursively call function
			for (NFAState transition : temp) {
				transitions.addAll(DFS(transition, visitedStates));
			}
		}

		return transitions;

	}

}