import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    kotlin("jvm") version "1.8.10"
    id("fabric-loom") version "1.1-SNAPSHOT"
}

val sourceCompatibility = JavaVersion.VERSION_17
val targetCompatibility = JavaVersion.VERSION_17

val archivesBaseName: String by project
val version: String by project
val group: String by project

val fabric_version: String by project
val minecraft_version: String by project

java.withSourcesJar()

dependencies {
    // fabric dependencies
    minecraft("com.mojang:minecraft:$minecraft_version")
    mappings("net.fabricmc:yarn:$minecraft_version+build.1:v2")
    modImplementation("net.fabricmc:fabric-loader:0.14.11")

    // Fabric API. This is technically optional, but you probably want it anyway.
    modImplementation("net.fabricmc.fabric-api:fabric-api:$fabric_version")

    // Uncomment the following line to enable the deprecated Fabric API modules.
    // These are included in the Fabric API production distribution and allow you to update your mod
    // to the latest modules at a later more convenient time.

    // modImplementation("net.fabricmc.fabric-api:fabric-api-deprecated:$fabric_version")
}

tasks {
    jar { 
        from("LICENSE") { 
            rename { "${it}_$archivesBaseName" } 
        } 
    }

    withType(JavaCompile::class).configureEach {
        options.release.set(17)
        options.encoding = "UTF-8"
    }

    withType<KotlinCompile> { 
        kotlinOptions { 
            jvmTarget = "17" 
        } 
    }

    getByName<ProcessResources>("processResources") {
        filesMatching("fabric.mod.json") {
            expand(mapOf("version" to version, "mcversion" to minecraft_version))
        }
    }
}
