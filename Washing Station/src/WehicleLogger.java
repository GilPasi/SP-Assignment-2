import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WehicleLogger {
	private static final String DEF_PATH = "log.txt";
	private File log;
	private FileWriter fw;
	
	public WehicleLogger() {
		log = new File(DEF_PATH);
		try {
			fw = new FileWriter(log);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public WehicleLogger(String path) {
		log = new File(path);
		try {
			fw = new FileWriter(log);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void log (String msg,int leftVehicles) {
		
		try {
			
			fw.write(msg);

			if(leftVehicles <= 0) {
				fw.close();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
