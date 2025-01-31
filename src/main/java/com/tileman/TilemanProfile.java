/*
 * Copyright (c) 2022, Colton Campbell <https://github.com/Notloc>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.tileman;

import lombok.Getter;

import java.io.Serializable;

public class TilemanProfile implements Serializable {

    public static final TilemanProfile NONE = new TilemanProfile(-1, "None");

    @Getter private final String accountHash;
    @Getter private final String profileName;

    public TilemanProfile(long accountHash, String profileName) {
        this.accountHash = String.valueOf(accountHash);
        this.profileName = profileName;
    }

    @Override
    public String toString() {
        return profileName;
    }

    public String getProfileKey() {
        return getProfileKey(accountHash);
    }

    public static String getProfileKey(String accountHash) {
        return accountHash + "_profile";
    }

    public String getGameRulesKey() {
        return accountHash + "_gamerules";
    }

    public String getRegionKey(int regionId) {
        return getRegionPrefix() + regionId;
    }

    public String getRegionPrefix() {
        return accountHash + "_region_";
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof TilemanProfile)) {
            return false;
        }
        TilemanProfile other = (TilemanProfile)o;
        return accountHash.equals(other.getAccountHash());
    }

    @Override
    public int hashCode() {
        return accountHash.hashCode();
    }
}
