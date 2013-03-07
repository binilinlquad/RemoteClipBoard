package com.gandan.clipboard;


public class RemoteClipBoard {
    public static void main(String args[]){
        Thread waitThread = new Thread(new WaitThread());
        waitThread.start();
    }
}
