package com.keylimepie7.bookingapp.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import com.keylimepie7.bookingapp.models.ShowBooking;
import com.keylimepie7.bookingapp.models.ShowModel;

@ShellComponent
public class ApplicationCommands {
	public String str = "helloThere";
	public Boolean isAdmin = true;
	public Boolean isBuyer = false;
//	ArrayList<ShowModel> showList = new ArrayList<ShowModel>();
	public HashMap<String, ShowModel> showList = new HashMap<>();
	public HashMap<String, ShowBooking> ticketList = new HashMap<>();
	public Set<Integer> usedPhoneNumbers = new HashSet<> ();
	public int incrementingTicketNum = 0;
	
	@ShellMethod(key = "testing",value = "I will return a test string")
	public String testing() {
		return str;
	}
	
	@ShellMethod(key = "testing2",value = "I will return a test string with args")
	public String testing2(@ShellOption(defaultValue = "default") String arg) {
		return str + " " + arg;
	}
	
	@ShellMethod(key = "testing3",value = "I will return a test string with args")
	public String testing3(@ShellOption String[] args) {
		String returnValue = "start";
		for (int i = 0; i < args.length; i++) {
			returnValue = returnValue + " " + args[i];
		}
		return returnValue;
	}
	
	@ShellMethod(key = "testing4", value = "testing specifically 2 arguments")
	public String testing4(
	        @ShellOption(help = "First argument") String arg1,
	        @ShellOption(help = "Second argument") String arg2
	) {
	    return "First argument: " + arg1 + ", Second argument: " + arg2;
	}
	
	@ShellMethod(key = "setadmin",value = "login as admin")
	public String setAdmin() {
		isAdmin = true;
		isBuyer = false;
		return "Logged in as Admin";
	}
	
	@ShellMethod(key = "setbuyer",value = "login as buyer")
	public String setBuyer() {
		isAdmin = false;
		isBuyer = true;
		return "Logged in as Buyer";
	}
	
	@ShellMethod(key = "checkaccount",value = "check the account you are logged in as")
	public String checkAccount() {
		if (isAdmin == true) {
			return "Logged in as admin";
		} else {
			return "Logged in as Buyer";
		}
	}
	
	@ShellMethod(key = "Setup",value = "Setup a show as Admin")
	public String setupShow(
			@ShellOption(help = "Show Number") String showNum,
	        @ShellOption(help = "Number of Rows") int numOfRows,
	        @ShellOption(help = "Number of Seats Per Row") int seatsPerRow,
	        @ShellOption(help = "Cancellation Window in Minutes") int cancelWindowMins
			) {
		if (isAdmin != true) {
			return "Please log in as admin";
		}
		ShowModel newShow = new ShowModel(showNum, numOfRows, seatsPerRow, cancelWindowMins);
		showList.put(showNum,newShow);
		return "Successfully Added a New Show";
	}
	
	// WIP
	@ShellMethod(key = "View",value = "View a show as Admin")
	public String viewShow(
			@ShellOption(help = "Show Number") String showNum
			) {
		if (isAdmin != true) {
			return "Please log in as admin";
		}
//		ShowModel returnedShow = showList.get(showNum);
		ArrayList<ShowBooking> qualifyingTickets = new ArrayList<>();
		ticketList.forEach((key, value) -> {
			if (value.getShowNumber().equals(showNum)) {
				qualifyingTickets.add(value);
			}
		});
		String returnStr = "Show Number \t Ticket Number \t Phone Number \t Seat Number \n";
		for (int i = 0 ; i < qualifyingTickets.size() ; i++ ) {
			returnStr = returnStr + showNum + " \t\t " + qualifyingTickets.get(i).getTicketNumber() + " \t\t " + qualifyingTickets.get(i).getPhoneNumber() + " \t\t " + qualifyingTickets.get(i).getSeatNumber() + " \n";
		}
		return returnStr;
//		return "Show Details - Show Number: " + returnedShow.getShowNumber() + " Cancellation Window: " + returnedShow.getCancellationWindowMinutes() 
//			+ " Available Seats: " + returnedShow.getAvailableSeats().toString();
	}
	
	@ShellMethod(key = "Availability",value = "View available seats for a show as Buyer")
	public String availability(
			@ShellOption(help = "Show Number") String showNum
			) {
		if (isBuyer != true) {
			return "Please log in as buyer";
		}
		ShowModel returnedShow = showList.get(showNum);
		if (returnedShow == null) {
			return "This show does not exist";
		}
		return "For Show Number " + showNum + " - Available seats: " + returnedShow.getAvailableSeats().toString();
	}
	
	@ShellMethod(key = "Book",value = "Book seats for a show as Buyer")
	public String book(
			@ShellOption(help = "Show Number") String showNum,
			@ShellOption(help = "Phone Number") int phoneNum,
			@ShellOption(help = "Comma Separated List of Seats") String commaSeparatedListOfSeats
			) {
		if (isBuyer != true) {
			return "Please log in as buyer";
		}
		if (usedPhoneNumbers.contains(phoneNum)) {
			return "This phone number has already been used for a booking";
		}
		ShowModel returnedShow = showList.get(showNum);
		if (returnedShow == null) {
			return "This show does not exist";
		}
		
		String[] seatList = commaSeparatedListOfSeats.split(",");
		Set<String> availableSeats = returnedShow.getAvailableSeats();
		for (int i = 0 ; i < seatList.length ; i++ ) {
			if (!availableSeats.contains(seatList[i])) {
				return "Please only provide valid seats";
			}
		}
		ArrayList<Integer> listOfTickets = new ArrayList<>();
		
		for (int i = 0 ; i < seatList.length ; i++ ) {
			availableSeats.remove(seatList[i]);
			int timestamp = (int) System.currentTimeMillis();
			ShowBooking newTicket = new ShowBooking(showNum, phoneNum, seatList[i], timestamp, incrementingTicketNum);
			ticketList.put(String.valueOf(incrementingTicketNum), newTicket);
			listOfTickets.add(incrementingTicketNum);
			incrementingTicketNum++;
		}
		usedPhoneNumbers.add(phoneNum);
		returnedShow.setAvailableSeats(availableSeats);
		return "Success. Seats booked for " + Arrays.toString(seatList) + " with ticket numbers " + listOfTickets.toString();
	}
	
	@ShellMethod(key = "Cancel",value = "Cancel a ticket for a show as Buyer")
	public String cancel(
			@ShellOption(help = "Ticket Number") String ticketNum,
			@ShellOption(help = "Phone Number") String phoneNum
			) {
		if (isBuyer != true) {
			return "Please log in as buyer";
		}
		if (!ticketList.containsKey(ticketNum)) {
			return "Please enter a valid ticket number";
		}
		
		ShowBooking theTicket = ticketList.get(ticketNum);
		String showNum = theTicket.getShowNumber();
		ShowModel theShow = showList.get(showNum);
		Integer cancellationWindowMinutes = theShow.getCancellationWindowMinutes();
		Integer currentEpochTime = (int) System.currentTimeMillis();
		Integer windowInMillis = cancellationWindowMinutes * 60 * 1000;
		if ((currentEpochTime - theTicket.getTimestamp()) > windowInMillis) {
			return "Failure: Unable to Cancel Ticket as Time Window (" + cancellationWindowMinutes + " min) is Exceeded";
		}
		Set<String> availSeats = theShow.getAvailableSeats();
		availSeats.add(theTicket.getSeatNumber());
		ticketList.remove(ticketNum);
		theShow.setAvailableSeats(availSeats);
		return "Success. Tickets successfully cancelled.";
	}
}
