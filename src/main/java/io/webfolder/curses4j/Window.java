package io.webfolder.curses4j;

import static io.webfolder.curses4j.CursesWindow.curses4j_beep;
import static io.webfolder.curses4j.CursesWindow.curses4j_color_pair;
import static io.webfolder.curses4j.CursesWindow.curses4j_color_pairs;
import static io.webfolder.curses4j.CursesWindow.curses4j_colors;
import static io.webfolder.curses4j.CursesWindow.curses4j_flash;
import static io.webfolder.curses4j.CursesWindow.curses4j_has_colors;
import static io.webfolder.curses4j.CursesWindow.curses4j_pair_number;
import static io.webfolder.curses4j.CursesWindow.curses4j_pdc_acs;
import static java.lang.String.format;

public class Window {

    public static final short COLOR_BLACK = 0;
    public static final short COLOR_RED = 1;
    public static final short COLOR_GREEN = 2;
    public static final short COLOR_BLUE = 4;
    public static final short COLOR_WHITE = 7;
    public static final short COLOR_CYAN = (COLOR_BLUE | COLOR_GREEN);
    public static final short COLOR_MAGENTA = (COLOR_RED | COLOR_BLUE);
    public static final short COLOR_YELLOW = (COLOR_RED | COLOR_GREEN);

    public static final int PDC_COLOR_SHIFT = 24;
    public static final int A_COLOR = 0xff000000;

    public static final int FALSE = 0;
    public static final int TRUE = 1;

    public static final int ERR = -1;
    public static final int OK = 0;

    /*** Video attribute macros ***/

    public static final int A_NORMAL = 0;

    public static final int A_ALTCHARSET = 0x00010000;
    public static final int A_RIGHT = 0x00020000;
    public static final int A_LEFT = 0x00040000;
    public static final int A_ITALIC = 0x00080000;
    public static final int A_UNDERLINE = 0x00100000;
    public static final int A_REVERSE = 0x00200000;
    public static final int A_BLINK = 0x00400000;
    public static final int A_BOLD = 0x00800000;

    public static final int A_ATTRIBUTES = 0xffff0000;
    public static final int A_CHARTEXT = 0x0000ffff;

    public static final int A_LEFTLINE = A_LEFT;
    public static final int A_RIGHTLINE = A_RIGHT;
    public static final int A_STANDOUT = (A_REVERSE | A_BOLD); /* X/Open */

    public static final int A_DIM = A_NORMAL;
    public static final int A_INVIS = A_NORMAL;
    public static final int A_PROTECT = A_NORMAL;

    public static final int A_HORIZONTAL = A_NORMAL;
    public static final int A_LOW = A_NORMAL;
    public static final int A_TOP = A_NORMAL;
    public static final int A_VERTICAL = A_NORMAL;

    /* VT100-compatible symbols -- box chars */

    public static final int ACS_ULCORNER  = PDC_ACS('l');
    public static final int ACS_LLCORNER  = PDC_ACS('m');
    public static final int ACS_URCORNER  = PDC_ACS('k');
    public static final int ACS_LRCORNER  = PDC_ACS('j');
    public static final int ACS_RTEE      = PDC_ACS('u');
    public static final int ACS_LTEE      = PDC_ACS('t');
    public static final int ACS_BTEE      = PDC_ACS('v');
    public static final int ACS_TTEE      = PDC_ACS('w');
    public static final int ACS_HLINE     = PDC_ACS('q');
    public static final int ACS_VLINE     = PDC_ACS('x');
    public static final int ACS_PLUS      = PDC_ACS('n');

    /* VT100-compatible symbols -- other */
    public static final int ACS_S1        = PDC_ACS('o');
    public static final int ACS_S9        = PDC_ACS('s');
    public static final int ACS_DIAMOND   = PDC_ACS('`');
    public static final int ACS_CKBOARD   = PDC_ACS('a');
    public static final int ACS_DEGREE    = PDC_ACS('f');
    public static final int ACS_PLMINUS   = PDC_ACS('g');
    public static final int ACS_BULLET    = PDC_ACS('~');

