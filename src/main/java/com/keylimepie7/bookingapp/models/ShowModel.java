package com.keylimepie7.bookingapp.models;

import java.util.HashSet;
import java.util.Set;

public class ShowModel {
	String showNumber;
	int numOfRows;
	int seatsPerRow;
	int cancellationWindowMinutes;
	Set<String> availableSeats = new HashSet<String> ();
	
	public String convertDigitToString(int digit) {
		switch(digit) {
		  case 1:
		    return "A";
		  case 2:
			  return "B";
		  case 3:
			  return "C";
		  case 4:
			  return "D";
		  case 5:
			  return "E";
		  case 6:
			  return "F";
		  case 7:
			  return "G";
		  case 8:
			  return "H";
		  case 9:
			  return "I";
		  case 10:
			  return "J";
		  case 11:
			  return "K";
		  case 12:
			  return "L";
		  case 13:
			  return "M";
		  case 14:
			  return "N";
		  case 15:
			  return "O";
		  case 16:
			  return "P";
		  case 17:
			  return "Q";
		  case 18:
			  return "R";
		  case 19:
			  return "S";
		  case 20:
			  return "T";
		  case 21:
			  return "U";
		  case 22:
			  return "V";
		  case 23:
			  return "W";
		  case 24:
			  return "X";
		  case 25:
			  return "Y";
		  case 26:
			  return "Z";
		  default:
		    return "ERROR";
		}
	}
	
	public Set<String> getAvailableSeats() {
		return availableSeats;
	}
	public void setAvailableSeats(Set<String> availableSeats) {
		this.availableSeats = availableSeats;
	}
	public ShowModel(String showNumber, int numOfRows, int seatsPerRow, int cancellationWindowMinutes) {
		super();
		this.showNumber = showNumber;
		this.numOfRows = numOfRows;
		this.seatsPerRow = seatsPerRow;
		this.cancellationWindowMinutes = cancellationWindowMinutes;
		
		for (int i = 1; i <= numOfRows ; i++) {
			for (int j = 1; j <= seatsPerRow ; j++) {
				availableSeats.add(convertDigitToString(i)+j);
			}
		}
	}
	public String getShowNumber() {
		return showNumber;
	}
	public void setShowNumber(String showNumber) {
		this.showNumber = showNumber;
	}
	public int getNumOfRows() {
		return numOfRows;
	}
	public void setNumOfRows(int numOfRows) {
		this.numOfRows = numOfRows;
	}
	public int getSeatsPerRow() {
		return seatsPerRow;
	}
	public void setSeatsPerRow(int seatsPerRow) {
		this.seatsPerRow = seatsPerRow;
	}
	public int getCancellationWindowMinutes() {
		return cancellationWindowMinutes;
	}
	public void setCancellationWindowMinutes(int cancellationWindowMinutes) {
		this.cancellationWindowMinutes = cancellationWindowMinutes;
	}
	
	
}
