/*
 * Copyright (c) 2018, Fitzyy. All Rights Reserved.
 */

package me.fitzyy.tool.sap;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import me.fitzyy.tool.StringPool;


/**
 * <p> </p>
 *
 * @author sog
 * @version 1.0
 * @since JDK 1.7
 */
public class SapProp implements Cloneable {
    /**
     * SAP链接名称
     */
    private final String name;
    private final String host;
    private final String r3name;
    private final String client;
    private final String systemNumber;
    private final String user;
    private final String password;
    private final String language;
    private final String poolCapacity;
    private final String peakLimit;
    private final String saprouter;

    public SapProp(String name, String host, String r3name, String client, String systemNumber, String user, String password, String language, String poolCapacity, String peakLimit, String saprouter) {
        this.name = name;
        this.host = host;
        this.r3name = r3name;
        this.client = client;
        this.systemNumber = systemNumber;
        this.user = user;
        this.password = password;
        this.language = language;
        this.poolCapacity = poolCapacity;
        this.peakLimit = peakLimit;
        this.saprouter = saprouter;
    }

    public SapProp(String name, String host, String client, String systemNumber, String user, String password, String language) {
        this.name = name;
        this.host = host;
        this.r3name = StringPool.EMPTY;
        this.client = client;
        this.systemNumber = systemNumber;
        this.user = user;
        this.password = password;
        this.language = language;
        this.peakLimit = StringPool.EMPTY;
        this.poolCapacity = StringPool.EMPTY;
        this.saprouter = StringPool.EMPTY;
    }

    public SapProp(String name, String host, String client, String systemNumber, String user, String password, String language, String poolCapacity) {
        this.name = name;
        this.host = host;
        this.r3name = StringPool.EMPTY;
        this.client = client;
        this.systemNumber = systemNumber;
        this.user = user;
        this.password = password;
        this.language = language;
        this.peakLimit = StringPool.EMPTY;
        this.poolCapacity = poolCapacity;
        this.saprouter = StringPool.EMPTY;
    }


    public SapProp(String name, String host, String r3name, String client, String systemNumber,
                   String user, String password, String language, String saprouter) {
        this.name = name;
        this.client = client;
        this.user = user;
        this.password = password;
        this.host = host;
        this.systemNumber = systemNumber;
        this.language = language;
        this.peakLimit = StringPool.EMPTY;
        this.poolCapacity = StringPool.EMPTY;
        this.r3name = r3name;
        this.saprouter = saprouter;
    }


    public String getName() {
        return name;
    }

    public String getHost() {
        return host;
    }


    public String getR3name() {
        return r3name;
    }

    public String getClient() {
        return client;
    }

    public String getSystemNumber() {
        return systemNumber;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getLanguage() {
        return language;
    }

    public String getPoolCapacity() {
        return poolCapacity;
    }

    public String getPeakLimit() {
        return peakLimit;
    }

    public String getSaprouter() {
        return saprouter;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("name", name)
                .add("host", host)
                .add("r3name", r3name)
                .add("client", client)
                .add("systemNumber", systemNumber)
                .add("user", user)
                .add("password", password)
                .add("language", language)
                .add("poolCapacity", poolCapacity)
                .add("peakLimit", peakLimit)
                .add("saprouter", saprouter)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SapProp that = (SapProp) o;
        return Objects.equal(name, that.name) &&
                Objects.equal(host, that.host) &&
                Objects.equal(r3name, that.r3name) &&
                Objects.equal(client, that.client) &&
                Objects.equal(systemNumber, that.systemNumber) &&
                Objects.equal(user, that.user) &&
                Objects.equal(password, that.password) &&
                Objects.equal(language, that.language) &&
                Objects.equal(poolCapacity, that.poolCapacity) &&
                Objects.equal(peakLimit, that.peakLimit) &&
                Objects.equal(saprouter, that.saprouter);
    }

    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public int hashCode() {
        return Objects.hashCode(name, host, r3name, client, systemNumber, user, password, language, poolCapacity, peakLimit, saprouter);
    }
}
