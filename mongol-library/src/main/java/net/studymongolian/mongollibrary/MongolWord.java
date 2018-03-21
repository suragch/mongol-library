package net.studymongolian.mongollibrary;


// package private helper class for MongolCode

class MongolWord {

    // strange exception where the first UE does not get a long tooth
    private static final String BUU_EXCEPTION = "\u182A\u1826\u1826";

    private MongolCode.Gender gender;
    private MongolCode.Location location;
    private int length;
    private boolean isSuffix;
    private CharSequence inputWord;
    private char fvs;
    private Shape glyphShapeBelow;

    private MongolWord() {}

    MongolWord(CharSequence mongolWord) {
        this.inputWord = mongolWord;
        this.gender = MongolCode.Gender.NEUTER;
        this.length = mongolWord.length();
        this.isSuffix = (mongolWord.charAt(0) == MongolCode.Uni.NNBS);
        this.fvs = 0;
        this.glyphShapeBelow = Shape.STEM;
    }

    private void updateLocation(int positionInWord, char charBelow) {
        if (positionInWord == 0) {
            if (length == 1 || (length == 2 && fvs > 0)) {
                location = MongolCode.Location.ISOLATE;
            } else {
                location = MongolCode.Location.INITIAL;
            }
        } else if (positionInWord == length - 1 || (positionInWord == length - 2 && fvs > 0)) {
            if (positionInWord == 1 && isSuffix) {
                location = MongolCode.Location.ISOLATE;
            } else {
                location = MongolCode.Location.FINAL;
            }
        } else {
            if (positionInWord == 1 && isSuffix) {
                location = MongolCode.Location.INITIAL;
            } else if (charBelow == MongolCode.Uni.MVS) {
                // treat character above MVS as a final by default
                location = MongolCode.Location.FINAL;
            } else {
                location = MongolCode.Location.MEDIAL;
            }
        }
    }

    private enum Shape {
        TOOTH,     // glyph slants to the left like a tooth (includes medial T/D, R, W, etc)
        STEM,      // glyph starts with a vertical stem (includes B, O/U, CH, etc)
        ROUND      // glyph top is round (includes feminine Q/G)
    }

    String convertToMenksoftCode() {

        StringBuilder renderedWord = new StringBuilder();
        char charBelow = 0;
        char charBelowFvs = 0;

        // start at the bottom of the word and work up
        for (int i = length - 1; i >= 0; i--) {

            char charAbove;
            char currentChar = inputWord.charAt(i);

            if (MongolCode.isFVS(currentChar)) {
                fvs = currentChar;
                continue;
            } else if (currentChar == MongolCode.Uni.MVS && i != length - 2) {
                // ignore MVS in all but second to last position
                continue;
            }

            // get the location
            updateLocation(i, charBelow);

            charAbove = (i > 0) ? inputWord.charAt(i - 1) : 0;

            // handle each letter separately
            switch (currentChar) {

                case MongolCode.Uni.A:
                    handleA(renderedWord, charAbove);
                    break;
                case MongolCode.Uni.E:
                    handleE(renderedWord, charAbove);
                    break;
                case MongolCode.Uni.I:
                    handleI(renderedWord, i, charAbove, charBelow);
                    break;
                case MongolCode.Uni.O:
                    handleO(renderedWord, charAbove);
                    break;
                case MongolCode.Uni.U:
                    handleU(renderedWord, charAbove);
                    break;
                case MongolCode.Uni.OE:
                    handleOE(renderedWord, i, charAbove);
                    break;
                case MongolCode.Uni.UE:
                    handleUE(renderedWord, i, charAbove);
                    break;
                case MongolCode.Uni.EE:
                    handleEE(renderedWord);
                    break;
                case MongolCode.Uni.NA:
                    handleNA(renderedWord, i, charBelow, charBelowFvs);
                    break;
                case MongolCode.Uni.ANG:
                    handleANG(renderedWord);
                    break;
                case MongolCode.Uni.BA:
                    handleBA(renderedWord, charBelow);
                    break;
                case MongolCode.Uni.PA:
                    handlePA(renderedWord, charBelow);
                    break;
                case MongolCode.Uni.QA:
                    handleQA(renderedWord, i, charAbove, charBelow);
                    break;
                case MongolCode.Uni.GA:
                    handleGA(renderedWord, i, charAbove, charBelow);
                    break;
                case MongolCode.Uni.MA:
                    handleMA(renderedWord, i, charAbove, charBelow);
                    break;
                case MongolCode.Uni.LA:
                    handleLA(renderedWord, i, charAbove, charBelow);
                    break;
                case MongolCode.Uni.SA:
                    handleSA(renderedWord);
                    break;
                case MongolCode.Uni.SHA:
                    handleSHA(renderedWord);
                    break;
                case MongolCode.Uni.TA:
                    handleTA(renderedWord);
                    break;
                case MongolCode.Uni.DA:
                    handleDA(renderedWord, charBelow);
                    break;
                case MongolCode.Uni.CHA:
                    handleCHA(renderedWord);
                    break;
                case MongolCode.Uni.JA:
                    handleJA(renderedWord, charBelow);
                    break;
                case MongolCode.Uni.YA:
                    handleYA(renderedWord, i, charAbove, charBelow);
                    break;
                case MongolCode.Uni.RA:
                    handleRA(renderedWord);
                    break;
                case MongolCode.Uni.WA:
                    handleWA(renderedWord, charBelow);
                    break;
                case MongolCode.Uni.FA:
                    handleFA(renderedWord, charBelow);
                    break;
                case MongolCode.Uni.KA:
                    handleKA(renderedWord, charBelow);
                    break;
                case MongolCode.Uni.KHA:
                    handleKHA(renderedWord, charBelow);
                    break;
                case MongolCode.Uni.TSA:
                    handleTSA(renderedWord);
                    break;
                case MongolCode.Uni.ZA:
                    handleZA(renderedWord);
                    break;
                case MongolCode.Uni.HAA:
                    handleHAA(renderedWord);
                    break;
                case MongolCode.Uni.ZRA:
                    handleZRA(renderedWord);
                    break;
                case MongolCode.Uni.LHA:
                    handleLHA(renderedWord, i, charAbove);
                    break;
                case MongolCode.Uni.ZHI:
                    handleZHI(renderedWord);
                    break;
                case MongolCode.Uni.CHI:
                    handleCHI(renderedWord);
                    break;
                case MongolCode.Uni.NNBS:
                    handleNNBS(renderedWord);
                    break;
                case MongolCode.Uni.MONGOLIAN_NIRUGU:
                    handleNirugu(renderedWord);
                    break;
                case MongolCode.Uni.ZWJ:
                    handleZWJ(renderedWord);
                    break;
                case MongolCode.Uni.ZWNJ:
                    handleZWNJ(renderedWord);
                    break;
                default:
                    // any extra MVS characters are ignored
            }

            charBelow = currentChar;
            charBelowFvs = fvs;
            fvs = 0;
        }

        return renderedWord.toString();
    }

    private void handleA(StringBuilder renderedWord, char charAbove) {
        gender = MongolCode.Gender.MASCULINE;
        switch (location) {
            case ISOLATE:
                if (fvs == MongolCode.Uni.FVS1) {
                    renderedWord.insert(0, MongolCode.Glyph.ISOL_A_FVS1);     // left sweeping tail
                } else {
                    renderedWord.insert(0, MongolCode.Glyph.ISOL_A);          // normal
                }
                break;
            case INITIAL:
                if (isSuffix) {
                    renderedWord.insert(0, MongolCode.Glyph.MEDI_A_FVS2);     // A of ACHA   *** suffix rule ***
                } else {
                    renderedWord.insert(0, MongolCode.Glyph.INIT_A);          // normal
                }
                break;
            case MEDIAL:
                if (fvs == MongolCode.Uni.FVS1) {
                    renderedWord.insert(0, MongolCode.Glyph.MEDI_A_FVS1);     // 2 teeth
                } else if (fvs == MongolCode.Uni.FVS2) {
                    renderedWord.insert(0, MongolCode.Glyph.MEDI_A_FVS2);     // A of ACHA suffix
                } else {
                    if (isRoundLetter(charAbove)) {
                        renderedWord.insert(0, MongolCode.Glyph.MEDI_A_BP);   // After BPFK
                    } else {
                        renderedWord.insert(0, MongolCode.Glyph.MEDI_A);      // normal
                    }
                }
                glyphShapeBelow = Shape.TOOTH;
                break;
            case FINAL:

                if (fvs == MongolCode.Uni.FVS1) {
                    renderedWord.insert(0, MongolCode.Glyph.FINA_A_FVS1);     // left sweeping tail
                    glyphShapeBelow = Shape.STEM;
                } else {
                    if (isRoundLetter(charAbove)) {
                        renderedWord.insert(0, MongolCode.Glyph.FINA_A_BP);   // after BPFK
                        glyphShapeBelow = Shape.TOOTH;
                    } else if (charAbove == MongolCode.Uni.MVS) {
                        renderedWord.insert(0, MongolCode.Glyph.FINA_A_MVS);  // MVS
                        glyphShapeBelow = Shape.STEM;
                    } else {
                        renderedWord.insert(0, MongolCode.Glyph.FINA_A);      // normal
                        glyphShapeBelow = Shape.STEM;
                    }
                }
                break;
        }
    }

