/*
 *Name:Hamza
 *Class:Play
 *Description: Deals with all the matches. Takes player names and ratings to simulate a match
 */
package FootballFortune;


import java.util.Random;


public class Play {

    protected String name;
    protected int rating;
    protected int userscore = 0;
    protected int cpuscore = 0;
    protected boolean user;
    protected boolean cpu;
    protected int enemy;
    protected Random rand = new Random();
    protected int result;

    //constructer
    Play(String n, int r, int ene, int res) {
        name = n;
        rating = r;
        enemy = rand.nextInt(10) + ene;
        result = rand.nextInt(100) + res;
    }

    //setters
    public void setName(String n) {
        name = n;
    }

    public void setRating(int r) {
        rating = r;
    }

    //getters
    public String getName() {
        return name;
    }

    public int getRating() {
        return rating;
    }

    public boolean getUser() {
        return user;
    }

    public boolean getCpu() {
        return cpu;
    }

    public void Game(Play[] p) {
        try {

            int num = 0;

            for (int i = 0; i < 11; i++) {
                num += (p[i]).getRating();
            }
            double average = num / p.length;
            boolean flag = true;

            int striker = 0;
            int assist = 0;
            int tackle = 0;
            System.out.println("Opponent found...");
            System.out.println("Your overall rating is " + average); //Print out your average
            System.out.println("The opponents overall rating is " + enemy); //Print out enemy advantage

            System.out.println("");
            int score = rand.nextInt(5) + 3;
            userscore = 0;
            cpuscore = 0;
            if (enemy > average) {
                System.out.println("The enemy has an advantage"); //Enemy has advantage 
                //35 percent chance of winning //10 percent chance of draw //55 percent chance of losing
                //20 percent chance of yellow card
                for (int i = 0; i < score; i++) {
                    flag = true;

                    int fouls = rand.nextInt(100);
                    while (flag) {
                        tackle = rand.nextInt(5) + 6; //Picks defender
                        striker = rand.nextInt(6) + 1; //Picks forward or mid
                        assist = rand.nextInt(6) + 1;//Picks Midfeild or forward
                        if (assist != striker) {
                            flag = false;
                            break;
                        }
                    }
                    if (fouls < 20) { //Yellow card
                        System.out.println(p[tackle - 1].getName() + " got a yellow card card 🟨");
                        Thread.sleep(750);
                    } else if (fouls < 60) {

                    } else { //Tackle
                        System.out.println(p[tackle - 1].getName() + " got a clean tackle");
                        Thread.sleep(750);
                    }

                    if (result < 35) { //User scored
                        System.out.println("\nScore is " + userscore + ":" + cpuscore);
                        System.out.println("");
                        System.out.println(p[striker - 1].getName() + " Scored ⚽\n " + p[assist - 1].getName() + " With an assist  👟");

                        Thread.sleep(750);

                        userscore += 1;
                    } else if (result < 45) { //Goalie saved it

                        System.out.println(p[10].getName() + " Saved a shot! 🖐");
                        Thread.sleep(750);
                    } else { //Opponent scored
                        System.out.println("\nScore is " + userscore + ":" + cpuscore);
                        System.out.println("");
                        System.out.println("Opponent Scored ⚽");

                        Thread.sleep(750);
                        cpuscore += 1;
                    }
                }
                System.out.println("\nGame over!");
                System.out.println("Final score is " + userscore + ":" + cpuscore); //Final Score

                if (userscore > cpuscore) {
                    System.out.println("You won and got $20");
                    user = true;
                } else if (userscore < cpuscore) {
                    System.out.println("You lost $10");
                    cpu = true;

                } else if (userscore == cpuscore) {
                    System.out.println("Going in to penalties..."); //If its still tied give them a 50/50 chance
                    int penalties = rand.nextInt(100);
                    if (penalties < 50) {
                        System.out.println("You won penalties. Won $20");
                        user = true;
                    } else {
                        System.out.println("You lost penalties. Lost $10");
                        cpu = true;
                    }

                }
            }

            if (average == enemy) {
                System.out.println("It is a close matchup"); //Tied matchup (really rare) //50 percent chance of draw //25 percent chance of winning //25 percent chance of losing //20 of yellow card
                for (int i = 0; i < score; i++) {
                    flag = true;
                    int result = rand.nextInt(100);
                    int fouls = rand.nextInt(100);
                    while (flag) {
                        tackle = rand.nextInt(5) + 6; //Pick defender
                        striker = rand.nextInt(6) + 1; //Pick forward or mid
                        assist = rand.nextInt(6) + 1; //Pick forward or mid
                        if (assist != striker) {
                            flag = false;
                            break;
                        }
                    }
                    if (fouls < 20) { //Yellow card
                        System.out.println(p[tackle - 1].getName() + " got a yellow card 🟨");
                        Thread.sleep(750);
                    } else { //Tackle
                        System.out.println(p[tackle - 1].getName() + " got a clean tackle");
                        Thread.sleep(750);
                    }
                    if (result < 50) { //Saved it

                        System.out.println(p[10].getName() + " Saved a shot! 🖐");
                        Thread.sleep(750);

                    } else if (result < 75) { //Scored
                        System.out.println("\nScore is " + userscore + ":" + cpuscore);
                        System.out.println("");
                        System.out.println(p[striker - 1].getName() + " Scored ⚽\n " + p[assist - 1].getName() + " With an assist  👟");

                        Thread.sleep(750);
                        userscore += 1;
                    } else { //Got scored on
                        System.out.println("\nScore is " + userscore + ":" + cpuscore);
                        System.out.println("");
                        System.out.println("Opponent Scored ⚽");

                        Thread.sleep(750);
                        cpuscore += 1;
                    }
                }
                System.out.println("\nGame over!");
                System.out.println("Final score is " + userscore + ":" + cpuscore); //Final score

                if (userscore > cpuscore) {
                    System.out.println("You won and got $20");
                    user = true;
                } else if (userscore < cpuscore) {
                    System.out.println("You lost $10");
                    cpu = true;

                } else if (userscore == cpuscore) {
                    System.out.println("Going in to penalties..."); //Penalties 50/50
                    int penalties = rand.nextInt(100);
                    if (penalties < 50) {
                        System.out.println("You won penalties. Won $20");
                        user = true;
                    } else {
                        System.out.println("You lost penalties. Lost $10");
                        cpu = true;
                    }

                }
            }

            if (average > enemy) {
                System.out.println("You have an advantage"); //Advantage //55 percent chance of winning //10 percent chance of draw //35 percent chance of losing//20 foul
                for (int i = 0; i < score; i++) {

                    flag = true;
                    int result = rand.nextInt(100);
                    int fouls = rand.nextInt(100);
                    while (flag) {

                        tackle = rand.nextInt(5) + 6;
                        striker = rand.nextInt(6) + 1;
                        assist = rand.nextInt(6) + 1;
                        if (assist != striker) {
                            flag = false;
                            break;
                        }
                    }
                    if (fouls < 20) { //Yellow card
                        System.out.println(p[tackle - 1].getName() + " got a yellow card 🟨");
                        Thread.sleep(750);
                    } else { //Tackle
                        System.out.println(p[tackle - 1].getName() + " got a clean tackle");
                        Thread.sleep(750);
                    }
                    if (result < 20) { //Opponent
                        System.out.println("\nScore is " + userscore + ":" + cpuscore);
                        System.out.println("");
                        System.out.println("Opponent Scored ⚽");

                        Thread.sleep(750);
                        cpuscore += 1;

                    } else if (result < 30) {//Saved

                        System.out.println(p[10].getName() + " Saved a shot! 🖐");
                        Thread.sleep(750);
                    } else { //Scored
                        System.out.println("\nScore is " + userscore + ":" + cpuscore);
                        System.out.println("");
                        System.out.println(p[striker - 1].getName() + " Scored ⚽\n " + p[assist - 1].getName() + " With an assist 👟");
                        
   

                        Thread.sleep(750);
                        userscore += 1;
                    }

                }
                System.out.println("\nGame over!");
                System.out.println("Final score is " + userscore + ":" + cpuscore);//Final score

                if (userscore > cpuscore) {
                    System.out.println("You won and got $20");
                    user = true;
                } else if (userscore < cpuscore) {
                    System.out.println("You lost $10");
                    cpu = true;

                } else if (userscore == cpuscore) {
                    System.out.println("Going in to penalties..."); //Penalty 50/50
                    int penalties = rand.nextInt(100);
                    if (penalties < 50) {
                        System.out.println("You won penalties. Won $20");
                        user = true;
                    } else {
                        System.out.println("You lost penalties. Lost $10");
                        cpu = true;
                    }

                }
            }
        } catch (InterruptedException e) {

        }

    }
}
