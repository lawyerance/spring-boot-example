import io.github.swagger2markup.tasks.Swagger2MarkupTask
import org.asciidoctor.gradle.AsciidoctorTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar
import java.io.File


buildscript {
    val kotlinVersion = "1.2.71"
    val springBootVersion = "2.1.8.RELEASE"
    repositories {
        mavenLocal()
        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }
        mavenCentral()
        jcenter()
    }

    dependencies {
        classpath("org.springframework.boot:spring-boot-gradle-plugin:${springBootVersion}")
        classpath("io.spring.gradle:dependency-management-plugin:1.0.8.RELEASE")
        classpath("org.jetbrains.kotlin:kotlin-allopen:${kotlinVersion}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${kotlinVersion}")
        classpath("org.asciidoctor:asciidoctor-gradle-plugin:1.5.8")
        classpath("org.asciidoctor:asciidoctorj-pdf:1.5.0-alpha.10.1")
        classpath("io.github.swagger2markup:swagger2markup-spring-restdocs-ext:1.3.3")
        classpath("io.github.swagger2markup:swagger2markup-gradle-plugin:1.3.3")
    }
}

apply(plugin = "org.springframework.boot")
apply(plugin = "io.spring.dependency-management")
apply(plugin = "org.jetbrains.kotlin.plugin.allopen")
apply(plugin = "org.jetbrains.kotlin.jvm")
apply(plugin = "org.jetbrains.kotlin.kapt")
apply(plugin = "org.asciidoctor.convert")
apply(plugin = "io.github.swagger2markup")
apply(plugin = "java")
apply(plugin = "idea")

group = "com.example"
version = "0.0.1-SNAPSHOT"

configure<JavaPluginConvention> {
    this.sourceCompatibility = JavaVersion.VERSION_1_8
    this.targetCompatibility = JavaVersion.VERSION_1_8
}


repositories {
    mavenLocal()
    maven {
        url = uri("https://repo.spring.io/simple/libs-release/")
    }
    mavenCentral()
}

val implementation by configurations
val testImplementation by configurations
val compile by configurations
val compileOnly by configurations

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    compile("org.mybatis.spring.boot:mybatis-spring-boot-starter:1.3.2")
    compile("mysql:mysql-connector-java:8.0.13")
    compile("io.springfox:springfox-swagger-ui:2.9.2")
    compile("io.springfox:springfox-swagger2:2.9.2")
    compile("org.yaml:snakeyaml:1.23")
}

var swaggerOutputDir: File by extra
var snippetsOutputDir: File by extra
var generatedOutputDir: File by extra

extra["swaggerOutputDir"] = file("build/swgger")
extra["snippetsOutputDir"] = file("build/asciidoc/snippets")
extra["generatedOutputDir"] = file("build/asciidoc/generated")

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}

tasks {
    named<Test>("test") {
        systemProperty("io.springfox.staticdocs.outputDir", swaggerOutputDir)
        systemProperty("io.springfox.staticdocs.snippetsOutputDir", snippetsOutputDir)
    }

    val convertSwagger2markup by existing

    "convertSwagger2markup"(Swagger2MarkupTask::class) {
        dependsOn(named<Test>("test"))
        swaggerInput = swaggerOutputDir.absolutePath + File.separatorChar + "swagger.json"
        outputDir = generatedOutputDir
        config["swagger2markup.pathsGroupedBy"] = "TAGS"
    }

    val asciidoctor by existing

    "asciidoctor"(AsciidoctorTask::class) {
        this.logDocuments = true
        dependsOn(convertSwagger2markup)
        this.inputs.dir(generatedOutputDir)
        sources(delegateClosureOf<PatternSet> {
            include("*.adoc")
        })
        this.backends("html5", "pdf")
        this.attributes["doctype"] = "book"
        this.attributes["toc"] = "left"
        this.attributes["toclevels"] = "3"
        this.attributes["numbered"] = ""
        this.attributes["sectlinks"] = ""
        this.attributes["sectanchors"] = ""
        this.attributes["hardbreaks"] = ""
        this.attributes["generated"] = generatedOutputDir
    }

    // invalid ??
    "jar"(Jar::class) {
        val asciidoc: AsciidoctorTask = asciidoctor.get() as AsciidoctorTask
        dependsOn(asciidoctor)
        from("${asciidoc.outputDir}/htmls") {
            into("META-INF/resources/docs")
        }
    }

    "bootJar"(BootJar::class) {
        this.archiveFileName.set("${project.name}.jar")
        this.archiveClassifier.set("boot")
        this.exclude("*.jar")
        this.include("**/*.class", "MANIFEST.MF")
        this.manifest {
            attributes["Manifest-Version"] = 1.0
            attributes["Class-Path"] = "config/"
        }
    }

    val build by existing
    val netesedSpecs by registering(Copy::class)

    netesedSpecs {
        into("$buildDir/explodedJar")

        into("lib") {
            from(configurations.named("compileClasspath"))
        }

        into("config") {
            from("$buildDir/resources/main") {
                include("**/*")
            }
        }
        from("$buildDir/libs") {
            include("*.jar")
        }
        into("bin") {
            from("scripts")
        }
    }

    val packageDistribution by registering(Zip::class) {
        dependsOn(build)
        dependsOn(netesedSpecs)
        this.archiveFileName.set("${project.name}-release.zip")
        this.destinationDirectory.set(file("$buildDir/distribution"))
        from("$buildDir/explodedJar")
    }
}
