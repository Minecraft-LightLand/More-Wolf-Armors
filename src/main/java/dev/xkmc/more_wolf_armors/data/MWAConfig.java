package dev.xkmc.more_wolf_armors.data;

import dev.xkmc.l2core.util.ConfigInit;
import dev.xkmc.more_wolf_armors.init.MoreWolfArmors;
import net.neoforged.neoforge.common.ModConfigSpec;

public class MWAConfig {

	public static class Client extends ConfigInit {

		Client(Builder builder) {
			markPlain();
		}

	}

	public static class Server extends ConfigInit {

		public final ModConfigSpec.BooleanValue enchantable;
		public final ModConfigSpec.IntValue ingotRepairFactor;

		Server(Builder builder) {
			markPlain();
			enchantable = builder.text("Enchantable Wolf Armor")
					.comment("Makes wolf armors from this mod enchantable with durability enchantments")
					.define("enchantable", true);
			ingotRepairFactor = builder.text("Ingot Repair Factor")
					.comment("How many ingots to consume to repair wolf armors from zero durability to full.")
					.comment("Set it to 0 to disable repair")
					.defineInRange("ingotRepairFactor", 8, 0, 64);
		}

	}

	public static final Client CLIENT = MoreWolfArmors.REGISTRATE.registerClient(Client::new);
	public static final Server SERVER = MoreWolfArmors.REGISTRATE.registerSynced(Server::new);

	public static void init() {
	}

}
