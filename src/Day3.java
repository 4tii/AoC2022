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

public class Day3 {

	public static void main(String[] args) throws IOException {

		Path filePath = Path.of("./input/input_3.txt");
		String content = Files.readString(filePath);
		
		BufferedReader bufReader = new BufferedReader(new StringReader(content));
		String line="";
		
		Integer sum=0;
		while((line= bufReader.readLine()) != null) {
			char[] firstHalf = line.substring(0, line.length()/2).toCharArray();
			String secondHalf = line.substring(line.length()/2, line.length());
			for(int i=0; i < firstHalf.length; i++){
				if(secondHalf.indexOf(firstHalf[i])!=-1){
					if(firstHalf[i] >= 97)
						sum += firstHalf[i] - 96;
					else
						sum += firstHalf[i] - 38;
					break;
				}
			}
		}
		System.out.println(sum);

		sum=0;
		bufReader = new BufferedReader(new StringReader(content));
		while((line= bufReader.readLine()) != null) {
			char[] firstLine = line.toCharArray();
			String secondLine = bufReader.readLine();
			String thirdLine = bufReader.readLine();
			for(int i=0; i<firstLine.length; i++){
				if(secondLine.indexOf(firstLine[i])!=-1 &&
				thirdLine.indexOf(firstLine[i])!=-1){
					if(firstLine[i] >= 97)
						sum += firstLine[i] - 96;
					else
						sum += firstLine[i] - 38;
					break;
				}
			}
		}
		System.out.println(sum);
    }
}
