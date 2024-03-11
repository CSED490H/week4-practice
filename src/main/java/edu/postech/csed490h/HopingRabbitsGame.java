package edu.postech.csed490h;

/**
 * Two teams of N rabbits each are placed facing each other in a row with 2N + 1
 * positions. Initially, the x team occupies the first N positions, the o team
 * occupies the last N positions, and the middle position is empty. The goal is
 * to swap the positions of the two teams by moving the rabbits. A rabbit can
 * move to an empty position or jump over a rival to an empty position.
 */
public class HopingRabbitsGame {
    //TODO: feel free to add any private fields and methods to implement this class.

    /**
     * Create a game with n rabbits on each team. For example, if n = 3, the initial
     * state is xxx_yyy, where x represents a rabbit from the x team, y represents a
     * rabbit from the o team, and _ represents an empty position.
     *
     * @param n the number of rabbits on each team
     */
    HopingRabbitsGame(int n) {
        //TODO: implement this
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
        //TODO: implement this
        return false;
    }

    /**
     * Return true if the game is over. The game is over if the two teams have swapped
     * their initial positions: e.g., ooo_xxx, when N = 3.
     *
     * @return true if the game is over, false otherwise
     */
    boolean isGoal() {
        //TODO: implement this
        return false;
    }

    /**
     * Return true if the game is stuck. The game is stuck if no rabbit can move.
     *
     * @return true if the game is stuck, false otherwise
     */
    boolean isStuck() {
        //TODO: implement this
        return false;
    }

    /**
     * Return the current state of the game. The state is represented as a string of
     * length 2N + 1 consisting of x, o, and _.
     *
     * @return a string of length 2N + 1 consisting of x, o, and _.
     */
    String getState() {
        //TODO: implement this
        return null;
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
