package Exercises;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class p07LegendaryFarming {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

       int shardsSum =0, fragmentsSum= 0, motesSum =0;
        Map <String, Integer> inventoryMap = new LinkedHashMap<>();
        Map <String, Integer> junkMap  = new LinkedHashMap<>();
        inventoryMap.put("shards", 0);
        inventoryMap.put("fragments", 0);
        inventoryMap.put("motes", 0);

       while ( shardsSum<250 && fragmentsSum<250 && motesSum<250 ){
          String[] inputArr = scanner.nextLine().toLowerCase().split(" ");

           for (int i = 0; i <= inputArr.length-1; i = i+2) {
            int inputAmount = Integer.parseInt(inputArr[i]);
            String itemName = inputArr[i+1];
               if ("shards".equals(itemName)|| "fragments".equals(itemName)||"motes".equals(itemName)) {
                   int currentValue = inventoryMap.get(itemName);
                   inventoryMap.put(itemName, currentValue + inputAmount);
                   shardsSum = inventoryMap.get("shards");
                   fragmentsSum = inventoryMap.get("fragments");
                   motesSum = inventoryMap.get("motes");
                   if ( shardsSum>=250 || fragmentsSum>=250 || motesSum>=250){
                       break;
                   }
               }else{
                   if (!junkMap.containsKey(itemName)){
                       junkMap.put(itemName,inputAmount);
                   }else{
                       int currentValue = junkMap.get(itemName);
                       junkMap.put(itemName, currentValue+inputAmount);
                   }
               }
           }



       }

       if (shardsSum>=250){
           System.out.println("Shadowmourne obtained!");
           int currentValue = inventoryMap.get("shards");
           inventoryMap.put("shards",currentValue-250);
       }else if ( fragmentsSum>=250) {
           System.out.println("Valanyr obtained!");
           int currentValue = inventoryMap.get("fragments");
           inventoryMap.put("fragments",currentValue-250);
       }else if ( motesSum >=250)  {
           System.out.println("Dragonwrath obtained!");
           int currentValue = inventoryMap.get("motes");
           inventoryMap.put("motes",currentValue-250);
       }

       inventoryMap.forEach((key,value)-> System.out.println(key+": "+value));
       junkMap.forEach((key,value)-> System.out.println(key+": "+value));


    }
}
