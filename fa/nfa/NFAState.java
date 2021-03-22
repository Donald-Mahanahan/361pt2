package fa.nfa;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;

import fa.State;
import fa.dfa.DFAState;

public class NFAState extends State{
  private boolean isFinal;
  private HashMap<Character, LinkedHashSet<NFAState>> transitions;

  public NFAState(String name){
    super.name = name;
    isFinal = false;
    transitions = new HashMap<Character, LinkedHashSet<NFAState>>();
  }

  public void addTransition(char onSymb, NFAState to){
    
  }
  public void setFinal(){
      this.isFinal = true;
  }
  public boolean isFinal(){
      return isFinal;
  }
  public Set<NFAState> getTo(char symb){
      Set<NFAState> returnState = transitions.get(symb);
      if(returnState == null){
          System.err.println("ERROR: no transition exists for symbol " + symb + "on state " + super.getName());
          System.exit(2);
      }
        return returnState;

      
  }
  
	
}