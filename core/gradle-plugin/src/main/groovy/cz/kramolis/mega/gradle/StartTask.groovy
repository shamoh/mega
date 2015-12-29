package cz.kramolis.mega.gradle

import org.gradle.api.tasks.TaskAction

class StartTask extends AbstractExecutionTask {

    StartTask() {
        description = "Start an application process in the background."
    }

    @TaskAction
    void startProcess() {
        def mainClassName = project.convention.plugins.application.mainClassName
        def command = ['java',
                       '-classpath', project.sourceSets.main.runtimeClasspath.join(":"),
                       "-Dmega.SystemOutFileName=${project.buildDir.path}/mega-run.out", //TODO configure it
                       "-Dmega.SystemErrFileName=${project.buildDir.path}/mega-run.err", //TODO configure it
                       mainClassName]
        logger.debug("Mega Start task command: " + command)
        def process = command.execute()

        def pidFile = getPidFile()
        pidFile << process.pid
        process.in.close()
        process.out.close()
        process.err.close()
    }

}
