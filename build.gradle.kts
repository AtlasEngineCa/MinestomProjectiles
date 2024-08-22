plugins {
    id("java")
    `maven-publish`
    signing
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21

    withSourcesJar()
    withJavadocJar()
}

repositories {
    mavenCentral()
}

publishing {
    publications.create<MavenPublication>("maven") {
        groupId = "ca.atlasengine"
        artifactId = "atlas-projectiles"
        version = "1.0.2"

        from(components["java"])
    }

    repositories {
        maven {
            name = "WorldSeed"
            url = uri("https://reposilite.worldseed.online/public")
            credentials(PasswordCredentials::class)
            authentication {
                create<BasicAuthentication>("basic")
            }
        }
    }
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")

    compileOnly("net.minestom:minestom-snapshots:a521c4e7cd")
    testImplementation("net.minestom:minestom-snapshots:a521c4e7cd")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
