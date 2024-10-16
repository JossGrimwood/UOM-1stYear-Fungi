package board;

import java.util.Collections;
import java.util.Stack;

public class CardPile {
   private Stack<cards.Card> cPile;
   
   public CardPile(){
        cPile = new Stack<cards.Card>();
   }

   public void addCard(cards.Card card) {
       cPile.push(card);
   }

   public cards.Card drawCard() {
       return cPile.pop();
   }

   public void shufflePile() {
       Collections.shuffle(cPile);
   }

   public int pileSize() {
       return cPile.size();
   }

   public boolean isEmpty() {
       return cPile.empty();
   }

}
