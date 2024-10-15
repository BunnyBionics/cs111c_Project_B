public class LinkedFrontBackCappedList<T> implements FrontBackCappedList<T> {

	private Node head, tail;
	private int size, capacity;

	public LinkedFrontBackCappedList(int capacity) {
		head = null;
		tail = null;
		size = 0;
		this.capacity = capacity;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder("[");
		Node current = head;
		while (current != null) {
			if (current != head) {
				builder.append(", ");
			}
			builder.append(current.data);
			current = current.next;
		}

		builder.append("]\tsize=").append(size).append("\tcapacity=").append(capacity);
		if (size != 0) {
			builder.append("\thead=").append(head.data).append(" tail=").append(tail.data);
		}

		return builder.toString();
	}

	@Override
	public boolean addFront(T newEntry) {
		if (size == capacity) {
			return false;
		}

		if (size == 0) {
			head = new Node(newEntry, null);
			tail = head;
			size++;
			return true;
		}

        head = new Node(newEntry, head);
		size++;
		return true;
	}

	@Override
	public boolean addBack(T newEntry) {
		if (size == capacity) {
			return false;
		}

		if (size == 0) {
			head = new Node(newEntry, null);
			tail = head;
			size++;
			return true;
		}

		tail.next = new Node(newEntry, null);
		tail = tail.next;
		size++;
		return true;
	}

	@Override
	public T removeFront() {
		if (size == 0) {
			return null;
		}

		T removed = head.data;
		head = head.next;
		size--;
		return removed;
	}

	@Override
	public T removeBack() {
		if (size == 0) {
			return null;
		}

		T removed = tail.data;
		Node current = head;
		if (size == 1) {
			clear();
			return removed;
		}

		while (current.next != tail) {
			current = current.next;
		}

		current.next = null;
		tail = current;
		size--;
		return removed;
	}

	@Override
	public void clear() {
		head = null;
		tail = null;
		size = 0;
	}

	@Override
	public T getEntry(int givenPosition) {
		if (givenPosition < 0 || givenPosition > size - 1) {
			return null;
		}

		Node current = head;
		for (int i = 0; i < givenPosition; i++) {
			current = current.next;
		}

		return current.data;
	}

	@Override
	public int indexOf(T anEntry) {
		Node current = head;
		int position = 0;
		while (current != null) {
			if (current.data.equals(anEntry)) {
				return position;
			}

			current = current.next;
			position++;
		}

		return -1;
	}

	@Override
	public int lastIndexOf(T anEntry) {
		Node current = head;
		int position = 0;
		int lastIndex = -1;
		while (current != null) {
			if (current.data.equals(anEntry)) {
				lastIndex = position;
			}

			current = current.next;
			position++;
		}

		return lastIndex;
	}

	@Override
	public boolean contains(T anEntry) {
		return (indexOf(anEntry) != -1);
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return (size == 0);
	}

	@Override
	public boolean isFull() {
		return (size == capacity);
	}

	public class Node {
		public T data; 
		public Node next; 

		private Node(T dataValue) {
			data = dataValue;
			next = null;
		}

		private Node(T dataValue, Node nextNode) {
			data = dataValue;
			next = nextNode;
		}

		private T getData() {
			return data;
		}

		private void setData(T newData) {
			data = newData;
		}

		private Node getNextNode() {
			return next;
		}

		private void setNextNode(Node nextNode) {
			next = nextNode;
		} 
	} 
}
