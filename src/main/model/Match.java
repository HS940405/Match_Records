package model;

//Records a match information
public class Match {

    private final String date; //->to int year, int month, int date?
    private int time;
    private String opposingTeam;
    private boolean booking;
    private String result;
    private String impression;

    //constructor
    //EFFECTS: Create a Match instance
    public Match(String date) {
        this.date = date;
        time = 0;
        opposingTeam = null;
        booking = false;
        result = null;
        impression = null;
    }

    //methods
    //revise match

    //EFFECTS: Return match date
    public String getDate() {
        return date;
    }

    //EFFECTS: Return match time
    public int getTime() {
        return time;
    }

    //EFFECTS: Set match time as given integer
    public void setTime(int time) {
        this.time = time;
    }

    //EFFECTS: Return the opposing team at the match
    public String getOpposingTeam() {
        return opposingTeam;
    }

    //EFFECTS: Set opposing team as given string
    public void setOpposingTeam(String opposingTeam) {
        this.opposingTeam = opposingTeam;
    }

    //EFFECTS: Return the boolean booking
    public boolean isBooking() {
        return this.booking;
    }

    //EFFECTS: Set booking as given boolean
    public void setBooking(boolean booking) {
        this.booking = booking;
    }

    //EFFECTS: Return result of the match
    public String getResult() {
        return result;
    }

    //REQUIRE: The parameter result should be one of "w", "l" and "n"
    //EFFECTS: Set result as the given string
    public void setResult(String result) {
        this.result = result;
    }

    //EFFECTS: Return impression of the match
    public String getImpression() {
        return impression;
    }

    //EFFECTS: Set impression as the given string
    public void setImpression(String impression) {
        this.impression = impression;
    }
}