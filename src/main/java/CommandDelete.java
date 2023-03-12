public class CommandDelete extends Command{
    private int streamId;
    private int streamerId;

    public CommandDelete(int streamerId, int streamId) {
        this.streamId = streamId;
        this.streamerId = streamerId;
    }

    @Override
    public void execute(Sistem sistem) {
        //sistem.deleteStream(streamId);
        Stream stream = sistem.getStreamById(streamId);
        if(stream == null) {
            System.out.println("Stream-ul nu exista");
        } else {
            if(stream.getStreamerId() == streamerId) {
                sistem.getStreams().remove(stream);
                for(User user : sistem.getUsers()) {
                    if(user.getStreams().contains(streamId)) {
                        user.getStreams().remove((Integer)streamId);
                    }
                }
            } else {
                System.out.println("Nu aveti dreptul sa stergeti acest stream");
            }
        }
    }
}
