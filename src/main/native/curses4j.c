#include "stdlib.h"

#include <jni.h>
#include "curses.h"

static JavaVM *jvm;

#define PDC_RGB 1

#if !defined(PDC_ACS)
 #define PDC_ACS(w) ((chtype)w | A_ALTCHARSET)
#endif

jlong curses4j_initscr(JNIEnv *env, jobject that) {
  WINDOW* win = initscr();
  jlong peer = (jlong) win;
  return peer;
}

jint curses4j_start_color(JNIEnv *env, jclass that) {
  return (jint) start_color();
}

jint curses4j_init_pair(JNIEnv *env, jclass that, jshort pair, jshort fg, jshort bg) {
 return (jint) init_pair(pair, fg, bg);
}

jint curses4j_wattr_on(JNIEnv *env, jobject that, jlong peer, jint attrs) {
 WINDOW* win = *(WINDOW **) &peer;
 return (jint) wattr_on(win, attrs, NULL);
}

jint curses4j_wrefresh(JNIEnv *env, jobject that, jlong peer) {
 WINDOW* win = *(WINDOW **) &peer;
 return (jint) wrefresh(win);
}

jint curses4j_wprintw(JNIEnv *env, jobject that, jlong peer, jstring str) {
 const char *_str = str == NULL ? NULL : (char *) (*env)->GetStringUTFChars(env, str, NULL);
 WINDOW* win = *(WINDOW **) &peer;
 jint ret = (jint) wprintw(win, _str);
 if (_str) {
  (*env)->ReleaseStringUTFChars(env, str, _str);
 }
 return ret;
}

jstring curses4j_wgetnstr(JNIEnv *env, jobject that, jlong peer, jint n) {
 if (n <= 0) {
  return NULL;
 }
 WINDOW* win = *(WINDOW **) &peer;
 char *_str = malloc(sizeof(char) * (n + 1));
 if (wgetnstr(win, _str, n) > ERR) {
  jstring str = (*env)->NewStringUTF(env, _str);
  free(_str);
  return str;
 } else {
  free(_str);
  return NULL;
 }
}

jint curses4j_endwin(JNIEnv *env, jclass that) {
 return endwin();
}

jint curses4j_waddch(JNIEnv *env, jobject that, jlong peer, jint ch) {
 WINDOW* win = *(WINDOW **) &peer;
 return waddch(win, ch);
}

jint curses4j_wgetch(JNIEnv *env, jobject that, jlong peer) {
 WINDOW* win = *(WINDOW **) &peer;
 return wgetch(win);
}

jint curses4j_waddstr(JNIEnv *env, jobject that, jlong peer, jstring str) {
 const char *_str = str == NULL ? NULL : (char *) (*env)->GetStringUTFChars(env, str, NULL);
 WINDOW* win = *(WINDOW **) &peer;
 jint ret = (jint) waddstr(win, _str);
 if (_str) {
  (*env)->ReleaseStringUTFChars(env, str, _str);
 }
 return ret;
}

jint curses4j_mvwaddstr(JNIEnv *env, jobject that, jlong peer, jint y, jint x, jstring str) {
 const char *_str = str == NULL ? NULL : (char *) (*env)->GetStringUTFChars(env, str, NULL);
 WINDOW* win = *(WINDOW **) &peer;
 jint ret = (jint) mvwaddstr(win, y, x, _str);
 if (_str) {
  (*env)->ReleaseStringUTFChars(env, str, _str);
 }
 return ret;
}

jint curses4j_noecho(JNIEnv *env, jclass that) {
 return noecho();
}

jint curses4j_nodelay(JNIEnv *env, jobject that, jlong peer, int bf) {
 WINDOW* win = *(WINDOW **) &peer;
 return nodelay(win, bf);
}

jint curses4j_napms(JNIEnv *env, jclass that, jint delay) {
 return napms(delay);
}

jint curses4j_mvwinsch(JNIEnv *env, jobject that, jint peer, jint y, jint x, jchar ch) {
 WINDOW* win = *(WINDOW **) &peer;
 return mvwinsch(win, y, x, ch);
}

jint curses4j_mvwdelch(JNIEnv *env, jobject that, jint peer, jint y, jint x) {
 WINDOW* win = *(WINDOW **) &peer;
 return mvwdelch(win, y, x);
}

jstring curses4j_unctrl(JNIEnv *env, jclass that, jint c) {
 const char *_str = unctrl(c);
 jstring str = NULL;
 if (_str) {
   str = (*env)->NewStringUTF(env, _str);
   return str;
 } else {
   return NULL;
 }
}