    private void handleE(StringBuilder renderedWord, char charAbove) {
        gender = MongolCode.Gender.FEMININE;
        switch (location) {
            case ISOLATE:
                renderedWord.insert(0, MongolCode.Glyph.ISOL_E);              // normal
                break;
            case INITIAL:
                if (fvs == MongolCode.Uni.FVS1) {
                    renderedWord.insert(0, MongolCode.Glyph.INIT_E_FVS1);     // double tooth
                } else {
                    renderedWord.insert(0, MongolCode.Glyph.INIT_E);          // normal
                }
                break;
            case MEDIAL:
                if (isRoundLetterIncludingQG(charAbove)) {
                    renderedWord.insert(0, MongolCode.Glyph.MEDI_E_BP);       // After BPFK
                } else {
                    renderedWord.insert(0, MongolCode.Glyph.MEDI_E);          // normal
                }
                glyphShapeBelow = Shape.TOOTH;
                break;
            case FINAL:

                if (fvs == MongolCode.Uni.FVS1) {
                    renderedWord.insert(0, MongolCode.Glyph.FINA_E_FVS1);     // left sweeping tail
                    glyphShapeBelow = Shape.STEM;
                } else {
                    if (isRoundLetterIncludingQG(charAbove)) {
                        renderedWord.insert(0, MongolCode.Glyph.FINA_E_BP);   // after BPFK
                        glyphShapeBelow = Shape.TOOTH;
                    } else if (charAbove == MongolCode.Uni.MVS) {
                        renderedWord.insert(0, MongolCode.Glyph.FINA_E_MVS);  // MVS
                        glyphShapeBelow = Shape.STEM;
                    } else {
                        renderedWord.insert(0, MongolCode.Glyph.FINA_E);      // normal
                        glyphShapeBelow = Shape.STEM;
                    }
                }
                break;
        }
    }

    private void handleI(StringBuilder renderedWord,
                         int positionInWord,
                         char charAbove,
                         char charBelow) {
        switch (location) {
            case ISOLATE:
                if (isSuffix) {
                    renderedWord.insert(0, MongolCode.Glyph.ISOL_I_SUFFIX);           // I  *** suffix rule ***
                } else {
                    renderedWord.insert(0, MongolCode.Glyph.ISOL_I);                  // normal
                }
                break;
            case INITIAL:
                if (isSuffix && charBelow == MongolCode.Uni.YA) {
                    renderedWord.insert(0, MongolCode.Glyph.MEDI_I_SUFFIX);           // I of IYEN   *** suffix rule ***
                } else {
                    renderedWord.insert(0, MongolCode.Glyph.INIT_I);                  // normal
                }
                break;
            case MEDIAL:

                // FVS 1: one short, one long tooth
                if (fvs == MongolCode.Uni.FVS1) {
                    renderedWord.insert(0, MongolCode.Glyph.MEDI_I_FVS1);
                    break;
                }

                // FVS 2:  Used to override context for NAIMA single tooth I
                // (Undefined in Unicode 10.0)
                if (fvs == MongolCode.Uni.FVS2) {
                    renderedWord.insert(0, MongolCode.Glyph.MEDI_I);    // normal
                    break;
                }

                // After BPFK
                if (isRoundLetterIncludingQG(charAbove)) {
                    renderedWord.insert(0, MongolCode.Glyph.MEDI_I_BP);
                    break;
                }

                // *** AI, EI, OI, UI, OEI, UEI
                // medial double tooth I diphthong rule ***
                if (contextCallsForDoubleToothI(positionInWord, charAbove, charBelow)) {
                    renderedWord.insert(0, MongolCode.Glyph.MEDI_I_DOUBLE_TOOTH); // double tooth
                    break;
                }

                // normal single tooth I
                renderedWord.insert(0, MongolCode.Glyph.MEDI_I);
                break;
            case FINAL:
                if (isRoundLetterIncludingQG(charAbove)) {
                    renderedWord.insert(0, MongolCode.Glyph.FINA_I_BP);               // after BPFK
                } else {
                    renderedWord.insert(0, MongolCode.Glyph.FINA_I);                  // normal
                }
                break;
        }
        glyphShapeBelow = Shape.TOOTH;
    }

    private boolean contextCallsForDoubleToothI(int positionInWord, char charAbove, char charBelow) {
        if (charBelow == MongolCode.Uni.I) return false;
        if (charAbove == MongolCode.Uni.A ||
                charAbove == MongolCode.Uni.E ||
                charAbove == MongolCode.Uni.O ||
                charAbove == MongolCode.Uni.U) return true;
        // or non long toothed OE/UE
        return ((charAbove == MongolCode.Uni.OE ||
                        charAbove == MongolCode.Uni.UE) &&
                        !needsLongToothU(inputWord, positionInWord - 1));
    }

    private void handleO(StringBuilder renderedWord, char charAbove) {
        gender = MongolCode.Gender.MASCULINE;
        switch (location) {
            case ISOLATE:
                if (isSuffix) {
                    renderedWord.insert(0, MongolCode.Glyph.FINA_O);                  // O suffix   *** suffix rule ***
                } else {
                    renderedWord.insert(0, MongolCode.Glyph.ISOL_O);                  // normal
                }
                break;
            case INITIAL:
                if (isSuffix) {
                    renderedWord.insert(0, MongolCode.Glyph.MEDI_O_BP);               // O of OO suffix   *** suffix rule ***
                } else {
                    renderedWord.insert(0, MongolCode.Glyph.INIT_O);                  // normal
                }
                break;
            case MEDIAL:
                if (fvs == MongolCode.Uni.FVS1) {
                    renderedWord.insert(0, MongolCode.Glyph.MEDI_O_FVS1);             // tooth + O
                } else {
                    if (isRoundLetter(charAbove)) {
                        renderedWord.insert(0, MongolCode.Glyph.MEDI_O_BP);           // After BPFK
                    } else {
                        renderedWord.insert(0, MongolCode.Glyph.MEDI_O);              // normal
                    }
                }
                break;
            case FINAL:
                if (fvs == MongolCode.Uni.FVS1) {
                    renderedWord.insert(0, MongolCode.Glyph.FINA_O_FVS1);             // round o
                } else {
                    if (isRoundLetter(charAbove)) {
                        renderedWord.insert(0, MongolCode.Glyph.FINA_O_BP);           // After BPFK
                    } else {
                        renderedWord.insert(0, MongolCode.Glyph.FINA_O);              // normal
                    }
                }
                break;
        }
        glyphShapeBelow = Shape.STEM;
    }

    private void handleU(StringBuilder renderedWord, char charAbove) {
        gender = MongolCode.Gender.MASCULINE;
        switch (location) {
            case ISOLATE:
                if (isSuffix) {
                    renderedWord.insert(0, MongolCode.Glyph.FINA_U);                  // O suffix   *** suffix rule ***
                } else {
                    renderedWord.insert(0, MongolCode.Glyph.ISOL_U);                  // normal
                }
                break;
            case INITIAL:
                if (isSuffix) {
                    renderedWord.insert(0, MongolCode.Glyph.MEDI_U_BP);               // U of UU suffix   *** suffix rule ***
                } else {
                    renderedWord.insert(0, MongolCode.Glyph.INIT_U);                  // normal
                }
                break;
            case MEDIAL:
                if (fvs == MongolCode.Uni.FVS1) {
                    renderedWord.insert(0, MongolCode.Glyph.MEDI_U_FVS1);             // tooth + O
                } else {
                    if (isRoundLetter(charAbove)) {
                        renderedWord.insert(0, MongolCode.Glyph.MEDI_U_BP);           // After BPFK
                    } else {
                        renderedWord.insert(0, MongolCode.Glyph.MEDI_U);              // normal
                    }
                }
                break;
            case FINAL:
                if (fvs == MongolCode.Uni.FVS1) {
                    renderedWord.insert(0, MongolCode.Glyph.FINA_U_FVS1);             // round o
                } else {
                    if (isRoundLetter(charAbove)) {
                        renderedWord.insert(0, MongolCode.Glyph.FINA_U_BP);           // After BPFK
                    } else {
                        renderedWord.insert(0, MongolCode.Glyph.FINA_U);              // normal
                    }
                }
                break;
        }
        glyphShapeBelow = Shape.STEM;
    }

