package main;

import java.util.ArrayList;
import java.util.List;


public final class BasicPlayer extends Player {
    private static final int SIX = 6;
    public BasicPlayer(final int id) {
        super(id);
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
        return "BASIC: " + coins;
    }
}
