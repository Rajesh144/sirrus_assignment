package org.sirrus.json;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Location {
	private String _id;
	private String description;
	private Geo geo;
	private Tmc tmc;
	private String roadName;
	private Integer eventCode;
	private Integer severity;
	private String validStart;
	private String validEnd;
	private String type;
	private String lastUpdated;

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Geo getGeo() {
		return geo;
	}

	public void setGeo(Geo geo) {
		this.geo = geo;
	}

	public Tmc getTmc() {
		return tmc;
	}

	public void setTmc(Tmc tmc) {
		this.tmc = tmc;
	}

	public String getRoadName() {
		return roadName;
	}

	public void setRoadName(String roadName) {
		this.roadName = roadName;
	}

	public Integer getEventCode() {
		return eventCode;
	}

	public void setEventCode(Integer eventCode) {
		this.eventCode = eventCode;
	}

	public Integer getSeverity() {
		return severity;
	}

	public String getValidStart() {
		return validStart;
	}

	public void setValidStart(String validStart) {
		this.validStart = validStart;
	}

	public String getValidEnd() {
		return validEnd;
	}

	public void setValidEnd(String validEnd) {
		this.validEnd = validEnd;
	}

	public void setSeverity(Integer severity) {
		this.severity = severity;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
}
