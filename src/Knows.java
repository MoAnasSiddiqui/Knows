public class Knows{
    static int choice;
    public static void main(String[] args){
        mainPage();
        System.out.println("\n\nKNOWS. All Rights Reserved. Copyright 2023.\n");
    }

    static void mainPage(){
        choice = Display.menu(new String[]{"Admin","Faculty","Student","Exit"});
        if (choice == 1){
            adminPage();
        } else if (choice == 2){
            facultyPage();
        } else if (choice == 3){
            studentPage();
        } else {return;}
    }
    static void adminPage(){
        choice = Display.menu(new String[]{"Login","Exit"});
        if (choice == 1){
            System.out.println("This is admin login");
        }
        else {mainPage();}
    }
    static void facultyPage(){
        choice = Display.menu(new String[]{"Login","Exit"});
        if (choice == 1){
            System.out.println("This is faculty login");
        }
        else {mainPage();}
    }
    static void studentPage(){
        choice = Display.menu(new String[]{"Login","Exit"});
        if (choice == 1){
            System.out.println("This is student login");
        }
        else {mainPage();}
    }
}
