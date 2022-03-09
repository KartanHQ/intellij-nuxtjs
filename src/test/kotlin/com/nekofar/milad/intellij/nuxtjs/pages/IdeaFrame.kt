package com.nekofar.milad.intellij.nuxtjs.pages

import com.intellij.remoterobot.RemoteRobot
import com.intellij.remoterobot.data.RemoteComponent
import com.intellij.remoterobot.fixtures.*
import com.intellij.remoterobot.search.locators.byXpath
import com.intellij.remoterobot.stepsProcessing.step
import com.intellij.remoterobot.utils.waitFor
import java.lang.Thread.currentThread
import java.lang.Thread.sleep
import java.time.Duration.ofSeconds


fun RemoteRobot.idea(function: IdeaFrame.() -> Unit) {
    find<IdeaFrame>(timeout = ofSeconds(10)).apply(function)
}

@FixtureName("Idea frame")
@DefaultXpath("IdeFrameImpl type", "//div[@class='IdeFrameImpl']")
class IdeaFrame(remoteRobot: RemoteRobot, remoteComponent: RemoteComponent) :
    CommonContainerFixture(remoteRobot, remoteComponent) {

    val inlineProgressPanel
        get() = find<ComponentFixture>(byXpath("//div[@class='InlineProgressPanel']"))

    val projectViewTree
        get() = find<ContainerFixture>(byXpath("ProjectViewTree", "//div[@class='ProjectViewTree']"))

    val projectName
        get() = step("Get project name") { return@step callJs<String>("component.getProject().getName()") }

    val menuBar
        get() = step("Menu...") { return@step remoteRobot.find(JMenuBarFixture::class.java, JMenuBarFixture.byType()) }

    fun waitForFinishBackgroundTasks() {
        waitFor(ofSeconds(300), ofSeconds(10), "The background tasks did not finish in 5 minutes.") {
            (0..4).forEach { _ ->
                val inlineProgressPanelContent = inlineProgressPanel.findAllText()
                if (inlineProgressPanelContent.isNotEmpty()) {
                    return@waitFor false
                }
                try {
                    sleep(1000)
                } catch (e: InterruptedException) {
                    currentThread().interrupt()
                }
            }
            return@waitFor true
        }
    }
}