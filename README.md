# jenkins-script-wrapper
This shared Library attempts to workaround an issue in Jenkins where
it is not possible to set at stepname for a script.

This means that if you have a bunch of scripts running, it will be hard to
separate them.

This wrapper makes it possible to give a name to the step.
It also supports returnStdout: true and returnStatus: true

## Note
You probably want to fork this repo, instead of using this one directly.  

## Install Library
1. Go to Jenkins: Configuration
2. Go to section "Global Pipeline Libraries"
  1. Fill inn any name (in the example below i called it "utils")
  2. Version: "master"
  3. "modern scm"
  4. Source code managment: Github
    1. credentials: your credentials
    2. owner: ael-computas (or your fork)
    3. Repo: jenkins-script-wrapper (or your fork)
3. Create a pipeline job and paste in the script below.

## Example usage
```groovy
@Library('utils') _

node(){
    stage("Without wrapper") {
            sh script: 'echo the invisible script', returnStdout: true
            sh script: 'echo the invisible script2', returnStdout: true
            sh script: 'echo the invisible script3', returnStdout: true
    }
    stage("With wrapper") {
            wrapper.script script: 'echo the invisible script', returnStdout: true, stepName: "description #1"
            wrapper.script script: 'echo the invisible script2', returnStdout: true, stepName: "description #2"
            wrapper.script script: 'echo the invisible script3', returnStdout: true, stepName: "description #3"
    }
}
```
### Sample output with wrapper
![alt text](media/with.png "With the wrapper")

### Sample output without wrapper
![alt text](media/without.png "without the wrapper")
