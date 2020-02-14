package main;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public final class Game {
    private static final int FOUR = 4;
    private static final int SIX = 6;
    private List<Player> players;
    private Queue<Card> cards;
    public Game(final GameInput gameInput) {
        players = new ArrayList<Player>();
        cards = new LinkedList<Card>();
        for (String name : gameInput.getPlayerNames()) {
            int id = 0;
            if (name.equals("basic")) {
                players.add(new BasicPlayer(id));
            } else if (name.equals("greedy")) {
                players.add(new GreedyPlayer(id));
            } else {
                players.add(new BribedPlayer(id));
            }
            id++;
        }
        for (int id : gameInput.getAssetIds()) {
            cards.add(new Card(id));
        }
    }
    private List<Player> getPlayersWithMost(final Card card) {
        int maxCount = -1;
        List<Player> maxPlayers = new ArrayList<Player>();
        for (Player player : players) {
            int count = 0;
            for (Card c : player.getFinalAssets()) {
                if (c.equals(card)) {
                    count++;
                }
            }
            if (count > maxCount) {
                maxCount = count;
            }
        }
        for (Player player : players) {
            int count = 0;
            for (Card c : player.getFinalAssets()) {
                if (c.equals(card)) {
                    count++;
                }
            }
            if (count == maxCount) {
                maxPlayers.add(player);
            }
        }
        return maxPlayers;
    }
    private List<Player> getSecondPlayersWithMost(final Card card) {
        int maxCount = -1;
        List<Player> maxPlayers = getPlayersWithMost(card);
        List<Player> secondPlayers = new ArrayList<Player>();
        for (Player player : players) {
            int count = 0;
            if (maxPlayers.contains(player)) {
                continue;
            }
            for (Card c : player.getFinalAssets()) {
                if (c.equals(card)) {
                    count++;
                }
            }
            if (count > maxCount) {
                maxCount = count;
            }
        }
        if (maxCount != -1) {
            for (Player player : players) {
                int count = 0;
                if (maxPlayers.contains(player)) {
                    continue;
                }
                for (Card c : player.getFinalAssets()) {
                    if (c.equals(card)) {
                        count++;
                    }
                }
                if (count == maxCount) {
                    secondPlayers.add(player);
                }
            }
        }
        return secondPlayers;
    }
    public void play() {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < players.size(); j++) {
                List<Player> toBeChecked =  new ArrayList<Player>();
                for (Player player : players) {
                    while (player.getHandAssets().size() < SIX) {
                        player.addCard(cards.poll());
                    }
                }
                for (int k = 0; k < players.size(); k++) {
                    if (k != j) {
                        players.get(k).putAssetsInBag();
                        toBeChecked.add(players.get(k));
                    }
                }
                List<Card> confiscatedCards = players.get(j).beSheriff(toBeChecked);
                for (Card card : confiscatedCards) {
                    cards.add(card);
                }
                for (Player player : toBeChecked) {
                    player.addToFinalAssets();
                }
            }
        }
        for (Player player : players) {
            player.sellAssets();
        }
        for (int i = 0; i < FOUR; i++) {
            Card card = new Card(i);
            List<Player> firstPlayers = getPlayersWithMost(card);
            List<Player> secondPlayers = getSecondPlayersWithMost(card);
            for (Player player : firstPlayers) {
                player.addCoins(card.getFinalFirstBonus());
            }
            for (Player player : secondPlayers) {
                player.addCoins(card.getFinalSecondBonus());
            }
        }
        players.sort(new PlayerComparator());
    }
    public void printOutput() {
        for (Player player : players) {
            System.out.println(player);
        }
    }
}
