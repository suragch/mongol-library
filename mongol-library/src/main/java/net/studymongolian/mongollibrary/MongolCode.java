package net.studymongolian.mongollibrary;


/*
 * Mongol Code
 * Version 1.0.0
 *
 * Updated for Unicode 9.0 standards
 * http://unicode.org/charts/PDF/U1800.pdf
 * Deviating from Unicode 9.0 for
 *    - MONGOLIAN LETTER A second form medial (mistake in Unicode 9.0)
 *    - MONGOLIAN LETTER GA feminine form final (matching DS01, needed to
  *     break context)
 *
 * The purpose of this class is to render Unicode text into glyphs
 * that can be displayed on all versions of Android. It solves the
 * problem of Mongolian script not being supported before Android 6.0,
 * and problems with Unicode rendering after Android 6.0.
 *
 * Current version needs to be used with Menksoft font glyphs located
 * in the PUA starting at \uE234. It is recommended that all external
 * text use Unicode. However, Menksoft code can also be converted back
 * into Unicode.
 */
public final class MongolCode {

    // this is a singleton class (should it just be a static class?)
    public final static MongolCode INSTANCE = new MongolCode();
    private static final int NORMAL_GLYPH = 0;
    private static final int NON_PRINTING_CHAR = -1;


    public enum Location {
        ISOLATE, INITIAL, MEDIAL, FINAL
    }

    public enum Gender {
        MASCULINE, FEMININE, NEUTER
    }

    private enum Shape {
        TOOTH,     // glyph slants to the left like a tooth (includes medial T/D, R, W, etc)
        STEM,      // glyph starts with a vertical stem (includes B, O/U, CH, etc)
        ROUND      // glyph top is round (includes feminine Q/G)
    }

    // Constructor
    private MongolCode() {}

    public String unicodeToMenksoft(String inputString) {
        return unicodeToMenksoft(inputString, null);
    }

    public String unicodeToMenksoft(String inputString, int[] glyphIndexes) {

        if (inputString == null) {
            return null;
        }

        if (inputString.length() == 0) {
            return "";
        }

        // the index array must be passed in with the correct size, or else ignore it.
        int length = inputString.length();
        //int currentGlyphIndex = 0;
        if (glyphIndexes != null && glyphIndexes.length != length) {
            glyphIndexes = null; // TODO reset the glyph indexes to the right length
        }

        StringBuilder outputString = new StringBuilder();
        StringBuilder mongolWord = new StringBuilder();


        // Loop through characters in string
        //for (char character : inputString.toCharArray()) {
        for (int i = 0; i < length; i++) {
            final char character = inputString.charAt(i);
            if (isMongolian(character)) {
                mongolWord.append(character);
            } else if (character == Uni.NNBS) {
                if (mongolWord.length() > 0) {
                    String renderedWord = convertWordToMenksoftCode(mongolWord.toString(), glyphIndexes, i);
                    //currentGlyphIndex += renderedWord.length();
                    outputString.append(renderedWord);
                    mongolWord.setLength(0);
                }
                mongolWord.append(Uni.NNBS);
            } else { // non-Mongol character
                if (mongolWord.length() > 0) {
                    String renderedWord = convertWordToMenksoftCode(mongolWord.toString(), glyphIndexes, i);
                    //currentGlyphIndex += renderedWord.length();
                    outputString.append(renderedWord);
                    mongolWord.setLength(0);
                }
                outputString.append(character);
            }
        }

        // Add any final substring
        if (mongolWord.length() > 0) {
            String renderedWord = convertWordToMenksoftCode(mongolWord.toString(), glyphIndexes, length - 1);
            outputString.append(renderedWord);
        }

        // calculate the glyph indexes
        if (glyphIndexes != null) {
            boolean indexingHasStarted = false;
            int glyphIndex = 0;
            for (int i = 0; i < length; i++) {
                if (glyphIndexes[i] == NORMAL_GLYPH) {
                    if (indexingHasStarted) {
                        glyphIndex++;
                    }
                    indexingHasStarted = true;
                }
                glyphIndexes[i] = glyphIndex;
            }
        }

        return outputString.toString();
    }

    public String menksoftToUnicode(String inputString) {
        final char space = ' ';
        StringBuilder outputString = new StringBuilder();

        if (inputString == null || inputString.length() == 0) {
            return "";
        }

        // Loop through characters in string
        int length = inputString.length();
        for (int i = 0; i < length; i++) {

            char currentChar = inputString.charAt(i);

            if (!isMenksoft(currentChar)) {
                if (currentChar == space && i < length - 1) {
                    switch (inputString.charAt(i + 1)) {
                        case Glyph.MEDI_A_FVS2:
                        case Glyph.FINA_I:
                        case Glyph.MEDI_I:
                        case Glyph.MEDI_I_SUFFIX:
                        case Glyph.ISOL_I_SUFFIX:
                        case Glyph.MEDI_O:
                        case Glyph.MEDI_O_BP:
                        case Glyph.FINA_O:
                        case Glyph.MEDI_U:
                        case Glyph.MEDI_U_BP:
                        case Glyph.FINA_U:
                        case Glyph.MEDI_OE:
                        case Glyph.MEDI_OE_BP:
                        case Glyph.FINA_OE:
                        case Glyph.MEDI_UE:
                        case Glyph.MEDI_UE_BP:
                        case Glyph.FINA_UE:
                        case Glyph.FINA_YA:
                        case Glyph.INIT_YA_FVS1:
                            outputString.append(Uni.NNBS);
                            break;
                        default:
                            outputString.append(space);
                    }
                } else {
                    outputString.append(currentChar);
                }
                continue;
            }

            // TODO check if glyph location type with actual location
            // If there is a mismatch then add ZWJ

            if (currentChar < Glyph.A_START) {                         // punctuation
                switch (currentChar) {
                    case Glyph.BIRGA:
                        outputString.append(Uni.MONGOLIAN_BIRGA);
                        break;
                    case Glyph.ELLIPSIS:
                        outputString.append(Uni.MONGOLIAN_ELLIPSIS);
                        break;
                    case Glyph.COMMA:
                        outputString.append(Uni.MONGOLIAN_COMMA);
                        break;
                    case Glyph.FULL_STOP:
                        outputString.append(Uni.MONGOLIAN_FULL_STOP);
                        break;
                    case Glyph.COLON:
                        outputString.append(Uni.MONGOLIAN_COLON);
                        break;
                    case Glyph.FOUR_DOTS:
                        outputString.append(Uni.MONGOLIAN_FOUR_DOTS);
                        break;
                    case Glyph.TODO_SOFT_HYPHEN:
                        outputString.append(Uni.MONGOLIAN_TODO_SOFT_HYPHEN);
                        break;
                    case Glyph.SIBE_SYLLABLE_BOUNDARY_MARKER:
                        outputString.append(Uni.MONGOLIAN_SIBE_SYLLABLE_BOUNDARY_MARKER);
                        break;
                    case Glyph.MANCH_COMMA:
                        outputString.append(Uni.MONGOLIAN_MANCH_COMMA);
                        break;
                    case Glyph.MANCHU_FULL_STOP:
                        outputString.append(Uni.MONGOLIAN_MANCHU_FULL_STOP);
                        break;
                    case Glyph.NIRUGU:
                        outputString.append(Uni.MONGOLIAN_NIRUGU);
                        break;
                    case Glyph.BIRGA_WITH_ORNAMENT:
                        outputString.append("\uD805\uDE60"); // U+11660
                        break;
                    case Glyph.ROTATED_BIRGA:
                        outputString.append("\uD805\uDE61"); // U+11661
                        break;
                    case Glyph.DOUBLE_BIRGA_WITH_ORNAMENT:
                        outputString.append("\uD805\uDE62"); // U+11662
                        break;
                    case Glyph.TRIPLE_BIRGA_WITH_ORNAMENT:
                        outputString.append("\uD805\uDE63"); // U+11663
                        break;
                    case Glyph.MIDDLE_DOT:
                        outputString.append('\u00B7');
                        break;
                    case Glyph.ZERO:
                        outputString.append(Uni.MONGOLIAN_DIGIT_ZERO);
                        break;
                    case Glyph.ONE:
                        outputString.append(Uni.MONGOLIAN_DIGIT_ONE);
                        break;
                    case Glyph.TWO:
                        outputString.append(Uni.MONGOLIAN_DIGIT_TWO);
                        break;
                    case Glyph.THREE:
                        outputString.append(Uni.MONGOLIAN_DIGIT_THREE);
                        break;
                    case Glyph.FOUR:
                        outputString.append(Uni.MONGOLIAN_DIGIT_FOUR);
                        break;
                    case Glyph.FIVE:
                        outputString.append(Uni.MONGOLIAN_DIGIT_FIVE);
                        break;
                    case Glyph.SIX:
                        outputString.append(Uni.MONGOLIAN_DIGIT_SIX);
                        break;
                    case Glyph.SEVEN:
                        outputString.append(Uni.MONGOLIAN_DIGIT_SEVEN);
                        break;
                    case Glyph.EIGHT:
                        outputString.append(Uni.MONGOLIAN_DIGIT_EIGHT);
                        break;
                    case Glyph.NINE:
                        outputString.append(Uni.MONGOLIAN_DIGIT_NINE);
                        break;
                    case Glyph.QUESTION_EXCLAMATION:
                        outputString.append('\u2048');
                        break;
                    case Glyph.EXCLAMATION_QUESTION:
                        outputString.append('\u2049');
                        break;
                    case Glyph.EXCLAMATION:
                        outputString.append('!');
                        break;
                    case Glyph.QUESTION:
                        outputString.append('?');
                        break;
                    case Glyph.SEMICOLON:
                        outputString.append(';');
                        break;
                    case Glyph.LEFT_PARENTHESIS:
                        outputString.append('\uFF08'); // full width
                        break;
                    case Glyph.RIGHT_PARENTHESIS:
                        outputString.append('\uFF09'); // full width
                        break;
                    case Glyph.LEFT_ANGLE_BRACKET:
                        outputString.append('\u3008');
                        break;
                    case Glyph.RIGHT_ANGLE_BRACKET:
                        outputString.append('\u3009');
                        break;
                    case Glyph.LEFT_BRACKET:
                        outputString.append('[');
                        break;
                    case Glyph.RIGHT_BRACKET:
                        outputString.append(']');
                        break;
                    case Glyph.LEFT_DOUBLE_ANGLE_BRACKET:
                        outputString.append('\u00AB');
                        break;
                    case Glyph.RIGHT_DOUBLE_ANGLE_BRACKET:
                        outputString.append('\u00BB');
                        break;
                    case Glyph.LEFT_WHITE_CORNER_BRACKET:
                        outputString.append('\u300E');
                        break;
                    case Glyph.RIGHT_WHITE_CORNER_BRACKET:
                        outputString.append('\u300F');
                        break;
                    case Glyph.FULLWIDTH_COMMA:
                        outputString.append('\uFF0C');
                        break;
                    case Glyph.X:
                        outputString.append('\u00D7'); // FIXME using the multiplication sign?
                        break;
                    case Glyph.REFERENCE_MARK:
                        outputString.append('\u203B');
                        break;
                    case Glyph.EM_DASH:
                        outputString.append('\u2014');
                        break;
                    case Glyph.TWO_EM_DASH:
                        outputString.append('\u2e3a');
                        break;
                    default:
                        outputString.append(currentChar);
                }
            } else if (currentChar < Glyph.E_START) {                  // A
                switch (currentChar) {
                    case Glyph.ISOL_A_FVS1:
                    case Glyph.MEDI_A_FVS1:
                        outputString.append(Uni.A);
                        outputString.append(Uni.FVS1);
                        break;
                    case Glyph.MEDI_A_FVS2:
                        outputString.append(Uni.A);
                        outputString.append(Uni.FVS2);
                        break;
                    case Glyph.FINA_A_MVS:
                        if (outputString.length() > 0 &&
                                outputString.charAt(outputString.length() - 1) != Uni.MVS) {
                            outputString.append(Uni.MVS);
                        }
                        outputString.append(Uni.A);
                        break;
                    default:
                        outputString.append(Uni.A);
                }
            } else if (currentChar < Glyph.I_START) {                  // E
                switch (currentChar) {
                    case Glyph.INIT_E_FVS1:
                        outputString.append(Uni.E);
                        outputString.append(Uni.FVS1);
                        break;
                    case Glyph.FINA_E_MVS:
                        if (outputString.length() > 0 &&
                                outputString.charAt(outputString.length() - 1) != Uni.MVS) {
                            outputString.append(Uni.MVS);
                        }
                        outputString.append(Uni.E);
                        break;
                    default:
                        outputString.append(Uni.E);
                }
            } else if (currentChar < Glyph.O_START) {                  // I

                switch (currentChar) {
                    case Glyph.MEDI_I_FVS1:
                        outputString.append(Uni.I);
                        outputString.append(Uni.FVS1);
                        break;
                    case Glyph.MEDI_I_DOUBLE_TOOTH:
                        outputString.append(Uni.YA);
                        outputString.append(Uni.I);
                        break;
                    default:
                        outputString.append(Uni.I);
                }
            } else if (currentChar < Glyph.U_START) {                  // O
                outputString.append(Uni.O);
                switch (currentChar) {
                    case Glyph.MEDI_O_FVS1:
                    case Glyph.FINA_O_FVS1:
                        outputString.append(Uni.FVS1);
                        break;
                }
            } else if (currentChar < Glyph.OE_START) {                 // U
                outputString.append(Uni.U);
                if (currentChar == Glyph.MEDI_U_FVS1) {
                    outputString.append(Uni.FVS1);
                }
            } else if (currentChar < Glyph.UE_START) {                 // OE
                outputString.append(Uni.OE);
                switch (currentChar) {
                    case Glyph.FINA_OE_FVS1:
                        outputString.append(Uni.FVS1);
                        break;
                    case Glyph.MEDI_OE_FVS2:
                        outputString.append(Uni.FVS2);
                        break;
                }
            } else if (currentChar < Glyph.EE_START) {                 // UE
                outputString.append(Uni.UE);
                switch (currentChar) {
                    case Glyph.ISOL_UE_FVS1:
                    case Glyph.FINA_UE_FVS1:
                        outputString.append(Uni.FVS1);
                        break;
                    case Glyph.MEDI_UE_FVS2:
                        outputString.append(Uni.FVS2);
                        break;
                }
            } else if (currentChar < Glyph.NA_START) {                 // EE
                outputString.append(Uni.EE);
            } else if (currentChar < Glyph.BA_START) {                 // NA and ANG
                // handling these together because NA glyphs
                // are split in the Menksoft code.
                if (currentChar >= Glyph.ANG_START &&
                        currentChar <= Glyph.ANG_END) {
                    outputString.append(Uni.ANG);
                } else {
                    outputString.append(Uni.NA);
                    switch (currentChar) {
                        case Glyph.MEDI_NA_FVS2:
                            outputString.append(Uni.MVS);
                            break;
                        case Glyph.INIT_NA_FVS1_TOOTH:
                        case Glyph.INIT_NA_FVS1_STEM:
                            outputString.append(Uni.FVS1);
                            break;
                    }
                }
            } else if (currentChar < Glyph.PA_START) {                 // BA
                outputString.append(Uni.BA);
                if (currentChar == Glyph.FINA_BA_FVS1) {
                    outputString.append(Uni.FVS1);
                }
            } else if (currentChar < Glyph.QA_START) {                 // PA
                outputString.append(Uni.PA);
            } else if (currentChar < Glyph.GA_START) {                 // QA
                switch (currentChar) {
                    // treat the dotted masculine Q as a G
                    // ignoring all ancient dotted feminine forms
                    case Glyph.INIT_QA_FVS1_TOOTH:
                    case Glyph.INIT_QA_FVS1_STEM:
                    case Glyph.MEDI_QA_FVS1:
                    case Glyph.MEDI_QA_FVS2:
                        outputString.append(Uni.GA);
                        break;
                    // If a medial Q is being used like a G before
                    // a consonant, then interpret it as a G.
                    case Glyph.MEDI_QA_TOOTH:
                    case Glyph.MEDI_QA_STEM:
                    case Glyph.MEDI_QA_FEM_CONSONANT:
                        if (i < length - 1 && isMenksoftConsonant(inputString.charAt(i + 1))) {
                            outputString.append(Uni.GA);
                        } else {
                            outputString.append(Uni.QA);
                        }
                        break;
                    default:
                        outputString.append(Uni.QA);
                        break;
                }
            } else if (currentChar < Glyph.MA_START) {                 // GA
                switch (currentChar) {
                    // treat the undotted masculine G as a Q
                    case Glyph.INIT_GA_FVS1_TOOTH:
                    case Glyph.INIT_GA_FVS1_STEM:
                        outputString.append(Uni.QA);
                        break;
                    case Glyph.MEDI_GA_FVS2:
                        outputString.append(Uni.GA);
                        outputString.append(Uni.MVS);
                        break;
                    default:
                        outputString.append(Uni.GA);
                        break;
                }
            } else if (currentChar < Glyph.LA_START) {                 // MA
                outputString.append(Uni.MA);
            } else if (currentChar < Glyph.SA_START) {                 // LA
                outputString.append(Uni.LA);
            } else if (currentChar < Glyph.SHA_START) {                // SA
                outputString.append(Uni.SA);
                switch (currentChar) {
                    case Glyph.FINA_SA_FVS1:
                        outputString.append(Uni.FVS1);
                        break;
                    case Glyph.FINA_SA_FVS2:
                        outputString.append(Uni.FVS2);
                        break;
                }
            } else if (currentChar < Glyph.TA_START) {                 // SHA
                outputString.append(Uni.SHA);
            } else if (currentChar < Glyph.DA_START) {                 // TA
                outputString.append(Uni.TA);
                switch (currentChar) {
                    case Glyph.MEDI_TA_FVS1_STEM:
                    case Glyph.MEDI_TA_FVS1_TOOTH:
                        outputString.append(Uni.FVS1);
                        break;
                }
            } else if (currentChar < Glyph.CHA_START) {                // DA
                outputString.append(Uni.DA);
                switch (currentChar) {
                    case Glyph.INIT_DA_FVS1:
                    case Glyph.FINA_DA_FVS1:
                        outputString.append(Uni.FVS1);
                        break;
                }
            } else if (currentChar < Glyph.JA_START) {                 // CHA
                outputString.append(Uni.CHA);
            } else if (currentChar < Glyph.YA_START) {                 // JA
                outputString.append(Uni.JA);
            } else if (currentChar < Glyph.RA_START) {                 // YA
                outputString.append(Uni.YA);
                // TODO add FVS1 for dipthongs?
                if (currentChar == Glyph.INIT_YA_FVS1) {
                        outputString.append(Uni.FVS1);
                }
            } else if (currentChar < Glyph.WA_START) {                 // RA
                outputString.append(Uni.RA);
            } else if (currentChar < Glyph.FA_START) {                 // WA
                outputString.append(Uni.WA);
            } else if (currentChar < Glyph.KA_START) {                 // FA
                outputString.append(Uni.FA);
            } else if (currentChar < Glyph.KHA_START) {                // KA
                outputString.append(Uni.KA);
            } else if (currentChar < Glyph.TSA_START) {                // KHA
                outputString.append(Uni.KHA);
            } else if (currentChar < Glyph.ZA_START) {                 // TSA
                outputString.append(Uni.TSA);
            } else if (currentChar < Glyph.HAA_START) {                // ZA
                outputString.append(Uni.ZA);
            } else if (currentChar < Glyph.ZRA_START) {                // HAA
                outputString.append(Uni.HAA);
            } else if (currentChar < Glyph.LHA_START) {                // ZRA
                outputString.append(Uni.ZRA);
            } else if (currentChar < Glyph.ZHI_START) {                // LHA
                outputString.append(Uni.LHA);
            } else if (currentChar < Glyph.CHI_START) {                // ZHI
                outputString.append(Uni.ZHI);
            } else if (currentChar <= Glyph.MENKSOFT_END) {            // CHI
                outputString.append(Uni.CHI);
            }


        }

        return outputString.toString();
    }

