/**
 * Enables movement of objects, stores the changeX and the changeY.
 *
 * @author Kennon
 */
public class MyVector {
    private int changeX;
    private int changeY;

    /**
     * Creates a vector.
     *
     * @param cX
     * @param cY
     */
    public MyVector(int cX, int cY) {
        changeX = cX;
        changeY = cY;
    }

    /**
     * Get the ChangeX.
     *
     * @return the changeX
     */
    public int getChangeX() {
        return changeX;
    }

    /**
     * Gets the changeY.
     *
     * @return the changeY
     */
    public int getChangeY() {
        return changeY;
    }

    /**
     * Sets the changeX.
     *
     * @param cX the changeX to set
     */
    public void setChangeX(int cX) {
        changeX = cX;
    }

    /**
     * Sets the Change Y.
     *
     * @param cY the changeY to set
     */
    public void setChangeY(int cY) {
        changeY = cY;
    }
}