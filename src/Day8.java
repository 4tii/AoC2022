import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Day8 {


    public static void main(String[] args) throws IOException {
        Path filePath = Path.of("./input/input_8.txt");
        String content = Files.readString(filePath);
        BufferedReader bufReader = new BufferedReader(new StringReader(content));
        String line;
        int[][] arr = new int[99][99];
        int i=0;
        while((line= bufReader.readLine())!=null){
            arr[i++] = Arrays.stream(line.split("")).mapToInt(Integer::parseInt).toArray();
        }
        getVisibleTrees(arr);
        getMostScenicTree(arr);
    }

    public static int[][] transpose(int[][] arr){
        int[][] arrayNew = new int[arr.length][arr.length];
        for (int i=0; i < arr.length; i++) {
            for (int j=0; j < arr.length; j++) {
                arrayNew[j][i] = arr[i][j];
            }
        }
        return arrayNew;
    }

    public static void getVisibleTrees(int[][] arr){
        int[][] arr2 = transpose(arr);
        HashMap<String, Long> visibleTrees = new HashMap<>();

        //starting from left and top
        for(int row=0; row<arr.length; row++){
            int currentHighestRow=-1;
            int currentHighestCol=-1;
            for(int col = 0; col <arr.length; col++){
                if(arr[row][col]>currentHighestRow){
                    currentHighestRow=arr[row][col];
                    visibleTrees.put("" + row + " " + col, (long) currentHighestRow);
                }
                if(arr2[row][col]>currentHighestCol){
                    currentHighestCol=arr2[row][col];
                    visibleTrees.put("" + col + " " + row, (long) currentHighestCol);
                }
            }
        }

        //starting from right and bottom
        for(int row=arr.length-1; row>=0; row--){
            int currentHighestRow=-1;
            int currentHighestCol=-1;
            for(int col=arr.length-1; col>=0; col--){
                if(arr[row][col]>currentHighestRow){
                    currentHighestRow=arr[row][col];
                    visibleTrees.put("" + row + " " + col, (long) currentHighestRow);
                }
                if(arr2[row][col]>currentHighestCol){
                    currentHighestCol=arr2[row][col];
                    visibleTrees.put("" + col + " " + row, (long) currentHighestCol);
                }
            }
        }
        System.out.println("# of Visible Trees: " + visibleTrees.size());
    }

    public static void getMostScenicTree(int[][] arr){
        int[][] arr2 = transpose(arr);
        HashMap<String, Long> scenicValue = new HashMap<>();

        //init map
        for(int row=0; row<arr.length; row++){
            for(int col = 0; col <arr.length; col++){
                if(row==0 || col==0)
                    scenicValue.put("" + row + " " + col, 0L);
                else
                    scenicValue.put("" + row + " " + col, 1L);
            }
        }

        //starting from left and top
        for(int row=0; row<arr.length; row++){
            for(int col = 0; col <arr.length; col++){
                for(int j=col-1; j>=0; j--){
                    if(arr[row][j]>=arr[row][col] || j==0) {
                        scenicValue.put("" + row + " " + col, scenicValue.get("" + row + " " + col) * (col - j));
                        break;
                    }
                }

                for(int j=col-1; j>=0; j--){
                    if(arr2[row][j]>=arr2[row][col] || j==0) {
                        scenicValue.put("" + col + " " + row, scenicValue.get("" + col + " " + row) * (col - j));
                        break;
                    }
                }
            }
        }

        //starting from right and bottom
        for(int row=arr.length-1; row>=0; row--){
            for(int col = arr.length-1; col>=0; col--){
                for(int j=col+1; j<arr.length; j++){
                    if(arr[row][j]>=arr[row][col] || j==arr.length-1) {
                        scenicValue.put("" + row + " " + col, scenicValue.get("" + row + " " + col) * (j - col));
                        break;
                    }
                }

                for(int j=col+1; j<arr.length; j++){
                    if(arr2[row][j]>=arr2[row][col] || j==arr.length-1) {
                        scenicValue.put("" + col + " " + row, scenicValue.get("" + col + " " + row) * (j - col));
                        break;
                    }
                }
            }
        }
        System.out.println("Scenic Score: " + scenicValue.entrySet().stream().max(Map.Entry.comparingByValue()).get().getValue());
    }
}

