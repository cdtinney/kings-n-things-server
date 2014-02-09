package com.kingsandthings.model.phase;

import java.util.logging.Logger;
import com.kingsandthings.model.Player;
import com.kingsandthings.model.board.Tile;
import com.kingsandthings.model.things.Creature;
import java.util.List;

public class CombatPhase extends Phase {

	private static Logger LOGGER = Logger.getLogger(CombatPhase.class.getName());

	public CombatPhase() {
		super("Combat", true, true, 1);
	}

	@Override
	public void begin() {
		// Notify listeners that the phase has begun
		notifyBegin();

	}

	// TODO eventually, attackers will need to be a list that can handle multiple attackers at once
	// TODO handle towers
	public void initiateCombat(Player playerDefender, Player playerAttacker,
			List<Creature> defenders, List<Creature> attackers) {
		
		int damageToAttacker = computeCombat(playerDefender, defenders);
		int damageToDefender = computeCombat(playerAttacker, attackers);
		
		playerRemoveCreatures(playerAttacker, attackers, damageToAttacker);
		playerRemoveCreatures(playerDefender, defenders, damageToDefender);
		
		//Check if battle is over
		if (!(defenders.isEmpty() || attackers.isEmpty())) {
			initiateCombat(playerDefender,playerAttacker,defenders,attackers);
		}
	}

	// Player chooses set of creatures that receive damage hits, call this
	// function to remove them
	private void playerRemoveCreatures(Player player, List<Creature> creatures, int damage) {
		// TODO player chooses creatures to remove/kill = damage dealt
		// player.remove([all chosen creatures]);
	}

	// TODO add in ability order of operations, ie Magic-->Ranged-->Melee respectively
	// Returns amount of damage a set of creatures deal based on die rolls
	private int computeCombat(Player player, List<Creature> creatures) {

		int damageDealt = 0;
		int diceRoll;
		// get attack damages from defending player's creatures
		for (Creature creature : creatures) {
			int combatValue = creature.getCombatValue();

			// TODO have player actually roll the dice here
			// CHARGE allows creature to roll twice
			if (creature.getAbilities().contains("CHARGE")) {
				diceRoll = rollDice();
				if (diceRoll >= combatValue) {
					damageDealt += 1;
				}
			}

			diceRoll = rollDice();
			if (diceRoll >= combatValue) {
				damageDealt += 1;
			}
		}
		return damageDealt;
	}

	// Not needed once player actually rolls dice
	private int rollDice() {
		return (int) (6.0 * Math.random()) + 1; // return # between 1-6
	}

}