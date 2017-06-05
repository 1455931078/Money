package money.command;

import cn.nukkit.Server;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandParameter;
import money.CurrencyType;
import money.Money;

import java.util.Map;

/**
 * @author Him188 @ Money Project
 */
public class GiveOnline2Command extends MoneyCommand {
	public GiveOnline2Command(String name, Money owner, String[] aliases,
	                          Map<String, CommandParameter[]> commandParameters) {
		super(name, owner, aliases, commandParameters);
		this.setPermission("money.command.giveonline2");
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!this.testPermission(sender)) {
			//sender.sendMessage(this.getPlugin().translateMessage("has-no-permission"));
			return true;
		}

		if (args.length < 1) {
			sender.sendMessage(this.getPlugin().translateMessage("give-online-format-error", "cmd", this.getName()));
			return true;
		}

		float to;

		try {
			to = Float.parseFloat(args[0]);
		} catch (NumberFormatException e) {
			sender.sendMessage(this.getPlugin().translateMessage("number-format-error"));
			return true;
		}

		final String message = getPlugin().translateMessage("give-online-for-you",
				"name", sender.getName(),
				"amount", to,
				"type", getPlugin().getCurrency2());
		Server.getInstance().getOnlinePlayers().forEach((uuid, player) -> {
			getPlugin().addMoney(player, to, CurrencyType.SECOND);
			player.sendMessage(message);
		});

		int count = getPlugin().addAllMoney(to, CurrencyType.SECOND);

		sender.sendMessage(getPlugin().translateMessage("give-online-done",
				"count", count,
				"type", getPlugin().getCurrency2(),
				"amount", to));
		return true;
	}
}
