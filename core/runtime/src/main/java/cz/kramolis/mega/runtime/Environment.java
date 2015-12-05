package cz.kramolis.mega.runtime;

public interface Environment {

    void shutdown();

    long getInitNanoTime();

    long getStartNanoTime();

}
