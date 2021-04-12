package com.github.labibmuhajir.di

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

/**
 * Created by labibmuhajir@yahoo.com
 * 1 December 2019
 **/

@Target(AnnotationTarget.FIELD)
@Retention
annotation class Inject(val name: String = "")

object InjectEngine {
    const val ACTIVITY = "activity"
    const val FRAGMENT = "fragment"

    lateinit var applicationContext: Context
    var specificContext: Context? = null
    lateinit var fragment: Fragment
    lateinit var activity: FragmentActivity

    val modules = mutableSetOf<ProvidedObject>()

    fun inject(obj: Any) {
        if (Context::class.java.isAssignableFrom(obj::class.java)) {
            specificContext = obj as Context
        }

        if (obj is FragmentActivity) {
            activity = obj
        }

        if (obj is Fragment) {
            fragment = obj
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
                            type.isAssignableFrom(providedObject.clazz) && injection.name == providedObject.name
                        }?.let { providedObject ->
                            propertyTobeInject.set(
                                obj,
                                if (providedObject.instance is Lazy<*>) providedObject.instance.value
                                else providedObject.instance
                            )
                        }
                    }
                }
            }
        }
    }

    fun resetInjection() {
        modules.clear()
    }

    inline fun <reified T : Any> getInjection(name: String? = ""): T {
        val type = T::class.java

        return when {
            type.isAssignableFrom(Context::class.java) -> {
                (specificContext ?: applicationContext) as T
            }
            type.isAssignableFrom(FragmentActivity::class.java) -> {
                activity as T
            }
            type.isAssignableFrom(Fragment::class.java) -> {
                fragment as T
            }
            else -> {
                val instance = modules.first {
                    (type.isAssignableFrom(it.clazz) && name == it.name)
                }.instance

                if (instance is Lazy<*>) instance.value as T
                else instance as T
            }
        }
    }
}

class ProvidedObject(val instance: Any, val name: String = "", clazz: Class<*>? = null) {
    val clazz = clazz ?: instance::class.java
}