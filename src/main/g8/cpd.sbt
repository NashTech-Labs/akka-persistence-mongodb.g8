import de.johoop.cpd4sbt.ReportType.{Simple}
import de.johoop.cpd4sbt._

//------------------------------------
// Setup cpd for sbt
// Invocation: sbt cpd
// See: https://github.com/sbt/cpd4sbt
//------------------------------------

enablePlugins(CopyPasteDetector)

cpdReportType := Simple

cpdReportName := "cpd"

cpdTargetPath := target(_ / "cpd").value

cpdLanguage := Language.Scala