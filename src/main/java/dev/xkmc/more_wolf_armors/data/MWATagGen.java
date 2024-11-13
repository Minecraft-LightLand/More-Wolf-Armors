package dev.xkmc.more_wolf_armors.data;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class MWATagGen {

	public static final TagKey<Item> WOLF_ARMORS = make("c", "wolf_armor");

	private static TagKey<Item> make(String namespace, String id) {
		return ItemTags.create(
				ResourceLocation.fromNamespaceAndPath(namespace, id)
		);
	}

}