    private boolean isMenksoftConsonant(char character) {
        return character >= Glyph.NA_START && character <= Glyph.FINA_CHI;
    }

    private boolean isMenksoft(char character) {
        return character >= Glyph.MENKSOFT_START && character <= Glyph.MENKSOFT_END;
    }

    private String convertWordToMenksoftCode(String mongolWord, int[] glyphIndexes, int lastUnicodeIndex) {

        // TODO would anything bad happen if lastUnicodeIndex were less than zero?

        boolean glyphIndexNeedsAdjusting = false;

        int length = mongolWord.length();
        StringBuilder renderedWord = new StringBuilder();

        Gender gender = Gender.NEUTER;
        char fvs = 0;
        boolean isSuffix = (mongolWord.charAt(0) == Uni.NNBS);

        // get initial location
        Location location;
        char charBelow = 0;
        char charBelowFvs = 0;
        Shape glyphShapeBelow = Shape.STEM;


        // start at the bottom of the word and work up
        for (int i = length - 1; i >= 0; i--) {

            char currentChar = mongolWord.charAt(i);

            if (isFVS(currentChar)) {
                fvs = currentChar;
                continue;
            } else if (currentChar == Uni.MVS && i != length - 2) {
                // ignore MVS in all but second to last position
                continue;
            }

            // get the location
            if (i == 0) {
                if (length == 1 || (length == 2 && fvs > 0)) {
                    location = Location.ISOLATE;
                } else {
                    location = Location.INITIAL;
                }
            } else if (i == length - 1 || (i == length - 2 && fvs > 0)) {
                if (i == 1 && isSuffix) {
                    location = Location.ISOLATE;
                } else {
                    location = Location.FINAL;
                }
            } else {
                if (i == 1 && isSuffix) {
                    location = Location.INITIAL;
                } else if (charBelow == Uni.MVS){
                    // treat character above MVS as a final by default
                    location = Location.FINAL;
                } else {
                    location = Location.MEDIAL;
                }
            }

            // handle each letter separately
            switch (currentChar) {

                //////////////////////////////  A  ///////////////////////////////

                case Uni.A:
                    gender = Gender.MASCULINE;
                    switch (location) {
                        case ISOLATE:
                            if (fvs == Uni.FVS1) {
                                renderedWord.insert(0, Glyph.ISOL_A_FVS1);     // left sweeping tail
                            } else {
                                renderedWord.insert(0, Glyph.ISOL_A);          // normal
                            }
                            break;
                        case INITIAL:
                            if (isSuffix) {
                                renderedWord.insert(0, Glyph.MEDI_A_FVS2);     // A of ACHA   *** suffix rule ***
                            } else {
                                renderedWord.insert(0, Glyph.INIT_A);          // normal
                            }
                            break;
                        case MEDIAL:
                            if (fvs == Uni.FVS1) {
                                renderedWord.insert(0, Glyph.MEDI_A_FVS1);     // 2 teeth
                            } else if (fvs == Uni.FVS2) {
                                renderedWord.insert(0, Glyph.MEDI_A_FVS2);     // A of ACHA suffix
                            } else {
                                if (isRoundLetter(mongolWord.charAt(i - 1))) {
                                    renderedWord.insert(0, Glyph.MEDI_A_BP);   // After BPFK
                                } else {
                                    renderedWord.insert(0, Glyph.MEDI_A);      // normal
                                }
                            }
                            glyphShapeBelow = Shape.TOOTH;
                            break;
                        case FINAL:

                            if (fvs == Uni.FVS1) {
                                renderedWord.insert(0, Glyph.FINA_A_FVS1);     // left sweeping tail
                                glyphShapeBelow = Shape.STEM;
                            } else {
                                if (isRoundLetter(mongolWord.charAt(i - 1))) {
                                    renderedWord.insert(0, Glyph.FINA_A_BP);   // after BPFK
                                    glyphShapeBelow = Shape.TOOTH;
                                } else if (mongolWord.charAt(i - 1) == Uni.MVS) {
                                    renderedWord.insert(0, Glyph.FINA_A_MVS);  // MVS
                                    glyphShapeBelow = Shape.STEM;
                                } else {
                                    renderedWord.insert(0, Glyph.FINA_A);      // normal
                                    glyphShapeBelow = Shape.STEM;
                                }
                            }
                            break;
                    }
                    break;

                //////////////////////////////  E  ///////////////////////////////

                case Uni.E:
                    gender = Gender.FEMININE;
                    switch (location) {
                        case ISOLATE:
                            renderedWord.insert(0, Glyph.ISOL_E);              // normal
                            break;
                        case INITIAL:
                            if (fvs == Uni.FVS1) {
                                renderedWord.insert(0, Glyph.INIT_E_FVS1);     // double tooth
                            } else {
                                renderedWord.insert(0, Glyph.INIT_E);          // normal
                            }
                            break;
                        case MEDIAL:
                            if (isRoundLetterIncludingQG(mongolWord.charAt(i - 1))) {
                                renderedWord.insert(0, Glyph.MEDI_E_BP);       // After BPFK
                            } else {
                                renderedWord.insert(0, Glyph.MEDI_E);          // normal
                            }
                            glyphShapeBelow = Shape.TOOTH;
                            break;
                        case FINAL:

                            if (fvs == Uni.FVS1) {
                                renderedWord.insert(0, Glyph.FINA_E_FVS1);     // left sweeping tail
                                glyphShapeBelow = Shape.STEM;
                            } else {
                                if (isRoundLetterIncludingQG(mongolWord.charAt(i - 1))) {
                                    renderedWord.insert(0, Glyph.FINA_E_BP);   // after BPFK
                                    glyphShapeBelow = Shape.TOOTH;
                                } else if (mongolWord.charAt(i - 1) == Uni.MVS) {
                                    renderedWord.insert(0, Glyph.FINA_E_MVS);  // MVS
                                    glyphShapeBelow = Shape.STEM;
                                } else {
                                    renderedWord.insert(0, Glyph.FINA_E);      // normal
                                    glyphShapeBelow = Shape.STEM;
                                }
                            }
                            break;
                    }
                    break;

                //////////////////////////////  I  ///////////////////////////////

                case Uni.I:
                    switch (location) {
                        case ISOLATE:
                            if (isSuffix) {
                                renderedWord.insert(0, Glyph.ISOL_I_SUFFIX);           // I  *** suffix rule ***
                            } else {
                                renderedWord.insert(0, Glyph.ISOL_I);                  // normal
                            }
                            break;
                        case INITIAL:
                            if (isSuffix && charBelow == Uni.YA) {
                                renderedWord.insert(0, Glyph.MEDI_I_SUFFIX);           // I of IYEN   *** suffix rule ***
                            } else {
                                renderedWord.insert(0, Glyph.INIT_I);                  // normal
                            }
                            break;
                        case MEDIAL:
                            if (fvs == Uni.FVS1) {
                                renderedWord.insert(0, Glyph.MEDI_I_FVS1);             // 2 long teeth
                            } else {
                                char charAbove = mongolWord.charAt(i - 1);
                                if (isRoundLetterIncludingQG(charAbove)) {
                                    renderedWord.insert(0, Glyph.MEDI_I_BP);           // After BPFK
                                // Disabling diphthongs for now because of NAIMA
                                    // problem. Make diphthongs with Y+FVS1+I.
                                } else if (
                                        charAbove == Uni.A ||
                                        charAbove == Uni.E ||
                                        charAbove == Uni.O ||
                                        charAbove == Uni.U ||
                                                // or non long toothed OE/UE
                                                ((charAbove == Uni.OE ||
                                                        charAbove == Uni.UE) &&
                                                !needsLongToothU(mongolWord, i - 1)) ) {
                                    // *** AI, EI, OI, UI, OEI, UEI
                                    // medial double tooth I diphthong rule ***
                                    renderedWord.insert(0, Glyph.MEDI_I_DOUBLE_TOOTH); // double tooth
                                } else {
                                    renderedWord.insert(0, Glyph.MEDI_I);              // normal
                                }
                            }
                            break;
                        case FINAL:
                            if (isRoundLetterIncludingQG(mongolWord.charAt(i - 1))) {
                                renderedWord.insert(0, Glyph.FINA_I_BP);               // after BPFK
                            } else {
                                renderedWord.insert(0, Glyph.FINA_I);                  // normal
                            }
                            break;
                    }
                    glyphShapeBelow = Shape.TOOTH;
                    break;

                //////////////////////////////  O  ///////////////////////////////

                case Uni.O:
                    gender = Gender.MASCULINE;
                    switch (location) {
                        case ISOLATE:
                            if (isSuffix) {
                                renderedWord.insert(0, Glyph.FINA_O);                  // O suffix   *** suffix rule ***
                            } else {
                                renderedWord.insert(0, Glyph.ISOL_O);                  // normal
                            }
                            break;
                        case INITIAL:
                            if (isSuffix) {
                                renderedWord.insert(0, Glyph.MEDI_O_BP);               // O of OO suffix   *** suffix rule ***
                            } else {
                                renderedWord.insert(0, Glyph.INIT_O);                  // normal
                            }
                            break;
                        case MEDIAL:
                            if (fvs == Uni.FVS1) {
                                renderedWord.insert(0, Glyph.MEDI_O_FVS1);             // tooth + O
                            } else {
                                if (isRoundLetter(mongolWord.charAt(i - 1))) {
                                    renderedWord.insert(0, Glyph.MEDI_O_BP);           // After BPFK
                                } else {
                                    renderedWord.insert(0, Glyph.MEDI_O);              // normal
                                }
                            }
                            break;
                        case FINAL:
                            if (fvs == Uni.FVS1) {
                                renderedWord.insert(0, Glyph.FINA_O_FVS1);             // round o
                            } else {
                                if (isRoundLetter(mongolWord.charAt(i - 1))) {
                                    renderedWord.insert(0, Glyph.FINA_O_BP);           // After BPFK
                                } else {
                                    renderedWord.insert(0, Glyph.FINA_O);              // normal
                                }
                            }
                            break;
                    }
                    glyphShapeBelow = Shape.STEM;
                    break;

                //////////////////////////////  U  ///////////////////////////////

                case Uni.U:
                    gender = Gender.MASCULINE;
                    switch (location) {
                        case ISOLATE:
                            if (isSuffix) {
                                renderedWord.insert(0, Glyph.FINA_U);                  // O suffix   *** suffix rule ***
                            } else {
                                renderedWord.insert(0, Glyph.ISOL_U);                  // normal
                            }
                            break;
                        case INITIAL:
                            if (isSuffix) {
                                renderedWord.insert(0, Glyph.MEDI_U_BP);               // U of UU suffix   *** suffix rule ***
                            } else {
                                renderedWord.insert(0, Glyph.INIT_U);                  // normal
                            }
                            break;
                        case MEDIAL:
                            if (fvs == Uni.FVS1) {
                                renderedWord.insert(0, Glyph.MEDI_U_FVS1);             // tooth + O
                            } else {
                                if (isRoundLetter(mongolWord.charAt(i - 1))) {
                                    renderedWord.insert(0, Glyph.MEDI_U_BP);           // After BPFK
                                } else {
                                    renderedWord.insert(0, Glyph.MEDI_U);              // normal
                                }
                            }
                            break;
                        case FINAL:
                            if (fvs == Uni.FVS1) {
                                renderedWord.insert(0, Glyph.FINA_U_FVS1);             // round o
                            } else {
                                if (isRoundLetter(mongolWord.charAt(i - 1))) {
                                    renderedWord.insert(0, Glyph.FINA_U_BP);           // After BPFK
                                } else {
                                    renderedWord.insert(0, Glyph.FINA_U);              // normal
                                }
                            }
                            break;
                    }
                    glyphShapeBelow = Shape.STEM;
                    break;

                //////////////////////////////  OE  ///////////////////////////////

                case Uni.OE:
                    gender = Gender.FEMININE;
                    switch (location) {
                        case ISOLATE:
                            if (isSuffix) {
                                renderedWord.insert(0, Glyph.FINA_OE);                 // O suffix   *** suffix rule ***
                            } else {
                                renderedWord.insert(0, Glyph.ISOL_OE);                 // normal
                            }
                            break;
                        case INITIAL:
                            if (isSuffix) {
                                renderedWord.insert(0, Glyph.MEDI_OE_BP);              // O of OO suffix   *** suffix rule ***
                            } else {
                                renderedWord.insert(0, Glyph.INIT_OE);                 // normal
                            }
                            break;
                        case MEDIAL:
                            if (fvs == Uni.FVS1) {
                                if (isRoundLetterIncludingQG(mongolWord.charAt(i - 1))) {
                                    renderedWord.insert(0, Glyph.MEDI_OE_FVS1_BP);     // first syllable long tooth OE after BPFK
                                } else {
                                    renderedWord.insert(0, Glyph.MEDI_OE_FVS1);        // first syllable long tooth OE
                                }
                            } else if (fvs == Uni.FVS2) {
                                renderedWord.insert(0, Glyph.MEDI_OE_FVS2);            // extra tooth for 2 part name
                            } else {
                                if (needsLongToothU(mongolWord, i)) {
                                    // *** first syllable long tooth rule (except in suffix) ***
                                    if (isRoundLetterIncludingQG(mongolWord.charAt(i - 1))) {
                                        renderedWord.insert(0, Glyph.MEDI_OE_FVS1_BP); // first syllable long tooth UE after BPFK
                                    } else {
                                        renderedWord.insert(0, Glyph.MEDI_OE_FVS1);    // first syllable long tooth UE
                                    }
                                } else if (isRoundLetterIncludingQG(mongolWord.charAt(i - 1))) {
                                    renderedWord.insert(0, Glyph.MEDI_OE_BP);          // After BPFK
                                } else {
                                    renderedWord.insert(0, Glyph.MEDI_OE);             // normal
                                }
                            }
                            break;
                        case FINAL:
                            if (fvs == Uni.FVS1) {
                                if (isRoundLetterIncludingQG(mongolWord.charAt(i - 1))) {
                                    renderedWord.insert(0, Glyph.FINA_OE_FVS1_BP);     // round o with tail after BPFK
                                } else {
                                    renderedWord.insert(0, Glyph.FINA_OE_FVS1);        // round o with tail
                                }
                            } else {
                                if (isRoundLetterIncludingQG(mongolWord.charAt(i - 1))) {
                                    renderedWord.insert(0, Glyph.FINA_OE_BP);          // After BPFK
                                } else {
                                    renderedWord.insert(0, Glyph.FINA_OE);             // normal
                                }
                            }
                            break;
                    }
                    glyphShapeBelow = Shape.STEM;
                    break;

                //////////////////////////////  UE  ///////////////////////////////

                case Uni.UE:
                    gender = Gender.FEMININE;
                    switch (location) {
                        case ISOLATE:
                            if (isSuffix) {
                                renderedWord.insert(0, Glyph.FINA_UE);                 // O suffix   *** suffix rule ***
                            } else if (fvs == Uni.FVS1) {
                                renderedWord.insert(0, Glyph.ISOL_UE_FVS1);            // like E+UE
                            } else {
                                renderedWord.insert(0, Glyph.ISOL_UE);                 // normal
                            }
                            break;
                        case INITIAL:
                            if (isSuffix) {
                                renderedWord.insert(0, Glyph.MEDI_UE_BP);              // U of UU suffix   *** suffix rule ***
                            } else {
                                renderedWord.insert(0, Glyph.INIT_UE);                 // normal
                            }
                            break;
                        case MEDIAL:
                            if (fvs == Uni.FVS1) {
                                if (isRoundLetterIncludingQG(mongolWord.charAt(i - 1))) {
                                    renderedWord.insert(0, Glyph.MEDI_UE_FVS1_BP);     // first syllable long tooth UE after BPFK
                                } else {
                                    renderedWord.insert(0, Glyph.MEDI_UE_FVS1);        // first syllable long tooth UE
                                }
                            } else if (fvs == Uni.FVS2) {
                                renderedWord.insert(0, Glyph.MEDI_UE_FVS2);            // extra tooth for 2 part name
                            } else {
                                if (needsLongToothU(mongolWord, i)) {
                                    // *** first syllable long tooth rule (except in suffix) ***
                                    if (isRoundLetterIncludingQG(mongolWord.charAt(i - 1))) {
                                        renderedWord.insert(0, Glyph.MEDI_UE_FVS1_BP); // first syllable long tooth UE after BPFK
                                    } else {
                                        renderedWord.insert(0, Glyph.MEDI_UE_FVS1);    // first syllable long tooth UE
                                    }
                                } else if (isRoundLetterIncludingQG(mongolWord.charAt(i - 1))) {
                                    renderedWord.insert(0, Glyph.MEDI_UE_BP);          // After BPFK
                                } else {
                                    renderedWord.insert(0, Glyph.MEDI_UE);             // normal
                                }
                            }
                            break;
                        case FINAL:
                            if (fvs == Uni.FVS1) {
                                if (isRoundLetterIncludingQG(mongolWord.charAt(i - 1))) {
                                    renderedWord.insert(0, Glyph.FINA_UE_FVS1_BP);     // round o with tail after BPFK
                                } else {
                                    renderedWord.insert(0, Glyph.FINA_UE_FVS1);        // round o with tail
                                }
                            } else {
                                if (isRoundLetterIncludingQG(mongolWord.charAt(i - 1))) {
                                    renderedWord.insert(0, Glyph.FINA_UE_BP);          // After BPFK
                                } else {
                                    renderedWord.insert(0, Glyph.FINA_UE);             // normal
                                }
                            }
                            break;
                    }
                    glyphShapeBelow = Shape.STEM;
                    break;

                //////////////////////////////  EE  ///////////////////////////////

                case Uni.EE:
                    gender = Gender.FEMININE;
                    switch (location) {
                        case ISOLATE:
                            renderedWord.insert(0, Glyph.ISOL_EE);                      // normal
                            break;
                        case INITIAL:
                            renderedWord.insert(0, Glyph.INIT_EE);                      // normal
                            break;
                        case MEDIAL:
                            renderedWord.insert(0, Glyph.MEDI_EE);                      // normal
                            break;
                        case FINAL:
                            renderedWord.insert(0, Glyph.FINA_EE);                      // normal
                            break;
                    }
                    glyphShapeBelow = Shape.TOOTH;
                    break;

                //////////////////////////////  NA  ///////////////////////////////

                case Uni.NA:
                    switch (location) {
                        case ISOLATE:
                            renderedWord.insert(0, Glyph.ISOL_NA);                      // normal
                            break;
                        case INITIAL:
                            if (fvs == Uni.FVS1) {
                                renderedWord.insert(0, Glyph.INIT_NA_FVS1_STEM);        // non-dotted
                            } else {
                                 if (glyphShapeBelow == Shape.STEM) {
                                     renderedWord.insert(0, Glyph.INIT_NA_STEM);        // normal stem
                                 } else {
                                     renderedWord.insert(0, Glyph.INIT_NA_TOOTH);       // normal tooth
                                 }
                            }
                            break;
                        case MEDIAL:

                            if (fvs == Uni.FVS1) {
                                if (glyphShapeBelow == Shape.STEM) {
                                    renderedWord.insert(0, Glyph.MEDI_NA_FVS1_STEM);    // dotted stem
                                } else {
                                    renderedWord.insert(0, Glyph.MEDI_NA_FVS1_TOOTH);   // dotted tooth
                                }
                                glyphShapeBelow = Shape.TOOTH;
                            } else if (fvs == Uni.FVS2) {
                                renderedWord.insert(0, Glyph.MEDI_NA_FVS2);             // MVS
                                glyphShapeBelow = Shape.STEM;
                            } else if (fvs == Uni.FVS3) {
                                renderedWord.insert(0, Glyph.MEDI_NA_FVS3);             // tod script
                                glyphShapeBelow = Shape.TOOTH;
                            } else {
                                // *** dot N before vowel rule ***
                                if (isVowel(charBelow)) {
                                    // *** don't dot N if final letter before vowel of compound name ***
                                    if (i < length - 2 && isFVS(mongolWord.charAt(i + 2)) &&
                                            isTwoPartNameInitialVowel(charBelow, charBelowFvs)) {
                                        // This will work for names whose second part starts with
                                        // A, I, O, U, OE, and UE. But it won't work if it starts
                                        // with E or EE because there are no second medial (FVS1)
                                        // forms for these letters. A user could insert a ZWJ but
                                        // they are unlikely to know that.
                                        if (glyphShapeBelow == Shape.STEM) {
                                            renderedWord.insert(0, Glyph.MEDI_NA_STEM);    // non-dotted stem
                                        } else {
                                            renderedWord.insert(0, Glyph.MEDI_NA_TOOTH);   // non-dotted tooth
                                        }
                                    } else {
                                        if (glyphShapeBelow == Shape.STEM) {
                                            renderedWord.insert(0, Glyph.MEDI_NA_FVS1_STEM);    // dotted stem
                                        } else {
                                            renderedWord.insert(0, Glyph.MEDI_NA_FVS1_TOOTH);   // dotted tooth
                                        }
                                    }
                                } else {
                                    if (glyphShapeBelow == Shape.STEM) {
                                        renderedWord.insert(0, Glyph.MEDI_NA_STEM);    // normal non-dotted stem
                                    } else {
                                        renderedWord.insert(0, Glyph.MEDI_NA_TOOTH);   // normal non-dotted tooth
                                    }
                                }
                                glyphShapeBelow = Shape.TOOTH;
                            }
                            break;
                        case FINAL:
                            if (charBelow == Uni.MVS) {
                                renderedWord.insert(0, Glyph.MEDI_NA_FVS2);             // MVS
                            } else {
                                renderedWord.insert(0, Glyph.FINA_NA);                  // normal
                            }
                            glyphShapeBelow = Shape.STEM;
                            break;
                    }
                    break;

                //////////////////////////////  ANG  ///////////////////////////////

                case Uni.ANG:
                    switch (location) {
                        case ISOLATE:
                            renderedWord.insert(0, Glyph.ISOL_ANG);                      // normal
                            break;
                        case INITIAL:
                            if (glyphShapeBelow == Shape.ROUND) {
                                renderedWord.insert(0, Glyph.INIT_ANG_ROUND);            // before round
                            } else if (glyphShapeBelow == Shape.STEM) {
                                renderedWord.insert(0, Glyph.INIT_ANG_STEM);             // before stem
                            } else {
                                renderedWord.insert(0, Glyph.INIT_ANG_TOOTH);            // tooth tooth
                            }
                            break;
                        case MEDIAL:
                            if (glyphShapeBelow == Shape.ROUND) {
                                renderedWord.insert(0, Glyph.MEDI_ANG_ROUND);            // before round
                            } else if (glyphShapeBelow == Shape.STEM) {
                                renderedWord.insert(0, Glyph.MEDI_ANG_STEM);             // before stem
                            } else {
                                renderedWord.insert(0, Glyph.MEDI_ANG_TOOTH);            // tooth tooth
                            }
                            break;
                        case FINAL:
                            renderedWord.insert(0, Glyph.FINA_ANG);                      // normal
                            break;
                    }
                    glyphShapeBelow = Shape.TOOTH;
                    break;

                //////////////////////////////  BA  ///////////////////////////////

                case Uni.BA:
                    switch (location) {
                        case ISOLATE:
                            renderedWord.insert(0, Glyph.ISOL_BA);                       // normal
                            break;
                        case INITIAL:
                            if (isOuVowel(charBelow)) {
                                renderedWord.insert(0, Glyph.INIT_BA_OU);                // OU
                            } else if (glyphShapeBelow == Shape.STEM) {
                                renderedWord.insert(0, Glyph.INIT_BA_STEM);              // stem
                            } else {
                                renderedWord.insert(0, Glyph.INIT_BA);                   // normal
                            }
                            break;
                        case MEDIAL:
                            if (isOuVowel(charBelow)) {
                                renderedWord.insert(0, Glyph.MEDI_BA_OU);                // OU
                            } else if (glyphShapeBelow == Shape.STEM) {
                                renderedWord.insert(0, Glyph.MEDI_BA_STEM);              // stem
                            } else {
                                renderedWord.insert(0, Glyph.MEDI_BA_TOOTH);                   // normal
                            }
                            break;
                        case FINAL:
                            if (fvs == Uni.FVS1) {
                                renderedWord.insert(0, Glyph.FINA_BA_FVS1);              // o with left sweep
                            } else {
                                renderedWord.insert(0, Glyph.FINA_BA);                   // normal
                            }
                            break;
                    }
                    glyphShapeBelow = Shape.STEM;
                    break;

                //////////////////////////////  PA  ///////////////////////////////

                case Uni.PA:
                    switch (location) {
                        case ISOLATE:
                            renderedWord.insert(0, Glyph.ISOL_PA);                       // normal
                            break;
                        case INITIAL:
                            if (isOuVowel(charBelow)) {
                                renderedWord.insert(0, Glyph.INIT_PA_OU);                // OU
                            } else if (glyphShapeBelow == Shape.STEM) {
                                renderedWord.insert(0, Glyph.INIT_PA_STEM);              // stem
                            } else {
                                renderedWord.insert(0, Glyph.INIT_PA);                   // normal
                            }
                            break;
                        case MEDIAL:
                            if (isOuVowel(charBelow)) {
                                renderedWord.insert(0, Glyph.MEDI_PA_OU);                // OU
                            } else if (glyphShapeBelow == Shape.STEM) {
                                renderedWord.insert(0, Glyph.MEDI_PA_STEM);              // stem
                            } else {
                                renderedWord.insert(0, Glyph.MEDI_PA_TOOTH);                   // normal
                            }
                            break;
                        case FINAL:
                            renderedWord.insert(0, Glyph.FINA_PA);                       // normal
                            break;
                    }
                    glyphShapeBelow = Shape.STEM;
                    break;

                //////////////////////////////  QA  ///////////////////////////////

                case Uni.QA:
                    switch (location) {
                        case ISOLATE:
                            if (fvs == Uni.FVS1) {
                                renderedWord.insert(0, Glyph.ISOL_QA_FVS1);             // dotted feminine
                            } else {
                                renderedWord.insert(0, Glyph.ISOL_QA);                  // normal
                            }
                            break;
                        case INITIAL:
                            if (fvs == Uni.FVS1) {
                                if (isFeminineVowel(charBelow) || charBelow == Uni.I) {
                                    if (isOuVowel(charBelow)) {
                                        renderedWord.insert(0, Glyph.INIT_QA_FVS1_FEM_OU);   // dotted feminine for OU
                                    } else {
                                        renderedWord.insert(0, Glyph.INIT_QA_FVS1_FEM);      // dotted feminine
                                    }
                                } else {
                                    if (glyphShapeBelow == Shape.STEM) {
                                        renderedWord.insert(0, Glyph.INIT_QA_FVS1_STEM);     // dotted masculine stem
                                    } else {
                                        renderedWord.insert(0, Glyph.INIT_QA_FVS1_TOOTH);    // dotted masculine tooth
                                    }
                                }
                            } else {
                                if (isFeminineVowel(charBelow) || charBelow == Uni.I) {
                                    if (isOuVowel(charBelow)) {
                                        renderedWord.insert(0, Glyph.INIT_QA_FEM_OU);   // feminine for OU
                                    } else {
                                        renderedWord.insert(0, Glyph.INIT_QA_FEM);      // feminine
                                    }
                                } else {
                                    if (glyphShapeBelow == Shape.STEM) {
                                        renderedWord.insert(0, Glyph.INIT_QA_STEM);     // normal (masculine) stem
                                    } else {
                                        renderedWord.insert(0, Glyph.INIT_QA_TOOTH);    // normal (masculine) tooth
                                    }
                                }
                            }
                            break;
                        case MEDIAL:

                            if (fvs == Uni.FVS1) {
                                if (isFeminineVowel(charBelow) || charBelow == Uni.I) {
                                    if (isOuVowel(charBelow)) {
                                        renderedWord.insert(0, Glyph.MEDI_QA_FVS1_FEM_OU);   // dotted feminine for OU
                                    } else {
                                        renderedWord.insert(0, Glyph.MEDI_QA_FVS1_FEM);      // dotted feminine
                                    }
                                    glyphShapeBelow = Shape.ROUND;
                                } else if (isMasculineVowel(charBelow)) {
                                    renderedWord.insert(0, Glyph.MEDI_QA_FVS1);         // dotted double tooth masculine
                                    glyphShapeBelow = Shape.TOOTH;
                                } else { // consonant
                                    if (gender == Gender.NEUTER) {
                                        gender = getWordGenderAboveIndex(i, mongolWord);
                                    }
                                    if (gender == Gender.FEMININE) {
                                        renderedWord.insert(0, Glyph.MEDI_QA_FEM_CONSONANT_DOTTED);   // dotted feminine final before consonant
                                    } else {
                                        renderedWord.insert(0, Glyph.MEDI_QA_FVS1);     // dotted double tooth masculine
                                    }
                                    glyphShapeBelow = Shape.TOOTH;
                                }
                            } else if (fvs == Uni.FVS2) {
                                renderedWord.insert(0, Glyph.MEDI_QA_FVS2);             // dotted MVS
                                glyphShapeBelow = Shape.TOOTH;
                            } else if (fvs == Uni.FVS3) {
                                renderedWord.insert(0, Glyph.MEDI_QA_FVS3);             // MVS
                                glyphShapeBelow = Shape.TOOTH;
                            } else {
                                if (isFeminineVowel(charBelow) || charBelow == Uni.I) {
                                    if (isOuVowel(charBelow)) {
                                        renderedWord.insert(0, Glyph.MEDI_QA_FEM_OU);   // feminine for OU
                                    } else {
                                        renderedWord.insert(0, Glyph.MEDI_QA_FEM);      // feminine
                                    }
                                    glyphShapeBelow = Shape.ROUND;
                                } else if (isMasculineVowel(charBelow)) {
                                    if (glyphShapeBelow == Shape.STEM) {
                                        renderedWord.insert(0, Glyph.MEDI_QA_STEM);     // normal stem (masculine double tooth)
                                    } else {
                                        renderedWord.insert(0, Glyph.MEDI_QA_TOOTH);    // normal tooth (masculine double tooth)
                                    }
                                    glyphShapeBelow = Shape.TOOTH;
                                } else { // consonant
                                    // does medial QA before a consonant ever happen
                                    // in a real word?
                                    if (gender == Gender.NEUTER) {
                                        gender = getWordGenderAboveIndex(i, mongolWord);
                                    }
                                    if (gender == Gender.FEMININE ||
                                            (gender == Gender.NEUTER
                                                    && mongolWord.charAt(i - 1) == Uni.I)) {
                                        renderedWord.insert(0, Glyph.MEDI_QA_FEM_CONSONANT);   // feminine final before consonant
                                    } else {
                                        if (glyphShapeBelow == Shape.STEM) {
                                            renderedWord.insert(0, Glyph.MEDI_QA_STEM);        // normal stem (masculine double tooth)
                                        } else {
                                            renderedWord.insert(0, Glyph.MEDI_QA_TOOTH);       // normal tooth (masculine double tooth)
                                        }
                                    }
                                    glyphShapeBelow = Shape.TOOTH;
                                }
                            }
                            break;
                        case FINAL:
                            renderedWord.insert(0, Glyph.FINA_QA);                       // normal
                            glyphShapeBelow = Shape.TOOTH;
                            break;
                    }
                    break;

                //////////////////////////////  GA  ///////////////////////////////

                case Uni.GA:
                    switch (location) {
                        case ISOLATE:
                            renderedWord.insert(0, Glyph.ISOL_GA);                      // normal
                            break;
                        case INITIAL:
                            if (fvs == Uni.FVS1) {
                                if (glyphShapeBelow == Shape.STEM) {
                                    renderedWord.insert(0, Glyph.INIT_GA_FVS1_STEM);    // undotted masculine stem
                                } else {
                                    renderedWord.insert(0, Glyph.INIT_GA_FVS1_TOOTH);   // undotted masculine tooth
                                }
                                // TODO feminine forms are not handled.
                                // What are they supposed to look like?
                            } else {
                                if (isFeminineVowel(charBelow) || charBelow == Uni.I) {
                                    if (isOuVowel(charBelow)) {
                                        renderedWord.insert(0, Glyph.INIT_GA_FEM_OU);   // feminine for OU
                                    } else {
                                        renderedWord.insert(0, Glyph.INIT_GA_FEM);      // feminine
                                    }
                                } else {
                                    if (isConsonant(charBelow)) {
                                        // *** feminine form before consonant in foreign words ***
                                        renderedWord.insert(0, Glyph.INIT_GA_FEM);      // feminine
                                    } else if (glyphShapeBelow == Shape.STEM) {
                                        renderedWord.insert(0, Glyph.INIT_GA_STEM);     // normal (masculine) stem
                                    } else {
                                        renderedWord.insert(0, Glyph.INIT_GA_TOOTH);    // normal (masculine) tooth
                                    }
                                }
                            }
                            break;
                        case MEDIAL:

                            if (fvs == Uni.FVS1) {
                                if (glyphShapeBelow == Shape.STEM) {
                                    renderedWord.insert(0, Glyph.MEDI_GA_FVS1_STEM);    // dotted masculine stem
                                } else {
                                    renderedWord.insert(0, Glyph.MEDI_GA_FVS1_TOOTH);   // dotted masculine tooth
                                }
                                glyphShapeBelow = Shape.TOOTH;
                            } else if (fvs == Uni.FVS2) {
                                renderedWord.insert(0, Glyph.MEDI_GA_FVS2);             // MVS
                                glyphShapeBelow = Shape.TOOTH;
                            } else if (fvs == Uni.FVS3) {
                                if (glyphShapeBelow == Shape.STEM) {
                                    renderedWord.insert(0, Glyph.MEDI_GA_FVS3_STEM);    // feminine before consonant stem
                                } else {
                                    renderedWord.insert(0, Glyph.MEDI_GA_FVS3_TOOTH);   // feminine before consonant tooth
                                }
                                glyphShapeBelow = Shape.TOOTH;
                            } else { // no FVS, just apply context rules
                                if (isFeminineVowel(charBelow) || charBelow == Uni.I) {
                                    // *** feminine GA rule ***
                                    if (isOuVowel(charBelow)) {
                                        renderedWord.insert(0, Glyph.MEDI_GA_FEM_OU);   // feminine for OU
                                    } else {
                                        renderedWord.insert(0, Glyph.MEDI_GA_FEM);      // feminine
                                    }
                                    glyphShapeBelow = Shape.ROUND;
                                } else if (isMasculineVowel(charBelow)) {
                                    // *** dotted masculine GA rule ***
                                    if (glyphShapeBelow == Shape.STEM) {
                                        renderedWord.insert(0, Glyph.MEDI_GA_FVS1_STEM);   // dotted masculine stem
                                    } else {
                                        renderedWord.insert(0, Glyph.MEDI_GA_FVS1_TOOTH);  // dotted masculine tooth
                                    }
                                    glyphShapeBelow = Shape.TOOTH;
                                } else { // consonant
                                    if (gender == Gender.NEUTER) {
                                        gender = getWordGenderAboveIndex(i, mongolWord);
                                    }
                                    // *** medial GA before consonant rule ***
                                    if (gender == Gender.FEMININE ||
                                            // Defaulting to feminine form for I
                                            (gender == Gender.NEUTER
                                                    && mongolWord.charAt(i - 1) == Uni.I) ||
                                            // treat a G between two consonants as feminine (as in ANGGLI)
                                            // (but not after Y because Y is like I)
                                            (mongolWord.charAt(i - 1) != Uni.YA &&
                                                    (isConsonant(mongolWord.charAt(i - 1)) ||
                                                            mongolWord.charAt(i - 1) == Uni.ZWJ))) {

                                        if (glyphShapeBelow == Shape.STEM) {
                                            renderedWord.insert(0, Glyph.MEDI_GA_FVS3_STEM);    // feminine before consonant stem
                                        } else {
                                            renderedWord.insert(0, Glyph.MEDI_GA_FEM);      // feminine
                                            //renderedWord.insert(0, Glyph.MEDI_GA_FVS3_TOOTH);   // feminine before consonant tooth
                                        }
                                        glyphShapeBelow = Shape.ROUND;
                                    } else {
                                        renderedWord.insert(0, Glyph.MEDI_GA);       // normal (undotted masculine)
                                        glyphShapeBelow = Shape.TOOTH;
                                    }

                                }
                            }
                            break;
                        case FINAL:
                            if (fvs == Uni.FVS1) {
                                renderedWord.insert(0, Glyph.FINA_GA_FVS1);          // masculine context override (same as default)
                            } else if (fvs == Uni.FVS2) {
                                renderedWord.insert(0, Glyph.FINA_GA_FVS2);          // feminine
                            } else if (charBelow == Uni.MVS) {
                                renderedWord.insert(0, Glyph.MEDI_GA_FVS2);          // MVS
                            } else {
                                gender = getWordGenderAboveIndex(i, mongolWord);
                                if (gender == Gender.MASCULINE ||
                                        mongolWord.charAt(i - 1) == Uni.ZWJ) {
                                    renderedWord.insert(0, Glyph.FINA_GA);           // masculine
                                } else {
                                    // Defaulting to feminine form for I
                                    renderedWord.insert(0, Glyph.FINA_GA_FVS2);      // feminine
                                }
                            }
                            glyphShapeBelow = Shape.TOOTH;
                            break;
                    }
                    break;

                //////////////////////////////  MA  ///////////////////////////////

                case Uni.MA:
                    switch (location) {
                        case ISOLATE:
                            renderedWord.insert(0, Glyph.ISOL_MA);                       // normal
                            break;
                        case INITIAL:
                            if (glyphShapeBelow == Shape.STEM) {
                                renderedWord.insert(0, Glyph.INIT_MA_STEM_LONG);         // stem
                            } else {
                                renderedWord.insert(0, Glyph.INIT_MA_TOOTH);             // tooth
                            }
                            break;
                        case MEDIAL:
                            if (isRoundLetter(mongolWord.charAt(i - 1)) ||
                                    mongolWord.charAt(i - 1) == Uni.ANG) {
                                renderedWord.insert(0, Glyph.MEDI_MA_BP);                // tail extended for round letter
                            } else if (mongolWord.charAt(i - 1) == Uni.QA ||
                                    mongolWord.charAt(i - 1) == Uni.GA) {
                                if (gender == Gender.NEUTER) {
                                    gender = getWordGenderAboveIndex(i, mongolWord);
                                }
                                if (gender != Gender.MASCULINE ||
                                        // feminine G when between consonants
                                        (i > 1 && mongolWord.charAt(i - 1) == Uni.GA &&
                                                (isConsonant(mongolWord.charAt(i - 2)) ||
                                                        mongolWord.charAt(i - 2) == Uni.ZWJ))) {
                                    renderedWord.insert(0, Glyph.MEDI_MA_BP);            // tail extended for round letter
                                } else {
                                    renderedWord.insert(0, Glyph.MEDI_MA_TOOTH);         // tooth
                                }
                            } else if (glyphShapeBelow != Shape.TOOTH ||
                                    // use the longer stem if M/L is below
                                    charBelow == Uni.MA || charBelow == Uni.LA ||
                                    charBelow == Uni.LHA) {
                                renderedWord.insert(0, Glyph.MEDI_MA_STEM_LONG);         // stem
                            } else {
                                renderedWord.insert(0, Glyph.MEDI_MA_TOOTH);             // tooth
                            }
                            glyphShapeBelow = Shape.TOOTH;
                            break;
                        case FINAL:
                            renderedWord.insert(0, Glyph.FINA_MA);                       // normal
                            glyphShapeBelow = Shape.STEM;
                            break;
                    }
                    break;

                //////////////////////////////  LA  ///////////////////////////////

                case Uni.LA:
                    switch (location) {
                        case ISOLATE:
                            renderedWord.insert(0, Glyph.ISOL_LA);                       // normal
                            break;
                        case INITIAL:
                            if (glyphShapeBelow == Shape.STEM) {
                                renderedWord.insert(0, Glyph.INIT_LA_STEM_LONG);         // stem
                            } else {
                                renderedWord.insert(0, Glyph.INIT_LA_TOOTH);             // tooth
                            }
                            break;
                        case MEDIAL:
                            if (isRoundLetter(mongolWord.charAt(i - 1)) ||
                                    mongolWord.charAt(i - 1) == Uni.ANG) {
                                renderedWord.insert(0, Glyph.MEDI_LA_BP);                // tail extended for round letter
                            } else if (mongolWord.charAt(i - 1) == Uni.QA ||
                                    mongolWord.charAt(i - 1) == Uni.GA) {
                                if (gender == Gender.NEUTER) {
                                    gender = getWordGenderAboveIndex(i, mongolWord);
                                }
                                if (gender != Gender.MASCULINE ||
                                        // feminine G when between consonants
                                        (i > 1 && mongolWord.charAt(i - 1) == Uni.GA &&
                                                (isConsonant(mongolWord.charAt(i - 2)) ||
                                                        mongolWord.charAt(i - 2) == Uni.ZWJ))) {
                                    renderedWord.insert(0, Glyph.MEDI_LA_BP);            // tail extended for round letter
                                } else {
                                    renderedWord.insert(0, Glyph.MEDI_LA_TOOTH);         // tooth
                                }
                            } else if (glyphShapeBelow != Shape.TOOTH ||
                                    // use the longer stem if M/L is below
                                    charBelow == Uni.MA || charBelow == Uni.LA ||
                                    charBelow == Uni.LHA) {
                                renderedWord.insert(0, Glyph.MEDI_LA_STEM_LONG);         // stem
                            } else {
                                renderedWord.insert(0, Glyph.MEDI_LA_TOOTH);             // tooth
                            }
                            glyphShapeBelow = Shape.TOOTH;
                            break;
                        case FINAL:
                            renderedWord.insert(0, Glyph.FINA_LA);                       // normal
                            glyphShapeBelow = Shape.STEM;
                            break;
                    }
                    break;

                //////////////////////////////  SA  ///////////////////////////////

                case Uni.SA:

                    switch (location) {

                        case ISOLATE:
                            renderedWord.insert(0, Glyph.ISOL_SA);                       // normal
                            break;
                        case INITIAL:
                            if (glyphShapeBelow == Shape.STEM) {
                                renderedWord.insert(0, Glyph.INIT_SA_STEM);              // stem
                            } else {
                                renderedWord.insert(0, Glyph.INIT_SA_TOOTH);             // tooth
                            }
                            break;
                        case MEDIAL:
                            if (glyphShapeBelow == Shape.STEM) {
                                renderedWord.insert(0, Glyph.MEDI_SA_STEM);              // stem
                            } else {
                                renderedWord.insert(0, Glyph.MEDI_SA_TOOTH);             // tooth
                            }
                            glyphShapeBelow = Shape.TOOTH;
                            break;
                        case FINAL:
                            glyphShapeBelow = Shape.TOOTH;
                            if (fvs == Uni.FVS1) {
                                renderedWord.insert(0, Glyph.FINA_SA_FVS1);              // short tail
                                glyphShapeBelow = Shape.STEM;
                            } else if (fvs == Uni.FVS2) {
                                renderedWord.insert(0, Glyph.FINA_SA_FVS2);              // (missing glyph)
                            } else {
                                renderedWord.insert(0, Glyph.FINA_SA);                   // normal
                            }
                            break;
                    }
                    break;

                //////////////////////////////  SHA  ///////////////////////////////

                case Uni.SHA:
                    switch (location) {
                        case ISOLATE:
                            renderedWord.insert(0, Glyph.ISOL_SHA);                       // normal
                            break;
                        case INITIAL:
                            if (glyphShapeBelow == Shape.STEM) {
                                renderedWord.insert(0, Glyph.INIT_SHA_STEM);              // stem
                            } else {
                                renderedWord.insert(0, Glyph.INIT_SHA_TOOTH);             // tooth
                            }
                            break;
                        case MEDIAL:
                            if (glyphShapeBelow == Shape.STEM) {
                                renderedWord.insert(0, Glyph.MEDI_SHA_STEM);              // stem
                            } else {
                                renderedWord.insert(0, Glyph.MEDI_SHA_TOOTH);             // tooth
                            }
                            break;
                        case FINAL:
                            renderedWord.insert(0, Glyph.FINA_SHA);                       // normal
                            break;
                    }
                    glyphShapeBelow = Shape.TOOTH;
                    break;

                //////////////////////////////  TA  ///////////////////////////////

                case Uni.TA:
                    switch (location) {
                        case ISOLATE:
                            renderedWord.insert(0, Glyph.ISOL_TA);                       // normal
                            break;
                        case INITIAL:
                            if (glyphShapeBelow == Shape.STEM) {
                                renderedWord.insert(0, Glyph.INIT_TA_STEM);              // stem
                            } else {
                                renderedWord.insert(0, Glyph.INIT_TA_TOOTH);             // tooth
                            }
                            break;
                        case MEDIAL:
                            if (fvs == Uni.FVS1) {
                                if (glyphShapeBelow == Shape.STEM) {
                                    renderedWord.insert(0, Glyph.MEDI_TA_FVS1_STEM);     // stem
                                } else {
                                    renderedWord.insert(0, Glyph.MEDI_TA_FVS1_TOOTH);    // tooth
                                }
                                glyphShapeBelow = Shape.STEM;
                            } else {
                                renderedWord.insert(0, Glyph.MEDI_TA);                   // normal
                                glyphShapeBelow = Shape.TOOTH;
                            }
                            break;
                        case FINAL:
                            renderedWord.insert(0, Glyph.FINA_TA);                       // normal
                            glyphShapeBelow = Shape.STEM;
                            break;
                    }
                    break;

                //////////////////////////////  DA  ///////////////////////////////

                case Uni.DA:
                    switch (location) {
                        case ISOLATE:
                            renderedWord.insert(0, Glyph.ISOL_DA);                       // normal
                            break;
                        case INITIAL:
                            if (fvs == Uni.FVS1 || isSuffix) {
                                renderedWord.insert(0, Glyph.INIT_DA_FVS1);              // left slanting
                            } else {
                                if (glyphShapeBelow == Shape.STEM) {
                                    renderedWord.insert(0, Glyph.INIT_DA_STEM);          // stem
                                } else {
                                    renderedWord.insert(0, Glyph.INIT_DA_TOOTH);         // tooth
                                }
                            }
                            break;
                        case MEDIAL:
                            if (fvs == Uni.FVS1) {
                                renderedWord.insert(0, Glyph.MEDI_DA_FVS1);              // left slanting
                                glyphShapeBelow = Shape.TOOTH;
                            } else {
                                if (isVowel(charBelow)) {
                                    renderedWord.insert(0, Glyph.MEDI_DA_FVS1);          // left slanting
                                    glyphShapeBelow = Shape.TOOTH;
                                } else {
                                    renderedWord.insert(0, Glyph.MEDI_DA);               // normal (before consonant)
                                    glyphShapeBelow = Shape.STEM;
                                }
                            }
                            break;
                        case FINAL:
                            if (fvs == Uni.FVS1) {
                                renderedWord.insert(0, Glyph.FINA_DA_FVS1);              // left slanting
                                glyphShapeBelow = Shape.TOOTH;
                            } else {
                                renderedWord.insert(0, Glyph.FINA_DA);                   // normal (like o-n)
                                glyphShapeBelow = Shape.STEM;
                            }
                            break;
                    }
                    break;

                //////////////////////////////  CHA  ///////////////////////////////

                case Uni.CHA:
                    switch (location) {
                        case ISOLATE:
                            renderedWord.insert(0, Glyph.ISOL_CHA);                       // normal
                            break;
                        case INITIAL:
                            renderedWord.insert(0, Glyph.INIT_CHA);                       // normal
                            break;
                        case MEDIAL:
                            renderedWord.insert(0, Glyph.MEDI_CHA);                       // normal
                            break;
                        case FINAL:
                            renderedWord.insert(0, Glyph.FINA_CHA);                       // normal
                            break;
                    }
                    glyphShapeBelow = Shape.STEM;
                    break;

                //////////////////////////////  JA  ///////////////////////////////

                case Uni.JA:
                    switch (location) {
                        case ISOLATE:
                            renderedWord.insert(0, Glyph.ISOL_JA);                       // normal
                            break;
                        case INITIAL:
                            if (glyphShapeBelow == Shape.STEM) {
                                renderedWord.insert(0, Glyph.INIT_JA_STEM);              // stem
                            } else {
                                // The Qimad font seems to be broken here
                                // so temporarily disabling this glyph
                                // TODO fix the font, or remove it, or just use
                                // this alternate glyph.
                                //renderedWord.insert(0, Glyph.INIT_JA_TOOTH);             // tooth
                                renderedWord.insert(0, Glyph.INIT_JA_STEM);
                            }
                            break;
                        case MEDIAL:
                            if (fvs == Uni.FVS1) {
                                renderedWord.insert(0, Glyph.MEDI_JA_FVS1);              // MVS
                                glyphShapeBelow = Shape.TOOTH;
                            } else {
                                renderedWord.insert(0, Glyph.MEDI_JA);                   // normal (before consonant)
                                glyphShapeBelow = Shape.STEM;
                            }
                            break;
                        case FINAL:
                            if (charBelow == Uni.MVS) {
                                renderedWord.insert(0, Glyph.MEDI_JA_FVS1);              // MVS
                                glyphShapeBelow = Shape.TOOTH;
                            } else {
                                renderedWord.insert(0, Glyph.FINA_JA);                   // normal
                                glyphShapeBelow = Shape.STEM;
                            }
                            break;
                    }
                    break;

                //////////////////////////////  YA  ///////////////////////////////

                case Uni.YA:
                    switch (location) {
                        case ISOLATE:
                            renderedWord.insert(0, Glyph.ISOL_YA);                  // normal
                            break;
                        case INITIAL:
                            if (isSuffix && charBelow == Uni.I) {
                                renderedWord.insert(0, Glyph.MEDI_YA_FVS1);         // suffix
                            } else if (fvs == Uni.FVS1) {
                                renderedWord.insert(0, Glyph.INIT_YA_FVS1);         // no hook
                            } else {
                                renderedWord.insert(0, Glyph.INIT_YA);              // hook
                            }
                            break;
                        case MEDIAL:
                            if (fvs == Uni.FVS1) {
                                renderedWord.insert(0, Glyph.MEDI_YA_FVS1);         // no hook
                            } else if (isSuffix && mongolWord.charAt(i - 1) == Uni.I) {
                                // *** no hook after Y (as in IYEN and IYER) ***
                                renderedWord.insert(0, Glyph.MEDI_YA_FVS1);         // suffix
                            } else {
                                char charAbove = mongolWord.charAt(i - 1);

                                // *** AYI, EYI, OYI, UYI, OEYI, UEYI
                                // medial double tooth YI diphthong rule ***
                                // Also do this for consonant below.
                                if (needsLongToothU(mongolWord, i - 1) || charAbove == Uni.I) {
                                    if (charBelow == Uni.I || isConsonant(charBelow)) {
                                        renderedWord.insert(0, Glyph.MEDI_YA_FVS1);     // no hook
                                    } else {
                                        renderedWord.insert(0, Glyph.MEDI_YA);          // hook
                                    }
                                } else if (isVowel(charAbove)) {
                                    if (charBelow == Uni.I) {
                                        if (i < length - 2) {
                                            renderedWord.setCharAt(0, Glyph.MEDI_I_DOUBLE_TOOTH); // double tooth replacement
                                        } else {
                                            // omit the Y if YI is at the end of a word
                                        }
                                        glyphIndexNeedsAdjusting = true;
                                    } else if (isConsonant(charBelow)) {
                                        renderedWord.insert(0, Glyph.MEDI_I_DOUBLE_TOOTH); // double tooth
                                    } else {
                                        renderedWord.insert(0, Glyph.MEDI_YA);          // hook
                                    }
                                } else {
                                    renderedWord.insert(0, Glyph.MEDI_YA);          // hook
                                }
                            }
                            break;
                        case FINAL:
                            renderedWord.insert(0, Glyph.FINA_YA);                  // normal
                            break;
                    }
                    glyphShapeBelow = Shape.TOOTH;
                    break;

                //////////////////////////////  RA  ///////////////////////////////

                case Uni.RA:
                    switch (location) {
                        case ISOLATE:
                            renderedWord.insert(0, Glyph.ISOL_RA);                  // normal
                            break;
                        case INITIAL:
                            if (glyphShapeBelow == Shape.STEM) {
                                renderedWord.insert(0, Glyph.INIT_RA_STEM);         // stem
                            } else {
                                renderedWord.insert(0, Glyph.INIT_RA_TOOTH);        // tooth
                            }
                            break;
                        case MEDIAL:
                            if (glyphShapeBelow == Shape.STEM) {
                                renderedWord.insert(0, Glyph.MEDI_RA_STEM);         // stem
                            } else {
                                renderedWord.insert(0, Glyph.MEDI_RA_TOOTH);        // tooth
                            }
                            glyphShapeBelow = Shape.TOOTH;
                            break;
                        case FINAL:
                            renderedWord.insert(0, Glyph.FINA_RA);                  // normal
                            glyphShapeBelow = Shape.STEM;
                            break;
                    }

                    break;

                //////////////////////////////  WA  ///////////////////////////////

                case Uni.WA:
                    switch (location) {
                        case ISOLATE:
                            renderedWord.insert(0, Glyph.ISOL_WA);                  // normal
                            break;
                        case INITIAL:
                            renderedWord.insert(0, Glyph.INIT_WA);                  // normal
                            break;
                        case MEDIAL:
                            renderedWord.insert(0, Glyph.MEDI_WA);              // normal
                            glyphShapeBelow = Shape.TOOTH;
                            break;
                        case FINAL:
                            if (fvs == Uni.FVS1) {
                                renderedWord.insert(0, Glyph.FINA_WA_FVS1);         // round like final o
                                glyphShapeBelow = Shape.STEM;
                            } else if (charBelow == Uni.MVS) {
                                renderedWord.insert(0, Glyph.FINA_WA_FVS1);         // MVS
                                glyphShapeBelow = Shape.STEM;
                            } else {
                                renderedWord.insert(0, Glyph.FINA_WA);              // normal
                                glyphShapeBelow = Shape.TOOTH;
                            }
                            break;
                    }
                    break;

                //////////////////////////////  FA  ///////////////////////////////

                case Uni.FA:
                    switch (location) {
                        case ISOLATE:
                            renderedWord.insert(0, Glyph.ISOL_FA);                       // normal
                            break;
                        case INITIAL:
                            if (isOuVowel(charBelow)) {
                                renderedWord.insert(0, Glyph.INIT_FA_OU);                // OU
                            } else if (glyphShapeBelow == Shape.STEM) {
                                renderedWord.insert(0, Glyph.INIT_FA_STEM);              // stem
                            } else {
                                renderedWord.insert(0, Glyph.INIT_FA);                   // normal
                            }
                            break;
                        case MEDIAL:
                            if (isOuVowel(charBelow)) {
                                renderedWord.insert(0, Glyph.MEDI_FA_OU);                // OU
                            } else if (glyphShapeBelow == Shape.STEM) {
                                renderedWord.insert(0, Glyph.MEDI_FA_STEM);              // stem
                            } else {
                                renderedWord.insert(0, Glyph.MEDI_FA_TOOTH);             // normal
                            }
                            break;
                        case FINAL:
                            renderedWord.insert(0, Glyph.FINA_FA);                       // normal
                            break;
                    }
                    glyphShapeBelow = Shape.STEM;
                    break;

                //////////////////////////////  KA  ///////////////////////////////

                case Uni.KA:
                    switch (location) {
                        case ISOLATE:
                            renderedWord.insert(0, Glyph.ISOL_KA);                       // normal
                            break;
                        case INITIAL:
                            if (isOuVowel(charBelow)) {
                                renderedWord.insert(0, Glyph.INIT_KA_OU);                // OU
                            } else {
                                renderedWord.insert(0, Glyph.INIT_KA);                   // normal
                            }
                            break;
                        case MEDIAL:
                            if (isOuVowel(charBelow)) {
                                renderedWord.insert(0, Glyph.MEDI_KA_OU);                // OU
                            } else if (glyphShapeBelow == Shape.STEM) {
                                renderedWord.insert(0, Glyph.MEDI_KA_STEM);              // stem
                            } else {
                                renderedWord.insert(0, Glyph.MEDI_KA_TOOTH);                   // normal
                            }
                            break;
                        case FINAL:
                            renderedWord.insert(0, Glyph.FINA_KA);                       // normal
                            break;
                    }
                    glyphShapeBelow = Shape.STEM;
                    break;

                //////////////////////////////  KHA  ///////////////////////////////

                case Uni.KHA:
                    switch (location) {
                        case ISOLATE:
                            renderedWord.insert(0, Glyph.ISOL_KHA);                       // normal
                            break;
                        case INITIAL:
                            if (isOuVowel(charBelow)) {
                                renderedWord.insert(0, Glyph.INIT_KHA_OU);                // OU
                            } else {
                                renderedWord.insert(0, Glyph.INIT_KHA);                   // normal
                            }
                            break;
                        case MEDIAL:
                            if (isOuVowel(charBelow)) {
                                renderedWord.insert(0, Glyph.MEDI_KHA_OU);                // OU
                            } else if (glyphShapeBelow == Shape.STEM) {
                                renderedWord.insert(0, Glyph.MEDI_KHA_STEM);              // stem
                            } else {
                                renderedWord.insert(0, Glyph.MEDI_KHA_TOOTH);             // normal
                            }
                            break;
                        case FINAL:
                            renderedWord.insert(0, Glyph.FINA_KHA);                       // normal
                            break;
                    }
                    glyphShapeBelow = Shape.STEM;
                    break;

                //////////////////////////////  TSA  ///////////////////////////////

                case Uni.TSA:
                    switch (location) {
                        case ISOLATE:
                            renderedWord.insert(0, Glyph.ISOL_TSA);                       // normal
                            break;
                        case INITIAL:
                            renderedWord.insert(0, Glyph.INIT_TSA);                       // normal
                            break;
                        case MEDIAL:
                            renderedWord.insert(0, Glyph.MEDI_TSA);                       // normal
                            break;
                        case FINAL:
                            renderedWord.insert(0, Glyph.FINA_TSA);                       // normal
                            break;
                    }
                    glyphShapeBelow = Shape.STEM;
                    break;

                //////////////////////////////  ZA  ///////////////////////////////

                case Uni.ZA:
                    switch (location) {
                        case ISOLATE:
                            renderedWord.insert(0, Glyph.ISOL_ZA);                        // normal
                            break;
                        case INITIAL:
                            renderedWord.insert(0, Glyph.INIT_ZA);                        // normal
                            break;
                        case MEDIAL:
                            renderedWord.insert(0, Glyph.MEDI_ZA);                        // normal
                            break;
                        case FINAL:
                            renderedWord.insert(0, Glyph.FINA_ZA);                        // normal
                            break;
                    }
                    glyphShapeBelow = Shape.STEM;
                    break;

                //////////////////////////////  HAA  ///////////////////////////////

                case Uni.HAA:
                    switch (location) {
                        case ISOLATE:
                            renderedWord.insert(0, Glyph.ISOL_HAA);                        // normal
                            break;
                        case INITIAL:
                            renderedWord.insert(0, Glyph.INIT_HAA);                        // normal
                            break;
                        case MEDIAL:
                            renderedWord.insert(0, Glyph.MEDI_HAA);                        // normal
                            break;
                        case FINAL:
                            renderedWord.insert(0, Glyph.FINA_HAA);                        // normal
                            break;
                    }
                    glyphShapeBelow = Shape.TOOTH;
                    break;

                //////////////////////////////  ZRA  ///////////////////////////////

                case Uni.ZRA:
                    switch (location) {
                        case ISOLATE:
                            renderedWord.insert(0, Glyph.ISOL_ZRA);                        // normal
                            break;
                        case INITIAL:
                            renderedWord.insert(0, Glyph.INIT_ZRA);                        // normal
                            break;
                        case MEDIAL:
                            renderedWord.insert(0, Glyph.MEDI_ZRA);                        // normal
                            break;
                        case FINAL:
                            renderedWord.insert(0, Glyph.FINA_ZRA);                        // normal
                            break;
                    }
                    glyphShapeBelow = Shape.STEM; // ROUND didn't look very good
                    break;

                //////////////////////////////  LHA  ///////////////////////////////

                case Uni.LHA:
                    switch (location) {
                        case ISOLATE:
                            renderedWord.insert(0, Glyph.ISOL_LHA);                       // normal
                            break;
                        case INITIAL:
                            renderedWord.insert(0, Glyph.INIT_LHA);                       // normal
                            break;
                        case MEDIAL:
                            if (isRoundLetter(mongolWord.charAt(i - 1)) ||
                                    mongolWord.charAt(i - 1) == Uni.ANG) {
                                renderedWord.insert(0, Glyph.MEDI_LHA_BP);                // tail extended for round letter
                            } else if (mongolWord.charAt(i - 1) == Uni.QA ||
                                    mongolWord.charAt(i - 1) == Uni.GA) {
                                if (gender == Gender.NEUTER) {
                                    gender = getWordGenderAboveIndex(i, mongolWord);
                                }
                                if (gender == Gender.FEMININE) {
                                    renderedWord.insert(0, Glyph.MEDI_LHA_BP);            // tail extended for round letter
                                } else {
                                    renderedWord.insert(0, Glyph.MEDI_LHA);               // normal
                                }
                            } else {
                                renderedWord.insert(0, Glyph.MEDI_LHA);                   // normal
                            }
                            break;
                        case FINAL:
                            renderedWord.insert(0, Glyph.FINA_LHA);                       // normal
                            break;
                    }
                    glyphShapeBelow = Shape.TOOTH;
                    break;

                //////////////////////////////  ZHI  ///////////////////////////////

                case Uni.ZHI:
                    switch (location) {
                        case ISOLATE:
                            renderedWord.insert(0, Glyph.ISOL_ZHI);                        // normal
                            break;
                        case INITIAL:
                            renderedWord.insert(0, Glyph.INIT_ZHI);                        // normal
                            break;
                        case MEDIAL:
                            renderedWord.insert(0, Glyph.MEDI_ZHI);                        // normal
                            break;
                        case FINAL:
                            renderedWord.insert(0, Glyph.FINA_ZHI);                        // normal
                            break;
                    }
                    glyphShapeBelow = Shape.TOOTH;
                    break;

                //////////////////////////////  CHI  ///////////////////////////////

                case Uni.CHI:
                    switch (location) {
                        case ISOLATE:
                            renderedWord.insert(0, Glyph.ISOL_CHI);                        // normal
                            break;
                        case INITIAL:
                            renderedWord.insert(0, Glyph.INIT_CHI);                        // normal
                            break;
                        case MEDIAL:
                            renderedWord.insert(0, Glyph.MEDI_CHI);                        // normal
                            break;
                        case FINAL:
                            renderedWord.insert(0, Glyph.FINA_CHI);                        // normal
                            break;
                    }
                    glyphShapeBelow = Shape.STEM;
                    break;



                //////////////////////////////  NNBS  ///////////////////////////////

                case Uni.NNBS:
                    renderedWord.insert(0, Uni.NNBS);
                    break;
                default:
                    // any extra FVS and MVS characters are ignored
                    glyphIndexNeedsAdjusting = true;
            }

            if (glyphIndexNeedsAdjusting) {
                if (glyphIndexes != null) {
                    glyphIndexes[lastUnicodeIndex - length  + 1 + i] = NON_PRINTING_CHAR; // FIXME
                }
                glyphIndexNeedsAdjusting = false;
            }

            charBelow = currentChar;
            charBelowFvs = fvs;
            fvs = 0;
        }

        return renderedWord.toString();
    }


