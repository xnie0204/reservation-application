package persistence;

import java.io.IOException;

public interface Saveable {
    void save() throws IOException;
}
