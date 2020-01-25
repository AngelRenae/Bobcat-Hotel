package project;

import java.util.Scanner;

public class BobcatHotel {

	public static void main(String[] args) {
		int guests, nights, room, mealPackage, anwr;
		double roomCost = 0;	// cost of single night in room selected
		double bRoomCost = 0;	// cost of room during stay w/o discount
		int disNights = 0;		// amount of nights that are discounted
		double bMealCost = 0;	// meal cost during stay
		double ptc = 0;			// preliminary total cost
		double tc = 0;			// final total cost
		double disAAA = 0;		// total discount for AAA Members
		double disCMem = 0;		// total discount for Club Members
		boolean aMember, cMember = false;										// if someone is a member or not
		String mealC = "Please select your desired meal package (enter 0/1/2";	// first part of meal prompt
		Scanner input = new Scanner(System.in);

		// -------------------------------------------------- ROOM OPTIONS
		System.out.println("ROOM OPTIONS \n"
				+ "1. Single @ $50.50 per night \n"
				+ "2. Double @ $75.00 per nigt \n"
				+ "3. Triple @ $100.75 per night \n"
				+ "4. King @ $150.25 per night \n"
				+ "5. Master Suit @ $225.50 per night \n");

		System.out.print("Number of guests: ");
		guests = input.nextInt();

		System.out.print("Please enter your choice of room (enter 1/2/3/4/5 corresponding to the options shown above): ");
		room = input.nextInt();

		System.out.print("How many nights will you spend in the room? ");
		nights = input.nextInt();

		// -------------------------------------------------- MEMBERSHIPS

		System.out.print("Are you an AAA Member (enter 1 for yes, 0 for no)? ");
		anwr = input.nextInt();;
		if(anwr == 1) {
			aMember = true;
		} else {
			aMember = false;
		}

		System.out.print("Are you a club member (enter 1 for yes, 0 for no)? ");
		anwr = input.nextInt();
		if(anwr == 1) {
			cMember = true;
		} else {
			cMember = false;
		}

		System.out.println("");

		int randomNum = (int)(Math.random() * 10) + 1;
		if (cMember && randomNum >= 4) {
			System.out.println("Congratulations! You've qualified for our \"Stay 4 nights get 1 free promotion\" \n" 
					+ "Discount will be applied during checkout depending on the number of days\r\n");
		} else if(cMember && randomNum < 4) {
			System.out.println("Unfortunately, you didn't qualify for our \"Stay 4 nights get 1 free promotion\". Better luck next time!\n");
		}

		// -------------------------------------------------- MEALS

		System.out.println("MEAL PACKAGES (PRICES ARE SHOWN PER NIGHT) \n"
				+ "0. Complentary @ $0 per guest \n"
				+ "1. Standard @ $30 per guest \n"
				+ "2. Deluxe @ $50 per guest");

		if(room == 5){												// if master suit is picked
			System.out.println("3. Indulgence @ $85 per guest");	// shows indulgence (only for master)
			mealC = mealC.concat("/3");								// adds /3 to "select your meal" prompt, reppin indulgence
		}
		System.out.print("\n" + mealC + " corresponding to the options shown above): ");
		mealPackage = input.nextInt();

		// -------------------------------------------------- CALCULATIONS

		// base room cost
		switch(room) {
		case 1:
			roomCost = 50.50;
			break;
		case 2:
			roomCost = 75;
			break;
		case 3:
			roomCost = 100.75;
			break;
		case 4:
			roomCost = 150.25;
			break;
		case 5:
			roomCost = 225.5;
			break;
		}

		bRoomCost = roomCost * nights;

		if(!cMember || nights <= 4 || randomNum < 4) {
			bRoomCost = roomCost * nights;
		} else {
			disNights = nights/ 4;
			//disNights = (nights - (nights % 4)) / 4;
		}

		// meal cost
		switch (mealPackage) {
		case 0:
			bMealCost = 0;
			break;
		case 1:
			bMealCost = 30.00;
			break;
		case 2:
			bMealCost = 50.00;
			break;
		case 3:
			bMealCost = 85.00;
			break;
		}
		bMealCost = bMealCost * nights * guests;

		// preliminary total cost
		ptc = bRoomCost + bMealCost;
		// club membership discount
		disCMem = roomCost * disNights;
		// AAA discount
		if(aMember) {
			disAAA = ptc * 0.1;
		}
		// total cost
		tc = ptc - disAAA - disCMem;
		
		// -------------------------------------------------- CHECKOUT

		System.out.print("\nCHECKOUT\n"
				+ "Room Cost:\t\t $" + bRoomCost);
		if(bMealCost > 0.0) {											// prints if meal isn't complementary
			System.out.print("\nMeal Cost:\t\t+$" + bMealCost);
		}
		System.out.print("\nPreliminary Total Cost:\t $" + ptc);		// prints preliminary total cost
		if(aMember) {													// if AAA member
			System.out.print("\nAAA Discount:\t\t-$" + disAAA);
		} 
		if (cMember && randomNum > 4) {									// if got Club discount
			System.out.print("\nClub Member Discount:\t-$" + disCMem);
		}
		if(!aMember && (!cMember || randomNum <4)) { 					// if not and AAA member AND  not a Club member or made the pool
			System.out.print("\nNo Discounts Applied");
		}
		System.out.print("\nTotal Cost of Booking:\t $" + tc);
		
		input.close();
	}

}
