package model;

//Records a match information
public class Match {

    private final String date; //->to int year, int month, int date?
    private double time;
    private String opposingTeam;
    private boolean booking;
    private String result;
    private String impression;

    //constructor
    public Match(String date) {
        this.date = date;
        time = 0;
        opposingTeam = "";
        booking = false;
        result = "";
        impression = "";
    }

    //methods

    //EFFECTS: return match date
    public String getDate() {
        return date;
    }

    //EFFECTS: return match time
    public double getTime() {
        return time;
    }

    //MODIFIES: this
    //EFFECTS: set match time as given integer
    public void setTime(double time) {
        this.time = time;
    }

    //EFFECTS: return the opposing team at the match
    public String getOpposingTeam() {
        return opposingTeam;
    }

    //MODIFIES: this
    //EFFECTS: set opposing team as given string
    public void setOpposingTeam(String opposingTeam) {
        this.opposingTeam = opposingTeam;
    }

    //EFFECTS: return the boolean booking
    public boolean isBooking() {
        return this.booking;
    }

    //MODIFIES: this
    //EFFECTS: set booking as given boolean
    public void setBooking(boolean booking) {
        this.booking = booking;
    }

    //EFFECTS: return result of the match
    public String getResult() {
        return result;
    }

    //REQUIRE: the parameter result should be one of "w", "l" and "n"
    //MODIFIES: this
    //EFFECTS: set result as the given string
    public void setResult(String result) {
        this.result = result;
    }

    //EFFECTS: return impression of the match
    public String getImpression() {
        return impression;
    }

    //MODIFIES: this
    //EFFECTS: set impression as the given string
    public void setImpression(String impression) {
        this.impression = impression;
    }
}