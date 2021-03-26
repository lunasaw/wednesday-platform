package com.luna.wednesday.platform.dto;

/**
 * @author Tony
 */
public class FoundDTO {
    /** 密文 */
    private String hash;

    /** 明文 */
    private String plain;

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getPlain() {
        return plain;
    }

    public void setPlain(String plain) {
        this.plain = plain;
    }

    public FoundDTO() {}

    public FoundDTO(String hash, String plain) {
        this.hash = hash;
        this.plain = plain;
    }
}
