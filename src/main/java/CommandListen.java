public class CommandListen extends Command {
    private int userId;
    private int streamId;

    public CommandListen(int userId, int streamId) {
        this.userId = userId;
        this.streamId = streamId;
    }

    @Override
    public void execute(Sistem sistem) {
        //sistem.listenStream(userId, streamId);
        User user = sistem.getUserById(userId);
        Stream stream = sistem.getStreamById(streamId);
        if(user == null) {
            System.out.println("Userul nu exista");
        } else if(stream == null) {
            System.out.println("Stream-ul nu exista");
        } else {
            user.getStreams().add(streamId);
            stream.setNoOfStreams(stream.getNoOfStreams() + 1);
        }
    }
}
