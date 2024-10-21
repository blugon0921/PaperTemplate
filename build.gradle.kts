import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlin)
    alias(libs.plugins.pluginYml)
    alias(libs.plugins.runPaper)
    alias(libs.plugins.shadowJar)
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

val buildPath = File("C:/Files/Minecraft/Servers/Default/plugins")

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://repo.blugon.kr/repository/maven-public/")
}

dependencies {
    paperLibrary(kotlin("stdlib"))
    compileOnly(libs.paper)

    paperLibrary(libs.coroutine)
    paperLibrary(libs.mcCoroutine)
    paperLibrary(libs.mcCoroutineCore)

    paperLibrary(libs.pluginUtils)
    paperLibrary(libs.kotlinBrigadier)
    paperLibrary(libs.miniColor)
}

paper {
    main = "$group.${project.name.lowercase()}.${project.name}"
    loader = "$group.${project.name.lowercase()}.loader.${project.name}Loader"

    version = project.version.toString()
    apiVersion = libs.versions.paper.get().replace("-R0.1-SNAPSHOT", "")
    author = "Blugon"
    prefix = project.name

    generateLibrariesJson = true
}

tasks {
    compileKotlin {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_21)
        }
    }

    create<Jar>("buildPaper") { this.build() }
    shadowJar { this.build() }

    runServer {
        minecraftVersion(libs.versions.paper.get().replace("-R0.1-SNAPSHOT", ""))
        jvmArgs = listOf("-Dcom.mojang.eula.agree=true")
    }
}

fun Jar.build() {
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