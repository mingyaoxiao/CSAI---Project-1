package part2;

import java.util.ArrayList;
import java.util.Comparator;

public class BinaryHeap<E>
{	
	public ArrayList<E> heap;
	public Comparator<E> comp;
	
	public BinaryHeap(Comparator<E> c)
	{
		heap = new ArrayList<E>();
		comp = c;
	}
	
	public void clear()
	{
		heap.clear();
	}
	
	public boolean contains(E c)
	{
		return heap.contains(c);
	}
	
	public int parent(int n)
	{
		return (n - 1) / 2;
	}
	
	public int leftChild(int n)
	{
		return 2 * n + 1;
	}
	
	public int rightChild(int n)
	{
		return 2 * n + 2;
	}
	
	public void insert(E c)
	{
		heap.add(c);
		int index = heap.size() - 1;
		heapifyUp(index);
	}
	
	public void delete(E c)
	{
		int index = heap.indexOf(c);
		if(index == heap.size() - 1)
		{
			heap.remove(heap.size() - 1);
			return;
		}
		E temp = heap.remove(heap.size() - 1);
		heap.set(index, temp);
		if(index > 0 && comp.compare(heap.get(index), heap.get(parent(index))) < 0)
			heapifyUp(index);
		else
			heapifyDown(index);
	}
	
	public void swap(int a, int b)
	{
		E c = heap.get(a);
		heap.set(a, heap.get(b));
		heap.set(b, c);
	}
	
	public E peek()
	{
		if(heap.size() == 0)
			return null;
		else
			return heap.get(0);
	}
	
	public E poll()
	{
		if(heap.size() == 0)
			return null;
		else if(heap.size() == 1)
		{
			return heap.remove(0);
		}
		else
		{
			E min = heap.get(0);
			heap.set(0, heap.remove(heap.size() - 1));
			heapifyDown(0);
			return min;
		}
	}
	
	public void heapifyUp(int x)
	{
		while(x > 0 && comp.compare(heap.get(x), heap.get(parent(x))) < 0)
		{
			swap(x, parent(x));
			x = parent(x);
		}
	}
	
	public void heapifyDown(int x)
	{
		int l = leftChild(x);
		int r = rightChild(x);
		int min = Integer.MIN_VALUE;
		if(l <= heap.size() - 1 && comp.compare(heap.get(l), heap.get(x)) < 0)
			min = l;
		else
			min = x;
		if(r <= heap.size() - 1 && comp.compare(heap.get(r), heap.get(min)) < 0)
			min = r;
		if(x != min)
		{
			swap(x, min);
			heapifyDown(min);
		}
	}
	/*
	public static void main(String[] args)
	{
		BinaryHeap<String> pq = new BinaryHeap<String>(String::compareTo);
		pq.insert("cat");
		pq.insert("dog");
		pq.insert("bee");
		System.out.println("Smallest is: " + pq.peek());
		System.out.println("Smallest again: " + pq.poll());
		System.out.println("Next smallest is: " + pq.poll());
		System.out.println("Is it empty? : " + pq.heap.isEmpty());
		pq.insert("eagle");
		System.out.println("Next smallest is: " + pq.poll());
		System.out.println("Next smallest is: " + pq.poll());
		System.out.println("Is it empty? : " + pq.heap.isEmpty());
		System.out.println("Min of empty queue: " + pq.peek());
		System.out.println("Remove min of empty queue: " + pq.poll());
		pq.insert("bear");
		System.out.println("Smallest is: " + pq.peek());
		System.out.println("Smallest again: " + pq.poll());
		pq.insert("cat");
		pq.insert("dog");
		pq.insert("sheep");
		pq.insert("cow");
		pq.insert("eagle");
		pq.insert("bee");
		pq.insert("lion");
		pq.insert("tiger");
		pq.insert("zebra");
		pq.insert("ant");
		System.out.println("Bigger example:");
		System.out.println("Smallest is: " + pq.poll());
		System.out.println("Next smallest is: " + pq.poll());
		System.out.println("Next smallest is: " + pq.poll());
		System.out.println("Next smallest is: " + pq.poll());
		System.out.println("Next smallest is: " + pq.poll());
		System.out.println("Next smallest is: " + pq.poll());
		System.out.println("Next smallest is: " + pq.poll());
		System.out.println("Next smallest is: " + pq.poll());
		System.out.println("Next smallest is: " + pq.poll());
		System.out.println("Next smallest is: " + pq.poll());
		System.out.println("Next smallest is: " + pq.poll());
		pq.insert("lol");
		pq.clear();
		if(pq.contains("lol"))
			System.out.println("yes");
		else
			System.out.println("no");
	}
	*/
}










































