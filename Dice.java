//created with the help of Scott Mayfield

import java.util.Random;

public class Dice
{
	private static Random generator = new Random();
	private int sides;

	public Dice(int sides)
	{
		sides = sides;
	}

	// USAGE
	// Dice myDice = new Dice(6); // 6-sided die (1-6)
	// int roll = myDice.Roll();

	// Dice myDice = new Dice(7); // 7-sided die (0-6)
	// int rollWithZero = myDice.Roll() - 1;
	public int Roll()
	{
		return (generator.nextInt(sides) + 1);
	}

	// USAGE
	// Dice myDice = new Dice(10); // 10-sided die (1-10)
	// int[] rolls = myDice.RollLots(3);
	public int[] RollLots(int numTimes)
	{
		int[] temp = new int[numTimes];
		for (int i = 0; i <= numTimes; i++)
		{
			temp[i] = (generator.nextInt(sides) + 1);
		}
		return temp;
	}

	// USAGE
	// Dice myDice = new Dice(6);
	// int roll = myDice.RollLotsAndAdd(3); // possible result is 3-18
	public int RollLotsAndAdd(int numTimes)
	{
		int temp = 0;
		for (int i = 0; i <= numTimes; i++)
		{
			temp += (generator.nextInt(sides) + 1);
		}
		return temp;
	}
}