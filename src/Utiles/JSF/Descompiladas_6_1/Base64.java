/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.JSF.Descompiladas_6_1;
import java.util.Arrays;

public class Base64 {

            private static final char CA[];
            private static final int IA[];


            public static final char[] encodeToChar(byte sArr[], boolean lineSep) {
/*  98*/        int sLen = sArr == null ? 0 : sArr.length;
/*  99*/        if (sLen == 0) {
/* 100*/            return new char[0];
                }
/* 102*/        int eLen = (sLen / 3) * 3;
/* 103*/        int cCnt = (sLen - 1) / 3 + 1 << 2;
/* 104*/        int dLen = cCnt + (lineSep ? (cCnt - 1) / 76 << 1 : 0);
/* 105*/        char dArr[] = new char[dLen];
/* 108*/        int s = 0;
/* 108*/        int d = 0;
/* 108*/        int cc = 0;
/* 108*/        do {
/* 108*/            if (s >= eLen) {
/* 110*/                break;
                    }
/* 110*/            int i = (sArr[s++] & 0xff) << 16 | (sArr[s++] & 0xff) << 8 | sArr[s++] & 0xff;
/* 113*/            dArr[d++] = CA[i >>> 18 & 0x3f];
/* 114*/            dArr[d++] = CA[i >>> 12 & 0x3f];
/* 115*/            dArr[d++] = CA[i >>> 6 & 0x3f];
/* 116*/            dArr[d++] = CA[i & 0x3f];
/* 119*/            if (lineSep && ++cc == 19 && d < dLen - 2) {
/* 120*/                dArr[d++] = '\r';
/* 121*/                dArr[d++] = '\n';
/* 122*/                cc = 0;
                    }
                } while (true);
/* 127*/        int left = sLen - eLen;
/* 128*/        if (left > 0) {
/* 130*/            int i = (sArr[eLen] & 0xff) << 10 | (left != 2 ? 0 : (sArr[sLen - 1] & 0xff) << 2);
/* 133*/            dArr[dLen - 4] = CA[i >> 12];
/* 134*/            dArr[dLen - 3] = CA[i >>> 6 & 0x3f];
/* 135*/            dArr[dLen - 2] = left != 2 ? '=' : CA[i & 0x3f];
/* 136*/            dArr[dLen - 1] = '=';
                }
/* 138*/        return dArr;
            }

            public static final byte[] decode(char sArr[]) {
/* 150*/        int sLen = sArr == null ? 0 : sArr.length;
/* 151*/        if (sLen == 0) {
/* 152*/            return new byte[0];
                }
/* 156*/        int sepCnt = 0;
/* 157*/        for (int i = 0; i < sLen; i++) {
/* 158*/            if (IA[sArr[i]] < 0) {
/* 159*/                sepCnt++;
                    }
                }

/* 162*/        if ((sLen - sepCnt) % 4 != 0) {
/* 163*/            return null;
                }
/* 165*/        int pad = 0;
/* 166*/        int i = sLen;
/* 166*/        do {
/* 166*/            if (i <= 1 || IA[sArr[--i]] > 0) {
/* 167*/                break;
                    }
/* 167*/            if (sArr[i] == '=') {
/* 168*/                pad++;
                    }
                } while (true);
/* 170*/        int len = ((sLen - sepCnt) * 6 >> 3) - pad;
/* 172*/        byte dArr[] = new byte[len];
/* 174*/        int s = 0;
/* 174*/        int d = 0;
/* 174*/        do {
/* 174*/            if (d >= len) {
/* 176*/                break;
                    }
/* 176*/            int ii = 0;
/* 177*/            for (int j = 0; j < 4; j++) {
/* 178*/                int c = IA[sArr[s++]];
/* 179*/                if (c >= 0) {
/* 180*/                    ii |= c << 18 - j * 6;
                        } else {
/* 182*/                    j--;
                        }
                    }

/* 185*/            dArr[d++] = (byte)(ii >> 16);
/* 186*/            if (d < len) {
/* 187*/                dArr[d++] = (byte)(ii >> 8);
/* 188*/                if (d < len) {
/* 189*/                    dArr[d++] = (byte)ii;
                        }
                    }
                } while (true);
/* 192*/        return dArr;
            }

