package dev.xkmc.more_wolf_armors.init;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(value = Dist.CLIENT, modid = MoreWolfArmors.MODID, bus = EventBusSubscriber.Bus.MOD)
public class MWAClient {

	@SubscribeEvent
	public static void clientSetup(FMLClientSetupEvent event) {
	}

}
