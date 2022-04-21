package priv.thinkam.toycode.algorithm.old.linklist;

public class Node {
	private int data;
	private Node next;
	
	public Node(int data) {
		this.data = data;
	}
	
	public void display() {
		System.out.print(data);
	}
	
	public void setNext(Node link) {
		next = link;
	}
	
	public Node getNext() {
		return next;
	}
}
