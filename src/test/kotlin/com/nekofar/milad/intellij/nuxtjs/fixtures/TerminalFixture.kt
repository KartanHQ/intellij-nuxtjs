package com.nekofar.milad.intellij.nuxtjs.fixtures

import com.intellij.remoterobot.RemoteRobot
import com.intellij.remoterobot.data.RemoteComponent
import com.intellij.remoterobot.fixtures.ComponentFixture
import com.intellij.remoterobot.stepsProcessing.step
import com.intellij.remoterobot.utils.Locators
import com.intellij.terminal.JBTerminalPanel

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
}