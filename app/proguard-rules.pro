# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

###########################################################

-optimizationpasses 5
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*
-keepattributes Exceptions, InnerClasses, *Annotation*, Signature

-obfuscationdictionary dictionary.txt
-packageobfuscationdictionary dictionary.txt
-classobfuscationdictionary dictionary.txt

-dontnote andoridx.**
-dontnote com.srt.decoder.**
-dontwarn com.google.**
-dontwarn okhttp3.**
-dontwarn okio.**

-ignorewarnings

-dontwarn okhttp3.internal.platform.*
-dontwarn com.oracle.svm.core.annotate.**

-keep class * {
    @com.oracle.svm.core.annotate.Delete *;
}
-keep class org.bouncycastle.jsse.** { *; }
-keep class org.bouncycastle.jsse.** { *; }
-keep class okhttp3.internal.platform.android.BouncyCastleSocketAdapter {
    *;
}

-keep class mazen.abdelgawad.news.data.remote.models.** { *; }