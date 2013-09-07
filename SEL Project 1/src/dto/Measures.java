package dto;

public class Measures {

	private int mutual = 0;

	private double intersectionFollowees = 0.0;
	private double jaccardFollowees = 0.0;
	private double cosineFollowees = 0.0;
	private double adamicAdarFollowees = 0.0;

	private double intersectionFollowers = 0.0;
	private double jaccardFollowers = 0.0;
	private double cosineFollowers = 0.0;
	private double adamicAdarFollowers = 0.0;

	private double intersectionFolloweesFollowers = 0.0;
	private double jaccardFolloweesFollowers = 0.0;
	private double cosineFolloweesFollowers = 0.0;
	private double adamicAdarFolloweesFollowers = 0.0;

	private int numFolloweesSrc = 0;
	private int numFolloweesDes = 0;
	private int numFollowersSrc = 0;
	private int numFollowersDes = 0;

	// private double jaccardFolloweesFollowers;
	// private double jaccardFollowersFollowees;

	private double pageRank = 0.0;

	// private final double MIN_CUT_OFF = 0.0000001;

	public int getMutual() {
		return mutual;
	}

	public double getIntersectionFollowees() {
		return intersectionFollowees;
	}

	public double getJaccardFollowees() {
		// return jaccardFollowees > MIN_CUT_OFF ? jaccardFollowees : 0.0;
		return jaccardFollowees;
	}

	public double getCosineFollowees() {
		// return cosineFollowees > MIN_CUT_OFF ? cosineFollowees : 0.0;
		return cosineFollowees;
	}

	public double getAdamicAdarFollowees() {
		return adamicAdarFollowees;
	}

	public double getIntersectionFollowers() {
		return intersectionFollowers;
	}

	public double getJaccardFollowers() {
		// return jaccardFollowers > MIN_CUT_OFF ? jaccardFollowers : 0.0;
		return jaccardFollowers;
	}

	public double getCosineFollowers() {
		// return cosineFollowers > MIN_CUT_OFF ? cosineFollowers : 0.0;
		return cosineFollowers;
	}

	public double getAdamicAdarFollowers() {
		return adamicAdarFollowers;
	}

	public int getNumFolloweesSrc() {
		return numFolloweesSrc;
	}

	public int getNumFolloweesDes() {
		return numFolloweesDes;
	}

	public int getNumFollowersSrc() {
		return numFollowersSrc;
	}

	public int getNumFollowersDes() {
		return numFollowersDes;
	}

	// public double getJaccardFolloweesFollowers() {
	// // return jaccardFolloweesFollowers > MIN_CUT_OFF ?
	// // jaccardFolloweesFollowers : 0.0;
	// return jaccardFolloweesFollowers;
	// }
	//
	// public double getJaccardFollowersFollowees() {
	// // return jaccardFollowersFollowees > MIN_CUT_OFF ?
	// // jaccardFollowersFollowees : 0.0;
	// return jaccardFollowersFollowees;
	// }

	public void setMutual(int mutual) {
		this.mutual = mutual;
	}

	public void setIntersectionFollowees(double intersectionFollowees) {
		this.intersectionFollowees = intersectionFollowees;
	}

	public void setJaccardFollowees(double jaccardFollowees) {
		this.jaccardFollowees = jaccardFollowees;
	}

	public void setCosineFollowees(double cosineFollowees) {
		this.cosineFollowees = cosineFollowees;
	}

	public void setAdamicAdarFollowees(double adamicAdarFollowees) {
		this.adamicAdarFollowees = adamicAdarFollowees;
	}

	public void setIntersectionFollowers(double intersectionFollowers) {
		this.intersectionFollowers = intersectionFollowers;
	}

	public void setJaccardFollowers(double jaccardFollowers) {
		this.jaccardFollowers = jaccardFollowers;
	}

	public void setCosineFollowers(double cosineFollowers) {
		this.cosineFollowers = cosineFollowers;
	}

	public void setAdamicAdarFollowers(double adamicAdarFollowers) {
		this.adamicAdarFollowers = adamicAdarFollowers;
	}

	public void setNumFolloweesSrc(int numFolloweesSrc) {
		this.numFolloweesSrc = numFolloweesSrc;
	}

	public void setNumFolloweesDes(int numFolloweesDes) {
		this.numFolloweesDes = numFolloweesDes;
	}

	public void setNumFollowersSrc(int numFollowersSrc) {
		this.numFollowersSrc = numFollowersSrc;
	}

	public void setNumFollowersDes(int numFollowersDes) {
		this.numFollowersDes = numFollowersDes;
	}

	// public void setJaccardFolloweesFollowers(double
	// jaccardFolloweesFollowers) {
	// this.jaccardFolloweesFollowers = jaccardFolloweesFollowers;
	// }
	//
	// public void setJaccardFollowersFollowees(double
	// jaccardFollowersFollowees) {
	// this.jaccardFollowersFollowees = jaccardFollowersFollowees;
	// }

	public String toString() {
		return getMutual() + "," + (int) getIntersectionFollowees() + "," + getCosineFollowees() + "," + getAdamicAdarFollowees() + ","
				+ getJaccardFollowees() + "," + (int) getIntersectionFollowers() + "," + getCosineFollowers() + "," + getAdamicAdarFollowers() + ","
				+ getJaccardFollowers() + "," + (int) getIntersectionFolloweesFollowers() + "," + getCosineFolloweesFollowers() + ","
				+ getAdamicAdarFolloweesFollowers() + "," + getJaccardFolloweesFollowers() + "," + getNumFolloweesSrc() + "," + getNumFolloweesDes()
				+ "," + getNumFollowersSrc() + "," + getNumFollowersDes() + "," + getLogRatioFollowersFolloweesSrc() + ","
				+ getLogRatioFollowersFolloweesDes();
	}

	public double getPageRank() {
		return pageRank;
	}

	public void setPageRank(double pageRank) {
		this.pageRank = pageRank;
	}

	public double getIntersectionFolloweesFollowers() {
		return intersectionFolloweesFollowers;
	}

	public void setIntersectionFolloweesFollowers(double intersectionFolloweesFollowers) {
		this.intersectionFolloweesFollowers = intersectionFolloweesFollowers;
	}

	public double getJaccardFolloweesFollowers() {
		return jaccardFolloweesFollowers;
	}

	public void setJaccardFolloweesFollowers(double jaccardFolloweesFollowers) {
		this.jaccardFolloweesFollowers = jaccardFolloweesFollowers;
	}

	public double getCosineFolloweesFollowers() {
		return cosineFolloweesFollowers;
	}

	public void setCosineFolloweesFollowers(double cosineFolloweesFollowers) {
		this.cosineFolloweesFollowers = cosineFolloweesFollowers;
	}

	public double getAdamicAdarFolloweesFollowers() {
		return adamicAdarFolloweesFollowers;
	}

	public void setAdamicAdarFolloweesFollowers(double adamicAdarFolloweesFollowers) {
		this.adamicAdarFolloweesFollowers = adamicAdarFolloweesFollowers;
	}

	public double getLogRatioFollowersFolloweesSrc() {
		if (numFolloweesSrc > 0)
			return Math.log(numFolloweesSrc / numFollowersSrc);
		return 0;
	}

	public double getLogRatioFollowersFolloweesDes() {
		if (numFolloweesDes > 0)
			return Math.log(numFolloweesDes / numFolloweesDes);
		return 0;
	}

}
