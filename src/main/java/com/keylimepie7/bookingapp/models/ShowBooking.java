package com.keylimepie7.bookingapp.models;

public class ShowBooking {
	String showNumber;
	int phoneNumber;
	String seatNumber;
	int timestamp;
	int ticketNumber;
	public int getTicketNumber() {
		return ticketNumber;
	}
	public ShowBooking(String showNumber, int phoneNumber, String seatNumber, int timestamp, int ticketNumber) {
		super();
		this.showNumber = showNumber;
		this.phoneNumber = phoneNumber;
		this.seatNumber = seatNumber;
		this.timestamp = timestamp;
		this.ticketNumber = ticketNumber;
	}
	public void setTicketNumber(int ticketNumber) {
		this.ticketNumber = ticketNumber;
	}
	public String getShowNumber() {
		return showNumber;
	}
	public void setShowNumber(String showNumber) {
		this.showNumber = showNumber;
	}
	public int getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getSeatNumber() {
		return seatNumber;
	}
	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}
	public int getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(int timestamp) {
		this.timestamp = timestamp;
	}
}
