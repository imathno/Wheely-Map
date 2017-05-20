#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring JNICALL
Java_aia_com_wheely_1map_MainActivity_getAPIKey(
        JNIEnv *env,
        jobject /* this */) {
    std::string apiKey = "PLACE KEY HERE";
    return env->NewStringUTF(apiKey.c_str());
}
