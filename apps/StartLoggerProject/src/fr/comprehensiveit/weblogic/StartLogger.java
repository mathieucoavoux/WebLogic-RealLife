package fr.comprehensiveit.weblogic;

import app.StartSleep;

public class StartLogger {
	public static void main(String args[]) throws InterruptedException {
		System.out.println("StartLogger");
		DatabaseLogger dsc = new DatabaseLogger();
		int myResult = dsc.writeStartTime();
		StartSleep ss = new StartSleep();
		int myResult2 = dsc.writeStartTime();
		System.out.println("StartLogger result:"+myResult+"\n");
	}
}
