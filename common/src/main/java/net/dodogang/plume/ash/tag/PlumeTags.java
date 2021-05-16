package net.dodogang.plume.ash.tag;

import net.dodogang.plume.ash.Environment;
import net.dodogang.plume.ash.Platform;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;

public final class PlumeTags {
    public static class Blocks {
        public static final Tag<Block> BOOKSHELVES = platformTag("bookshelves");

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

    public static class Items {
        public static final Tag<Item> BOOKSHELVES = platformTag("bookshelves");

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
}
