package com.tree;

public class BinaryTree {

	private BinaryTreeNode root;

	public BinaryTree(){}

	public BinaryTree(BinaryTreeNode root){
		this.root = root;

	}

	public BinaryTreeNode getRoot() {
		return root;
	}

	public void setRoot(BinaryTreeNode root) {
		this.root = root;
	}


	public void insert(int value){
		BinaryTreeNode tree = new BinaryTreeNode(value);
		if(root == null){
			root = tree;
		}else{
			BinaryTreeNode currentNode = root;
			BinaryTreeNode parentNode;
			while(true){
				parentNode = currentNode;
				//左子树
				if(currentNode.data>tree.data){
					currentNode = currentNode.leftChild;
					if(currentNode == null){
						parentNode.leftChild = tree;
						return;
					}
				}
				//右子树
				if(currentNode.data<tree.data){
					currentNode = currentNode.rightChild;
					if(currentNode == null){
						parentNode.rightChild = tree;
						return;
					}
				}
			}
		}

	}

	public void inOrder(BinaryTreeNode node){
		if(node != null){
			inOrder(node.leftChild);
			System.out.print(" "+node.data+" ");
			inOrder(node.rightChild);
		}
	}

	public void befOrder(BinaryTreeNode node){
		if(node != null){
			System.out.print(" "+node.data+" ");
			befOrder(node.leftChild);

			befOrder(node.rightChild);
		}
	}

	public void afterOrder(BinaryTreeNode node){
		if(node != null){

			afterOrder(node.leftChild);
			afterOrder(node.rightChild);
			System.out.print(" "+node.data+" ");
		}
	}

	public int height(BinaryTreeNode node){

		int lh = 0;
		int rh = 0;

		if(node == null){
			return 0;
		}
		lh = height(node.leftChild);
		rh = height(node.rightChild);
		return lh>rh?lh+1:rh+1;

	}
	public int data find(int value,BinaryTreeNode root){
	
		BinaryTreeNode current = root;
		
		if(root.data == value){
			return true;
		}
		if(root.data > value){
			current = current.leftChild;
			find(value,current);
		}
		if(root.data < value){
			current = current.rightChild;
			find(value,current);
		}
		
	}

	public static void main(String[] agrs){

		BinaryTree tree = new BinaryTree();
		tree.insert(2);
		tree.insert(1);
		tree.insert(3);
		tree.insert(4);
		tree.insert(6);
		tree.inOrder(tree.root);
		System.out.println(tree.height(tree.root));
	}
}
