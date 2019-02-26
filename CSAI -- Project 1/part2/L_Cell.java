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
	public int search;
	
	L_Cell()
	{
		prev = null;
		next = null;
	}
	
	public static Comparator<L_Cell> larger = new Comparator<L_Cell>()
	{
		public int compare(L_Cell a, L_Cell b)
		{
			if((a.gVal + a.hVal) < (b.gVal + b.hVal))
				return -1;
			else if((a.gVal + a.hVal) > (b.gVal + b.hVal))
				return 1;
			else
			{
				if(a.gVal > b.gVal)
					return -1;
				else if(a.gVal < b.gVal)
					return 1;
				else
					return 0;
			}
		}
	};
	
	public static Comparator<L_Cell> smaller = new Comparator<L_Cell>()
	{
		public int compare(L_Cell a, L_Cell b)
		{
			if((a.gVal + a.hVal) < (b.gVal + b.hVal))
				return -1;
			else if((a.gVal + a.hVal) > (b.gVal + b.hVal))
				return 1;
			else
			{
				if(a.gVal < b.gVal)
					return -1;
				else if(a.gVal > b.gVal)
					return 1;
				else
					return 0;
			}
		}
	};
}