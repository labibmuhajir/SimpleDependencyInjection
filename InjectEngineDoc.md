# How to use InjectEngine
## Define your singleton
One ProvidedObject
```kotlin
val module = ProvidedObject(Repository())
```
List ProvidedObject
```kotlin
val modules = listOf(ProvidedObject(Repository()), ProvidedObject(OtherRepository()))
```

## Add module to InjectEngine
on your Android Custom Application add modules to InjectEngine don't forget to define application context
```kotlin
InjectEngine.applicationContext = applicationContext
InjectEngine.modules.add(module)
InjectEngine.modules.addAll(modules)
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
define specific ProvidedObject
```kotlin
val module = ProvidedObject(Repository(), "your specific name")
val modules = listOf(ProvidedObject(Repository(), "your specific name"), ProvidedObject(OtherRepository(), "your specific name"))
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
lateinit var repository: YourRepository
```
later in lifecycle onCreate or whatever you want use inject method
```kotlin
InjectEngine.inject(this)
```

## Known Bugs
- Can't define ProvidedObject with dependencies on one list module