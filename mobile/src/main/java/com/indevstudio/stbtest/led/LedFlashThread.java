package com.indevstudio.stbtest.led;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class LedFlashThread extends Thread{ 
	public boolean stop = false;
	
    public LedFlashThread(String name) { 
        super(name); 
    } 

	
    public void run()
    { 
        while(!stop)
        {
        	try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        	
        	FileWriter writer = null;
			try {
				writer = new FileWriter("/proc/ledlight/powerled/state");
			} catch (IOException e2) {
				e2.printStackTrace();
			}
    		BufferedWriter bw = new BufferedWriter(writer);
    		
    		try {
				bw.write("on");
				bw.close();
	    		writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
    		
    		try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    		
    		try {
				writer = new FileWriter("/proc/ledlight/powerled/state");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
    		bw = new BufferedWriter(writer);
    		
    		try {
				bw.write("all_off");
				bw.close();
	    		writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
    		
        }
    } 

}
