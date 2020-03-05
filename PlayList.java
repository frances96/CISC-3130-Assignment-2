//the Playlist implementation
class Song {
    private String track;
    private Song next;
    
    public Song(String track) {
        this.track = track;
        this.next = null;
    }
	
    public Song getNext() {
	    return next;
    }
	
    public void setNext(Song next) {
	    this.next = next;
    }
    
    public String getTrack() {
	    return track;
	}
}

public class PlayList {
    private Song first, last;
    
    public PlayList() {
	    this.first = null;
    }
    
    public void addSong(Song s) {
	    Song temp = new Song(s.getTrack());
	    if (this.last == null) {
            this.first = this.last = temp;
            return;
        }
	    this.last.setNext(temp);
	    this.last = temp;
    }
	
    //retrieves the next song to listen to 
    public Song listenToSong() {
	    if (this.first == null)
            return null;
            Song temp = this.first; 
        this.first = this.first.getNext();
        if (this.first == null) 
            this.last = null;
        return temp;
	}
    
    //Two queues and returns one into one 
    public MyQueue mergingFunction(MyQueue q1, MyQueue q2) {
        MyQueue merged = new MyQueue();
	      if (!q1.isEmpty() && !q2.isEmpty()) {
            Node n1 = q1.front;
            Node n2 = q2.front;
            if (n1.compare(n2) < 0) {
		            merged.enqueue(q1.dequeue());
            } 
            else {
		          merged.enqueue(q2.dequeue());
            }
	      }	
	      while(!q1.isEmpty()) {
            merged.enqueue(q1.dequeue());
	      }
	      while(!q2.isEmpty()) {
            merged.enqueue(q2.dequeue());
	      }
	      return merged;
    }
}
