package com.tree;

public class Node {
	
	public int data;
	
	public Node leftNode;
	
	public Node rightNode;
	
	public Node(int data){
		this.data = data;
		leftNode = null;
		rightNode = null;
	}
	
	private Node addNode(Node current,int value){
		if(current == null){
			return new Node(value);
		}
		if(value<current.data){
			current.leftNode = addNode(current.leftNode,value);
		}else if(value>current.data){
			current.rightNode = addNode(current.rightNode,value);
		}else{
			return current;
		}
		
		return current;
	}
	
	
}