    private void handleOE(StringBuilder renderedWord, int positionInWord, char charAbove) {
        gender = MongolCode.Gender.FEMININE;
        switch (location) {
            case ISOLATE:
                if (isSuffix) {
                    renderedWord.insert(0, MongolCode.Glyph.FINA_OE);                 // O suffix   *** suffix rule ***
                } else {
                    renderedWord.insert(0, MongolCode.Glyph.ISOL_OE);                 // normal
                }
                break;
            case INITIAL:
                if (isSuffix) {
                    renderedWord.insert(0, MongolCode.Glyph.MEDI_OE_BP);              // O of OO suffix   *** suffix rule ***
                } else {
                    renderedWord.insert(0, MongolCode.Glyph.INIT_OE);                 // normal
                }
                break;
            case MEDIAL:
                if (fvs == MongolCode.Uni.FVS1) {
                    if (isRoundLetterIncludingQG(charAbove)) {
                        renderedWord.insert(0, MongolCode.Glyph.MEDI_OE_FVS1_BP);     // first syllable long tooth OE after BPFK
                    } else {
                        renderedWord.insert(0, MongolCode.Glyph.MEDI_OE_FVS1);        // first syllable long tooth OE
                    }
                } else if (fvs == MongolCode.Uni.FVS2) {
                    renderedWord.insert(0, MongolCode.Glyph.MEDI_OE_FVS2);            // extra tooth for 2 part name
                } else {
                    if (needsLongToothU(inputWord, positionInWord)) {
                        // *** first syllable long tooth rule (except in suffix) ***
                        if (isRoundLetterIncludingQG(charAbove)) {
                            renderedWord.insert(0, MongolCode.Glyph.MEDI_OE_FVS1_BP); // first syllable long tooth UE after BPFK
                        } else {
                            renderedWord.insert(0, MongolCode.Glyph.MEDI_OE_FVS1);    // first syllable long tooth UE
                        }
                    } else if (isRoundLetterIncludingQG(charAbove)) {
                        renderedWord.insert(0, MongolCode.Glyph.MEDI_OE_BP);          // After BPFK
                    } else {
                        renderedWord.insert(0, MongolCode.Glyph.MEDI_OE);             // normal
                    }
                }
                break;
            case FINAL:
                if (fvs == MongolCode.Uni.FVS1) {
                    if (isRoundLetterIncludingQG(charAbove)) {
                        renderedWord.insert(0, MongolCode.Glyph.FINA_OE_FVS1_BP);     // round o with tail after BPFK
                    } else {
                        renderedWord.insert(0, MongolCode.Glyph.FINA_OE_FVS1);        // round o with tail
                    }
                } else {
                    if (isRoundLetterIncludingQG(charAbove)) {
                        renderedWord.insert(0, MongolCode.Glyph.FINA_OE_BP);          // After BPFK
                    } else {
                        renderedWord.insert(0, MongolCode.Glyph.FINA_OE);             // normal
                    }
                }
                break;
        }
        glyphShapeBelow = Shape.STEM;
    }

    private void handleUE(StringBuilder renderedWord, int positionInWord, char charAbove) {
        gender = MongolCode.Gender.FEMININE;
        switch (location) {
            case ISOLATE:
                if (isSuffix) {
                    renderedWord.insert(0, MongolCode.Glyph.FINA_UE);                 // O suffix   *** suffix rule ***
                } else if (fvs == MongolCode.Uni.FVS1) {
                    renderedWord.insert(0, MongolCode.Glyph.ISOL_UE_FVS1);            // like E+UE
                } else {
                    renderedWord.insert(0, MongolCode.Glyph.ISOL_UE);                 // normal
                }
                break;
            case INITIAL:
                if (isSuffix) {
                    renderedWord.insert(0, MongolCode.Glyph.MEDI_UE_BP);              // U of UU suffix   *** suffix rule ***
                } else {
                    renderedWord.insert(0, MongolCode.Glyph.INIT_UE);                 // normal
                }
                break;
            case MEDIAL:
                if (fvs == MongolCode.Uni.FVS1) {
                    if (isRoundLetterIncludingQG(charAbove)) {
                        renderedWord.insert(0, MongolCode.Glyph.MEDI_UE_FVS1_BP);     // first syllable long tooth UE after BPFK
                    } else {
                        renderedWord.insert(0, MongolCode.Glyph.MEDI_UE_FVS1);        // first syllable long tooth UE
                    }
                } else if (fvs == MongolCode.Uni.FVS2) {
                    renderedWord.insert(0, MongolCode.Glyph.MEDI_UE_FVS2);            // extra tooth for 2 part name
                } else {
                    if (needsLongToothU(inputWord, positionInWord)) {
                        // *** first syllable long tooth rule (except in suffix) ***
                        if (isRoundLetterIncludingQG(charAbove)) {
                            renderedWord.insert(0, MongolCode.Glyph.MEDI_UE_FVS1_BP); // first syllable long tooth UE after BPFK
                        } else {
                            renderedWord.insert(0, MongolCode.Glyph.MEDI_UE_FVS1);    // first syllable long tooth UE
                        }
                    } else if (isRoundLetterIncludingQG(charAbove)) {
                        renderedWord.insert(0, MongolCode.Glyph.MEDI_UE_BP);          // After BPFK
                    } else {
                        renderedWord.insert(0, MongolCode.Glyph.MEDI_UE);             // normal
                    }
                }
                break;
            case FINAL:
                if (fvs == MongolCode.Uni.FVS1) {
                    if (isRoundLetterIncludingQG(charAbove)) {
                        renderedWord.insert(0, MongolCode.Glyph.FINA_UE_FVS1_BP);     // round o with tail after BPFK
                    } else {
                        renderedWord.insert(0, MongolCode.Glyph.FINA_UE_FVS1);        // round o with tail
                    }
                } else {
                    if (isRoundLetterIncludingQG(charAbove)) {
                        renderedWord.insert(0, MongolCode.Glyph.FINA_UE_BP);          // After BPFK
                    } else {
                        renderedWord.insert(0, MongolCode.Glyph.FINA_UE);             // normal
                    }
                }
                break;
        }
        glyphShapeBelow = Shape.STEM;
    }

    private void handleEE(StringBuilder renderedWord) {
        gender = MongolCode.Gender.FEMININE;
        switch (location) {
            case ISOLATE:
                renderedWord.insert(0, MongolCode.Glyph.ISOL_EE);                      // normal
                break;
            case INITIAL:
                renderedWord.insert(0, MongolCode.Glyph.INIT_EE);                      // normal
                break;
            case MEDIAL:
                renderedWord.insert(0, MongolCode.Glyph.MEDI_EE);                      // normal
                break;
            case FINAL:
                renderedWord.insert(0, MongolCode.Glyph.FINA_EE);                      // normal
                break;
        }
        glyphShapeBelow = Shape.TOOTH;
    }

    private void handleNA(StringBuilder renderedWord, int positionInWord,
                          char charBelow, char charBelowFvs) {

        switch (location) {
            case ISOLATE:
                renderedWord.insert(0, MongolCode.Glyph.ISOL_NA);                      // normal
                break;
            case INITIAL:
                if (fvs == MongolCode.Uni.FVS1) {
                    renderedWord.insert(0, MongolCode.Glyph.INIT_NA_FVS1_STEM);        // non-dotted
                } else {
                    if (glyphShapeBelow == Shape.STEM) {
                        renderedWord.insert(0, MongolCode.Glyph.INIT_NA_STEM);        // normal stem
                    } else {
                        renderedWord.insert(0, MongolCode.Glyph.INIT_NA_TOOTH);       // normal tooth
                    }
                }
                break;
            case MEDIAL:

                if (fvs == MongolCode.Uni.FVS1) {
                    if (glyphShapeBelow == Shape.STEM) {
                        renderedWord.insert(0, MongolCode.Glyph.MEDI_NA_FVS1_STEM);    // dotted stem
                    } else {
                        renderedWord.insert(0, MongolCode.Glyph.MEDI_NA_FVS1_TOOTH);   // dotted tooth
                    }
                    glyphShapeBelow = Shape.TOOTH;
                } else if (fvs == MongolCode.Uni.FVS2) {
                    renderedWord.insert(0, MongolCode.Glyph.MEDI_NA_FVS2);             // MVS
                    glyphShapeBelow = Shape.STEM;
                } else if (fvs == MongolCode.Uni.FVS3) {
                    renderedWord.insert(0, MongolCode.Glyph.MEDI_NA_FVS3);             // tod script
                    glyphShapeBelow = Shape.TOOTH;
                } else {
                    // *** dot N before vowel rule ***
                    if (MongolCode.isVowel(charBelow)) {
                        // *** don't dot N if final letter before vowel of compound name ***
                        if (positionInWord < length - 2 && MongolCode.isFVS(inputWord.charAt(positionInWord + 2)) &&
                                isTwoPartNameInitialVowel(charBelow, charBelowFvs)) {
                            // This will work for names whose second part starts with
                            // A, I, O, U, OE, and UE. But it won't work if it starts
                            // with E or EE because there are no second medial (FVS1)
                            // forms for these letters. A user could insert a ZWJ but
                            // they are unlikely to know that.
                            if (glyphShapeBelow == Shape.STEM) {
                                renderedWord.insert(0, MongolCode.Glyph.MEDI_NA_STEM);    // non-dotted stem
                            } else {
                                renderedWord.insert(0, MongolCode.Glyph.MEDI_NA_TOOTH);   // non-dotted tooth
                            }
                        } else {
                            if (glyphShapeBelow == Shape.STEM) {
                                renderedWord.insert(0, MongolCode.Glyph.MEDI_NA_FVS1_STEM);    // dotted stem
                            } else {
                                renderedWord.insert(0, MongolCode.Glyph.MEDI_NA_FVS1_TOOTH);   // dotted tooth
                            }
                        }
                    } else {
                        if (glyphShapeBelow == Shape.STEM) {
                            renderedWord.insert(0, MongolCode.Glyph.MEDI_NA_STEM);    // normal non-dotted stem
                        } else {
                            renderedWord.insert(0, MongolCode.Glyph.MEDI_NA_TOOTH);   // normal non-dotted tooth
                        }
                    }
                    glyphShapeBelow = Shape.TOOTH;
                }
                break;
            case FINAL:
                if (charBelow == MongolCode.Uni.MVS) {
                    renderedWord.insert(0, MongolCode.Glyph.MEDI_NA_FVS2);             // MVS
                } else {
                    renderedWord.insert(0, MongolCode.Glyph.FINA_NA);                  // normal
                }
                glyphShapeBelow = Shape.STEM;
                break;
        }
    }

