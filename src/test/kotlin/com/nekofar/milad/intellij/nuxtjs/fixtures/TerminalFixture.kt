package com.nekofar.milad.intellij.nuxtjs.fixtures

import com.intellij.remoterobot.RemoteRobot
import com.intellij.remoterobot.data.RemoteComponent
import com.intellij.remoterobot.fixtures.ComponentFixture
import com.intellij.remoterobot.fixtures.ContainerFixture
import com.intellij.remoterobot.fixtures.DefaultXpath
import com.intellij.remoterobot.fixtures.FixtureName
import com.intellij.remoterobot.stepsProcessing.step
import com.intellij.remoterobot.utils.Locators
import com.intellij.terminal.JBTerminalPanel
import java.time.Duration

fun ContainerFixture.terminal(
    timeout: Duration = Duration.ofSeconds(20),
    function: TerminalFixture.() -> Unit = {}
): TerminalFixture = step("Search for terminal") {
    find<TerminalFixture>(timeout).apply(function)
}

@Suppress("JSUnresolvedFunction", "unused")
@DefaultXpath(by = "JBTerminalPanel type", xpath = "//div[@class='JBTerminalPanel']")
@FixtureName("Terminal")
class TerminalFixture(
    remoteRobot: RemoteRobot,
    remoteComponent: RemoteComponent
) : ComponentFixture(remoteRobot, remoteComponent) {
    companion object {
        fun byType() = Locators.byType(JBTerminalPanel::class.java)
    }

    val windowTitle: String
        get() = step("Get window title") {
            callJs("""component.getWindowTitle() || """"", true)
        }

    val screenLines: String
        get() = step("Get screen lines") {
            callJs("""component.getTerminalTextBuffer().getScreenLines() || """"", true)
        }

    val screenLinesCount: Int
        get() = step("Get screen lines count") {
            callJs("""component.getTerminalTextBuffer().getScreenLinesCount() || 0""", true)
        }
}