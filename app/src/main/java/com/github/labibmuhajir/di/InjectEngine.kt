package com.github.labibmuhajir.di

/**
 * Created by labibmuhajir@yahoo.com
 * 1 December 2019
 **/

@Target(AnnotationTarget.FIELD)
@Retention
annotation class Inject

object InjectEngine {
    val modules = mutableSetOf<Any>()

    fun inject(obj: Any) {
        obj.javaClass.declaredFields.forEach { property ->
            property?.let { propertyTobeInject ->
                if (propertyTobeInject.isAnnotationPresent(Inject::class.java)) {
                    modules.find {
                        val singletonType = it.javaClass
                        val type = propertyTobeInject.type

                        type.isAssignableFrom(singletonType)
                    }?.let { singleton ->
                        propertyTobeInject.set(obj, singleton)
                    }
                }
            }
        }
    }

    inline fun <reified T : Any> getInjection(): T =
        modules.first { T::class.java.isAssignableFrom(it.javaClass) } as T
}