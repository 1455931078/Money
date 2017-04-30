package money.command;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandParameter;
import money.Money;

import java.util.Map;

/**
 * @author Him188 @ Money Project
 * @since Money 1.0.0
 */
public class WalletInfo2Command extends MoneyCommand {
	public WalletInfo2Command(String name, Money owner, String[] aliases, Map<String, CommandParameter[]> commandParameters) {
		super(name, owner, aliases, commandParameters);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!this.testPermission(sender)) {
			sender.sendMessage(this.getPlugin().translateMessage("has-no-permission"));
			return true;
		}
		if (!(sender instanceof Player)) {
			sender.sendMessage(this.getPlugin().translateMessage("use-in-game"));
			return true;
		}

		sender.sendMessage(this.getPlugin().translateMessage("your-money-2", Math.round(getPlugin().getMoney((Player) sender, false)), getPlugin().getMoneyUnit1()));
		return true;
	}
}
