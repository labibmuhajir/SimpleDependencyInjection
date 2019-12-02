# How to use InjectEngine
## Define your singleton
direcly define ProvidedObject
```kotlin
val module = ProvidedObject(Repository())
```
or define normally
```kotlin
val module = Repository()
```
## Add module to InjectEngine
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
## Injection
### Constructor Injection
```kotlin
Repository(getInjection())
```
### Property Injection
in your class define property with @Inject annotation make sure is lateinit and not private
```kotlin
@Inject
lateinit var repository
```
later in lifecycle onCreate or whatever you want use inject method
```kotlin
InjectEngine.inject(this)
```

## Specific Singleton
### How to define
direcly define ProvidedObject
```kotlin
val module = ProvidedObject(Repository(), "your specific name")
```
or define normally
```kotlin
val module = Repository()
```
### How to add module to InjectEngine module
if you define with ProvidedObject
```kotlin
InjectEngine.modules.add(module)
```

if you don't
```kotlin
InjectEngine.addSpecificModule("your specific name", module)
```

### How to inject
#### Constructor Injection
```kotlin
Repository(getInjection("your specific name"))
```
#### Property Injection
in your class define property with annotation make sure is not private
```kotlin
@Inject("your specific name")
lateinit var repository
```
later in lifecycle onCreate or whatever you want use inject method
```kotlin
InjectEngine.inject(this)
```