package cz.kramolis.mega.gradle

import org.gradle.api.DefaultTask
import org.gradle.api.logging.Logger
import org.gradle.api.logging.Logging

abstract class AbstractMegaTask extends DefaultTask {

    private final Logger logger;

    AbstractMegaTask() {
        this.logger = Logging.getLogger(this.getClass());
        group = "Mega tasks"
    }

    @Override
    Logger getLogger() {
        return logger
    }

}
