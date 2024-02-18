package com.epam.rd.autocode.startegy.cards;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CardDealingStrategies {
    public static CardDealingStrategy texasHoldemCardDealingStrategy() {
        return (deck, players) -> {
            Map<String, List<Card>> cardStacks = new HashMap<>();

            // Deal 2 cards to each player
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < players; j++) {
                    List<Card> playerCards = new ArrayList<>();
                    String playerName = "Player " + (j + 1);
                    if (cardStacks.containsKey(playerName)) {
                        playerCards.addAll(cardStacks.get(playerName));
                    }
                    playerCards.add(deck.dealCard());
                    cardStacks.put(playerName, playerCards);
                }
            }

            // Deal 5 cards to the community stack
            List<Card> communityCards = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                communityCards.add(deck.dealCard());
            }

            cardStacks.put("Community", communityCards);

            List<Card> remainingCards = new ArrayList<>(deck.restCards());

            cardStacks.put("Remaining", remainingCards);

            return cardStacks;
        };
    }

    public static CardDealingStrategy classicPokerCardDealingStrategy() {
        return (deck, players) -> {
            Map<String, List<Card>> cardStacks = new HashMap<>();
//            deal 5 cards to each player
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < players; j++) {
                    List<Card> playerCards = new ArrayList<>();
                    String playerName = "Player " + (j + 1);
                    if (cardStacks.containsKey(playerName)) {
                        playerCards.addAll(cardStacks.get(playerName));
                    }
                    playerCards.add(deck.dealCard());
                    cardStacks.put(playerName, playerCards);
                }
            }
            List<Card> remainingCards = new ArrayList<>(deck.restCards());

            cardStacks.put("Remaining", remainingCards);

            return cardStacks;
        };
    }

    public static CardDealingStrategy bridgeCardDealingStrategy() {
        return (deck, players) -> {
            Map<String, List<Card>> cardStacks = new HashMap<>();
//            deal 13 cards to each player
            for (int i = 0; i < 13; i++) {
                for (int j = 0; j < players; j++) {
                    List<Card> playerCards = new ArrayList<>();
                    String playerName = "Player " + (j + 1);
                    if (cardStacks.containsKey(playerName)) {
                        playerCards.addAll(cardStacks.get(playerName));
                    }
                    playerCards.add(deck.dealCard());
                    cardStacks.put(playerName, playerCards);
                }
            }
            return cardStacks;
        };
    }

    public static CardDealingStrategy foolCardDealingStrategy() {
        return (deck, players) -> {
            Map<String, List<Card>> cardStacks = new HashMap<>();

            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < players; j++) {
                    List<Card> playerCards = new ArrayList<>();
                    String playerName = "Player " + (j + 1);
                    if (cardStacks.containsKey(playerName)) {
                        playerCards.addAll(cardStacks.get(playerName));
                    }
                    playerCards.add(deck.dealCard());
                    cardStacks.put(playerName, playerCards);
                }
            }
            List<Card> trumpCard = new ArrayList<>();
            trumpCard.add(deck.dealCard());
            cardStacks.put("Trump card", trumpCard);

            List<Card> remainingCards = new ArrayList<>(deck.restCards());

            cardStacks.put("Remaining", remainingCards);
            return cardStacks;
        };
    }

}