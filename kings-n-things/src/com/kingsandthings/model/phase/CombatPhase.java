package com.kingsandthings.model.phase;
import java.util.logging.Logger;
import com.kingsandthings.model.Player;
import com.kingsandthings.model.board.Tile;
import com.kingsandthings.model.things.Creature;
import java.util.ArrayList;



public class CombatPhase extends Phase{

    private static Logger LOGGER = Logger.getLogger(CombatPhase.class.getName());

    public CombatPhase() {
        super("Combat", true, true, 1);
    }

    @Override
    public void begin() {
        // Notify listeners that the phase has begun
        notifyBegin();

    }

    private int computeCombat(ArrayList<Creature> creatures) {

        int damageDealt = 0;
        //get attack damages from defending player's creatures
        for (Creature creature : creatures) {
            int combatValue = creature.getCombatValue();

            //TODO have player actually roll the dice here
            int diceRoll = rollDice();
            if (diceRoll >= combatValue) {
                damageDealt += 1; //Deal one extra damage
            }

        }
        return damageDealt;
    }

    //Not needed once player actually rolls dice
    private int rollDice() {
        return (int)(6.0 * Math.random()) + 1; //return # between 1-6
    }
}