    private boolean needsLongToothU(String word, int uIndex) {

        if (word.charAt(uIndex) != Uni.OE && word.charAt(uIndex) != Uni.UE) return false;

        if (uIndex == 0) return true;

        if (uIndex == 1) {
            if (isConsonant(word.charAt(0))) {
                // strange BUU exception
                if (word.equals("\u182A\u1826\u1826")) {
                    return false;
                }
                return true;
            }
        }

        if (uIndex == 2) {
            if (isConsonant(word.charAt(0)) && isFVS(word.charAt(1))) {
                return true;
            }
        }

        return false;
    }

    private boolean isRoundLetterIncludingQG(char character) {
        return (character == Uni.BA || character == Uni.PA || character == Uni.QA ||
                character == Uni.GA || character == Uni.FA || character == Uni.KA ||
                character == Uni.KHA);
    }

    private boolean isRoundLetter(char character) {
        return (character == Uni.BA || character == Uni.PA || character == Uni.FA ||
                character == Uni.KA || character == Uni.KHA);
    }

    private static boolean isVowel(char character) {
        return (character >= Uni.A && character <= Uni.EE);
    }

    private static boolean isOuVowel(char character) {
        return (character >= Uni.O && character <= Uni.UE);
    }

    private boolean isMasculineVowel(char character) {
        return (character == Uni.A || character == Uni.O || character == Uni.U);
    }

