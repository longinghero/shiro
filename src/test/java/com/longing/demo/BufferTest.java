package com.longing.demo;

import java.nio.CharBuffer;

public class BufferTest {
	
	public static void main(String[] args){
		
		CharBuffer buff = CharBuffer.allocate(8);
		
		System.out.println("buff capacity: "+buff.capacity());
		System.out.println("buff limit "+buff.limit());
		System.out.println("buff position "+buff.position());
		
		buff.put('a');
		buff.put('b');
		buff.put('c');
		
		System.out.println("buff position ==="+buff.position());
		
		buff.flip();
		
		System.out.println("buff limit ==="+buff.limit());
		System.out.println("buff position ==="+buff.position());

		System.out.println("(第一个position0) ==="+buff.get());
		System.out.println("(第二个position0) ==="+buff.get());
		
		buff.clear();
		
		System.out.println("clear会的limit==="+buff.limit());
		System.out.println("clear会的position==="+buff.position());
		
		System.out.println("(第一个position0) ==="+buff.get());
	}
}
