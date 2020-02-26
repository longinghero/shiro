package com.longing.demo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class RandomAccessFileChannelTest {
	
	public static void main(String[] args) throws IOException{
		
		File file = new File("C:/Users/zhangzhenkun/Desktop/test.txt");
		
		RandomAccessFile randomAccessFile = new RandomAccessFile(file,"rw");
		FileChannel channel = randomAccessFile.getChannel();
		
		ByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
		
		channel.position(file.length());
		channel.write(buffer);
	}
}	
