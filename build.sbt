lazy val commonSettings = commonSmlBuildSettings ++ ossPublishSettings ++ Seq(
  organization := "com.softwaremill.debug",
  scalaVersion := "0.23.0-RC1"
)

lazy val rootProject = (project in file("."))
  .settings(commonSettings: _*)
  .settings(publishArtifact := false, name := "root")
  .aggregate(core)

lazy val core: Project = (project in file("core"))
  .settings(commonSettings: _*)
  .settings(
    name := "core",
    libraryDependencies += "org.scalameta" %% "munit" % "0.7.3" % Test,
    testFrameworks += new TestFramework("munit.Framework")
  )

