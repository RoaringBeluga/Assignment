plugins {
    id("java")
}

group = "net.nowherelands"
version = "1.0-SNAPSHOT"

// Package versions
val testNgVersion = "7.10.2"
val assertJVersion = "3.27.2"
val seleniumVersion = "4.29.0"
val webDriverManagerVersion = "5.9.3"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.testng:testng:$testNgVersion")
    testImplementation("org.assertj:assertj-core:$assertJVersion")
    implementation("org.seleniumhq.selenium:selenium-java:$seleniumVersion")
    implementation("io.github.bonigarcia:webdrivermanager:$webDriverManagerVersion")
}

//tasks.test {
//    useTestNG {
//        preserveOrder = true
//        includeGroups(systemProperties.getOrDefault("tag", "Full").toString())
//    }
//}

tasks.withType<Test>().configureEach() {
    systemProperties["runGroup"] = System.getProperty("runGroup", "Full")
    systemProperties["excludeGroup"] = System.getProperty("excludeGroup", "NONE")
}

// Invoke with: ./gradlew :clean :test -DrunGroup=<group to run> -DexcludeGroup=<group to exclude>
tasks.named<Test>("test") {
    useTestNG {
        val options = this as TestNGOptions
        options.excludeGroups(systemProperties.getOrDefault("excludeGroup", "NONE").toString())
        options.includeGroups(systemProperties.getOrDefault("runGroup", "Full").toString())
    }
}

