plugins {
    kotlin("jvm") version "1.8.21"
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("io.papermc.paperweight.userdev") version "1.5.3"
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

val buildPath = File("C:/Files/Minecraft/Servers/Test/plugins")
val mcVersion = "1.20.1"
val kotlinVersion = kotlin.coreLibrariesVersion

repositories {
    mavenCentral()
    maven("https://repo.blugon.kr/repository/maven-public/")
}

dependencies {
    compileOnly(kotlin("stdlib"))
    compileOnly(kotlin("reflect"))
    paperweight.paperDevBundle("${mcVersion}-R0.1-SNAPSHOT")
//    compileOnly("io.github.monun:invfx-api:latest.release")
    compileOnly("io.github.monun:kommand-api:latest.release")
    compileOnly("io.github.monun:tap-api:latest.release")
    implementation("kr.blugon:pluginPlus:latest.release")
}

extra.apply {
    set("ProjectName", project.name)
    set("ProjectVersion", project.version)
    set("KotlinVersion", kotlinVersion)
    set("MinecraftVersion", mcVersion.split(".").subList(0, 2).joinToString("."))
    set("kommand", "io.github.monun:kommand-core:3.1.6")
    set("tap", "io.github.monun:tap-core:4.9.6")
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = JavaVersion.VERSION_17.toString()
    }

    processResources {
        filesMatching("*.yml") {
            expand(project.properties)
            expand(extra.properties)
        }
    }

    create<Jar>("buildPaper") {
        val file = File("./build/libs/${project.name}.jar")
        if(file.exists()) file.deleteOnExit()
        archiveBaseName.set(project.name) //Project Name
        archiveFileName.set("${project.name}.jar") //Build File Name
        archiveVersion.set(project.version.toString()) //Version
        from(sourceSets["main"].output)

        doLast {
            copy {
                from(archiveFile) //Copy from
                into(buildPath) //Copy to
            }
        }
    }

    shadowJar {
        val file = File("./build/libs/${project.name}.jar")
        if(file.exists()) file.deleteOnExit()
        archiveBaseName.set(project.name) //Project Name
        archiveFileName.set("${project.name}.jar") //Build File Name
        archiveVersion.set(project.version.toString()) //Version
        from(sourceSets["main"].output)

        doLast {
            copy {
                from(archiveFile) //Copy from
                into(buildPath) //Copy to
            }
        }
    }
}