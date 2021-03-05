package persistence;

import java.io.IOException;

public interface Loadable {
    void load() throws IOException;
}
