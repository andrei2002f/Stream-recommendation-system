import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Stream {
    private int streamType;
    private int id;
    private int streamGenre;
    private long noOfStreams;
    private int streamerId;
    private long length;
    private long dateAdded;
    private String name;

    public Stream(int streamType, int idStream, int streamGenre, long noOfStreams, int streamerId, long length, long dateAdded, String name) {
        this.streamType = streamType;
        this.id = idStream;
        this.streamGenre = streamGenre;
        this.noOfStreams = noOfStreams;
        this.streamerId = streamerId;
        this.length = length;
        this.dateAdded = dateAdded;
        this.name = name;
    }

    public String toString() {
        return "{" +
                "\"id\":\"" + id +
                "\",\"name\":\"" + name +
                "\",\"streamerName\":\"" + Sistem.getInstance().getStreamerById(streamerId).getName() +
                "\",\"noOfListenings\":\"" + noOfStreams +
                "\",\"length\":\"" + secondsToTime(length) +
                "\",\"dateAdded\":\"" + unixTimeToDate(dateAdded) +
                "\"}";
    }

    public String secondsToTime(long seconds) {
        long hours = seconds / 3600;
        long minutes = (seconds % 3600) / 60;
        seconds = seconds % 60;
        if (hours >= 1) {
            return String.format("%02d:%02d:%02d", hours, minutes, seconds);
        } else {
            return String.format("%02d:%02d", minutes, seconds);
        }
    }

    public String unixTimeToDate(long unixTime) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
        return formatter.format(new Date(dateAdded * 1000));
    }

    public int getStreamType() {
        return streamType;
    }
    public void setStreamType(int streamType) {
        this.streamType = streamType;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getStreamGenre() {
        return streamGenre;
    }
    public void setStreamGenre(int streamGenre) {
        this.streamGenre = streamGenre;
    }
    public long getNoOfStreams() {
        return noOfStreams;
    }
    public void setNoOfStreams(long noOfStreams) {
        this.noOfStreams = noOfStreams;
    }
    public int getStreamerId() {
        return streamerId;
    }
    public void setStreamerId(int streamerId) {
        this.streamerId = streamerId;
    }
    public long getLength() {
        return length;
    }
    public void setLength(long length) {
        this.length = length;
    }
    public long getDateAdded() {
        return dateAdded;
    }
    public void setDateAdded(long dateAdded) {
        this.dateAdded = dateAdded;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
