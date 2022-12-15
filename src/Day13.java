import org.json.JSONArray;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.lang.Math.max;


public class Day13 {

    public static void main(String[] args) throws IOException {
        Path filePath = Path.of("./input/input_13.txt");
        String content = Files.readString(filePath);
        BufferedReader bufReader = new BufferedReader(new StringReader(content));
        String line="";
        int idx=0;
        int arrIdx=0;
        int sum=0;

        JSONArray[] arr = new JSONArray[302];

        //iterate over tuples
        while(line!=null){
            idx++;
            JSONArray left = new JSONArray(bufReader.readLine());
            arr[arrIdx++] = left;
            JSONArray right = new JSONArray(bufReader.readLine());
            arr[arrIdx++] = right;
            if(compareLists(left, right).equals("valid"))
                sum+=idx;
            line=bufReader.readLine(); //read empty line
        }
        System.out.println("# of Packets in right order: " + sum);

        arr[arrIdx++] = new JSONArray("[[2]]");
        arr[arrIdx] = new JSONArray("[[6]]");

        for(int i=0; i<arr.length; i++){
            for(int j=0; j<arr.length-i-1; j++){
                if(compareLists(arr[j], arr[j+1]).equals("invalid")){
                    JSONArray help=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=help;
                }
            }
        }
        System.out.println("Sum: " + findDividerPackets(arr));
    }

    public static String compareLists(JSONArray left, JSONArray right){
        if(left.length()==0 && right.length()!=0)
            return "valid";
        else if(left.length()!=0 && right.length()==0)
            return "invalid";
        for (int i = 0; i < max(left.length(),right.length()); i++) {
            //check lengths
            if(i>=left.length() && i>=right.length())
                return "continue";
            else if(i>=left.length() && i<right.length())
                return "valid";
            else if(i<left.length() && i>=right.length())
                return "invalid";

            //recursion
            String action;
            if(left.get(i).getClass().getName().equals(JSONArray.class.getName()) && right.get(i).getClass().getName().equals(JSONArray.class.getName())){
                action=compareLists((JSONArray) left.get(i), (JSONArray) right.get(i));
            }else if(left.get(i).getClass().getName().equals(JSONArray.class.getName()) && right.get(i).getClass().getName().equals(Integer.class.getName())){
                action=compareLists((JSONArray) left.get(i), new JSONArray("["+right.get(i).toString()+"]"));
            }else if(right.get(i).getClass().getName().equals(JSONArray.class.getName()) && left.get(i).getClass().getName().equals(Integer.class.getName())){
                action=compareLists(new JSONArray("[" + left.get(i).toString()+ "]"), (JSONArray) right.get(i));
            }else{
                Integer leftValue = (Integer) left.get(i);
                Integer rightValue = (Integer) right.get(i);
                if(leftValue<rightValue)
                    action = "valid";
                else if(leftValue>rightValue)
                    action = "invalid";
                else
                    action = "continue";
            }
            if(action.equals("valid"))
                return "valid";
            else if(action.equals("invalid"))
                return "invalid";
        }
        return "continue";
    }


    public static int findDividerPackets(JSONArray[] arr){
        int sum=1;
        for(int i=0; i<arr.length; i++){
            if(arr[i]!=null && arr[i].length()==1 && arr[i].get(0).getClass().getName().equals(JSONArray.class.getName())){
                JSONArray first = (JSONArray) arr[i].get(0);
                if(first!=null && first.length()==1 && first.get(0).getClass().getName().equals(Integer.class.getName())){
                    Integer value= (Integer) first.get(0);
                    if(value==2){
                        sum*=(i+1);
                    }else if(value==6){
                        sum*=(i+1);
                    }
                }
            }
        }
        return sum;
    }
}

