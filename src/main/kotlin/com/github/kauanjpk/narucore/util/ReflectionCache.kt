package com.github.kauanjpk.narucore.util

object ReflectionUtil {

    fun getField(instance: Any, name: String): Any? {

        val field = instance.javaClass.getDeclaredField(name)

        field.isAccessible = true

        return field.get(instance)

    }

    fun setField(instance: Any, name: String, value: Any) {

        val field = instance.javaClass.getDeclaredField(name)

        field.isAccessible = true

        field.set(instance, value)

    }

}