    private boolean isFeminineVowel(char character) {
        return (character == Uni.E || character == Uni.EE || character == Uni.OE || character == Uni.UE);
    }

    private boolean isConsonant(char character) {
        return (character >= Uni.NA && character <= Uni.CHI);
    }

    private boolean isFVS(char character) {
        return (character >= Uni.FVS1 && character <= Uni.FVS3);
    }

    private boolean isTwoPartNameInitialVowel(char vowel, char fvs) {
        // XXX There is no way to recognize initial E or EE
        return (vowel == Uni.A && fvs == Uni.FVS1) ||
                (vowel == Uni.I && fvs == Uni.FVS1) ||
                (vowel == Uni.O && fvs == Uni.FVS1) ||
                (vowel == Uni.U && fvs == Uni.FVS1) ||
                (vowel == Uni.OE && fvs == Uni.FVS3) ||
                (vowel == Uni.UE && fvs == Uni.FVS3);
    }

    private static boolean isMongolian(char character) {
        // Mongolian letters, MVS, FVS1-3, NIRUGU, Uni.ZWJ, (but not NNBS)
        return ((character >= Uni.A && character <= Uni.CHI)
                || (character >= Uni.MONGOLIAN_NIRUGU && character <= Uni.MVS) || character == Uni.ZWJ);
    }

