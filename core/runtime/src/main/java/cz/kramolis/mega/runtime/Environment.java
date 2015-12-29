package cz.kramolis.mega.runtime;

import java.util.List;

public interface Environment {

    List<String> getArgs();

    void shutdown();

    long getStartNanoTime();

    long getRunNanoTime();

}
