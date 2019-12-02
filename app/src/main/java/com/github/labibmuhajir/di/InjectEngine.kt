package com.github.labibmuhajir.di

import android.content.Context

/**
 * Created by labibmuhajir@yahoo.com
 * 1 December 2019
 **/

@Target(AnnotationTarget.FIELD)
@Retention
annotation class Inject(val name: String = "")

object InjectEngine {
    lateinit var applicationContext: Context
    var specificContext: Context? = null

    val modules = mutableSetOf<ProvidedObject>()

    fun inject(obj: Any) {
        if (Context::class.java.isAssignableFrom(obj::class.java)) {
            specificContext = obj as Context
        }

        obj.javaClass.declaredFields.forEach { property ->
            property?.let { propertyTobeInject ->
                propertyTobeInject.getAnnotation(Inject::class.java)?.let { injection ->
                    val type = propertyTobeInject.type

                    if (type.isAssignableFrom(Context::class.java)) {
                        propertyTobeInject.set(
                            obj, specificContext ?: applicationContext
                        )
                    } else {
                        modules.find { providedObject ->
                            val singletonType = providedObject.instance.javaClass

                            type.isAssignableFrom(singletonType) && injection.name == providedObject.name
                        }?.let { providedObject ->
                            propertyTobeInject.set(obj, providedObject.instance)
                        }
                    }
                }
            }
        }
    }

    fun addModules(modules: List<Any>) {
        modules.forEach {
            this.modules.add(ProvidedObject(it))
        }
    }

    fun addModule(module: Any) {
        this.modules.add(ProvidedObject(module))
    }

    fun addSpecificModule(name: String, module: Any) {
        this.modules.add(ProvidedObject(module, name))
    }

    fun addSpecificModules(modules: List<Pair<String, Any>>) {
        modules.forEach {
            this.modules.add(ProvidedObject(it.second, it.first))
        }
    }

    inline fun <reified T : Any> getInjection(name: String? = ""): T {
        val type = T::class.java

        return if (type.isAssignableFrom(Context::class.java)) {
            (specificContext ?: applicationContext) as T
        } else {
            modules.first {
                type.isAssignableFrom(it.instance.javaClass) && name == it.name
            }.instance as T
        }
    }

}

data class ProvidedObject(val instance: Any, val name: String = "")