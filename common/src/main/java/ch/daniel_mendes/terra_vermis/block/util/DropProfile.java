package ch.daniel_mendes.terra_vermis.block.util;

public record DropProfile(int min, int max, float lowChance) {
    private static final DropProfile DEFAULT = new DropProfile(0, 0, 1.0f);

    static DropProfile getDropProfile(boolean isHibernating, boolean isFertile, boolean isWormy, boolean isMuddy, boolean isRaining) {
        if (isHibernating) return new DropProfile(0, 1, 0.7f);
        if (isFertile && isRaining) return new DropProfile(2, 4, 0.3f);
        if (isFertile) return new DropProfile(2, 4, 0.5f);
        if (isWormy && isRaining) return new DropProfile(1, 3, 0.3f);
        if (isWormy) return new DropProfile(1, 3, 0.7f);
        if (isMuddy && isRaining) return new DropProfile(1, 2, 0.7f);
        if (isMuddy) return new DropProfile(0, 1, 0.7f);
        return DEFAULT;
    }
}