package main;

import java.util.ArrayList;
import java.util.List;

public final class BribedPlayer extends Player {
    private static final int THREE = 3;
    private static final int FIVE = 5;
    private static final int TEN = 10;
    private static final int SIX = 6;
    public BribedPlayer(final int id) {
        super(id);
    }
    private int countIllegalCards() {
        int count = 0;
        for (Card card : handAssets) {
            if (card.getType().equals("Illegal")) {
                count++;
            }
        }
        return count;
    }
    private boolean allCardsAreIllegal() {
        for (Card asset : handAssets) {
            if (asset.getType().equals("Legal")) {
                return false;
            }
        }
        return true;
    }
    public void putAssetsInBag() {
        bribe = 0;
        int illegalCardCount = countIllegalCards();
        if (illegalCardCount > 0 && illegalCardCount < THREE && coins >= FIVE) {
            bribe = FIVE;
        }
        if (illegalCardCount > 2 && coins >= TEN) {
            bribe = TEN;
        }
        if (bribe == 0) {
            if (allCardsAreIllegal()) {
                Card maxCard = handAssets.get(0);
                int maxProfit = maxCard.getProfit();
                for (Card card: handAssets) {
                    if (card.getProfit() > maxProfit) {
                        maxCard = card;
                        maxProfit = card.getProfit();
                    }
                }
                handAssets.remove(maxCard);
                assetsInBag.add(maxCard);
                declaredAssets = new Card(0);
            } else {
                int maxCounts = 0;
                int maxProfit = 0;
                Card maxCard = handAssets.get(0);
                for (Card card1 : handAssets) {
                    int count = 0;
                    if (card1.getType().equals("Illegal")) {
                        continue;
                    }
                    for (Card card2 : handAssets) {
                        if (card1.equals(card2)) {
                            count++;
                        }
                    }
                    if ((count > maxCounts) || ((count == maxCounts
                            && card1.getProfit() > maxProfit))) {
                        maxCounts = count;
                        maxProfit = card1.getProfit();
                        maxCard = card1;
                    }
                }
                declaredAssets = maxCard;
                if (maxCounts == SIX) {
                    maxCounts--;
                }
                for (int i = 0; i < handAssets.size() && maxCounts > 0; i++) {
                    Card card = handAssets.get(i);
                    if (card.equals(declaredAssets)) {
                        handAssets.remove(card);
                        assetsInBag.add(card);
                        --i;
                        --maxCounts;
                    }
                }
            }
        } else {
            declaredAssets = new Card(0);
            if (illegalCardCount == SIX) {
                illegalCardCount--;
            }
            for (int i = 0; i < illegalCardCount; i++) {
                int maxProfit = 0;
                Card maxCard = handAssets.get(0);
                for (Card card : handAssets) {
                    if (card.getType().equals("Illegal") && card.getProfit() > maxProfit) {
                        maxCard = card;
                        maxProfit = card.getProfit();
                    }
                }
                handAssets.remove(maxCard);
                assetsInBag.add(maxCard);
            }
        }
    }
    public List<Card> beSheriff(final List<Player> players) {
        List<Card> confiscatedCards = new ArrayList<Card>();
        for (Player player : players) {
            List<Card> confiscatedCardsAux = checkBag(player);
            for (Card card : confiscatedCardsAux) {
                confiscatedCards.add(card);
            }
        }
        return confiscatedCards;
    }
    public String toString() {
        return "BRIBED: " + coins;
    }
}
