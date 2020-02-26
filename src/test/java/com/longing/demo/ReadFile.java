package com.longing.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class ReadFile {
	
	public static void main(String[] args) throws IOException{
		
		File file = new File("C:/Users/zhangzhenkun/Desktop/test.txt");
		
		FileChannel inChannel = new FileInputStream(file).getChannel();
		
		ByteBuffer buffer = ByteBuffer.allocate(256);
		Charset charset = Charset.forName("GBK");
		CharsetDecoder decoder = charset.newDecoder();
		
		while(inChannel.read(buffer)!= -1){
			
			buffer.flip();
			
			CharBuffer charbuff = decoder.decode(buffer);
			System.out.println(charbuff);
			buffer.clear();
		}
	}
}
