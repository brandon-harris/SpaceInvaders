public class MyVector {
	private int changeX;
	private	int changeY;
	/**
	 * @param changeX
	 * @param changeY
	 */
	public MyVector(int cX, int cY) {
	
		changeX = cX;
		changeY = cY;
	}
	/**
	 * @return the changeX
	 */
	public int getChangeX() {
		return changeX;
	}
	/**
	 * @param changeX the changeX to set
	 */
	public void setChangeX(int cX) {
		changeX = cX;
	}
	/**
	 * @return the changeY
	 */
	public int getChangeY() {
		return changeY;
	}
	/**
	 * @param changeY the changeY to set
	 */
	public void setChangeY(int cY) {
		changeY = cY;
	}
}