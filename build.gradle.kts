import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.0"
}

group = "com.silverhetch"
version = "1.0.0"

repositories {
    mavenCentral()
    maven(url = "http://172.104.79.181:81/repository/Elizabeth/")
}

dependencies {
    compile(kotlin("stdlib-jdk8"))
    compile("com.silverhetch:clotho:1.0.+")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}