    private boolean isBGDRS(char character) {
        // This method is not used internally, only for external use.
        return (character == Uni.BA || character == Uni.GA || character == Uni.DA
                || character == Uni.RA || character == Uni.SA);
    }

    private static boolean isMongolianAlphabet(char character) {
        // This method is not used internally, only for external use.
        return (character >= Uni.A && character <= Uni.CHI);
    }

    // this starts at the index and works up
    // FIXME: If there are mixed genders then only the first will be reported
    // (could add a MIXED form)
    private Gender getWordGenderAboveIndex(int index, String word) {
        for (int i = index - 1; i >= 0; i--) {
            if (isMasculineVowel(word.charAt(i))) {
                return Gender.MASCULINE;
            } else if (isFeminineVowel(word.charAt(i))) {
                return Gender.FEMININE;
            }
        }
        return Gender.NEUTER;
    }

    public class Uni {

        public static final char ZWNJ = '\u200C'; // Zero-width non joiner
        public static final char ZWJ = '\u200D'; // Zero-width joiner
        public static final char NNBS = '\u202F'; // Narrow No-Break Space
        // Unicode Mongolian Values
        public static final char MONGOLIAN_BIRGA = '\u1800';
        public static final char MONGOLIAN_ELLIPSIS = '\u1801';
        public static final char MONGOLIAN_COMMA = '\u1802';
        public static final char MONGOLIAN_FULL_STOP = '\u1803';
        public static final char MONGOLIAN_COLON = '\u1804';
        public static final char MONGOLIAN_FOUR_DOTS = '\u1805';
        public static final char MONGOLIAN_TODO_SOFT_HYPHEN = '\u1806';
        public static final char MONGOLIAN_SIBE_SYLLABLE_BOUNDARY_MARKER = '\u1807';
        public static final char MONGOLIAN_MANCH_COMMA = '\u1808';
        public static final char MONGOLIAN_MANCHU_FULL_STOP = '\u1809';
        public static final char MONGOLIAN_NIRUGU = '\u180a';
        public static final char FVS1 = '\u180b';
        public static final char FVS2 = '\u180c';
        public static final char FVS3 = '\u180d';
        public static final char MVS = '\u180e'; // MONGOLIAN_VOWEL_SEPARATOR
        public static final char MONGOLIAN_DIGIT_ZERO = '\u1810';
        public static final char MONGOLIAN_DIGIT_ONE = '\u1811';
        public static final char MONGOLIAN_DIGIT_TWO = '\u1812';
        public static final char MONGOLIAN_DIGIT_THREE = '\u1813';
        public static final char MONGOLIAN_DIGIT_FOUR = '\u1814';
        public static final char MONGOLIAN_DIGIT_FIVE = '\u1815';
        public static final char MONGOLIAN_DIGIT_SIX = '\u1816';
        public static final char MONGOLIAN_DIGIT_SEVEN = '\u1817';
        public static final char MONGOLIAN_DIGIT_EIGHT = '\u1818';
        public static final char MONGOLIAN_DIGIT_NINE = '\u1819';
        public static final char A = '\u1820'; // MONGOLIAN_LETTER_xx
        public static final char E = '\u1821';
        public static final char I = '\u1822';
        public static final char O = '\u1823';
        public static final char U = '\u1824';
        public static final char OE = '\u1825';
        public static final char UE = '\u1826';
        public static final char EE = '\u1827';
        public static final char NA = '\u1828';
        public static final char ANG = '\u1829';
        public static final char BA = '\u182A';
        public static final char PA = '\u182B';
        public static final char QA = '\u182C';
        public static final char GA = '\u182D';
        public static final char MA = '\u182E';
        public static final char LA = '\u182F';
        public static final char SA = '\u1830';
        public static final char SHA = '\u1831';
        public static final char TA = '\u1832';
        public static final char DA = '\u1833';
        public static final char CHA = '\u1834';
        public static final char JA = '\u1835';
        public static final char YA = '\u1836';
        public static final char RA = '\u1837';
        public static final char WA = '\u1838';
        public static final char FA = '\u1839';
        public static final char KA = '\u183A';
        public static final char KHA = '\u183B';
        public static final char TSA = '\u183C';
        public static final char ZA = '\u183D';
        public static final char HAA = '\u183E';
        public static final char ZRA = '\u183F';
        public static final char LHA = '\u1840';
        public static final char ZHI = '\u1841';
        public static final char CHI = '\u1842';
    };

