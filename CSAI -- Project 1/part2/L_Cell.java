package part2;

import java.util.Comparator;

public class L_Cell 
{
	public boolean isBlocked;
	public int xCoor;
	public int yCoor;
	public double gVal;
	public double hVal;
	public boolean actionRemoved;
	public L_Cell prev;
	public L_Cell next;
	
	L_Cell()
	{
		prev = null;
		next = null;
	}
	
	public static Comparator<L_Cell> comp = new Comparator<L_Cell>()
	{
		public int compare(L_Cell a, L_Cell b)
		{
			if((a.gVal + a.hVal) > (b.gVal + b.hVal))
				return 1;
			if((a.gVal + a.hVal) < (b.gVal + b.hVal))
				return -1;
			return 0;
		}
	};
}
