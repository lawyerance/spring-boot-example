package pers.lyks.plugin

import org.gradle.api.Action
import org.gradle.api.Plugin
import org.gradle.api.Project
import pers.lyks.tasks.DistributionTask

/**
 *
 * @author lawyerance* @version 1.0 2019-09-28
 */
class DistributionPackagePlugin implements Plugin<Project> {
    private final static String INCLUDE_BASE_DIRECTORY_KEY = "include.base.directory"

    @Override
    void apply(Project project) {
        boolean includeArchiveDirectory = project.hasProperty(INCLUDE_BASE_DIRECTORY_KEY) ? Boolean.valueOf(project.findProperty(INCLUDE_BASE_DIRECTORY_KEY).toString()) : false
        project.tasks.create("distribution", DistributionTask, new Action<DistributionTask>() {
            @Override
            void execute(DistributionTask t) {
                t.release(includeArchiveDirectory)
            }
        })
    }
}
