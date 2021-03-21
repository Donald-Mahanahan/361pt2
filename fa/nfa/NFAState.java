package fa.nfa;

import java.util.HashMap;

import fa.State;

public class NFAState extends State{
  private boolean isFinal;
  private HashMap<Character, NFAState> transitions;

  public NFAState(String name){
    super.name = name;
    isFinal = false;
    transitions = new HashMap<Character, NFAState>();
  }

  public void addTransition(char onSymb, NFAState to){
    transitions.put(onSymb, to);
  }
  public void setFinal(){
      this.isFinal = true;
  }
  public boolean isFinal(){
      return isFinal;
  }
  public NFAState getTo(char symb){
      NFAState returnState = transitions.get(symb);
      if(returnState == null){
          System.err.println("ERROR: no transition exists for symbol " + symb + "on state " + super.getName());
          System.exit(2);
      }
        return returnState;

      
  }
  
	
}