jint curses4j_getmaxx(JNIEnv *env, jobject that, jint peer) {
 WINDOW* win = *(WINDOW **) &peer;
 return getmaxx(win);
}

jint curses4j_getmaxy(JNIEnv *env, jobject that, jint peer) {
 WINDOW* win = *(WINDOW **) &peer;
 return getmaxy(win);
}

jint curses4j_wclear(JNIEnv *env, jobject that, jint peer) {
 WINDOW* win = *(WINDOW **) &peer;
 return wclear(win);
}

jint curses4j_typeahead(JNIEnv *env, jclass that, jint fields) {
  return typeahead(fields);
}

jint curses4j_def_shell_mode(JNIEnv *env, jclass that) {
  return def_shell_mode();
}

jlong curses4j_newwin(JNIEnv *env, jclass that, jint nlines, jint ncols, jint begy, jint begx) {
 WINDOW* win = newwin(nlines, ncols, begy, begx);
 return (jlong) win;
}

jint curses4j_getpary(JNIEnv *env, jobject that, jlong peer) {
 WINDOW* win = *(WINDOW **) &peer;
 return getpary(win);
}

jint curses4j_getparx(JNIEnv *env, jobject that, jlong peer) {
 WINDOW* win = *(WINDOW **) &peer;
 return getparx(win);
}

jint curses4j_wbkgd(JNIEnv *env, jobject that, jlong peer, jlong ch) {
 WINDOW* win = *(WINDOW **) &peer;
 return wbkgd(win, ch);
}

jlong curses4j_subwin(JNIEnv *env, jobject that, jlong peer, jint nlines, jint ncols, jint begy, jint begx) {
 WINDOW* win = *(WINDOW **) &peer;
 WINDOW* sub = subwin(win, nlines, ncols, begy, begx);
 return (jlong) sub;
}

jint curses4j_touchwin(JNIEnv *env, jobject that, jlong peer) {
 WINDOW* win = *(WINDOW **) &peer;
 return touchwin(win);
}

jlong curses4j_derwin(JNIEnv *env, jobject that, jlong peer, jint nlines, jint ncols, jint begy, jint begx) {
 WINDOW* win = *(WINDOW **) &peer;
 WINDOW* sub = derwin(win, nlines, ncols, begy, begx);
 return (jlong) sub;
}

jint curses4j_scrollok(JNIEnv *env, jobject that, jlong peer, int bf) {
 WINDOW* win = *(WINDOW **) &peer;
 return (jint) scrollok(win, bf);
}

jint curses4j_box(JNIEnv *env, jobject that, jlong peer, jchar verch, jchar horch) {
 WINDOW* win = *(WINDOW **) &peer;
 return (jint) box(win, verch, horch);
}

jint curses4j_wmove(JNIEnv *env, jobject that, jlong peer, jint y, jint x) {
 WINDOW* win = *(WINDOW **) &peer;
 return (jint) wmove(win, y, x);
}

jint curses4j_wattron(JNIEnv *env, jobject that, jlong peer, jint attrs) {
 WINDOW* win = *(WINDOW **) &peer;
 return (jint) wattron(win, attrs);
}

jint curses4j_wattroff(JNIEnv *env, jobject that, jlong peer, jint attrs) {
 WINDOW* win = *(WINDOW **) &peer;
 return (jint) wattroff(win, attrs);
}

jint curses4j_wattrset(JNIEnv *env, jobject that, jlong peer, jint attrs) {
 WINDOW* win = *(WINDOW **) &peer;
 return (jint) wattrset(win, attrs);
}

jint curses4j_can_change_color(JNIEnv *env, jclass that) {
 return (jint) can_change_color();
}

jint curses4j_init_color(JNIEnv *env, jclass that, jshort color, jshort red, jshort green, jshort blue) {
 return init_color(color, red, green, blue);
}

jint curses4j_beep(JNIEnv *env, jclass that) {
 return beep();
}

jint curses4j_flash(JNIEnv *env, jclass that) {
 return flash();
}

jint curses4j_has_colors(JNIEnv *env, jclass that) {
 return (jint) has_colors();
}

jint curses4j_colors(JNIEnv *env, jclass that) {
 return (jint) COLORS;
}

jint curses4j_color_pairs(JNIEnv *env, jclass klass) {
 return (jint) COLOR_PAIRS;
}

jint curses4j_color_pair(JNIEnv *env, jclass klass, jint n) {
 return (jint) COLOR_PAIR(n);
}

jint curses4j_pair_number(JNIEnv *env, jclass klass, jint n) {
 return (jint) PAIR_NUMBER(n);
}

jint curses4j_pdc_acs(JNIEnv *env, jclass klass, jint w) {
 return PDC_ACS(w);
}

