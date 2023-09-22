#include <jni.h>
#include <string>

#define MY_NULL NULL
extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_pixabaygalleryapp_utils_Keys_apiKey(JNIEnv *env, jobject thiz) {
    std::string api_key = "";
    return env->NewStringUTF(api_key.c_str());
}