    private void handleANG(StringBuilder renderedWord) {
        switch (location) {
            case ISOLATE:
                renderedWord.insert(0, MongolCode.Glyph.ISOL_ANG);                      // normal
                break;
            case INITIAL:
                if (glyphShapeBelow == Shape.ROUND) {
                    renderedWord.insert(0, MongolCode.Glyph.INIT_ANG_ROUND);            // before round
                } else if (glyphShapeBelow == Shape.STEM) {
                    renderedWord.insert(0, MongolCode.Glyph.INIT_ANG_STEM);             // before stem
                } else {
                    renderedWord.insert(0, MongolCode.Glyph.INIT_ANG_TOOTH);            // tooth tooth
                }
                break;
            case MEDIAL:
                if (glyphShapeBelow == Shape.ROUND) {
                    renderedWord.insert(0, MongolCode.Glyph.MEDI_ANG_ROUND);            // before round
                } else if (glyphShapeBelow == Shape.STEM) {
                    renderedWord.insert(0, MongolCode.Glyph.MEDI_ANG_STEM);             // before stem
                } else {
                    renderedWord.insert(0, MongolCode.Glyph.MEDI_ANG_TOOTH);            // tooth tooth
                }
                break;
            case FINAL:
                renderedWord.insert(0, MongolCode.Glyph.FINA_ANG);                      // normal
                break;
        }
        glyphShapeBelow = Shape.TOOTH;
    }

    private void handleBA(StringBuilder renderedWord, char charBelow) {
        switch (location) {
            case ISOLATE:
                renderedWord.insert(0, MongolCode.Glyph.ISOL_BA);                       // normal
                break;
            case INITIAL:
                if (isOuVowel(charBelow)) {
                    renderedWord.insert(0, MongolCode.Glyph.INIT_BA_OU);                // OU
                } else if (glyphShapeBelow == Shape.STEM) {
                    renderedWord.insert(0, MongolCode.Glyph.INIT_BA_STEM);              // stem
                } else {
                    renderedWord.insert(0, MongolCode.Glyph.INIT_BA);                   // normal
                }
                break;
            case MEDIAL:
                if (isOuVowel(charBelow)) {
                    renderedWord.insert(0, MongolCode.Glyph.MEDI_BA_OU);                // OU
                } else if (glyphShapeBelow == Shape.STEM) {
                    renderedWord.insert(0, MongolCode.Glyph.MEDI_BA_STEM);              // stem
                } else {
                    renderedWord.insert(0, MongolCode.Glyph.MEDI_BA_TOOTH);                   // normal
                }
                break;
            case FINAL:
                if (fvs == MongolCode.Uni.FVS1) {
                    renderedWord.insert(0, MongolCode.Glyph.FINA_BA_FVS1);              // o with left sweep
                } else {
                    renderedWord.insert(0, MongolCode.Glyph.FINA_BA);                   // normal
                }
                break;
        }
        glyphShapeBelow = Shape.STEM;
    }

    private void handlePA(StringBuilder renderedWord, char charBelow) {
        switch (location) {
            case ISOLATE:
                renderedWord.insert(0, MongolCode.Glyph.ISOL_PA);                       // normal
                break;
            case INITIAL:
                if (isOuVowel(charBelow)) {
                    renderedWord.insert(0, MongolCode.Glyph.INIT_PA_OU);                // OU
                } else if (glyphShapeBelow == Shape.STEM) {
                    renderedWord.insert(0, MongolCode.Glyph.INIT_PA_STEM);              // stem
                } else {
                    renderedWord.insert(0, MongolCode.Glyph.INIT_PA);                   // normal
                }
                break;
            case MEDIAL:
                if (isOuVowel(charBelow)) {
                    renderedWord.insert(0, MongolCode.Glyph.MEDI_PA_OU);                // OU
                } else if (glyphShapeBelow == Shape.STEM) {
                    renderedWord.insert(0, MongolCode.Glyph.MEDI_PA_STEM);              // stem
                } else {
                    renderedWord.insert(0, MongolCode.Glyph.MEDI_PA_TOOTH);                   // normal
                }
                break;
            case FINAL:
                renderedWord.insert(0, MongolCode.Glyph.FINA_PA);                       // normal
                break;
        }
        glyphShapeBelow = Shape.STEM;
    }

    private void handleQA(StringBuilder renderedWord, int positionInWord, char charAbove, char charBelow) {
        switch (location) {
            case ISOLATE:
                if (fvs == MongolCode.Uni.FVS1) {
                    renderedWord.insert(0, MongolCode.Glyph.ISOL_QA_FVS1);             // dotted feminine
                } else {
                    renderedWord.insert(0, MongolCode.Glyph.ISOL_QA);                  // normal
                }
                break;
            case INITIAL:
                if (fvs == MongolCode.Uni.FVS1) {
                    if (MongolCode.isFeminineVowel(charBelow) || charBelow == MongolCode.Uni.I) {
                        if (isOuVowel(charBelow)) {
                            renderedWord.insert(0, MongolCode.Glyph.INIT_QA_FVS1_FEM_OU);   // dotted feminine for OU
                        } else {
                            renderedWord.insert(0, MongolCode.Glyph.INIT_QA_FVS1_FEM);      // dotted feminine
                        }
                    } else {
                        if (glyphShapeBelow == Shape.STEM) {
                            renderedWord.insert(0, MongolCode.Glyph.INIT_QA_FVS1_STEM);     // dotted masculine stem
                        } else {
                            renderedWord.insert(0, MongolCode.Glyph.INIT_QA_FVS1_TOOTH);    // dotted masculine tooth
                        }
                    }
                } else {
                    if (MongolCode.isFeminineVowel(charBelow) || charBelow == MongolCode.Uni.I) {
                        if (isOuVowel(charBelow)) {
                            renderedWord.insert(0, MongolCode.Glyph.INIT_QA_FEM_OU);   // feminine for OU
                        } else {
                            renderedWord.insert(0, MongolCode.Glyph.INIT_QA_FEM);      // feminine
                        }
                    } else {
                        if (glyphShapeBelow == Shape.STEM) {
                            renderedWord.insert(0, MongolCode.Glyph.INIT_QA_STEM);     // normal (masculine) stem
                        } else {
                            renderedWord.insert(0, MongolCode.Glyph.INIT_QA_TOOTH);    // normal (masculine) tooth
                        }
                    }
                }
                break;
            case MEDIAL:

                if (fvs == MongolCode.Uni.FVS1) {
                    if (MongolCode.isFeminineVowel(charBelow) || charBelow == MongolCode.Uni.I) {
                        if (isOuVowel(charBelow)) {
                            renderedWord.insert(0, MongolCode.Glyph.MEDI_QA_FVS1_FEM_OU);   // dotted feminine for OU
                        } else {
                            renderedWord.insert(0, MongolCode.Glyph.MEDI_QA_FVS1_FEM);      // dotted feminine
                        }
                        glyphShapeBelow = Shape.ROUND;
                    } else if (MongolCode.isMasculineVowel(charBelow)) {
                        renderedWord.insert(0, MongolCode.Glyph.MEDI_QA_FVS1);         // dotted double tooth masculine
                        glyphShapeBelow = Shape.TOOTH;
                    } else { // consonant
                        if (gender == MongolCode.Gender.NEUTER) {
                            gender = getWordGenderAboveIndex(positionInWord, inputWord);
                        }
                        if (gender == MongolCode.Gender.FEMININE) {
                            renderedWord.insert(0, MongolCode.Glyph.MEDI_QA_FEM_CONSONANT_DOTTED);   // dotted feminine final before consonant
                        } else {
                            renderedWord.insert(0, MongolCode.Glyph.MEDI_QA_FVS1);     // dotted double tooth masculine
                        }
                        glyphShapeBelow = Shape.TOOTH;
                    }
                } else if (fvs == MongolCode.Uni.FVS2) {
                    renderedWord.insert(0, MongolCode.Glyph.MEDI_QA_FVS2);             // dotted MVS
                    glyphShapeBelow = Shape.TOOTH;
                } else if (fvs == MongolCode.Uni.FVS3) {
                    renderedWord.insert(0, MongolCode.Glyph.MEDI_QA_FVS3);             // MVS
                    glyphShapeBelow = Shape.TOOTH;
                } else {
                    if (MongolCode.isFeminineVowel(charBelow) || charBelow == MongolCode.Uni.I) {
                        if (isOuVowel(charBelow)) {
                            renderedWord.insert(0, MongolCode.Glyph.MEDI_QA_FEM_OU);   // feminine for OU
                        } else {
                            renderedWord.insert(0, MongolCode.Glyph.MEDI_QA_FEM);      // feminine
                        }
                        glyphShapeBelow = Shape.ROUND;
                    } else if (MongolCode.isMasculineVowel(charBelow)) {
                        if (glyphShapeBelow == Shape.STEM) {
                            renderedWord.insert(0, MongolCode.Glyph.MEDI_QA_STEM);     // normal stem (masculine double tooth)
                        } else {
                            renderedWord.insert(0, MongolCode.Glyph.MEDI_QA_TOOTH);    // normal tooth (masculine double tooth)
                        }
                        glyphShapeBelow = Shape.TOOTH;
                    } else { // consonant
                        // does medial QA before a consonant ever happen
                        // in a real word?
                        if (gender == MongolCode.Gender.NEUTER) {
                            gender = getWordGenderAboveIndex(positionInWord, inputWord);
                        }
                        if (gender == MongolCode.Gender.FEMININE ||
                                (gender == MongolCode.Gender.NEUTER
                                        && charAbove == MongolCode.Uni.I)) {
                            renderedWord.insert(0, MongolCode.Glyph.MEDI_QA_FEM_CONSONANT);   // feminine final before consonant
                        } else {
                            if (glyphShapeBelow == Shape.STEM) {
                                renderedWord.insert(0, MongolCode.Glyph.MEDI_QA_STEM);        // normal stem (masculine double tooth)
                            } else {
                                renderedWord.insert(0, MongolCode.Glyph.MEDI_QA_TOOTH);       // normal tooth (masculine double tooth)
                            }
                        }
                        glyphShapeBelow = Shape.TOOTH;
                    }
                }
                break;
            case FINAL:
                renderedWord.insert(0, MongolCode.Glyph.FINA_QA);                       // normal
                glyphShapeBelow = Shape.TOOTH;
                break;
        }
    }

