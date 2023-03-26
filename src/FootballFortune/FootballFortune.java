/**
 * Name:Hamza
 * Program: FootballFortune
 * Date: 1/11/2023
 * Description: Football Card trading game where you can build your ultimate
 *  team, increase your team average and run match simulations up the ranks.
 */
package FootballFortune;

import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;


public class FootballFortune {

    static Scanner scanS = new Scanner(System.in);
    static int win;
    static String rank;

    public static void main(String[] args) {

        int money = 0;

        Players[] nameArray = new Players[6]; //Name of Players consists of 
        //(playerNames, playerCountries, playerBorn, playerPosition, playerRating)

        bronze[] b = new bronze[11];
        silver[] s = new silver[11];
        gold[] g = new gold[11];
        plat[] p = new plat[11];
        champion[] c = new champion[11];
        File CardFile = new File("MyCards.txt"); //Ultimate team
        File RankFile = new File("Ranks.txt"); //Ranks (Bronze,Silver,Gold,Plat,Champ)
        File WinsFile = new File("Wins.txt");//Wins

        File MoneyFile = new File("Money.txt"); //Money (Start with 25)
        File ShopCards = new File("ShopCards.txt");//188 cards

        ArrayList<String> collection = new ArrayList<>();

        String menu;
        Boolean loop = true;

        if (!CardFile.exists()) { //If file does not exist go in method
            CreatingFiles(CardFile, MoneyFile, RankFile, WinsFile);
        }
        try {
            Scanner scanC = new Scanner(CardFile); //Scanner for the card file
            for (int i = 0; i < 11; i++) {

                String line = scanC.nextLine();

                collection.add(line);//Add your current Ultimate team to the card file
            }

        } catch (IOException err_code) {
        }

        try {
            Scanner scanR = new Scanner(RankFile);
            Scanner scanM = new Scanner(MoneyFile); //Scanner for money file
            Scanner scanW = new Scanner(WinsFile);

            win = scanW.nextInt();
            rank = scanR.nextLine();
            money = scanM.nextInt(); //Current money to file
            scanW.close();
            scanR.close();
            scanM.close();
        } catch (IOException err_code) {
        }
        
try{
    
        String message = "Welcome to Football fortune";
        String[] words = message.split(" ");
        for (String word : words) {
            System.out.print(word + " ");
            
            Thread.sleep(500);
        }
        
        }catch(Exception err_code){} //Only shows when start program
      
        while (loop) {
            try {
                Scanner scanR = new Scanner(RankFile);
                rank = scanR.nextLine();
            } catch (IOException err) {
            }
            //Print out the main menu displaying money, rank and wins
            System.out.println("\nMain Menu:");
            System.out.println("Your money: $ " + money);
            System.out.println("Rank: " + rank);
            System.out.println("Wins: " + win);
            System.out.println("1) View Starting Lineup\n2) Visit Pack Shop\n3) "
                    + "Play a Game\n4) Help\n5) save and exit");
            menu = scanS.nextLine();
            switch (menu) { //Switch all the possible answers 

                case "1":
                    AddingDeck(MoneyFile, CardFile, collection, money);
                    //If player wishes to view lineup go to method AddingDeck

                    break;

                case "2":

                    money = Shop(ShopCards, MoneyFile, CardFile, collection,
                            money, nameArray); //If player wishes to visit shop go to the shop method 
                    break;
                case "3":
                    money = plays(collection, b, s, g, p, c, money, RankFile,
                            WinsFile); //If player wishes to play go to the play method 

                    break;
                case "4": //If player wants to go to the help method 
                    help();
                    break;
                case "5":
                    System.out.println("Saving game..."); //If player wants to save game
                    try {
                        // Save the player's collection to the "MyCards.txt" file
                        PrintWriter cardsWriter = new PrintWriter(CardFile);
                        for (String card : collection) {
                            cardsWriter.println(card);
                        }
                        //Save the Player's wins to the Wins.txt file
                        cardsWriter.close();
                        PrintWriter winsWriter = new PrintWriter(WinsFile);
                        winsWriter.println(win);
                        winsWriter.close();

                        // Save the player's funds to the "Money.txt" file
                        PrintWriter moneyWriter = new PrintWriter(MoneyFile);
                        moneyWriter.println(money);
                        moneyWriter.close();
                        //Save the player's rank to the RankFile.txt file. 
                        PrintWriter Rank = new PrintWriter(RankFile);
                        Rank.println(rank);
                        Rank.close();
                        loop = false;
                    } catch (Exception err_code) {
                    }
                    break;
                
                default:
                    try {
                    Thread.sleep(250); //If user types an invalid input 
                    System.out.println("Invalid input...\nTry again\n");
                    Thread.sleep(250);
                } catch (Exception err_code) {
                    System.out.println("ERROR");
                }
                break;
            }

        } //end of Menu Loop

    } //end of Main

