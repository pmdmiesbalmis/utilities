package com.pmdmiesbalmis.utilities.validacion

interface Validador<T> {
    fun valida(datos: T): Validacion
}