    private void handleGA(StringBuilder renderedWord, int positionInWord, char charAbove, char charBelow) {

        switch (location) {
            case ISOLATE:
                renderedWord.insert(0, MongolCode.Glyph.ISOL_GA);                      // normal
                break;
            case INITIAL:
                if (fvs == MongolCode.Uni.FVS1) {
                    if (glyphShapeBelow == Shape.STEM) {
                        renderedWord.insert(0, MongolCode.Glyph.INIT_GA_FVS1_STEM);    // undotted masculine stem
                    } else {
                        renderedWord.insert(0, MongolCode.Glyph.INIT_GA_FVS1_TOOTH);   // undotted masculine tooth
                    }
                    // TODO feminine forms are not handled.
                    // What are they supposed to look like?
                } else {
                    if (MongolCode.isFeminineVowel(charBelow) || charBelow == MongolCode.Uni.I) {
                        if (isOuVowel(charBelow)) {
                            renderedWord.insert(0, MongolCode.Glyph.INIT_GA_FEM_OU);   // feminine for OU
                        } else {
                            renderedWord.insert(0, MongolCode.Glyph.INIT_GA_FEM);      // feminine
                        }
                    } else {
                        if (MongolCode.isConsonant(charBelow)) {
                            // *** feminine form before consonant in foreign words ***
                            renderedWord.insert(0, MongolCode.Glyph.INIT_GA_FEM);      // feminine
                        } else if (glyphShapeBelow == Shape.STEM) {
                            renderedWord.insert(0, MongolCode.Glyph.INIT_GA_STEM);     // normal (masculine) stem
                        } else {
                            renderedWord.insert(0, MongolCode.Glyph.INIT_GA_TOOTH);    // normal (masculine) tooth
                        }
                    }
                }
                break;
            case MEDIAL:

                if (fvs == MongolCode.Uni.FVS1) {
                    if (glyphShapeBelow == Shape.STEM) {
                        renderedWord.insert(0, MongolCode.Glyph.MEDI_GA_FVS1_STEM);    // dotted masculine stem
                    } else {
                        renderedWord.insert(0, MongolCode.Glyph.MEDI_GA_FVS1_TOOTH);   // dotted masculine tooth
                    }
                    glyphShapeBelow = Shape.TOOTH;
                } else if (fvs == MongolCode.Uni.FVS2) {
                    renderedWord.insert(0, MongolCode.Glyph.MEDI_GA_FVS2);             // MVS
                    glyphShapeBelow = Shape.TOOTH;
                } else if (fvs == MongolCode.Uni.FVS3) {
                    if (glyphShapeBelow == Shape.STEM) {
                        renderedWord.insert(0, MongolCode.Glyph.MEDI_GA_FVS3_STEM);    // feminine before consonant stem
                    } else {
                        renderedWord.insert(0, MongolCode.Glyph.MEDI_GA_FVS3_TOOTH);   // feminine before consonant tooth
                    }
                    glyphShapeBelow = Shape.TOOTH;
                } else { // no FVS, just apply context rules
                    if (MongolCode.isFeminineVowel(charBelow) || charBelow == MongolCode.Uni.I) {
                        // *** feminine GA rule ***
                        if (isOuVowel(charBelow)) {
                            renderedWord.insert(0, MongolCode.Glyph.MEDI_GA_FEM_OU);   // feminine for OU
                        } else {
                            renderedWord.insert(0, MongolCode.Glyph.MEDI_GA_FEM);      // feminine
                        }
                        glyphShapeBelow = Shape.ROUND;
                    } else if (MongolCode.isMasculineVowel(charBelow)) {
                        // *** dotted masculine GA rule ***
                        if (glyphShapeBelow == Shape.STEM) {
                            renderedWord.insert(0, MongolCode.Glyph.MEDI_GA_FVS1_STEM);   // dotted masculine stem
                        } else {
                            renderedWord.insert(0, MongolCode.Glyph.MEDI_GA_FVS1_TOOTH);  // dotted masculine tooth
                        }
                        glyphShapeBelow = Shape.TOOTH;
                    } else { // consonant
                        if (gender == MongolCode.Gender.NEUTER) {
                            gender = getWordGenderAboveIndex(positionInWord, inputWord);
                        }
                        // *** medial GA before consonant rule ***
                        if (gender == MongolCode.Gender.FEMININE ||
                                // Defaulting to feminine form for I
                                (gender == MongolCode.Gender.NEUTER
                                        && charAbove == MongolCode.Uni.I) ||
                                // treat a G between two consonants as feminine (as in ANGGLI)
                                // (but not after Y because Y is like I)
                                (charAbove != MongolCode.Uni.YA &&
                                        (MongolCode.isConsonant(charAbove) ||
                                                charAbove == MongolCode.Uni.ZWJ))) {

                            if (glyphShapeBelow == Shape.STEM) {
                                renderedWord.insert(0, MongolCode.Glyph.MEDI_GA_FVS3_STEM);    // feminine before consonant stem
                            } else {
                                renderedWord.insert(0, MongolCode.Glyph.MEDI_GA_FEM);      // feminine
                                //renderedWord.insert(0, Glyph.MEDI_GA_FVS3_TOOTH);   // feminine before consonant tooth
                            }
                            glyphShapeBelow = Shape.ROUND;
                        } else {
                            renderedWord.insert(0, MongolCode.Glyph.MEDI_GA);       // normal (undotted masculine)
                            glyphShapeBelow = Shape.TOOTH;
                        }

                    }
                }
                break;
            case FINAL:
                if (fvs == MongolCode.Uni.FVS1) {
                    renderedWord.insert(0, MongolCode.Glyph.FINA_GA_FVS1);          // masculine context override (same as default)
                } else if (fvs == MongolCode.Uni.FVS2) {
                    renderedWord.insert(0, MongolCode.Glyph.FINA_GA_FVS2);          // feminine
                } else if (charBelow == MongolCode.Uni.MVS) {
                    renderedWord.insert(0, MongolCode.Glyph.MEDI_GA_FVS2);          // MVS
                } else {
                    gender = getWordGenderAboveIndex(positionInWord, inputWord);
                    if (gender == MongolCode.Gender.MASCULINE ||
                            charAbove == MongolCode.Uni.ZWJ) {
                        renderedWord.insert(0, MongolCode.Glyph.FINA_GA);           // masculine
                    } else {
                        // Defaulting to feminine form for I
                        renderedWord.insert(0, MongolCode.Glyph.FINA_GA_FVS2);      // feminine
                    }
                }
                glyphShapeBelow = Shape.TOOTH;
                break;
        }
    }

    private void handleMA(StringBuilder renderedWord, int positionInWord,
                          char charAbove, char charBelow) {
        switch (location) {
            case ISOLATE:
                renderedWord.insert(0, MongolCode.Glyph.ISOL_MA);                       // normal
                break;
            case INITIAL:
                if (glyphShapeBelow == Shape.STEM) {
                    renderedWord.insert(0, MongolCode.Glyph.INIT_MA_STEM_LONG);         // stem
                } else {
                    renderedWord.insert(0, MongolCode.Glyph.INIT_MA_TOOTH);             // tooth
                }
                break;
            case MEDIAL:
                if (isRoundLetter(charAbove) ||
                        charAbove == MongolCode.Uni.ANG) {
                    renderedWord.insert(0, MongolCode.Glyph.MEDI_MA_BP);                // tail extended for round letter
                } else if (charAbove == MongolCode.Uni.QA ||
                        charAbove == MongolCode.Uni.GA) {
                    if (gender == MongolCode.Gender.NEUTER) {
                        gender = getWordGenderAboveIndex(positionInWord, inputWord);
                    }
                    if (gender != MongolCode.Gender.MASCULINE ||
                            // feminine G when between consonants
                            (positionInWord > 1 && charAbove == MongolCode.Uni.GA &&
                                    (MongolCode.isConsonant(inputWord.charAt(positionInWord - 2)) ||
                                            inputWord.charAt(positionInWord - 2) == MongolCode.Uni.ZWJ))) {
                        renderedWord.insert(0, MongolCode.Glyph.MEDI_MA_BP);            // tail extended for round letter
                    } else {
                        renderedWord.insert(0, MongolCode.Glyph.MEDI_MA_TOOTH);         // tooth
                    }
                } else if (glyphShapeBelow != Shape.TOOTH ||
                        // use the longer stem if M/L is below
                        charBelow == MongolCode.Uni.MA || charBelow == MongolCode.Uni.LA ||
                        charBelow == MongolCode.Uni.LHA) {
                    renderedWord.insert(0, MongolCode.Glyph.MEDI_MA_STEM_LONG);         // stem
                } else {
                    renderedWord.insert(0, MongolCode.Glyph.MEDI_MA_TOOTH);             // tooth
                }
                glyphShapeBelow = Shape.TOOTH;
                break;
            case FINAL:
                renderedWord.insert(0, MongolCode.Glyph.FINA_MA);                       // normal
                glyphShapeBelow = Shape.STEM;
                break;
        }
    }

