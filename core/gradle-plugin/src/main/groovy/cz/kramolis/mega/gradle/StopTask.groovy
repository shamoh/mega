package cz.kramolis.mega.gradle

import org.gradle.api.tasks.TaskAction

class StopTask extends AbstractExecutionTask {

    StopTask() {
        description = "Stop a running application."
    }

    @TaskAction
    void stopProcess() {
        def pidFile = getPidFile()
        if (pidFile.exists()) {
            def pid = pidFile.text
            if (pid) {
                def process = "kill -15 $pid".execute()

                try {
                    process.waitFor()
                } finally {
                    pidFile.delete()
                }
            }
        }
    }

}
