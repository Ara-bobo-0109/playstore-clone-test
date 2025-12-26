package com.example.application

object NavRoutes {
    const val Catalog = "catalog"
    const val Add = "add"
    const val Detail = "detail"

    fun detail(appId: Long): String = "$Detail/$appId"
    const val DetailPattern = "$Detail/{appId}"
}
