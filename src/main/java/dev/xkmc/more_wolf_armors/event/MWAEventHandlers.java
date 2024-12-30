package dev.xkmc.more_wolf_armors.event;

import dev.xkmc.more_wolf_armors.content.WolfArmorItem;
import dev.xkmc.more_wolf_armors.data.MWAConfig;
import dev.xkmc.more_wolf_armors.init.MoreWolfArmors;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

@EventBusSubscriber(modid = MoreWolfArmors.MODID, bus = EventBusSubscriber.Bus.GAME)
public class MWAEventHandlers {

	@SubscribeEvent
	public static void interact(PlayerInteractEvent.EntityInteract event) {
		var player = event.getEntity();
		if (event.getTarget() instanceof Wolf wolf && wolf.isTame() &&
				wolf.isOwnedBy(player)) {
			ItemStack stack = event.getItemStack();
			ItemStack armor = wolf.getBodyArmorItem();
			if (armor.isEmpty()) {
				if (stack.getItem() instanceof WolfArmorItem) {
					if (!event.getTarget().level().isClientSide()) {
						wolf.setBodyArmorItem(stack.copyWithCount(1));
						stack.consume(1, player);
					}
					event.setCancellationResult(InteractionResult.SUCCESS);
					event.setCanceled(true);
				}
			} else {
				int factor = MWAConfig.SERVER.ingotRepairFactor.getAsInt();
				if (wolf.isInSittingPose() && armor.isDamaged() && factor > 0 &&
						armor.getItem() instanceof WolfArmorItem wolfArmor &&
						wolfArmor.getMaterial().value().repairIngredient().get().test(stack)) {
					if (!event.getTarget().level().isClientSide()) {
						stack.shrink(1);
						wolf.playSound(SoundEvents.WOLF_ARMOR_REPAIR);
						armor.setDamageValue(Math.max(0, armor.getDamageValue() - armor.getMaxDamage() / factor));
					}
					event.setCancellationResult(InteractionResult.SUCCESS);
					event.setCanceled(true);
				}
			}
		}
	}

}
