package persistence;

import java.io.IOException;

public interface Saveable {
    void save(String destination) throws IOException;
}