    private void handleLA(StringBuilder renderedWord, int positionInWord,
                          char charAbove, char charBelow) {

        switch (location) {
            case ISOLATE:
                renderedWord.insert(0, MongolCode.Glyph.ISOL_LA);                       // normal
                break;
            case INITIAL:
                if (glyphShapeBelow == Shape.STEM) {
                    renderedWord.insert(0, MongolCode.Glyph.INIT_LA_STEM_LONG);         // stem
                } else {
                    renderedWord.insert(0, MongolCode.Glyph.INIT_LA_TOOTH);             // tooth
                }
                break;
            case MEDIAL:
                if (isRoundLetter(charAbove) ||
                        charAbove == MongolCode.Uni.ANG) {
                    renderedWord.insert(0, MongolCode.Glyph.MEDI_LA_BP);                // tail extended for round letter
                } else if (charAbove == MongolCode.Uni.QA ||
                        charAbove == MongolCode.Uni.GA) {
                    if (gender == MongolCode.Gender.NEUTER) {
                        gender = getWordGenderAboveIndex(positionInWord, inputWord);
                    }
                    if (gender != MongolCode.Gender.MASCULINE ||
                            // feminine G when between consonants
                            (positionInWord > 1 && charAbove == MongolCode.Uni.GA &&
                                    (MongolCode.isConsonant(inputWord.charAt(positionInWord - 2)) ||
                                            inputWord.charAt(positionInWord - 2) == MongolCode.Uni.ZWJ))) {
                        renderedWord.insert(0, MongolCode.Glyph.MEDI_LA_BP);            // tail extended for round letter
                    } else {
                        renderedWord.insert(0, MongolCode.Glyph.MEDI_LA_TOOTH);         // tooth
                    }
                } else if (glyphShapeBelow != Shape.TOOTH ||
                        // use the longer stem if M/L is below
                        charBelow == MongolCode.Uni.MA || charBelow == MongolCode.Uni.LA ||
                        charBelow == MongolCode.Uni.LHA) {
                    renderedWord.insert(0, MongolCode.Glyph.MEDI_LA_STEM_LONG);         // stem
                } else {
                    renderedWord.insert(0, MongolCode.Glyph.MEDI_LA_TOOTH);             // tooth
                }
                glyphShapeBelow = Shape.TOOTH;
                break;
            case FINAL:
                renderedWord.insert(0, MongolCode.Glyph.FINA_LA);                       // normal
                glyphShapeBelow = Shape.STEM;
                break;
        }
    }

    private void handleSA(StringBuilder renderedWord) {
        switch (location) {

            case ISOLATE:
                renderedWord.insert(0, MongolCode.Glyph.ISOL_SA);                       // normal
                break;
            case INITIAL:
                if (glyphShapeBelow == Shape.STEM) {
                    renderedWord.insert(0, MongolCode.Glyph.INIT_SA_STEM);              // stem
                } else {
                    renderedWord.insert(0, MongolCode.Glyph.INIT_SA_TOOTH);             // tooth
                }
                break;
            case MEDIAL:
                if (glyphShapeBelow == Shape.STEM) {
                    renderedWord.insert(0, MongolCode.Glyph.MEDI_SA_STEM);              // stem
                } else {
                    renderedWord.insert(0, MongolCode.Glyph.MEDI_SA_TOOTH);             // tooth
                }
                glyphShapeBelow = Shape.TOOTH;
                break;
            case FINAL:
                glyphShapeBelow = Shape.TOOTH;
                if (fvs == MongolCode.Uni.FVS1) {
                    renderedWord.insert(0, MongolCode.Glyph.FINA_SA_FVS1);              // short tail
                    glyphShapeBelow = Shape.STEM;
                } else if (fvs == MongolCode.Uni.FVS2) {
                    renderedWord.insert(0, MongolCode.Glyph.FINA_SA_FVS2);              // (missing glyph)
                } else {
                    renderedWord.insert(0, MongolCode.Glyph.FINA_SA);                   // normal
                }
                break;
        }
    }

    private void handleSHA(StringBuilder renderedWord) {
        switch (location) {
            case ISOLATE:
                renderedWord.insert(0, MongolCode.Glyph.ISOL_SHA);                       // normal
                break;
            case INITIAL:
                if (glyphShapeBelow == Shape.STEM) {
                    renderedWord.insert(0, MongolCode.Glyph.INIT_SHA_STEM);              // stem
                } else {
                    renderedWord.insert(0, MongolCode.Glyph.INIT_SHA_TOOTH);             // tooth
                }
                break;
            case MEDIAL:
                if (glyphShapeBelow == Shape.STEM) {
                    renderedWord.insert(0, MongolCode.Glyph.MEDI_SHA_STEM);              // stem
                } else {
                    renderedWord.insert(0, MongolCode.Glyph.MEDI_SHA_TOOTH);             // tooth
                }
                break;
            case FINAL:
                renderedWord.insert(0, MongolCode.Glyph.FINA_SHA);                       // normal
                break;
        }
        glyphShapeBelow = Shape.TOOTH;
    }

    private void handleTA(StringBuilder renderedWord) {
        switch (location) {
            case ISOLATE:
                renderedWord.insert(0, MongolCode.Glyph.ISOL_TA);                       // normal
                break;
            case INITIAL:
                if (glyphShapeBelow == Shape.STEM) {
                    renderedWord.insert(0, MongolCode.Glyph.INIT_TA_STEM);              // stem
                } else {
                    renderedWord.insert(0, MongolCode.Glyph.INIT_TA_TOOTH);             // tooth
                }
                break;
            case MEDIAL:
                if (fvs == MongolCode.Uni.FVS1) {
                    if (glyphShapeBelow == Shape.STEM) {
                        renderedWord.insert(0, MongolCode.Glyph.MEDI_TA_FVS1_STEM);     // stem
                    } else {
                        renderedWord.insert(0, MongolCode.Glyph.MEDI_TA_FVS1_TOOTH);    // tooth
                    }
                    glyphShapeBelow = Shape.STEM;
                } else {
                    renderedWord.insert(0, MongolCode.Glyph.MEDI_TA);                   // normal
                    glyphShapeBelow = Shape.TOOTH;
                }
                break;
            case FINAL:
                renderedWord.insert(0, MongolCode.Glyph.FINA_TA);                       // normal
                glyphShapeBelow = Shape.STEM;
                break;
        }
    }

    private void handleDA(StringBuilder renderedWord, char charBelow) {
        switch (location) {
            case ISOLATE:
                renderedWord.insert(0, MongolCode.Glyph.ISOL_DA);                       // normal
                break;
            case INITIAL:
                if (fvs == MongolCode.Uni.FVS1 || isSuffix) {
                    renderedWord.insert(0, MongolCode.Glyph.INIT_DA_FVS1);              // left slanting
                } else {
                    if (glyphShapeBelow == Shape.STEM) {
                        renderedWord.insert(0, MongolCode.Glyph.INIT_DA_STEM);          // stem
                    } else {
                        renderedWord.insert(0, MongolCode.Glyph.INIT_DA_TOOTH);         // tooth
                    }
                }
                break;
            case MEDIAL:
                if (fvs == MongolCode.Uni.FVS1) {
                    renderedWord.insert(0, MongolCode.Glyph.MEDI_DA_FVS1);              // left slanting
                    glyphShapeBelow = Shape.TOOTH;
                } else {
                    if (MongolCode.isVowel(charBelow)) {
                        renderedWord.insert(0, MongolCode.Glyph.MEDI_DA_FVS1);          // left slanting
                        glyphShapeBelow = Shape.TOOTH;
                    } else {
                        renderedWord.insert(0, MongolCode.Glyph.MEDI_DA);               // normal (before consonant)
                        glyphShapeBelow = Shape.STEM;
                    }
                }
                break;
            case FINAL:
                if (fvs == MongolCode.Uni.FVS1) {
                    renderedWord.insert(0, MongolCode.Glyph.FINA_DA_FVS1);              // left slanting
                    glyphShapeBelow = Shape.TOOTH;
                } else {
                    renderedWord.insert(0, MongolCode.Glyph.FINA_DA);                   // normal (like o-n)
                    glyphShapeBelow = Shape.STEM;
                }
                break;
        }
    }

    private void handleCHA(StringBuilder renderedWord) {
        switch (location) {
            case ISOLATE:
                renderedWord.insert(0, MongolCode.Glyph.ISOL_CHA);                       // normal
                break;
            case INITIAL:
                renderedWord.insert(0, MongolCode.Glyph.INIT_CHA);                       // normal
                break;
            case MEDIAL:
                renderedWord.insert(0, MongolCode.Glyph.MEDI_CHA);                       // normal
                break;
            case FINAL:
                renderedWord.insert(0, MongolCode.Glyph.FINA_CHA);                       // normal
                break;
        }
        glyphShapeBelow = Shape.STEM;
    }

