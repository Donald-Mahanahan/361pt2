package fa.nfa;

import java.util.HashMap;
import java.util.HashSet;
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
        LinkedHashSet<NFAState> current = transitions.get(onSymb);
        if(current == null){
            current = new LinkedHashSet<NFAState>();
         
         
        }
        current.add(to);
        transitions.put(onSymb, current);
   
  }

  public HashMap<Character,LinkedHashSet<NFAState>> getTransition()
	{
		return transitions;
	}

  public void setFinal(){
      this.isFinal = true;
  }
  public boolean isFinal(){
      return isFinal;
  }
  public Set<NFAState> getTo(char symb){
      
      LinkedHashSet<NFAState> returnState = transitions.get(symb);
      return returnState;

      
  }
  
	
}