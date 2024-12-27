package dev.xkmc.more_wolf_armors.init;

import com.tterrag.registrate.providers.ProviderType;
import dev.xkmc.l2core.init.reg.registrate.L2Registrate;
import dev.xkmc.l2serial.network.PacketHandler;
import dev.xkmc.more_wolf_armors.data.MWAConfig;
import dev.xkmc.more_wolf_armors.data.MWARecipeGen;
import dev.xkmc.more_wolf_armors.data.MWATagGen;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(MoreWolfArmors.MODID)
@EventBusSubscriber(modid = MoreWolfArmors.MODID, bus = EventBusSubscriber.Bus.MOD)
public class MoreWolfArmors {

	public static final String MODID = "more_wolf_armors";
	public static final Logger LOGGER = LogManager.getLogger();

	public static final L2Registrate REGISTRATE = new L2Registrate(MODID);

	public static final PacketHandler HANDLER = new PacketHandler(MODID, 1);

	public MoreWolfArmors() {
		MWAItems.register();
		MWAConfig.init();
	}

	@SubscribeEvent
	public static void onCommonInit(FMLCommonSetupEvent event) {
	}

	@SubscribeEvent(priority = EventPriority.HIGH)
	public static void onDataGen(GatherDataEvent event) {
		REGISTRATE.addDataGenerator(ProviderType.ITEM_TAGS, MWATagGen::genTags);
		REGISTRATE.addDataGenerator(ProviderType.RECIPE, MWARecipeGen::genRecipes);
	}


}
