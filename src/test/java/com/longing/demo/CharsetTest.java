package com.longing.demo;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.SortedMap;

public class CharsetTest {
	
	public static void main(String[] args) throws CharacterCodingException{
		
		/*SortedMap<String, Charset> map = Charset.availableCharsets();
		for(String alias:map.keySet()){
			System.out.println(map.get(alias));
		}*/
		Charset cn = Charset.forName("GBK");
		
		CharsetEncoder cnEncoder = cn.newEncoder();
		CharsetDecoder cnDecoder = cn.newDecoder();
		
		CharBuffer cffer = CharBuffer.allocate(8);
		cffer.put('孙');
		cffer.put('悟');
		cffer.flip();
		
		ByteBuffer buffer = cnEncoder.encode(cffer);
		for(int i =0; i<buffer.capacity();i++){
			System.out.print(buffer.get(i) +"  ");
		}
		
		System.out.println("\n"+cnDecoder.decode(buffer));
	}
}
