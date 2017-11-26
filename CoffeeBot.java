/**
 * info1103 - assignment 1
 * Syed Umar Kaliyadan
 * skal7504
 */

import java.util.*;
public class CoffeeBot
{
	static Scanner key = new Scanner(System.in);
	public static void main(String[] args)
	{
		if(args.length==0)
		{
			System.out.println("No arguments. System terminating.");
			System.exit(1);
		}
		if(args.length<2)
		{
			System.out.println("Not enough arguments. System terminating.");
			System.exit(1);
		}
		if(args.length>2)
		{
			System.out.println("Too many arguments. System terminating.");
			System.exit(1);
		}
		int supc = Integer.parseInt(args[0]);
		int sups = Integer.parseInt(args[1]);
		if(supc < 0  && sups < 0)
		{
			System.out.println("Negative supply chain. System terminating.");
			System.exit(1);
		}
		if(supc < 0)
		{
			System.out.println("Negative supply of coffee cups. System terminating.");
			System.exit(1);
		}
		if(sups < 0)
		{
			System.out.println("Negative supply of coffee shots. System terminating.");
			System.exit(1);
		}
		System.out.print("Hello, what's your name? ");
		String name = key.nextLine();
		System.out.print("Would you like to order some coffee, "+name+"? (y/n) ");
		char c = key.nextLine().trim().charAt(0);
		while(c != 'y' && c!= 'n')
		{
			System.out.println("Invalid response. Try again.");
			System.out.print("Would you like to order some coffee, "+name+"? (y/n) ");
			c = key.nextLine().trim().charAt(0);
		}
		if(c == 'n')
		{
			System.out.println("Come back next time, "+name);
			System.exit(1);
		}
		System.out.println("Great! Let's get started.");
		System.out.println("\nOrder selection");
		System.out.println("---------------\n");
		if(supc == 1 && sups == 1)
		{
			System.out.println("There is "+supc+" coffee cup in stock and each costs $2.00.");
			System.out.println("There is "+sups+" coffee shot in stock and each costs $1.00.");
		}
		else if(supc == 1 && sups > 1)
		{
			System.out.println("There is "+supc+" coffee cup in stock and each costs $2.00.");
			System.out.println("There are "+sups+" coffee shots in stock and each costs $1.00.");
		}
		else if(supc > 1 && sups == 1)
		{
			System.out.println("There are "+supc+" coffee cups in stock and each costs $2.00.");
			System.out.println("There is "+sups+" coffee shot in stock and each costs $1.00.");
		}
		else
		{
			System.out.println("There are "+supc+" coffee cups in stock and each costs $2.00.");
			System.out.println("There are "+sups+" coffee shots in stock and each costs $1.00.");
		}
		System.out.print("\nHow many cups of coffee would you like? ");
		int ncup = key.nextInt();
		System.out.print("\n");
		if(ncup ==0)
		{
			System.out.println("No cups, no coffee. Goodbye.");
			System.exit(1);
		}
		if(ncup < 0)
		{
			System.out.println("Does not compute. System terminating.");
			System.exit(1);
		}
		if(ncup > supc)
		{
			System.out.println("Not enough stock. Come back later");
			System.exit(1);
		}
		double[] CoffeeC= new double[ncup];
		for(int i = 0; i < ncup; i++)
		{
			System.out.print("How many coffee shots in cup "+(i+1)+"? ");
			CoffeeC[i]=key.nextInt();
			if(CoffeeC[i] < 0)			//Main Statement
			{
				System.out.println("Does not compute. Try again.");
				i=i - 1;
				continue;
				/*System.out.print("How many coffee shots in cup "+(i+1)+"? ");
				CoffeeC[i]=key.nextInt();
				if(CoffeeC[i] > sups)	//Sub-statement
				{
					System.out.println("There is only "+sups+" coffee shots left. Try again.");
					System.out.print("How many coffee shots in cup "+(i+1)+"? ");
					CoffeeC[i]=key.nextInt();
				}*/
			}
			if(CoffeeC[i] > sups)		//Main statement
			{
				if(sups == 1)
				{
					System.out.println("There is only "+sups+" coffee shot left. Try again.");
				}
				else
				{
					System.out.println("There are only "+sups+" coffee shots left. Try again.");
				}
				i=i - 1;
				continue;
				/*System.out.print("How many coffee shots in cup "+(i+1)+"? ");
				CoffeeC[i]=key.nextInt();
				if(CoffeeC[i] < 0)		//Sub statement
				{
					System.out.println("Does not compute. Try again.");
					System.out.print("How many coffee shots in cup "+(i+1)+"? ");
					CoffeeC[i]=key.nextInt();
				}*/
			}
			sups = sups - (int)CoffeeC[i];
			// System.out.println("SUPS = "+sups);  Code to test if sups is updating in real-time.
		}
		key.nextLine();
		double tcost = 0.00;
		System.out.println("\nOrder summary");
		System.out.println("-------------\n");
		for(int i=0; i< ncup; i++)
		{
			if(CoffeeC[i] == 1)
			{
				System.out.print("Cup "+(i+1)+" has "+(int)CoffeeC[i]+" shot and will cost $");
			}
			else
			{
				System.out.print("Cup "+(i+1)+" has "+(int)CoffeeC[i]+" shots and will cost $");
			}
			System.out.format("%.2f%n", 2+CoffeeC[i]);
			tcost += (2+CoffeeC[i]);
		}
		if(ncup == 1)
		{
			System.out.println("\n"+ncup+" coffee to purchase.");
		}
		else
		{
			System.out.println("\n"+ncup+" coffees to purchase.");
		}
		System.out.print("Purchase price is $");
		System.out.format("%.2f%n", tcost);
		System.out.print("Proceed to payment? (y/n) ");
		char pay = key.nextLine().trim().charAt(0);
		while(pay != 'y' && pay != 'n')
		{
			System.out.println("Invalid response. Try again.");
			System.out.println(ncup+" coffee to purchase.");
			System.out.println("Purchase price is $"+tcost);
			System.out.println("Proceed to payment? (y/n)");
			pay = key.nextLine().trim().charAt(0);
		}
		if(pay == 'n')
		{
			System.out.println("Come back next time, "+name+".");
			System.exit(1);
		}
		String OnlyCash = new String();
		System.out.println("\nOrder payment");
		System.out.print("-------------\n");
		double tcash = 0.00;
		double dcash = 0.00;
		while(true)
		{
			System.out.format("$%.2f", tcost);
			System.out.print(" remains to be paid. Enter coin or note: ");
			String cash = key.nextLine();
			char dollar = cash.charAt(0);
			if(dollar != '$')
			{
				System.out.println("Invalid coin or note. Try again.");
				continue;
			}
			StringBuffer sb = new StringBuffer(cash);
			sb.deleteCharAt(0);
			OnlyCash = sb.toString();
			tcash = Double.parseDouble(OnlyCash);
			/*if(tcash < 1 && tcost > 0)
			{
				System.out.println("Invalid coin or note. Try again.");
				continue;
			}
			if (tcash < 1 && tcash > 0)
			{

			}*/
			//System.out.println("Dollar="+dollar+"\nCash="+OnlyCash+"\n");

			if(tcash < tcost)
			{
				dcash += tcash;
				tcost -= tcash;
				continue;
			}
			break;
		}
		if(tcash == tcost)
		{
			dcash += tcash;
			System.out.format("\nYou gave :$%.2f\n", dcash);
			System.out.println("Perfect! No change given.");
		}
		if(tcash > tcost)
		{
			dcash += tcash;
			System.out.format("\nYou gave $%.2f\n", dcash);
			double dechange = tcash - tcost;
			int change = (int)tcash - (int)tcost;
			dechange -= (double)change;
			System.out.println("Your change:");
			while(true)
			{
				if(change >= 100)
				{
					int q = change/100;
					if(q >= 1)
					{
						System.out.println(q+" x $100.00");
						change = change % 100;
						continue;
					}
				}
				if(change >= 50)
				{
					int q = change/50;
					if(q >= 1)
					{
						System.out.println(q+" x $50.00");
						change = change % 50;
						continue;
					}
				}
				if(change >= 20)
				{
					int q = change/20;
					if(q >= 1)
					{
						System.out.println(q+" x $20.00");
						change = change % 20;
						continue;
					}
				}
				if(change >= 10)
				{
					int q = change/10;
					if(q >= 1)
					{
						System.out.println(q+" x $10.00");
						change = change % 10;
						continue;
					}
				}
				if(change >= 5)
				{
					int q = change/5;
					if(q >= 1)
					{
						System.out.println(q+" x $5.00");
						change = change % 5;
						continue;
					}
				}
				if(change >= 2)
				{
					int q = change/2;
					if(q >= 1)
					{
						System.out.println(q+" x $2.00");
						change = change % 2;
						continue;
					}
				}
				if(change >= 1)
				{
					int q = change/1;
					if(q >= 1)
					{
						System.out.println(q+" x $1.00");
						change = change % 1;
						continue;
					}
				}
				if(dechange >= 0.50)
				{
					double q = dechange/0.50;
					if(q >= 1.0)
					{
						System.out.println((int)q+" x $0.50");
						dechange = dechange % 0.50;
						continue;
					}
				}
				if(dechange >= 0.20)
				{
					double q = dechange/0.20;
					if(q >= 1.0)
					{
						System.out.println((int)q+" x $0.20");
						dechange = dechange % 0.20;
						continue;
					}
				}
				if(dechange >= 0.10)
				{
					double q = dechange/0.10;
					if(q >= 1.0)
					{
						System.out.println((int)q+" x $0.10");
						dechange = dechange % 0.10;
						continue;
					}
				}
				if(dechange >= 0.05)
				{
					double q = dechange/0.05;
					if(q >= 1.0)
					{
						System.out.println((int)q+" x $0.05");
						dechange = dechange % 0.05;
						continue;
					}
				}
				if(change == 0)
				{
					break;
				}

			}
		}
		System.out.println("\nThank you, "+name+".");
		System.out.println("See you next time.");
	}
}
