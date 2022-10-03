/**
* 2022 CS151 Programming Assignment 2 Solution
* @author Rachel Liao
* @version 1.0 09/30/2022
*/

/**
 * Reservation contains name, service class, seat, group name, if reserved, if
 * group.
 */
public class Reservation {

	private String name;
	private boolean isGroup;
	private String groupName;
	private boolean isReserved;

	private int rowNum;
	private String seatLetter;

	/**
	 * Return individual's name on reservation
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set name of event
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Return if reservation is part of a group
	 * 
	 * @return isGroup - boolean
	 */
	public boolean isGroup() {
		return isGroup;
	}

	/**
	 * Set if reservation is part of group
	 * 
	 * @param isGroup precondition: isGroup must be true/false
	 */
	public void setGroup(boolean isGroup) {
		this.isGroup = isGroup;
	}

	/**
	 * Return group name for reservation
	 * 
	 * @return groupName
	 */
	public String getGroupName() {
		return groupName;
	}

	/**
	 * Set group name for reservation
	 * 
	 * @param groupName
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	/**
	 * Return row number for seat
	 * 
	 * @return rowNum
	 */
	public int getRowNum() {
		return rowNum;
	}

	/**
	 * Set row number for seat
	 * 
	 * @param rowNum precondition: rowNum should not be null
	 */
	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}

	/**
	 * Return seat letter for seat
	 * 
	 * @return seatLetter
	 */
	public String getSeatLetter() {
		return seatLetter;
	}

	/**
	 * Set seat letter for seat
	 * 
	 * @param seatLetter precondition: seatLetter should not be null
	 */
	public void setSeatLetter(String seatLetter) {
		this.seatLetter = seatLetter;
	}

	/**
	 * Return if seat is reserved
	 * 
	 * @return isReserved - true/false
	 */
	public boolean isReserved() {
		return isReserved;
	}

	/**
	 * Set if seat is reserved
	 * 
	 * @param isReserved precondition: isReserved should be true/false
	 */
	public void setReserved(boolean isReserved) {
		this.isReserved = isReserved;
	}
}
