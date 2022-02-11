package com.nekofar.milad.intellij.nuxtjs.cli

import com.intellij.ide.util.projectWizard.WebTemplateNewProjectWizard
import com.intellij.ide.wizard.GeneratorNewProjectWizardBuilderAdapter

class NuxtCliProjectModuleBuilder :
    GeneratorNewProjectWizardBuilderAdapter(WebTemplateNewProjectWizard(NuxtCliProjectGenerator()))
