import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import static java.lang.Math.abs;

public class Day9 {

    public static void main(String[] args) throws IOException {
        Point head = new Point(0,0);
        Point tail = new Point(0,0);

        Path filePath = Path.of("./input/input_9.txt");
        String content = Files.readString(filePath);
        BufferedReader bufReader = new BufferedReader(new StringReader(content));
        String line;

        HashSet<String> visitedPos = new HashSet<>();

        while((line= bufReader.readLine())!=null){
            String[] input = line.split(" ");
            String direction = input[0];
            int amount = Integer.parseInt(input[1]);
            for(int i=0; i<amount; i++){
                switch (direction) {
                    case "R" -> head.x = head.x + 1;
                    case "L" -> head.x = head.x - 1;
                    case "U" -> head.y = head.y + 1;
                    case "D" -> head.y = head.y - 1;
                }
                move(head, tail);
                visitedPos.add("" + tail.x + " " + tail.y);
            }
        }
        System.out.println("Visited positions: " + visitedPos.size());

        //part 2
        content = Files.readString(filePath);
        bufReader = new BufferedReader(new StringReader(content));

        Point[] rope = new Point[10];
        for(int i=0; i<rope.length; i++) {
            rope[i]=new Point(0,0);
        }

        HashSet<String> visitedPos2 = new HashSet<>();
        while((line= bufReader.readLine())!=null) {
            String[] input = line.split(" ");
            String direction = input[0];
            int amount = Integer.parseInt(input[1]);
            for(int i=0; i<amount; i++){
                switch (direction) {
                    case "R" -> rope[0].x = rope[0].x + 1;
                    case "L" -> rope[0].x = rope[0].x - 1;
                    case "U" -> rope[0].y = rope[0].y + 1;
                    case "D" -> rope[0].y = rope[0].y - 1;
                }
                for(int j=0; j<rope.length-1; j++) {
                    move(rope[j], rope[j+1]);
                }
                visitedPos2.add("" + rope[9].x + " " + rope[9].y);
            }
        }
        System.out.println("Visited positions: " + visitedPos2.size());
    }

    public static void move(Point p1, Point p2){
        //different x, but same y
        if(abs(p1.x-p2.x)>1 && p1.y==p2.y){
            if(p1.x>p2.x)
                p2.x = p2.x+1;
            else if (p1.x<p2.x) {
                p2.x = p2.x-1;
            }
        //different y, but same x
        } else if(abs(p1.y-p2.y)>1 && p1.x==p2.x){
            if(p1.y>p2.y)
                p2.y = p2.y+1;
            else if (p1.y<p2.y) {
                p2.y = p2.y-1;
            }
        //different x, different y
        } else if(abs(p1.x-p2.x)>1 && p1.y!=p2.y){
            if(p1.x>p2.x) {
                p2.x = p2.x + 1;
            }
            else if (p1.x<p2.x) {
                p2.x = p2.x-1;
            }
            if(p1.y>p2.y)
                p2.y = p2.y+1;
            else
                p2.y = p2.y-1;
        //different y, different x
        } else if(abs(p1.y-p2.y)>1 && p1.x!=p2.x){
            if(p1.y>p2.y)
                p2.y = p2.y+1;
            else if (p1.y<p2.y) {
                p2.y = p2.y-1;
            }
            if(p1.x>p2.x)
                p2.x = p2.x + 1;
            else
                p2.x = p2.x-1;
        }
    }
}

