package be.belfius.Daouri_Samy_Games;

import java.lang.invoke.MethodHandles;
import java.util.stream.IntStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("org.slf4j.simpleLogger.showDateTime", "true");
		System.setProperty("org.slf4j.simpleLogger.dateTimeFormat", "yyyy-MM-dd HH:mm:ss");
		Logger logger = LoggerFactory.getLogger(Test.class);
		logger.info("Test info {}" , "juste qqch");
		logger.error("Test error {}" , "juste qqch");
		System.out.println("Logger name ==> " + Logger.ROOT_LOGGER_NAME);
		System.out.println("Test");
		
		String index ;
		for(int i =0; i<5;i++) {
			
			index =Integer.toString(i+1);
			logger.error("Test error {}" , index );
		}
	}

}
