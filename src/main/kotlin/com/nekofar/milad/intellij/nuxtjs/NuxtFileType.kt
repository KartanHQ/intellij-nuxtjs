package com.nekofar.milad.intellij.nuxtjs

import com.intellij.lang.javascript.JavascriptLanguage
import com.intellij.openapi.fileTypes.LanguageFileType

object NuxtFileType : LanguageFileType(JavascriptLanguage.INSTANCE) {
    override fun getIcon() = NuxtIcons.FileType
    override fun getName() = NuxtBundle.message("nuxt.file.type.name")
    override fun getDescription() = NuxtBundle.message("nuxt.file.type.description")
    override fun getDefaultExtension() = "js"
}
