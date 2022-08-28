import java.util.*;

public class Mines{
    public static Random rand = new Random();
    public static boolean found=false;
    public static boolean failure=false;
    public static boolean continuePlaying=true;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        separator();
        System.out.println("How much money would you like to play with:");
        double money=sc.nextInt();
        wholeLoop:
        while (continuePlaying==true){
            found=false;
            separator();
            System.out.println("Input field length:");
            int length=sc.nextInt();
            separator();
            System.out.println("Input field width:");
            int width=sc.nextInt();
            separator();
            String[][]t=new String[width][length];
            String[][]current=new String[width][length];
            int[]taken=new int[width*length];
            // for (int i=0;i<t.length;i++){
            //     System.out.println(Arrays.toString(t[i]));
            // }
            for (int i=0;i<t.length;i++){
                for (int j=0;j<t[0].length;j++){
                    t[i][j]="o";
                    current[i][j]="o";
                }
            }

            System.out.println("The more mines you have, the more money you get per field opened without a mine, but the higher the risk of losing your money if you land on a mine!");
            separator();
            System.out.printf("How many mines would you like to have in your %d x %d field? The maximum amount of mines is %d%n",length,width,(width*length)-1);
            double numberOfMines=sc.nextInt();
            while (numberOfMines>(width*length)-1 || numberOfMines<=0){
                System.out.printf("You want too many mines, please input how many mines you want again, the maximum number of mines for a filed of the size %d x %d is %d!%n",length,width,(width*length)-1);
                numberOfMines=sc.nextInt();
            }
            System.out.printf("Your current balance is $%f, please input how much you would like to bet on this round:%n",money);
            double currentBet=sc.nextDouble();
            separator();
            for (int i=0;i<numberOfMines;i++){
                int currentNumber=rand.nextInt(width*length);
                while (taken[currentNumber]==1){ // checks if a mine already exists on the generated random number spot
                    currentNumber=rand.nextInt(width*length);
                }
                //System.out.println("current random number:"+currentNumber);     // for debuggging
                t[(currentNumber/length)][(currentNumber%length)]="x"; // adds the mine to the selected field spot
                taken[currentNumber]=1;
            }
            //         for (int i=0;i<t.length;i++){
            //     System.out.println(Arrays.toString(t[i]));
            // }
            
            System.out.printf("%.0f mines have been randomly distributed across a %d x %d field!%n",numberOfMines,length,width);
            separator();
            System.out.println("Your field currently looks like this (the mines are hidden):");
            System.out.println("\"o\" represents an unmarked spot, \"+\" represent spots that you have chosen where no mine was found, \"x\" represent a spot that you chose where a mine was found");
            System.out.println();
            printCurrentState(current);
            int currentX;
            boolean alreadyPrinted=false;
            int currentY;
            while (found==false && continuePlaying==true){
                if (alreadyPrinted==true){
                printCurrentState(current);
                }
                alreadyPrinted=true;
                separator();
                System.out.println("Pick a field where you think there is no mine like this: \"Y x X\" (without the spaces, so for example type 5x4 if you think it is on Y:5 and X:4)");
                String x=sc.next();
                //System.out.printf("string input: %s%n",x); //for debugging
                currentX=Character.getNumericValue(x.charAt(2))-1;
                currentY=Character.getNumericValue(x.charAt(0))-1; 
                //System.out.printf("currentX: %d, currentY: %d%n",currentX,currentY);
                if (t[currentY][currentX].equals("x")){
                    separator();
                    System.out.printf("You unfortunately landed on a mine at %d x %d (Y x X), the mine is represented by an \"x\"%n",currentY+1,currentX+1);
                    failure=true;
                    found=true;
                    System.out.printf("You lost your current bet of $%f due to landing on a mine!%n",currentBet);
                    current[currentY][currentX]="x";
                    printCurrentState(current);
                    money-=currentBet;
                    balance(money);
                    if (money<=0.001){
                        money=0;
                        break wholeLoop;
                        
                    }
                    continueBetting();
                    if (continuePlaying==true){
                        separator();
                        System.out.println("You have lost your previous grid due to finding a mine but decided to continue playing, therefore you have to create a new table!");
                    }
                    break;
                    
                }
                else{current[currentY][currentX]="+";
                separator();
                System.out.println("There was no bomb on your selected spot!");
                System.out.printf("You have won $%f%n",currentBet/((length*width)-numberOfMines));
                printCurrentState(current);
                money+=currentBet/((length*width)-numberOfMines);
                balance(money);
                continueBetting();
            }
                
            }
        }
            if (failure==true){
                separator();
                System.out.println("Your final balance: $"+money);
                
            }
            else{
                separator();
                System.out.println("Your final balance: $"+money);
                
            }
        
            

        
    }
    public static void separator(){
        System.out.printf("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=%n");
    }
    public static void printCurrentState(String[][] current){
        for (int i=0;i<current.length;i++){
            System.out.println(Arrays.toString(current[i]));
        }
    }
    public static void balance(double money){
        separator();
        System.out.printf("Your current balance: $%f%n",money);
    }
    public static void continueBetting(){
        separator();
        System.out.println("Would you like to continue playing? Type \"Y\" for YES or \"N\" for NO");
        separator();
        Scanner sc=new Scanner(System.in);
        String nadaljevanje=sc.next();
        if (nadaljevanje.equals("Y")||nadaljevanje.equals("y")){
            continuePlaying=true;
        }
        else if (nadaljevanje.equals("N")||nadaljevanje.equals("n")){
            continuePlaying=false;
        }
        else{
            System.out.println("Error: Incorrect input for continueBetting");
            continueBetting();
        }
    }


}