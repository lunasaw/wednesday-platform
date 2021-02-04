package com.luna.wednesday.platform.dto;

import javax.validation.constraints.NotEmpty;

/**
 * @author Tony
 */
public class StatusDTO {

    @NotEmpty
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
