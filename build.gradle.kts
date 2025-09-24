plugins {
    id("hexxyinthealps.java")
    id("hexxyinthealps.mod-publish")
}

architectury {
    // this looks up the value from gradle/libs.versions.toml
    minecraft = libs.versions.minecraft.get()
}

tasks {
    register("runAllDatagen") {
        dependsOn(":Fabric:runDatagen")
        dependsOn(":Forge:runCommonDatagen")
        dependsOn(":Forge:runForgeDatagen")
    }
}

publishMods {
    displayName = "v${project.version}"

    github {
        repository = System.getenv("GITHUB_REPOSITORY") ?: ""
        commitish = System.getenv("GITHUB_SHA") ?: ""

        // https://modmuss50.github.io/mod-publish-plugin/platforms/github/#parent-releases
        allowEmptyFiles = true
    }
}
