package com.nekofar.milad.intellij.nuxtjs.cli

import com.intellij.execution.filters.Filter
import com.intellij.javascript.CreateRunConfigurationUtil
import com.intellij.lang.javascript.boilerplate.NpmPackageProjectGenerator
import com.intellij.lang.javascript.boilerplate.NpxPackageDescriptor
import com.intellij.openapi.project.Project
import com.intellij.openapi.roots.ContentEntry
import com.intellij.openapi.vfs.VirtualFile
import com.nekofar.milad.intellij.nuxtjs.NuxtBundle
import com.nekofar.milad.intellij.nuxtjs.NuxtIcons

class NuxtCliProjectGenerator : NpmPackageProjectGenerator() {
    private val packageName = "nuxi"
    private val npxCommand = "nuxi"

    override fun getIcon() = NuxtIcons.ProjectGenerator

    override fun getName() = NuxtBundle.message("nuxt.project.generator.name")

    override fun getDescription() = NuxtBundle.message("nuxt.project.generator.description")

    override fun packageName() = packageName

    override fun presentablePackageName() = NuxtBundle.message("nuxt.project.generator.presentable.package.name")

    override fun getNpxCommands() = listOf(NpxPackageDescriptor.NpxCommand(packageName, npxCommand))

    override fun generatorArgs(project: Project?, dir: VirtualFile?, settings: Settings?) =
        project?.let { arrayOf("init", it.name) }

    override fun generateInTemp(): Boolean = true

    override fun filters(project: Project, baseDir: VirtualFile): Array<Filter> = emptyArray()

    override fun customizeModule(baseDir: VirtualFile, entry: ContentEntry?) { /* Do nothing */ }

    override fun onGettingSmartAfterProjectGeneration(project: Project, baseDir: VirtualFile) {
        super.onGettingSmartAfterProjectGeneration(project, baseDir)
        CreateRunConfigurationUtil.npmConfiguration(project, "build")
        CreateRunConfigurationUtil.npmConfiguration(project, "dev")
        CreateRunConfigurationUtil.npmConfiguration(project, "generate")
        CreateRunConfigurationUtil.npmConfiguration(project, "preview")
    }
}
