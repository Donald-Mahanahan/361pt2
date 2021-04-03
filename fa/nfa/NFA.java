package fa.nfa;

import fa.State;
import fa.dfa.DFA;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.Deque;

/**
 * Implementation of DFA class to be used
 * in p1p2
 * @author elenasherman
 *
 */

public class NFA implements NFAInterface{
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
	private NFAState stateExist(String name){
		for (NFAState s: states) {
			if(name.equals(s.getName())){
				return s;
				
			}
			
		}
		return null;
		
	}

	@Override
	public void addState(String name) {
		if(name != null){
			NFAState newState = new NFAState(name);
            addState(newState);
		}	
	}

	public void addState(NFAState name) {
        states.add(name);
    }

	@Override
	public void addFinalState(String name) {
		if(stateExist(name) != null){
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
		if(from == null){
			System.err.println("No state exists for " + fromState);
			System.exit(2);
		}
		else if(to == null){
			System.err.println("No state exists for " + toState);
			System.exit(2);
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
			if(state.isFinal()){
				finalStates.add(state);
			}
		}
		return finalStates;
		
	}

	@Override
	public State getStartState() {
		// TODO Auto-generated method stub
		return (State) startState;
	}

	@Override
	public Set<Character> getABC() {
		// TODO Auto-generated method stub
		return alphabet;
	}

	@Override
	public DFA getDFA() {		
		DFA dfa = new DFA();

		//instantiate queue for bfs
		Queue<Set<NFAState>> queue = new LinkedList<Set<NFAState>>();

		HashSet<NFAState> NFAstate = new HashSet<NFAState>();

		NFAstate.add(this.startState);

		dfa.addStartState(NFAstate.toString());
		
		//iterate through queue
		while (!queue.isEmpty()) {
			
			// You should track inside getDFA() method whether a DFA state with the label(name) corresponding to the string 
			//representation of the NFA states has been created or not. 
			//Q of a DFA can be printed either as {[a] [a, b]}or as{[b, a] [a]}

			//first item
			Set<NFAState> current = queue.poll();

			for(Character a : getABC() ) {
				Set<NFAState> characterSet = new HashSet<NFAState>();
				for (NFAState b : current) {
					for (NFAState t : b.getTransition().get(a)) {
							characterSet.addAll(eClosure(t));
					}	
				}
			}

		}
		
		return dfa;
	}

	@Override
	public Set<NFAState> getToState(NFAState from, char onSymb) {
		NFAState start = new NFAState(null);

		for(NFAState l : states){
			if(from.getName().equals(l.getName())){
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
	public Set<NFAState> DFS(NFAState s, Set<NFAState> visitedStates){
		Set<NFAState> transitions = new LinkedHashSet<NFAState>();
		transitions.add(s);

		if(s.getTo('e') != null && !visitedStates.contains(s)){
			Set<NFAState> temp = new LinkedHashSet<NFAState>();
			temp.addAll(s.getTo('e'));
			visitedStates.add(s);
			for(NFAState transition : temp){
				transitions.addAll(DFS(transition, visitedStates));
			}
		}
		return transitions;
		
		
	}
	
    


}