import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.lang.Math.abs;

public class Day10 {

    public static void main(String[] args) throws IOException {
        Path filePath = Path.of("./input/input_10.txt");
        String content = Files.readString(filePath);
        BufferedReader bufReader = new BufferedReader(new StringReader(content));
        String line= bufReader.readLine();
        int cycle=0;
        int x=1;
        int check=20;
        int sum=0;
        int spritePosition;
        int crtPosition;
        int i=0;

        while(line!=null){
            String[] input = line.split(" ");
            String instruction = input[0];
            if(cycle%40==0)
                System.out.println();
            spritePosition=x%40;
            crtPosition=cycle%40;
            cycle++;
            if(abs(spritePosition-crtPosition)<=1)
                System.out.print("#");
            else
                System.out.print(".");

            if (cycle == check) {
                check += 40;
                sum += cycle * x;
            }
            switch (instruction) {
                case "noop" -> line=bufReader.readLine();
                case "addx" -> {
                    if (i == 1) {
                        x += Integer.parseInt(input[1]);
                        line=bufReader.readLine();
                        i=0;
                    }else
                        i++;
                }
            }
        }
        System.out.println();
        System.out.println("Sum: " + sum);
    }
}