    private class Glyph {

        private static final char MENKSOFT_START = '\uE234';
        private static final char MENKSOFT_END = '\uE34F';

        // Private Use Area glyph values
        //private static final char NOTDEF = '\uE360';
        // TODO add these to the MenksoftToUnicode conversion
        private static final char BIRGA = '\uE234';
        private static final char ELLIPSIS = '\uE235';
        private static final char COMMA = '\uE236';
        private static final char FULL_STOP = '\uE237';
        private static final char COLON = '\uE238';
        private static final char FOUR_DOTS = '\uE239';
        private static final char TODO_SOFT_HYPHEN = '\uE23A';
        private static final char SIBE_SYLLABLE_BOUNDARY_MARKER = '\uE23B';
        private static final char MANCH_COMMA = '\uE23C';
        private static final char MANCHU_FULL_STOP = '\uE23D';
        private static final char NIRUGU = '\uE23E';
        private static final char BIRGA_WITH_ORNAMENT = '\uE23F';
        private static final char ROTATED_BIRGA = '\uE240';
        private static final char DOUBLE_BIRGA_WITH_ORNAMENT = '\uE241';
        private static final char TRIPLE_BIRGA_WITH_ORNAMENT = '\uE242';
        private static final char MIDDLE_DOT = '\uE243';
        private static final char ZERO = '\uE244';
        private static final char ONE = '\uE245';
        private static final char TWO = '\uE246';
        private static final char THREE = '\uE247';
        private static final char FOUR = '\uE248';
        private static final char FIVE = '\uE249';
        private static final char SIX = '\uE24A';
        private static final char SEVEN = '\uE24B';
        private static final char EIGHT = '\uE24C';
        private static final char NINE = '\uE24D';
        private static final char QUESTION_EXCLAMATION = '\uE24E';
        private static final char EXCLAMATION_QUESTION = '\uE24F';
        private static final char EXCLAMATION = '\uE250';
        private static final char QUESTION = '\uE251';
        private static final char SEMICOLON = '\uE252';
        private static final char LEFT_PARENTHESIS = '\uE253';
        private static final char RIGHT_PARENTHESIS = '\uE254';
        private static final char LEFT_ANGLE_BRACKET = '\uE255';
        private static final char RIGHT_ANGLE_BRACKET = '\uE256';
        private static final char LEFT_BRACKET = '\uE257';
        private static final char RIGHT_BRACKET = '\uE258';
        private static final char LEFT_DOUBLE_ANGLE_BRACKET = '\uE259';
        private static final char RIGHT_DOUBLE_ANGLE_BRACKET = '\uE25A';
        private static final char LEFT_WHITE_CORNER_BRACKET = '\uE25B';
        private static final char RIGHT_WHITE_CORNER_BRACKET = '\uE25C';
        private static final char FULLWIDTH_COMMA = '\uE25D';
        private static final char X = '\uE25E';
        private static final char REFERENCE_MARK = '\uE25F';                   // 0x203b
        private static final char EM_DASH = '\uE260';
        private static final char TWO_EM_DASH = '\uE261';

