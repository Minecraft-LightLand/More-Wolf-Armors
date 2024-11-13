package dev.xkmc.more_wolf_armors.data;

import dev.xkmc.l2core.util.ConfigInit;
import dev.xkmc.more_wolf_armors.init.MoreWolfArmors;

public class MWAConfig {

	public static class Client extends ConfigInit {

		Client(Builder builder) {
			markPlain();
		}

	}

	public static class Server extends ConfigInit {

		Server(Builder builder) {
			markPlain();
		}

	}

	public static final Client CLIENT = MoreWolfArmors.REGISTRATE.registerClient(Client::new);
	public static final Server SERVER = MoreWolfArmors.REGISTRATE.registerSynced(Server::new);

	public static void init() {
	}

}