    private void handleJA(StringBuilder renderedWord, char charBelow) {

        switch (location) {
            case ISOLATE:
                renderedWord.insert(0, MongolCode.Glyph.ISOL_JA);                       // normal
                break;
            case INITIAL:
                if (glyphShapeBelow == Shape.STEM) {
                    renderedWord.insert(0, MongolCode.Glyph.INIT_JA_STEM);              // stem
                } else {
                    // The Qimad font seems to be broken here
                    // so temporarily disabling this glyph
                    // TODO fix the font, or remove it, or just use
                    // this alternate glyph.
                    //renderedWord.insert(0, Glyph.INIT_JA_TOOTH);             // tooth
                    renderedWord.insert(0, MongolCode.Glyph.INIT_JA_STEM);
                }
                break;
            case MEDIAL:
                if (fvs == MongolCode.Uni.FVS1) {
                    renderedWord.insert(0, MongolCode.Glyph.MEDI_JA_FVS1);              // MVS
                    glyphShapeBelow = Shape.TOOTH;
                } else {
                    renderedWord.insert(0, MongolCode.Glyph.MEDI_JA);                   // normal (before consonant)
                    glyphShapeBelow = Shape.STEM;
                }
                break;
            case FINAL:
                if (charBelow == MongolCode.Uni.MVS) {
                    renderedWord.insert(0, MongolCode.Glyph.MEDI_JA_FVS1);              // MVS
                    glyphShapeBelow = Shape.TOOTH;
                } else {
                    renderedWord.insert(0, MongolCode.Glyph.FINA_JA);                   // normal
                    glyphShapeBelow = Shape.STEM;
                }
                break;
        }
    }

    private void handleYA(StringBuilder renderedWord, int positionInWord,
                          char charAbove, char charBelow) {

        switch (location) {
            case ISOLATE:
                renderedWord.insert(0, MongolCode.Glyph.ISOL_YA);                  // normal
                break;
            case INITIAL:
                if (isSuffix && charBelow == MongolCode.Uni.I) {
                    renderedWord.insert(0, MongolCode.Glyph.MEDI_YA);         // suffix - no hook
                } else if (fvs == MongolCode.Uni.FVS1) {
                    renderedWord.insert(0, MongolCode.Glyph.INIT_YA_FVS1);         // no hook
                } else {
                    renderedWord.insert(0, MongolCode.Glyph.INIT_YA);              // hook
                }
                break;
            case MEDIAL:
                if (fvs == MongolCode.Uni.FVS1) {
                    renderedWord.insert(0, MongolCode.Glyph.MEDI_YA_FVS1);         // hook
                } else if (isSuffix && charAbove == MongolCode.Uni.I) {
                    // *** no hook after Y (as in IYEN and IYER) ***
                    renderedWord.insert(0, MongolCode.Glyph.MEDI_YA);             // suffix - no hook
                } else {

                    // *** AYI, EYI, OYI, UYI, OEYI, UEYI
                    // medial double tooth YI diphthong rule ***
                    // Also do this for consonant below.
                    if (needsLongToothU(inputWord, positionInWord - 1) || charAbove == MongolCode.Uni.I) {
                        if (charBelow == MongolCode.Uni.I || MongolCode.isConsonant(charBelow)) {
                            renderedWord.insert(0, MongolCode.Glyph.MEDI_YA);           // no hook
                        } else {
                            renderedWord.insert(0, MongolCode.Glyph.MEDI_YA_FVS1);      // hook
                        }
                    } else if (MongolCode.isVowel(charAbove)) {
                        if (charBelow == MongolCode.Uni.I) {
                            renderedWord.insert(0, MongolCode.Glyph.MEDI_YA);          // no hook
                        } else if (MongolCode.isConsonant(charBelow)) {
                            renderedWord.insert(0, MongolCode.Glyph.MEDI_I_DOUBLE_TOOTH); // double tooth
                        } else {
                            renderedWord.insert(0, MongolCode.Glyph.MEDI_YA_FVS1);          // hook
                        }
                    } else {
                        renderedWord.insert(0, MongolCode.Glyph.MEDI_YA_FVS1);          // hook
                    }
                }
                break;
            case FINAL:
                renderedWord.insert(0, MongolCode.Glyph.FINA_YA);                  // normal
                break;
        }
        glyphShapeBelow = Shape.TOOTH;
    }

    private void handleRA(StringBuilder renderedWord) {
        switch (location) {
            case ISOLATE:
                renderedWord.insert(0, MongolCode.Glyph.ISOL_RA);                  // normal
                break;
            case INITIAL:
                if (glyphShapeBelow == Shape.STEM) {
                    renderedWord.insert(0, MongolCode.Glyph.INIT_RA_STEM);         // stem
                } else {
                    renderedWord.insert(0, MongolCode.Glyph.INIT_RA_TOOTH);        // tooth
                }
                break;
            case MEDIAL:
                if (glyphShapeBelow == Shape.STEM) {
                    renderedWord.insert(0, MongolCode.Glyph.MEDI_RA_STEM);         // stem
                } else {
                    renderedWord.insert(0, MongolCode.Glyph.MEDI_RA_TOOTH);        // tooth
                }
                glyphShapeBelow = Shape.TOOTH;
                break;
            case FINAL:
                renderedWord.insert(0, MongolCode.Glyph.FINA_RA);                  // normal
                glyphShapeBelow = Shape.STEM;
                break;
        }
    }

    private void handleWA(StringBuilder renderedWord, char charBelow) {
        switch (location) {
            case ISOLATE:
                renderedWord.insert(0, MongolCode.Glyph.ISOL_WA);                  // normal
                break;
            case INITIAL:
                renderedWord.insert(0, MongolCode.Glyph.INIT_WA);                  // normal
                break;
            case MEDIAL:
                renderedWord.insert(0, MongolCode.Glyph.MEDI_WA);              // normal
                glyphShapeBelow = Shape.TOOTH;
                break;
            case FINAL:
                if (fvs == MongolCode.Uni.FVS1) {
                    renderedWord.insert(0, MongolCode.Glyph.FINA_WA_FVS1);         // round like final o
                    glyphShapeBelow = Shape.STEM;
                } else if (charBelow == MongolCode.Uni.MVS) {
                    renderedWord.insert(0, MongolCode.Glyph.FINA_WA_FVS1);         // MVS
                    glyphShapeBelow = Shape.STEM;
                } else {
                    renderedWord.insert(0, MongolCode.Glyph.FINA_WA);              // normal
                    glyphShapeBelow = Shape.TOOTH;
                }
                break;
        }
    }

    private void handleFA(StringBuilder renderedWord, char charBelow) {
        switch (location) {
            case ISOLATE:
                renderedWord.insert(0, MongolCode.Glyph.ISOL_FA);                       // normal
                break;
            case INITIAL:
                if (isOuVowel(charBelow)) {
                    renderedWord.insert(0, MongolCode.Glyph.INIT_FA_OU);                // OU
                } else if (glyphShapeBelow == Shape.STEM) {
                    renderedWord.insert(0, MongolCode.Glyph.INIT_FA_STEM);              // stem
                } else {
                    renderedWord.insert(0, MongolCode.Glyph.INIT_FA);                   // normal
                }
                break;
            case MEDIAL:
                if (isOuVowel(charBelow)) {
                    renderedWord.insert(0, MongolCode.Glyph.MEDI_FA_OU);                // OU
                } else if (glyphShapeBelow == Shape.STEM) {
                    renderedWord.insert(0, MongolCode.Glyph.MEDI_FA_STEM);              // stem
                } else {
                    renderedWord.insert(0, MongolCode.Glyph.MEDI_FA_TOOTH);             // normal
                }
                break;
            case FINAL:
                renderedWord.insert(0, MongolCode.Glyph.FINA_FA);                       // normal
                break;
        }
        glyphShapeBelow = Shape.STEM;
    }

    private void handleKA(StringBuilder renderedWord, char charBelow) {
        switch (location) {
            case ISOLATE:
                renderedWord.insert(0, MongolCode.Glyph.ISOL_KA);                       // normal
                break;
            case INITIAL:
                if (isOuVowel(charBelow)) {
                    renderedWord.insert(0, MongolCode.Glyph.INIT_KA_OU);                // OU
                } else {
                    renderedWord.insert(0, MongolCode.Glyph.INIT_KA);                   // normal
                }
                break;
            case MEDIAL:
                if (isOuVowel(charBelow)) {
                    renderedWord.insert(0, MongolCode.Glyph.MEDI_KA_OU);                // OU
                } else if (glyphShapeBelow == Shape.STEM) {
                    renderedWord.insert(0, MongolCode.Glyph.MEDI_KA_STEM);              // stem
                } else {
                    renderedWord.insert(0, MongolCode.Glyph.MEDI_KA_TOOTH);                   // normal
                }
                break;
            case FINAL:
                renderedWord.insert(0, MongolCode.Glyph.FINA_KA);                       // normal
                break;
        }
        glyphShapeBelow = Shape.STEM;
    }

    private void handleKHA(StringBuilder renderedWord, char charBelow) {
        switch (location) {
            case ISOLATE:
                renderedWord.insert(0, MongolCode.Glyph.ISOL_KHA);                       // normal
                break;
            case INITIAL:
                if (isOuVowel(charBelow)) {
                    renderedWord.insert(0, MongolCode.Glyph.INIT_KHA_OU);                // OU
                } else {
                    renderedWord.insert(0, MongolCode.Glyph.INIT_KHA);                   // normal
                }
                break;
            case MEDIAL:
                if (isOuVowel(charBelow)) {
                    renderedWord.insert(0, MongolCode.Glyph.MEDI_KHA_OU);                // OU
                } else if (glyphShapeBelow == Shape.STEM) {
                    renderedWord.insert(0, MongolCode.Glyph.MEDI_KHA_STEM);              // stem
                } else {
                    renderedWord.insert(0, MongolCode.Glyph.MEDI_KHA_TOOTH);             // normal
                }
                break;
            case FINAL:
                renderedWord.insert(0, MongolCode.Glyph.FINA_KHA);                       // normal
                break;
        }
        glyphShapeBelow = Shape.STEM;
    }

