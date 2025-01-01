package dev.xkmc.more_wolf_armors.data;

import com.tterrag.registrate.providers.RegistrateItemTagsProvider;
import dev.xkmc.more_wolf_armors.init.MoreWolfArmors;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public class MWATagGen {

	public static final TagKey<Item> WOLF_ARMORS = make(MoreWolfArmors.MODID, "wolf_armor");
	public static final TagKey<Item> C_WOLF_ARMORS = make("c", "wolf_armor");

	private static TagKey<Item> make(String namespace, String id) {
		return ItemTags.create(
				ResourceLocation.fromNamespaceAndPath(namespace, id)
		);
	}

	public static void genTags(RegistrateItemTagsProvider pvd) {
		pvd.addTag(C_WOLF_ARMORS).addTag(WOLF_ARMORS).add(Items.WOLF_ARMOR);
		pvd.addTag(ItemTags.DURABILITY_ENCHANTABLE).addTag(WOLF_ARMORS);
		pvd.addTag(ItemTags.CHEST_ARMOR_ENCHANTABLE).addTag(WOLF_ARMORS);
	}
}