    @Deprecated
    public static final int CHR_MSK = A_CHARTEXT; /* Obsolete */
    @Deprecated
    public static final int ATR_MSK = A_ATTRIBUTES; /* Obsolete */
    @Deprecated
    public static final int ATR_NRM = A_NORMAL; /* Obsolete */

    private final CursesWindow peer = new CursesWindow();

    public static final Window stdscr = new Window();

    public static int COLOR_PAIR(int n) {
        return curses4j_color_pair(n);
    }

    public static int PAIR_NUMBER(int n) {
        return curses4j_pair_number(n);
    }

    public static int PDC_ACS(int w) {
        return curses4j_pdc_acs(w);
    }

    /**
     * initscr() should be the first curses routine called. It will initialize all
     * curses data structures, and arrange that the first call to refresh() will
     * clear the screen.
     * <p>
     * In case of error, initscr() will write a message to standard error and end
     * the program.
     */
    public static void initscr() {
        if (stdscr.peer.peer == 0) {
            stdscr.peer.peer = stdscr.peer.curses4j_initscr();
        } else {
            throw new IllegalStateException();
        }
    }

    /**
     * initializes eight basic colors (black, red, green, yellow, blue, magenta,
     * cyan, and white), and two global variables: COLORS and COLOR_PAIRS
     * (respectively defining the maximum number of colors and color-pairs the
     * terminal is capable of displaying).
     */
    public static int start_color() {
        return stdscr.peer.curses4j_start_color();
    }

    /**
     * init_pair() changes the definition of a color-pair.
     * <p>
     * It takes three arguments: the number of the color-pair to be redefined, and
     * the new values of the foreground and background colors. The pair number must
     * be between 0 and COLOR_PAIRS - 1, inclusive. The foreground and background
     * must be between 0 and COLORS - 1, inclusive. If the color pair was previously
     * initialized, the screen is refreshed, and all occurrences of that color-pair
     * are changed to the new definition.
     */
    public static int init_pair(int pair, short fg, short bg) {
        if (pair > Short.MAX_VALUE) {
            throw new RuntimeException("pair must be smaller than: " + Short.MAX_VALUE);
        } else {
            return stdscr.peer.curses4j_init_pair((short) pair, fg, bg);
        }
    }

    public static int init_color(short color, short red, short green, short blue) {
        return stdscr.peer.curses4j_init_color(color, red, green, blue);
    }

    /**
     * Turn on attrs in the current or specified window without affecting any
     * others.
     */
    public int attr_on(int attrs) {
        return peer.curses4j_wattr_on(peer.peer, attrs);
    }

    /**
     * Prints a string.
     */
    public int printw(String str, Object... args) {
        return peer.curses4j_wprintw(peer.peer, format(str, args));
    }

    /**
     * wrefresh() copies the named window to the physical terminal screen, taking
     * into account what is already there in order to optimize cursor movement.
     * <p>
     * These routines must be called to get any output on the terminal, as other
     * routines only manipulate data structures. Unless leaveok() has been enabled,
     * the physical cursor of the terminal is left at the location of the window's
     * cursor.
     * <p>
     * wnoutrefresh() and doupdate() allow multiple updates with more efficiency
     * than wrefresh() alone. wrefresh() works by first calling wnoutrefresh(),
     * which copies the named window to the virtual screen. It then calls
     * doupdate(), which compares the virtual screen to the physical screen and does
     * the actual update. A series of calls to wrefresh() will result in alternating
     * calls to wnoutrefresh() and doupdate(), causing several bursts of output to
     * the screen. By first calling wnoutrefresh() for each window, it is then
     * possible to call doupdate() only once.
     */
    public int refresh() {
        return peer.curses4j_wrefresh(peer.peer);
    }

    public static boolean can_change_color() {
        return stdscr.peer.curses4j_can_change_color() == TRUE;
    }

    public int addch(int ch) {
        return peer.curses4j_waddch(peer.peer, ch);
    }

    public int addch(char ch) {
        return addch((int) ch);
    }

