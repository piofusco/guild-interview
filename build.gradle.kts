import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.50"
    kotlin("plugin.spring") version "1.3.50"
    id("org.jetbrains.kotlin.plugin.jpa") version "1.3.50"
    id("io.spring.dependency-management") version "1.0.8.RELEASE"
    id("org.springframework.boot") version "2.2.1.RELEASE"
    id("org.flywaydb.flyway") version "5.2.4"
}

group = "io.piofusco"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    implementation("org.flywaydb:flyway-core")
    implementation("org.postgresql:postgresql")

    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.3.2")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.mockk:mockk:1.9.3")
}

flyway {
    url = "jdbc:postgresql://localhost:5432/guild?serverTimezone=UTC&createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true"
    user = "guild_user"
    password = "password"
}

tasks.withType<Test> {
    val flywayMigrate by tasks.getting(org.flywaydb.gradle.task.FlywayMigrateTask::class) {
        url = "jdbc:postgresql://localhost:5432/guild?serverTimezone=UTC&createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true"
		user = "guild_user"
		password = "password"
	}

    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}
