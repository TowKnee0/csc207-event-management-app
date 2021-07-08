package team1.angela;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Event {
    /**
     * Events
     */
    // === Class Variables ===
    // === Instance Variables ===
    // https://stackoverflow.com/questions/24876188/how-big-is-the-chance-to-get-a-java-uuid-randomuuid-collision
    private String eventId;
    private boolean published;
    private Date createdTime;
    private Date editTime;
    private String eventOwner;
    // This saves the fieldName and dataType from the FieldSpecs class into a map so that we can keep a reference of it
    // for the event, in case the template gets changed. Also we can match the data type to the Object in the
    // eventDetails map so things don't break.
    private Map<String, Class<?>> templateFieldSpec = new HashMap<>();
    // The actual map containing event details using the same field details from Template class and with the values
    // entered by the user.
    private Map<String, Object> eventDetails;
    // equal -1 if no max is specified
    private int maxAttendees;
    // the number of people who are attending the event. (We won't be having any tickets at least for Phase 1)
    private int numAttendees;
    // Will essentially be the name of the template e.g. BBQ, concert, wedding
    private String eventType;

    // === Representation Invariants ===
    // Not sure yet
    // === Methods ===
    public Event(Template template, String eventOwner){

        eventId = UUID.randomUUID().toString();
        this.templateFieldSpec.put("field1", String.class);
    }

    // Getters
    public int getEventID() {
        return eventID;
    }
    public Date getCreateDate() {
        return createDate;
    }
    public Date getEditDate() {
        return editDate;
    }
    public boolean isPublished() {
        return published;
    }
    public String getEventOwner() {
        return eventOwner;
    }
    public Map<String, String> getEventDetails() {
        return eventDetails;
    }
    public static int getNumEvents() {
        return numEvents;
    }
    // Setters
    public void setEditDate(Date editDate) {
        this.editDate = editDate;
    }
    public void setPublished(boolean published) {
        this.published = published;
    }
    public void setEventDetails(Map<String, String> eventDetails) {
        this.eventDetails = eventDetails;
    }
}