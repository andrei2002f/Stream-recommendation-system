import java.util.ArrayList;
import java.util.List;

public class CommandSurprise extends Command{
    int userId;
    int streamType;

    public CommandSurprise(int userId, int streamtype) {
        this.userId = userId;
        this.streamType = streamtype;
    }

    @Override
    public void execute(Sistem sistem) {
        List<Integer> streamers = sistem.getUserById(userId).streamersNotListenedTo();
        // creez lista de stream-uri
        List<Integer> streamsList = new ArrayList<Integer>();
        for(Stream stream : Sistem.getInstance().getStreams()) {
            if(streamers.contains(stream.getStreamerId())) {
                if(stream.getStreamType() == streamType) {
                    streamsList.add(stream.getId());
                }
            }
        }
        // sortez lista de stream-uri dupa data adaugarii si nr de ascultari
        for(int i = 0; i < streamsList.size() - 1; i++) {
            for(int j = i + 1; j < streamsList.size(); j++) {
                Stream stream1 = sistem.getStreamById(streamsList.get(i));
                Stream stream2 = sistem.getStreamById(streamsList.get(j));
                if (stream1.unixTimeToDate(stream1.getDateAdded()).compareTo(stream2.unixTimeToDate(stream2.getDateAdded())) == 0) {
                    if(stream1.getNoOfStreams() < stream2.getNoOfStreams()) {
                        int aux = streamsList.get(i);
                        streamsList.set(i, streamsList.get(j));
                        streamsList.set(j, aux);
                    }
                } else if (stream1.getDateAdded() < stream2.getDateAdded()) {
                    int aux = streamsList.get(i);
                    streamsList.set(i, streamsList.get(j));
                    streamsList.set(j, aux);
                }
            }
        }
        int count = 0;
        // afisez primele 3 stream-uri
        System.out.print("[");
        String afisare = "";
        for (int streamId : streamsList) {
            if(count == 3) {
                break;
            }
            if(!sistem.getUserById(userId).getStreams().contains(streamId)) {
                //System.out.println(sistem.getStreamById(streamId));
                afisare += sistem.getStreamById(streamId) + ",";
                count++;
            }
        }
        System.out.print(afisare.substring(0, afisare.length() - 1));
        System.out.println("]");
    }
}
