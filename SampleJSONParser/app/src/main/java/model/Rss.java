/*
 * Copyright © 2015, Kotak Mahindra Bank
 * Written under contract by Robosoft Technologies Pvt. Ltd.
 */

package model;

import java.io.Serializable;

/**
 * Created by varnesh on 25/2/16.
 */
public class Rss implements Serializable {

    private String dc;
    private String content;
    private String atom;

    public String getDc() {
        return dc;
    }

    public String getContent() {
        return content;
    }

    public String getAtom() {
        return atom;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAtom(String atom) {
        this.atom = atom;
    }

    public void setDc(String dc) {
        this.dc = dc;
    }

    @Override
    public String toString() {
        String response =  "DC: " + dc
                + "\ncontent: " + content
                + "\natom: " + atom;
        return response;
    }
}
