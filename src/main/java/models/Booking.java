package main.java.models;

import java.util.UUID;

public class Booking {
  private String bookingId;
  private TimeSlot bookingDate;
  private Client client;
  private Offering offering;

  public Booking(Offering offering) {
    this.bookingId = UUID.randomUUID().toString();
    this.bookingDate = offering.getClassTime();
    this.offering = offering;
  }

  public void cancel() {
    // remove instances of booking in offering
  }
}
