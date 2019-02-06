package io.webfolder.pdcurses4j;

import static java.lang.System.getProperty;
import static java.lang.System.load;
import static java.nio.file.Files.copy;
import static java.nio.file.Files.createTempFile;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static java.util.Locale.ENGLISH;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

class PDCWindow {

    long peer;

    private static final String OS = getProperty("os.name").toLowerCase(ENGLISH);

    private static final boolean WINDOWS = OS.startsWith("windows");

    static {
        ClassLoader cl = PDCWindow.class.getClassLoader();
        Path libFile;
        String library = WINDOWS ? "META-INF/pdcurses.dll" : "META-INF/libpdcurses4j.so";
        try (InputStream is = cl.getResourceAsStream(library)) {
            libFile = createTempFile("pdcurses", WINDOWS ? ".dll" : ".so");
            copy(is, libFile, REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        libFile.toFile().deleteOnExit();
        load(libFile.toAbsolutePath().toString());
    }

    native long pdcurses4j_initscr();

    native int pdcurses4j_start_color();

    native int pdcurses4j_init_pair(short pair, short fg, short bg);

    native int pdcurses4j_wattr_on(long peer, int attrs);

    native int pdcurses4j_wprintw(long peer, String str);

    native int pdcurses4j_wrefresh(long peer);

    native int pdcurses4j_endwin();

    native int pdcurses4j_waddch(long peer, char ch);

    native int pdcurses4j_wgetch(long peer);

    native int pdcurses4j_waddstr(long peer, String str);

    native int pdcurses4j_mvwaddstr(long peer, int y, int x, String str);

    native int pdcurses4j_noecho();

    native int pdcurses4j_nodelay(long peer, int bf);

    native int pdcurses4j_napms(int delay);

    native int pdcurses4j_mvwinsch(long peer, int y, int x, char ch);

    native int pdcurses4j_mvwdelch(long peer, int y, int x);

    native String pdcurses4j_wgetnstr(long peer, int n);

    native String pdcurses4j_unctrl(int c);

    native int pdcurses4j_getmaxx(long peer);

    native int pdcurses4j_getmaxy(long peer);

    native int pdcurses4j_wclear(long peer);

    native int pdcurses4j_typeahead(int fields);

    native int pdcurses4j_def_shell_mode();

    native long pdcurses4j_newwin(int nlines, int ncols, int begy, int begx);

    native int pdcurses4j_getpary(long peer);

    native int pdcurses4j_getparx(long peer);

    native int pdcurses4j_wbkgd(long peer, long ch);

    native long pdcurses4j_subwin(long peer, int nlines, int ncols, int begy, int begx);

    native int pdcurses4j_touchwin(long peer);

    native long pdcurses4j_derwin(long peer, int nlines, int ncols, int begy, int begx);

    native int pdcurses4j_scrollok(long peer, int bf);

    native int pdcurses4j_box(long peer, char verch, char horch);

    native int pdcurses4j_wmove(long peer, int y, int x);

    native int pdcurses4j_wattron(long peer, int attrs);

    native int pdcurses4j_wattroff(long peer, int attrs);

    native int pdcurses4j_wattrset(long peer, int attrs);

    native int pdcurses4j_can_change_color();

    native int pdcurses4j_init_color(short color, short red, short green, short blue);

    native int pdcurses4j_beep();

    native int pdcurses4j_flash();

    native int pdcurses4j_has_colors();

    native int pdcurses4j_colors();

    native int pdcurses4j_color_pairs();

    native int pdcurses4j_color_pair(int n);

    native int pdcurses4j_pair_number(int n);
}
