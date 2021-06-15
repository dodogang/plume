package net.dodogang.plume.ash.tag;

import net.dodogang.plume.ash.Environment;
import net.dodogang.plume.ash.Platform;
import net.minecraft.block.Block;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;

public class AshBlockTags {
    public static final Tag<Block> BOOKSHELVES = platformTag("bookshelves");

    public static final Tag<Block> CHESTS = platformTag("chests", "chests");
    public static final Tag<Block> ENDER_CHESTS = platformTag("ender_chests", "chests/ender");
    public static final Tag<Block> TRAPPED_CHESTS = platformTag("trapped_chests", "chests/trapped");
    public static final Tag<Block> WOODEN_CHESTS = platformTag("wooden_chests", "chests/wooden");

    private static Tag<Block> platformTag(String fabricPath, String forgePath) {
        if (Environment.getPlatform() == Platform.FORGE) {
            return TagRegistry.block(new Identifier("forge", forgePath));
        } else {
            return TagRegistry.block(new Identifier("c", fabricPath));
        }
    }

    private static Tag<Block> platformTag(String path) {
        return platformTag(path, path);
    }
}
