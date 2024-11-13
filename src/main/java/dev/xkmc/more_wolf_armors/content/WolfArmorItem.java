package dev.xkmc.more_wolf_armors.content;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.AnimalArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.function.Supplier;

public class WolfArmorItem extends AnimalArmorItem {

	private final ResourceLocation texture;
	private final Supplier<Item> particle;

	public WolfArmorItem(Holder<ArmorMaterial> mat, boolean overlay, Supplier<Item> particle, Properties properties) {
		super(mat, AnimalArmorItem.BodyType.CANINE, overlay, properties);
		this.particle = particle;
		texture = getMaterial().unwrapKey().orElseThrow().location().withPath(e -> "textures/entity/wolf/armor/" + e + ".png");
	}

	@Override
	public ResourceLocation getTexture() {
		return texture;
	}

	public ItemStack getBreakParticleItem(ItemStack armor) {
		return particle.get().getDefaultInstance();
	}

}
