plugins {
    java
    kotlin("jvm") version "1.8.21"
    id("fabric-loom") version "1.2-SNAPSHOT"
}

val minecraft_version: String by project
val yarn_mappings: String by project
val loader_version: String by project
val mod_version: String by project
val maven_group: String by project
val archives_base_name: String by project
val fabric_version: String by project
val fabric_kotlin_version: String by project

version = mod_version
group = maven_group

repositories {
    // Add repositories to retrieve artifacts from in here.
    // You should only use this when depending on other mods because
    // Loom adds the essential maven repositories to download Minecraft and libraries from automatically.
    // See https://docs.gradle.org/current/userguide/declaring_repositories.html
    // for more information about repositories.
}

dependencies {
    // fabric dependencies
    minecraft("com.mojang:minecraft:$minecraft_version")
    mappings("net.fabricmc:yarn:$minecraft_version+build.1:v2")
    modImplementation("net.fabricmc:fabric-loader:0.14.11")

    // Fabric API. This is technically optional, but you probably want it anyway.
    modImplementation("net.fabricmc.fabric-api:fabric-api:$fabric_version")

    // Kotlin adapter for fabric
    modImplementation("net.fabricmc:fabric-language-kotlin:$fabric_kotlin_version")

    // Uncomment the following line to enable the deprecated Fabric API modules.
    // These are included in the Fabric API production distribution and allow you to update your mod
    // to the latest modules at a later more convenient time.

    // modImplementation("net.fabricmc.fabric-api:fabric-api-deprecated:$fabric_version")
}

base {
    archivesName.set(archives_base_name)
}

java {
    withSourcesJar()
}

kotlin {
    jvmToolchain(17)
}

tasks {
    jar {
        from("LICENSE") {
            rename { "${it}_${base.archivesName.get()}" }
        }
    }

    withType<JavaCompile> {
        options.release.set(17)
        options.encoding = "UTF-8"
    }

    processResources {
        inputs.property("version", project.version)
        inputs.property("mcversion", minecraft_version)

        filesMatching("fabric.mod.json") {
            expand(mapOf("version" to version, "mcversion" to minecraft_version))
        }
    }
}
