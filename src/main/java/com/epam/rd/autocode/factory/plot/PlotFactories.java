package com.epam.rd.autocode.factory.plot;

import java.util.Arrays;

class PlotFactories {

    public PlotFactory classicDisneyPlotFactory(Character hero, Character beloved, Character villain) {
        class ClassicDisneyPlot implements Plot {
            @Override
            public String toString() {
                return hero.name() + " is great. " + hero.name() + " and " + beloved.name() + " love each other. " + villain.name() + " interferes with their happiness but loses in the end.";
            }
        }
        return ClassicDisneyPlot::new;
    }

    public PlotFactory contemporaryDisneyPlotFactory(Character hero, EpicCrisis epicCrisis, Character funnyFriend) {
        class ClassContemporaryDisneyPlot implements Plot {
            @Override
            public String toString() {
                return hero.name() + " feels a bit awkward and uncomfortable. But personal issues fades, when a big trouble comes - " + epicCrisis.name() + ". " + hero.name() + " stands up against it, but with no success at first.But putting self together and help of friends, including spectacular funny " + funnyFriend.name() +" restore the spirit and "+hero.name()+" overcomes the crisis and gains gratitude and respect";
            }
        }
        return ClassContemporaryDisneyPlot::new;
    }

    public PlotFactory marvelPlotFactory(Character[] heroes, EpicCrisis epicCrisis, Character villain) {
        class ClassMarvelPlotFactory implements Plot {
            final Character[] listOfHeroes = Arrays.copyOf(heroes, heroes.length);

            @Override
            public String toString() {
                if (heroes.length == 1) {
                    return epicCrisis.name() + " threatens the world. But brave " + heroes[0].name() + " on guard. So, no way that intrigues of " + villain.name() + " overcome the willpower of inflexible heroes";
                } else if (heroes.length == 6) {
                    return epicCrisis.name() + " threatens the world. But brave " + listOfHeroes[0].name() + ", brave " + listOfHeroes[1].name() + ", brave " + listOfHeroes[2].name() + ", brave " + listOfHeroes[3].name() + ", brave " + listOfHeroes[4].name() + ", brave " + listOfHeroes[5].name() + " on guard. So, no way that intrigues of " + villain.name() + " overcome the willpower of inflexible heroes";
                } else if (heroes.length == 5) {
                    return epicCrisis.name() + " threatens the world. But brave " + listOfHeroes[0].name() + ", brave " + listOfHeroes[1].name() + ", brave " + listOfHeroes[2].name() + ", brave " + listOfHeroes[3].name() + ", brave " + listOfHeroes[4].name() + " on guard. So, no way that intrigues of " + villain.name() + " overcome the willpower of inflexible heroes";
                }
                return null;
            }
        }
        return ClassMarvelPlotFactory::new;
    }
}