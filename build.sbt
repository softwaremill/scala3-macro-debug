lazy val commonSettings = commonSmlBuildSettings ++ Seq(
  organization := "com.softwaremill.debug",
  scalaVersion := "3.0.0-RC2"
)

lazy val rootProject = (project in file("."))
  .settings(commonSettings: _*)
  .settings(publishArtifact := false, name := "root")
  .aggregate(core)

lazy val core: Project = (project in file("core"))
  .settings(commonSettings: _*)
  .settings(name := "core")