            public static final byte[] decodeFast(char sArr[]) {
/* 207*/        int sLen = sArr.length;
/* 208*/        if (sLen == 0) {
/* 209*/            return new byte[0];
                }
/* 211*/        int sIx = 0;
                int eIx;
/* 211*/        for (eIx = sLen - 1; sIx < eIx && IA[sArr[sIx]] < 0; sIx++) { }
/* 218*/        for (; eIx > 0 && IA[sArr[eIx]] < 0; eIx--) { }
/* 222*/        int pad = sArr[eIx] != '=' ? 0 : ((int) (sArr[eIx - 1] != '=' ? 1 : 2));
/* 223*/        int cCnt = (eIx - sIx) + 1;
/* 224*/        int sepCnt = sLen <= 76 ? 0 : (sArr[76] != '\r' ? 0 : cCnt / 78) << 1;
/* 226*/        int len = ((cCnt - sepCnt) * 6 >> 3) - pad;
/* 227*/        byte dArr[] = new byte[len];
/* 230*/        int d = 0;
/* 231*/        int cc = 0;
/* 231*/        int eLen = (len / 3) * 3;
/* 231*/        do {
/* 231*/            if (d >= eLen) {
/* 233*/                break;
                    }
/* 233*/            int i = IA[sArr[sIx++]] << 18 | IA[sArr[sIx++]] << 12 | IA[sArr[sIx++]] << 6 | IA[sArr[sIx++]];
/* 236*/            dArr[d++] = (byte)(i >> 16);
/* 237*/            dArr[d++] = (byte)(i >> 8);
/* 238*/            dArr[d++] = (byte)i;
/* 241*/            if (sepCnt > 0 && ++cc == 19) {
/* 242*/                sIx += 2;
/* 243*/                cc = 0;
                    }
                } while (true);
/* 247*/        if (d < len) {
/* 249*/            int i = 0;
/* 250*/            for (int j = 0; sIx <= eIx - pad; j++) {
/* 251*/                i |= IA[sArr[sIx++]] << 18 - j * 6;
                    }

/* 253*/            for (int r = 16; d < len; r -= 8) {
/* 254*/                dArr[d++] = (byte)(i >> r);
                    }

                }
/* 257*/        return dArr;
            }

            public static final byte[] encodeToByte(byte sArr[], boolean lineSep) {
/* 273*/        return encodeToByte(sArr, 0, sArr == null ? 0 : sArr.length, lineSep);
            }

            public static final byte[] encodeToByte(byte sArr[], int sOff, int sLen, boolean lineSep) {
/* 288*/        if (sArr == null || sLen == 0) {
/* 289*/            return new byte[0];
                }
/* 291*/        int eLen = (sLen / 3) * 3;
/* 292*/        int cCnt = (sLen - 1) / 3 + 1 << 2;
/* 293*/        int dLen = cCnt + (lineSep ? (cCnt - 1) / 76 << 1 : 0);
/* 294*/        byte dArr[] = new byte[dLen];
/* 297*/        int s = sOff;
/* 297*/        int d = 0;
/* 297*/        int cc = 0;
/* 297*/        do {
/* 297*/            if (s >= sOff + eLen) {
/* 299*/                break;
                    }
/* 299*/            int i = (sArr[s++] & 0xff) << 16 | (sArr[s++] & 0xff) << 8 | sArr[s++] & 0xff;
/* 302*/            dArr[d++] = (byte)CA[i >>> 18 & 0x3f];
/* 303*/            dArr[d++] = (byte)CA[i >>> 12 & 0x3f];
/* 304*/            dArr[d++] = (byte)CA[i >>> 6 & 0x3f];
/* 305*/            dArr[d++] = (byte)CA[i & 0x3f];
/* 308*/            if (lineSep && ++cc == 19 && d < dLen - 2) {
/* 309*/                dArr[d++] = 13;
/* 310*/                dArr[d++] = 10;
/* 311*/                cc = 0;
                    }
                } while (true);
/* 316*/        int left = sLen - eLen;
/* 317*/        if (left > 0) {
/* 319*/            int i = (sArr[sOff + eLen] & 0xff) << 10 | (left != 2 ? 0 : (sArr[(sOff + sLen) - 1] & 0xff) << 2);
/* 322*/            dArr[dLen - 4] = (byte)CA[i >> 12];
/* 323*/            dArr[dLen - 3] = (byte)CA[i >>> 6 & 0x3f];
/* 324*/            dArr[dLen - 2] = left != 2 ? 61 : (byte)CA[i & 0x3f];
/* 325*/            dArr[dLen - 1] = 61;
                }
/* 327*/        return dArr;
            }

