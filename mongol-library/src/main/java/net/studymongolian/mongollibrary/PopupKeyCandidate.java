package net.studymongolian.mongollibrary;

/**
 * these are the choices for a popup key
 */
public class PopupKeyCandidate {

    private String unicode;

    private String display;

    /**
     * The composing String can be set if a unicode character would be temporarily
     * rendered incorrectly. For example, a mongolian medial would be rendered as
     * a final until another character is typed. Adding the composing span will let
     * it be displayed as a medial until the next char is typed.
     */
    private String composing;

    private boolean isRotated;

    /**
     * Convenience constructor for PopupCandidates(String unicode)
     *
     * @param unicode the unicode values for the popup items
     */
    public PopupKeyCandidate(char unicode) {
        this(String.valueOf(unicode));
    }

    /**
     * @param unicode the unicode value for a popup item
     */
    public PopupKeyCandidate(String unicode) {
        this(unicode, null, null, true);
    }

    /**
     * This constructor can be used for non-Mongolian keyboards so that the popup
     * characters are not rotated. The default for other constructors is rotated.
     *
     * @param unicode   the unicode value for a popup item
     * @param isRotated whether the popup display is rotated
     */
    public PopupKeyCandidate(String unicode, boolean isRotated) {
        this(unicode, null, null, isRotated);
    }

    /**
     * @param unicode the unicode value for a popup item
     * @param display the value to display if different than the unicode value
     */
    public PopupKeyCandidate(String unicode, String display) {
        this(unicode, display, null, true);
    }

    /**
     * @param unicode   the unicode value for a popup item
     * @param display   the value to display if different than the unicode value
     * @param composing the value to display for a temporary composing span
     */
    public PopupKeyCandidate(String unicode, String display, String composing) {
        this(unicode, display, composing, true);
    }

    /**
     * @param unicode   the unicode value for a popup item
     * @param display   the value to display if different than the unicode value
     * @param composing the value to display for a temporary composing span
     * @param isRotated whether the popup display is rotated
     */
    public PopupKeyCandidate(String unicode, String display, String composing, boolean isRotated) {
        this.unicode = unicode;
        this.display = display;
        this.composing = composing;
        this.isRotated = isRotated;
    }


    public String getUnicode() {
        return unicode;
    }

    public String getDisplay() {
        if (display == null)
            return unicode;
        return display;
    }

    public String getComposing() {
        return composing;
    }

    public boolean isRotated() {
        return isRotated;
    }
}