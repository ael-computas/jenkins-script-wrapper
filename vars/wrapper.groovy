import org.jenkinsci.plugins.workflow.cps.CpsThread
import org.jenkinsci.plugins.workflow.actions.LabelAction

// A hack to create a named script output stage.  For some reason Jenkins
// does not want to name script steps, so this hack is used.
// ref: https://stackoverflow.com/questions/39414921/jenkins-pipeline-sh-display-name-label
def script(def Map vars) {
  def script = vars["script"]
  def stepName = vars["stepName"]
  def returnStatus = vars.get("returnStatus")
  def output
  try{
      if (returnStatus) {
        output = sh(script: script,returnStatus: true)
      } else {
        output = sh(script: script,returnStdout: true)
      }
  } finally {
      CpsThread.current().head.get().addAction(new LabelAction("Shell script ${stepName} "))
  }
  return output
}
