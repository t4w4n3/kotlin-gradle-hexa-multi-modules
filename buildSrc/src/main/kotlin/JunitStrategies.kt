import org.gradle.api.tasks.testing.Test

fun Test.parallelJunitDynamic() {
  systemProperties["junit.jupiter.execution.parallel.enabled"] = true
  systemProperties["junit.jupiter.execution.parallel.mode.default"] = "concurrent"
  systemProperties["junit.jupiter.execution.parallel.config.strategy"] = "dynamic"
  systemProperties["junit.jupiter.execution.parallel.config.dynamic.factor"] = 1
}
