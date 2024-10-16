package cards;

public class Mushroom extends EdibleItem{
    protected int sticksPerMushroom;

    public Mushroom(CardType type, String name){
        super(type, name);
        if (name.equals("honeyfungus") | name.equals("lawyerswig") | name.equals("henofwoods")) {
            sticksPerMushroom = 1;
        } else if (name.equals("treeear") | name.equals("chanterelle") | name.equals("shiitake") | name.equals("birchbolete")) {
            sticksPerMushroom = 2;
        } else if (name.equals("porcini")) {
            sticksPerMushroom = 3;
        } else if (name.equals("morel")) {
            sticksPerMushroom = 4;
        }
    }

    public int getSticksPerMushroom() {
        return sticksPerMushroom;
    }
}
