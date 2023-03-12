import java.util.Iterator;

public class CommandListByUser extends Command {
    private int userId;

    public CommandListByUser(int userId) {
        this.userId = userId;
    }

    @Override
    public void execute(Sistem sistem) {
        //sistem.listStreamsOfUser(userId);
        User user = sistem.getUserById(userId);
        if(user == null) {
            System.out.println("Userul nu exista");
        } else {
            System.out.print("[");
            Iterator<Integer> streamIterator = user.getStreams().iterator();
            while(streamIterator.hasNext()) {
                System.out.print(sistem.getStreamById(streamIterator.next()));
                if(streamIterator.hasNext()) {
                    System.out.print(",");
                }
            }
            System.out.println("]");
        }
    }
}