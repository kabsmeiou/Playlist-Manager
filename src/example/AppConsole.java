package example;

//note: the nextLine() without assigned variables are for continue... prompts or buffer clears.
import java.io.IOException;
import java.util.*;
//import javadsa.linkedList.Art;
public class AppConsole {

    public static void clearScreen() {  //a function for clearing the screen
        for (int i = 0; i < 10; i++) {
            System.out.print("\n");
        }
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        LinkedList<Playlist> playlistList = new LinkedList<>(); //the list of playlist declared and is initially empty
        //Art artGen = new Art();
        //artGen.printTextArt("Song Playlist Manager", Art.ART_SIZE_SMALL);

        while (true) {

            System.out.println("====================");
            System.out.println("Song Playlist Manager");
            System.out.println("====================\n");
            System.out.println("1. Create Playlist - Create a new playlist.");
            System.out.println("2. View Playlists - Display existing playlists.");
            System.out.println("3. Exit - Close the application.");

            int actionChoice;
            System.out.print("Choose Action (e.g., 1): ");
            actionChoice = scanner.nextInt();

            switch (actionChoice) {
                case 1:
                    createPlaylist(playlistList);
                    clearScreen();
                    break;
                case 2:
                    if (playlistList.getHead() == null) {
                        clearScreen();
                        scanner.nextLine();
                        System.out.println("\nYou haven't created any playlist yet.");
                        System.out.print("Press enter to return to Main Menu.");
                        scanner.nextLine();
                        clearScreen();
                        break;
                    }
                    viewPlaylist(playlistList);
                    clearScreen();
                    break;
                case 3:
                    System.out.print("Program Ended.");
                    return;
                default:
                    clearScreen();
                    System.out.println("\n\nInvalid Input! You may only choose numbers 1, 2, or 3.\n");
                    break;
            }
        }
    }

    public static void createPlaylist(LinkedList<Playlist> playlistList) {

        clearScreen();

        System.out.println("PLAYLIST CREATION\n");

        Scanner scanner = new Scanner(System.in);
        //prompt for name and description for playlist
        String playlistName;
        System.out.print("Enter Playlist name: ");
        playlistName = scanner.nextLine().trim();

        String playlistDescription;
        System.out.print("Enter Playlist description: ");
        playlistDescription = scanner.nextLine().trim();

        Playlist newPlaylist = new Playlist(playlistName, playlistDescription);
        playlistList.insert(newPlaylist);

        System.out.println("Playlist Created!");
        System.out.print("Press enter to continue...");
        scanner.nextLine();
    }

    public static void viewPlaylist(LinkedList<Playlist> mainList) {

        Scanner scanner = new Scanner(System.in);

        while (true) {

            clearScreen();

            System.out.print("\nYOUR PLAYLISTS\n\n");

            mainList.display();  //display the list of playlist

            String action;      //action handling on the playlists
            System.out.print("Choose action (e.g., delete 1): ");
            action = scanner.next();

            if (action.equalsIgnoreCase("view")) {

                clearScreen();

                int playlistNumber;
                playlistNumber = scanner.nextInt();

                Node<Playlist> playlistNode = mainList.find(playlistNumber, mainList);  //get the  playlist node
                if (playlistNode == null) {

                    System.out.println("This playlist does not exist in this list.");
                    System.out.print("Press enter to return...");
                    scanner.nextLine();
                    continue;

                }

                thisPlaylist(playlistNode);
                clearScreen();

            } else if (action.equalsIgnoreCase("delete")) {

                int playlistNumber;
                playlistNumber = scanner.nextInt();
                Node<Playlist> playlistNode = mainList.find(playlistNumber, mainList);

                if (playlistNode == null) {

                    System.out.println("This playlist does not exist in this list.");
                    System.out.print("Press enter to return...");
                    scanner.nextLine();
                    continue;

                }

                mainList.remove(playlistNode);
                scanner.nextLine();
                System.out.println("Playlist deleted.");
                System.out.print("Press enter to return to Main Menu...");
                scanner.nextLine();
                return;

            } else if (action.equalsIgnoreCase("exit")) {

                clearScreen();
                return;

            } else {

                scanner.nextLine();
                System.out.printf("Invalid input! What is %s?", action);
                System.out.print("\nPress enter to continue...");
                scanner.nextLine();

            }
        }
    }

