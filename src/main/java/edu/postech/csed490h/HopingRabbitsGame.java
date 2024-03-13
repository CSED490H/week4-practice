package edu.postech.csed490h;

/**
 * Two teams of N rabbits each are placed facing each other in a row with 2N + 1
 * positions. Initially, the x team occupies the first N positions, the o team
 * occupies the last N positions, and the middle position is empty. The goal is
 * to swap the positions of the two teams by moving the rabbits. A rabbit can
 * move to an empty position or jump over a rival to an empty position.
 */
public class HopingRabbitsGame {
    private final int numRabbitsOnEachTeam; // stores N

    /**
     * Represents the state of game as an array of length 2N + 1.
     * For example, rabbit on team x positioned at the first position
     * corresponds to Rabbit.X stored in index 0.
     */
    private Rabbit[] gameState;

    /**
     * Create a game with n rabbits on each team. For example, if n = 3, the initial
     * state is xxx_yyy, where x represents a rabbit from the x team, y represents a
     * rabbit from the o team, and _ represents an empty position.
     *
     * @param n the number of rabbits on each team
     */
    HopingRabbitsGame(int n) {
        numRabbitsOnEachTeam = n;

        // Initialize game state
        gameState = new Rabbit[2 * numRabbitsOnEachTeam + 1];
        for (int i = 0; i < numRabbitsOnEachTeam; i++)
        {
            // x team occupies the first N positions
            gameState[i] = Rabbit.X;

            // o team occupies the last N positions
            gameState[((2 * numRabbitsOnEachTeam + 1) - 1) - i] = Rabbit.O;
        }
    }

    /**
     * Try to update a rabbit to a new position.
     * If the original or new position is invalid, return fase..
     * Move a rabbit to an empty position. Rabbits from the x team can only move to
     * the right, and rabbits from the o team can only move to the left. A rabbit is
     * allowed to advance one position if that position is empty. A rabbit can jump
     * over a rival if the position behind the rival is empty. For example, if the
     * current state is xxxo_oo, the x team can move to xx_oxoo.
     *
     * @param rabbit a rabbit
     * @return true if the rabbit can move, false otherwise
     */
    private boolean updatePos(Rabbit rabbit, int ori, int newP) {
        if (0 <= ori && ori < 2 * numRabbitsOnEachTeam + 1 && 0 <= newP && newP < 2 * numRabbitsOnEachTeam + 1) {
            gameState[ori] = null; // original position is now empty
            gameState[newP] = rabbit;
            return true;
        }
        return false;
    }

    /**
     * Move a rabbit to an empty position. Rabbits from the x team can only move to
     * the right, and rabbits from the o team can only move to the left. A rabbit is
     * allowed to advance one position if that position is empty. A rabbit can jump
     * over a rival if the position behind the rival is empty. For example, if the
     * current state is xxxo_oo, the x team can move to xx_oxoo.
     *
     * @param rabbit a rabbit
     * @return true if the rabbit can move, false otherwise
     */
    boolean move(Rabbit rabbit) {
        int movableRabbitPosition = -1;
        String currentGameState = this.getState();

        // Check if rabbit can advance (empty position in front of x or o)
        // And if rabbit can advance, advance and update game state
        if (rabbit == Rabbit.X) {
            movableRabbitPosition = currentGameState.indexOf("x_");
            if (updatePos(rabbit, movableRabbitPosition, movableRabbitPosition + 1)) {
                return true;
            }
        } else {

            movableRabbitPosition = currentGameState.indexOf("_o");
            if (updatePos(rabbit, movableRabbitPosition + 1, movableRabbitPosition)) {
                return true;
            }
        }

        // Check if rabbit can jump (o/x and empty position in front of x/o)
        // And if rabbit can jump, jump and update game state
        if (rabbit == Rabbit.X) {
            movableRabbitPosition = currentGameState.indexOf("xo_");
            if (updatePos(rabbit, movableRabbitPosition, movableRabbitPosition + 2)) {
                return true;
            }
        } else {
            movableRabbitPosition = currentGameState.indexOf("_xo");
            if (updatePos(rabbit, movableRabbitPosition + 2, movableRabbitPosition)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Return true if the game is over. The game is over if the two teams have swapped
     * their initial positions: e.g., ooo_xxx, when N = 3.
     *
     * @return true if the game is over, false otherwise
     */
    boolean isGoal() {
        int idx;
        /* Check if o team have swapped correctly */
        for (idx = 0; idx < numRabbitsOnEachTeam; idx++) {
            if (gameState[idx] != Rabbit.O) {
                return false;
            }
        }

        /* Check if the middle position is empty */
        if (gameState[idx++] != null) {
            return false;
        }

        /* Check if x team have swapped correctly */
        for (; idx < (2 * numRabbitsOnEachTeam + 1) - 1; idx++) {
            if (gameState[idx] != Rabbit.X) {
                return false;
            }
        }
        return true;
    }

    /**
     * Return true if the game is stuck. The game is stuck if no rabbit can move.
     *
     * @return true if the game is stuck, false otherwise
     */
    boolean isStuck() {
        String temp = this.getState();
        String[] samples = {"x_", "xo_", "_o", "_xo"};
        for (String sample : samples) {
            if (temp.contains(sample)) { return false; }
        }
        return true;
    }

    /**
     * Return the current state of the game. The state is represented as a string of
     * length 2N + 1 consisting of x, o, and _.
     *
     * @return a string of length 2N + 1 consisting of x, o, and _.
     */
    String getState() {
        StringBuilder state = new StringBuilder();
        for (Rabbit rabbit : gameState) {
            if (rabbit == Rabbit.X) { state.append("x"); }
            else if (rabbit == Rabbit.O) { state.append("o"); }
            else { state.append("_"); } // rabbit == null
        }
        return state.toString();
    }

    public static void main(String[] args) {
        var game = new HopingRabbitsGame(3);

        System.out.println(game.getState());
        game.move(Rabbit.X);
        System.out.println(game.getState());
        game.move(Rabbit.O);
        System.out.println(game.getState());
    }

}
