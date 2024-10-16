package board;

/**
 * Displayable
 */
public interface Displayable {

    public void add(cards.Card Card);

    public int size();

    public cards.Card getElementAt(int Int);

    public cards.Card removeElement(int Int);
}