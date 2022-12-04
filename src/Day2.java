import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day2 {
	
	
	public static void main(String[] args) throws IOException {
		
		//A=1=Rock, B=2=Paper, C=3=Scissors
		//X=1=Rock, Y=2=Paper, Z=3=Scissors
		
		Map<String, Integer> compareMap = new HashMap<String, Integer>();
		compareMap.put("A X", 4);
		compareMap.put("A Y", 8);
		compareMap.put("A Z", 3);
		compareMap.put("B X", 1);
		compareMap.put("B Y", 5);
		compareMap.put("B Z", 9);
		compareMap.put("C X", 7);
		compareMap.put("C Y", 2);
		compareMap.put("C Z", 6);
		
		//X=Lose, Y=Draw, Z=Win
		Map<String, Integer> compareMap2 = new HashMap<String, Integer>();
		compareMap2.put("A X", 3);
		compareMap2.put("A Y", 4);
		compareMap2.put("A Z", 8);
		compareMap2.put("B X", 1);
		compareMap2.put("B Y", 5);
		compareMap2.put("B Z", 9);
		compareMap2.put("C X", 2);
		compareMap2.put("C Y", 6);
		compareMap2.put("C Z", 7);
		
		
		Path filePath = Path.of("./input/input_2.txt");
		String content = Files.readString(filePath);
		
		BufferedReader bufReader = new BufferedReader(new StringReader(content));
		String line="";
		
		Integer sum=0;
		while((line= bufReader.readLine()) != null) {
			sum += compareMap.get(line);
		}

		System.out.println(sum);
		
		bufReader = new BufferedReader(new StringReader(content));
		sum=0;
		
		while((line= bufReader.readLine()) != null) {
			sum += compareMap2.get(line);
		}

		System.out.println(sum);
		
		
    }
}