    /**
     * Name: CreatingFiles Description: If the user enters the game first time
     * they will enter this method where it creates all the files necessary.
     *
     * @param CardFile //Card File
     * @param MoneyFile //Money File
     * @param RankFile//Rank File
     */
    public static void CreatingFiles(File CardFile, File MoneyFile, File RankFile, File WinsFile) {
        try {
            String teamChoice;
            boolean flag = true;
            PrintWriter wins = new PrintWriter(WinsFile);
            PrintWriter rank = new PrintWriter(RankFile);
            PrintWriter cards = new PrintWriter(CardFile);
            PrintWriter money = new PrintWriter(MoneyFile);

            //Asks the user to enter their starting team. 
            System.out.println("Welcome to Football Fortune ");
            System.out.println("What is your starting team?\n1) Man United\n2)"
                    + " Man City\n3) Chelsea\n4) Liverpool"); 
            System.out.println("Note: Choosing one of these teams makes your starting lineup");
        
            
            while (flag) {
                teamChoice = scanS.nextLine();
                if (teamChoice.equalsIgnoreCase("Man United") || 
                        teamChoice.equalsIgnoreCase("1")) {    //Man Unt Lineup
                    cards.println("A.Martial 80\nB.Fernandes 86"
                            + "\nM.Rashford 81\nAntony 82\nCasemiro 89"
                            + "\nC.Eriksen 83\nT.Malacia 79\nL.Shaw 80"
                            + "\nA.Wan-Bissaka 79\nR.Varane 85"
                            + "\nD.de-"
                            + "Gea 87\n");
                    flag = false;
                }
                if (teamChoice.equalsIgnoreCase("Man City") || 
                        teamChoice.equalsIgnoreCase("2")) { //Man City Lineup
                    cards.println("E.Haaland 89\nJ.Alvarez 79\n"
                            + "K.DeBruyne 91\nP.Foden 85\nJ.Grealish 84"
                            + "\nRodri 75\nB.Silva 88\nJ.Cancelo 88"
                            + "\nN.Ake 79\nJ.Stones 83"
                            + "\nEderson 89\n");
                    flag = false;
                }
                if (teamChoice.equalsIgnoreCase("Chelsea") || 
                        teamChoice.equalsIgnoreCase("3")) { //Chelsea Lineup
                    cards.println("K.Havertz 84\nR.Sterling 85"
                            + "\nC.Pulisic 82\nH.Ziyech 83\nM.Kovacic 84"
                            + "\nD.Zakaria 81\nC.Azpilicueta 82"
                            + "\nT.Silva 86\nK.Koulibaly 86\nM.Cucurella 81"
                            + "\nK.Arrizabalaga 81");
                    flag = false;
                }
                if (teamChoice.equalsIgnoreCase("Liverpool") || 
                        teamChoice.equalsIgnoreCase("4")) { //Liverpool Lineup
                    cards.println("A.Oxlade-Chamberlain 77\nD.Nunez 82"
                            + "\nM.Salah 90\nT.Alcantara 86\nFabinho 86"
                            + "\nH.Elliott 75\nK.Tsimikas 77\nV.van-Dijk 89"
                            + "\nI.Konate 81\nT.Alexander-Arnold 87"
                            + "\nAlisson 89");
                    flag = false;
                }
                if (flag) {
                    System.out.println("Invalid Input");
                    continue;
                }
            }
            wins.println(0); //Start off with 0 wins
            rank.println("Bronze"); //Start off with bronze rank
            money.println(25); //Start of with 25 
            System.out.println("Creating new save file...");
            cards.close();
            money.close();
            rank.close();
            wins.close();

        } catch (IOException e) {
            System.out.println("ERROR \n Closing program...");

        }

    } //end of CreatingFiles

