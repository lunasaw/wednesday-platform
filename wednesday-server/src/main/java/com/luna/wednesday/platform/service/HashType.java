package com.luna.wednesday.platform.service;

/**
 * @author luna@mac
 * 2021年03月30日 20:16:00
 */
public enum HashType {
    /**
     * hashcat类型与calculation类型对应
     *
     */
    SHA256("1400", "sha256"),
    MD5("0", "md5");

    public static String getHashcatHashType(String calculationObjectType) {
        for (HashType hashType : HashType.values()) {
            if (hashType.getCalculationObjectType().equals(calculationObjectType)) {
                return hashType.hashcatHashType;
            }
        }
        return null;
    }

    public static String getCalculationObjectType(String hashcatHashType) {
        for (HashType hashType : HashType.values()) {
            if (hashType.getHashcatHashType().equals(hashcatHashType)) {
                return hashType.calculationObjectType;
            }
        }
        return null;
    }

    private String hashcatHashType;

    private String calculationObjectType;

    HashType(String hashcatHashType, String calculationObjectType) {
        this.hashcatHashType = hashcatHashType;
        this.calculationObjectType = calculationObjectType;
    }

    public String getHashcatHashType() {
        return hashcatHashType;
    }

    public void setHashcatHashType(String hashcatHashType) {
        this.hashcatHashType = hashcatHashType;
    }

    public String getCalculationObjectType() {
        return calculationObjectType;
    }

    public void setCalculationObjectType(String calculationObjectType) {
        this.calculationObjectType = calculationObjectType;
    }

}
