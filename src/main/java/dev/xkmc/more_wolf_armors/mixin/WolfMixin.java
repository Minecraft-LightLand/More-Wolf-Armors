package dev.xkmc.more_wolf_armors.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import dev.xkmc.more_wolf_armors.content.WolfArmorItem;
import dev.xkmc.more_wolf_armors.data.MWATagGen;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Wolf.class)
public abstract class WolfMixin extends TamableAnimal {

	protected WolfMixin(EntityType<? extends TamableAnimal> entityType, Level level) {
		super(entityType, level);
	}

	@WrapOperation(method = "actuallyHurt", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/Item;getDefaultInstance()Lnet/minecraft/world/item/ItemStack;"))
	public ItemStack moreWolfArmors$actuallyHurt$armorParticle(Item instance, Operation<ItemStack> original) {
		ItemStack armor = getBodyArmorItem();
		if (armor.getItem() instanceof WolfArmorItem item) {
			return item.getBreakParticleItem(armor);
		}
		return original.call(instance);
	}

	@WrapOperation(method = "hasArmor", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z"))
	public boolean moreWolfArmors$hasArmor$useTags(ItemStack stack, Item item, Operation<Boolean> original) {
		return stack.is(MWATagGen.WOLF_ARMORS) || original.call(stack, item);
	}

}
