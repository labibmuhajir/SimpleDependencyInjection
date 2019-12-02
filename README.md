# Simple Dependency Injection
this project is inspired by <a href="https://insert-koin.io">Koin</a> and for learning purposes GDK Jakarta 2019

## What is Dependency Injection
In software engineering, dependency injection is a technique whereby one object supplies the dependencies of another object. A "dependency" is an object that can be used, for example as a service. Instead of a client specifying which service it will use, something tells the client what service to use. The "injection" refers to the passing of a dependency (a service) into the object (a client) that would use it. The service is made part of the client's state. Passing the service to the client, rather than allowing a client to build or find the service, is the fundamental requirement of the pattern. 

In simple word Dependency Injection is passing dependency to other objects or framework with some techniques.

## Why Dependency Injection
* Reusability of code
* Ease of refactoring
* Ease of testing

## Basic Type Dependency Injection
### Constructor Injection
Dependencies supplied as parameters

```kotlin
class EngineX

data class Plane(private val engine: EngineX)

fun main() {
    val engine = EngineX()

    // Constructor Injection
    Plane(engine)
}
```

### Property Injection
Dependencies injected by accessing properties of object

```kotlin
class EngineX

class Plane {
    lateinit var enginex: EngineX
}

fun main() {
    val enginex = EngineX()
    val plane = Plane()

    //Property Injection
    plane.enginex = enginex
}
```

### Method Injection
Dependencies to be obtained from super class function that implemented by child of super class

```kotlin
interface SuperEngine

class EngineX: SuperEngine

class ShipEngine: SuperEngine

abstract class Vehicle {
    //Method Injection
    private val enginex: SuperEngine = getEngine()

    protected abstract fun getEngine(): SuperEngine
}

class Plane : Vehicle() {
    override fun getEngine(): SuperEngine {
        return EngineX()
    }
}

class Ship : Vehicle() {
    override fun getEngine(): SuperEngine {
        return ShipEngine()
    }
}
```

## Implemented Dependency Injection
* Constructor Injection
* Property Injection

## Branch Note
* release-0.1 The simplest

## Library
* Android
* GSON

### References
* <a href="https://developer.android.com/training/dependency-injection">Google Android Developer Training</a>
* <a href="https://en.wikipedia.org/wiki/Dependency_injection">Wikipedia</a>
* <a href="https://stackoverflow.com/questions/130794/what-is-dependency-injection">Stackoverflow</a>

#### How to use InjectEngine
##### Define your singleton
direcly define ProvidedObject
```kotlin
val module = ProvidedObject(Repository())
```
or define normally
```kotlin
val module = Repository()
```
##### Add module to InjectEngine
on your Android Custom Application add modules to InjectEngine don't forget to define application context

use this method if you direcly define module as ProvidedObject
```kotlin
InjectEngine.applicationContext = applicationContext
InjectEngine.modules.add(module)
```

if you don't
```kotlin
InjectEngine.applicationContext = applicationContext
InjectEngine.addModule(module)
```
##### Injection
###### Constructor Injection
```kotlin
Repository(getInjection())
```
###### Property Injection
in your class define property with @Inject annotation make sure is lateinit and not private
```kotlin
@Inject
lateinit var repository
```
later in lifecycle onCreate or whatever you want use inject method
```kotlin
InjectEngine.inject(this)
```

##### Specific Singleton
###### How to define
direcly define ProvidedObject
```kotlin
val module = ProvidedObject(Repository(), "your specific name")
```
or define normally
```kotlin
val module = Repository()
```
###### How to add module to InjectEngine module
if you define with ProvidedObject
```kotlin
InjectEngine.modules.add(module)
```

if you don't
```kotlin
InjectEngine.addSpecificModule("your specific name", module)
```

###### How to inject
####### Constructor Injection
```kotlin
Repository(getInjection("your specific name"))
```
####### Property Injection
in your class define property with annotation make sure is not private
```kotlin
@Inject("your specific name")
lateinit var repository
```
later in lifecycle onCreate or whatever you want use inject method
```kotlin
InjectEngine.inject(this)
```

#### Log
* add qualifier 3 December 2019
* created 1 December 2019
tobe update