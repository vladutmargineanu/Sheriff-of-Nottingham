package main;

import java.util.ArrayList;
import java.util.List;

public final class GreedyPlayer extends Player {
    private static final int SIX = 6;
    private static final int FIVE = 5;
    private int roundId = 0;
    public GreedyPlayer(final int id) {
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
        roundId++;
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
            if (roundId % 2 == 0 && assetsInBag.size() < FIVE) {
                maxProfit = 0;
                for (Card card : handAssets) {
                    if (card.getType().equals("Illegal") && maxProfit < card.getProfit()) {
                        maxProfit = card.getProfit();
                        maxCard = card;
                    }
                }
                if (maxProfit > 0) {
                    assetsInBag.add(maxCard);
                    handAssets.remove(maxCard);
                }
            }
        }
    }
    public List<Card> beSheriff(final List<Player> players) {
        List<Card> confiscatedCards = new ArrayList<Card>();
        for (Player player : players) {
            List<Card> confiscatedCardsAux;
            if (player.getBribe() > 0) {
                coins += player.getBribe();
                player.subtractCoins(player.getBribe());
                confiscatedCardsAux = new ArrayList<Card>();
            } else {
                confiscatedCardsAux = checkBag(player);
            }
            for (Card card : confiscatedCardsAux) {
                confiscatedCards.add(card);
            }
        }
        return confiscatedCards;
    }
    public String toString() {
        return "GREEDY: " + coins;
    }
}
