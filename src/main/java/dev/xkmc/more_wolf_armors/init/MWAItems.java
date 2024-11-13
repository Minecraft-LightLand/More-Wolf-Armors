package dev.xkmc.more_wolf_armors.init;

import com.tterrag.registrate.util.entry.ItemEntry;
import dev.xkmc.more_wolf_armors.content.WolfArmorItem;
import dev.xkmc.more_wolf_armors.data.MWATagGen;
import net.minecraft.world.item.*;

public class MWAItems {

	public static final ItemEntry<Item> TEMPLATE;
	public static final ItemEntry<WolfArmorItem> IRON;

	static {
		MoreWolfArmors.REGISTRATE.defaultCreativeTab(CreativeModeTabs.INGREDIENTS);
		TEMPLATE = MoreWolfArmors.REGISTRATE.item("wolf_armor_upgrade_template", Item::new)
				.register();
		MoreWolfArmors.REGISTRATE.defaultCreativeTab(CreativeModeTabs.COMBAT);
		IRON = MoreWolfArmors.REGISTRATE.item("iron_wolf_armor", p -> new WolfArmorItem(
				ArmorMaterials.IRON, false, () -> Items.IRON_INGOT,
				new Item.Properties().durability(ArmorItem.Type.BODY.getDurability(15))
		)).tag(MWATagGen.WOLF_ARMORS).register();
	}

	public static void register() {

	}

}
