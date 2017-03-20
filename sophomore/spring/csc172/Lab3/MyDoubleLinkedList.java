package csc172lab3;

/*
 * Henry Le
 * 
 * February 1, 2014
 * 
 * CSC 172
 * 
 * Lab 3
 * 
 * Lab Partner : Roy Eldar & Murray Wan
 * 
 * TA : Ian Davison
 */

//part2 of lab3
public interface MyDoubleLinkedList {
	public void insert(Object x);
	public void delete(Object x);
	public boolean lookup(Object x);
	public boolean isEmpty();
	public void printList();
	public void printListRev();
}
