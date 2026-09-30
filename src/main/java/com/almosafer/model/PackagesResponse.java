package com.almosafer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PackagesResponse {
    private String pkgSId; // Assuming API returns package session id or id

    public String getPkgSId() {
        return pkgSId;
    }

    public void setPkgSId(String pkgSId) {
        this.pkgSId = pkgSId;
    }
}