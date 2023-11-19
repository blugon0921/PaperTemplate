plugins {
    kotlin("jvm") version "1.9.20"
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("io.papermc.paperweight.userdev") version "1.5.9"
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

val buildPath = File("C:/Files/Minecraft/Servers/Default/plugins")
val mcVersion = "1.20.2"
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
    implementation("kr.blugon:pluginPlus:latest.release")
}

extra.apply {
    set("ProjectName", project.name)
    set("ProjectVersion", project.version)
    set("KotlinVersion", kotlinVersion)
    set("MinecraftVersion", mcVersion.split(".").subList(0, 2).joinToString("."))
//    set("invfx", "io.github.monun:invfx-core:3.3.2")
    set("kommand", "io.github.monun:kommand-core:3.1.8")
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