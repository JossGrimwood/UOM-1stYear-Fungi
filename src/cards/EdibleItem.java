package cards;

public class EdibleItem extends Card{
    protected int flavourPoints;
    
    public EdibleItem(CardType type, String name){
        super(type, name);
        if (name.equals("honeyfungus") | name.equals("treeear")) {
            flavourPoints = 1;
        } else if (name.equals("lawyerswig") | name.equals("shiitake")) {
            flavourPoints = 2;
        } else if (name.equals("henofwoods") | name.equals("birchbolete") | name.equals("porcini") | name.equals("butter")) {
            flavourPoints = 3;
        } else if (name.equals("chanterelle")) {
            flavourPoints = 4;
        } else if (name.equals("cider")) {
            flavourPoints = 5;
        } else if (name.equals("morel")) {
            flavourPoints = 6;
        }
    }

    public int getFlavourPoints() {
        return flavourPoints;
    }
}
