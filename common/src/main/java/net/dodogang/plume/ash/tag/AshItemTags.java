package net.dodogang.plume.ash.tag;

import net.dodogang.plume.ash.Environment;
import net.dodogang.plume.ash.Platform;
import net.minecraft.item.Item;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;

public class AshItemTags {
    public static final Tag<Item> BOOKSHELVES = platformTag("bookshelves");

    public static final Tag<Item> CHESTS = platformTag("chests", "chests");
    public static final Tag<Item> ENDER_CHESTS = platformTag("ender_chests", "chests/ender");
    public static final Tag<Item> TRAPPED_CHESTS = platformTag("trapped_chests", "chests/trapped");
    public static final Tag<Item> WOODEN_CHESTS = platformTag("wooden_chests", "chests/wooden");

    private static Tag<Item> platformTag(String fabricPath, String forgePath) {
        if (Environment.getPlatform() == Platform.FORGE) {
            return TagRegistry.item(new Identifier("forge", forgePath));
        } else {
            return TagRegistry.item(new Identifier("c", fabricPath));
        }
    }

    private static Tag<Item> platformTag(String path) {
        return platformTag(path, path);
    }
}
