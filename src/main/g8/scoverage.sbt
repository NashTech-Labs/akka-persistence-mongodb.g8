//------------------------------------
// Setup scoverage for sbt
// Invocation: sbt clean coverage test && sbt coverageReport
// See: https://github.com/scoverage/sbt-scoverage
//------------------------------------

coverageEnabled := true

coverageExcludedPackages := "com.knoldus.main"