package com.luna.wednesday.calculate.constant;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

/**
 * @author Tony
 */
public class HashModeConstant {
    // 难以统一命名，故无法一次性加进来，需要用时补充

    public static final int                  MD4                                          = 900;
    public static final String               MD4_NAME                                     = "MD4";

    public static final int                  MD5                                          = 0;
    public static final String               MD5_NAME                                     = "MD5";

    public static final int                  HALF_MD5                                     = 5100;
    public static final String               HALF_MD5_NAME                                = "Half MD5";

    public static final int                  SHA1                                         = 100;
    public static final String               SHA1_NAME                                    = "SHA1";

    public static final int                  SIPHASH                                      = 10100;
    public static final int                  WHIRLPOOL                                    = 6100;

    private static final int                 MD5_$PASS_$SALT                              = 10;
    private static final String              MD5_$PASS_$SALT_NAME                         = "md5($pass.$salt)";

    public static final int                  CHACHA20                                     = 15400;
    public static final int                  PHPASS                                       = 400;
    public static final int                  SCRYPT                                       = 8900;
    public static final int                  SKYPE                                        = 23;
    public static final int                  NETNTLMV1                                    = 5500;
    public static final int                  NETNTLMV2                                    = 5600;
    public static final int                  PHPS                                         = 2612;
    public static final int                  DRUPAL7                                      = 7900;
    public static final int                  OSCOMMERCE                                   = 21;
    public static final int                  PRESTASHOP                                   = 11000;
    public static final int                  TRIPCODE                                     = 16000;
    public static final int                  MEDIAWIKI_B_TYPE                             = 3711;
    public static final int                  OPENCART                                     = 13900;
    public static final int                  REDMINE                                      = 4521;
    public static final int                  PUNBB                                        = 4522;
    public static final int                  POSTGRESQL                                   = 12;
    public static final int                  MYSQL323                                     = 200;
    public static final int                  SYBASE_ASE                                   = 8000;
    public static final int                  HMAILSERVER                                  = 1421;
    public static final int                  CRC32                                        = 11500;

    public static final int                  LM                                           = 3000;
    public static final String               LM_NAME                                      = "LM";

    public static final int                  NTLM                                         = 1000;
    public static final String               NTLM_NAME                                    = "NTLM";

    public static final int                  DPAPI_MASTERKEY_FILE_V1                      = 15300;
    public static final int                  DPAPI_MASTERKEY_FILE_V2                      = 15900;
    public static final int                  CITRIX_NETSCALER                             = 8100;
    public static final int                  RACF                                         = 8500;
    public static final int                  GRUB_2                                       = 7200;
    public static final int                  RADMIN2                                      = 9900;
    public static final int                  ARUBAOS                                      = 125;
    public static final int                  PEOPLESOFT                                   = 133;
    public static final int                  PEOPLESOFT_PS_TOKEN                          = 13500;

    public static final int                  _7_ZIP                                       = 11600;
    public static final String               _7_ZIP_NAME                                  = "7-Zip";

    public static final int                  RAR3_HP                                      = 12500;
    public static final String               RAR3_HP_NAME                                 = "RAR3-hp";

    public static final int                  RAR5                                         = 13000;
    public static final String               RAR5_NAME                                    = "RAR5";

    public static final int                  AXCRYPT                                      = 13200;

    public static final int                  WINZIP                                       = 13600;
    public static final String               WINZIP_NAME                                  = "WinZip";

    public static final int                  ECRYPTFS                                     = 12200;
    public static final int                  LUKS                                         = 14600;
    public static final int                  FILEVAULT_2                                  = 16700;

    public static final int                  MS_OFFICE_2003_$0_$1_MD5_RC4                 = 9700;
    public static final String               MS_OFFICE_2003_$0_$1_MD5_RC4_NAME            =
        "MS Office <= 2003 $0/$1, MD5 + RC4";

    public static final int                  MS_OFFICE_2003_$0_$1_MD5_RC4_COLLIDER_1      = 9710;
    public static final String               MS_OFFICE_2003_$0_$1_MD5_RC4_COLLIDER_1_NAME =
        "MS Office <= 2003 $0/$1, MD5 + RC4, collider #1";

    public static final int                  MS_OFFICE_2003_$0_$1_MD5_RC4_COLLIDER_2      = 9720;
    public static final String               MS_OFFICE_2003_$0_$1_MD5_RC4_COLLIDER_2_NAME =
        "MS Office <= 2003 $0/$1, MD5 + RC4, collider #2";

    public static final int                  MS_OFFICE_2003_$3_$4_SHA1_RC4                = 9800;
    public static final String               MS_OFFICE_2003_$3_$4_SHA1_RC4_NAME           =
        "MS Office <= 2003 $3/$4, SHA1 + RC4";

    public static final int                  MS_OFFICE_2003_$3_SHA1_RC4_COLLIDER_1        = 9810;
    public static final String               MS_OFFICE_2003_$3_SHA1_RC4_COLLIDER_1_NAME   =
        "MS Office <= 2003 $3, SHA1 + RC4, collider #1";

    public static final int                  MS_OFFICE_2003_$3_SHA1_RC4_COLLIDER_2        = 9820;
    public static final String               MS_OFFICE_2003_$3_SHA1_RC4_COLLIDER_2_NAME   =
        "MS Office <= 2003 $3, SHA1 + RC4, collider #2";

    public static final int                  MS_OFFICE_2007                               = 9400;
    public static final String               MS_OFFICE_2007_NAME                          = "MS Office 2007";

