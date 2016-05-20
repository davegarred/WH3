package org.garred.wh3.model;

import java.io.Serializable;
import java.net.URL;

import org.garred.wh3.model.v2.HashEventDto;
import org.garred.wh3.model.v2.Kennel;
import org.joda.time.DateTime;
import org.joda.time.DateTimeFieldType;
import org.joda.time.LocalTime;

public class HashEvent implements Comparable<Object>,Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private final String id; 
	private final DateTime date;
	private final LocalTime time;
	private final Kennel type;
	private final int eventNumber;
	private final String hare;
	private final String eventName;
	private final String description;
	private final String address;
	private final URL mapLink;

	public HashEvent(Builder builder) {
		this.id = builder.id;
		this.date = builder.date;
		this.time = builder.time;
		this.type = builder.type;
		this.eventNumber = builder.eventNumber;
		this.hare = builder.hare == null ? "" : builder.hare;
		this.eventName = builder.eventName;
		this.description = builder.description == null ? "" : builder.description;
		this.address = builder.address;
		this.mapLink = builder.mapLink;
	}

	private String getFormattedLine() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.getDay() + "\t");
		builder.append(this.getTime().toString("h:mm a") + " CST\t");
		builder.append(this.getType() + "\t");
		builder.append(this.getEventNumber() + "\t");
		builder.append(this.getHare() + "\t");
		builder.append(this.getEventName() + "\t");
		builder.append(this.getDescription() + "\t");
		builder.append(this.getAddress() + "\t");
		builder.append(this.getMapLink() + "\n");
		return builder.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HashEvent other = (HashEvent) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (eventName == null) {
			if (other.eventName != null)
				return false;
		} else if (!eventName.equals(other.eventName))
			return false;
		if (hare == null) {
			if (other.hare != null)
				return false;
		} else if (!hare.equals(other.hare))
			return false;
		if (time != other.time)
			return false;
		if (type != other.type)
			return false;
		return true;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((eventName == null) ? 0 : eventName.hashCode());
		result = prime * result + ((hare == null) ? 0 : hare.hashCode());
		result = prime * result + time.getMillisOfDay();
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}
	@Override
	public String toString() {
		StringBuilder string = new StringBuilder(this.eventName);
		if(string.length() > 0) string.append("\n");
		return string.toString() + this.hare;
	}
	@Override
	public int compareTo(Object o) {
		if(o instanceof HashEvent) {
			HashEvent event = (HashEvent) o;
			if(event.getDay() == this.getDay()) {
				return this.getTime().getMillisOfDay() - event.getTime().getMillisOfDay();
			}
			return this.getDay() - event.getDay();
		}
		return -1;		
	}

	private int getDay() {
		return date.get(DateTimeFieldType.dayOfMonth());
	}
	public String getDateStringShort() {
 		return date.toString("E\nMMM d"); 		//String.format("%1$ta\n%1$tb %1$te", date);
 	}

	public String getId() {
		return id;
	}

	private LocalTime getTime() {
		return time;
	}

	public Kennel getType() {
		return type;
	}

	public int getEventNumber() {
		return eventNumber;
	}

	public String getHare() {
		return hare;
	}

	public String getEventName() {
		if(eventName.length() > 0) return eventName;
		return this.getTitle();
	}

	public String getDescription() {
		return description;
	}

	public String getAddress() {
		return address;
	}

	public String getMapLink() {
		if(mapLink == null) return "";		
		return mapLink.toExternalForm();
	}
	
	public String getTitle() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.getType().getName());
		if(eventNumber > 0) builder.append(" #" + Integer.toString(eventNumber));
		return builder.toString();
	}

	public CharSequence getDateTime() {
		StringBuilder builder = new StringBuilder();
		builder.append(date.toString("MMMM d"));
		if(date.getHourOfDay() > 0) builder.append(", " + date.toString("h:mm a"));
		return builder;
	}

	public boolean hasDetails() {
		return !description.isEmpty() || mapLink != null;
	}

	public static class Builder {
		private String id = null;
		private DateTime date = null;
		private LocalTime time = null;
		private Kennel type = Kennel.OTHER;
		private int eventNumber = 0;
		private String hare = "";
		private String eventName = "";
		private String description = "";
		private String address = "";
		private URL mapLink = null;

		public HashEvent build() {
			return new HashEvent(this);
		}
		
		public Builder withId(String i) {
			this.id = i;
			return this;
		}
		public Builder withDate(DateTime datetime) {
			this.time = datetime.toLocalTime();
			this.date = datetime;
			return this;
		}
		public LocalTime getTime() {
			return time;
		}
		public Builder setTime(LocalTime time) {
			this.time = time;
			return this;
		}
		public Builder withType(Kennel type) {
			this.type = type;
			return this;
		}
		public Builder withEventNumber(int eventNumber) {
			this.eventNumber = eventNumber;
			return this;
		}
		public Builder withHare(String hare) {
			this.hare = hare;
			return this;
		}
		public Builder withEventName(String eventName) {
			this.eventName = eventName;
			return this;
		}
		public Builder withDescription(String description) {
			this.description = description;
			return this;
		}
		public Builder withAddress(String address) {
			this.address = address;
			return this;
		}
		public Builder withMapLinkBuild(URL mapLink) {
			this.mapLink = mapLink;
			return this;
		}


	}

	public static HashEvent fromDto(HashEventDto dto) {
		Builder builder = new Builder();
		builder.withId(dto.id);
		DateTime date = null;
		if(dto.date.length == 0) {
			date = new DateTime(dto.date[0],dto.date[1],dto.date[2],0,0);
		} else {
			date = new DateTime(dto.date[0],dto.date[1], dto.date[2], dto.date[3], dto.date[4]);
		}
		builder.withDate(new DateTime(date));
		builder.withType(Kennel.fromCode(dto.kennel));
		builder.withEventName(dto.eventName);
		builder.withEventNumber(dto.eventNumber);
		builder.withHare(dto.hare);
		builder.withDescription(dto.description);
		builder.withAddress(dto.address);
		builder.withMapLinkBuild(dto.mapLink);
		return builder.build();
	}
}
