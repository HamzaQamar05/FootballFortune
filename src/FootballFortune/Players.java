/*
 *Name:Hamza
 *Class:Players
 *Description: Deals with all the legendary players so I can call apon their name, country, birth,position and rating
 */
package FootballFortune;


public class Players {

    private String name;
    private String country;
    private String born;
    private String position;
    private int rating;

    //constructer
    Players(String n, String c, String b, String p, int r) {
        name = n;
        country = c;
        born = b;
        position = p;
        rating = r;

    }

    public void setName(String n) {
         name = n;
    }

    public void setCountry(String c) {
        country = c;
    }

    public void setBorn(String b) {
        born = b;
    }

    public void setPosition(String p) {
        position = p;
    }

    public void setRating(int r) {
        rating = r;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getBorn() {
        return born;
    }

    public String getPosition() {
        return position;
    }

    public int getRating() {
        return rating;
    }

    public String toString() {

        return ((getName())+"\nCountry : " + (getCountry()) + "\nPlace of birth: " + (getBorn()) + "\nPosition: " + (getPosition()) + "\nRating: " + (getRating()));
    }
}
