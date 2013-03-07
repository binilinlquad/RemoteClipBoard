package com.gandan.clipboard;

import java.io.InputStream;

import javax.microedition.io.StreamConnection;

public class ProcessConnectionThread implements Runnable {
    private StreamConnection mConnection;
	
	public ProcessConnectionThread(StreamConnection connection){
	   mConnection = connection;
	}
	
	@Override
	public void run(){
	    try {
		    InputStream inputStream = mConnection.openInputStream();
			
			System.out.println("waiting for input");
			
			while (true) {
			    int command = inputStream.read();
				
				System.out.println("Input : " + command);
			}
		} catch(Exception e) {
		    e.printStackTrace();
		}
	}
}
