package com.nekofar.milad.intellij.nuxtjs.cli

import com.intellij.ide.util.projectWizard.WizardContext
import com.intellij.lang.javascript.boilerplate.JavaScriptNewTemplatesFactoryBase

class NuxtProjectTemplateFactory : JavaScriptNewTemplatesFactoryBase() {
    override fun createTemplates(context: WizardContext?) = arrayOf(NuxtCliProjectGenerator())
}
