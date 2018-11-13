import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.0"
    id("application")
}

group = "com.silverhetch"
version = "1.0.0"

application {
    mainClassName = "com.silverhetch.hecate.Main"
}

repositories {
    mavenCentral()
    maven(url = "http://172.104.79.181:81/repository/Elizabeth/")
}

dependencies {
    testCompile("junit:junit:4.12")
    compile(kotlin("stdlib-jdk8"))
    compile("com.silverhetch:clotho:1.0.+")
    compile("commons-cli:commons-cli:1.4")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}