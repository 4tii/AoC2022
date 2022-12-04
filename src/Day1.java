import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day1 {
	
	public static void main(String[] args) throws IOException {
		
		Path filePath = Path.of("./input/input_1.txt");
		String content = Files.readString(filePath);
		
		BufferedReader bufReader = new BufferedReader(new StringReader(content));
		String line="";
		List<Integer> a = new ArrayList<Integer>();
		
		Integer sum=0;
		while((line= bufReader.readLine()) != null) {
			if(!line.equals(""))
				sum += Integer.parseInt(line);
			if(line.equals("")) {
				a.add(sum);
				sum=0;
			}
		}
		Collections.sort(a);
		System.out.println(a.get(a.size()-1).toString()+" "+a.get(a.size()-2).toString()+" "+a.get(a.size()-3).toString());
		System.out.println(a.get(a.size()-1)+a.get(a.size()-2)+a.get(a.size()-3));
        
    }
}
