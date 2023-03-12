import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class CommandRecommend extends Command {
    int userId;
    int streamType;

    public CommandRecommend(int userId, int streamType) {
        this.userId = userId;
        this.streamType = streamType;
    }

    @Override
    public void execute(Sistem sistem) {
        List<Integer> streamersList = sistem.getUserById(userId).streamersListenedTo(sistem);
        // creez lista de stream-uri
        List<Integer> streamsList = new ArrayList<>();
        for(Stream stream : sistem.getStreams()) {
            if(streamersList.contains(stream.getStreamerId())) {
                if(stream.getStreamType() == streamType) {
                    streamsList.add(stream.getId());
                }
            }
        }
        // sortez lista de stream-uri dupa numarul de ascultari
        for(int i = 0; i < streamsList.size() - 1; i++) {
            for(int j = i + 1; j < streamsList.size(); j++) {
                if(sistem.getStreamById(streamsList.get(i)).getNoOfStreams() < sistem.getStreamById(streamsList.get(j)).getNoOfStreams()) {
                    int aux = streamsList.get(i);
                    streamsList.set(i, streamsList.get(j));
                    streamsList.set(j, aux);
                }
            }
        }

        int count = 0;
        // afisez primele 5 stream-uri
        System.out.print("[");
        String afisare = "";
        for (int streamId : streamsList) {
            if(count == 5) {
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
