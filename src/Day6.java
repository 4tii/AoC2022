import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class Day6 {
    public static void main(String[] args) throws IOException {
        Path filePath = Path.of("./input/input_6.txt");
        String content = Files.readString(filePath);
        BufferedReader bufReader = new BufferedReader(new StringReader(content));
        String line = bufReader.readLine();

        boolean found;
        String window;
        for(int i = 0; i<line.length()-4; i++){
            found=true;
            window = line.substring(i, i+4);
            char[] arr = window.toCharArray();
            Arrays.sort(arr);
            for(int j=0; j<arr.length-1; j++){
                if(arr[j]==arr[j+1]){
                    found=false;
                    break;
                }
            }
            if(found) {
                System.out.println("Index: " + (i+4));
                break;
            }
        }

        for(int i=0; i<line.length()-14; i++){
            found=true;
            window = line.substring(i, i+14);
            char[] arr = window.toCharArray();
            Arrays.sort(arr);
            for(int j=0; j<arr.length-1; j++){
                if(arr[j]==arr[j+1]){
                    found=false;
                    break;
                }
            }
            if(found) {
                System.out.println("Index: " + (i+14));
                break;
            }
        }
    }
}
