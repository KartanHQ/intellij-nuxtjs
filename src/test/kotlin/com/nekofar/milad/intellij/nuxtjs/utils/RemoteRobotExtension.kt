package com.nekofar.milad.intellij.nuxtjs.utils

import com.intellij.remoterobot.RemoteRobot
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.ParameterContext
import org.junit.jupiter.api.extension.ParameterResolver

class RemoteRobotExtension : ParameterResolver {
    private val url = System.getProperty("remote-robot-url") ?: "http://127.0.0.1:8082"
    private val remoteRobot = RemoteRobot(url)

    override fun supportsParameter(
        parameterContext: ParameterContext?, extensionContext: ExtensionContext?
    ) = parameterContext?.parameter?.type?.equals(RemoteRobot::class.java) ?: false

    override fun resolveParameter(
        parameterContext: ParameterContext?, extensionContext: ExtensionContext?
    ) = remoteRobot
}