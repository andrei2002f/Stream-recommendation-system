import java.util.ArrayList;
import java.util.List;

public class Sistem {
    private static Sistem Sistem;
    private ArrayList<Streamer> streamers;
    private ArrayList<User> users;
    private ArrayList<Stream> streams;

    private Sistem() {
    }
    public static Sistem getInstance() {
        if(Sistem == null) {
            Sistem = new Sistem();
            Sistem.streamers = new ArrayList<Streamer>();
            Sistem.users = new ArrayList<User>();
            Sistem.streams = new ArrayList<Stream>();
        }
        return Sistem;
    }

    public static Streamer createStreamer(int streamerType, int idStreamer, String name) {
        if(streamerType == 1) {
            return new Muzician(name, idStreamer);
        } else if(streamerType == 2) {
            return new GazdaPodcast(name, idStreamer);
        } else if(streamerType == 3) {
            return new AutorAudiobook(name, idStreamer);
        } else {
            return null;
        }
    }
    public ArrayList<Stream> getStreams() {
        return streams;
    }
    public ArrayList<Streamer> getStreamers() {
        return streamers;
    }
    public ArrayList<User> getUsers() {
        return users;
    }
    public Streamer getStreamerById(int streamerId) {
        for(Streamer streamer : streamers) {
            if(streamer.getId() == streamerId) {
                return streamer;
            }
        }
        return null;
    }
    public User getUserById(int userId) {
        for(User user : users) {
            if(user.getId() == userId) {
                return user;
            }
        }
        return null;
    }
    public Stream getStreamById(int streamId) {
        StreamIterator streamIterator = new StreamIterator(streams);
        while(streamIterator.hasNext()) {
            Stream stream = streamIterator.next();
            if(stream.getId() == streamId) {
                return stream;
            }
        }
        return null;
    }
    public ArrayList<Stream> getStreamsOfStreamer(int streamerId) {
        StreamIterator streamIterator = new StreamIterator(streams);
        ArrayList<Stream> streamsOfStreamer = new ArrayList<Stream>();
        while(streamIterator.hasNext()) {
            Stream stream = streamIterator.next();
            if(stream.getStreamerId() == streamerId) {
                streamsOfStreamer.add(stream);
            }
        }
        return streamsOfStreamer;
    }
    public long getTime() {
        return System.currentTimeMillis() / 1000L;
    }

}
