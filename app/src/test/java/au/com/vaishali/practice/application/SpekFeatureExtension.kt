package au.com.vaishali.practice.application

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import org.spekframework.spek2.dsl.Fixture
import org.spekframework.spek2.style.gherkin.FeatureBody

fun FeatureBody.beforeEachScenarioLiveDataStructure(featureBody: Fixture) {
    beforeEachScenario {
        with(ArchTaskExecutor.getInstance()) {
            setDelegate(object : TaskExecutor() {
                override fun executeOnDiskIO(runnable: Runnable) = runnable.run()

                override fun postToMainThread(runnable: Runnable) = runnable.run()

                override fun isMainThread(): Boolean = true

            })
        }
        featureBody()
    }
}

fun FeatureBody.afterEachScenarioLiveDataStructure(featureBody: Fixture) {
    beforeEachScenario {
        with(ArchTaskExecutor.getInstance()) {
            setDelegate(null)
        }
        featureBody()
    }
}