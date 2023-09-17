package example;

public class Song {
    String SongArtist;
    String SongTitle;
    int SongDuration;
    //String SongAlbum;
    //String SongGenre;

    //song constructor
    public Song(String Title, String Artist, int Duration) {
        this.SongArtist = Artist;
        this.SongTitle = Title;
        this.SongDuration = Duration;
    }

    public String getSongArtist() {
        return this.SongArtist;
    }

    public String getSongTitle() {
        return this.SongTitle;
    }

    public int getSongDuration() {
        return this.SongDuration;
    }

    public void setSongArtist(String Artist) {
        this.SongArtist = Artist;
    }

    public void setSongTitle(String Title) {
        this.SongTitle = Title;
    }

    public void setSongDuration(int Duration) {
        this.SongDuration = Duration;
    }

    public void playThis(Song song) {
        System.out.print("NOW PLAYING: " + song.getSongTitle() + " by " + song.getSongArtist() + " (" + durationConvert(song.getSongDuration()) + ")");
    }

    public String durationConvert(int seconds) {

        String minutes = "";
        minutes += String.valueOf(seconds / 60);

        int temp = seconds % 60; //get how many minutes

        if (temp < 10) {  //format handling for when the integer is less than 10. this is important since we are converting an integer to string for display

            if (temp == 0) {

                minutes += ":00";

            } else {

                minutes += ":0" + String.valueOf(temp);

            }

        } else {

            minutes += ":" + String.valueOf(temp);

        }

        return minutes;

    }

    //to string method for displaying the songs(same as playlist toString())
    @Override
    public String toString() {
        return this.getSongTitle() + " by " + this.getSongArtist() + " (Duration: " + durationConvert(this.SongDuration) + ")";
    }
}
