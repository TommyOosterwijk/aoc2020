package days.day7;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Bag {

    String bagColor = "";
    Map<String, Integer> bagContainer = new HashMap<String, Integer>();

    Boolean hasGold = false;

    public void addBag(String bagName, int amount){
        bagContainer.put(bagName, amount);
    }

    public Map<String, Integer> getBagContainer () {
        return this.bagContainer;
    }

    public boolean hasBagThatContainsGold(String bagName) {
        if(!hasGold) {
            hasGold = bagContainer.containsKey(bagName);
        }
        return bagContainer.containsKey(bagName);
    }

    public void setBagColor(String bagColor) {
        this.bagColor = bagColor;
    }

    public String getBagColor() {
        return this.bagColor;
    }

    public Boolean hasGold() {
        return hasGold;
    }
}
