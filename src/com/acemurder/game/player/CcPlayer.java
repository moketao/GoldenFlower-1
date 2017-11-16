package com.acemurder.game.player;

import com.acemurder.game.Manager;
import com.acemurder.game.Player;
import com.acemurder.game.Poker;

import java.util.Collections;
import java.util.List;

    public class CcPlayer implements Player {
        @Override
        public String getName() {
            return "陈材";
        }

        @Override
        public String getStuNum() {
            return "2016210515";
        }

        @Override
        public void onGameStart(Manager manager, int totalPlayer) {

        }

        @Override
        public int bet(int time, int round, int lastPerson, int moneyOnDesk, int moneyYouNeedToPayLeast, List<Poker> pokers) {
            Collections.sort(pokers);
            if (isSameColor(pokers) )
                return moneyYouNeedToPayLeast * 3;
            if ( (isSameColorStraight(pokers) || isSamePoint(pokers))  )
                return moneyYouNeedToPayLeast * 3;
            if (isPair(pokers)&&moneyYouNeedToPayLeast<1000)
                return moneyYouNeedToPayLeast;
            if (isStraight(pokers)&&moneyYouNeedToPayLeast<3000)
                return moneyYouNeedToPayLeast;
            for (Poker p : pokers){
                if ( p.getPoint().getNum() >= 11&&round < 1)
                    return moneyYouNeedToPayLeast;
            }
            return 0;
        }

        @Override
        public void onResult(int time, boolean isWin, List<Poker> pokers) {

        }

        private boolean isSameColor(List<Poker> pokers) {
            return pokers.get(0).getSuit() == pokers.get(1).getSuit() &&
                    pokers.get(1).getSuit() == pokers.get(2).getSuit();
        }

        private boolean isPair(List<Poker> pokers) {
            return pokers.get(0).getPoint().getNum() == pokers.get(1).getPoint().getNum()
                    || pokers.get(1).getPoint().getNum() == pokers.get(2).getPoint().getNum()
                    || pokers.get(0).getPoint().getNum() == pokers.get(2).getPoint().getNum();
        }

        private boolean isStraight(List<Poker> pokers) {
            Collections.sort(pokers);
            return Math.abs(pokers.get(0).getPoint().getNum() - pokers.get(1).getPoint().getNum()) == 1
                    && Math.abs(pokers.get(1).getPoint().getNum() - pokers.get(2).getPoint().getNum()) == 1;

        }

        private boolean isSameColorStraight(List<Poker> handCards) {
            return isSameColor(handCards) && isStraight(handCards);
        }

        private boolean isSamePoint(List<Poker> handCards) {
            return handCards.get(0).getPoint() == handCards.get(1).getPoint()
                    && handCards.get(2).getPoint() == handCards.get(1).getPoint();

        }
    }
