//The SongHistoryList implementation

public class SongHistoryList {
    private Song first;
    
    public void SongHistoryList() {
    	this.first = null;
    } 
	
    public void addSong(Song s) {
        Song temp = new Song(s.getTrack());
	      temp.setNext(first);
	      first = temp;
    }
    
    // retrieves the next song to listen to 
    public Song lastListened() {
	    if (this.first == null) {
            return null;
        }
	    Song temp = this.first;
	    this.first  = first.getNext();
	    return temp;
    }
}