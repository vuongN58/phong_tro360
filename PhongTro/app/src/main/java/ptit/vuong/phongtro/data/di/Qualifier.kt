package ptit.vuong.phongtro.data.di

import javax.inject.Qualifier

/**
 * Preferences qualifier
 */
@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class SecuredPrefs

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class SharedPrefs

/**
 * Coroutine Dispatcher qualifier
 */
@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class DefaultDispatcher

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class IoDispatcher

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class MainDispatcher

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class MainImmediateDispatcher
