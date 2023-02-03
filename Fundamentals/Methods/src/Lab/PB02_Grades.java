package Lab;

public class PB02_Grades { public static void main(String[] args) {
    getNumberAsText();
}
    public static void getNumberAsText(){
        Scanner scanner = new Scanner(System.in);
        double numberInput = Double.parseDouble(scanner.nextLine());
        String outputText = "";
        if (numberInput >= 2 && numberInput<3){
            outputText = "Fail";
        }else if (numberInput<3.5){
            outputText = "Poor";
        }else if (numberInput<4.5){
            outputText = "Good";
        }
        else if (numberInput< 5.5){
            outputText = "Very good";
        }else if(numberInput<=6) {
            outputText = "Excellent";

        }else{
            outputText ="Invalid Grade!";
        }
        System.out.print(outputText);
    }
}
