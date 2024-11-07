package models;

import java.time.LocalDateTime;

public class TimeSlot {

    //Instance variables
    private int timeSlotID;  
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    //Constructor
    public TimeSlot(int timeSlotID, LocalDateTime startTime, LocalDateTime endTime, LocalDateTime startDate, LocalDateTime endDate) {
        this.timeSlotID = timeSlotID;
        this.startTime = startTime;
        this.endTime = endTime;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    //Getters and setters
    public int getTimeSlotID() {
        return timeSlotID;
    }

    public void setTimeSlotId(int timeSlotID) {
        this.timeSlotID = timeSlotID;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
}
