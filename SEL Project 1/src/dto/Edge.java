package dto;

public class Edge {

	private Integer sourceId;
	private Integer destinationId;

	public Edge(Integer sourceId, Integer destinationId) {
		this.sourceId = sourceId;
		this.destinationId = destinationId;
	}

	public Integer getSourceId() {
		return sourceId;
	}

	public void setSourceId(Integer sourceId) {
		this.sourceId = sourceId;
	}

	public Integer getDestinationId() {
		return destinationId;
	}

	public void setDestinationId(Integer destinationId) {
		this.destinationId = destinationId;
	}

}
