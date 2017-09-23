package com.indevstudio.stbtest.led;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ChangeLedLight {
	
	
	static int flag = 0;
	static LedFlashThread thread = new LedFlashThread("flashLed");
	
	
	public void setLedOn() throws IOException
	{
		if(flag == 1)
		{
			thread.stop = true;
			flag = 0;
			setLedOn();
			
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			thread = new LedFlashThread("flashLed");
		}
		
		FileWriter writer = new FileWriter("/proc/ledlight/powerled/state");
		BufferedWriter bw = new BufferedWriter(writer);
		
		bw.write("on");
		
		bw.close();
		writer.close();
	}
	
	
	public void setLedOff() throws IOException
	{
		if(flag == 1)
		{
			thread.stop = true;
			flag = 0;
			setLedOn();
			
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			thread = new LedFlashThread("flashLed");
		}	
		
		FileWriter writer = new FileWriter("/proc/ledlight/powerled/state");
		BufferedWriter bw = new BufferedWriter(writer);
		
		bw.write("all_off");
		
		bw.close();
		writer.close();
	}
	
	public void setLedFlash() throws IOException
	{
		if(flag == 0)
		{
			thread.start();
			flag = 1;
		}
		
		else 
			setLedOn();
	}

}
