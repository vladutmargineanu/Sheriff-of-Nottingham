package main;

import java.util.ArrayList;
import java.util.List;

public final class Card {
    private static final int TWENTY = 20;
    private static final int FIFTEEN = 15;
    private static final int TEN = 10;
    private static final int FOUR = 4;
    private static final int THREE = 3;
    private static final int FIVE = 5;
    private static final int NINE = 9;
    private static final int ELEVEN = 11;
    private static final int EIGHT = 8;
    private static final int TWELVE = 12;
    private static final int SEVEN = 7;
    private int id;
    private int profit;
    private int penalty;
    private String asset;
    private String type;
    private int finalFirstBonus;
    private int finalSecondBonus;
    public Card(final int id) {
        this.id = id;
        switch (this.id) {
            case 0:
                this.asset   = "Apple";
                this.type    = "Legal";
                this.profit  = 2;
                this.penalty = 2;
                this.finalFirstBonus = TWENTY;
                this.finalSecondBonus = TEN;
                break;
            case 1:
                this.asset   = "Cheese";
                this.type    = "Legal";
                this.profit  = THREE;
                this.penalty = 2;
                this.finalFirstBonus = FIFTEEN;
                this.finalSecondBonus = TEN;
                break;
            case 2:
                this.asset   = "Bread";
                this.type    = "Legal";
                this.profit  = FOUR;
                this.penalty = 2;
                this.finalFirstBonus = FIFTEEN;
                this.finalSecondBonus = TEN;
                break;
            case THREE:
                this.asset   = "Chicken";
                this.type    = "Legal";
                this.profit  = FOUR;
                this.penalty = 2;
                this.finalFirstBonus = TEN;
                this.finalSecondBonus = FIVE;
                break;
            case TEN:
                this.asset   = "Silk";
                this.type    = "Illegal";
                this.profit  = NINE;
                this.penalty = FOUR;
                break;
            case ELEVEN:
                this.asset   = "Pepper";
                this.type    = "Illegal";
                this.profit  = EIGHT;
                this.penalty = FOUR;
                break;
            case TWELVE:
                this.asset   = "Barrel";
                this.type    = "Illegal";
                this.profit  = SEVEN;
                this.penalty = FOUR;
                break;
            default:
                break;
        }
    }
    public int getProfit() {
        return profit;
    }
    public int getId() {
        return id;
    }

    public int getPenalty() {
        return penalty;
    }


    public List<Card> getBonus() {
        List<Card> bonus = new ArrayList<Card>();
        switch (id) {
            case TEN:
                for (int i = 0; i < THREE; i++) {
                    bonus.add(new Card(1));
                }
                break;
            case ELEVEN:
                for (int i = 0; i < 2; i++) {
                    bonus.add(new Card(THREE));
                }
                break;
            case TWELVE:
                for (int i = 0; i < 2; i++) {
                    bonus.add(new Card(2));
                }
                break;
            default:
                break;
        }
        return bonus;
    }


    public String getAsset() {
        return asset;
    }


    public String getType() {
        return type;
    }
    public boolean equals(final Card other) {
        return (this.asset.equals(other.getAsset()));
    }

    public int getFinalFirstBonus() {
        return finalFirstBonus;
    }


    public int getFinalSecondBonus() {
        return finalSecondBonus;
    }


}
