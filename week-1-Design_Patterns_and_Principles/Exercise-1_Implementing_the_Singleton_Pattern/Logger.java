import java.util.*;
public class Logger {
	private static Logger instance;
	private Logger() {
		System.out.println("Logger created");
	}
	public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }
	public static void main(String[] args) {
        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();
        if (logger1 == logger2) {
        	System.out.println(logger1);
        	System.out.println(logger2);
            System.out.println("Both logger instances are the same.");
        } else {
            System.out.println("Different logger instances.");
        }
    }
}
