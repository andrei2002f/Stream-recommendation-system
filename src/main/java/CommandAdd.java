public class CommandAdd extends Command{
    int streamerId;
    int streamType;
    int streamId;
    int streamGenre;
    long length;
    String name;
    int noOfStreams;

    public CommandAdd(int streamerId, int streamType, int streamId, int streamGenre, long length, String name) {
        this.streamerId = streamerId;
        this.streamType = streamType;
        this.streamId = streamId;
        this.streamGenre = streamGenre;
        this.length = length;
        this.name = name;
        this.noOfStreams = 0;
    }

    @Override
    public void execute(Sistem sistem) {
        //sistem.createStream(streamType, streamId, streamGenre, length, name, streamerId);
        Streamer streamer = sistem.getStreamerById(streamerId);
        if(streamer == null) {
            System.out.println("Streamerul nu exista");
        } else {
            Stream stream = new Stream(streamType, streamId, streamGenre, 0, streamerId, length, sistem.getTime(), name);
            sistem.getStreams().add(0, stream);
        }
    }
}
