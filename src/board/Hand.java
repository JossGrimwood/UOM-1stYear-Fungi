package board;

import java.util.ArrayList;

public class Hand implements Displayable{
    private ArrayList<cards.Card> handList = new ArrayList<cards.Card>();

    public void add(cards.Card Card){
        handList.add(Card);
    }

    public int size(){
        return handList.size();
    }

    public cards.Card getElementAt(int i){
        return handList.get(i);
    }

    public cards.Card removeElement(int i){
        return handList.remove(i);
    }
}
