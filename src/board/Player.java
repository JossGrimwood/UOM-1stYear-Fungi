package board;

import java.util.ArrayList;

import cards.*;


/**
 * Player
 */
public class Player {
    private Hand h;
    private Display d;
    private int score, handlimit, sticks;

    public Player(){
        h = new Hand();
        Pan p = new Pan();
        d = new Display();
        d.add(p);
        score = 0;
        sticks = 0;
        handlimit = 8;
    }

    public int getScore() {
        return score;
    }

    public int getHandLimit() {
        return handlimit;
    }

    public int getStickNumber() {
        return sticks;
    }

    public void addSticks(int n) {
        sticks += n;
        Card s;
        for (int index = 0; index < n; index++) {
            s= new Stick();
            d.add(s);
        }
    }

    public void removeSticks(int n) {
        sticks -= n;
        int t = 0;
        for (int i = 0; i < d.size(); i++) {
            if (d.getElementAt(i).getType().equals(CardType.STICK)) {
                d.removeElement(i);
                t++;
                i--;
            }
            if (t == n) {
                break;
            }
        }
    }

    public Hand getHand() {
        return h;
    }

    public Display getDisplay() {
        return d;
    }

    public void addCardtoHand(Card c) {
        if (c.getType().equals(CardType.BASKET)) {
            handlimit += 2;
            d.add(c);
        } else {
            h.add(c);
        }
    }

    public void addCardtoDisplay(Card c) {
        d.add(c);
    }

    public boolean takeCardFromTheForest(int i) {
        if (h.size() == handlimit | i>8 | i <1 | (i > 2 & sticks < i - 2)) {
            return false;
        }
        CardList f = Board.getForest();
        Card c = f.removeCardAt(i);
        if (i > 2) {
            removeSticks(i-2);
        }
        if (c.getType().equals(CardType.BASKET)) {
            handlimit += 2;
            d.add(c);
        } else {
            h.add(c);
        }
        return true;
    }

    public boolean takeFromDecay() {
        ArrayList<Card> decay = Board.getDecayPile();
        if (decay.size() == 0) {
            return false;
        }
        int tempHandLimit = 0;
        for (Card c : decay) {
            if (c.getType().equals(CardType.BASKET)) {
                tempHandLimit += 2;
            }
        } 
        if ((decay.size() - (tempHandLimit / 2)) + h.size() > tempHandLimit + handlimit) {
            return false;
        } 
        handlimit += tempHandLimit;
        for (Card c : decay) {
            if (c.getType().equals(CardType.BASKET)) {
                d.add(c);
            }else{
                h.add(c);
            }
        }
        return true;
    }

    public boolean cookMushrooms(ArrayList<Card> c) {
        int pan, mushroom, cider, butter;
        String name = null;
        boolean displayPan = false;
        pan = 0;
        mushroom = 0;
        cider = 0;
        butter = 0;
        for (Card card : c) {
            if (card.getType().equals(CardType.PAN)) {
                pan +=1;
            } else if (card.getType().equals(CardType.DAYMUSHROOM)) {
                if (name == null) {
                    name = card.getName();
                } else if (!name.equals(card.getName())) {
                    return false;
                }
                mushroom += 1;
            } else if (card.getType().equals(CardType.NIGHTMUSHROOM)) {
                if (name == null) {
                    name = card.getName();
                } else if (!name.equals(card.getName())) {
                    return false;
                }
                mushroom += 2;
            } else if (card.getType().equals(CardType.CIDER)) {
                cider +=1;
            } else if (card.getType().equals(CardType.BUTTER)) {
                butter +=1;
            } else {
                return false;
            }
        }
        if (pan == 0) { // check for pans
            for (int i = 0; i < d.size(); i++) {
                if (d.getElementAt(i).getType().equals(CardType.PAN)) {
                    displayPan = true;
                    break;
                }
            }
            if (!displayPan) {
                return false;
            }
        }
        if (mushroom < 3) { // check min mushrooms
            return false;
        }
        if (mushroom < ((cider * 5) + (butter * 4))) { // calculate correct cider and butter
            return false;
        }
        if (displayPan) { //remove pan from display if exists
            for (int i = 0; i < d.size(); i++) {
                if (d.getElementAt(i).getType().equals(CardType.PAN)) {
                    d.removeElement(i);
                    break;
                }
            }
        }
        EdibleItem temp; //remove cards from hand
        for (Card card : c) {
            if (card.getType().equals(CardType.NIGHTMUSHROOM)) {
                temp = (EdibleItem) card;
                score += temp.getFlavourPoints() * 2;
            } else if (!card.getType().equals(CardType.PAN)) {
                temp = (EdibleItem) card;
                score += temp.getFlavourPoints();
            }
            for (int i = 0; i < h.size(); i++) {
                if (h.getElementAt(i).equals(card)) {
                    h.removeElement(i);
                    break;
                }
            }
        }
        return true;
    }

    public boolean sellMushrooms(String name, int n) {
        int total = 0;
        Mushroom c = null;
        name = name.replace(" ", "").toLowerCase().replace("'", "");
        if (n < 2) {
            return false;
        }
        for (int i = 0; i < h.size(); i++) {
            if (h.getElementAt(i).getName().equals(name) & h.getElementAt(i) instanceof Mushroom) {
                total++;
                c = (Mushroom) h.getElementAt(i);
                if (h.getElementAt(i).getType().equals(CardType.NIGHTMUSHROOM)) {
                    total++;
                }
            }
        }
        if (n > total) {
            return false;
        }
        addSticks(n * c.getSticksPerMushroom());
        for (int i = 0; i < h.size(); i++) {
            if (h.getElementAt(i).getType().equals(CardType.NIGHTMUSHROOM) & n >= 2) {
                n -= 2;
                h.removeElement(i);
                i--;
            }
        }
        for (int i = 0; i < h.size(); i++) {
            if (h.getElementAt(i).getType().equals(CardType.DAYMUSHROOM) & n >= 1) {
                n--;
                h.removeElement(i);
                i--;
            }
        }
        return true;
    }

    public boolean putPanDown() {
        for (int i = 0; i < h.size(); i++) {
            if (h.getElementAt(i).getType().equals(CardType.PAN)) {
                Card p;
                p = h.removeElement(i);
                d.add(p);
                return true;
            }
        }
        return false;
    }
}