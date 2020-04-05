import java.util.List;

public class HashMap implements SimpleMap {
    @Override
    public int hashFn(String key) {
        return 0;
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
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public int capacity() {
        return 0;
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
