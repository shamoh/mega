package cz.kramolis.mega.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.ApplicationPlugin
import org.gradle.api.plugins.JavaPlugin
import org.gradle.api.tasks.Copy


class MegaPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        project.with {
            // plugins
            plugins.apply(JavaPlugin)
            plugins.apply(ApplicationPlugin)

            // tasks
            def startTask = task('start', type: StartTask)
            def stopTask = task('stop', type: StopTask)
            def copyResourcesToClassesTask = task('copyResourcesToClasses', type: Copy) {
                from 'build/resources/main'
                into 'build/classes/main'
            }

            // task dependencies
            copyResourcesToClassesTask.mustRunAfter tasks.processResources
            tasks.classes.dependsOn copyResourcesToClassesTask
            startTask.dependsOn tasks.installDist
            startTask.dependsOn stopTask
        }
    }

}
