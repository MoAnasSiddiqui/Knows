import java.util.InputMismatchException;
import java.util.Scanner;

public class Display{
    public static Scanner input = new Scanner(System.in);
    public static void logo() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
            System.out.println("\n\n               KNOWS\n\n");
        } catch (Exception e) { /* Handle exceptions */ }
    }
    public static void displayOptions(String[] options){
        for (int i=0; i<options.length; i++){
            System.out.println((i+1) + ": " + options[i]);
        }
        System.out.println("0: Exit");
        System.out.println();
    }
    public static int chooseOption(int range){
        int choice = -1;
        while (choice < 0 || choice > range){
            System.out.print("Enter: ");
            try{
                choice = input.nextInt();
                input.nextLine();
                if (choice >=0 && choice <= range){
                    break;
                } 
                else {
                    System.out.println("Not valid input...");
                }
            } catch (InputMismatchException e){
                System.out.println("Not valid option...");
                input.nextLine();
            }
        }
        return choice;
    }
    public static int menu(String[] options){
        displayOptions(options);
        return chooseOption(options.length);
    }
}