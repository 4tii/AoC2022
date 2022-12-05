import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Day5{
    public static void main(String[] args) throws IOException {
        Path filePath = Path.of("./input/input_5_1.txt");
        String content = Files.readString(filePath);
        BufferedReader bufReader = new BufferedReader(new StringReader(content));
        String line;
        List<Stack> stacks = new ArrayList<Stack>();
        while((line=bufReader.readLine())!=null){
            Stack s = new Stack();
            s.addAll(Arrays.asList(line.split(",")));
            stacks.add(s);
        }

        filePath = Path.of("./input/input_5_2.txt");
        content = Files.readString(filePath);
        bufReader = new BufferedReader(new StringReader(content));
        while((line=bufReader.readLine())!=null){
            String[] parsedInput = line.split(" ");
            for(int i=0; i<Integer.parseInt(parsedInput[1]); i++){
                Object item = stacks.get(Integer.parseInt(parsedInput[3])-1).pop();
                stacks.get(Integer.parseInt(parsedInput[5])-1).push(item);
            }
        }
        for(int i=0; i<stacks.size(); i++){
            System.out.print(stacks.get(i).peek());
        }

        System.out.println();

        filePath = Path.of("./input/input_5_1.txt");
        content = Files.readString(filePath);
        bufReader = new BufferedReader(new StringReader(content));
        List<Stack> stacks2 = new ArrayList<Stack>();
        while((line=bufReader.readLine())!=null){
            Stack s = new Stack();
            s.addAll(Arrays.asList(line.split(",")));
            stacks2.add(s);
        }

        filePath = Path.of("./input/input_5_2.txt");
        content = Files.readString(filePath);
        bufReader = new BufferedReader(new StringReader(content));
        Stack help = new Stack();
        while((line=bufReader.readLine())!=null){
            String[] parsedInput = line.split(" ");
            for(int i=0; i<Integer.parseInt(parsedInput[1]); i++){
                Object item = stacks2.get(Integer.parseInt(parsedInput[3])-1).pop();
                help.push(item);
            }
            int size=help.size();
            for(int i=0; i<size; i++) {
                stacks2.get(Integer.parseInt(parsedInput[5]) - 1).push(help.pop());
            }
            help.clear();
        }
        for(int i=0; i<stacks2.size(); i++){
            System.out.print(stacks2.get(i).peek());
        }
    }
}
