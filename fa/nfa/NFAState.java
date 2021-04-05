package fa.nfa;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import fa.State;
import fa.dfa.DFAState;

/**
 * Implements a NFAState object.
 * 
 * @author Aidan Leuck, Zach Sherwood
 */
public class NFAState extends State {
  private boolean isFinal; // Keeps track if the class is final
  private HashMap<Character, LinkedHashSet<NFAState>> transitions; // Keeps track of transitions to other states

  /**
   * Constructor for NFAState, instantiates variables at creation
   * 
   * @param name - name of the NFAState
   */
  public NFAState(String name) {
    super.name = name;
    isFinal = false;
    transitions = new HashMap<Character, LinkedHashSet<NFAState>>();
  }

  /**
   * Adds a transition to the transition set
   * 
   * @param onSymb - Symbol to transition on
   * @param to     - Which state to transition to
   */
  public void addTransition(char onSymb, NFAState to) {
    LinkedHashSet<NFAState> current = transitions.get(onSymb);
    if (current == null) {
      current = new LinkedHashSet<NFAState>();

    }
    current.add(to);
    transitions.put(onSymb, current);

  }

  /**
   * 
   * @return - The transitions for a particular state
   */
  public HashMap<Character, LinkedHashSet<NFAState>> getTransition() {
    return transitions;
  }

  /**
   * Sets a state as final
   */
  public void setFinal() {
    this.isFinal = true;
  }

  /**
   * 
   * @return true if state is final, false otherwise
   */
  public boolean isFinal() {
    return isFinal;
  }

  /**
   * Returns possible paths from this state to another on a certain character
   * 
   * @param symb - character to transition from
   * @return - The set of states that can be reached on the symbol
   */
  public Set<NFAState> getTo(char symb) {

    LinkedHashSet<NFAState> returnState = transitions.get(symb);
    return returnState;

  }

}