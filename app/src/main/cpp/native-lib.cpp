#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring JNICALL
Java_aia_com_wheely_1map_MainActivity_someMethodName(
        JNIEnv *env,
        jobject ) {
    std::string something = "RETURN SOMETHING";
    return env->NewStringUTF(something.c_str());
}