    /**
     * Name: AddingDeck 
     * Description: Prints out the current deck and average
     * @param MoneyFile//Money File
     * @param CardFile//Team file
     * @param collection //Team ArrayList
     * @param money
     */
    public static void AddingDeck(File MoneyFile, File CardFile, 
            ArrayList<String> collection, int money) {
        try {
            String[][] delimeter = new String[11][];

            for (int i = 0; i < 11; i++) {
                delimeter[i] = collection.get(i).split("\\s"); 
        //Seperate the rank and names of the players by space
            }

            String[] ratings = new String[11];
            for (int i = 0; i < 11; i++) {
                String dummy = collection.get(i);

                ratings[i] = delimeter[i][1]; //Set an array of all the ratings 
            }

            int length = ratings.length; //Get the legnth of the ratings
            int sum = 0;
            for (int i = 0; i < ratings.length; i++) { //Get the sum of all the ratings
                sum += Integer.parseInt(ratings[i]);
            }
            double average = sum / length; //Determine the lineup average 

            Scanner scanC = new Scanner(CardFile);

            for (int i = 0; i < 11; i++) {
                String line = scanC.nextLine();  //Get the current lineup
                collection.add(line);

            }

            System.out.println("Generating deck...");
            System.out.println("Here is your Ultimate Team ");
            System.out.println("");
            for (int i = 0; i < 11; i++) {
                if (i <= 2) {
                    System.out.println("FORWARD: " + collection.get(i)); 
                //First 3 are going to be Forward
                }
                if (i > 2 && i <= 5) {
                    System.out.println("MIDFIELD: " + collection.get(i)); 
                //Second 3 are going to be midfield 
                }
                if (i > 5 && i <= 9) {
                    System.out.println("DEFENDER: " + collection.get(i)); 
                //Second last 4 people are defenders 
                }
                if (i > 9) {
                    System.out.println("GOAL KEEPER: " + collection.get(i));
                 //Last player is the goalie
                }

                Thread.sleep(50);
            }
            System.out.println("\nYour teams overall rating is " + average); //Display the teams average 
        } catch (IOException err_code) {

        }catch(InterruptedException err){
        }
    } //end of AddingDeck

