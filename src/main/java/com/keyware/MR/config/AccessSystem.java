package com.keyware.MR.config;

import java.io.Serializable;

/**
 * @author YaoJz
 * @description
 * @date 2024/4/2 11:51
 */
public class AccessSystem implements Serializable {
    private static final long serialVersionUID = 8333665889439802146L;

    private String systemId;

    private String secretKey;

    public AccessSystem() {
    }

    public AccessSystem(String systemId, String secretKey) {
        this.systemId = systemId;
        this.secretKey = secretKey;
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("{")
                .append("\"systemId\":").append(systemId)
                .append(", \"secretKey\":").append("******")
                .append('}');
        return sb.toString();
    }
}