    public static void thisPlaylist(Node<Playlist> playlistNode) {

        Scanner scanner = new Scanner(System.in);

        while (true) {

            clearScreen();

            LinkedList<Song> playlistSongs = playlistNode.getData().getSongList(); //song list access

            Playlist playlistObject = playlistNode.getData(); //the actual playlist

            String description = playlistObject.getPlaylistDescription();
            String playlistName = playlistObject.getPlaylistName();
            System.out.print("PLAYLIST NAME: " + playlistName + "\n\nDESCRIPTION: " + description + "\n\n");

            if (playlistSongs.getHead() == null) {

                System.out.println("This playlist does not have a song yet.");

            } else {

                playlistSongs.display();

            }

            String action;      //action handling on the songs
            System.out.print("Choose action (e.g., play 1): ");
            action = scanner.next();

            if (action.equalsIgnoreCase("add")) { //adding songs

                clearScreen();
                String title, artist;
                int duration;

                //prompt for song data
                scanner.nextLine();
                System.out.println("==========================");
                System.out.println("ADD A SONG TO THE PLAYLIST");
                System.out.println("==========================");
                System.out.print("Enter song title: ");
                title = scanner.nextLine().trim();
                System.out.print("Enter song artist: ");
                artist = scanner.nextLine().trim();
                System.out.print("Enter song duration(in seconds): ");
                duration = scanner.nextInt();

                Song song = new Song(title, artist, duration);
                boolean duplicate = playlistObject.addSong(song);

                if (duplicate) {

                    scanner.nextLine();
                    System.out.println("This song already exists.");

                } else {

                    System.out.println("Song added to the playlist.");

                }

                System.out.print("Press enter to continue...");
                scanner.nextLine();

            } else if (action.equalsIgnoreCase("exit")) {  //exiting the playlist and return to the list of playlists

                clearScreen();
                return;

            } else if (action.equalsIgnoreCase("play")) { //playing a song in the playlist followed by the next/pause option

                clearScreen();

                int index;
                index = scanner.nextInt();

                Node<Song> songNode = playlistSongs.find(index, playlistSongs);

                if (songNode == null) {

                    clearScreen();
                    scanner.nextLine();
                    System.out.println("The song does not exist in this list.");
                    System.out.print("Press enter to return...");
                    scanner.nextLine();
                    continue;

                }

                Song currentSong = songNode.getData();
                currentSong.playThis(currentSong);
                scanner.nextLine();

                while (true) {

                    String todo;
                    System.out.print("\nChoose action (next/exit): ");
                    todo = scanner.nextLine();

                    if (todo.equalsIgnoreCase("next")) {

                        index++;
                        songNode = playlistSongs.find(index, playlistSongs);

                        if (songNode == null) {

                            System.out.println("There is no next song.");
                            System.out.print("Press enter to return...");
                            scanner.nextLine();
                            break;

                        }

                        currentSong = songNode.getData();
                        clearScreen();
                        currentSong.playThis(currentSong);

                    } else if (todo.equalsIgnoreCase("exit")) { //exit for the playing interface

                        break;

                    } else {

                        scanner.nextLine();
                        System.out.printf("Invalid input! What is %s?", todo);
                        System.out.print("\nPress enter to continue...");
                        scanner.nextLine();

                    }
                }
            } else if (action.equalsIgnoreCase("delete")) { //delete song

                scanner.nextLine();//buffer clear
                System.out.print("Enter the title of the song you want to delete: ");
                String songTitle;
                songTitle = scanner.nextLine().trim();
                playlistObject.removeSong(songTitle);

                System.out.print("Press enter to continue...");
                scanner.nextLine();

            } else {

                scanner.nextLine();
                System.out.printf("Invalid input! What is %s?", action);
                System.out.print("\nPress enter to continue...");
                scanner.nextLine();

            }
        }
    }

}
