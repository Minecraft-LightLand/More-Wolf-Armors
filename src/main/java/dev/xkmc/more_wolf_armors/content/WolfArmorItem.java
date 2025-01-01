package dev.xkmc.more_wolf_armors.content;

import com.google.common.base.Suppliers;
import dev.xkmc.more_wolf_armors.data.MWAConfig;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.enchantment.Enchantment;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class WolfArmorItem extends AnimalArmorItem {

	private final ResourceLocation texture;
	private final ResourceLocation textureOverlay;
	private final Supplier<Item> particle;
	private final Supplier<ItemAttributeModifiers> defaultModifiers;

	public WolfArmorItem(Holder<ArmorMaterial> mat, boolean overlay, Supplier<Item> particle, Properties properties) {
		super(mat, AnimalArmorItem.BodyType.CANINE, overlay, properties);
		this.particle = particle;
		var tex = getMaterial().unwrapKey().orElseThrow().location().withPrefix("textures/entity/wolf/armor/");
		texture = tex.withSuffix(".png");
		textureOverlay = tex.withSuffix("_overlay.png");
		defaultModifiers = Suppliers.memoize(
				() -> {
					int def = material.value().getDefense(Type.CHESTPLATE) + 6;
					float tough = material.value().toughness();
					ItemAttributeModifiers.Builder builder = ItemAttributeModifiers.builder();
					EquipmentSlotGroup group = EquipmentSlotGroup.bySlot(type.getSlot());
					ResourceLocation id = ResourceLocation.withDefaultNamespace("armor." + type.getName());
					builder.add(Attributes.ARMOR,
							new AttributeModifier(id, def, AttributeModifier.Operation.ADD_VALUE), group);
					builder.add(Attributes.ARMOR_TOUGHNESS,
							new AttributeModifier(id, tough, AttributeModifier.Operation.ADD_VALUE), group);
					float res = material.value().knockbackResistance();
					if (res > 0.0F) {
						builder.add(Attributes.KNOCKBACK_RESISTANCE,
								new AttributeModifier(id, res, AttributeModifier.Operation.ADD_VALUE), group);
					}
					return builder.build();
				}
		);
	}

	@Override
	public ResourceLocation getTexture() {
		return texture;
	}

	@Override
	public boolean isEnchantable(ItemStack stack) {
		return MWAConfig.SERVER.enchantable.get();
	}

	@Override
	public boolean supportsEnchantment(ItemStack stack, Holder<Enchantment> enchantment) {
		if(! MWAConfig.SERVER.enchantable.get() )
			return false;
		if (! super.supportsEnchantment(stack, enchantment) )
			return false;
		if (MWAConfig.SERVER.chestArmorEnchantable.get())
			return true;
		return Items.ELYTRA.getDefaultInstance().supportsEnchantment(enchantment);
	}

	@Nullable
	@Override
	public ResourceLocation getOverlayTexture() {
		return textureOverlay;
	}

	public ItemStack getBreakParticleItem(ItemStack armor) {
		return particle.get().getDefaultInstance();
	}

	@Override
	public ItemAttributeModifiers getDefaultAttributeModifiers() {
		return this.defaultModifiers.get();
	}

}