jint JNI_OnLoad(JavaVM* vm, void* reserved) {
    JNIEnv* env;
    if ((*vm)->GetEnv(vm, (void **) &env, JNI_VERSION_1_8) != JNI_OK) {
        return -1;
    }
    jvm = vm;
    jclass klass = (*env)->FindClass(env, "io/webfolder/curses4j/CursesWindow");
    JNINativeMethod methods[] = {
        { "curses4j_initscr", "()J", (void*) curses4j_initscr },
        { "curses4j_start_color", "()I", (void*) curses4j_start_color },
        { "curses4j_init_pair", "(SSS)I", (void*) curses4j_init_pair },
        { "curses4j_wattr_on", "(JI)I", (void*) curses4j_wattr_on },
        { "curses4j_wprintw", "(JLjava/lang/String;)I", (void*) curses4j_wprintw },
        { "curses4j_wrefresh", "(J)I", (void*) curses4j_wrefresh },
        { "curses4j_endwin", "()I", (void*) curses4j_endwin },
        { "curses4j_waddch", "(JI)I", (void*) curses4j_waddch },
        { "curses4j_wgetch", "(J)I", (void*) curses4j_wgetch },
        { "curses4j_waddstr", "(JLjava/lang/String;)I", (void*) curses4j_waddstr },
        { "curses4j_mvwaddstr", "(JIILjava/lang/String;)I", (void*) curses4j_mvwaddstr },
        { "curses4j_noecho", "()I", (void*) curses4j_noecho },
        { "curses4j_nodelay", "(JI)I", (void*) curses4j_nodelay },
        { "curses4j_napms", "(I)I", (void*) curses4j_napms },
        { "curses4j_mvwinsch", "(JIIC)I", (void*) curses4j_mvwinsch },
        { "curses4j_mvwdelch", "(JII)I", (void*) curses4j_mvwdelch },
        { "curses4j_wgetnstr", "(JI)Ljava/lang/String;", (void*) curses4j_wgetnstr },
        { "curses4j_unctrl", "(I)Ljava/lang/String;", (void*) curses4j_unctrl },
        { "curses4j_getmaxx", "(J)I", (void*) curses4j_getmaxx },
        { "curses4j_getmaxy", "(J)I", (void*) curses4j_getmaxy },
        { "curses4j_wclear", "(J)I", (void*) curses4j_wclear },
        { "curses4j_typeahead", "(I)I", (void*) curses4j_typeahead },
        { "curses4j_def_shell_mode", "()I", (void*) curses4j_def_shell_mode },
        { "curses4j_newwin", "(IIII)J", (void*) curses4j_newwin },
        { "curses4j_getpary", "(J)I", (void*) curses4j_getpary },
        { "curses4j_getparx", "(J)I", (void*) curses4j_getparx },
        { "curses4j_wbkgd", "(JJ)I", (void*) curses4j_wbkgd },
        { "curses4j_subwin", "(JIIII)J", (void*) curses4j_subwin },
        { "curses4j_touchwin", "(J)I", (void*) curses4j_touchwin },
        { "curses4j_derwin", "(JIIII)J", (void*) curses4j_derwin },
        { "curses4j_scrollok", "(JI)I", (void*) curses4j_scrollok },
        { "curses4j_box", "(JCC)I", (void*) curses4j_box },
        { "curses4j_wmove", "(JII)I", (void*) curses4j_wmove },
        { "curses4j_wattron", "(JI)I", (void*) curses4j_wattron },
        { "curses4j_wattroff", "(JI)I", (void*) curses4j_wattroff },
        { "curses4j_wattrset", "(JI)I", (void*) curses4j_wattrset },
        { "curses4j_can_change_color", "()I", (void*) curses4j_can_change_color },
        { "curses4j_init_color", "(SSSS)I", (void*) curses4j_init_color },
        { "curses4j_beep", "()I", (void*) curses4j_beep },
        { "curses4j_flash", "()I", (void*) curses4j_flash },
        { "curses4j_has_colors", "()I", (void*) curses4j_has_colors },
        { "curses4j_colors", "()I", (void*) curses4j_colors },
        { "curses4j_color_pairs", "()I", (void*) curses4j_color_pairs },
        { "curses4j_color_pair", "(I)I", (void*) curses4j_color_pair },
        { "curses4j_pair_number", "(I)I", (void*) curses4j_pair_number },
        { "curses4j_pdc_acs", "(I)I", (void*) curses4j_pdc_acs }
    };

    (*env)->RegisterNatives(env, klass, methods, sizeof(methods) / sizeof(methods[0]));
    return JNI_VERSION_1_8;
}