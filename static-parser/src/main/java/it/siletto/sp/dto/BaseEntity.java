package it.siletto.sp.dto;

import java.util.Date;

import org.mongodb.morphia.annotations.PrePersist;
import org.mongodb.morphia.annotations.Version;

public abstract class BaseEntity {

	protected Date creationDate;

	protected Date lastChange;

	@Version
	private long version;

	public BaseEntity() {
		super();
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public Date getLastChange() {
		return lastChange;
	}

	public long getVersion() {
		return version;
	}

	@PrePersist
	public void prePersist() {
		this.creationDate = (creationDate == null) ? new Date() : creationDate;
		this.lastChange = (lastChange == null) ? creationDate : new Date();
	}

}
