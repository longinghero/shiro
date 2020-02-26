package com.longing.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class FileChannelTest {
	
	public static void main(String[] args) throws IOException{
		
		File file = new File("C:/Users/zhangzhenkun/Desktop/new.txt");
		
		FileChannel inChannel = new FileInputStream(file).getChannel();
		
		FileChannel outChannel = new FileOutputStream("C:/Users/zhangzhenkun/Desktop/test.txt").getChannel();
		
		MappedByteBuffer buffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
		
		/*Charset charset = Charset.forName("GBK");*/
		
		outChannel.write(buffer);
		
		buffer.clear();
		
		/*CharsetDecoder decoder = charset.newDecoder();
		CharBuffer charBuff = decoder.decode(buffer);
		System.out.println(charBuff);*/
		
	}
}
