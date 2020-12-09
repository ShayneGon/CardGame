package cardgame;
import java.util.*;
import java.io.*;
public class CardGame {
    static int[][] cards = {{0,2},{1,4},{2,4},{3,4},{4,4},{5,4},{6,4},{7,4},{8,4},{9,4},{10,4},{11,4},{12,4},{13,4}};
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to higher or lower card edition");
        System.out.println("We play with a standard pack of 54 cards, an Ace is the same as a 1 and a joker gives you a point regardless.");
        boolean gameOver = false;
        int lastCard = -1;
        int choice = -1;
        int points = 0;
        boolean repeat = true;
        System.out.println("What is your full name");
        String name = input.nextLine();
        while (gameOver == false){
            int cardChoice = pickCard();
            String card = card(cardChoice);
            System.out.println("The card picked is a "+ card);
            if (choice == -1){
            }else if (cardChoice == 0 || lastCard == 0){
                points++;
                System.out.println("Since this card or the last card was a joker, you get a point either way, lucky");
            }else if (lastCard == cardChoice){
                System.out.println("Same card so no points gained or lost");
            }else if (choice == 1){
                if (cardChoice > lastCard){
                    points++;
                    System.out.println("Last time you said higher and you were right, well done");
                }else{
                    points--;
                    System.out.println("Last time you said higher and you were wrong, unfortunate");
                }
            }else if (choice == 2){
                if (cardChoice > lastCard){
                    points--;
                    System.out.println("Last time you said lower and you were wrong, unfortunate");
                }else{
                    points++;
                    System.out.println("Last time you said lower and you were right, well done");
                }
            }
            System.out.println("Your score is: "+points);
            System.out.println("I repeat the card picked was a "+card);
            while (repeat == true){
                try{
                
                    System.out.println("Do you guess Higher[1] or Lower[2], please enter the correct corrosponding number");
                    choice = input.nextInt();
                    if (choice == 1 || choice == 2){
                        repeat = false;
                    }
                }catch (Exception e){
                System.out.println("Error: "+e);
                System.out.println("Please enter 1 or 2");
                input.next();
                }
            }
            lastCard = cardChoice;
            gameOver = gameOver();
            repeat = true;
            
        }
        System.out.println("Your final score is: "+points);
        getDir();
        scores(points, name, fullDir);
        readFile();
        printHighScores();
    }
    public static int pickCard(){
        int cardChoice;
        do{
            Random randInt = new Random();
            cardChoice = randInt.nextInt(14);
        }while ((cards[cardChoice][1]-1) == -1);
        return cardChoice;
    }
    public static String card(int cardChoice){
        String card;
        if (cardChoice == 11){
            card = "Jack";
            cards[cardChoice][1] = cards[cardChoice][1]-1;
        }else if (cardChoice == 12){
            card = "Queen";
            cards[cardChoice][1] = cards[cardChoice][1]-1;
        }else if (cardChoice == 13){
            card = "King";
            cards[cardChoice][1] = cards[cardChoice][1]-1;
        }else if (cardChoice == 1){
            card = "Ace";
            cards[cardChoice][1] = cards[cardChoice][1]-1;
        }else if (cardChoice == 0){
            card = "Joker";
            cards[cardChoice][1] = cards[cardChoice][1]-1;
        }else{
            card = Integer.toString(cardChoice);
            cards[cardChoice][1] = cards[cardChoice][1]-1;
        }
        return card;
    }
    public static boolean gameOver(){
        boolean gameOver = false;
        int finish = 0;
        for (int i = 0; i < 13; i++) {
            if (cards[i][1] != 0){
                finish++;
            }
        }if (finish == 0){
            gameOver = true;
            System.out.println("Game Over");
        }
        return gameOver;
    }
    public static ArrayList<String> textItems = new ArrayList<>();
    public static String fullDir;
    public static void getDir() {
        fullDir = System.getProperty("user.dir")+"\\Scores.text";
    }
    public static void scores(int points, String name, String dir){
        try {
            FileWriter writeToFile = new FileWriter(dir,true);
            PrintWriter printToFile = new PrintWriter(writeToFile);
            if (points < 10 && points >= 0){
                printToFile.println("0"+points+" "+name);
            }else{
                printToFile.println(points+" "+name);
            }
            printToFile.close();
            writeToFile.close();
        } catch (Exception e) {
            System.out.println("Error: "+e);
        }
    }
    public static void readFile(){
        String inputLine;
        try {
            BufferedReader read = new BufferedReader(new FileReader(fullDir));
            while ((inputLine = read.readLine()) != null){
                    textItems.add(inputLine); 
            }
            read.close();
        } catch (Exception e) {
            System.out.println("Error: "+e);
        }
    }
    public static void printHighScores(){
        Collections.sort(textItems);
        for (int i = textItems.size()-1; i >= 0; i--) {
            System.out.println(textItems.get(i));
        }
    }
    
}
