package by.effectivesoft.intellexer.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum EntityType {
    UNKNOWN(0, "Unknown"),
    PERSON(1, "Person"),
    ORGANIZATION(2, "Organization"),
    LOCATION(3, "Location"),
    TITLE(4, "Title"),
    POSITION(5, "Position"),
    AGE(6, "Age"),
    DATE(7, "Date"),
    DURATION(8, "Duration"),
    NATIONALITY(9, "Nationality"),
    EVENT(10, "Event"),
    URL(11, "Url"),
    MISCELLANEOUS_LOCATION(12, "MiscellaneousLocation");

    private Integer id;
    private String name;

    EntityType(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @JsonValue
    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
