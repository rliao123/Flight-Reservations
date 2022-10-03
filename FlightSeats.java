
/**
* 2022 CS151 Programming Assignment 2 Solution
* @author Rachel Liao
* @version 1.0 09/30/2022
*/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * FlightSeats is a collection to manage and store reservations.
 */
public class FlightSeats {

	private ArrayList<Reservation> firstClass;
	private ArrayList<Reservation> economyClass;

	/**
	 * Return first class reservation list
	 * 
	 * @return firstClass
	 */
	public ArrayList<Reservation> getFirstClass() {
		return firstClass;
	}

	/**
	 * Return economy class reservation list
	 * 
	 * @return economyClass
	 */
	public ArrayList<Reservation> getEconomyClass() {
		return economyClass;
	}

	/**
	 * Constructs a first class and an economy class array list with Reservation
	 * objects
	 */
	public FlightSeats(String flightName) {

		firstClass = new ArrayList<Reservation>();
		economyClass = new ArrayList<Reservation>();

		for (int i = 1; i < 3; i++) {

			Reservation a = new Reservation();
			a.setSeatLetter("A");
			a.setRowNum(i);
			a.setReserved(false);
			a.setName(null);

			Reservation b = new Reservation();
			b.setSeatLetter("B");
			b.setRowNum(i);
			b.setReserved(false);
			b.setName(null);

			Reservation c = new Reservation();
			c.setSeatLetter("C");
			c.setRowNum(i);
			c.setReserved(false);
			c.setName(null);

			Reservation d = new Reservation();
			d.setSeatLetter("D");
			d.setRowNum(i);
			d.setReserved(false);
			d.setName(null);

			firstClass.add(a);
			firstClass.add(b);
			firstClass.add(c);
			firstClass.add(d);

		}

		for (int i = 10; i < 30; i++) {

			Reservation a = new Reservation();
			a.setSeatLetter("A");
			a.setRowNum(i);
			a.setReserved(false);

			Reservation b = new Reservation();
			b.setSeatLetter("B");
			b.setRowNum(i);
			b.setReserved(false);

			Reservation c = new Reservation();
			c.setSeatLetter("C");
			c.setRowNum(i);
			c.setReserved(false);

			Reservation d = new Reservation();
			d.setSeatLetter("D");
			d.setRowNum(i);
			d.setReserved(false);

			Reservation e = new Reservation();
			e.setSeatLetter("E");
			e.setRowNum(i);
			e.setReserved(false);

			Reservation f = new Reservation();
			f.setSeatLetter("F");
			f.setRowNum(i);
			f.setReserved(false);

			economyClass.add(a);
			economyClass.add(b);
			economyClass.add(c);
			economyClass.add(d);
			economyClass.add(e);
			economyClass.add(f);

		}

		// read from CL34 or creates the file if it does not exist
		try {

			File file = new File(flightName);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String line;
			boolean done = false;

			while (!done) {

				line = br.readLine();

				if (line == null) {
					done = true;
					break;
				}

				String[] words = line.split(" ");

				if (Integer.parseInt(words[0]) <= 2) {

					for (int i = 0; i < firstClass.size(); i++) {

						Reservation temp = firstClass.get(i);

						if (Integer.parseInt(words[0]) == temp.getRowNum()) {

							if (words[1].equals(temp.getSeatLetter())) {

								temp.setName(words[2]);

								if (words.length > 3) {

									temp.setGroup(true);
									temp.setGroupName(words[3]);

								} else {

									temp.setGroup(false);

								}

								if (words[2].equals("null")) {

									temp.setReserved(false);

								} else {

									temp.setReserved(true);

								}
							}
						}
					}
				} else {

					for (int i = 0; i < economyClass.size(); i++) {

						Reservation temp = economyClass.get(i);

						if (Integer.parseInt(words[0]) == temp.getRowNum()) {

							if (words[1].equals(temp.getSeatLetter())) {

								temp.setName(words[2]);

								if (words.length > 3) {

									temp.setGroup(true);
									temp.setGroupName(words[3]);

								} else {

									temp.setGroup(false);

								}
								if (words[2].equals("null")) {

									temp.setReserved(false);

								} else {

									temp.setReserved(true);

								}
							}
						}
					}
				}
			}

			br.close();
			fr.close();

		} catch (IOException x) {
			System.out.println(x.getMessage());
		}
	}
}
