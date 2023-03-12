public class CommandListByStreamer extends Command {
    private int streamerId;

    public CommandListByStreamer(int streamerId) {
        this.streamerId = streamerId;
    }

    @Override
    public void execute(Sistem sistem) {
        //sistem.listStreamsOfStreamer(streamerId);
        Streamer streamer = sistem.getStreamerById(streamerId);
        if(streamer == null) {
            System.out.println("Streamerul nu exista");
        } else {
            System.out.print("[");
            for (int i = sistem.getStreamsOfStreamer(streamerId).size() - 1; i >= 0; i--) {
                if (sistem.getStreamsOfStreamer(streamerId).get(i).getStreamerId() == streamerId) {
                    if (i == 0)
                        System.out.print(sistem.getStreamsOfStreamer(streamerId).get(i));
                    else
                        System.out.print(sistem.getStreamsOfStreamer(streamerId).get(i) + ",");
                }
            }
            System.out.println("]");
        }
    }
}