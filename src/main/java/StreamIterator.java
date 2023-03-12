import java.util.ArrayList;
import java.util.Iterator;

public class StreamIterator implements Iterator<Stream> {
    private int index;
    private ArrayList<Stream> streams;

    public StreamIterator(ArrayList<Stream> streams) {
        this.streams = streams;
        index = 0;
    }

    @Override
    public boolean hasNext() {
        if(index < streams.size()) {
            return true;
        }
        return false;
    }

    @Override
    public Stream next() {
        if(this.hasNext()) {
            return streams.get(index++);
        }
        return null;
    }
}