    private void handleTSA(StringBuilder renderedWord) {
        switch (location) {
            case ISOLATE:
                renderedWord.insert(0, MongolCode.Glyph.ISOL_TSA);                       // normal
                break;
            case INITIAL:
                renderedWord.insert(0, MongolCode.Glyph.INIT_TSA);                       // normal
                break;
            case MEDIAL:
                renderedWord.insert(0, MongolCode.Glyph.MEDI_TSA);                       // normal
                break;
            case FINAL:
                renderedWord.insert(0, MongolCode.Glyph.FINA_TSA);                       // normal
                break;
        }
        glyphShapeBelow = Shape.STEM;
    }

    private void handleZA(StringBuilder renderedWord) {
        switch (location) {
            case ISOLATE:
                renderedWord.insert(0, MongolCode.Glyph.ISOL_ZA);                        // normal
                break;
            case INITIAL:
                renderedWord.insert(0, MongolCode.Glyph.INIT_ZA);                        // normal
                break;
            case MEDIAL:
                renderedWord.insert(0, MongolCode.Glyph.MEDI_ZA);                        // normal
                break;
            case FINAL:
                renderedWord.insert(0, MongolCode.Glyph.FINA_ZA);                        // normal
                break;
        }
        glyphShapeBelow = Shape.STEM;
    }

    private void handleHAA(StringBuilder renderedWord) {
        switch (location) {
            case ISOLATE:
                renderedWord.insert(0, MongolCode.Glyph.ISOL_HAA);                        // normal
                break;
            case INITIAL:
                renderedWord.insert(0, MongolCode.Glyph.INIT_HAA);                        // normal
                break;
            case MEDIAL:
                renderedWord.insert(0, MongolCode.Glyph.MEDI_HAA);                        // normal
                break;
            case FINAL:
                renderedWord.insert(0, MongolCode.Glyph.FINA_HAA);                        // normal
                break;
        }
        glyphShapeBelow = Shape.TOOTH;
    }

    private void handleZRA(StringBuilder renderedWord) {
        switch (location) {
            case ISOLATE:
                renderedWord.insert(0, MongolCode.Glyph.ISOL_ZRA);                        // normal
                break;
            case INITIAL:
                renderedWord.insert(0, MongolCode.Glyph.INIT_ZRA);                        // normal
                break;
            case MEDIAL:
                renderedWord.insert(0, MongolCode.Glyph.MEDI_ZRA);                        // normal
                break;
            case FINAL:
                renderedWord.insert(0, MongolCode.Glyph.FINA_ZRA);                        // normal
                break;
        }
        glyphShapeBelow = Shape.STEM; // ROUND didn't look very good
    }

    private void handleLHA(StringBuilder renderedWord, int positionInWord, char charAbove) {
        switch (location) {
            case ISOLATE:
                renderedWord.insert(0, MongolCode.Glyph.ISOL_LHA);                       // normal
                break;
            case INITIAL:
                renderedWord.insert(0, MongolCode.Glyph.INIT_LHA);                       // normal
                break;
            case MEDIAL:
                if (isRoundLetter(charAbove) ||
                        charAbove == MongolCode.Uni.ANG) {
                    renderedWord.insert(0, MongolCode.Glyph.MEDI_LHA_BP);                // tail extended for round letter
                } else if (charAbove == MongolCode.Uni.QA ||
                        charAbove == MongolCode.Uni.GA) {
                    if (gender == MongolCode.Gender.NEUTER) {
                        gender = getWordGenderAboveIndex(positionInWord, inputWord);
                    }
                    if (gender == MongolCode.Gender.FEMININE) {
                        renderedWord.insert(0, MongolCode.Glyph.MEDI_LHA_BP);            // tail extended for round letter
                    } else {
                        renderedWord.insert(0, MongolCode.Glyph.MEDI_LHA);               // normal
                    }
                } else {
                    renderedWord.insert(0, MongolCode.Glyph.MEDI_LHA);                   // normal
                }
                break;
            case FINAL:
                renderedWord.insert(0, MongolCode.Glyph.FINA_LHA);                       // normal
                break;
        }
        glyphShapeBelow = Shape.TOOTH;
    }

    private void handleZHI(StringBuilder renderedWord) {
        switch (location) {
            case ISOLATE:
                renderedWord.insert(0, MongolCode.Glyph.ISOL_ZHI);                        // normal
                break;
            case INITIAL:
                renderedWord.insert(0, MongolCode.Glyph.INIT_ZHI);                        // normal
                break;
            case MEDIAL:
                renderedWord.insert(0, MongolCode.Glyph.MEDI_ZHI);                        // normal
                break;
            case FINAL:
                renderedWord.insert(0, MongolCode.Glyph.FINA_ZHI);                        // normal
                break;
        }
        glyphShapeBelow = Shape.TOOTH;
    }

    private void handleCHI(StringBuilder renderedWord) {
        switch (location) {
            case ISOLATE:
                renderedWord.insert(0, MongolCode.Glyph.ISOL_CHI);                        // normal
                break;
            case INITIAL:
                renderedWord.insert(0, MongolCode.Glyph.INIT_CHI);                        // normal
                break;
            case MEDIAL:
                renderedWord.insert(0, MongolCode.Glyph.MEDI_CHI);                        // normal
                break;
            case FINAL:
                renderedWord.insert(0, MongolCode.Glyph.FINA_CHI);                        // normal
                break;
        }
        glyphShapeBelow = Shape.STEM;
    }

    private void handleNNBS(StringBuilder renderedWord) {
        renderedWord.insert(0, MongolCode.Uni.NNBS);
    }

    private void handleNirugu(StringBuilder renderedWord) {
        renderedWord.insert(0, MongolCode.Glyph.NIRUGU);
        glyphShapeBelow = Shape.STEM;
    }

    // Even though ZWJ and ZWNJ are invisible, inserting them into the rendered text in order to
    // simplify glyph-unicode indexing
    private void handleZWJ(StringBuilder renderedWord) {
        renderedWord.insert(0, MongolCode.Uni.ZWJ);
    }

    private void handleZWNJ(StringBuilder renderedWord) {
        renderedWord.insert(0, MongolCode.Uni.ZWNJ);
    }

    private boolean needsLongToothU(CharSequence word, int uIndex) {

        if (word.charAt(uIndex) != MongolCode.Uni.OE
                && word.charAt(uIndex) != MongolCode.Uni.UE) return false;

        if (uIndex == 0) return true;

        if (uIndex == 1) {
            if (MongolCode.isConsonant(word.charAt(0))) {
                // strange BUU exception
                if (BUU_EXCEPTION.contentEquals(word)) {
                    return false;
                }
                return true;
            }
        }

        if (uIndex == 2) {
            if (MongolCode.isConsonant(word.charAt(0)) && MongolCode.isFVS(word.charAt(1))) {
                return true;
            }
        }

        return false;
    }

    private boolean isRoundLetterIncludingQG(char character) {
        return (character == MongolCode.Uni.BA || character == MongolCode.Uni.PA || character == MongolCode.Uni.QA ||
                character == MongolCode.Uni.GA || character == MongolCode.Uni.FA || character == MongolCode.Uni.KA ||
                character == MongolCode.Uni.KHA);
    }

    private boolean isRoundLetter(char character) {
        return (character == MongolCode.Uni.BA || character == MongolCode.Uni.PA || character == MongolCode.Uni.FA ||
                character == MongolCode.Uni.KA || character == MongolCode.Uni.KHA);
    }

    private boolean isTwoPartNameInitialVowel(char vowel, char fvs) {
        // XXX There is no way to recognize initial E or EE
        return (vowel == MongolCode.Uni.A && fvs == MongolCode.Uni.FVS1) ||
                (vowel == MongolCode.Uni.I && fvs == MongolCode.Uni.FVS1) ||
                (vowel == MongolCode.Uni.O && fvs == MongolCode.Uni.FVS1) ||
                (vowel == MongolCode.Uni.U && fvs == MongolCode.Uni.FVS1) ||
                (vowel == MongolCode.Uni.OE && fvs == MongolCode.Uni.FVS3) ||
                (vowel == MongolCode.Uni.UE && fvs == MongolCode.Uni.FVS3);
    }

    private static boolean isOuVowel(char character) {
        return (character >= MongolCode.Uni.O && character <= MongolCode.Uni.UE);
    }

    // Starts at the end of the word and works up
    // if mixed genders only reports the first one from the bottom
    // returns null if word does not end in a valid Mongolian character
    static MongolCode.Gender getGender(CharSequence word) {
        // check that word is valid mongolian
        if (word == null || word.length() == 0) return null;
        int length = word.length();
        char lastChar = word.charAt(length - 1);
        if (!MongolCode.isMongolian(lastChar)) return null;
        return getWordGenderAboveIndex(length, word);
    }

    // assumes that word is valid mongolian
    // this starts at the index and works up
    // If there are mixed genders then only the first will be reported
    // (could add a Gender.MIXED form)
    private static MongolCode.Gender getWordGenderAboveIndex(int index, CharSequence word) {
        for (int i = index - 1; i >= 0; i--) {
            if (MongolCode.isMasculineVowel(word.charAt(i))) {
                return MongolCode.Gender.MASCULINE;
            } else if (MongolCode.isFeminineVowel(word.charAt(i))) {
                return MongolCode.Gender.FEMININE;
            }
        }
        return MongolCode.Gender.NEUTER;
    }
}