    public static final int                  MS_OFFICE_2010                               = 9500;
    public static final String               MS_OFFICE_2010_NAME                          = "MS Office 2010";

    public static final int                  MS_OFFICE_2013                               = 9600;
    public static final String               MS_OFFICE_2013_NAME                          = "MS Office 2013";

    public static final int                  PDF_1_1_1_3_ACROBAT_2_4                      = 10400;
    public static final String               PDF_1_1_1_3_ACROBAT_2_4_NAME                 =
        "PDF 1.1 - 1.3 (Acrobat 2 - 4)";

    public static final int                  PDF_1_1_1_3_ACROBAT_2_4_COLLIDER_1           = 10410;
    public static final String               PDF_1_1_1_3_ACROBAT_2_4_COLLIDER_1_NAME      =
        "PDF 1.1 - 1.3 (Acrobat 2 - 4), collider #1";

    public static final int                  PDF_1_1_1_3_ACROBAT_2_4_COLLIDER_2           = 10420;
    public static final String               PDF_1_1_1_3_ACROBAT_2_4_COLLIDER_2_NAME      =
        "PDF 1.1 - 1.3 (Acrobat 2 - 4), collider #2";

    public static final int                  PDF_1_4_1_6_ACROBAT_5_8                      = 10500;
    public static final String               PDF_1_4_1_6_ACROBAT_5_8_NAME                 =
        "PDF 1.4 - 1.6 (Acrobat 5 - 8)";

    public static final int                  PDF_1_7_LEVEL_3_ACROBAT_9                    = 10600;
    public static final String               PDF_1_7_LEVEL_3_ACROBAT_9_NAME               =
        "PDF 1.7 Level 3 (Acrobat 9)";

    public static final int                  PDF_1_7_LEVEL_8_ACROBAT_10_11                = 10700;
    public static final String               PDF_1_7_LEVEL_8_ACROBAT_10_11_NAME           =
        "PDF 1.7 Level 8 (Acrobat 10 - 11)";

    public static final int                  APPLE_SECURE_NOTES                           = 16200;
    public static final int                  PASSWORD_SAFE_V2                             = 9000;
    public static final int                  PASSWORD_SAFE_V3                             = 5200;
    public static final int                  ANSIBLE_VAULT                                = 16900;
    public static final int                  PLAINTEXT                                    = 99999;

    public static final int                  DOMINO_5                                     = 8600;
    public static final String               DOMINO_5_NAME                                = "Lotus Notes/Domino 5";

    public static final Map<Integer, String> MODE_2_NAME_MAP                              =
        ImmutableMap.<Integer, String>builder()
            .put(MD4, MD4_NAME)
            .put(MD5, MD5_NAME)
            .put(HALF_MD5, HALF_MD5_NAME)
            .put(SHA1, SHA1_NAME)
            .put(LM, LM_NAME)
            .put(NTLM, NTLM_NAME)
            .put(_7_ZIP, _7_ZIP_NAME)
            .put(RAR3_HP, RAR3_HP_NAME)
            .put(RAR5, RAR5_NAME)
            .put(WINZIP, WINZIP_NAME)
            .put(MS_OFFICE_2003_$0_$1_MD5_RC4, MS_OFFICE_2003_$0_$1_MD5_RC4_NAME)
            .put(MS_OFFICE_2003_$0_$1_MD5_RC4_COLLIDER_1, MS_OFFICE_2003_$0_$1_MD5_RC4_COLLIDER_1_NAME)
            .put(MS_OFFICE_2003_$0_$1_MD5_RC4_COLLIDER_2, MS_OFFICE_2003_$0_$1_MD5_RC4_COLLIDER_2_NAME)
            .put(MS_OFFICE_2003_$3_$4_SHA1_RC4, MS_OFFICE_2003_$3_$4_SHA1_RC4_NAME)
            .put(MS_OFFICE_2003_$3_SHA1_RC4_COLLIDER_1, MS_OFFICE_2003_$3_SHA1_RC4_COLLIDER_1_NAME)
            .put(MS_OFFICE_2003_$3_SHA1_RC4_COLLIDER_2, MS_OFFICE_2003_$3_SHA1_RC4_COLLIDER_2_NAME)
            .put(MS_OFFICE_2007, MS_OFFICE_2007_NAME)
            .put(MS_OFFICE_2010, MS_OFFICE_2010_NAME)
            .put(MS_OFFICE_2013, MS_OFFICE_2013_NAME)
            .put(PDF_1_1_1_3_ACROBAT_2_4, PDF_1_1_1_3_ACROBAT_2_4_NAME)
            .put(PDF_1_1_1_3_ACROBAT_2_4_COLLIDER_1, PDF_1_1_1_3_ACROBAT_2_4_COLLIDER_1_NAME)
            .put(PDF_1_1_1_3_ACROBAT_2_4_COLLIDER_2, PDF_1_1_1_3_ACROBAT_2_4_COLLIDER_2_NAME)
            .put(PDF_1_4_1_6_ACROBAT_5_8, PDF_1_4_1_6_ACROBAT_5_8_NAME)
            .put(PDF_1_7_LEVEL_3_ACROBAT_9, PDF_1_7_LEVEL_3_ACROBAT_9_NAME)
            .put(PDF_1_7_LEVEL_8_ACROBAT_10_11, PDF_1_7_LEVEL_8_ACROBAT_10_11_NAME)
            .put(DOMINO_5, DOMINO_5_NAME)
            .put(MD5_$PASS_$SALT, MD5_$PASS_$SALT_NAME)
            .build();
}
