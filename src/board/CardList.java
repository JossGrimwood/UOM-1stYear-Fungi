package board;

import java.util.ArrayList;

public class CardList {
    private ArrayList<cards.Card> cList;

    public CardList() {
        cList = new ArrayList<cards.Card>();
    }

    public void add(cards.Card card) {
        cList.add(0,card);
    }

    public int size() {
        return cList.size();
    }

    public cards.Card getElementAt(int i) {
        return cList.get(i);
    }

    public cards.Card removeCardAt(int i) {
        return cList.remove(8-i);
    }

}
