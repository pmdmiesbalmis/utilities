package com.github.pmdmiesbalmis.utilities.validacion

interface Validador<T> {
    fun valida(datos: T): Validacion
}

