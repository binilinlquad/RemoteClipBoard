package com.gandan.clipboard;

import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.UUID;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.microedition.io.StreamConnectionNotifier;

public class WaitThread implements Runnable {
    public WaitThread(){
	}
	
	@Override
	public void run(){
	    waitForConnection();
	}
	
	private void waitForConnection(){
	    LocalDevice local = null;
		
		StreamConnectionNotifier notifier;
		StreamConnection connection = null;
		
		try {
		    local = LocalDevice.getLocalDevice();
			local.setDiscoverable(DiscoveryAgent.GIAC);
			
			UUID uuid = new UUID(12345678);
			String url = "btspp://localhost" + uuid.toString()+";name=RemoteClipboard";
			notifier = (StreamConnectionNotifier) Connector.open(url);
		} catch (Exception e) {
		    e.printStackTrace();
			return;
		}
		
		while(true){
		    try {
			    System.out.println("waiting for conenciton...");
				connection = notifier.acceptAndOpen();
				
				Thread processThread = new Thread(new ProcessConnectionThread(connection));
				processThread.start();				
			} catch (Exception e){
			    e.printStackTrace();
				return;
			}
		}
	}
}
