import sbt._

object AppDependencies {
  import play.core.PlayVersion

  val compile = Seq(
    "uk.gov.hmrc"       %% "bootstrap-play-26" % "1.4.0",
    "com.typesafe.play" %% "play-json"         % "2.6.14",
    "com.amazonaws"     % "aws-java-sdk-s3"    % "1.11.500",
    "com.typesafe.akka" %% "akka-stream"       % "2.5.6"
  )

  trait TestDependencies {
    lazy val scope: String       = "test"
    lazy val test: Seq[ModuleID] = ???
  }

  private def commonTestDependencies(scope: String) = Seq(
    "uk.gov.hmrc"            %% "hmrctest"                    % "3.3.0"             % scope,
    "uk.gov.hmrc"            %% "http-verbs-test"             % "1.3.0"             % scope,
    "com.typesafe.play"      %% "play-test"                   % PlayVersion.current % scope,
    "com.typesafe.play"      %% "play-ws"                     % PlayVersion.current % scope,
    "org.scalatest"          %% "scalatest"                   % "3.0.8"             % scope,
    "org.scalatestplus.play" %% "scalatestplus-play"          % "3.1.2"             % scope,
    "org.pegdown"            % "pegdown"                      % "1.6.0"             % scope,
    "org.mockito"            % "mockito-core"                 % "2.6.2"             % scope,
    "com.github.tomakehurst" % "wiremock"                     % "2.2.2"             % scope,
    "org.scalamock"          %% "scalamock-scalatest-support" % "3.5.0"             % scope,
    "io.findify"             %% "s3mock"                      % "0.2.4"             % scope,
    "commons-io"             % "commons-io"                   % "2.6"               % scope,
    "org.scalacheck"         %% "scalacheck"                  % "1.13.4"            % scope
  )

  object Test {
    def apply() =
      new TestDependencies {
        override lazy val test = commonTestDependencies(scope)
      }.test
  }

  object IntegrationTest {
    def apply() =
      new TestDependencies {

        override lazy val scope: String = "it"

        override lazy val test = commonTestDependencies(scope)
      }.test
  }

  def apply() = compile ++ Test() ++ IntegrationTest()
}