package pers.lyks.tasks

import org.gradle.api.file.CopySpec
import org.gradle.api.internal.file.copy.CopyAction
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.bundling.Zip

/**
 *
 * @author lawyerance* @version 1.0 2019-09-28
 */
class DistributionTask extends Zip {
    private File archiveStoreDirectory;
    private boolean sameBuildDir = true

    DistributionTask() {
        super()
        dependsOn "build"
        this.archiveStoreDirectory = new File(this.project.getBuildDir(), "libs")
    }

    void setArchiveStoreDirectory(File archiveStoreDirectory) {
        sameBuildDir = this.archiveStoreDirectory == archiveStoreDirectory;
        this.archiveStoreDirectory = archiveStoreDirectory
    }

    @Internal
    File getArchiveStoreDirectory() {
        return archiveStoreDirectory
    }

    @Override
    protected CopyAction createCopyAction() {
        super.createCopyAction()
    }

    void copyBuild() {

        if (!sameBuildDir) {
            into("${archiveStoreDirectory.absolutePath}") {
                from("${project.buildDir}/libs") {
                    include("**/*")
                }
            }
        }
    }

    void release(boolean archive) {
        String message = sameBuildDir ? "temp store path is build libs path:${archiveStoreDirectory.absolutePath}" : "temp store path -----> ${archiveStoreDirectory.absolutePath}"
        println("destination direcotory -----> ${destinationDirectory.get().asFile.absolutePath}")
        println(message)
//        this.doLast {
        if (archive) {
            into("${archiveBaseName.get()}") {
                from(archiveStoreDirectory.absolutePath)
            }
        } else {
            from(archiveStoreDirectory.absolutePath)
        }
//        }

    }

    void release(final Closure action) {
        this.exclude(action)
    }

}
