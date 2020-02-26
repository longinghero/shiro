package com.longing.demo;

public class OrderTest {
	
	public static void main(String[] args){
		
		int[] arr = {4,1,14,8,10,2,14};
		order(arr);
		for(int i = 0;i<arr.length;i++){
			System.out.print(arr[i]+" ");
		}
	}
	
	public static int pos(int[] arr,int n){
		
		int max = arr[0];
		int pos = 0;
		for(int i = 1; i< n; i++){
			if(arr[i] > max){
				max = arr[i];
				pos = i;
			}
		}
		
		return pos;
	}
	public static void order(int[] arr){
		
		int n = arr.length;
		int temp;
		while(n > 1){
			
			int pos = pos(arr,n);
			temp = arr[pos];
			arr[pos] = arr[n - 1];
			arr[n -1] = temp;
			n--;
		}
	}
	
}	
