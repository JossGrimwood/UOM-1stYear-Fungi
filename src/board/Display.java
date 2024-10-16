package board;

import java.util.ArrayList;

public class Display implements Displayable{
    private ArrayList<cards.Card> displayList = new ArrayList<cards.Card>();

    public void add(cards.Card Card){
        displayList.add(Card);
    }

    public int size(){
        return displayList.size();
    }

    public cards.Card getElementAt(int i){
        return displayList.get(i);
    }

    public cards.Card removeElement(int i){
        return displayList.remove(i);
    }
}
