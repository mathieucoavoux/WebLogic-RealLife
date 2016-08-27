package fr.comprehensiveit.weblogic;

public class StartLogger {
	public static void main(String args[]) throws InterruptedException {
		System.out.println("StartLogger");
		DatabaseLogger dsc = new DatabaseLogger();
		int myResult = dsc.writeStartTime();
		System.out.println("StartLogger result:"+myResult+"\n");
	}
}
