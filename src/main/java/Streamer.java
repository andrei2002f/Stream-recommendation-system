public abstract class Streamer {
    private int streamerType;
    private int id;
    private String name;

    public Streamer(int streamerType, int idStreamer, String name) {
        this.streamerType = streamerType;
        this.id = idStreamer;
        this.name = name;
    }

    public int getStreamerType() {
        return streamerType;
    }
    public void setStreamerType(int streamerType) {
        this.streamerType = streamerType;
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
}
