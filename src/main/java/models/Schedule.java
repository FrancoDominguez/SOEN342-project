package models;

import java.util.List;

public class Schedule {

    //Instance variables
    private int scheduleID;  
    private List<TimeSlot> timeSlots;

    //Constructor
    public Schedule(int scheduleID, List<TimeSlot> timeSlots) {
        this.scheduleID = scheduleID;
        this.timeSlots = timeSlots;
    }

    //Getters and setters
    public int getScheduleID() {
        return scheduleID;
    }

    public void setScheduleId(int scheduleID) {
        this.scheduleID = scheduleID;
    }

    public List<TimeSlot> getTimeSlots() {
        return timeSlots;
    }

    public void setTimeSlots(List<TimeSlot> timeSlots) {
        this.timeSlots = timeSlots;
    }

    // Method to add a TimeSlot to the Schedule
    public void addTimeSlot(TimeSlot timeSlot) {
        this.timeSlots.add(timeSlot);
    }

    // Method to remove a TimeSlot from the Schedule
    public void removeTimeSlot(TimeSlot timeSlot) {
        this.timeSlots.remove(timeSlot);
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "scheduleId=" + scheduleID +
                ", timeSlots=" + timeSlots +
                '}';
    }
}