    /**
     * Name:Shop 
     * Description: Makes a pack shop and displays it to the user
     * @param ShopCards //Shop card file
     * @param MoneyFile// Money card file
     * @param CardFile//Team file
     * @param collection//Team ArrayList
     * @param money//Money int
     * @param nameArray//Legendary Players array
     * @return
     */
    public static int Shop(File ShopCards, File MoneyFile, File CardFile, 
            ArrayList collection, int money, Players[] nameArray) {
        String[] playerNames = {"Messi", "Ronaldo", "Maradona", "Pele", 
            "Zidane", "Ronaldinho"}; //These are all the legendary players,
        //There is a less chance of getting them
        String[] playerCountries = {"Argentina", "Portugal", "Argentina", 
            "Brazil", "France", "Brazil"};
        //Countries of all legendary players
        String[] playerBorn = {"1987", "1985", "1960", "1940", "1972", "1980"};
        //Birth of all legendary players
        String[] playerPosition = {"FORWARD", "FORWARD", "MIDFIELD", 
            "FORWARD", "MIDFIELD", "MIDFIELD"};
        //Positions 
        int[] playerRating = {93, 94, 97, 98, 96, 95};
        //Ratings

        for (int i = 0; i < 6; i++) { //Add these players to an array of the class Players
            nameArray[i] = new Players(playerNames[i], playerCountries[i], playerBorn[i], playerPosition[i], playerRating[i]); //Put all the players into Array

        }

        try {
            int slot = 0;
            ArrayList<String> shop = new ArrayList<>(); 
            ArrayList<String> shopCards = new ArrayList<>();
            Random rand = new Random();
            Scanner scanSh = new Scanner(ShopCards);

            for (int i = 0; i < 187; i++) { //Making an array consistng of all shop 
                String line = scanSh.nextLine();
                shop.add(line);
            }

            Scanner scanN = new Scanner(System.in);
            String shopMenu;
            System.out.println("\nWelcome to the Card shop. Here you can buy " //Prints out the menu for the shop 
                    + "a card for $5. 3 Cards are randomly selected every shop purchase (85% chance of Common, 11% chance of Rare 4%chance of Legendary");
            System.out.println("1) Buy \n2) Exit ");
            shopMenu = scanS.nextLine();

            if (shopMenu.equalsIgnoreCase("buy") || shopMenu.equalsIgnoreCase("1")) { //If the user wishes to buy 
                if (money < 5) {
                    System.out.println("\nInsuffiecent funds\n"); //User has to have atleast 5 for one card
                    return money;
                }
                System.out.println("\nHere is the current shop");

                boolean cardTrue;
                int num = 0;
                for (int i = 0; i < 3; i++) {
                    int random = rand.nextInt(188);
                    for (int j = 0; j < 3; j++) {

                        if (shop.get(j).equalsIgnoreCase(shop.get(random))) { //Does not allow duplicate cards in the shop
                            i--;
                            break;
                        }
                        shopCards.add(shop.get(random));  //Prints out three cards from the shop 
                        break;
                    }

                }
                do {
                    for (int i = 0; i < 3; i++) {
                        if (shopCards.get(i).contains("[L]")) {
                            System.out.println("*[L] Pack: " + (i + 1)); //Shows user the pack is legendary to make it easier 
                        } else {
                            System.out.println("     Pack: " + (i + 1)); //Prints out pack 

                        }
                    }

                    cardTrue = false;
                    System.out.println("Which pack would you like to buy (Type N to go back)"); //Ask the user which pack they want and n to retrurn to menu

                    String cardSelection = scanS.nextLine();
                    if (cardSelection.equalsIgnoreCase("N")) { //If the user wishes to go back (he wont have spent any money yet)
                        return money;

                    }
                    for (int i = 0; i < 3; i++) {

                        if (cardSelection.equalsIgnoreCase(shopCards.get(i)) 
                                || cardSelection.equals(Integer.toString(i + 1))) {
                            cardTrue = true;
                            num = i; //If the selection is valid
                        }

                    }
                    if (cardTrue == false) { //If the selection is invalid
                        System.out.println("Invalid Input");
                    }
                } while (cardTrue == false);

                System.out.println("Opening Pack...");
                if (shopCards.get(num).contains("[L]")) { //If the pack is legendary print out a fancy introduction for the player

                    if (shopCards.get(num).contains("Messi[L]")) {
                        System.out.println("\nCountry "
                                + ": " + (nameArray[0].getCountry()) + "\nPlace of birth: " //Print out a introduction to the legendary players
                                + "" + (nameArray[0].getBorn()) + "\nPosition: "
                                + "" + (nameArray[0].getPosition())
                                + "\nRating: " + (nameArray[0].getRating()));
                    }
                    if (shopCards.get(num).contains("Ronaldo[L]")) {
                        System.out.println("\nCountry "
                                + ": " + (nameArray[1].getCountry()) + "\nPlace of birth: "
                                + "" + (nameArray[1].getBorn()) + "\nPosition: "
                                + "" + (nameArray[1].getPosition())
                                + "\nRating: " + (nameArray[1].getRating()));
                    }
                    if (shopCards.get(num).contains("Maradona[L]")) {
                        System.out.println("\nCountry "
                                + ": " + (nameArray[2].getCountry()) + "\nPlace of birth: "
                                + "" + (nameArray[2].getBorn()) + "\nPosition: "
                                + "" + (nameArray[2].getPosition())
                                + "\nRating: " + (nameArray[2].getRating()));
                    }
                    if (shopCards.get(num).contains("Pele[L]")) {
                        System.out.println("\nCountry "
                                + ": " + (nameArray[3].getCountry()) + "\nPlace of birth: "
                                + "" + (nameArray[3].getBorn()) + "\nPosition: "
                                + "" + (nameArray[3].getPosition())
                                + "\nRating: " + (nameArray[3].getRating()));
                    }
                    if (shopCards.get(num).contains("Zidane[L]")) {
                        System.out.println("\nCountry "
                                + ": " + (nameArray[4].getCountry()) + "\nPlace of birth: "
                                + "" + (nameArray[4].getBorn()) + "\nPosition: "
                                + "" + (nameArray[4].getPosition())
                                + "\nRating: " + (nameArray[4].getRating()));
                    }
                    if (shopCards.get(num).contains("Ronaldinho")) {
                        System.out.println("\nCountry "
                                + ": " + (nameArray[5].getCountry()) + "\nPlace of birth: "
                                + "" + (nameArray[5].getBorn()) + "\nPosition: "
                                + "" + (nameArray[5].getPosition())
                                + "\nRating: " + (nameArray[5].getRating()));
                    }

                    System.out.println("LEGENDARY");
                    scanS.nextLine();

                }
                String player = shopCards.get(num);

                String Name = player.substring(0, player.lastIndexOf(" "));
                System.out.println("");
                System.out.println("You got " + Name + "!!");
                System.out.println("His rating is " + player.substring(player.lastIndexOf(" ") + 1)); //Type the player name and rating
                money -= 5;
                boolean error = true;
                while (error = true) {
                    System.out.println("\nWhich slot would you like to put it in. Type 12 to throw the card away"); //Player isent forced to keep player

                    try {
                        for (int i = 0; i < 11; i++) { //print deck
                            if (i <= 2) {
                                System.out.println((i + 1) + " FORWARD: " + collection.get(i));
                            }
                            if (i > 2 && i <= 5) {
                                System.out.println((i + 1) + " MIDFIELD: " + collection.get(i));
                            }
                            if (i > 5 && i <= 9) {
                                System.out.println((i + 1) + " DEFENDER: " + collection.get(i));
                            }
                            if (i > 9) {
                                System.out.println((i + 1) + " GOAL KEEPER: " + collection.get(i));
                            }

                            Thread.sleep(50);
                        }
                        System.out.println("12 throw the card"); //throw card
                    } catch (Exception err_code) {
                    }

                    slot = scanN.nextInt();
                    if (slot == 12) { //Throw card
                        return money;

                    } else if (slot > 12 || slot < 1) { //Error trap
                        error = true;

                        System.out.println("Please enter a value from 1-11");
                    } else if (0 < slot && slot <= 11) { //Valid input
                        error = false;
                        break;

                    }
                }

                if (cardTrue = true) {
                    if (money < 5) {
                        System.out.println("broke");
                    }
                    System.out.println(collection.get(slot - 1) + " has been removed from your collection"); //who got replaced
                    collection.set(slot - 1, shopCards.get(num));

                    System.out.println(shopCards.get(num) + " has been added to your collection"); //who got added

                    shopCards.remove(num);

                }
            } else if (shopMenu.equalsIgnoreCase("back") || shopMenu.equalsIgnoreCase("2")) { //if user wishes to go back

            } else {
                System.out.println("\nInvalid input\nReturning to Menu...");
            }

            System.out.println("");
        } catch (IOException e) {
        }
        return money;
    } //end of Shop

