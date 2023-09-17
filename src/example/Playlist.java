package example;


import java.util.Scanner;

public class Playlist {
    private String playlistName;
    private String playlistDescription;
    private LinkedList<Song> songList; // a list of playlist

    public Playlist(String name, String description) {

        this.playlistName = name;
        this.playlistDescription = description;
        this.songList = new LinkedList<>();

    }

    public String getPlaylistName() {
        return this.playlistName;
    }

    public String getPlaylistDescription() {
        return this.playlistDescription;
    }

    public void setPlaylistName(String name) {
        this.playlistName = name;
    }

    public void setPlaylistDescription(String description) {
        this.playlistDescription = description;
    }

    public LinkedList<Song> getSongList() {
        return this.songList;
    }

    //adding songs with duplicate handling
    public boolean addSong(Song song) {

        boolean isDuplicate = false;

        Node<Song> current = songList.getHead();

        while (current != null) {       //handle the song duplicates by checking the list before appending

            Song currentSong = current.getData();
            if (currentSong.getSongTitle().equalsIgnoreCase(song.getSongTitle())) {
                if (currentSong.getSongArtist().equalsIgnoreCase(song.getSongArtist())) {
                    isDuplicate = true;
                    break;
                }
            }
            current = current.getNext();

        }

        if (!isDuplicate) {

            songList.insert(song);

        }

        return isDuplicate;
    }

    //removing songs with duplicate handling
    public void removeSong(String Title) {

        int titleDuplicates = 0;

        Node<Song> current = songList.getHead();
        Node<Song> lastSongMatch = null;

        while (current != null) {

            Song currentSong = current.getData();

            if (currentSong.getSongTitle().equalsIgnoreCase(Title)) {
                titleDuplicates++;
                lastSongMatch = current;
            }

            current = current.getNext();

        }

        if (titleDuplicates == 1) {

            songList.remove(lastSongMatch);
            System.out.println("Song Deleted.");
            return;

        }

        if (titleDuplicates > 1) {  //title duplicates but different artist case

            Scanner scanner = new Scanner(System.in);
            System.out.printf("\nThere are %d songs with the same title.\n\n", titleDuplicates);

            String Artist;
            System.out.print("Specify the artist of the song you wish to delete: ");
            Artist = scanner.nextLine();

            Node<Song> previous = null;
            Node<Song> songToDelete = null;
            current = songList.getHead();

            while (current != null) {

                Song currentSong = current.getData();

                if (currentSong.getSongTitle().equalsIgnoreCase(Title) && currentSong.getSongArtist().equalsIgnoreCase(Artist)) {
                    songToDelete = current;
                    songList.remove(songToDelete);
                    System.out.println("Song deleted.");
                    return;
                }

                previous = current;
                current = current.getNext();

            }

        } else {

            System.out.println("This song is not in the playlist.");

        }
    }


    //function override for the string allowing us to display what we want whenever we do display() call
    @Override
    public String toString() {
        return this.playlistName;
    }
}
