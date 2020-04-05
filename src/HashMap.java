import java.util.ArrayList;
import java.util.List;

public class HashMap implements SimpleMap {

    private List<Entry>[ ] hashArray = null;
    private int[] intArray = new int[4];
    private ArrayList<Entry> entryList = new ArrayList<>();
    private Entry entry = null;
    private int size = 0;

    @SuppressWarnings( { "rawtypes", "unused", "unchecked" } )
    public HashMap ( int capacity ) {
        hashArray = (List<Entry>[]) new List[capacity];
    }

    @Override
    public Integer get(String key) {
        return null;
    }

    @Override
    public Integer put(String key, Integer value) {
        return null;
    }

    @Override
    public Integer remove(String key) {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return hashArray.length == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int capacity() {
        return hashArray.length;
    }

    @Override
    public double loadFactor() {
        return 0;
    }

    @Override
    public int loadVariance() {
        return 0;
    }

    @Override
    public List<Entry> entryList() {
        return null;
    }

    @Override
    public List<Entry>[] hashArray() {
        return new List[0];
    }
}
