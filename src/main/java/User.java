import java.util.ArrayList;
import java.util.List;

public class User {
    private int id;
    private String name;
    private List<Integer> streams;

    public User(String name, int idUser) {
        this.id = idUser;
        this.name = name;
        this.streams = new ArrayList<Integer>();
    }

    public List<Integer> streamersListenedTo(Sistem sistem) {
        List<Integer> streamers = new ArrayList<>();
        for(Stream stream : Sistem.getInstance().getStreams()) {
            if(streams.contains(stream.getId())) {
                if(!streamers.contains(stream.getStreamerId())) {
                    streamers.add(stream.getStreamerId());
                }
            }
        }
        return streamers;
    }

    public List<Integer> streamersNotListenedTo() {
        List<Integer> streamers = new ArrayList<Integer>();
        List<Integer> streamersListenedTo = streamersListenedTo(Sistem.getInstance());
        for(Streamer streamer : Sistem.getInstance().getStreamers()) {
            if(!streamersListenedTo.contains(streamer.getId())) {
                streamers.add(streamer.getId());
            }
        }
        return streamers;
    }


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<Integer> getStreams() {
        return streams;
    }
    public void setStreams(List<Integer> streams) {
        this.streams = streams;
    }

    public void addStream(int idStream) {
        streams.add(idStream);
    }
}
