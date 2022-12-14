import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Day11 {

    public static void main(String[] args) throws IOException {
        List<Monkey> monkeys = readInput();
        List<Long> inspectionCount = new ArrayList<>();
        for(int i=0; i<monkeys.size(); i++){
            inspectionCount.add(0L);
        }
        //20 rounds
        for(int i=0; i<20; i++){
            Iterator<Monkey> monkeyIt = monkeys.iterator();
            int j=0;
            while(monkeyIt.hasNext()){
                Monkey currentMonkey = monkeyIt.next();
                for (Long aLong : currentMonkey.getItems()) {
                    inspectionCount.set(j, inspectionCount.get(j) + 1);
                    Long item = aLong;
                    item = monkeyFunction(j, item);
                    item /= 3;
                    if (item % currentMonkey.getDivisibleBy() == 0) {
                        monkeys.get(currentMonkey.getTrueMonkey()).addItem(item);
                    } else {
                        monkeys.get(currentMonkey.getFalseMonkey()).addItem(item);
                    }
                }
                currentMonkey.removeItems();
                j++;
            }
        }
        inspectionCount=inspectionCount.stream().sorted().collect(Collectors.toList());
        System.out.println("Monkey Business Level: " + inspectionCount.get(6)*inspectionCount.get(7));

        monkeys = readInput();
        inspectionCount.clear();
        for(int i=0; i<monkeys.size(); i++){
            inspectionCount.add(0L);
        }
        //10000 rounds
        for(int i=0; i<10000; i++){
            Iterator<Monkey> monkeyIt = monkeys.iterator();
            int j=0;
            while(monkeyIt.hasNext()){
                Monkey currentMonkey = monkeyIt.next();
                for (Long aLong : currentMonkey.getItems()) {
                    inspectionCount.set(j, inspectionCount.get(j) + 1);
                    Long item = aLong;
                    item = monkeyFunction(j, item) % 9699690L;
                    if (item % currentMonkey.getDivisibleBy() == 0) {
                        monkeys.get(currentMonkey.getTrueMonkey()).addItem(item);
                    } else {
                        monkeys.get(currentMonkey.getFalseMonkey()).addItem(item);
                    }
                }
                currentMonkey.removeItems();
                j++;
            }
        }
        inspectionCount=inspectionCount.stream().sorted().collect(Collectors.toList());
        System.out.println("Monkey Business Level: " + inspectionCount.get(6)*inspectionCount.get(7));
    }

    private static class Monkey{
        List<Long> items;
        Long divisibleBy;
        int trueMonkey;
        int falseMonkey;

        public Monkey(){
            this.items = new ArrayList<>();
        }

        public void setItems(List<Long> items){
            this.items.addAll(items);
        }
        public void addItem(Long item){
            this.items.add(item);
        }
        public List<Long> getItems(){
            return this.items;
        }
        public void removeItems(){
            this.items.clear();
        }

        public void setDivisibleBy(Long divisibleBy){
            this.divisibleBy=divisibleBy;
        }
        public Long getDivisibleBy(){
            return this.divisibleBy;
        }

        public void setTrueMonkey(int trueMonkey){
            this.trueMonkey=trueMonkey;
        }
        public int getTrueMonkey(){
            return this.trueMonkey;
        }

        public void setFalseMonkey(int falseMonkey){
            this.falseMonkey=falseMonkey;
        }
        public int getFalseMonkey(){
            return falseMonkey;
        }
    }

    public static Long monkeyFunction(int monkeyNumber, Long old){
        return switch (monkeyNumber) {
            case 0 -> old * 3L;
            case 1 -> old + 8L;
            case 2 -> old * old;
            case 3 -> old + 2L;
            case 4 -> old + 3L;
            case 5 -> old * 17L;
            case 6 -> old + 6L;
            case 7 -> old + 1L;
            default -> 0L;
        };
    }

    public static List<Monkey> readInput() throws IOException {
        Path filePath = Path.of("./input/input_11.txt");
        String content = Files.readString(filePath);
        BufferedReader bufReader = new BufferedReader(new StringReader(content));
        List<Monkey> monkeys = new ArrayList<>();
        String line;

        while ((line=bufReader.readLine())!=null){
            if(line.contains("Monkey")) {
                Monkey monkey = new Monkey();
                line = bufReader.readLine();
                monkey.setItems(Arrays.stream(line.substring(18).split(", ")).mapToLong(Long::parseLong).boxed().toList());
                bufReader.readLine(); //skip operation line
                line=bufReader.readLine();
                monkey.setDivisibleBy(Long.valueOf(line.substring(line.indexOf("by")+3)));
                line=bufReader.readLine();
                monkey.setTrueMonkey(Integer.parseInt(line.substring(line.indexOf("monkey")+7)));
                line=bufReader.readLine();
                monkey.setFalseMonkey(Integer.parseInt(line.substring(line.indexOf("monkey")+7)));
                monkeys.add(monkey);
            }
        }
        return monkeys;
    }

}

