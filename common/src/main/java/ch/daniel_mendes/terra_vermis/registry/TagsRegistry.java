package ch.daniel_mendes.terra_vermis.registry;

import ch.daniel_mendes.terra_vermis.Constants;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class TagsRegistry {

    public static void init() {}

    public static class BlockTags {
        public static final TagKey<Block> FERTILE_BLOCKS = create("fertile_blocks");
        public static final TagKey<Block> HIBERNATION_BLOCKS = create("hibernation_blocks");
        public static final TagKey<Block> MUDDY_BLOCKS = create("muddy_blocks");
        public static final TagKey<Block> WORMY_BLOCKS = create("wormy_blocks");

        private static TagKey<Block> create(String name) {
            return TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, name));
        }
    }
}