    /**
     * Name: Plays 
     * Description: Sends the players and ratings to their rank
     * @param collection
     * @param b //bronze array
     * @param s// silver array
     * @param g//gold array
     * @param p //plat array
     * @param c//champ array
     * @param money //money
     * @param RankFile//saved file of ranks
     * @param WinsFile//saved file of wins
     * @return //money
     */
    public static int plays(ArrayList collection, bronze[] b, silver[] s, gold[] g, plat[] p, champion[] c, int money, File RankFile, File WinsFile) {
        try {

            PrintWriter Rank = new PrintWriter(RankFile);
            PrintWriter wins = new PrintWriter(WinsFile);

            FileWriter fileWriter1 = new FileWriter(WinsFile, true);
            FileWriter fileWriter = new FileWriter(RankFile, true);
            BufferedWriter bufferedWriter1 = new BufferedWriter(fileWriter1);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            System.out.println("Earn $20 every win and lose $10 every loss. Would you like to play. (N to exit)"); //Tell the player the money lost and won
            String choice = scanS.nextLine();
            if (choice.equalsIgnoreCase("N")) { //leave
                return money;
            }
            String[] ratings = new String[11];
            String[] names = new String[11];

            for (int i = 0; i < 11; i++) {
                String dummy = (String) collection.get(i);

                names[i] = dummy.substring(0, dummy.indexOf(" "));

                ratings[i] = dummy.substring(dummy.indexOf(" ") + 1);
            }
//get the names and ratings
            for (int i = 0; i < 11; i++) { //put them into the divisions

                b[i] = new bronze(names[i], Integer.parseInt(ratings[i]));
                s[i] = new silver(names[i], Integer.parseInt(ratings[i]));
                g[i] = new gold(names[i], Integer.parseInt(ratings[i]));
                p[i] = new plat(names[i], Integer.parseInt(ratings[i]));
                c[i] = new champion(names[i], Integer.parseInt(ratings[i]));

            }

            bronze Bronze = new bronze("a", 1);
            silver Silver = new silver("a", 1);
            gold Gold = new gold("a", 1);
            plat Plat = new plat("a", 1);
            champion Champion = new champion("a", 1);

            Play play = new Play("a", 1, 1, 1); //depending on the wins the division will change
            if (win <= 3) {

                System.out.println("Welcome to Bronze League");

                bufferedWriter.write("Bronze"); //Print bronze on the ranks
                bufferedWriter.newLine();
                bufferedWriter.close();

                Bronze.Game(b);

                wins.println(win);
               

            }
            if (win > 3 && win < 7) {
                System.out.println("Welcome to Silver League");

                bufferedWriter.write("Silver"); //Print bronze on the ranks
                bufferedWriter.newLine();
                bufferedWriter.close();
                Silver.Game(s);
                

            }
            if (win >= 7 && win < 12) {
                System.out.println("Welcome to Gold League"); //Print gold on the ranks
                bufferedWriter.write("Gold");
                bufferedWriter.newLine();
                bufferedWriter.close();

                Gold.Game(g);
                
            }
            if (win >= 12 && win < 17) {
                System.out.println("Welcome to Plat League"); //Print Plat on the ranks
                bufferedWriter.write("Plat");
                bufferedWriter.newLine();
                bufferedWriter.close();
                Plat.Game(p);
                
            }
            if (win >= 17) {
                System.out.println("Welcome to Champions League"); //Print champion on the ranks
                bufferedWriter.write("Champion");
                bufferedWriter.newLine();
                bufferedWriter.close();
                Champion.Game(c);
                
            }

            System.out.println("Type anything to continue"); //Give them time to read simulation if needed

            scanS.nextLine();
            if (Bronze.getUser()) {
                win++; //IF user wins
                money += 20;
                bufferedWriter1.write(win);//Print wins on the ranks after the game
                bufferedWriter1.newLine();
                bufferedWriter1.close();

            }
            if (Bronze.getCpu()) { //If user loses

                win--;
                if (win < 0) { //wins cant be negative
                    win = 1;
                }
                bufferedWriter1.write(win);//Print wins on the ranks after the game
                bufferedWriter1.newLine();
                bufferedWriter1.close();
                money -= 10;

            }

        } catch (IOException err) {
        }
        return money;

    }

    /**
     * Name:Help 
     * Description: Brief tutorial
     */
    public static void help() {

        System.out.println("Welcome to Football Fortune");
        System.out.println("Build up your Ultimate team and up the ranks");
        System.out.println("Your team average is the overall rating of each "
                + "player in your team. It determines the difficulty in games");
        System.out.println("Earn money from games and spend it at the pack"
                + " shop. There is a rare chance of getting rares[R](11%) or legendary[L] (4%) cards.");
        System.out.println("Your rank starts at bronze but can go up "
                + "depending on your wins Bronze(1-3), Silver(4-7), Gold(8-12),Plat(13-17)");

    }
/**
 * Name: Save
 * Description: Ends program after saving
 */
    public static void save() {

    }//end of Save
    


} //End 