    public int getch() {
        return peer.curses4j_wgetch(peer.peer);
    }

    public static int endwin() {
        return stdscr.peer.curses4j_endwin();
    }

    public int addstr(String str) {
        return peer.curses4j_waddstr(peer.peer, str);
    }

    public int mvaddstr(int y, int x, String str) {
        return peer.curses4j_mvwaddstr(peer.peer, y, x, str);
    }

    public int noecho() {
        return stdscr.peer.curses4j_noecho();
    }

    public int nodelay(boolean bf) {
        return peer.curses4j_nodelay(peer.peer, bf ? TRUE : FALSE);
    }

    public static int napms(int delay) {
        return stdscr.peer.curses4j_napms(delay);
    }

    public int mvinsch(int y, int x, char ch) {
        return peer.curses4j_mvwinsch(peer.peer, y, x, ch);
    }

    public int mvdelch(int y, int x) {
        return peer.curses4j_mvwdelch(peer.peer, y, x);
    }

    public String getnstr(int n) {
        return peer.curses4j_wgetnstr(peer.peer, n);
    }

    public static String unctrl(int c) {
        return stdscr.peer.curses4j_unctrl(c);
    }

    public int getmaxx() {
        return stdscr.peer.curses4j_getmaxx(peer.peer);
    }

    public int getmaxy() {
        return stdscr.peer.curses4j_getmaxy(peer.peer);
    }

    public int clear() {
        return stdscr.peer.curses4j_wclear(peer.peer);
    }

    public static int typeahead(int fields) {
        return stdscr.peer.curses4j_typeahead(fields);
    }

    public static int def_shell_mode() {
        return stdscr.peer.curses4j_def_shell_mode();
    }

    public static Window newwin(int nlines, int ncols, int begy, int begx) {
        long newwin = stdscr.peer.curses4j_newwin(nlines, ncols, begy, begx);
        if (newwin <= ERR) {
            return null;
        }
        Window window = new Window();
        window.peer.peer = newwin;
        return window;
    }

    public int getpary() {
        return peer.curses4j_getpary(peer.peer);
    }

    public int getparx() {
        return peer.curses4j_getparx(peer.peer);
    }

    public int bkgd(int ch) {
        return peer.curses4j_wbkgd(peer.peer, ch);
    }

    public Window subwin(int nlines, int ncols, int begy, int begx) {
        long subwin = peer.curses4j_subwin(peer.peer, nlines, ncols, begy, begx);
        if (subwin <= ERR) {
            return null;
        }
        Window window = new Window();
        window.peer.peer = subwin;
        return window;
    }

    public int touchwin() {
        return peer.curses4j_touchwin(peer.peer);
    }

    public Window derwin(int nlines, int ncols, int begy, int begx) {
        long subwin = peer.curses4j_derwin(peer.peer, nlines, ncols, begy, begx);
        if (subwin <= ERR) {
            return null;
        }
        Window window = new Window();
        window.peer.peer = subwin;
        return window;
    }

    public int scrollok(boolean bf) {
        return peer.curses4j_scrollok(peer.peer, bf ? TRUE : FALSE);
    }

    public int box(char verch, char horch) {
        return peer.curses4j_box(peer.peer, verch, horch);
    }

    public int move(int y, int x) {
        return peer.curses4j_wmove(peer.peer, y, x);
    }

    public int attron(int attrs) {
        return peer.curses4j_wattron(peer.peer, attrs);
    }

    public int attroff(int attrs) {
        return peer.curses4j_wattroff(peer.peer, attrs);
    }

    public int attrset(int attrs) {
        return peer.curses4j_wattrset(peer.peer, attrs);
    }

    public static int beep() {
        return curses4j_beep();
    }

    public static int flash() {
        return curses4j_flash();
    }

    public static boolean has_colors() {
        return curses4j_has_colors() == TRUE;
    }

    public static int COLORS() {
        return curses4j_colors();
    }

    public static int COLOR_PAIRS() {
        return curses4j_color_pairs();
    }
}
