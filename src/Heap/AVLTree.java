package Heap;

import java.util.ArrayList;

public class AVLTree {

	public Node root;
	public int height;
	public int numberOfNodes;
	public Node last;
	
	public AVLTree(){
		this.root = null;
		this.height = -1;
		this.numberOfNodes = 0;
		this.last = null;
		
	}
	
	public AVLTree(Node node){
		this.root = node;
		this.height = 0;
		this.numberOfNodes = 1;
		this.last = node;
	}
	
	public boolean search(Comparable value){
		Node current = root;
		while(current.getData().compareTo(value) != 0 && current.hasChildren()){
			if(current.getData().compareTo(value) < 0){
				current = current.getRightChild();
			} else {
				current = current.getLeftChild();
			}
		}
		if (current.getData().compareTo(value) == 0){
			return true;
		}
		return false;
	}
	
	public boolean balance(){
		return true;
	}
	
	public boolean insert(Node node){
		return true;
	}
	
	public boolean delete(Node node){
		return true;
	}
	
	
	public ArrayList<Comparable> inorder(Node node){
		ArrayList<Comparable> list = new ArrayList<>();
		if(node.hasChildren()){
			if(node.getLeftChild() != null){
				list.addAll(inorder(node.getLeftChild()));
			}
			list.add(node.getData());
			if(node.getRightChild() != null){
				list.addAll(inorder(node.getRightChild()));
			}
		} else {
			list.add(node.getData());
		}
		return list;
	}
	
	public int count(Comparable xZero, Comparable xOne){
		return countFromRoot(xZero, xOne, root);
	}
	
	public int countFromRoot(Comparable xZero, Comparable xOne, Node node){
		int i = 0;
		if(node.hasChildren()){
			if(node.getLeftChild() != null){
				if(node.getLeftChild().getData().compareTo(xZero) >= 0 && node.getLeftChild().getData().compareTo(xOne) <= 0){
					i = i + countFromRoot(xZero, xOne, node.getLeftChild());
				}
			}
			if(node.getRightChild() != null){
				if(node.getRightChild().getData().compareTo(xZero) >= 0 && node.getRightChild().getData().compareTo(xOne) <= 0){
					i = i + countFromRoot(xZero, xOne, node.getRightChild());
				}
			}
			if(node.getData().compareTo(xZero) >= 0 && node.getData().compareTo(xOne) <= 0){
				i++;
			}
		} else {
			if(node.getData().compareTo(xZero) >= 0 && node.getData().compareTo(xOne) <= 0){
				i++;
			}
		}

		return i;
	}
}
