package com.tileman;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class TilemanGameRules implements Serializable {

    @Getter @Setter private TilemanGameMode gameMode;
    @Getter @Setter private boolean enableCustomGameMode;

    @Setter private boolean allowTileDeficit;
    @Setter private boolean tilesFromExp;
    @Setter private int expPerTile;
    @Setter private boolean tilesFromTotalLevel;
    @Setter private int tilesOffset;

    @Getter @Setter private String groupServerURL;

    public TilemanGameRules() {}

    public TilemanGameRules(TilemanGameMode gameMode, boolean enableCustomGameMode, boolean allowTileDeficit, boolean tilesFromTotalLevel, boolean tilesFromExp, int tilesOffset, int expPerTile, String serverURL) {
        this.gameMode = gameMode;
        this.enableCustomGameMode = enableCustomGameMode;
        this.allowTileDeficit = allowTileDeficit;
        this.tilesFromTotalLevel = tilesFromTotalLevel;
        this.tilesFromExp = tilesFromExp;
        this.tilesOffset = tilesOffset;
        this.expPerTile = expPerTile;
        this.groupServerURL = serverURL;
    }

    public static TilemanGameRules GetDefaultRules() {
        return new TilemanGameRules(
                TilemanGameMode.COMMUNITY,
                false,
                false,
                false,
                true,
                9,
                1000,
                ""
        );
    }

    public boolean isTilesFromTotalLevel() {
        return enableCustomGameMode ? tilesFromTotalLevel : getTilesFromTotalLevelByGameMode();
    }

    public int getTilesOffset() {
        return enableCustomGameMode ? tilesOffset : getTilesOffsetByGameMode();
    }

    public boolean isAllowTileDeficit() {
        return enableCustomGameMode && allowTileDeficit;
    }

    public boolean isTilesFromExp() {
        return !enableCustomGameMode || tilesFromExp;
    }

    public int getExpPerTile() {
        return enableCustomGameMode ? expPerTile : 1000;
    }

    private boolean getTilesFromTotalLevelByGameMode() {
        switch (gameMode) {
            case ACCELERATED:
            case GROUP:
                return true;
            default:
                return false;
        }
    }

    private int getTilesOffsetByGameMode() {
        switch (gameMode) {
            case COMMUNITY:
                return 9;
            default:
                return 0;
        }
    }
}
