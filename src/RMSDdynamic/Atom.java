package RMSDdynamic;
public class Atom {
	private String atomType;
	private float xPos;
	private float yPos;
	private float zPos;
	
	public Atom(String atomType, float xPos, float yPos, float zPos) {
		this.atomType = atomType;
		this.xPos = xPos;
		this.yPos = yPos;
		this.zPos = zPos;
	}
	
	public float[] getPOS() {
		float[] pos = new float[3];
		pos[1] = this.xPos;
		pos[2] = this.yPos;
		pos[3] = this.zPos;
		return pos;
	}
	
	public void setxPos(float xPos) {
		this.xPos = xPos;
	}

	public void setyPos(float yPos) {
		this.yPos = yPos;
	}

	public void setzPos(float zPos) {
		this.zPos = zPos;
	}

	public float getxPos() {
		return xPos;
	}

	public String getAtomType() {
		return this.atomType;
	}

	public float getyPos() {
		return yPos;
	}

	public float getzPos() {
		return zPos;
	}
}
