package com.longing.demo;

public class ChoiceOrder {

	public static void main(String[] args){

		int[] arr = {3,1,2};
		select(arr,3);
		for(int i = 0;i<arr.length;i++){
			System.out.print(arr[i] +" ");
		}
	}
	
	public static void select(int[] arr,int n){
		
		int pos = 0;
		int temp;
		while(n>1){
			
			pos = max(arr,n);
			temp = arr[n -1];
			arr[n -1] = arr[pos];
			arr[pos] = temp;
			n--;
		}
	}
	
	public static int max(int[] arr,int n){
		int max = arr[0];
		int pos = 0;
		for(int i = 1;i<n;i++){
			if(arr[i]>max){
				max = arr[i];
				pos = i;
			}
		}
		return pos;
	}
}
