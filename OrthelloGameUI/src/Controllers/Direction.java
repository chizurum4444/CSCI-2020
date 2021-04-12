package Controllers;

/*
 * Direction
 *
 * This helper class is used to store the 8 cardinal directions Up down left right etc
 *
 * @author Dayne Dellaire 100741322
 * @author Chizurum Agomoth 100756924
 * @author Nakil Jung 100803433
 * @version 1.0
 * @since 11/04/2021
 */
public final class Direction {

    private final int DELTA_X;
    private final int DELTA_Y;

    public Direction(int deltaX, int deltaY) {
        this.DELTA_X = deltaX;
        this.DELTA_Y = deltaY;
    }

    public int getDeltaX() {
        return DELTA_X;
    }
    public int getDeltaY() { return DELTA_Y; }

    @Override
    public String toString() {
        return "Direction{" +
                "deltaX=" + DELTA_X +
                ", deltaY=" + DELTA_Y +
                '}';
    }
}
