import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Day4 {

	public static void main(String[] args) throws IOException {
		Path filePath = Path.of("./input/input_4.txt");
		String content = Files.readString(filePath);
		BufferedReader bufReader = new BufferedReader(new StringReader(content));
		String line="";
		
		int includes =0;
		int overlaps=0;
		while((line= bufReader.readLine()) != null) {
			int[] numbers = Arrays.stream(line.split("[,-]")).mapToInt(Integer::parseInt).toArray();
			if(numbers[0]>=numbers[2] && numbers[1]<=numbers[3])
				includes++;
			else if(numbers[2]>=numbers[0] && numbers[3]<=numbers[1])
				includes++;
			if(max(numbers[0], numbers[2])<=min(numbers[1], numbers[3]))
				overlaps++;
		}
		System.out.println(includes);
		System.out.println(overlaps);
    }
}
