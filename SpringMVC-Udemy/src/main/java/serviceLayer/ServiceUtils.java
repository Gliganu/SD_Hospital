package serviceLayer;

import java.util.Random;

public class ServiceUtils {

	
	public static String getRandomCNP(){
		
		StringBuffer buffer = new StringBuffer();
		
		Random random = new Random();
		
		buffer.append(random.nextInt(2)+1);
		buffer.append("9");
		buffer.append(random.nextInt(50)+50);
		
		for(int i=0;i<9;i++){
			buffer.append(random.nextInt(10));
		}
		
		return buffer.toString();
		
	}
	
}