            public static final byte[] decode(byte sArr[]) {
/* 338*/        return decode(sArr, 0, sArr.length);
            }

            public static final byte[] decode(byte sArr[], int sOff, int sLen) {
/* 353*/        int sepCnt = 0;
/* 354*/        for (int i = 0; i < sLen; i++) {
/* 355*/            if (IA[sArr[sOff + i] & 0xff] < 0) {
/* 356*/                sepCnt++;
                    }
                }

/* 359*/        if ((sLen - sepCnt) % 4 != 0) {
/* 360*/            return null;
                }
/* 362*/        int pad = 0;
/* 363*/        int i = sLen;
/* 363*/        do {
/* 363*/            if (i <= 1 || IA[sArr[sOff + --i] & 0xff] > 0) {
/* 364*/                break;
                    }
/* 364*/            if (sArr[sOff + i] == 61) {
/* 365*/                pad++;
                    }
                } while (true);
/* 367*/        int len = ((sLen - sepCnt) * 6 >> 3) - pad;
/* 369*/        byte dArr[] = new byte[len];
/* 371*/        int s = 0;
/* 371*/        int d = 0;
/* 371*/        do {
/* 371*/            if (d >= len) {
/* 373*/                break;
                    }
/* 373*/            int ii = 0;
/* 374*/            for (int j = 0; j < 4; j++) {
/* 375*/                int c = IA[sArr[sOff + s++] & 0xff];
/* 376*/                if (c >= 0) {
/* 377*/                    ii |= c << 18 - j * 6;
                        } else {
/* 379*/                    j--;
                        }
                    }

/* 383*/            dArr[d++] = (byte)(ii >> 16);
/* 384*/            if (d < len) {
/* 385*/                dArr[d++] = (byte)(ii >> 8);
/* 386*/                if (d < len) {
/* 387*/                    dArr[d++] = (byte)ii;
                        }
                    }
                } while (true);
/* 391*/        return dArr;
            }

            public static final byte[] decodeFast(byte sArr[]) {
/* 407*/        int sLen = sArr.length;
/* 408*/        if (sLen == 0) {
/* 409*/            return new byte[0];
                }
/* 411*/        int sIx = 0;
                int eIx;
/* 411*/        for (eIx = sLen - 1; sIx < eIx && IA[sArr[sIx] & 0xff] < 0; sIx++) { }
/* 418*/        for (; eIx > 0 && IA[sArr[eIx] & 0xff] < 0; eIx--) { }
/* 422*/        int pad = sArr[eIx] != 61 ? 0 : ((int) (sArr[eIx - 1] != 61 ? 1 : 2));
/* 423*/        int cCnt = (eIx - sIx) + 1;
/* 424*/        int sepCnt = sLen <= 76 ? 0 : (sArr[76] != 13 ? 0 : cCnt / 78) << 1;
/* 426*/        int len = ((cCnt - sepCnt) * 6 >> 3) - pad;
/* 427*/        byte dArr[] = new byte[len];
/* 430*/        int d = 0;
/* 431*/        int cc = 0;
/* 431*/        int eLen = (len / 3) * 3;
/* 431*/        do {
/* 431*/            if (d >= eLen) {
/* 433*/                break;
                    }
/* 433*/            int i = IA[sArr[sIx++]] << 18 | IA[sArr[sIx++]] << 12 | IA[sArr[sIx++]] << 6 | IA[sArr[sIx++]];
/* 436*/            dArr[d++] = (byte)(i >> 16);
/* 437*/            dArr[d++] = (byte)(i >> 8);
/* 438*/            dArr[d++] = (byte)i;
/* 441*/            if (sepCnt > 0 && ++cc == 19) {
/* 442*/                sIx += 2;
/* 443*/                cc = 0;
                    }
                } while (true);
/* 447*/        if (d < len) {
/* 449*/            int i = 0;
/* 450*/            for (int j = 0; sIx <= eIx - pad; j++) {
/* 451*/                i |= IA[sArr[sIx++]] << 18 - j * 6;
                    }

/* 453*/            for (int r = 16; d < len; r -= 8) {
/* 454*/                dArr[d++] = (byte)(i >> r);
                    }

                }
/* 457*/        return dArr;
            }

            public static final String encodeToString(byte sArr[], boolean lineSep) {
/* 474*/        return new String(encodeToChar(sArr, lineSep));
            }

