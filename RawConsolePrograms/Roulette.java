import java.util.*;

public class Roulette{
    public static boolean continueBetting=true;
    public static void main(String [] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("How much money you would like to play roulette with:");
        int money = sc.nextInt();
        separator();
        balance(money);
        separator();
        while (continueBetting==true && money>0){
            System.out.println("Pick the color that you would like to bet on");
            System.out.println("If you would like to pick RED, type \"red\", if it lands on RED you will gain 2x your bet amount.");
            System.out.println("If you would like to pick BLACK, type \"black\", if it lands on BLACK you will gain 2x your bet amount.");
            System.out.println("If you would like to pick GREEN, type \"green\", if it lands on GREEN you will gain 14x your bet amount.");
            separator();
            String currentColor=sc.next();
            System.out.printf("How much would you like to bet on that number? Your maximum bet amount is %d%n",money);
            int currentBet=sc.nextInt();
            money-=currentBet;
            separator();
            balance(money);
            separator();
            currentBet(currentBet,currentColor);
            separator();

            Random rand = new Random();
        int currentNumber=rand.nextInt(14);
        if (currentNumber==0){ // potem je zeleno
            separator();
            System.out.printf("Number: %d%nGreen has been hit!%n",currentNumber);
            separator();
            if (currentColor.equals("green")){
                money+=14*currentBet;
                balance(money);
                continueBetting();
            }
            else if (money!=0){
                loss(currentColor);
                balance(money);
                continueBetting();
            }
            else{
                break;
            }

            
        }
        if (currentNumber%2==0 && currentNumber!=0){ //potem je rdece
            separator();
            System.out.printf("Number: %d%nRed has been hit!%n",currentNumber);
            separator();
            if (currentColor.equals("red")){
                money+=2*currentBet;
                balance(money);
                continueBetting();
            }
            else if (money!=0){
                loss(currentColor);
                balance(money);
                continueBetting();
            }
            else{
                break;
            }
        }
        else if (currentNumber%2!=0){ //potem je crno
            separator();
            System.out.printf("Number: %d%nBlack has been hit!%n",currentNumber);
            separator();
            if (currentColor.equals("black")){
                money+=2*currentBet;
                balance(money);
                continueBetting();
            }
            else if (money!=0){
                loss(currentColor);
                balance(money);
                continueBetting();
            }
            else{
                break;
            }
        }
    }
    separator();
    balance(money);
    System.out.println("You ran out of money or stopped betting!");
    separator();
}
        public static void balance(int money){
            System.out.printf("Your current balance: $%d%n",money);
        }
        public static void currentBet(int currentBet, String color){
            System.out.printf("Your current bet on %s: $%d%n",color,currentBet);
        }
        public static void separator(){
            System.out.printf("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=%n");
        }
        public static void loss(String currentColor){
            separator();
            System.out.printf("You bet on %s, unfortunately you lost your bet%n",currentColor);
            separator();
        }
        public static void continueBetting(){
            separator();
            System.out.println("Would you like to continue betting? Type \"Y\" for YES or \"N\" for NO");
            separator();
            Scanner sc=new Scanner(System.in);
            String nadaljevanje=sc.next();
            if (nadaljevanje.equals("Y")||nadaljevanje.equals("y")){
                continueBetting=true;
            }
            else if (nadaljevanje.equals("N")||nadaljevanje.equals("n")){
                continueBetting=false;
            }
            else{
                System.out.println("Error: Incorrect input for continueBetting");
                continueBetting();
            }
        }



    
}