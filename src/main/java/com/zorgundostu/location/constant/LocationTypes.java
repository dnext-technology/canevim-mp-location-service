package com.zorgundostu.location.constant;

public enum LocationTypes {
    CITY("city"),
    DISTRICT("district"),
    TOWN("town"),
    NEIGHBORHOOD("neighborhood");

    private String label;

    LocationTypes(String label){
        this.label = label;
    }

    public String label() {
        return this.label;
    }
}