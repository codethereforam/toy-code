package priv.thinkam.toycode.algorithm.old.linklist;

public class Test {
	public static void main(String...args) {
		Linklist ll = new Linklist();
		ll.insertFirst(1);
		ll.insertFirst(2);
		ll.insertFirst(3);
		ll.insertFirst(4);
		ll.insertFirst(5);
		
		ll.display();
		System.out.println();
		
		Node temp = null;
		while(!ll.isEmpty()) {
			temp = ll.deleteFirst();
			temp.display();
			System.out.print(" ");
		}
	}
}