            public static final byte[] decode(String str) {
/* 488*/        int sLen = str == null ? 0 : str.length();
/* 489*/        if (sLen == 0) {
/* 490*/            return new byte[0];
                }
/* 494*/        int sepCnt = 0;
/* 495*/        for (int i = 0; i < sLen; i++) {
/* 496*/            if (IA[str.charAt(i)] < 0) {
/* 497*/                sepCnt++;
                    }
                }

/* 500*/        if ((sLen - sepCnt) % 4 != 0) {
/* 501*/            return null;
                }
/* 504*/        int pad = 0;
/* 505*/        int i = sLen;
/* 505*/        do {
/* 505*/            if (i <= 1 || IA[str.charAt(--i)] > 0) {
/* 506*/                break;
                    }
/* 506*/            if (str.charAt(i) == '=') {
/* 507*/                pad++;
                    }
                } while (true);
/* 509*/        int len = ((sLen - sepCnt) * 6 >> 3) - pad;
/* 511*/        byte dArr[] = new byte[len];
/* 513*/        int s = 0;
/* 513*/        int d = 0;
/* 513*/        do {
/* 513*/            if (d >= len) {
/* 515*/                break;
                    }
/* 515*/            int ii = 0;
/* 516*/            for (int j = 0; j < 4; j++) {
/* 517*/                int c = IA[str.charAt(s++)];
/* 518*/                if (c >= 0) {
/* 519*/                    ii |= c << 18 - j * 6;
                        } else {
/* 521*/                    j--;
                        }
                    }

/* 524*/            dArr[d++] = (byte)(ii >> 16);
/* 525*/            if (d < len) {
/* 526*/                dArr[d++] = (byte)(ii >> 8);
/* 527*/                if (d < len) {
/* 528*/                    dArr[d++] = (byte)ii;
                        }
                    }
                } while (true);
/* 531*/        return dArr;
            }

            public static final byte[] decodeFast(String s) {
/* 546*/        int sLen = s.length();
/* 547*/        if (sLen == 0) {
/* 548*/            return new byte[0];
                }
/* 550*/        int sIx = 0;
                int eIx;
/* 550*/        for (eIx = sLen - 1; sIx < eIx && IA[s.charAt(sIx) & 0xff] < 0; sIx++) { }
/* 557*/        for (; eIx > 0 && IA[s.charAt(eIx) & 0xff] < 0; eIx--) { }
/* 561*/        int pad = s.charAt(eIx) != '=' ? 0 : ((int) (s.charAt(eIx - 1) != '=' ? 1 : 2));
/* 562*/        int cCnt = (eIx - sIx) + 1;
/* 563*/        int sepCnt = sLen <= 76 ? 0 : (s.charAt(76) != '\r' ? 0 : cCnt / 78) << 1;
/* 565*/        int len = ((cCnt - sepCnt) * 6 >> 3) - pad;
/* 566*/        byte dArr[] = new byte[len];
/* 569*/        int d = 0;
/* 570*/        int cc = 0;
/* 570*/        int eLen = (len / 3) * 3;
/* 570*/        do {
/* 570*/            if (d >= eLen) {
/* 572*/                break;
                    }
/* 572*/            int i = IA[s.charAt(sIx++)] << 18 | IA[s.charAt(sIx++)] << 12 | IA[s.charAt(sIx++)] << 6 | IA[s.charAt(sIx++)];
/* 575*/            dArr[d++] = (byte)(i >> 16);
/* 576*/            dArr[d++] = (byte)(i >> 8);
/* 577*/            dArr[d++] = (byte)i;
/* 580*/            if (sepCnt > 0 && ++cc == 19) {
/* 581*/                sIx += 2;
/* 582*/                cc = 0;
                    }
                } while (true);
/* 586*/        if (d < len) {
/* 588*/            int i = 0;
/* 589*/            for (int j = 0; sIx <= eIx - pad; j++) {
/* 590*/                i |= IA[s.charAt(sIx++)] << 18 - j * 6;
                    }

/* 592*/            for (int r = 16; d < len; r -= 8) {
/* 593*/                dArr[d++] = (byte)(i >> r);
                    }

                }
/* 596*/        return dArr;
            }

            static  {
/*  75*/        CA = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();
/*  76*/        IA = new int[256];
/*  78*/        Arrays.fill(IA, -1);
/*  79*/        int i = 0;
/*  79*/        for (int iS = CA.length; i < iS; i++) {
/*  80*/            IA[CA[i]] = i;
                }

/*  81*/        IA[61] = 0;
            }
}
