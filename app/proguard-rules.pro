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
# Aturan untuk Gson

# Keep Gson classes
-keep class com.google.gson.** { *; }
-keep class com.google.gson.internal.bind.DateTypeAdapter { *; } # Explicitly keep this if needed

# If you use @SerializedName, keep the fields
-keepattributes Signature
-keepattributes *Annotation*

# For specific classes that Gson needs to instantiate
# -keep class your.package.name.YourDataClass { *; }
# You might need to add specific keep rules for your data classes
# if they are being stripped and Gson can't instantiate them.