        // These are in the order of the Unicode 9 specs sheet
        // BP = looks better after B, P (and other rounded like Q, G, F, K, KH)
        // MVS = final glyph varient for MVS
        // gv = glyph varient, same basic glyph form as the one it follows.
        // TOOTH = the ending of this character matches a following character that slants left (for example, a tooth)
        // STEM = the ending of this character matches a following character that starts with a vertical stem
        // ROUND = the ending of this character matches a round following character (feminine QG)
        private static final char A_START = '\uE264';
        private static final char ISOL_A = '\uE264';
        private static final char INIT_A = '\uE266';
        private static final char MEDI_A = '\uE26C';
        private static final char MEDI_A_BP = '\uE26D'; // gv
        private static final char FINA_A = '\uE268';
        private static final char FINA_A_BP = '\uE26B'; // final A following BPKF
        private static final char ISOL_A_FVS1 = '\uE265';
        private static final char MEDI_A_FVS1 = '\uE26E'; //    TODO check difference from specs
        private static final char FINA_A_FVS1 = '\uE269';
        private static final char FINA_A_MVS = '\uE26A'; // gv for MVS + A
        private static final char MEDI_A_FVS2 = '\uE267'; // A of ACHA suffix

        private static final char E_START = '\uE270';
        private static final char ISOL_E = '\uE270';
        private static final char INIT_E = '\uE271';
        private static final char MEDI_E = '\uE276';
        private static final char MEDI_E_BP = '\uE277';
        private static final char FINA_E = '\uE273';
        private static final char FINA_E_BP = '\uE275'; // final E following BPKF
        private static final char INIT_E_FVS1 = '\uE272';
        private static final char FINA_E_FVS1 = '\uE269'; // no E glyph so using A
        private static final char FINA_E_MVS = '\uE274'; // gv for MVS + E

        private static final char I_START = '\uE279';
        private static final char ISOL_I = '\uE279';
        private static final char ISOL_I_SUFFIX = '\uE282';
        private static final char INIT_I = '\uE27A';
        private static final char MEDI_I = '\uE27E';
        private static final char MEDI_I_SUFFIX = '\uE280';
        private static final char MEDI_I_BP = '\uE27F'; // gv
        private static final char MEDI_I_DOUBLE_TOOTH = '\uE281'; // gv
        private static final char FINA_I = '\uE27B';
        private static final char FINA_I_BP = '\uE27C'; // gv
        private static final char MEDI_I_FVS1 = '\uE27D'; //

        private static final char O_START = '\uE283';
        private static final char ISOL_O = '\uE283';
        private static final char INIT_O = '\uE284';
        private static final char MEDI_O = '\uE289';
        private static final char MEDI_O_BP = '\uE28A';
        private static final char FINA_O = '\uE285';
        private static final char FINA_O_BP = '\uE287'; // gv
        private static final char MEDI_O_FVS1 = '\uE288';
        private static final char FINA_O_FVS1 = '\uE286';

        private static final char U_START = '\uE28B';  // Using Init U gliph
        private static final char ISOL_U = '\uE28C';  // Using Init U gliph
        private static final char INIT_U = '\uE28C';
        private static final char MEDI_U = '\uE291';
        private static final char MEDI_U_BP = '\uE292'; // gv
        private static final char FINA_U = '\uE28D';
        private static final char FINA_U_BP = '\uE28F'; // gv
        private static final char MEDI_U_FVS1 = '\uE290';
        private static final char FINA_U_FVS1 = '\uE28E';  // FIXME not defined in Unicode 9.0

        private static final char OE_START = '\uE293';
        private static final char ISOL_OE = '\uE293';
        private static final char INIT_OE = '\uE295';
        private static final char MEDI_OE = '\uE29E';
        private static final char MEDI_OE_BP = '\uE29F'; // gv
        private static final char FINA_OE = '\uE296';
        private static final char FINA_OE_BP = '\uE29A'; // gv
        private static final char MEDI_OE_FVS1 = '\uE29C';
        private static final char MEDI_OE_FVS1_BP = '\uE29D';
        private static final char FINA_OE_FVS1 = '\uE297';
        private static final char FINA_OE_FVS1_BP = '\uE298'; // gv
        private static final char MEDI_OE_FVS2 = '\uE29B';

        private static final char UE_START = '\uE2A0';
        private static final char ISOL_UE = '\uE2A2'; // Using initial glyph
        private static final char INIT_UE = '\uE2A2';
        private static final char MEDI_UE = '\uE2AB';
        private static final char MEDI_UE_BP = '\uE2AC';
        private static final char FINA_UE = '\uE2A3';
        private static final char FINA_UE_BP = '\uE2A7';
        private static final char ISOL_UE_FVS1 = '\uE2A1';
        private static final char MEDI_UE_FVS1 = '\uE2A9';
        private static final char MEDI_UE_FVS1_BP = '\uE2AA';
        private static final char FINA_UE_FVS1 = '\uE2A4';
        private static final char FINA_UE_FVS1_BP = '\uE2A5';
        private static final char MEDI_UE_FVS2 = '\uE2A8';

        private static final char EE_START = '\uE2AD';
        private static final char ISOL_EE = '\uE2AD';
        private static final char INIT_EE = '\uE2AE';
        private static final char MEDI_EE = '\uE2B0';
        private static final char FINA_EE = '\uE2AF';

