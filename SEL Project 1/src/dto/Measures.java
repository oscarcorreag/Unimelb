package dto;

public class Measures {

	private int mutual;

	private double intersectionFollowees;
	// private double jaccardFollowees;
	private double cosineFollowees;
	private double adamicAdarFollowees;

	private double intersectionFollowers;
	// private double jaccardFollowers;
	private double cosineFollowers;
	private double adamicAdarFollowers;

	private int numFolloweesSrc = 0;
	private int numFolloweesDes = 0;
	private int numFollowersSrc = 0;
	private int numFollowersDes = 0;
		
	private double jacFollowerBasedToFollowees = 0.0;
	
	private double jaccardFolloweesFollowers;
	private double jaccardFollowersFollowees;

	private double pageRank = 0.0;

	// private final double MIN_CUT_OFF = 0.0000001;

	public Measures() {

		mutual = 0;

		intersectionFollowees = 0.0;
		// jaccardFollowees = 0.0;
		cosineFollowees = 0.0;

		intersectionFollowers = 0.0;
		// jaccardFollowers = 0.0;
		cosineFollowers = 0.0;

		numFolloweesSrc = 0;
		numFolloweesDes = 0;
		numFollowersSrc = 0;
		numFollowersDes = 0;

		jaccardFolloweesFollowers = 0.0;
		jaccardFollowersFollowees = 0.0;
	}

	public int getMutual() {
		return mutual;
	}

	public double getIntersectionFollowees() {
		return intersectionFollowees;
	}

	// public double getJaccardFollowees() {
	// // return jaccardFollowees > MIN_CUT_OFF ? jaccardFollowees : 0.0;
	// return jaccardFollowees;
	// }

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

	// public double getJaccardFollowers() {
	// // return jaccardFollowers > MIN_CUT_OFF ? jaccardFollowers : 0.0;
	// return jaccardFollowers;
	// }

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

	public double getJaccardFolloweesFollowers() {
		// return jaccardFolloweesFollowers > MIN_CUT_OFF ?
		// jaccardFolloweesFollowers : 0.0;
		return jaccardFolloweesFollowers;
	}

	public double getJaccardFollowersFollowees() {
		// return jaccardFollowersFollowees > MIN_CUT_OFF ?
		// jaccardFollowersFollowees : 0.0;
		return jaccardFollowersFollowees;
	}

	public void setMutual(int mutual) {
		this.mutual = mutual;
	}

	public void setIntersectionFollowees(double intersectionFollowees) {
		this.intersectionFollowees = intersectionFollowees;
	}

	// public void setJaccardFollowees(double jaccardFollowees) {
	// this.jaccardFollowees = jaccardFollowees;
	// }

	public void setCosineFollowees(double cosineFollowees) {
		this.cosineFollowees = cosineFollowees;
	}

	public void setAdamicAdarFollowees(double adamicAdarFollowees) {
		this.adamicAdarFollowees = adamicAdarFollowees;
	}

	public void setIntersectionFollowers(double intersectionFollowers) {
		this.intersectionFollowers = intersectionFollowers;
	}

	// public void setJaccardFollowers(double jaccardFollowers) {
	// this.jaccardFollowers = jaccardFollowers;
	// }

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

	public void setJaccardFolloweesFollowers(double jaccardFolloweesFollowers) {
		this.jaccardFolloweesFollowers = jaccardFolloweesFollowers;
	}

	public void setJaccardFollowersFollowees(double jaccardFollowersFollowees) {
		this.jaccardFollowersFollowees = jaccardFollowersFollowees;
	}

	public String toString() {
		// return getPageRank() + "," + getMutual() + "," + (int)
		// getIntersectionFollowees() + "," + getJaccardFollowees() + "," +
		// getCosineFollowees()
		// + "," + (int) getIntersectionFollowers() + "," +
		// getJaccardFollowers() + "," + getCosineFollowers() + "," +
		// getNumFolloweesSrc()
		// + "," + getNumFolloweesDes() + "," + getNumFollowersSrc() + "," +
		// getNumFollowersDes();

		// return getPageRank() + "," + getMutual() + "," + (int)
		// getIntersectionFollowees() + "," + getCosineFollowees() + ","
		// + getAdamicAdarFollowees() + "," + (int) getIntersectionFollowers() +
		// "," + getCosineFollowers() + "," + getAdamicAdarFollowers()
		// + "," + getNumFolloweesSrc() + "," + getNumFolloweesDes() + "," +
		// getNumFollowersSrc() + "," + getNumFollowersDes();

		return getMutual() + "," + (int) getIntersectionFollowees() + "," + getCosineFollowees() + "," + getAdamicAdarFollowees() + ","
				+ (int) getIntersectionFollowers() + "," + getCosineFollowers() + "," + getAdamicAdarFollowers() + "," + getNumFolloweesSrc() + ","
				+ getNumFolloweesDes() + "," + getNumFollowersSrc() + "," + getNumFollowersDes();
	}

	public double getPageRank() {
		return pageRank;
	}

	public void setPageRank(double pageRank) {
		this.pageRank = pageRank;
	}

	public double getJacFollowerBasedToFollowees() {
		return jacFollowerBasedToFollowees;
	}

	public void setJacFollowerBasedToFollowees(double jacFollowerBasedToFollowees) {
		this.jacFollowerBasedToFollowees = jacFollowerBasedToFollowees;
	}
	
	
	
	
	
}
