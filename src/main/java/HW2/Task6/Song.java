package HW2.Task6;

/**
 * @author Alexey
 */
public class Song implements Comparable<Song> {
    private String title;
    private String artist;
    private double rank;

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public double getRank() {
        return rank;
    }

    public Song(String title, String artist, double rank) {
        this.title = title;
        this.artist = artist;
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "Song{" +
                "title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                ", rank=" + rank +
                '}';
    }




    //Sort by Artist
    @Override
    public int compareTo(Song o) {
        //if Artists equals sort by Title
        if(this.artist.equals(o.getArtist())){
            return this.title.compareTo(o.getTitle());
        }
        return this.artist.compareTo(o.getArtist());
    }
}
