
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * MyReservationSystem uses user input to carry out specified task.
 */

public class ReservationSystem {

	private static String[] command;
	private static String flightName;

	/**
	 * Takes in user input to carry out specified task
	 */
	public static void main(String[] args) {

		command = new String[args.length];

		for (int i = 0; i < args.length; i++) {
			command[i] = args[i];
		}

		flightName = command[2];

		FlightSeats flight = new FlightSeats(flightName);
		Scanner s = new Scanner(System.in);
		String letter = "";
		String input = "";
		Boolean done = false;

		while (!done) {

			System.out.println("Add [P]assenger, Add [G]roup, [C]ancel Reservations, "
					+ "Print Seating [A]vailability Chart, Print [M]anifest, [Q]uit");

			System.out.println("\nEnter a letter: ");
			letter = s.nextLine().toUpperCase();

			// *************************** ADD PASSENGER ***************************//

			if (letter.equals("P")) {

				String name = "";
				String serviceClass = "";
				String seat = "";
				Boolean added = false;

				System.out.println("Enter Name: ");
				name = s.nextLine();

				System.out.println("Enter Service Class (first/economy): ");
				serviceClass = s.nextLine().toUpperCase();

				while (!added) {

					if (serviceClass.equals("FIRST")) {
						System.out.println("Enter Seat Preference ([W]indow,[A]isle): ");
					} else {
						System.out.println("Enter Seat Preference ([W]indow,[C]enter,[A]isle): ");
					}
					seat = s.nextLine().toUpperCase();

					// ***************** FIRST CLASS *****************//

					if (serviceClass.equals("FIRST")) {

						// window seat
						if (seat.equals("W")) {

							for (int i = 0; i < flight.getFirstClass().size(); i++) {

								Reservation temp = flight.getFirstClass().get(i);

								if (!temp.isReserved()
										&& (temp.getSeatLetter().equals("A") || temp.getSeatLetter().equals("D"))) {

									temp.setName(name);
									temp.setReserved(true);
									temp.setGroup(false);
									System.out.println("Reservation was successfully added!\n" + temp.getRowNum()
											+ temp.getSeatLetter());
									added = true;
									break;
								}
							}
							System.out.println();
						}

						// aisle seat
						else if (seat.equals("A")) {

							for (int i = 0; i < flight.getFirstClass().size(); i++) {

								Reservation temp = flight.getFirstClass().get(i);

								if (!temp.isReserved()
										&& (temp.getSeatLetter().equals("B") || temp.getSeatLetter().equals("C"))) {

									temp.setName(name);
									temp.setReserved(true);
									temp.setGroup(false);
									System.out.println("Reservation was successfully added!\n" + temp.getRowNum()
											+ temp.getSeatLetter());
									added = true;
									break;
								}
							}
						}
						System.out.println();
					}

					// ***************** ECONOMY CLASS *****************//

					if (serviceClass.equals("ECONOMY")) {

						// window seat
						if (seat.equals("W")) {

							for (int i = 0; i < flight.getEconomyClass().size(); i++) {

								Reservation temp = flight.getEconomyClass().get(i);

								if (!temp.isReserved()
										&& (temp.getSeatLetter().equals("A") || temp.getSeatLetter().equals("F"))) {

									temp.setName(name);
									temp.setReserved(true);
									temp.setGroup(false);
									System.out.println("Reservation was successfully added!\n" + temp.getRowNum()
											+ temp.getSeatLetter());
									added = true;
									break;
								}
							}
							System.out.println();
						}

						// center seat
						else if (seat.equals("C")) {

							for (int i = 0; i < flight.getEconomyClass().size(); i++) {

								Reservation temp = flight.getEconomyClass().get(i);

								if (!temp.isReserved()
										&& (temp.getSeatLetter().equals("B") || temp.getSeatLetter().equals("E"))) {

									temp.setName(name);
									temp.setReserved(true);
									temp.setGroup(false);
									System.out.println("Reservation was successfully added!\n" + temp.getRowNum()
											+ temp.getSeatLetter());
									added = true;
									break;
								}
							}
							System.out.println();
						}

						// aisle seat
						else if (seat.equals("A")) {

							for (int i = 0; i < flight.getEconomyClass().size(); i++) {

								Reservation temp = flight.getEconomyClass().get(i);

								if (!temp.isReserved()
										&& (temp.getSeatLetter().equals("D") || temp.getSeatLetter().equals("C"))) {

									temp.setName(name);
									temp.setReserved(true);
									temp.setGroup(false);
									System.out.println("Reservation was successfully added!\n" + temp.getRowNum()
											+ temp.getSeatLetter());
									added = true;
									break;
								}
							}
							System.out.println();
						}
					}
					if (!added) {
						System.out.println("No seat is available. Please try another seat preference.\n");
					}
				}

				letter = "";
				input = "";
			}

			// *************************** ADD GROUP ***************************//

			if (letter.equals("G")) {

				ArrayList<String> nameList = new ArrayList<String>();
				String inputNames = "";
				String name = "";
				String serviceClass = "";
				String groupName = "";

				System.out.println("Enter Group Name: ");
				groupName = s.nextLine();

				System.out
						.println("Enter Names (separate each name with a comma and use appropriate capitalization): ");
				inputNames = s.nextLine();

				// separating names and inserting into nameList
				for (int i = 0; i < inputNames.length(); i++) {

					if (i == inputNames.length() - 1) {
						name += inputNames.substring(i, i + 1);
					}
					if (inputNames.substring(i, i + 1).equals(",") || i == inputNames.length() - 1) {
						nameList.add(name);
						name = "";
					} else {
						name += inputNames.substring(i, i + 1);
					}

				}

				System.out.println("Enter Service Class (first/economy): ");
				serviceClass = s.nextLine().toUpperCase();

				int numOfPeople = nameList.size();
				int rowNum = 0;
				int counter = 0;
				int emptySeats = 0;
				int emptySeatsCounter = 0;
				// int seated = 0;

				ArrayList<Reservation> tempList = new ArrayList<Reservation>();
				ArrayList<String> freeSeatsTemp = new ArrayList<String>(); // counting free seats
				ArrayList<String> freeSeats = new ArrayList<String>();

				// ***************** FIRST CLASS *****************//

				if (serviceClass.equals("FIRST")) {

					int totalFree = 0; // total # of free seats

					// number of total free seats
					for (int i = 0; i < flight.getFirstClass().size(); i++) {
						Reservation temp = flight.getFirstClass().get(i);
						if (!temp.isReserved()) {
							totalFree++;
						}
					}

					// make sure there are more free seats than # of people
					if (totalFree >= numOfPeople) {

						// while not all people have been seated
						while (numOfPeople > 0) {

							freeSeats.clear();
							rowNum = 0;
							emptySeats = 0;
							tempList.clear();

							for (int i = 0; i < flight.getFirstClass().size(); i++) {

								Reservation temp = flight.getFirstClass().get(i);

								counter = temp.getRowNum(); // keep track of current row

								if (temp.getSeatLetter().equals("A")) {

									emptySeatsCounter = 0;
									freeSeatsTemp.clear();

									for (int j = 0; j < 4; j++) { // loop through all A B C D

										Reservation temp2 = flight.getFirstClass().get(i + j);

										if (!temp2.isReserved()) {

											emptySeatsCounter++;
											freeSeatsTemp.add(temp2.getSeatLetter());
										}
									}

									// finding row with most empty seats
									if (emptySeatsCounter > emptySeats) {
										emptySeats = emptySeatsCounter;
										rowNum = counter;
										freeSeats.clear();
										for (int k = 0; k < freeSeatsTemp.size(); k++) {
											freeSeats.add(freeSeatsTemp.get(k));
										}
									}
								}
							}

							for (int i = 0; i < flight.getFirstClass().size(); i++) {

								Reservation temp = flight.getFirstClass().get(i);

								if (numOfPeople == 0) {
									break;
								}

								for (int j = 0; j < freeSeats.size(); j++) {

									if (temp.getRowNum() == rowNum && temp.getSeatLetter().equals(freeSeats.get(j))) {
										if (numOfPeople == 0) {
											break;
										}
										tempList.add(temp);
										temp.setReserved(true);
										numOfPeople--;
									}
								}
							}

							for (int i = 0; i < tempList.size(); i++) {

								Reservation add = tempList.get(i);

								for (int j = 0; j < flight.getFirstClass().size(); j++) {

									Reservation seat = flight.getFirstClass().get(j);

									// add group members
									if (add.getRowNum() == seat.getRowNum()
											&& add.getSeatLetter().equals(seat.getSeatLetter())) {
										seat.setGroup(true);
										seat.setGroupName(groupName);
										seat.setName(nameList.get(0));
										nameList.remove(0);
										seat.setReserved(true);
										break;
									}
								}
							}

							for (int i = 0; i < tempList.size(); i++) {
								System.out.println(
										"Seat: " + tempList.get(i).getRowNum() + tempList.get(i).getSeatLetter());
							}
						}

					} else {
						System.out.println("Not enough seats available!\n");
					}

					System.out.println();
				}

				// ***************** ECONOMY CLASS *****************//

				if (serviceClass.equals("ECONOMY")) {

					int totalFree = 0; // total # of free seats

					// number of total free seats
					for (int i = 0; i < flight.getEconomyClass().size(); i++) {
						Reservation temp = flight.getEconomyClass().get(i);
						if (!temp.isReserved()) {
							totalFree++;
						}
					}

					// make sure there are more free seats than # of people
					if (totalFree >= numOfPeople) {

						// while not all people have been seated
						while (numOfPeople > 0) {

							freeSeats.clear();
							rowNum = 0;
							emptySeats = 0;
							tempList.clear();

							for (int i = 0; i < flight.getEconomyClass().size(); i++) {

								Reservation temp = flight.getEconomyClass().get(i);

								counter = temp.getRowNum(); // keep track of current row

								if (temp.getSeatLetter().equals("A")) {

									emptySeatsCounter = 0;
									freeSeatsTemp.clear();

									for (int j = 0; j < 6; j++) {

										Reservation temp2 = flight.getEconomyClass().get(i + j);

										if (!temp2.isReserved()) {

											emptySeatsCounter++;
											freeSeatsTemp.add(temp2.getSeatLetter());

										}
									}

									if (emptySeatsCounter > emptySeats) {
										emptySeats = emptySeatsCounter;
										rowNum = counter;
										freeSeats.clear();
										for (int k = 0; k < freeSeatsTemp.size(); k++) {
											freeSeats.add(freeSeatsTemp.get(k));
										}
									}
								}
							}

							for (int i = 0; i < flight.getEconomyClass().size(); i++) {

								Reservation temp = flight.getEconomyClass().get(i);

								if (numOfPeople == 0) {
									break;
								}

								for (int j = 0; j < freeSeats.size(); j++) {

									if (temp.getRowNum() == rowNum && temp.getSeatLetter().equals(freeSeats.get(j))) {
										if (numOfPeople == 0) {
											break;
										}

										tempList.add(temp);
										temp.setReserved(true);
										numOfPeople--;

									}
								}
							}

							for (int i = 0; i < tempList.size(); i++) {

								Reservation add = tempList.get(i);

								for (int j = 0; j < flight.getEconomyClass().size(); j++) {

									Reservation seat = flight.getEconomyClass().get(j);

									if (add.getRowNum() == seat.getRowNum()
											&& add.getSeatLetter().equals(seat.getSeatLetter())) {
										seat.setGroup(true);
										seat.setGroupName(groupName);
										seat.setName(nameList.get(0));
										nameList.remove(0);
										seat.setReserved(true);
										break;

									}
								}
							}
							for (int i = 0; i < tempList.size(); i++) {
								System.out.println(
										"Seat: " + tempList.get(i).getRowNum() + tempList.get(i).getSeatLetter());
							}
						}

					} else {
						System.out.println("Not enough seats available!\n");

					}

					System.out.println();
				}

				letter = "";
			}

			// *************************** CANCEL ***************************//

			if (letter.equals("C")) {

				String name = "";
				Boolean cancel = false;
				System.out.println("Cancel [I]ndividual or [G]roup? ");
				input = s.nextLine().toUpperCase();

				// remove individual reservation
				if (input.equals("I")) {

					System.out.println("Enter Name: ");
					name = s.nextLine();

					while (!cancel) {

						for (int i = 0; i < flight.getFirstClass().size(); i++) {

							Reservation temp = flight.getFirstClass().get(i);

							if (!temp.isGroup()) {

								if (temp.isReserved()) {

									if (temp.getName().equals(name)) {

										temp.setName(null);
										temp.setReserved(false);
										System.out.println("Successfully removed!\n");
										cancel = true;

									}
								}
							}
						}

						if (cancel) {
							break;
						}

						for (int i = 0; i < flight.getEconomyClass().size(); i++) {

							Reservation temp = flight.getEconomyClass().get(i);

							if (!temp.isGroup()) {

								if (temp.isReserved()) {

									if (temp.getName().equals(name)) {
										temp.setName(null);
										temp.setReserved(false);
										System.out.println("Successfully removed!\n");
										cancel = true;

									}
								}
							}
						}

						if (!cancel) {
							System.out.println("Reservation not found!\n");
						}
						break;
					}

				} else if (input.equals("G")) {

					System.out.println("Enter Group Name: ");
					name = s.nextLine();

					for (int i = 0; i < flight.getFirstClass().size(); i++) {

						Reservation temp = flight.getFirstClass().get(i);

						if (temp.getGroupName() != null && temp.getGroupName().equals(name)) {

							temp.setName(null);
							temp.setReserved(false);
							temp.setGroup(false);
							temp.setGroupName(null);

						}
					}

					for (int i = 0; i < flight.getEconomyClass().size(); i++) {

						Reservation temp = flight.getEconomyClass().get(i);

						if (temp.getGroupName() != null && temp.getGroupName().equals(name)) {

							temp.setName(null);
							temp.setReserved(false);
							temp.setGroup(false);
							temp.setGroupName(null);
						}
					}

					System.out.println("Successfully removed all reservations for " + name + "!\n");
				}

				letter = "";
			}

			// *************************** AVAILABILITY CHART ***************************//

			if (letter.equals("A")) {

				String serviceClass = "";
				System.out.println("Enter service class (first/economy): ");
				serviceClass = s.nextLine().toUpperCase();

				int counter = 0; // for iterating through the rows

				// ***************** FIRST CLASS *****************//

				if (serviceClass.equals("FIRST")) {

					System.out.println("First Class");

					for (int i = 0; i < flight.getFirstClass().size(); i++) {

						Reservation temp = flight.getFirstClass().get(i);

						if (temp.getRowNum() != counter) {

							counter++;
							System.out.print("\n" + counter + ": ");
						}

						if (!temp.isReserved()) {

							System.out.print(temp.getSeatLetter() + " ");
						}
					}
				}

				counter = 9;

				// ***************** ECONOMY CLASS *****************//

				if (serviceClass.equals("ECONOMY")) {

					System.out.println("Economy Class");

					for (int i = 0; i < flight.getEconomyClass().size(); i++) {

						Reservation temp = flight.getEconomyClass().get(i);

						if (temp.getRowNum() != counter) {

							counter++;
							System.out.print("\n" + counter + ": ");
						}

						if (!temp.isReserved()) {

							System.out.print(temp.getSeatLetter() + " ");
						}
					}
				}
				System.out.println("\n");
				letter = "";
			}

			// *************************** MANIFEST ***************************//

			if (letter.equals("M")) {

				String serviceClass = "";
				System.out.println("Enter service class (first/economy): ");
				serviceClass = s.nextLine().toUpperCase();

				// ***************** FIRST CLASS *****************//

				if (serviceClass.equals("FIRST")) {
					System.out.println("First Class");

					for (int i = 0; i < flight.getFirstClass().size(); i++) {

						Reservation temp = flight.getFirstClass().get(i);

						if (temp.isReserved()) {
							System.out.println(temp.getRowNum() + temp.getSeatLetter() + ": " + temp.getName());
						}
					}
					System.out.println();

				}

				// ***************** ECONOMY CLASS *****************//

				if (serviceClass.equals("ECONOMY")) {

					System.out.println("Economy Class");

					for (int i = 0; i < flight.getEconomyClass().size(); i++) {

						Reservation temp = flight.getEconomyClass().get(i);

						if (temp.isReserved()) {
							System.out.println(temp.getRowNum() + temp.getSeatLetter() + ": " + temp.getName());

						}
					}
					System.out.println();
				}
			}

			// *************************** QUIT ***************************//

			if (letter.equals("Q")) {
				done = true;
			}

			letter = "";
		}

		System.out.println("Good Bye!");
		s.close();

		// save all reservations into CL34 file
		try {

			File output = new File(flightName);
			PrintWriter pw = new PrintWriter(new FileWriter(output));

			for (Reservation r : flight.getFirstClass()) {

				if (r.isGroup()) {
					pw.println(r.getRowNum() + " " + r.getSeatLetter() + " " + r.getName() + " " + r.getGroupName());
				} else {
					pw.println(r.getRowNum() + " " + r.getSeatLetter() + " " + r.getName());
				}

			}

			for (Reservation r : flight.getEconomyClass()) {

				if (r.isGroup()) {
					pw.println(r.getRowNum() + " " + r.getSeatLetter() + " " + r.getName() + " " + r.getGroupName());
				} else {
					pw.println(r.getRowNum() + " " + r.getSeatLetter() + " " + r.getName());
				}

			}

			pw.close();

		} catch (FileNotFoundException x) {
			System.out.println(x.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