        private static final char NA_START = '\uE2B1';
        private static final char ISOL_NA = '\uE2B3';
        private static final char INIT_NA_TOOTH = '\uE2B1';
        private static final char INIT_NA_STEM = '\uE2B3';
        private static final char MEDI_NA_TOOTH = '\uE2B8';
        private static final char MEDI_NA_STEM = '\uE2BA';
        private static final char FINA_NA = '\uE2B5';
        private static final char INIT_NA_FVS1_TOOTH = '\uE2B2';
        private static final char INIT_NA_FVS1_STEM = '\uE2B4';
        private static final char MEDI_NA_FVS1_TOOTH = '\uE2B7';
        private static final char MEDI_NA_FVS1_STEM = '\uE2B9';
        //private static final char MEDI_NA_FVS1_NG = '\uE2BF'; // What is this one for?
        private static final char MEDI_NA_FVS2 = '\uE2B6'; // MVS
        private static final char MEDI_NA_FVS3 = '\uE2B7'; // Tod Mongol N; FIXME: no glyph, substituting medial dotted n

        private static final char ANG_START = '\uE2BB';
        private static final char ISOL_ANG = '\uE2BC';
        private static final char INIT_ANG_TOOTH = '\uE2BC';
        private static final char INIT_ANG_ROUND = '\uE2BD';
        private static final char INIT_ANG_STEM = '\uE2BE';
        private static final char MEDI_ANG_TOOTH = '\uE2BC'; // good for following tooth
        private static final char MEDI_ANG_ROUND = '\uE2BD'; // good for following round letter like B P H K
        private static final char MEDI_ANG_STEM = '\uE2BE'; // good for following stem letter like J CH R
        private static final char FINA_ANG = '\uE2BB';
        private static final char ANG_END = '\uE2BE';

        private static final char BA_START = '\uE2C1';
        private static final char ISOL_BA = '\uE2C1';
        private static final char INIT_BA = '\uE2C1';
        private static final char INIT_BA_OU = '\uE2C2';
        private static final char INIT_BA_STEM = '\uE2C7'; // using medial stem
        private static final char MEDI_BA_TOOTH = '\uE2C5';
        private static final char MEDI_BA_OU = '\uE2C6';
        private static final char MEDI_BA_STEM = '\uE2C7';
        private static final char FINA_BA = '\uE2C3';
        private static final char FINA_BA_FVS1 = '\uE2C4';

        private static final char PA_START = '\uE2C8';
        private static final char ISOL_PA = '\uE2C8';
        private static final char INIT_PA = '\uE2C8';
        private static final char INIT_PA_OU = '\uE2C9';
        private static final char INIT_PA_STEM = '\uE2CD'; // using medial stem
        private static final char MEDI_PA_TOOTH = '\uE2CB';
        private static final char MEDI_PA_OU = '\uE2CC';
        private static final char MEDI_PA_STEM = '\uE2CD';
        private static final char FINA_PA = '\uE2CA';

        private static final char QA_START = '\uE2CE';
        private static final char ISOL_QA = '\uE2D2';
        private static final char INIT_QA_TOOTH = '\uE2CE';
        private static final char INIT_QA_STEM = '\uE2D2';
        private static final char INIT_QA_FEM = '\uE2D0'; // GV FEMININE
        private static final char INIT_QA_FEM_OU = '\uE2D4';
        private static final char MEDI_QA_TOOTH = '\uE2D8';
        private static final char MEDI_QA_STEM = '\uE2DC';
        private static final char MEDI_QA_FEM = '\uE2DA';
        private static final char MEDI_QA_FEM_CONSONANT = '\uE2DF';
        private static final char MEDI_QA_FEM_CONSONANT_DOTTED = '\uE2E0';
        private static final char MEDI_QA_FEM_OU = '\uE2DD';
        private static final char FINA_QA = '\uE2D6';
        private static final char INIT_QA_FVS1_TOOTH = '\uE2CF';
        private static final char INIT_QA_FVS1_STEM = '\uE2D3';
        private static final char INIT_QA_FVS1_FEM = '\uE2D1';
        private static final char INIT_QA_FVS1_FEM_OU = '\uE2D5';
        private static final char MEDI_QA_FVS1 = '\uE2D9';
        private static final char MEDI_QA_FVS1_FEM = '\uE2DB';
        private static final char MEDI_QA_FVS1_FEM_OU = '\uE2DE';
        private static final char ISOL_QA_FVS1 = '\uE2D1'; // feminine with 2 dots
        private static final char MEDI_QA_FVS2 = '\uE2D7';
        private static final char MEDI_QA_FVS3 = '\uE2D6';

        private static final char GA_START = '\uE2E1';
        private static final char ISOL_GA = '\uE2E4';
        private static final char INIT_GA_TOOTH = '\uE2E1';
        private static final char INIT_GA_STEM = '\uE2E4';
        private static final char INIT_GA_FEM = '\uE2E3';
        private static final char INIT_GA_FEM_OU = '\uE2E6';
        private static final char MEDI_GA = '\uE2EE';
        private static final char MEDI_GA_FEM = '\uE2EB';
        private static final char MEDI_GA_FEM_OU = '\uE2ED';
        private static final char FINA_GA = '\uE2E7';
        private static final char INIT_GA_FVS1_TOOTH = '\uE2E2';
        private static final char INIT_GA_FVS1_STEM = '\uE2E5';
        private static final char MEDI_GA_FVS1_TOOTH = '\uE2EA';
        private static final char MEDI_GA_FVS1_STEM = '\uE2EC';
        // This deviation is necessary to override context rules.
        // This follows the WG2 decision: https://r12a.github.io/mongolian-variants/
        private static final char FINA_GA_FVS1 = '\uE2E7'; // masculine context override FIXME Deviating from Unicode 9.0 !!!
        private static final char FINA_GA_FVS2 = '\uE2E8'; // feminine final form FIXME Deviating from Unicode 9.0 !!!
        private static final char MEDI_GA_FVS2 = '\uE2E9';
        private static final char MEDI_GA_FVS3_TOOTH = '\uE2EF';
        private static final char MEDI_GA_FVS3_STEM = '\uE2F0';

        private static final char MA_START = '\uE2F1';
        private static final char ISOL_MA = '\uE2F2';
        private static final char INIT_MA_TOOTH = '\uE2F1';
        private static final char INIT_MA_STEM_LONG = '\uE2F2';
        private static final char MEDI_MA_TOOTH = '\uE2F4';
        private static final char MEDI_MA_STEM_LONG = '\uE2F5'; // long stem GV, use this if M or L follows
        private static final char MEDI_MA_BP = '\uE2F6'; // GV use this if following a B, P, H, K, etc.
        private static final char FINA_MA = '\uE2F3';

        private static final char LA_START = '\uE2F7';
        private static final char ISOL_LA = '\uE2F8';
        private static final char INIT_LA_TOOTH = '\uE2F7';
        private static final char INIT_LA_STEM_LONG = '\uE2F8';
        private static final char MEDI_LA_TOOTH = '\uE2FA';
        private static final char MEDI_LA_STEM_LONG = '\uE2FB'; // long stem GV, use this if M or L follows
        private static final char MEDI_LA_BP = '\uE2FC'; // GV use this if following a B, P, H, K, etc.
        private static final char FINA_LA = '\uE2F9';

        private static final char SA_START = '\uE2FD';
        private static final char ISOL_SA = '\uE2FE';
        private static final char INIT_SA_TOOTH = '\uE2FD';
        private static final char INIT_SA_STEM = '\uE2FE';
        private static final char MEDI_SA_TOOTH = '\uE301';
        private static final char MEDI_SA_STEM = '\uE302';
        private static final char FINA_SA = '\uE2FF';
        private static final char FINA_SA_FVS1 = '\uE300';
        private static final char FINA_SA_FVS2 = '\uE2FF'; //0x100CE; FIXME: glyph is not in Menksoft PUA, substituting first form

        private static final char SHA_START = '\uE303';
        private static final char ISOL_SHA = '\uE304';
        private static final char INIT_SHA_TOOTH = '\uE303';
        private static final char INIT_SHA_STEM = '\uE304';
        private static final char MEDI_SHA_TOOTH = '\uE306';
        private static final char MEDI_SHA_STEM = '\uE307';
        private static final char FINA_SHA = '\uE305';

        private static final char TA_START = '\uE308';
        private static final char ISOL_TA = '\uE309';
        private static final char INIT_TA_TOOTH = '\uE308';
        private static final char INIT_TA_STEM = '\uE309';
        private static final char MEDI_TA = '\uE30B';
        private static final char FINA_TA = '\uE30A';
        private static final char MEDI_TA_FVS1_TOOTH = '\uE30C';
        private static final char MEDI_TA_FVS1_STEM = '\uE30D';

        private static final char DA_START = '\uE30E';
        private static final char ISOL_DA = '\uE310';
        private static final char INIT_DA_TOOTH = '\uE30E';
        private static final char INIT_DA_STEM = '\uE30F';
        private static final char MEDI_DA = '\uE314';
        private static final char FINA_DA = '\uE311';
        private static final char INIT_DA_FVS1 = '\uE310';
        private static final char MEDI_DA_FVS1 = '\uE313';
        private static final char FINA_DA_FVS1 = '\uE312';

        private static final char CHA_START = '\uE315';
        private static final char ISOL_CHA = '\uE315';
        private static final char INIT_CHA = '\uE315';
        private static final char MEDI_CHA = '\uE317';
        private static final char FINA_CHA = '\uE316';

        private static final char JA_START = '\uE318';
        private static final char ISOL_JA = '\uE318';
        private static final char INIT_JA_TOOTH = '\uE319';
        private static final char INIT_JA_STEM = '\uE31A';
        private static final char MEDI_JA = '\uE31D';
        private static final char FINA_JA = '\uE31B';
        private static final char MEDI_JA_FVS1 = '\uE31C'; // MVS

        private static final char YA_START = '\uE31E';
        private static final char ISOL_YA = '\uE31E';
        private static final char INIT_YA = '\uE31E';
        private static final char MEDI_YA = '\uE320';
        private static final char FINA_YA = '\uE31F';
        private static final char INIT_YA_FVS1 = '\uE321';
        private static final char MEDI_YA_FVS1 = '\uE321';
        private static final char MEDI_YA_FVS2 = '\uE31F';

        private static final char RA_START = '\uE322';
        private static final char ISOL_RA = '\uE322';
        private static final char INIT_RA_TOOTH = '\uE323';
        private static final char INIT_RA_STEM = '\uE322';
        private static final char MEDI_RA_TOOTH = '\uE327';
        private static final char MEDI_RA_STEM = '\uE326';
        private static final char FINA_RA = '\uE325';

        private static final char WA_START = '\uE329';
        private static final char ISOL_WA = '\uE329';
        private static final char INIT_WA = '\uE329';
        private static final char MEDI_WA = '\uE32C';
        private static final char FINA_WA = '\uE32A';
        private static final char FINA_WA_FVS1 = '\uE32B'; // MVS

        private static final char FA_START = '\uE32D';
        private static final char ISOL_FA = '\uE32D';
        private static final char INIT_FA = '\uE32D';
        private static final char INIT_FA_OU = '\uE32E';
        private static final char INIT_FA_STEM = '\uE332'; // using medial stem
        private static final char MEDI_FA_TOOTH = '\uE330';
        private static final char MEDI_FA_OU = '\uE331';
        private static final char MEDI_FA_STEM = '\uE332';
        private static final char FINA_FA = '\uE32F';

        private static final char KA_START = '\uE333';
        private static final char ISOL_KA = '\uE333';
        private static final char INIT_KA = '\uE333';
        private static final char INIT_KA_OU = '\uE334';
        private static final char MEDI_KA_TOOTH = '\uE336';
        private static final char MEDI_KA_OU = '\uE337';
        private static final char MEDI_KA_STEM = '\uE338';
        private static final char FINA_KA = '\uE335';

        private static final char KHA_START = '\uE339';
        private static final char ISOL_KHA = '\uE339';
        private static final char INIT_KHA = '\uE339';
        private static final char INIT_KHA_OU = '\uE33A';
        private static final char MEDI_KHA_TOOTH = '\uE33C';
        private static final char MEDI_KHA_OU = '\uE33D';
        private static final char MEDI_KHA_STEM = '\uE33E';
        private static final char FINA_KHA = '\uE33B';

        private static final char TSA_START = '\uE33F';
        private static final char ISOL_TSA = '\uE33F';
        private static final char INIT_TSA = '\uE33F';
        private static final char MEDI_TSA = '\uE341';
        private static final char FINA_TSA = '\uE340';

        private static final char ZA_START = '\uE342';
        private static final char ISOL_ZA = '\uE342';
        private static final char INIT_ZA = '\uE342';
        private static final char MEDI_ZA = '\uE344';
        private static final char FINA_ZA = '\uE343';

        private static final char HAA_START = '\uE345';
        private static final char ISOL_HAA = '\uE345';
        private static final char INIT_HAA = '\uE345';
        private static final char MEDI_HAA = '\uE347';
        private static final char FINA_HAA = '\uE346';

        private static final char ZRA_START = '\uE348';
        private static final char ISOL_ZRA = '\uE348';
        private static final char INIT_ZRA = '\uE348';
        private static final char MEDI_ZRA = '\uE349';
        private static final char FINA_ZRA = '\uE34A';

        private static final char LHA_START = '\uE34B';
        private static final char ISOL_LHA = '\uE34B';
        private static final char INIT_LHA = '\uE34B';
        private static final char MEDI_LHA = '\uE34C';
        private static final char MEDI_LHA_BP = '\uE34D';
        private static final char FINA_LHA = '\uE34C';
        private static final char FINA_LHA_BP = '\uE34D';

        private static final char ZHI_START = '\uE34E';
        private static final char ISOL_ZHI = '\uE34E';
        private static final char INIT_ZHI = '\uE34E';
        private static final char MEDI_ZHI = '\uE34E';
        private static final char FINA_ZHI = '\uE34E';

        private static final char CHI_START = '\uE34F';
        private static final char ISOL_CHI = '\uE34F';
        private static final char INIT_CHI = '\uE34F';
        private static final char MEDI_CHI = '\uE34F';
        private static final char FINA_CHI = '\uE34F';

    }



}
