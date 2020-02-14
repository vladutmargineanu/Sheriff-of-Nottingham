package main;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {
    private static final int FIFTY = 50;
    protected int id;
    protected int coins;
    protected List<Card> handAssets;
    protected List<Card> assetsInBag;
    protected Card declaredAssets;
    protected List<Card> finalAssets;
    protected int bribe;
    public Player(final int id) {
        this.id         = id;
        coins           = FIFTY;
        handAssets      = new ArrayList<Card>();
        finalAssets     = new ArrayList<Card>();
        assetsInBag     = new ArrayList<Card>();
        bribe           = 0;
    }
    public final List<Card> getHandAssets() {
        return handAssets;
    }
    public final void addCard(final Card card) {
        handAssets.add(card);
    }
    public final Card getDeclaredAssets() {
        return declaredAssets;
    }
    public final List<Card> getAssetsInBag() {
        return assetsInBag;
    }
    public abstract void putAssetsInBag();
    public final void subtractCoins(final int nr) {
        coins -= nr;
    }
    public final void addCoins(final int nr) {
        coins += nr;
    }
    public final void removeFromBag(final Card card) {
        assetsInBag.remove(card);
    }
    public abstract List<Card> beSheriff(List<Player> players);
    public final List<Card> checkBag(final Player player) {
        List<Card> confiscatedCards = new ArrayList<Card>();
        Card playerDeclaredAssets = player.getDeclaredAssets();
        boolean playerIsClean = true;
        for (int i = 0; i < player.getAssetsInBag().size(); i++) {
            Card card = player.getAssetsInBag().get(i);
            if (!playerDeclaredAssets.equals(card)) {
                playerIsClean = false;
                player.removeFromBag(card);
                i--;
                confiscatedCards.add(card);
                coins += card.getPenalty();
                player.subtractCoins(card.getPenalty());
            }
        }
        if (playerIsClean) {
            coins = coins - player.getAssetsInBag().size() * 2;
            player.addCoins(player.getAssetsInBag().size() * 2);
        }
        return confiscatedCards;
    }
    public final int getBribe() {
        return bribe;
    }
    public final int getCoins() {
        return coins;
    }
    public final void addToFinalAssets() {
        for (int i = 0; i < assetsInBag.size(); i++) {
            Card card = assetsInBag.get(i);
            assetsInBag.remove(card);
            --i;
            finalAssets.add(card);
            if (card.getType().equals("Illegal")) {
                List<Card> bonus = card.getBonus();
                for (Card bonusCard : bonus) {
                    finalAssets.add(bonusCard);
                }
            }
        }
    }
    public final void sellAssets() {
        for (Card card : finalAssets) {
            coins += card.getProfit();
        }
    }
    public final List<Card> getFinalAssets() {
        return finalAssets;
    }
    public final int getId() {
        return id;
    }
    public final boolean equals(final Player other) {
        return (id == other.getId());
    }
}
