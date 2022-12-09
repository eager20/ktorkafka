package com.eager20

import io.ktor.routing.*
import rawdata.router.rawDataRoute

fun Routing.apiRoute() {
    route("/api/v1") {
        rawDataRoute()

    }
}