package cz.kramolis.mega.gradle

abstract class AbstractExecutionTask extends AbstractMegaTask {

    File getPidFile() {
        return new File(project.buildDir.path, 'mega-run.pid') //TODO configure it
    }

}
