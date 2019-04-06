//package com.nullgeodesic.washingtonhhh.domain.archive;
//
//import android.util.Log;
//
//import com.nullgeodesic.washingtonhhh.EventListActivity;
//import com.nullgeodesic.washingtonhhh.dto.HashEventDto;
//
//import org.joda.time.DateTime;
//import org.joda.time.DateTimeFieldType;
//import org.joda.time.LocalTime;
//
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.net.URLEncoder;
//
//public class HashEvent implements Comparable<HashEvent> {
//
//
//    private static final String TAG = HashEvent.class.getSimpleName();
//
//    private final String id;
//    private final DateTime date;
//    private final LocalTime time;
//    private final Kennel type;
//    private final int eventNumber;
//    private final String hare;
//    private final String eventName;
//    private final String description;
//    private final String address;
//    private final String mapLink;
//
//    public HashEvent(Builder builder) {
//        this.id = builder.id;
//        this.date = builder.date;
//        this.time = builder.time;
//        this.type = builder.type;
//        this.eventNumber = builder.eventNumber;
//        this.hare = builder.hare == null ? "" : builder.hare;
//        this.eventName = builder.eventName;
//        this.description = builder.description == null ? "" : builder.description;
//        this.address = builder.address;
//        this.mapLink = builder.mapLink;
//    }
//
//    public String getFormattedLine() {
//        StringBuilder builder = new StringBuilder();
//        builder.append(this.getDay() + "\t");
//        builder.append(this.getTime().toString("h:mm a") + " CST\t");
//        builder.append(this.getType() + "\t");
//        builder.append(this.getEventNumber() + "\t");
//        builder.append(this.getHare() + "\t");
//        builder.append(this.getEventName() + "\t");
//        builder.append(this.getDescription() + "\t");
//        builder.append(this.getAddress() + "\t");
//        builder.append(this.getMapLink() + "\n");
//        return builder.toString();
//    }
//    @Override
//    public int compareTo(HashEvent that) {
//        if(that.getDay() == this.getDay()) {
//            return this.getTime().getMillisOfDay() - that.getTime().getMillisOfDay();
//        }
//        return this.getDay() - that.getDay();
//    }
//
//    public DateTime getDate() {
//        return date;
//    }
//    public int getDay() {
//        return date.get(DateTimeFieldType.dayOfMonth());
//    }
//    public int getMonth() {
//        return date.get(DateTimeFieldType.monthOfYear());
//    }
//
//    public String getDateString() {
//        return date.toString("MMMM d, yyyy"); 	//String.format("%1$tB %1$te, %1$tY", date);
//    }
//    public String getDateTimeString() {
//        return date.toString("yyyy-MM-dd HH:mm");
//    }
//    public String getDateStringShort() {
//        return date.toString("E\nMMM d"); 		//String.format("%1$ta\n%1$tb %1$te", date);
//    }
//    public String getDayOfWeek() {
//        return date.toString("E"); 				//String.format("%1$ta", date);
//    }
//    public String getDayAndDate() {
//        return date.toString("EEEE, MMMM d"); 	//String.format("%1$tA, %1$tB %1$te", date);
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public LocalTime getTime() {
//        return time;
//    }
//    public boolean hasTime() {
//        if(time.getMillisOfDay() > 0 && time.getHourOfDay() <= 24) return true;
//        return false;
//    }
//
//    public Kennel getType() {
//        return type;
//    }
//
//    public int getEventNumber() {
//        return eventNumber;
//    }
//    public boolean hasEventNumber() {
//        if(eventNumber > 0) return true;
//        return false;
//    }
//
//    public String getHare() {
//        return hare;
//    }
//
//    public String getEventName() {
//        if(eventName.length() > 0) return eventName;
//        return this.getTitle();
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public String getMapLink() {
//        if(mapLink == null) return "";
//        return mapLink;
//    }
//
//    public boolean beforeDate(DateTime date) {
//        if(this.date.isBefore(date)) return true;
//        return false;
//    }
//
//    public boolean afterDate(DateTime date) {
//        if(this.date.isAfter(date)) return true;
//        return false;
//    }
//
//    public String getTitle() {
//        StringBuilder builder = new StringBuilder();
//        builder.append(this.getType().getName());
//        if(eventNumber > 0) builder.append(" #" + Integer.toString(eventNumber));
//        return builder.toString();
//    }
//
//    public CharSequence getDateTime() {
//        StringBuilder builder = new StringBuilder();
//        builder.append(date.toString("MMMM d"));
//        if(date.getHourOfDay() > 0) builder.append(", " + date.toString("h:mm a"));
//        return builder;
//    }
//
//    public boolean hasDetails() {
//        return !description.isEmpty() || mapLink != null;
//    }
//
//    public static class Builder {
//        private String id = null;
//        private DateTime date = null;
//        private LocalTime time = null;
//        private Kennel type = Kennel.OTHER;
//        private int eventNumber = 0;
//        private String hare = "";
//        private String eventName = "";
//        private String description = "";
//        private String address = "";
//        private String mapLink = null;
//
//        public HashEvent build() {
//            return new HashEvent(this);
//        }
//
//        public void setId(String i) {
//            this.id = i;
//        }
//        public void setDate(DateTime datetime) {
//            this.time = datetime.toLocalTime();
//            this.date = datetime;
//        }
//        public LocalTime getTime() {
//            return time;
//        }
//        public void setTime(LocalTime time) {
//            this.time = time;
//        }
//        public void setType(Kennel type) {
//            this.type = type;
//        }
//        public void setEventNumber(int eventNumber) {
//            this.eventNumber = eventNumber;
//        }
//        public void setHare(String hare) {
//            this.hare = hare;
//        }
//        public void setEventName(String eventName) {
//            this.eventName = eventName;
//        }
//        public void setDescription(String description) {
//            this.description = description;
//        }
//        public void setAddress(String address) {
//            this.address = address;
//        }
//        public void setMapLinkBuild(String mapLink) {
//            this.mapLink = mapLink;
//        }
//
//
//    }
//
//    public static HashEvent fromDto(HashEventDto dto) {
//        Builder builder = new Builder();
//        builder.setId(dto.id);
//        DateTime date = null;
//        if(dto.date.length == 0) {
//            date = new DateTime(dto.date[0],dto.date[1],dto.date[2],0,0);
//        } else {
//            date = new DateTime(dto.date[0],dto.date[1], dto.date[2], dto.date[3], dto.date[4]);
//        }
//        builder.setDate(new DateTime(date));
//        builder.setType(Kennel.fromCode(dto.kennel));
//        builder.setEventName(dto.eventName);
//        builder.setEventNumber(dto.eventNumber);
//        builder.setHare(dto.hare);
//        builder.setDescription(dto.description);
//        builder.setAddress(dto.address);
//        builder.setMapLinkBuild(dto.mapLink);
//        return builder.build();
//    }
//
//    public static URL parseMapLink(String location) {
//        if(location != null && !location.isEmpty() && !location.startsWith("TBD")) {
//            final String where = URLEncoder.encode(location);
////            final String where = location.replace(' ','+').replace("&","%26");
//            try {
//                return new URL("https://maps.google.com/maps?q=" + where);
//            } catch (final MalformedURLException e) {
//                Log.v(TAG, "Event map link parse error", e);
//            }
//        }
//        return null;
//    }
//}
