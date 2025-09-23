plugins {
    id("hexxyinthealps.java")
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
